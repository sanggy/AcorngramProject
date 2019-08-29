// import * as $ from 'jquery';

var makeCover = ()=>{
	var item = document.createElement('div');
	item.className = 'cover';
	item.onclick = ()=>{item.remove()}
	return item;
}
	 
const toggle = {
	run: function (org, target, is_cover){
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
	}
}

toggle.run(
	document.querySelector('.header__user-info'),
	document.querySelector('nav.user-menu'),
	true
);
