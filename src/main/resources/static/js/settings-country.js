// 관심 국가 목록
const countries = [
    "아르헨티나","오스트레일리아","오스트리아",
    "벨기에","브라질","캐나다",
    "중국","콜롬비아","이집트",
    "프랑스","독일","그리스"
];

let selected = new Set();

// 화면 로드
window.onload = () => {
    renderButtons();
    detectMode();
};

// 버튼 생성
function renderButtons() {
    const grid = document.getElementById("countryGrid");

    countries.forEach(c => {
        const btn = document.createElement("div");
        btn.classList.add("country-btn");
        btn.innerText = c;
        btn.onclick = () => toggleSelect(c, btn);
        grid.appendChild(btn);
    });
}

// 선택 토글
function toggleSelect(country, btn) {
    if (selected.has(country)) {
        selected.delete(country);
        btn.classList.remove("selected");
    } else {
        selected.add(country);
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
function openSkipPopup() {
    document.getElementById("skipPopup").classList.remove("hidden");
}

function closeSkipPopup() {
    document.getElementById("skipPopup").classList.add("hidden");
}

function confirmSkip() {
    // 카테고리 설정 화면으로 이동
    location.href = "/settings/category?mode=init";
}

// ------------------------------------------------------------
// 🎯 저장 버튼
// ------------------------------------------------------------
function goNext() {
    // ★ 백엔드 저장 API 연결할 예정
    /*
    fetch("/api/settings/country/save", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({ countries: Array.from(selected) })
    }).then(...)
    */

    // 지금은 화면 이동만
    location.href = "/settings/category?mode=init";
}
