// import * as $ from 'jquery';

//	getCpath
function getCpath(){
	return sessionStorage.getItem('cpath')+'/';
}
const cpath = getCpath();


//	json
function getResultFromAjax(url, param:Map, type){
	return fetch(url,{
		method: type?type:'get',
		cache: 'no-cache',
		headers: {
			"Content-Type": "application/json; charset=utf-8"
		},
		body: JSON.stringify(param)
	}).then(res=>{return res.json()})
	.catch(err=>{return false;})
}


//	Timeline load시 실행
(function loadPost(){
	moment.locale('ko');

	//	시간 설정
	document.querySelectorAll('time').forEach(e=>{
		var regdate = moment().utc(e.dateTime);
		e.innerText = 
			regdate
		//	new moment(regdate).fromNow()
	})

	//	좋아요 버튼 hover 효과
	$('.post__btn-like').on('hover',function(){
		console.dir($(this).children('.glyphicon'));
		$(this).children('.glyphicon')
			.toggleClass('glyphicon-heart')
			.toggleClass('glyphicon-heart-empty');
	});

	//	to post/detail?num=?

	$('.post').on('click',function(e){
		const num:number = $(this).attr('id').replace(/\D/g, "");
		if(e.target.className.match(/img/)){
			location.href=cpath+'post/detail.do?num='+num;
		}
	});

	//	댓글 작성 textarea 자동 크기조절
	var $textarea = $('#comment-content');
	var lineHeight = parseInt($textarea.css('lineHeight'));
	$textarea.on('input', function(e) {
		var lines = ($(this).val() + '\n').match(/\n/g).length;
		$(this).height(lineHeight * lines);
	});
	
	//	더보기 버튼 클릭시 정보 보여주기
	$('.more-btn').on('click',()=>{
		let more_btn:HTMLElement = $(event.currentTarget);
		more_btn.closest('ul').find('li.is-hidden').each((index,elem)=>{
			if(index<4)elem.classList.remove('.is-hidden');
			else return false;
		}).length<1?more_btn.remove():''
	})


})();

//	글쓰기 창 토글

function toggleWritePopup(){
	var block:HTMLElement = document.querySelector('.writepost');
	block.classList.toggle('is-visible');
}

//	업로드전 이미지 표시

$('#writepost-img').on('change', function (e) {
	var reader = new FileReader();
	reader.onload = function (e) {
		$('.writepost__image-info')[0].style.display = 'none';
		$("#preview").attr('src', e.target.result);
	}
	reader.readAsDataURL(e.target.files[0]);
});



//	유저메뉴 토글

var makeCover = ()=>{
	var item = document.createElement('div');
	item.className = 'cover';
	item.onclick = ()=>{item.remove()}
	return item;
}
	 
const toggle = {
	run: function (org, target, is_cover){
		org?
		org.addEventListener('click',function(){
			(<HTMLElement>target)
			.classList.toggle('is-active');
			if(is_cover){
				var cover = makeCover();
				cover.addEventListener('click',()=>{
					(<HTMLElement>target).classList.remove('is-active');
				});
				(<HTMLElement>target)
					.classList.contains('is-active')?
						document.body.insertBefore(cover, document.querySelector('main')):
						document.querySelector('.cover').remove();
			}
		})
		:'';
		return this;
	}
}


toggle.run(
	document.querySelector('.header__user-info'),
	document.querySelector('nav.user-menu'),
	true
).run(
	document.querySelector('[class*="writepost"] button'),
	document.querySelector('.writepost'),
	false
);

//	유저메뉴 토글 끝



//	특정조작시 리다이렉트

function confirmAccess(act){
	var prompt_msg = '정말로 '+act+'하시겠습니까? \n 하시려 한다면'+act+'를 입력해주세요.';
	var result = prompt(prompt_msg);
	if(act === result){
		location.href = act+'.do';
	}
}


//  좋아요 버튼

function likeControl(num){
	const flag = document.querySelector('.post-'+num+' .post__like a');
	const mode = flag.classList.contains('liked')?
		'unlike':'like';
	fetch(cpath+'post/'+mode+'.do?num='+num)
	.then(res=>res.json())
	.then(res=>{
		if(res.result) {
			switch (mode){
			case 'unlike':
				flag.querySelector('i').classList.replace('glyphicon-heart', 'glyphicon-heart-empty');
				flag.classList.remove('liked');
				break;
			case 'like':
				flag.querySelector('i').classList.replace('glyphicon-heart-empty', 'glyphicon-heart');
				flag.classList.add('liked');
				break;
			}
		}
	})
	.catch(error=>{
//	테스트용 실제로는 x
		window.alert('서버와 통신 도중 에러가 발생했습니다.');
	})

	// $.ajax({
	// 	url:"post/"+mode+'.do?num'+num,
	// 	type:'get',
	// 	dataType:'json'
	// }).done(res=>{
	// 	if(res.result) {
	// 		switch (mode){
	// 		case 'unlike':
	// 			flag.querySelector('i').classList.replace('glyphicon-heart', 'glyphicon-heart-empty');
	// 			break;
	// 		case 'like':
	// 			flag.querySelector('i').classList.replace('glyphicon-heart-empty', 'glyphicon-heart');
	// 			break;
	// 		}
	// 	}
	// })
}

//	삭제버튼

function deletePost(num){
	var result:boolean = window.confirm('정말로 삭제하시겠습니까?');
	if(result){
		fetch(cpath+'post/delete.do?num='+num)
		.then(res=>res.json())
		.then(res=>{
				if(res.result){
					window.alert('성공적으로 삭제되었습니다.');
					$('.post-'+num).fadeOut(300, function() { $(this).remove(); });
				}else{
					window.alert('오류가 발생했습니다.');
				}
		}).catch(
			error=>{
				window.alert('서버와 통신 도중 에러가 발생했습니다.')
			}
		)

		// $.ajax({
		// 	url : "post/delete.do",
		// 	type : "get",
		// 	data : {'num' : num},
		// 	dataType: "json",
		// }).done(
		// 	res => {
		// 		if(res.result){
		// 			window.alert('성공적으로 삭제되었습니다.');
		// 			$('.post-'+num).fadeOut(300, function() { $(this).remove(); });
		// 		}else{
		// 			window.alert('오류가 발생했습니다.');
		// 		}
		// 	}
		// ).fail(
		// 	error=>{
		// 		$('.post-'+num).fadeOut(300, function() { $(this).remove(); });
		// 	}
		// )
	}
}

//	팔로우/언팔 버튼

function followToggle(usercode){
	let url = cpath+"follwer/follow.do";
	const result = followAjax(url, usercode);
	if(result){
		window.alert('성공적으로 팔로우되었습니다.');
		const target = document.querySelector('post-'+usercode+' a[class*="follow]');
			target.classList.replace('post__btn-follow', 'post__btn-unfollow');
			target.querySelector('i.glyphicon').classList.replace('glyphicon-plus-sign', 'glyphicon-remove-sign')
			target.querySelector('span').innerText = 'Unfollow';
	}else{
		window.alert('오류가 발생했습니다.');
	}
}

function unfollowToggle(usercode){
	let url = 'follwer/unfollow.do';
	const result = followAjax(url, usercode);
	if(result){
		window.alert('성공적으로 언팔로우되었습니다.');
		const target = document.querySelector('post-'+usercode+' a[class*="follow]');
			target.classList.replace('post__btn-unfollow', 'post__btn-follow');
			target.querySelector('i.glyphicon').classList.replace('glyphicon-remove-sign', 'glyphicon-plus-sign')
			target.querySelector('span').innerText = 'Follow';
	}else{
		window.alert('오류가 발생했습니다.');
	}
}

function followAjax(url, usercode){
	return fetch(cpath+url+'?usercode='+usercode)
	.then(res=>res.json())
	.then(res=>{return res.result;})
	.catch(err=>{return false;}) // 테스트용 실제로는 반대로 
}

//	팔로우 언팔 끝

//	회원가입 폼

//	아이디 중복체크
$('#signup-id').on('keypress',()=>{
	var id = $('#signup-id').val();
	var result:JSON = getResultFromAjax(
		cpath+'users/checkid.do',
		{'id':id}
		,'get'
	);
	var target = $('#signup-id-check-result');
	if(result.result){
		target.removeClass('false').fadeOut();
	}else{
		target.val('중복된 ID이거나 서버 통신이 원활하지 않습니다.')
			.addClass('false').fadeIn();
	}
})

$('#signUp').on('submit',()=>{
	var pw:HTMLInputElement = document.querySelector('#signup-pw');
	var pw_c:HTMLInputElement = document.querySelector('#signup-pw-c');
	var email:HTMLInputElement = document.querySelector('#signup-email');
	var msg;
	if(pw.value !== pw_c.value){
		msg = '패스워드 확인에 패스워드와 동일하게 작성하세요';
		alert(msg);
		return false;
	}else if( email.value.match(/[^\s]@[^\s]/) ){
		msg = '이메일 형식이 올바르지 않습니다.';
		alert(msg);
		return false;
	}else if($('#signup-agree').prop("checked")){
		msg = '규약에 동의해주세요';
		alert(msg);
		return false;
	}
})