var errorObj = /** @class */ (function () {
    function errorObj(code) {
        switch (this.code = code) {
            case 404:
                this.msg = "페이지를 찾을 수 없습니다.";
                this.log = 'not found';
                break;
            case 500:
                this.msg = "서버에서 오류가 발생했습니다. 다시 시도해 주세요";
                this.log = '서버 오류';
                break;
            case 418:
                this.msg = "커피라도 마시면서 이야기하죠";
                this.log = '';
                break;
            default:
                this.msg = "원인 불명의 오류가 발생했습니다.";
                this.log = '알 수 없는 에러';
        }
    }
    return errorObj;
}());
var error = new errorObj(code);
var errorPanel = document.querySelector('.error');
var errorMsg = errorPanel.querySelector('.error__msg');
var logArea = errorPanel.querySelector('.error__log');
errorMsg.innerText = error.msg;
logArea.innerText = error.log;
document.querySelector('script').remove();
//# sourceMappingURL=errorPage.js.map