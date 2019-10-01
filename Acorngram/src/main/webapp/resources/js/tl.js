$(function () {

	let page = 1;
	let flag = true;

    $(window).bottom({proximity: 0.05});

    $(window).bind("bottom", function() {
		
		var obj = $(this);
	
		if (!obj.data("loading") && flag) {
			obj.data("loading", true),
			(document.querySelector('.loader'))?"":
			$('.loading').append('<div class="loader"></div>');
			
				fetch('post/loadtl.do?page='+(++page) + "&" + location.search.substring(1))
				.then(res=>res.text())
				.then(res=>{
					if(res.trim()){
						document.querySelector('.timeline').insertAdjacentHTML('beforeend', res);
						loadTL();
					}else{
						throw "empty";
					}
				})
				.catch(err=>{
					page--;
					if(err==="empty") flag = false;
				})
				.finally(()=>{
					document.querySelector('.loader').remove();
					obj.data("loading", false);
				})
				
		}
    
    });
});
