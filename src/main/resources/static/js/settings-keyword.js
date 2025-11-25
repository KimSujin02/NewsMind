let keywords = [];

window.onload = () => {
    document.getElementById("addBtn").addEventListener("click", addKeyword);

    document.getElementById("keywordInput").addEventListener("keypress", (e) => {
        if (e.key === "Enter") addKeyword();
    });
};

// 키워드 추가
function addKeyword() {
    const input = document.getElementById("keywordInput");
    let value = input.value.trim();

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
function skipSetting() {
    location.href = "/settings/finish?mode=initial";
}

// 저장 (백엔드 연결 예정)
function saveKeywords() {

    if (keywords.length === 0) {
        alert("최소 1개 이상의 키워드를 입력해주세요.");
        return;
    }

    // ★ 백엔드 연동은 아래 AJAX 사용 (주석만 남김)
    /*
    fetch("/api/settings/keyword/save", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({ keywords })
    }).then(res => res.json())
      .then(() => {
          const mode = new URLSearchParams(location.search).get("mode");
          if (mode === "initial") {
              location.href = "/settings/finish?mode=initial";
          } else {
              alert("저장되었습니다.");
              history.back();
          }
      });
    */

    // 임시 동작: 다음 페이지로 이동
    const mode = new URLSearchParams(location.search).get("mode");
    if (mode === "initial") {
        location.href = "/settings/finish?mode=initial";
    } else {
        alert("저장되었습니다.");
        history.back();
    }
}
