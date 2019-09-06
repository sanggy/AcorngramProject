$(function () {

    let page = sessionStorage.getItem('page')?sessionStorage.getItem('page'):1;
    let end_flag:number = 0; 

    $(window).bottom({proximity: 0.8});

    $(window).bind("bottom", function() {
    
        if(end_flag==0){ //ページが最後までいってなければ
    
			var obj = $(this);
		
			if (!obj.data("loading")) {
				obj.data("loading", true),
				(document.querySelector('.loader'))?"":
				$('.loading').append('<div class="loader"></div>');
		
				setTimeout(()=> {
					fetch('post/loadtl.do',{
						body:{page:++page}						
					})
					.then(res=>res.text())
					.then(res=>{
						if(res!="end"){
							document.querySelector('.timeline')
								.innerHTML += res;
						}else{
							end_flag = 1;
						}
					})
					.catch(err=>{
						page--;
					})
					.finally(()=>{
						document.querySelector('.loader').remove();
						sessionStorage.setItem('page', page);
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
					
				}, 1000);
			}
    
        } //end_flag
    });
});
