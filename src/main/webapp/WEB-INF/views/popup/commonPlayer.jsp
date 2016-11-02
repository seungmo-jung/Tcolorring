<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.Statement, java.sql.ResultSet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html style="overflow:hidden">
<head>
<title>tplus 통화연결음</title>
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
$(function(){
	$(".song_flow a").click(function(){
		$(this).parent().addClass("on").siblings().removeClass("on");
	});
});
</script>

<script type="text/javascript">
	var set_song_type = '1'; // long 1, basic 0
	var set_song_part = '1'; // 1 하이라이트, 2 전반부, 3 후반부
	var set_vcode = ''; // 곡의 vcode를 알려줌.

	var set_result_price = '0'; // 최종 가격
	var set_long_price = '0'; // 롱 가격
	var set_basic_price = '0'; // 일반곡 가격
	
	var set_result_sub_code = '0'; //최종 Sub_code
	var set_long_sub_code ='0'; // long sub_code
	var set_basic_sub_code = '0'; // basic sub_code
	
	var visual_price='0'; // 웹에서 보이는 가격
	
	var check_long_type = 0;
	var check_basic_type = 0;
	
	//롱 버튼
	function longTypeButton() {
		$('#long_type').show();
		$('#basic_type').hide();
		$('#long_type_part').show();
		$('#basic_type_part').hide();
		
		set_result_price = set_long_price;
		set_result_sub_code = set_long_sub_code;
		set_song_type = '1';
		
		if(rCommon.getBrowserType() == 7 || rCommon.getBrowserType() == 8){
			embedStop();
		}else{
			audioStop();
		}
		
	}

	//일반 버튼
	function basicTypeButton() {
		$('#long_type').hide();
		$('#basic_type').show();
		$('#long_type_part').hide();
		$('#basic_type_part').show();
		
		set_result_price = set_basic_price;
		set_result_sub_code = set_basic_sub_code;
		set_song_type = '0';
		
		if(rCommon.getBrowserType() == 7 || rCommon.getBrowserType() == 8){
			embedStop();
		}else{
			audioStop();
		}
		
	}

	
	//오디오 전부 스탑.
	function audioStop() {
		var audioPlayer = '';

		for (var i = 0; i < document.getElementsByTagName('audio').length; i++) {
			audioPlayer = document.getElementsByTagName('audio')[i];
			audioPlayer.pause();
			audioPlayer.currentTime = 0;
		}
	}
	
	//Embed 전부 스탑.
	function embedStop() {
		var embedPlayer = '';

		for (var i = 0; i < document.getElementsByTagName('embed').length; i++) {
			embedPlayer = document.getElementsByTagName('embed')[i];
			embedPlayer.stop();
		}
	}

	//get player url
	function getSongPlayer(song_id, song_part, longplay_yn) {
		set_song_part = song_part;
		
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
			success : function(result ) {
				//console.log(result);
				//console.log(song_part);
				addSongPlayer(result, longplay_yn);
			},
			error : function(error) {
				//console.log(error);
			}
		});
	}
	
	//add music player
	function addSongPlayer(url, longplay_yn) {
		var innerHtml = '';

		if (url != null) {
			if (longplay_yn == '1') {
				$('#long_add_player').remove();
				innerHtml += '<div id="long_add_player">';
				if(rCommon.getBrowserType() == 7 || rCommon.getBrowserType() == 8){
					innerHtml += '<embed class="long_play" src="' + url +'" style="filter: invert()" autostart="0" volume="50" loop="1"></embed>';
				}else{
					innerHtml += '<audio class="long_play" src="' + url + '" controls="controls" style="width: 99%"></audio>';
				}
				innerHtml += '</div>';

				$('#long_player').append(innerHtml);

			} else if (longplay_yn == '0') {
				$('#basic_add_player').remove();

				innerHtml += '<div id="basic_add_player">';
				if(rCommon.getBrowserType() == 7 || rCommon.getBrowserType() == 8){
					innerHtml += '<embed class="long_play" src="' + url +'" style="filter: invert()" autostart="0" volume="50" loop="1"></embed>';
				}else{
					innerHtml += '<audio class="long_play" src="' + url + '" controls="controls" style="width: 99%"></audio>';
				}
				innerHtml += '</div>';

				$('#basic_player').append(innerHtml);
			} else if (longplay_yn == 'F'){
				if(check_long_type > 0 && check_basic_type > 0){
					$('#long_add_player').remove();
					innerHtml += '<div id="long_add_player">';
					if(rCommon.getBrowserType() == 7 || rCommon.getBrowserType() == 8){
						innerHtml += '<embed class="long_play" src="' + url +'" style="filter: invert()" autostart="0" volume="50" loop="1"></embed>';
					}else{
						innerHtml += '<audio class="long_play" src="' + url + '" controls="controls" style="width: 99%"></audio>';
					}
					innerHtml += '</div>';
					$('#long_player').append(innerHtml);
					
					$('#basic_add_player').remove();
					innerHtml = '<div id="basic_add_player">';
					if(rCommon.getBrowserType() == 7 || rCommon.getBrowserType() == 8){
						innerHtml += '<embed class="long_play" src="' + url +'" style="filter: invert()" autostart="0" volume="50" loop="1"></embed>';
					}else{
						innerHtml += '<audio class="long_play" src="' + url + '" controls="controls" style="width: 99%"></audio>';
					}
					innerHtml += '</div>';
					$('#basic_player').append(innerHtml);
				}else if(check_long_type == 0 && check_basic_type > 0){
					$('#basic_add_player').remove();
					innerHtml = '<div id="basic_add_player">';
					if(rCommon.getBrowserType() == 7 || rCommon.getBrowserType() == 8){
						innerHtml += '<embed class="long_play" src="' + url +'" style="filter: invert()" autostart="0" volume="50" loop="1"></embed>';
					}else{
						innerHtml += '<audio class="long_play" src="' + url + '" controls="controls" style="width: 99%"></audio>';
					}
					innerHtml += '</div>';
					$('#basic_player').append(innerHtml);
				}else if (check_long_type == 0 && check_basic_type == 0){
					alert("해당 곡에 대한 듣기 기능을 제공할 수 없습니다. 창을 닫아주세요.");
				}
			}
		} else{
			alert("듣기 기능을 사용 할 수 없습니다. 창을 닫아주세요.");
		}
	}
	
	//천의 자리 콤마 찍기
	function comma(str) {
	    str = String(str);
	    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
	}
	
	//add type li
	function addLi(longplay_yn, song_part, vcode, song_id, song_price, sub_code) {
		var innerHtml = '';

		if (longplay_yn == '1') {
			check_long_type++;
			if (song_part == '1') {
				//log price 설정, result price 설정.
				set_long_price = song_price;
				set_result_price = set_long_price;
				
				set_long_sub_code = sub_code;
				set_result_sub_code = set_long_sub_code;
				
				visual_price = parseInt(song_price * 1.1);
				innerHtml += '<span class="time"> 롱 컬러링(60초): '+comma(visual_price)+' 원</span>';
				innerHtml += '<span class="vat"> (VAT 포함)</span>';
				innerHtml += '<a href="javascript:purchasefirm(\'' + song_id + '\')" class="btn_pay"><img src="/img/design/btn_pay.gif" alt="구매하기" /></a>';
				
				$('#long_info_ring').append(innerHtml);
				innerHtml = '';
				
				innerHtml = '<li id="long_vcode" class="long_vcode on" data-value="'+ vcode +'" onclick="getSongPlayer(\'' + song_id + '\', \'' + song_part + '\', \''+ longplay_yn + '\')"><a href="#">하이라이트</a></li>';
			} else if (song_part == '2') {
				innerHtml = '<li id="long_vcode" data-value="'+ vcode +'" onclick="getSongPlayer(\'' + song_id + '\', \'' + song_part + '\', \''+ longplay_yn + '\')"><a href="#">전반부</a></li>';
			} else if (song_part == '3') {
				innerHtml = '<li id="long_vcode" data-value="'+ vcode +'" onclick="getSongPlayer(\'' + song_id + '\', \'' + song_part + '\', \''+ longplay_yn + '\')"><a href="#">후반부</a></li>';
			}
			$('#long_type_part_ul').append(innerHtml);
		} else if (longplay_yn == '0') {
			check_basic_type++;
			if (song_part == '1') {
				set_basic_price = song_price;
				set_basic_sub_code = sub_code;
				
				getSongPlayer(song_id, song_part, 'F');
				
				visual_price = parseInt(song_price * 1.1);
				innerHtml += '<span class="time"> 일반 컬러링(40초): ' + comma(visual_price)+' 원</span>';
				innerHtml += '<span class="vat"> (VAT 포함)</span>';
				innerHtml += '<a href="javascript:purchasefirm(\'' + song_id + '\')" class="btn_pay"><img src="/img/design/btn_pay.gif" alt="구매하기" /></a>';
					
				$('#basic_info_ring').append(innerHtml);
				
				innerHtml = '';
				
				innerHtml = '<li id="basic_vcode" class="on" data-value="'+ vcode +'" onclick="getSongPlayer(\'' + song_id + '\', \'' + song_part + '\', \''+ longplay_yn + '\')"><a href="#">하이라이트</a></li>';
			} else if (song_part == '2') {
				innerHtml = '<li id="basic_vcode" data-value="'+ vcode +'" onclick="getSongPlayer(\'' + song_id + '\', \'' + song_part + '\', \''+ longplay_yn + '\')"><a href="#">전반부</a></li>';
			} else if (song_part == '3') {
				innerHtml = '<li id="basic_vcode" data-value="'+ vcode +'" onclick="getSongPlayer(\'' + song_id + '\', \'' + song_part + '\', \''+ longplay_yn + '\')"><a href="#">후반부</a></li>';
			}
			$('#basic_type_part_ul').append(innerHtml);
		}
	}
	
	//Check 된 Vcode 값 확인.
	function setVcode(){
		
		if(set_song_type == '1'){
			set_vcode = $("#long_type_part_ul").find(".on").data("value");
		}else if(set_song_type == '0'){
			set_vcode = $("#basic_type_part_ul").find(".on").data("value");
		}
		
	}
	
	//곡 구매 진행.
	function purchaseSong(song_id){
		setVcode();

		var data = {
			song_id:song_id,
			longplay_yn:set_song_type,
			song_part:set_song_part,
			vcode:set_vcode,
			price: set_result_price,
			sub_code: set_result_sub_code
		};
		
		$.ajax({
			url : '/popup/purchase.do',
			type : 'GET',
			data : data,
			asysnc : false,
			cache : false,
			dataType : 'json',
			success : function(result){
				if(result.code == '1000'){
					alert(result.msg);
					popupClose();
					window.opener.location = "/mypage/mypage.do";
				}else if(result.code == '2000'){
					alert(result.msg);
				}else if(result.code == '0000'){
					alert(result.msg);
				}
			},
			error : function(error){
				alert(error);
				popupClose();
			}
		});
	}
	
	//곡 구매 확인.
	function purchasefirm(song_id){
  		if('<%=session.getAttribute("service_yn") %>' == 'Y'){
  			var message = confirm("구매하시겠습니까?");
  			
  			if(message == true){
  				purchaseSong(song_id);
  			}
  		}else{
				alert('티플링 서비스를 가입해주세요.');
		}
  	}
	
	//미리 듣기에서 구매창 이동.
	function passToPurchase(set_song_type, song_id, song_part, vcode, song_title, singer_name){
		rPopup.service_yn_setting('<%=session.getAttribute("service_yn") %>');
		rPopup.purchasePopup(song_id, song_title, singer_name);
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
		<%
			if(session.getAttribute("so").equals("KCT")){
		%>
			<img src="/img/design/logo_s.png" alt="tplus" />
		<%
			}else if(session.getAttribute("so").equals("TBRD")){
		%>
			<img src="/img/design/logo_t.png" alt="tbroad" />
		<% 	
			}else{
		%>
			<img src="/img/design/logo_s.png" alt="tplus" />
		<% 	
			}
		%>
			<div class="type_ring" id="type_select" >
				<a id="longTypeButton" href="javascript:longTypeButton()">롱 컬러링</a> | 
				<a id="basicTypeButton" href="javascript:basicTypeButton()">일반 컬러링</a>
			</div>
			<p class="tit_song"><c:out value='${singer_name}' /> - <c:out value='${song_title}' /></p>
			
			<div id="long_type">
				<div id="long_player">
					<div id="long_add_player"></div>
				</div>
				<div id="long_info_ring" class="info_ring">
				</div>
			</div>
			
			<div id="basic_type" style="display: none">
				<div id="basic_player">
					<div id="basic_add_player"></div>
				</div>
				<div id="basic_info_ring" class="info_ring">
				</div>
			</div>
		</div>
		
		<div id="long_type_part">
			<ul id="long_type_part_ul" class="song_flow">
			</ul>
		</div>
		
		<div id="basic_type_part" style="display: none">
			<ul id="basic_type_part_ul" class="song_flow">
			</ul>
		</div>
	</div>
	
