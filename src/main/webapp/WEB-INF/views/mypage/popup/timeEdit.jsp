<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.Statement, java.sql.ResultSet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <!DOCTYPE html> 
 <html> 
  <head> 
  <meta charset="UTF-8"> 
  <title>Insert title here</title> 
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
			
			var s_time = <%=request.getParameter("s_time") %>;
			var e_time = <%=request.getParameter("e_time") %>;
			if(s_time < 10){
				s_time = '0'+s_time;
			}
			
			if(e_time <10){
				e_time = '0'+e_time;
			}
			$("#s_time option[value= '"+s_time+"']").attr("selected", true);
			$("#e_time option[value= '"+e_time+"']").attr("selected", true);
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
									<input type="hidden" id="original_s_time" value="<%= request.getParameter("s_time")%>" readonly />
									<span class="dash">-</span>
									<select id="e_time" class="time"></select>
									<input type="hidden" id="original_e_time" value="<%= request.getParameter("e_time")%>" readonly />
								</div>
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
	  		<button onclick="javascript:rTimeSet.regEdit()">확인</button>
	  		<button onclick="javascript:rTimeSet.goBack()">취소</button>
	  	</div>
  </body> 
 </html>