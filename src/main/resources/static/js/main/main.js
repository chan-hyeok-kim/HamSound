/**
 * 
 */
window.onload=function(){
     
    $.ajax({
        type:'GET',
        url:'sound/sourceList',
        success:function(result){
            console.log(result);
        }
    })
    
}
