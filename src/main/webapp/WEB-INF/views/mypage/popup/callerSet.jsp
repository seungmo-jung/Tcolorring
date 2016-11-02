<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.Statement, java.sql.ResultSet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 
 response.setHeader("Pragma","no-cache"); //HTTP 1.0 
 response.setDateHeader ("Expires", 0); //prevents caching at the proxy server  
%>

 <!DOCTYPE html> 
 <html> 
  <head> 
  <meta charset="UTF-8"> 
  <meta http-equiv="Cache-Control" content="no-cache"/>
  <meta http-equiv="Expires" content="0"/>
  <meta http-equiv="Pragma" content="no-cache"/>
  <title>Insert title here</title> 
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/mypage/popup/callerSet.js"></script>
	<script type="text/javascript">
	$(function(){
		window.resizeTo(475, 325);
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
					</table>
			</div>
		  <div id="callerButtonDiv" class="tac mt20">
		  	<button onclick="javascript:rCallerSet.regSet('<%=session.getAttribute("mdn")%>')">추가</button>
		  	<button onclick="javascript:rCallerSet.goBack()">취소</button>
		  </div>		
		</div>		
  </body> 
 </html>