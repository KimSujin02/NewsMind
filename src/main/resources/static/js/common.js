function getCookie(name) {
    const value = document.cookie
        .split('; ')
        .find(row => row.startsWith(name + '='));
    return value ? decodeURIComponent(value.split('=')[1]) : null;
}

function goSettings() {
    window.location.href = '/settings';
}

function logout() {
    fetch('/api/logout', {
        method: 'POST'
    }).then(() => {
        location.href = "/login"; // 로그인 페이지로 보내기
    });
}

async function checkLogin() {
    const res = await fetch('/api/auth/me', {
        method: 'GET',
        credentials: 'include'
    });
    if (!res.ok) {
        window.location.href = '/login';
    }
    const user = await res.json();
    return user;    // 로그인됨
}

function getSettingsButton(user, codeTy, successFunc) {
    $.ajax({
        url: '/api/code/selectCodeList',
        method: "POST",
        dataType : "json",
        contentType:"application/json",
        data : JSON.stringify({
            codeTy : codeTy
        }),
        success: function (data) {
            const grid = document.getElementById("grid");
            $.each(data, function(index, item){
                const btn = document.createElement("div");
                btn.classList.add("box-btn");
                btn.setAttribute("data-code", item.code);
                btn.innerText = item.code_nm;
                btn.onclick = () => toggleSelect(item.code, btn);
                grid.appendChild(btn);
            });
            successFunc(user);
        },
        error: function (data, status, err) {
        }
    });
}

function detectMode() {
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