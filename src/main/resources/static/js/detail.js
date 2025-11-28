// 뒤로가기 시 스크롤 위치 유지
function goBackToFeed() {
    const savedScroll = sessionStorage.getItem("feedScroll");
    history.back();

    setTimeout(() => {
        if (savedScroll) {
            window.scrollTo(0, savedScroll);
        }
    }, 10);
}
let currentDetailArticleId = null;

// 상세 페이지 초기화
window.onload = async () => {
    const params = new URLSearchParams(window.location.search);
    currentDetailArticleId = params.get("articleId");

    if (!currentDetailArticleId) return;

    const scrollTop = sessionStorage.getItem("feedScroll");

    // API 호출
    $.ajax({
        url: "/api/newsdata/detail",
        method: "POST",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            articleId: currentDetailArticleId
            , translate : true
            , targetLang : "ko"
        }),
        success: function (item) {
    renderDetail(item);
        }
    });
};
function renderDetail(item) {

    // =======================
    //  기본 텍스트 처리
    // =======================
    const title =
        item.titleTranslated ??
        item.title ??
        "제목 없음";

    const content =
        item.contentTranslated ??
        item.descriptionTranslated ??
        (item.content === "ONLY AVAILABLE IN PAID PLANS" || item.content === "유료 요금제에서만 이용 가능" ? null : item.content) ??
        item.description ??
        "내용 데이터가 제공되지 않는 기사입니다.";

    // =======================
    //  이미지 처리
    // =======================
    const img =
        item.image_url && !item.image_url.includes("null")
            ? item.image_url
            : "https://img.icons8.com/?size=100&id=-5tLho_7N4sS&format=png&color=FFFFFF";

    $("#newsImage").attr("src", img);

    // =======================
    // Press / Date / Sentiment
    // =======================
    $("#pressName").text(item.source_name || item.source_id);
    $("#newsDate").text(item.pubDate);

    // =======================
    // 배열 처리
    // =======================
    // 카테고리
    $("#detailCategory").text(
        item.category?.length ? item.category.join(", ") : "-"
    );

    // AI 태그
    let aiTag = item.ai_tag;
    if (!aiTag || aiTag.includes("ONLY AVAILABLE")) {
        aiTag = "분석 정보 없음";
    }
    $("#detailAiTag").text(aiTag);

    // 국가
    $("#detailCountry").text(item.country?.join(", "));

    // =======================
    // 제목/본문 출력
    // =======================
    $("#newsTitle").text(title);
    $("#newsContent").text(content);

    // 원문보기
    $("#openOriginalBtn").on("click", () => {
        window.open(item.link, "_blank");
    });
}

async function requestAiSummary() {

    const articleText = $("#newsContent").text();

    $("#aiResultContainer").removeClass("hidden");
    $("#aiSummary").text("AI가 분석 중입니다...");
    $("#aiAnalysis").empty();
    $("#aiKeywords").empty();

    try {
        const response = await fetch("/api/ai/summarize", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                articleId: currentDetailArticleId,
                content: articleText
            })
        });

        const data = await response.json();

        // summary
        $("#aiSummary").text(data.summary || "요약 결과 없음");

        // analysis
        $("#aiAnalysis").empty();
        if (Array.isArray(data.analysis)) {
            data.analysis.forEach(item => {
                $("#aiAnalysis").append(`<li>${item}</li>`);
            });
        }

        // keywords
        $("#aiKeywords").empty();
        if (Array.isArray(data.keywords)) {
            data.keywords.forEach(k => {
                $("#aiKeywords").append(`<span>${k}</span>`);
            });
        }

    } catch (err) {
        $("#aiSummary").text("AI 분석 요청 중 오류가 발생했습니다.");
    }
}
