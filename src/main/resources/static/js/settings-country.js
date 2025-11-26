const mode = new URLSearchParams(location.search).get("mode");
let selected = new Set();

// 화면 로드
window.onload = () => {
    checkLogin().then(user => {
        if (user) {
            getSettingsButton(user, "COUNTRY", function(data) {
                if(mode == "edit") {
                    getUserCountrySelected(data);
                }
            });
            detectMode();
        }
    });
};

function getUserCountrySelected(user) {
    $.ajax({
        url: '/api/user/country/selectUserCountryList',
        method: "POST",
        dataType : "json",
        contentType:"application/json",
        data : JSON.stringify({
            userId : user.userId
        }),
        success: function (data) {
            $.each(data, function(index, item){
                $(".box-btn[data-code=" + item.countryCode +"]").addClass("selected");
                selected.add(item.countryCode);
            });
        },
        error: function (data, status, err) {
        }
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

function openSkipPopup() {
    document.getElementById("skipPopup").classList.remove("hidden");
}

function closeSkipPopup() {
    document.getElementById("skipPopup").classList.add("hidden");
}

function confirmSkip() {
    // 카테고리 설정 화면으로 이동
    fetch("https://ipapi.co/json/")
    .then(res => res.json())
    .then(data => {
        selected.add(data.country.toLowerCase());
        goNext();
    });
}

// ------------------------------------------------------------
// 🎯 저장 버튼
// ------------------------------------------------------------
function goNext() {
    checkLogin().then(user => {
        if (user) {
            $.ajax({
                url: "/api/user/country/updateUserCountry",
                method: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify({
                    userId: user.userId
                    , countries: Array.from(selected)
                }),
                success: function (data) {
                    if (data.resultMsg == "Success") {
                        if (mode == "init") {
                            location.href = "/settings/category?mode=init";
                        } else {
                            location.href = "/news/feed";
                        }
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
