// import * as $ from 'jquery';

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
	document.querySelector('.writepost form'),
	false
);

function toggleWritePopup(){
	var block:HTMLElement = document.querySelector('.write-post');
	block.classList.toggle('is-visible');
}

function confirmAccess(url){
	var result:boolean = window.confirm('정말로 하시겠습니까?');
	if(result){
		location.href = url;
	}
}



function likeControl(num){
	const flag = document.querySelector('.post-'+num+' .post__like a');
	const mode = flag.classList.contains('liked')?
		'unlike':'like';
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
}

function deletePost(num){
	var result:boolean = window.confirm('정말로 삭제하시겠습니까?');
	if(result){
		fetch('post/delete.do?num='+num)
		.then(res=>res.json())
		.then(
			res=>{
				if(res.result){
					window.alert('성공적으로 삭제되었습니다.');
					$('.post-'+num).fadeOut(300, function() { $(this).remove(); });
				}else{
					window.alert('오류가 발생했습니다.');
				}
			}
		).catch(
			error=>{
				$('.post-'+num).fadeOut(300, function() { $(this).remove(); });
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

(function loadPost(){
	$('time').val(
		new moment($(this).datetime).format('YYYY/MM/DD hh:mm:ss')
	);
	$('.post__btn-like').on('hover',function(){
		console.dir($(this).children('.glyphicon'));
		$(this).children('.glyphicon')
			.toggleClass('glyphicon-heart')
			.toggleClass('glyphicon-heart-empty');
	});
})();

$('#form__writepost-img').on('change', function (e) {
	var reader = new FileReader();
	reader.onload = function (e) {
		$("#preview").attr('src', e.target.result);
	}
	reader.readAsDataURL(e.target.files[0]);
});