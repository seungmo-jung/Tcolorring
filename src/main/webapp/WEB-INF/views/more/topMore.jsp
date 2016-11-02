<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/more/topMore.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/popup/popup.js"></script>
	<script type="text/javascript">
		rPopup.service_yn_setting('<%=session.getAttribute("service_yn") %>');
	</script>
	
		<h2 class="stit"><img src="/img/design/top_caller_ring.gif" alt="인기 통화연결음" /></h2>
		<!-- 검색 영역 -->
		<jsp:include page="../search/searchForm.jsp"/>
		
		<!-- 곡 리스트 영역 -->
		<input type="hidden" id="listDiv-currentPage" name="listDiv-currentPage" value="1" />
		<div id="topSongDiv" class="tb_type">
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