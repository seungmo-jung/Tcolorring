<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html> 
 <html> 
  <head> 
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
  	<title>Tplring</title> 
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css" media="all"/>
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/content.css" media="all"/>
  	<!--	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" media="all"/>  -->
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
 	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
	<!--  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/utils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/popup/popup.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/jquery_paging.js"></script>

	<script type="text/javascript" src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
	<script type="text/javascript">
	  	$(function(){
	  		var innerHtml = '';
	  		
	  		if("<%=session.getAttribute("service_yn")%>" == "Y"){
	  			innerHtml += '<strong>'+'<%= session.getAttribute("name")%>'+'</strong>님 (' + rCommon.appendHyphen('<%=session.getAttribute("mdn")%>') + ') ';
	  			innerHtml += '<a href="Javascript:rCommon.toMypage();"><img src="/img/design/btn_mypage.gif" alt="마이페이지"/></a>';
	  		}else{
	  			innerHtml += '<strong>'+'<%= session.getAttribute("name")%>'+'</strong>님 (' + rCommon.appendHyphen('<%=session.getAttribute("mdn")%>') + ') ';
	  			innerHtml += '<a href="Javascript:rCommon.serviceAlert();"><img src="/img/design/btn_mypage.gif" alt="마이페이지"/></a>';
	  		}
	  		
	  		$('.login_info').append(innerHtml);
		});
  	</script> 
  </head>
  <body>
  <div class="call">
  <c:if test="${isHeaderHas}"> 
   			<div class="top">
   				<h1><a href="/main.do">
   				<img src="/img/logo/<%=session.getAttribute("so_code")%>.gif" alt="티플러스 통화연결음"/>
   				</a></h1>
   				<div class="login_info">
   				<!--  
   						<% 
							if(session.getAttribute("service_yn").equals("Y")){
						%>
						<strong><%= session.getAttribute("name")%> </strong> 님 <%=session.getAttribute("mdn")%>
						<a href="Javascript:rCommon.toMypage();"><img src="/img/design/btn_mypage.gif" alt="마이페이지"/></a>
						<%
							} else {
						%>
						<strong><%= session.getAttribute("name")%> </strong> <%="님 ("+ session.getAttribute("mdn")+")" %>
						<a href="Javascript:rCommon.serviceAlert();"><img src="/img/design/btn_mypage.gif" alt="마이페이지"/></a>
						<%
							}
						%>
						-->
				</div>
			</div>
</c:if>
