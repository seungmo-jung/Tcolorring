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
  <meta http-equiv="Cache-Control" content="no-cache"/>
  <meta http-equiv="Expires" content="0"/>
  <meta http-equiv="Pragma" content="no-cache"/>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
	<!--  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>-->
	<script type="text/javascript" src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/mypage/popup/timeSet.js"></script>
	</head> 
	<script type="text/javascript">
		$(function(){
			window.resizeTo(475, 325);
		});
	</script>
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
									<select id="s_time" class="time"></select>
									<span class="dash">-</span>
									<select id="e_time" class="time"></select>
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
		</div>
		
	  	<div class="tac mt20">
	  		<button onclick="javascript:rTimeSet.regSet()">추가</button>
	  		<button onclick="javascript:rTimeSet.goBack()">취소</button>
	  	</div>

  </body> 
 </html>