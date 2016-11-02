<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.Statement, java.sql.ResultSet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%response.addHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\""); %>

 <!DOCTYPE html> 
 <html> 
  <head> 
  <title>시간별 음악설정</title> 
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
  <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries --> 
  <!--[if lt IE 9]> 
   <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script> 
   <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script> 
  <![endif]-->
 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css" />
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/content.css" />
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
	<!--  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>-->
	<script type="text/javascript" src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/mypage/popup/timeList.js"></script>
	<script type="text/javascript">
		$(function(){
			window.resizeTo(490, 470);
		});
	</script>
	</head> 
	<body>
		<div class="pop">
			<h1><img src="/img/design/tit_set_time.gif" alt="시간대별 음악설정" /></h1>
			<p class="text1 mt20">시간대 별로 최대 3구간의 시간을 지정하여 음악을 선택할 수<br /> 있습니다.</p>
			
			<div id="timeSetRegDiv" class="select_song tb_type">
				<table class="">
					<colgroup>
						<col width="25%" />
						<col width="75%" />
					</colgroup>
					<tbody>
						<tr>
							<th>시간설정</th>
							<td>
								<div class="select_area">
									<select id="set_s_time" class="time"></select>
									<span class="dash">-</span>
									<select id="set_e_time" class="time"></select>
								</div>
							</td>
						</tr>
						
						<tr>
							<th>곡명선택</th>
							<td>
								<div class="select_area">
									<select id="songList" class="songList"></select>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>	
			
			<div id="timeButtonDiv" class="tac mt20">
				<a href="javascript:rTimeSet.regSet()"><img src="/img/design/btn_add.gif" alt="추가하기" /></a>
		  	</div>
		  	
		  	<div id="timeFullDiv" style="display:none" class="tac mt20">
		  		<a href="javascript:alert('최대 3구간 까지 설정이 가능합니다. 삭제를 진행해주세요.')"><img src="/img/design/btn_add.gif" alt="추가하기" /></a>
		  	</div>
		  	
			<div class="tb_type mt30">
				<table summary="번호, 전화번호, 곡명, 삭제 셀로 구성">
					<caption>발신자별 음악설정</caption>
						<colgroup>
							<col width="7%" />
							<col width="23%" />
							<col width="33%" />
							<col width="24%" />
							<!--<col width="8%" /> -->
							<col width="8%" />
							
						</colgroup>
						<thead>
							<tr>
								<th scope="col">번호</th>
								<th scope="col">시간대</th>
								<th scope="col">곡명</th>
								<th scope="col">가수</th>
								<!-- <th scope="col">수정</th>  -->
								<th scope="col">삭제</th>
							</tr>
						</thead>
						<tbody id="tList">
						</tbody>
				</table>
			</div>
		</div>
	
		<div class="tac mt20">
			<a href="javascript:rTimeList.closePopup()"><img src="/img/design/btn_confirm.gif" alt="확인" /></a>
	  </div>
  </body> 
 </html>