let nextPage = null;
let isLoading = false;
let currentUser = null;

let filterMode = false;

let countrySelected = new Set();
let categorySelected = new Set();
let keywordSelected = new Set();

// 화면 로드
window.onload = async () => {
    currentUser = await checkLogin();
    if (currentUser) {
        getTotalNewsList(currentUser);
    }
};

// 스크롤 감지
window.addEventListener("scroll", () => {
    const nearBottom = window.innerHeight + window.scrollY >= document.body.offsetHeight - 300;
    if (!nearBottom) return;
    if (!nextPage) return;       // 다음 페이지 없음
    if (isLoading) return;       // 이미 로딩 중

    if (currentUser) {
        getTotalNewsList(currentUser, "Y");
    }
});

function refresh() {
    $("#newsList").empty();
   getTotalNewsList(currentUser);
}

function resetFilter() {
    filterClose();
    $("#newsList").empty();
    filterMode = false;
    $("#onFilter").addClass("hidden");
    $("#offFilter").removeClass("hidden");
    countrySelected = new Set();
    categorySelected = new Set();
    keywordSelected = new Set();
    getTotalNewsList(currentUser);
}

function getTotalNewsList(user, goNextPage) {
    if (isLoading) return;      // 혹시라도 중복 방지
    isLoading = true;

    var params = {
        userId : user.userId
        , translate : false
        , targetLang : 'ko'
    };

    if (goNextPage === "Y" && nextPage != null) {
        params.page = nextPage;
    }

    var url = "";
    if(filterMode) {
        url = "/api/newsdata/filterSearch";
        params.countries = Array.from(countrySelected);
        params.categories = Array.from(categorySelected);
        params.keywords = Array.from(keywordSelected);
    } else {
        url = "/api/newsdata/getTotalNewsList";
    }

    $.ajax({
        url: url,
        method: "POST",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(params),
        success: function (data) {
            console.log(data);
            drawNewsGrid(data);
        },
        error: function (xhr, status, err) {
            alert("뉴스 서버 호출 제한에 걸렸어요. 잠시 후 다시 시도해주세요.");
            nextPage = null; // 더 이상 요청 안 보내도록
        },
        complete: function () {
            isLoading = false;
        }
    });
}


function openFilter(){
    $("#feed").addClass("hidden");
    $("#filterContainer").removeClass("hidden");
    getUserFilterSelected(currentUser);
}

function drawNewsGrid(data) {
    nextPage = data.nextPage;
    var newsList = $("#newsList");
    $.each(data.results, function(index, item){
        var innerHTML = `
            <div class="news-card" onclick="location.href='/news/detail?articleId=${item.article_id}'">
                <div class="news-thumb">
                    <img src="${item.image_url ? item.image_url : 'https://img.icons8.com/?size=100&id=-5tLho_7N4sS&format=png&color=FFFFFF'}">
                </div>
                <div class="news-content">
                    <div class="news-title">${item.title}</div>
                    <div class="news-category">Category <b>${item.category}</b></div>
                    <div class="news-tags">AI Tags: <b>${item.ai_tag}</b></div>
                    <div class="news-date">${item.pubDate} <span class="news-status">${item.country}</span></div>
                </div>
            </div>`;
        newsList.append(innerHTML);
    });
}

function getUserFilterSelected(user) {
    $.ajax({
        url: '/api/user/country/selectUserCountryList',
        method: "POST",
        dataType : "json",
        contentType:"application/json",
        data : JSON.stringify({
            userId : user.userId
        }),
        success: function (data) {
            const grid = document.getElementById("countryGrid");
            $.each(data, function(index, item){
                const btn = document.createElement("div");
                btn.classList.add("box-btn");
                if (!filterMode) {
                    btn.classList.add("selected");
                    countrySelected.add(item.countryCode);
                } else if(countrySelected.has(item.countryCode)) {
                    btn.classList.add("selected");
                }
                btn.setAttribute("data-code", item.countryCode);
                btn.innerText = item.countryName;
                btn.onclick = () => toggleSelectCountry(item.countryCode, btn);
                grid.appendChild(btn);
            });
        },
        error: function (data, status, err) {
        }
    });
    $.ajax({
        url: '/api/user/category/selectUserCategoryList',
        method: "POST",
        dataType : "json",
        contentType:"application/json",
        data : JSON.stringify({
            userId : user.userId
        }),
        success: function (data) {
            const grid = document.getElementById("categoryGrid");
            $.each(data, function(index, item){
                const btn = document.createElement("div");
                btn.classList.add("box-btn");
                if (!filterMode) {
                    btn.classList.add("selected");
                    categorySelected.add(item.categoryCode);
                } else if(categorySelected.has(item.categoryCode)) {
                    btn.classList.add("selected");
                }
                btn.setAttribute("data-code", item.categoryCode);
                btn.innerText = item.categoryName;
                btn.onclick = () => toggleSelectCategory(item.categoryCode, btn);
                grid.appendChild(btn);
            });
        },
        error: function (data, status, err) {
        }
    });
    $.ajax({
        url: '/api/user/keyword/selectUserKeywordList',
        method: "POST",
        dataType : "json",
        contentType:"application/json",
        data : JSON.stringify({
            userId : user.userId
        }),
        success: function (data) {
            const grid = document.getElementById("keywordGrid");
            $.each(data, function(index, item){
                const btn = document.createElement("div");
                btn.classList.add("box-btn");
                if (!filterMode) {
                    keywordSelected.add(item.keyword);
                } else if(keywordSelected.has(item.keyword)) {
                    btn.classList.add("selected");
                }
                btn.setAttribute("data-code", item.keyword);
                btn.innerText = item.keyword;
                btn.onclick = () => toggleSelectKeyword(item.keyword, btn);
                grid.appendChild(btn);
            });
        },
        error: function (data, status, err) {
        }
    });
}

// 선택 토글
function toggleSelectCountry(country, btn) {
    if (countrySelected.has(country)) {
        countrySelected.delete(country);
        btn.classList.remove("selected");
    } else {
        countrySelected.add(country);
        btn.classList.add("selected");
    }
}

// 선택 토글
function toggleSelectCategory(category, btn) {
    if (categorySelected.has(category)) {
        categorySelected.delete(category);
        btn.classList.remove("selected");
    } else {
        categorySelected.add(category);
        btn.classList.add("selected");
    }
}

// 선택 토글
function toggleSelectKeyword(keyword, btn) {
    if (keywordSelected.has(keyword)) {
        keywordSelected.delete(keyword);
        btn.classList.remove("selected");
    } else {
        keywordSelected.add(keyword);
        btn.classList.add("selected");
    }
}

function goFilterNext() {
    filterMode = true;
    $("#offFilter").addClass("hidden");
    $("#onFilter").removeClass("hidden");
    $("#newsList").empty();
    getTotalNewsList(currentUser);
    filterClose();
}

function filterClose() {
    $("#filterContainer").addClass("hidden");
    $("#feed").removeClass("hidden");
    $("#countryGrid").empty();
    $("#categoryGrid").empty();
    $("#keywordGrid").empty();
}

$('.news-card').on('click', function() {
    sessionStorage.setItem("feedScroll", window.scrollY);
});