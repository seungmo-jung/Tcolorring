<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ include file="/WEB-INF/views/common/header.jsp"%> 
  	
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/search/searchList.js"></script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/popup/popup.js"></script>
  	<script type="text/javascript">
		rPopup.service_yn_setting('<%=session.getAttribute("service_yn") %>');
	</script>
  	
  	<script type="text/javascript">
	$(function(){
		//탭
		$(".tab > li").eq(<%=request.getParameter("type")%>).addClass("on").children(".detail_tab").show();
		$(".detail_tab > a").addClass("on");
	
		$(".tab > li > a").click(function(){
			$(this).parent("li").addClass("on").siblings().removeClass("on");
			$(".tab > li").children(".detail_tab").hide();
			$(this).next(".detail_tab").show();
		});
		
		$(".search_sel a").click(function(){
			$(this).parent("li").siblings().toggle();
			$(".search_sel a").hover(function(){
				$(this).parent().addClass('on');
			},function(){
				$(this).parent().removeClass('on');
			});
		});
		
		$(".sort-song-div a").click(function(){
			$(".sort-song-div").find(".on").removeClass('on');
			$(this).addClass('on');
		});
		
		$(".search_sel li").click(function(){
			$(".search_sel").find(".selected").removeClass('selected');
		    $(this).addClass('selected');
		});
	});
	</script>
	  	<div class="search_area">
	  		<div class="search_input">
	  			<ul class="search_sel" id="searchDiv-type">
					<c:set var="type" value="${type}"/>
					<c:if test="${type == 0 }">
						<li class="selected" id="s_song" data-value="0"><a href="#">곡</a></li>
						<li class="" id="s_singer" data-value="1"><a href="#">가수</a></li>
						<li class="" id="s_album" data-value="2"><a href="#">앨범</a></li>
					</c:if>
					<c:if test="${type == 1 }">
						<li class=" selected" id="s_singer"  data-value="1"><a href="#">가수</a></li>
						<li class="" id="s_song" data-value="0"><a href="#">곡</a></li>
						<li class="" id="s_album" data-value="2"><a href="#">앨범</a></li>
					</c:if>
						<c:if test="${type == 2 }">
						<li class="selected" id="s_album"  data-value="2"><a href="#">앨범</a></li>
						<li class="" id="s_song" data-value="0"><a href="#">곡</a></li>
						<li class="" id="s_singer" data-value="1"><a href="#">가수</a></li>
					</c:if>
		  		</ul>
	  			<p class="search_ip"><input type="text" id="searchDiv-value" value="<c:out value='${query }'/>" /></p>
	  		</div>
	  		<a href="javascript:rCommon.search()" class="btn_search"><img src="/img/design/btn_search.gif" alt="검색" /></a>
		</div>
			
	  	<input type="hidden" id="listDiv-currentPage" name="listDiv-currentPage" value="1" />
	  			<div class="tab-menu-div">
			  		<ul class="tab song-menu">
  						<li><a class="0" href="#" id="searchSongList">곡</a></li>
  						<li><a class="1" href="#" id="searchSingerList">가수</a></li>
  						<li><a class="2" href="#" id="searchAlbumList">앨범</a></li>
  					</ul>
		  		</div>
		  		<div id="searchSongDiv">
		  			<hr class="horizon">
		  			<input type="hidden" id="searchSongDiv-sort" name="searchSongDiv-sort" value="0" />
		  			<span id="searchResultSong"></span>
		  			<div id="songSortDiv" class="sort-song-div" align="right">
						<a href="javascript:rSearchList.sortType('0')" id="DateSort" class="on">최신순</a>
						<span>|</span>
						<a href="javascript:rSearchList.sortType('1')" id="charSort">제목순</a>
					</div>
				<div class="tb_type" >
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
				<div id="listDiv-paging0" class="paging"></div>	
					<!-- paging 영역 end -->
			</div>
				
				<div id="searchSingerDiv">
					<span id="searchResultSinger"></span>
					<ul class="singer-list">
					</ul>
					<!-- paging 영역 -->
					<div id="listDiv-paging1" class="paging">
					</div>	
					<!-- paging 영역 end -->
				</div>
				
				<div id="searchAlbumCounter">
					<span id="searchResultAlbum"></span>
				</div>
				
				<div id="searchAlbumDiv" class="album-div">
					<div class="search-album-div">
						<ul class="album-list">
						</ul>
						<!-- paging 영역 -->
						<div id="listDiv-paging2" class="paging">
						</div>	
						<!-- paging 영역 end -->
					</div>
				</div>
				
				<div id="searchNotFoundDiv">
					<br/>
					<div class="search-nf-div">
						<h4><em id="searchQuery"></em>
						(으)로 검색한 결과가 없습니다.</h4>
					</div>
					<div class="search-help-div">
						<p>
						검색어가 바르게 입력되어 있는지 확인해 보세요.<br>
						보다 일반적인 검색어 등 다른 검색어로 검색해 보세요.
						</p>
					</div>
				</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>