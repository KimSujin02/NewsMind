// settings-keyword.js

document.addEventListener('DOMContentLoaded', function () {
    const input = document.getElementById('keyword-input');
    const btnAdd = document.getElementById('btn-add');
    const list = document.getElementById('keyword-list');
    const btnSkip = document.getElementById('btn-skip');
    const btnFinish = document.getElementById('btn-finish');

    SettingsMode.toggleSkipButton(btnSkip);

    function addKeyword(text) {
        const value = text.trim();
        if (!value) return;

        // 중복 체크
        const exists = Array.from(list.querySelectorAll('.nm-tag-text'))
            .some(span => span.textContent === value);
        if (exists) {
            alert('이미 추가된 키워드입니다.');
            return;
        }

        const tag = document.createElement('div');
        tag.className = 'nm-tag';

        const span = document.createElement('span');
        span.className = 'nm-tag-text';
        span.textContent = value;

        const x = document.createElement('span');
        x.className = 'nm-tag-remove';
        x.textContent = '×';
        x.addEventListener('click', () => tag.remove());

        tag.appendChild(span);
        tag.appendChild(x);
        list.appendChild(tag);
    }

    btnAdd.addEventListener('click', function () {
        addKeyword(input.value);
        input.value = '';
        input.focus();
    });

    input.addEventListener('keydown', function (e) {
        if (e.key === 'Enter') {
            e.preventDefault();
            addKeyword(input.value);
            input.value = '';
        }
    });

    function collectKeywords() {
        return Array.from(list.querySelectorAll('.nm-tag-text'))
            .map(span => span.textContent);
    }

    function goToFeed() {
        window.location.href = '/news/feed';
    }

    btnFinish.addEventListener('click', function () {
        const keywords = collectKeywords();

        // TODO: 백엔드 저장 + user.setting_completed = true 로 업데이트
        /*
        fetch('/api/settings/keyword', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ keywords: keywords })
        }).then(res => {
            if (!res.ok) throw new Error();
            return res.json();
        }).then(() => {
            return fetch('/api/settings/complete', { method: 'POST' });
        }).then(() => {
            goToFeed();
        }).catch(() => {
            alert('설정 저장 중 오류가 발생했습니다.');
        });
        */

        // 일단은 바로 feed 로 이동
        goToFeed();
    });

    btnSkip.addEventListener('click', function () {
        // 최초 설정에서라도 스킵 시, 백엔드에서 setting_completed = true 로 처리해줄 예정
        goToFeed();
    });
});
