<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<jsp:include page="./commonPlayer.jsp" />
	
	<c:forEach var="entry" items="${vcode_list}">
		<script>addLi(${entry.longplay_yn}, ${entry.song_part},${entry.vcode}, ${song_id}, ${entry.song_price}, ${entry.sub_code})</script>
	</c:forEach>	
	
	<script type="text/javascript">
		$(function(){
			if(check_long_type != 0 && check_basic_type != 0){
				//getSongPlayer(${song_id}, '1', '1');
				//getSongPlayer(${song_id}, '1', '0');
			}else if(check_long_type == 0 && check_basic_type != 0){
				$('#long_type').hide();
				$('#basic_type').show();
				$('#long_type_part').hide();
				$('#basic_type_part').show();
				$('#longTypeButton').hide();
				
				set_result_price = set_basic_price;
				set_result_sub_code = set_basic_sub_code;
				set_song_type = '0';
				
				//getSongPlayer(${song_id}, '1', '0');
			}else if(check_basic_type == 0 && check_long_type != 0){
				$('#basicTypeButton').hide();
				//getSongPlayer(${song_id}, '1', '1');
			}else{
				window.alert("팝업창을 닫아주세요.");
			}
		});
	</script>
	

