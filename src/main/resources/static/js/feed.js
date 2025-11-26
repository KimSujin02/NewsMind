document.addEventListener("DOMContentLoaded", () => {
    loadDummyNews();

    document.querySelector(".refresh-btn").addEventListener("click", () => {
        loadDummyNews();
    });
});

// --- 더미 데이터 렌더링 ---
function loadDummyNews() {

    const dummy = [
        {
            title: "프랑스, 유엔총회에서 팔레스타인 국가 인정 선언",
            category: "world",
            tags: "government",
            date: "23 Sep 2025 오전 5:45:00 (UTC)",
            status: "정상"
        },
        {
            title: "중국 경제성장률 발표… 글로벌 시장 영향은?",
            category: "business",
            tags: "economy",
            date: "25 Sep 2025 오후 3:20:00 (UTC)",
            status: "정상"
        }
    ];

    const newsList = document.getElementById("newsList");
    newsList.innerHTML = "";

    dummy.forEach(item => {
        newsList.innerHTML += `
            <div class="news-card">
                <div class="news-thumb"></div>
                <div class="news-content">
                    <div class="news-title">${item.title}</div>
                    <div class="news-category">Category <b>${item.category}</b></div>
                    <div class="news-tags">AI Tags: <b>${item.tags}</b></div>
                    <div class="news-date">${item.date} <span class="news-status">${item.status}</span></div>
                </div>
            </div>
        `;
    });
}
