/**
 * 
 */
$('#source-list-btn').click(function(){
    console.log('클릭 확인')
    $.ajax({
        url:'/sound/ajaxList',
        type:'GET',
        success:function(result){
               console.log(result)
        },error:function(){
            console.log('왜 에러남')
        }
    })
})