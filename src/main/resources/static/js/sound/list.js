/**
 * 
 */
const card=document.getElementsByClassName('card');
const cardAudio=document.getElementsByClassName('card-audio');


var soundObj;
var soundArr=new Array();
var soundArrCal=new Array();


    $.ajax({
        url:'/sound/ajaxList',
        type:'GET',
        success:function(result){
              console.log(result)
               for(r of result){
                   
             
                soundObj=r.previews
               
           
                soundArr.push(Object.values(soundObj)[1]);
                let a = { source: Object.values(soundObj)[1] };
                
                soundArrCal.push(a); 
                

                let img='<img src="'
                let imgClose='">'
            
                let sourceOpen='<source type="audio/ogg" src=';
                let sourceClose='>';
                
                for(let i=0; i<cardAudio.length; i++){
                    cardAudio[i].innerHTML=sourceOpen+soundArr[i]+sourceClose;
                }

              
				const calamansiElements = document.querySelectorAll('.card-audio');
							    
							    calamansiElements.forEach((element,index) => {
							        new Calamansi(element, {
							            skin: '/calamansi-js-master/dist/skins/calamansi-compact',
							            playlists: {
							                'Classics': [soundArrCal[index]],
							            },
							        });
							    });

                    
                
               }

          /*    for(let i=0; i<cardAudio.length; i++){
				  new Calamansi(document.querySelector('.card-audio'), {
				            skin: '/calamansi-js-master/dist/skins/calamansi',
				            playlists: {
				                'Classics': soundArrCal,
				            },
				            defaultAlbumCover: 'http://audiocogs.org/dgplayer/resources/fallback_album_art.png',
				        });
				
				        Calamansi.autoload();
            
               }*/

			 
               
        },error:function(){
           
        }
    })




