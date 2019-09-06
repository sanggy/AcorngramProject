
$(function () {

	let page =1;

    $(window).bottom();

    $(window).bind("bottom", function() {
    
		var obj = $(this);
	
		if (!obj.data("loading")) {
			obj.data("loading", true),
			(document.querySelector('.loader'))?"":
			$('.loading').append('<div class="loader"></div>');
			
				fetch('post/loadtl.do?page='+(++page))
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
				})
				.finally(()=>{
					document.querySelector('.loader').remove();
					obj.data("loading", false);
				})
				
				/*
				$.ajax('post/loadtl.do', { 
					method: 'GET',
					datatype:'html',
					data:{page:++page},
				}).done(response=>{
					$(".loading").empty(); 
					if(response!="end"){
						$(".timeline").append($($.parseHTML(response))); 
					}else{
						end_flag=1;
					}
				}).fail(error=>{
					page--;
				}); //ajax
				*/
				
		}
    
    });
});
