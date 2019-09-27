// import * as $ from 'jquery';

//	getCpath
function getCpath(){
	return sessionStorage.getItem('cpath')+'/';
}
const cpath = getCpath();

//	json
async function getResultFromAjax(url, param:Map<string, any>, type){
	try{
		return await fetch(url,{
			method: type?type:'get',
			cache: 'no-cache',
			headers: {
				"Content-Type": "application/json; charset=utf-8"
			},
			body: JSON.stringify(param)
		}).then(res=> {return res.json() })
	}catch(err){
		return undefined;
	}
}


//	Timeline load시 실행
function loadTL(){
	moment.locale('ko');

	//	시간 설정
	document.querySelectorAll('time').forEach(e=>{
        var regdate = moment.utc(e.dateTime).local().fromNow();
        e.innerText = regdate
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
	const $textarea = $('#comment-content');
	let lineHeight:number = parseInt($textarea.css('lineHeight'));
	$textarea.on('input', function(e) {
		let lines = ($(this).val() + '\n').match(/\n/g).length;
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

	//	search시 검색 키워드 보여주기
	const param = location.search.substring(1);
	const paramMap = new Map<string, string>();
	if(param){
		param.split('&').forEach(e=>{
			const paramItem = e.split('=');
			paramMap.set(paramItem[0], paramItem[1]);
		});
	}
	console.log(paramMap);

}

window.onload = ()=>{

	//	TimeLine load
	loadTL();

	//	Textarea가 있는 Form에서 Ctrl+엔터키 입력시 송신기능
	document.querySelectorAll('form').forEach(e=>{
		const textarea:HTMLTextAreaElement = e.querySelector('textarea');
		if(textarea) 
			textarea.addEventListener('keydown',i=>{
				if(i.ctrlKey&&i.code==="Enter"&&!e.querySelector('input[type="file"]'))
					e.submit();
			});
		e.addEventListener('submit',i=>{
			if(!textarea.value) {
				alert('내용을 입력해주세요');
				i.preventDefault();
			}
		});
	})


	//	유저메뉴 토글

	var makeCover = ()=>{
		let item:HTMLDivElement = document.createElement('div');
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


	//	글쓰기 창 토글

	function toggleWritePopup(){
		let block:HTMLElement = document.querySelector('.writepost');
		block.classList.toggle('is-visible');
	}

	//	업로드전 이미지 표시
		//	post 작성
		$('#writepost-img').on('change', function (e) {
			let reader = new FileReader();
			reader.onload = function (e) {
				$('.writepost__image-info')[0].style.display = 'none';
				$("#preview").attr('src', e.target.result);
			}
			reader.readAsDataURL(e.target.files[0]);
		});
		//	프로필 업로드시
		$('#ProfileImage').on('change', function (e) {
			let reader = new FileReader();
			reader.onload = function (e) {
				$("#user__profile-img").attr('src', e.target.result);
			}
			reader.readAsDataURL(e.target.files[0]);
		});
	//	업로드전 이미지 표시 끝

	//	특정조작시 리다이렉트

	function confirmAccess(act, url){
		var prompt_msg = '정말로 '+act+'하시겠습니까? \n 하시려 한다면'+act+'를 입력해주세요.';
		var result = prompt(prompt_msg);
		if(act === result){
			location.href = url+'.do';
		}
	}


	//  좋아요 버튼

	function likeControl(num){
		const flag = document.querySelector('#post-'+num+' .post__like a');
		const mode = flag.classList.contains('liked')?
			'unlike':'like';
			
		fetch(cpath+'post/'+mode+'.do?num='+num)
		.then(res=>{
			if(res.status<400) return res.json()
			else throw new Error()
		})
		.then(res=>{
			if(res.result) {
				switch (mode){
				case 'unlike':
					flag.querySelector('i').classList.replace('glyphicon-heart', 'glyphicon-heart-empty');
					flag.classList.remove('liked');
					flag.parentElement.querySelector('.count-like').innerText = --flag.parentElement.querySelector('.count-like').innerText;
					break;
				case 'like':
					flag.querySelector('i').classList.replace('glyphicon-heart-empty', 'glyphicon-heart');
					flag.classList.add('liked');
					flag.parentElement.querySelector('.count-like').innerText = ++flag.parentElement.querySelector('.count-like').innerText;
					break;
				}
			}
		})
		.catch(error=>{
			window.alert('서버와 통신 도중 에러가 발생했습니다.');
		})

		/*
		$.ajax({
			url:"post/"+mode+'.do?num'+num,
			type:'get',
			dataType:'json'
		}).done(res=>{
			if(res.result) {
				switch (mode){
				case 'unlike':
					flag.querySelector('i').classList.replace('glyphicon-heart', 'glyphicon-heart-empty');
					break;
				case 'like':
					flag.querySelector('i').classList.replace('glyphicon-heart-empty', 'glyphicon-heart');
					break;
				}
			}
		})
		*/
	}

	//	삭제버튼

	function deletePost(num){
		var result:boolean = window.confirm('정말로 삭제하시겠습니까?');
		if(result){
			fetch(cpath+'post/delete.do?num='+num)
			.then(res=> {return res.json()})
			.then(res=>{
				if(res.result){
					window.alert('성공적으로 삭제되었습니다.');
					$('#post-'+num).fadeOut(300, function() { $(this).remove(); });
				}else{
					window.alert('오류가 발생했습니다.');
				}
			}).catch(
				error=>{
					console.dir(error);
					window.alert('서버와 통신 도중 에러가 발생했습니다.')
				}
			)
			/*
			$.ajax({
				url : "post/delete.do",
				type : "get",
				data : {'num' : num},
				dataType: "json",
			}).done(
				res => {
					if(res.result){
						window.alert('성공적으로 삭제되었습니다.');
						$('#post-'+num).fadeOut(300, function() { $(this).remove(); });
					}else{
						window.alert('오류가 발생했습니다.');
					}
				}
			).fail(
				error=>{
					console.dir(error);
					window.alert('서버와 통신 도중 에러가 발생했습니다.')
				}
			)
			*/
		}
	}

	//	팔로우/언팔 버튼

	function followToggle(usercode){
		let url = "follower/follow.do";
		const result = fetch(cpath+url+'?usercode='+usercode)
		.then(res=> {return res.json()} )
		.then(res=>{
			if(res.result){
				const postList = document.querySelectorAll('.post');
				postList.forEach(i=>{
					if(i.classList.contains('post-user-'+usercode)){
						const target = i.querySelector('a[class*="follow"]');
						target.classList.replace('post__btn-follow', 'post__btn-unfollow');
						target.href = 'javascript:unfollowToggle('+usercode+')';
						target.querySelector('i.glyphicon').classList.replace('glyphicon-plus-sign', 'glyphicon-remove-sign');
						target.querySelector('span').innerText = 'Unfollow';			
					}
				});
				//	프로필페이지일 경우 추가 동작
				const item = document.querySelector('.profile');
				if(item){
					const target = item.querySelector('a[class*="follow"]');
					target.classList.replace('profile__btn-follow', 'profile__btn-unfollow');
					target.href = 'javascript:unfollowToggle('+usercode+')';
					target.querySelector('i.glyphicon').classList.replace('glyphicon-plus-sign', 'glyphicon-remove-sign');
					target.querySelector('span').innerText = 'Unfollow';	
				}
				window.alert('성공적으로 팔로우되었습니다.');
			}else{
				window.alert('오류가 발생했습니다.');
			}
		})
		.catch(err=>{window.alert('오류가 발생했습니다.');})
	}

	function unfollowToggle(usercode){
		let url = 'follower/unfollow.do';
		const result = fetch(cpath+url+'?usercode='+usercode)
		.then(res=>{
			if(res.status<400) {return res.json()}
			else throw new Error('error')
		})
		.then(res=>{
			if(res.result){
				window.alert('성공적으로 언팔로우되었습니다.');
				const postList = document.querySelectorAll('.post');
				postList.forEach(i=>{
					if(i.classList.contains('post-user-'+usercode)){
						const target = i.querySelector('a[class*="follow"]');
						target.classList.replace('post__btn-unfollow', 'post__btn-follow');
						target.href = 'javascript:followToggle('+usercode+')';
						target.querySelector('i.glyphicon').classList.replace('glyphicon-remove-sign', 'glyphicon-plus-sign');
						target.querySelector('span').innerText = 'Follow';
					}
				});
				//	프로필페이지일 경우 추가 동작
				const item = document.querySelector('.profile');
				if(item){
					const target = item.querySelector('a[class*="follow"]');
					target.classList.replace('profile__btn-unfollow', 'profile__btn-follow');
					target.href = 'javascript:followToggle('+usercode+')';
					target.querySelector('i.glyphicon').classList.replace('glyphicon-remove-sign', 'glyphicon-plus-sign');
					target.querySelector('span').innerText = 'Follow';	
				}
			}else{
				window.alert('오류가 발생했습니다.');
			}
		})
		.catch(err=>{window.alert('오류가 발생했습니다.');});
	}

	//	팔로우 언팔 끝


	//	회원가입 및 정보변경 폼

	//	아이디 중복체크
	$('#signup-id').on('keyup',()=>{
		var id = $('#signup-id').val();
		var target = $('#signup-id-check-result');
		fetch(cpath+"users/checkid.do?inputId="+id)
		.then(res=> {return res.json() })
		.then(result=>{
			if(result.istExist){
				target.text('').removeClass('false').fadeOut();
			}else{
				target.html(
					'<i class="glyphicon glyphicon-remove"></i>'+
					'중복된 ID이거나 서버 통신이 원활하지 않습니다.'
					).addClass('false').fadeIn();
			}
		})
		.catch(err=>{return false;})
	})

	//	기타 폼 체크
	function checkFormData(e){
		var id:HTMLInputElement = e.target.querySelector('input[name="id"]')
		var nickname:HTMLInputElement = e.target.querySelector('input[name="nickname"]');
		var pw:HTMLInputElement = e.target.querySelector('input[name="pw"]');
		var pw_c:HTMLInputElement  = e.target.querySelector('input[name="pw-c"]');
		var email:HTMLInputElement  = e.target.querySelector('input[name="email"]');
		var agree:HTMLInputElement  = e.target.querySelector('input[name="agree"]');

		var msg:string;

		if(id && id.value.length > 20){
			msg = "아이디 글자수 제한을 초과했습니다.";
		}else if(id && !id.value.match(/[A-Za-z0-9]/g)){
			msg = "아이디는 영숫자만 사용할 수 있습니다."
		}else if(nickname && nickname.value.length > 10){
			msg = "닉네임 글자수 제한을 초과했습니다.";
		}else if(pw){
			if(pw.value !== pw_c.value){
				msg = '패스워드 확인에 패스워드와 동일하게 작성하세요';
			}else if(pw.value.length<8){
				msg = '패스워드가 8글자 미만입니다.';
			}else if(!(pw.value.match(/[A-Z]{1,}/g) && pw.value.match(/[\!\@\#\$\;\:]/g))){
				msg = '패스워드에는 영대문자, 소문자와 숫자, 특수기호가 1자 이상 포함됩니다.'
			}
		}else if(email && !email.value.match(/[^\s]@[^\s]/) ){
			msg = '이메일 형식이 올바르지 않습니다.';
		}else if(agree && !agree.checked){
			msg = '규약에 동의해주세요';
		}else{
			if(!msg)return true;
		}
		alert(msg);
		e.preventDefault();
	}

	const forms = ['signUp', 'userSettingsForm'];
	forms.forEach(e=>{
		if(document.getElementById(e)) document.getElementById(e).addEventListener('submit', checkFormData);
	})

	//	댓글 폼
	$('.comment__info a').on('click',function(){
		$(this).parent().next().fadeToggle(250);
	});


}


