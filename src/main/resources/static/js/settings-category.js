// 카테고리 목록
const categories = [
    "비즈니스/경제","정치","사회",
    "IT/기술","스포츠","엔터테인먼트",
    "건강","세계","문화","환경"
]

let selected = new Set();

// 화면 로드
window.onload = () => {
    renderButtons();
    detectMode();
};

// 버튼 생성
function renderButtons() {
    const grid = document.getElementById("categoryGrid");

    categories.forEach(c => {
        const btn = document.createElement("div");
        btn.classList.add("category-btn");
        btn.innerText = c;
        btn.onclick = () => toggleSelect(c, btn);
        grid.appendChild(btn);
    });
}

// 선택 토글
function toggleSelect(category, btn) {
    if (selected.has(category)) {
        selected.delete(category);
        btn.classList.remove("selected");
    } else {
        selected.add(category);
        btn.classList.add("selected");
    }
}

// ------------------------------------------------------------
// 🎯 init / edit 모드 감지
// ------------------------------------------------------------
function detectMode() {
    const mode = new URLSearchParams(location.search).get("mode");

    if (mode === "init") {
        // init 모드 표시
        document.getElementById("initHeader").classList.remove("hidden");
        document.getElementById("initBtns").classList.remove("hidden");
    } else {
        // edit 모드 표시
        document.getElementById("editHeader").classList.remove("hidden");
        document.getElementById("editBtns").classList.remove("hidden");
    }
}

// ------------------------------------------------------------
// 🎯 init 모드 : 팝업
// ------------------------------------------------------------
function confirmSkip() {
    // 키워드 설정 화면으로 이동
    location.href = "/settings/keyword?mode=init";
}

// ------------------------------------------------------------
// 🎯 저장 버튼
// ------------------------------------------------------------
function goNext() {
    // ★ 백엔드 저장 API 연결할 예정
    /*
    fetch("/api/settings/category/save", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({ categories: Array.from(selected) })
    }).then(...)
    */

    // 지금은 화면 이동만
    location.href = "/settings/keyword?mode=init";
}
