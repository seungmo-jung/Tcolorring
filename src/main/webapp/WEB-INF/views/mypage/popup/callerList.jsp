<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.Statement, java.sql.ResultSet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%response.addHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\""); %>

 <!DOCTYPE html> 
 <html> 
  <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
  <title>발신자별 음악설정</title> 
  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries --> 
  <!--[if lt IE 9]> 
   <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script> 
   <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script> 
  <![endif]-->
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css" />
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/content.css" />
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/mypage/popup/callerList.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/placeholders.min.js"></script>
	<script type="text/javascript">
		$(function(){
			window.resizeTo(490, 540);
		});
	</script>
	
	</head> 
	<body> 
		<div class="pop">
			<h1><img src="/img/design/tit_set_send.gif" alt="발신자별 음악설정" /></h1>
			<p class="text1 mt20">최대 5명까지 지정하신 번호로부터 전화가 올 경우 이용중인<br />음악으로 들려드립니다.</p>
			<div class="select_song tb_type" id="callerSetRegDiv">
				<table class="">
					<colgroup>
						<col width="25%" />
						<col width="75%" />
					</colgroup>
					<tbody>
						<tr>
							<th>전화번호</th>
							<td><input type="text" id="caller_mdn" onkeydown='return rCallerSet.onlyNumber(event)' onkeyup='rCallerSet.removeChar(event)' maxlength="11" placeholder="01000000000" /></td>
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
			
		  	<div id="callerButtonDiv" class="tac mt20">
		  		<a href="javascript:rCallerSet.regSet()"><img src="/img/design/btn_add.gif" alt="추가하기" /></a>
		  		<!--  <button onclick="javascript:rCallerSet.regSet('<%=session.getAttribute("mdn")%>')">추가</button>-->
		  	</div>
		  	
		  	<div id="callerFullDiv" style="display:none" class="tac mt20">
		  		<a href="javascript:alert('최대 5명까지 설정이 가능합니다. 삭제를 진행해주세요.')"><img src="/img/design/btn_add.gif" alt="추가하기" /></a>
		  	</div>
		  	
			<div id="callerListTable" class="tb_type mt30">
				<table summary="번호, 전화번호, 곡명, 가수, 수정, 삭제 셀로 구성">
					<caption>발신자별 음악설정</caption>
					<colgroup>
						<col width="7%" />
						<col width="25%" />
						<col width="33%" />
						<col width="24%" />
						<!--<col width="8%" /> -->
						<col width="8%" />
					</colgroup>
					<thead>
						<tr>
							<th scope="col">번호</th>
							<th scope="col">전화번호</th>
							<th scope="col">곡명</th>
							<th scope="col">가수</th>
							<!-- <th scope="col">수정</th> -->
							<th scope="col">삭제</th>
						</tr>
					</thead>
					<tbody id="tList">
					</tbody>
		  		</table>
			</div>
			
			<div class="tac mt20">
				<a href="javascript:rCallerList.closePopup()"><img src="/img/design/btn_confirm.gif" alt="확인" /></a>
			</div>
	</div>
  </body> 
 </html>