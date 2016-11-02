<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/more/genreMore.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/popup/popup.js"></script>
	
	<script type="text/javascript">
		rPopup.service_yn_setting('<%=session.getAttribute("service_yn") %>');
		
		$(function(){
			$('.<%=request.getParameter("genre")%>').addClass("on");
		});
		
	</script>
	
		<h2 class="stit"><img src="/img/design/genre_caller_ring.gif" alt="장르별 통화연결음" /></h2>
		<!-- <h2 class="stit">장르별 통화연결음</h2> -->
		<!-- 검색 영역 -->	
		<jsp:include page="../search/searchForm.jsp"/>
		<br/>
	
	  	<input type="hidden" id="listDiv-currentPage" name="listDiv-currentPage" value="1" />
	  	<input type="hidden" id="selectedGenre" name="selectedGenre" value="<c:out value="${genre }"/>"/>
	  	
	  		<div id="genreCategoryDiv" class="genre_detail_tab" align="center">
				<a href="javascript:rGenreMore.movePage('100')" class="100">발라드</a>
				<a href="javascript:rGenreMore.movePage('101')" class="101">POP</a>
				<a href="javascript:rGenreMore.movePage('102')" class="102">JPOP</a>
				<a href="javascript:rGenreMore.movePage('103')" class="103">댄스, 락</a>
				<a href="javascript:rGenreMore.movePage('104')" class="104">OST</a>
				<a href="javascript:rGenreMore.movePage('105')" class="105">R&amp;B, 힙합</a>
				<a href="javascript:rGenreMore.movePage('106')" class="106">트로트</a>
				<a href="javascript:rGenreMore.movePage('107')" class="107">클래식</a>
				<a href="javascript:rGenreMore.movePage('108')" class="108">JAZZ</a>
				<a href="javascript:rGenreMore.movePage('109')" class="109">어린이</a>
				<a href="javascript:rGenreMore.movePage('110')" class="110">종교</a>
				<a href="javascript:rGenreMore.movePage('111')" class="111">기타</a>
			</div>
			
			<div id="genreSongDiv" class="tb_type">
				<table summary="순위, 곡명, 가수, 앨범, 듣기, 구매 셀로 구성">
					<caption>곡 목록</caption>
					<colgroup>
						<col width="15%" />
						<col width="29%" />
						<col width="20%" />
						<col width="12%" />
						<col width="12%" />
						<col width="12%" />
					</colgroup>
					<thead>
						<tr>
							<th scope="col">순위</th>
							<th scope="col">곡명</th>
							<th scope="col">가수</th>
							<th scope="col">앨범</th>
							<th scope="col">듣기</th>
							<th scope="col">구매</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		<!-- paging 영역 -->
		<div id="listDiv-paging" class="paging">
		</div>	
		<!-- paging 영역 end -->

<%@ include file="/WEB-INF/views/common/footer.jsp"%>