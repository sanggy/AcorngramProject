function getCpath(){return sessionStorage.getItem("cpath")+"/"}function getResultFromAjax(e,t,n){return fetch(e,{method:n||"get",cache:"no-cache",headers:{"Content-Type":"application/json; charset=utf-8"},body:JSON.stringify(t)}).then(function(e){return e.json()}).catch(function(e){return!1})}function toggleWritePopup(){document.querySelector(".writepost").classList.toggle("is-visible")}function confirmAccess(e,t){e===prompt("정말로 "+e+"하시겠습니까? \n 하시려 한다면"+e+"를 입력해주세요.")&&(location.href=t+".do")}function likeControl(e){var t=document.querySelector("#post-"+e+" .post__like a"),n=t.classList.contains("liked")?"unlike":"like";fetch(cpath+"post/"+n+".do?num="+e).then(function(e){if(e.status<400)return e.json();throw new Error}).then(function(e){if(e.result)switch(n){case"unlike":t.querySelector("i").classList.replace("glyphicon-heart","glyphicon-heart-empty"),t.classList.remove("liked");break;case"like":t.querySelector("i").classList.replace("glyphicon-heart-empty","glyphicon-heart"),t.classList.add("liked")}}).catch(function(e){window.alert("서버와 통신 도중 에러가 발생했습니다.")})}function deletePost(t){window.confirm("정말로 삭제하시겠습니까?")&&fetch(cpath+"post/delete.do?num="+t).then(function(e){return e.json()}).then(function(e){e.result?(window.alert("성공적으로 삭제되었습니다."),$("#post-"+t).fadeOut(300,function(){$(this).remove()})):window.alert("오류가 발생했습니다.")}).catch(function(e){console.dir(e),window.alert("서버와 통신 도중 에러가 발생했습니다.")})}function followToggle(n){fetch(cpath+(cpath+"follwer/follow.do")+"?usercode="+n).then(function(e){return e.json()}).then(function(e){e.result?(window.alert("성공적으로 팔로우되었습니다."),document.querySelectorAll(".post").forEach(function(e){if(e.classList.contains("post-user-"+n)){var t=e.querySelector('a[class*="follow"]');t.classList.replace("post__btn-follow","post__btn-unfollow"),t.href="javascript:unfollowToggle("+n+")",t.querySelector("i.glyphicon").classList.replace("glyphicon-plus-sign","glyphicon-remove-sign"),t.querySelector("span").innerText="Unfollow"}})):window.alert("오류가 발생했습니다.")}).catch(function(e){window.alert("오류가 발생했습니다.")})}function unfollowToggle(n){fetch(cpath+"follwer/unfollow.do?usercode="+n).then(function(e){if(!(e.status<400))throw new Error("error");e.json()}).then(function(e){e.result?(window.alert("성공적으로 언팔로우되었습니다."),document.querySelectorAll(".post").forEach(function(e){if(e.classList.contains("post-user-"+n)){var t=e.querySelector('a[class*="follow"]');t.classList.replace("post__btn-unfollow","post__btn-follow"),t.href="javascript:followToggle("+n+")",t.querySelector("i.glyphicon").classList.replace("glyphicon-remove-sign","glyphicon-plus-sign"),t.querySelector("span").innerText="Follow"}})):window.alert("오류가 발생했습니다.")}).catch(function(e){window.alert("오류가 발생했습니다.")})}var cpath=getCpath();!function(){moment.locale("ko"),document.querySelectorAll("time").forEach(function(e){var t=moment.utc(e.dateTime).local().fromNow();e.innerText=t}),$(".post__btn-like").on("hover",function(){console.dir($(this).children(".glyphicon")),$(this).children(".glyphicon").toggleClass("glyphicon-heart").toggleClass("glyphicon-heart-empty")}),$(".post").on("click",function(e){var t=$(this).attr("id").replace(/\D/g,"");e.target.className.match(/img/)&&(location.href=cpath+"post/detail.do?num="+t)});var e=$("#comment-content"),n=parseInt(e.css("lineHeight"));e.on("input",function(e){var t=($(this).val()+"\n").match(/\n/g).length;$(this).height(n*t)}),$(".more-btn").on("click",function(){var e=$(event.currentTarget);e.closest("ul").find("li.is-hidden").each(function(e,t){if(!(e<4))return!1;t.classList.remove(".is-hidden")}).length<1&&e.remove()})}(),$("#writepost-img").on("change",function(e){var t=new FileReader;t.onload=function(e){$(".writepost__image-info")[0].style.display="none",$("#preview").attr("src",e.target.result)},t.readAsDataURL(e.target.files[0])}),$("#ProfileImage").on("change",function(e){var t=new FileReader;t.onload=function(e){$("#user__profile-img").attr("src",e.target.result)},t.readAsDataURL(e.target.files[0])});var makeCover=function(){var e=document.createElement("div");return e.className="cover",e.onclick=function(){e.remove()},e},toggle={run:function(e,t,n){return e&&e.addEventListener("click",function(){if(t.classList.toggle("is-active"),n){var e=makeCover();e.addEventListener("click",function(){t.classList.remove("is-active")}),t.classList.contains("is-active")?document.body.insertBefore(e,document.querySelector("main")):document.querySelector(".cover").remove()}}),this}};toggle.run(document.querySelector(".header__user-info"),document.querySelector("nav.user-menu"),!0).run(document.querySelector('[class*="writepost"] button'),document.querySelector(".writepost"),!1),$("#signup-id").on("keypress",function(){var e=$("#signup-id").val(),t=getResultFromAjax(cpath+"users/checkid.do",{id:e},"get"),n=$("#signup-id-check-result");t.result?n.removeClass("false").fadeOut():n.val("중복된 ID이거나 서버 통신이 원활하지 않습니다.").addClass("false").fadeIn()}),$("#signUp").on("submit",function(){var e,t=document.querySelector("#signup-pw"),n=document.querySelector("#signup-pw-c"),o=document.querySelector("#signup-email");return t.value!==n.value?(e="패스워드 확인에 패스워드와 동일하게 작성하세요",alert(e),!1):o.value.match(/[^\s]@[^\s]/)?(e="이메일 형식이 올바르지 않습니다.",alert(e),!1):$("#signup-agree").prop("checked")?(e="규약에 동의해주세요",alert(e),!1):void 0}),$(".user-settings__form").on("submit",function(){var e,t=document.querySelector('input[name="pw"]'),n=document.querySelector('input[name="pw-c"]'),o=document.querySelector('input[name="email"]');if(t){if(t.value!==n.value)return e="패스워드 확인에 패스워드와 동일하게 작성하세요",alert(e),!1}else{if(!o)return e="실패",alert(e),!1;if(!o.value.match(/[^\s]@[^\s]/))return e="이메일 형식이 올바르지 않습니다.",alert(e),!1}}),$(".comment__info a").on("click",function(){$(this).parent().next().fadeToggle(250)});
