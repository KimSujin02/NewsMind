// settings-common.js
// 최초 설정(init) / 일반 수정(edit) 모드 공용 유틸

const SettingsMode = (function () {
    const params = new URLSearchParams(window.location.search);
    const mode = params.get('mode') || 'init';   // init or edit
    const isInit = mode === 'init';

    function toggleSkipButton(skipButton) {
        if (!skipButton) return;
        // 일반 수정 모드에서는 "나중에 설정하기" 숨김
        if (!isInit) {
            skipButton.style.display = 'none';
        }
    }

    return {
        mode,
        isInit,
        toggleSkipButton
    };
})();
