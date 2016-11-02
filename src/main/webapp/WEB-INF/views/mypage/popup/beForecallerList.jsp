<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.Statement, java.sql.ResultSet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <!DOCTYPE html> 
 <html> 
  <head> 
  <meta charset="UTF-8"> 
  <title>Insert title here</title> 
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

			<div id="callerListTable" class="tb_type mt30">
				<table summary="번호, 전화번호, 곡명, 가수, 수정, 삭제 셀로 구성">
					<caption>발신자별 음악설정</caption>
					<colgroup>
						<col width="7%" />
						<col width="25%" />
						<col width="29%" />
						<col width="20%" />
						<col width="8%" />
						<col width="8%" />
					</colgroup>
					<thead>
						<tr>
							<th scope="col">번호</th>
							<th scope="col">전화번호</th>
							<th scope="col">곡명</th>
							<th scope="col">가수</th>
							<th scope="col">수정</th>
							<th scope="col">삭제</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
		  		</table>
			</div>
			
			<div id="callerSetRegDiv" class="tac mt20">
				<button id="addCallerButton" onclick="javascript:rCallerList.callerAddPopup()">추가</button>
				<a href="javascript:rCallerList.closePopup()"><img src="/img/design/btn_confirm.gif" alt="확인" /></a>
			</div>
	</div>
  </body> 
 </html>