<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HamSound</title>


<link rel="stylesheet" href="/css/main/main.css">

<!-- howler -->
<script src="https://cdn.jsdelivr.net/npm/howler@2.2.4/dist/howler.min.js"></script>

<!-- calamansi -->
<link rel="stylesheet" href="/calamansi-js-master/dist/calamansi.min.css">

</head>
<body>

 <!-- Hero Section Begin -->
    <section class="hero">
        <div class="container">
               
			<div class="card-list">
			 <div class="card"><div class="card-audio"></div></div>
			 <div class="card"><div class="card-audio"></div></div>
			 <div class="card"><div class="card-audio"></div></div>
			 <div class="card"><div class="card-audio"></div></div>
			 <div class="card"><div class="card-audio"></div></div>
			 <div class="card"><div class="card-audio"></div></div>
			 <div class="card"><div class="card-audio"></div></div>
			 <div class="card"><div class="card-audio"></div></div>
			 <div class="card"><div class="card-audio"></div></div>
			 <div class="card"><div class="card-audio"></div></div>
			</div>
        </div>
    </section>
    <!-- Hero Section End -->
    
    
 


    <button class="btn btn-info" onclick="playAudio()">오디오 재생</button>

    <script>
        // Howler.js를 사용하여 오디오를 재생하는 함수
        function playAudio() {
            // 새로운 Howl 인스턴스 생성
            var sound = new Howl({
                src: ['https://cdn.freesound.org/previews/59/59928_516569-hq.ogg'] // 여러분의 오디오 파일 경로로 변경하세요
            });

            // 오디오 재생
            sound.play();
        }
    </script>


 <div style="width: 300px; height: 300px;">
        <div id="calamansi-player-1">
        </div>
    </div>

    <script src="/calamansi-js-master/dist/calamansi.min.js"></script>
    
    <script>
       
    </script>
<script src="/js/sound/list.js"></script>

<script src="/js/main/main.js"></script>
</body>
</html>