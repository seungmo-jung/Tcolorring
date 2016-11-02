<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/album/albumInfo.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/popup/popup.js"></script>
	<script type="text/javascript">
		rPopup.service_yn_setting('<%=session.getAttribute("service_yn") %>');
	</script>
		<jsp:include page="../search/searchForm.jsp"/>
		
		<h2 class="mt35"><img src="/img/design/tit_info_album.gif" alt="앨범정보" /></h2>
		
		
		<input type="hidden" id="albumDiv-value" name="albumDiv-value" value="<c:out value='${album }'/>"/>
		<!-- 앨범 정보 영역 -->
		<div class="info_album" id="albumInfoDiv" ></div>
		<!-- 앨범 정보 끝 -->
		
		<!-- 수록곡 정보 -->
		<h2 class="mt40"><img src="/img/design/tit_info_song.gif" alt="수록곡 정보" /></h2>
		<div id="albumSongListDiv" class="tb_type">
			<table summary="번호, 곡명, 가수, 앨범, 듣기, 구매 셀로 구성">
				<caption>수록곡 정보</caption>
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
						<th scope="col">번호</th>
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
	
<%@ include file="/WEB-INF/views/common/footer.jsp"%>