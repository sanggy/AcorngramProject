class errorObj{
	code: number;
	msg: string;
	log: string;
	constructor(code:number){
		switch(this.code = code){
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
	// get getError() {
	// 	return {msg: error.msg, log: error.log}
	// }
	// set setMsg(msg:string) {
	// 	this.msg = msg;
	// }
	// set setLog(log:string) {
	// 	this.log = log;
	// }
}

const error = new errorObj(code);
var errorPanel = document.querySelector('.error');
var errorMsg:HTMLElement = errorPanel.querySelector('.error__msg');
var logArea:HTMLElement =  errorPanel.querySelector('.error__log');

errorMsg.innerText = error.msg;
logArea.innerText = error.log;

document.querySelector('script').remove();