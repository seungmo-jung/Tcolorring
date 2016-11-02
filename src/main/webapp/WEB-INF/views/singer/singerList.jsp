<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/singer/singerList.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/popup/popup.js"></script>
	
	<script type="text/javascript">
		rPopup.service_yn_setting('<%=session.getAttribute("service_yn") %>');
		

	</script>
	
	<script type="text/javascript">
	$(function(){
		$(".tab > li").eq(0).addClass("on").children(".detail_tab").show();
		$(".detail_tab > a:first").addClass("on");
		$(".tab > li > a").click(function(){
			$(this).parent("li").addClass("on").siblings().removeClass("on");
			$(".tab > li").children(".detail_tab").hide();
			$(this).next(".detail_tab").show();
		});
	});
	</script>
		
	<h2 class="stit"><img src="/img/design/singer.gif" alt="가수" /></h2>
			
	<jsp:include page="../search/searchForm.jsp"/>
	
	  	<div class="tab-menu-div">
	  		<ul class="tab song-menu">
  				<li><a class="0" href="#" id="singerSongList">전체곡</a></li>
  				<li><a class="1" href="#" id="singerAlbumList">전체앨범</a></li>
  			</ul>
  		</div>
  		<input type="hidden" id="singerDiv-type" name="singerDiv-type" value="0"/>
  		<input type="hidden" id="singerDiv-value" name="singerDiv-value" value="<c:out value='${singer }'/>"/>
	  	<input type="hidden" id="listDiv-currentPage" name="listDiv-currentPage" value="1" />
	  		<div id="singerSongDiv" class="tb_type">
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
				<!-- paging 영역 -->
				<div id="listDiv-paging0" class="paging"></div>	
				<!-- paging 영역 end -->
			</div>

			<div id="singerAlbumDiv" class="album-div">
				<ul class="album-list"></ul>
				<!-- paging 영역 -->
				<div id="listDiv-paging1" class="paging"></div>	
				<!-- paging 영역 end -->
			</div>
</body>
</html>