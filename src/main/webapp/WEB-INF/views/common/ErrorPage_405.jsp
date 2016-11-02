<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%response.addHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\""); %>
 <!DOCTYPE html> 
 <html> 
  <head> 
  <meta charset="UTF-8"> 
  <title>Insert title here</title> 
	<script type="text/javascript">
	if("<%=session.getAttribute("so_cust_no")%>" != null && "<%=session.getAttribute("so_cust_no")%>"  != ""){
		location.replace('/main.do');
	}else{
		alert('잘못된 접근입니다.');
		location.replace('/main.do');
	}
	</script>
	</head> 
	<body> 
  	</body> 
 </html>