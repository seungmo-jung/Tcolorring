<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/more/albumMore.js"></script>
	
	<!-- 앨범 타이틀 -->
	<h2 class="stit"><img src="/img/design/tit_album.gif" alt="최신앨범" /></h2>
	
	<!-- 검색 부분 -->
	<jsp:include page="../search/searchForm.jsp"/>
		<!-- 
	  	<div class="background-div">

		</div>
		-->
		<input type="hidden" id="listDiv-currentPage" name="listDiv-currentPage" value="1" />
	  	<div id="AlbumListMore" class="album-div" >
			<ul id="ulAlbumListMore" class="album-list">
			</ul>
		</div>
		<div id="listDiv-paging" class="paging"></div>	
		<br/>
		
<%@ include file="/WEB-INF/views/common/footer.jsp"%>