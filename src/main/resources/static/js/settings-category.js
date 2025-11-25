// 카테고리 목록
const categories = [
    "비즈니스/경제","정치","사회",
    "IT/기술","스포츠","엔터테인먼트",
    "건강","세계","문화","환경"
];

let selected = new Set();

// 초기 로드
window.onload = () => {
    renderButtons();
    checkMode();
};

// 버튼 렌더링
function renderButtons() {
    const grid = document.getElementById("categoryGrid");

    categories.forEach(cat => {
        const btn = document.createElement("div");
        btn.classList.add("category-btn");
        btn.innerText = cat;

        btn.onclick = () => toggle(cat, btn);

        grid.appendChild(btn);
    });
}

// 선택 토글
function toggle(item, btn) {
    if (selected.has(item)) {
        selected.delete(item);
        btn.classList.remove("selected");
    } else {
        selected.add(item);
        btn.classList.add("selected");
    }
}

// 모드 분기(initial / edit)
function checkMode() {
    const params = new URLSearchParams(location.search);
    const mode = params.get("mode");

    if (mode === "initial") {
        document.getElementById("initialTitle").classList.remove("hidden");
        document.getElementById("initialDesc").classList.remove("hidden");
        document.getElementById("skipBtn").classList.remove("hidden");
    } else {
        document.getElementById("editHeader").classList.remove("hidden");
        document.getElementById("editTitle").classList.remove("hidden");
    }
}

// 나중에 설정 (초기 모드)
function skipSetting() {
    location.href = "/settings/keyword?mode=initial";
}

// 저장 버튼 누르면
function saveCategories() {

    // AJAX 저장 (백엔드 완성 후 활성화)
    /*
    fetch("/api/settings/category/save", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({ categories: Array.from(selected) })
    });
    */

    const params = new URLSearchParams(location.search);
    const mode = params.get("mode");

    // 다음 단계로 이동
    location.href = `/settings/keyword?mode=${mode}`;
}
