function getCpath(){return sessionStorage.getItem("cpath")+"/"}function getResultFromAjax(t,n,r){return __awaiter(this,void 0,void 0,function(){return __generator(this,function(e){switch(e.label){case 0:return e.trys.push([0,2,,3]),[4,fetch(t,{method:r||"get",cache:"no-cache",headers:{"Content-Type":"application/json; charset=utf-8"},body:JSON.stringify(n)}).then(function(e){return e.json()})];case 1:return[2,e.sent()];case 2:return e.sent(),[2,void 0];case 3:return[2]}})})}function loadTL(){moment.locale("ko"),document.querySelectorAll("time").forEach(function(e){var t=moment.utc(e.dateTime).local().fromNow();e.innerText=t}),$(".post__btn-like").on("hover",function(){console.dir($(this).children(".glyphicon")),$(this).children(".glyphicon").toggleClass("glyphicon-heart").toggleClass("glyphicon-heart-empty")}),$(".post").on("click",function(e){var t=$(this).attr("id").replace(/\D/g,"");e.target.className.match(/img/)&&(location.href=cpath+"post/detail.do?num="+t)});var e=$("#comment-content"),n=parseInt(e.css("lineHeight"));e.on("input",function(e){var t=($(this).val()+"\n").match(/\n/g).length;$(this).height(n*t)}),$(".more-btn").on("click",function(){var e=$(event.currentTarget);e.closest("ul").find("li.is-hidden").each(function(e,t){if(!(e<4))return!1;t.classList.remove(".is-hidden")}).length<1&&e.remove()});var t=location.search.substring(1),r=new Map;t&&t.split("&").forEach(function(e){var t=e.split("=");r.set(t[0],t[1])})}var __awaiter=this&&this.__awaiter||function(a,i,c,u){return new(c||(c=Promise))(function(e,t){function n(e){try{o(u.next(e))}catch(e){t(e)}}function r(e){try{o(u.throw(e))}catch(e){t(e)}}function o(t){t.done?e(t.value):new c(function(e){e(t.value)}).then(n,r)}o((u=u.apply(a,i||[])).next())})},__generator=this&&this.__generator||function(n,r){function e(t){return function(e){return function(t){if(o)throw new TypeError("Generator is already executing.");for(;c;)try{if(o=1,a&&(i=a[2&t[0]?"return":t[0]?"throw":"next"])&&!(i=i.call(a,t[1])).done)return i;switch(a=0,i&&(t=[0,i.value]),t[0]){case 0:case 1:i=t;break;case 4:return c.label++,{value:t[1],done:!1};case 5:c.label++,a=t[1],t=[0];continue;case 7:t=c.ops.pop(),c.trys.pop();continue;default:if(!(i=0<(i=c.trys).length&&i[i.length-1])&&(6===t[0]||2===t[0])){c=0;continue}if(3===t[0]&&(!i||t[1]>i[0]&&t[1]<i[3])){c.label=t[1];break}if(6===t[0]&&c.label<i[1]){c.label=i[1],i=t;break}if(i&&c.label<i[2]){c.label=i[2],c.ops.push(t);break}i[2]&&c.ops.pop(),c.trys.pop();continue}t=r.call(n,c)}catch(e){t=[6,e],a=0}finally{o=i=0}if(5&t[0])throw t[1];return{value:t[0]?t[1]:void 0,done:!0}}([t,e])}}var o,a,i,t,c={label:0,sent:function(){if(1&i[0])throw i[1];return i[1]},trys:[],ops:[]};return t={next:e(0),throw:e(1),return:e(2)},"function"==typeof Symbol&&(t[Symbol.iterator]=function(){return this}),t},cpath=getCpath();window.onload=function(){function t(e){var t,n=e.target.querySelector('input[name="id"]'),r=e.target.querySelector('input[name="nickname"]'),o=e.target.querySelector('input[name="pw"]'),a=e.target.querySelector('input[name="pw-c"]'),i=e.target.querySelector('input[name="email"]'),c=e.target.querySelector('input[name="agree"]');n&&20<n.value.length?t="아이디 글자수 제한을 초과했습니다.":n&&!n.value.match(/[A-Za-z0-9]/g)?t="아이디는 영숫자만 사용할 수 있습니다.":r&&10<r.value.length?t="닉네임 글자수 제한을 초과했습니다.":o?o.value!==a.value?t="패스워드 확인에 패스워드와 동일하게 작성하세요":o.value.length<8?t="패스워드가 8글자 미만입니다.":o.value.match(/[A-Z]{1,}/g)&&o.value.match(/[\!\@\#\$\;\:]/g)||(t="패스워드에는 영대문자, 소문자와 숫자, 특수기호가 1자 이상 포함됩니다."):i&&!i.value.match(/[^\s]@[^\s]/)?t="이메일 형식이 올바르지 않습니다.":c&&!c.checked&&(t="규약에 동의해주세요"),t&&(alert(t),e.preventDefault())}loadTL(),document.querySelectorAll("form").forEach(function(t){var n=t.querySelector("textarea");console.log(n),n&&(n.addEventListener("keydown",function(e){e.ctrlKey&&"Enter"===e.code&&!t.querySelector('input[type="file"]')&&t.submit()}),t.addEventListener("submit",function(e){n.value||(alert("내용을 입력해주세요"),e.preventDefault())}))});({run:function(e,n,r){return e&&e.addEventListener("click",function(){if(n.classList.toggle("is-active"),r){var e=((t=document.createElement("div")).className="cover",t.onclick=function(){t.remove()},t);e.addEventListener("click",function(){n.classList.remove("is-active")}),n.classList.contains("is-active")?document.body.insertBefore(e,document.querySelector("main")):document.querySelector(".cover").remove()}var t}),this}}).run(document.querySelector(".header__user-info"),document.querySelector("nav.user-menu"),!0).run(document.querySelector('[class*="writepost"] button'),document.querySelector(".writepost"),!1),$("#writepost-img").on("change",function(e){var t=new FileReader;t.onload=function(e){$(".writepost__image-info")[0].style.display="none",$("#preview").attr("src",e.target.result)},t.readAsDataURL(e.target.files[0])}),$("#ProfileImage").on("change",function(e){var t=new FileReader;t.onload=function(e){$("#user__profile-img").attr("src",e.target.result)},t.readAsDataURL(e.target.files[0])}),$("#signup-id").on("keyup",function(){var e=$("#signup-id").val(),t=$("#signup-id-check-result");fetch(cpath+"users/checkid.do?inputId="+e).then(function(e){return e.json()}).then(function(e){e.istExist?t.text("").removeClass("false").fadeOut():t.html('<i class="glyphicon glyphicon-remove"></i>중복된 ID이거나 서버 통신이 원활하지 않습니다.').addClass("false").fadeIn()}).catch(function(e){return!1})});["signUp","userSettingsForm"].forEach(function(e){document.getElementById(e)&&document.getElementById(e).addEventListener("submit",t)}),$(".comment__info a").on("click",function(){$(this).parent().next().fadeToggle(250)})};
//# sourceMappingURL=core.js.map