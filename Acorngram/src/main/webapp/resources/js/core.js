function toggleWritePopup(){document.querySelector(".write-post").classList.toggle("is-visible")}function confirmAccess(e){window.confirm("정말로 하시겠습니까?")&&(location.href=e)}function likeControl(e){var t=document.querySelector(".post-"+e+" .post__like a"),o=t.classList.contains("liked")?"unlike":"like";fetch("post/"+o+".do?num="+e).then(function(e){return e.json()}).then(function(e){if(e.result)switch(o){case"unlike":t.querySelector("i").classList.replace("glyphicon-heart","glyphicon-heart-empty"),t.classList.remove("liked");break;case"like":t.querySelector("i").classList.replace("glyphicon-heart-empty","glyphicon-heart"),t.classList.add("liked")}}).catch(function(e){switch(o){case"unlike":t.querySelector("i").classList.replace("glyphicon-heart","glyphicon-heart-empty"),t.classList.remove("liked");break;case"like":t.querySelector("i").classList.replace("glyphicon-heart-empty","glyphicon-heart"),t.classList.add("liked")}})}function deletePost(t){window.confirm("정말로 삭제하시겠습니까?")&&fetch("post/delete.do?num="+t).then(function(e){return e.json()}).then(function(e){e.result?(window.alert("성공적으로 삭제되었습니다."),$(".post-"+t).fadeOut(300,function(){$(this).remove()})):window.alert("오류가 발생했습니다.")}).catch(function(e){$(".post-"+t).fadeOut(300,function(){$(this).remove()})})}var makeCover=function(){var e=document.createElement("div");return e.className="cover",e.onclick=function(){e.remove()},e},toggle={run:function(e,t,o){return e&&e.addEventListener("click",function(){if(t.classList.toggle("is-active"),o){var e=makeCover();e.addEventListener("click",function(){t.classList.remove("is-active")}),t.classList.contains("is-active")?document.body.insertBefore(e,document.querySelector("main")):document.querySelector(".cover").remove()}}),this}};toggle.run(document.querySelector(".header__user-info"),document.querySelector("nav.user-menu"),!0).run(document.querySelector('[class*="writepost"] button'),document.querySelector(".writepost form"),!1),function(){$("time").val(new moment($(this).datetime).format("YYYY/MM/DD hh:mm:ss")),$(".post__btn-like").on("hover",function(){console.dir($(this).children(".glyphicon")),$(this).children(".glyphicon").toggleClass("glyphicon-heart").toggleClass("glyphicon-heart-empty")})}(),$("#form__writepost-img").on("change",function(e){var t=new FileReader;t.onload=function(e){$("#preview").attr("src",e.target.result)},t.readAsDataURL(e.target.files[0])});
