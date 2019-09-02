$(function () {

    let page:number = 1;
    let end_flag:number = 0; //最後のページまでいったら1にして次から読み込ませない

    $(window).bottom({proximity: 0.8});

    $(window).bind("bottom", function() {
    
        if(end_flag==0){ //ページが最後までいってなければ
    
        var obj = $(this);
    
            if (!obj.data("loading")) {
    
            obj.data("loading", true);
    
            $('.loading').append('loading...'); //画面にloading...と表示
    
            setTimeout(function() {
    
                $.ajax('post/loadtl.do', { //data.phpが実際に表示するデータを読み込むファイル
                method: 'GET',
                datatype:'html',
                data:{page:++page},
                error: function(xhr, error) {
                    console.log('실패');
                    console.log(error);
                },
                success: function(response) {
                    $(".loading").empty(); //画面に出ているloading...を空にする
                    if(response!="end"){
                        $(".timeline").append($($.parseHTML(response))); //コンテンツを追加
                    }else{
                        end_flag=1;
                    }
                } //success
                }); //ajax
    
                obj.data("loading", false);
            }, 1000);
            }
    
        } //end_flag
    });
});
