<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.Statement, java.sql.ResultSet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html style="overflow:hidden">
<head>
<title>tplus 연결음 듣기</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/content.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/popup/popup.js"></script>


<script type="text/javascript">
	rPopup.service_yn_setting('<%=session.getAttribute("service_yn") %>');
</script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]> 
   <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script> 
   <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script> 
  <![endif]-->
<script type="text/javascript">
	//get player url
	function getSongPlayer(song_id, song_part) {
		set_song_part = song_part;
		
		//window.console.log(rCommon.getBrowserType());
		
		var data = {
			song_id : song_id,
			song_part : song_part
		};

		$.ajax({
			url : '/popup/getplayer.do',
			type : 'GET',
			data : data,
			asysnc : false,
			cache : false,
			dataType : 'text',
			success : function(result) {
				addSongPlayer(result);
			},
			error : function(error) {
				//console.log(error);
			}
		});

	}
	
	//add music player
	function addSongPlayer(url) {
		var innerHtml = '';
		if (url != null && url != '') {
			innerHtml += '<div id="long_add_player">';
			if(rCommon.getBrowserType() == 7 || rCommon.getBrowserType() == 8){
				//<embed src="audiofile.mp3">
				//window.console.log('audio player check');
				innerHtml += '<embed class="long_play" src="' + url +'" style="filter: invert()" autostart="0" volume="50" loop="1"></embed>';
			}else {
					innerHtml += '<audio class="long_play" src="' + url +'" controls="controls" style="width: 99%"></audio>';
			}
			innerHtml += '</div>';
			$('#long_info_ring').append(innerHtml);
		}
	}
	
	//팝업창 닫기
	function popupClose(){
		window.close();
	}
</script>
</head>

<body style="overflow:hidden">
	<div class="player">
		<div class="cont">
			<img src="/img/design/logo_s.png" alt="tplus" />
			
			<p class="tit_song"><c:out value='${singer_name}' /> - <c:out value='${song_title}' /> (<c:out value='${c_longplay_yn}' />/<c:out value='${c_song_part}' />)</p>
			
			<div id="long_info_ring" class="info_ring">
			</div>
		</div>
	</div>
	
	<script>
		$(function(){
			getSongPlayer(${song_id}, ${song_part});
		});
	</script>
</body>
</html>
