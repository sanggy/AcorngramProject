$(function () {

    let page:number = 1;
    let end_flag:number = 0; 

    $(window).bottom({proximity: 0.6});

    $(window).bind("bottom", function() {
    
        if(end_flag==0){ //ページが最後までいってなければ
    
			var obj = $(this);
		
			if (!obj.data("loading")) {
				obj.data("loading", true);
		
				(document.querySelector('.loader'))?"":
				$('.loading').append('<div class="loader"></div>');
		
				setTimeout(function() {
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
					obj.data("loading", false);
				}, 1000);
			}
    
        } //end_flag
    });
});
