let keywords = [];
const mode = new URLSearchParams(location.search).get("mode");

window.onload = () => {
    checkLogin().then(user => {
        if (user) {
            loadEvent();
            detectMode();
            loadData(user);
        }
    });
};

function loadEvent() {
    document.getElementById("keywordInput").addEventListener("keypress", (e) => {
        if (e.key === "Enter") addKeyword();
    });
}

function loadData(user) {
    $.ajax({
        url: "/api/user/keyword/selectUserKeywordList",
        method: "POST",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            userId: user.userId
        }),
        success: function (data) {
            $.each(data, function (idx, item) {
                addKeyword(item.keyword);
            }) ;
        },
        error: function (data, status, err) {
        }
    });
}

// 키워드 추가
function addKeyword(value) {
    const input = document.getElementById("keywordInput");
    if(value == null || value == undefined) {
        value = input.value.trim();
    }

    if (value === "") return;

    // 중복 방지
    if (keywords.includes(value)) {
        alert("이미 추가된 키워드입니다.");
        input.value = "";
        return;
    }

    keywords.push(value);
    input.value = "";
    renderKeywords();
}

// 키워드 삭제
function removeKeyword(keyword) {
    keywords = keywords.filter(k => k !== keyword);
    renderKeywords();
}

// 태그 렌더링
function renderKeywords() {
    const list = document.getElementById("keywordList");
    list.innerHTML = "";

    keywords.forEach(k => {
        const tag = document.createElement("div");
        tag.classList.add("tag");
        tag.innerHTML = `
            ${k}
            <span class="remove" onclick="removeKeyword('${k}')">x</span>
        `;
        list.appendChild(tag);
    });
}

// 나중에 설정 = 초기 모드일 경우 다음 화면으로 이동
function confirmSkip() {
    location.href = "/news/feed";
}

function goNext() {
    checkLogin().then(user => {
        if (user) {
            $.ajax({
                url: "/api/user/keyword/updateUserKeyword",
                method: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify({
                    userId: user.userId
                    , keywords: Array.from(keywords)
                }),
                success: function (data) {
                    if (data.resultMsg == "Success") {
                        location.href = "/news/feed";
                    } else {
                        alert("저장 실패");
                    }
                },
                error: function (data, status, err) {
                }
            });
        }
    });
}
