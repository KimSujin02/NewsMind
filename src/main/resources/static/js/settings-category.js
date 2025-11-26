let selected = new Set();
const mode = new URLSearchParams(location.search).get("mode");

// 화면 로드
window.onload = () => {
    checkLogin().then(user => {
        if (user) {
            getSettingsButton(user, "CATEGORY", function(data) {
                if(mode == "edit") {
                    getUserCategorySelected(data);
                }
            });
            detectMode();
        }
    });
};

function getUserCategorySelected(user) {
    $.ajax({
        url: "/api/user/category/selectUserCategoryList",
        method: "POST",
        dataType : "json",
        contentType:"application/json",
        data : JSON.stringify({
            userId : user.userId
        }),
        success: function (data) {
            $.each(data, function(index, item){
                $(".box-btn[data-code=" + item.categoryCode +"]").addClass("selected");
                selected.add(item.categoryCode);
            });
        },
        error: function (data, status, err) {
        }
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

function confirmSkip() {
    // 키워드 설정 화면으로 이동
    location.href = "/settings/keyword?mode=init";
}

// ------------------------------------------------------------
// 🎯 저장 버튼
// ------------------------------------------------------------
function goNext() {
    checkLogin().then(user => {
        if (user) {
            $.ajax({
                url: "/api/user/category/updateUserCategory",
                method: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify({
                    userId: user.userId
                    , categories: Array.from(selected)
                }),
                success: function (data) {
                    if (data.resultMsg == "Success") {
                        if (mode == "init") {
                            location.href = "/settings/keyword?mode=init";
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
