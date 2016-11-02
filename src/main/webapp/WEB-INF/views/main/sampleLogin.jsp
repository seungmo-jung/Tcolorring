<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="sampleLoginForm">
		<!--  <form action="http://tplring.com" method="POST">-->
 		<form action="/" method="POST"> -
			고객관리번호 (CCBS_SEQ) : <input type="text" id="CCBS_SEQ" name="CCBS_SEQ" value="0123456789"/><br/>
			고객핸드폰번호 (MEM_CELLNUM) : <input type="text" id="MEM_CELLNUM" name="MEM_CELLNUM" value="01036093169"/><br/>
			고객이름 (MEM_NAME) : <input type="text" id="MEM_NAME" name="MEM_NAME" value="정승모"/><br/>
			SO코드 (SO_CODE) : <input type="text" id="SO_CODE" name="SO_CODE" value="1009"/><br/>
			가입여부
				<select id="SERVICE_YN" name="SERVICE_YN">
					<option value="Y">Y</option>
					<option value="N">N</option>
				</select>
				<br/>
			<input type="submit" value="전송">
		</form>
	</div>
</body>
</html>
