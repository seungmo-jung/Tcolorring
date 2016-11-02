<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/songList.css" media="all"/>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/main/songList.js"></script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/popup/popup.js"></script>
  	
	<script type="text/javascript">
		rPopup.service_yn_setting('<%=session.getAttribute("service_yn") %>');
		
		$(function(){
			//탭
			
			$(".tab > li").eq(0).addClass("on").children(".detail_tab").show();
			$(".detail_tab > a:first").addClass("on");
		
			$(".tab > li > a").click(function(){
				$(this).parent("li").addClass("on").siblings().removeClass("on");
				$(".tab > li").children(".detail_tab").hide();
				$(this).next(".detail_tab").show();
			});
		
			$(".detail_tab > a").click(function(){
				$(this).addClass("on").siblings().removeClass("on");
			});
		});
	</script>
  	
  	<input type="hidden" id="selectedType" name="selectedType" value="0">
  	<ul class="tab">
  		<li><a href="#" id="topSongList">인기 통화연결음</a></li>
  		<li><a href="#" id="newSongList">최신 통화연결음</a></li>
  		<li><a href="#" id="genreSongList">장르별 통화연결음</a>
  			<div class="detail_tab">
  				<a href="javascript:rSongList.moveGenre('100')"  class="on">발라드</a>
				<a href="javascript:rSongList.moveGenre('101')" >POP</a>
				<a href="javascript:rSongList.moveGenre('102')" >JPOP</a>
				<a href="javascript:rSongList.moveGenre('103')" >댄스, 락</a>
				<a href="javascript:rSongList.moveGenre('104')" >OST</a>
				<a href="javascript:rSongList.moveGenre('105')" >R&amp;B, 힙합</a>
				<a href="javascript:rSongList.moveGenre('106')" >트로트</a>
				<a href="javascript:rSongList.moveGenre('107')" >클래식</a>
				<a href="javascript:rSongList.moveGenre('108')" >JAZZ</a>
				<a href="javascript:rSongList.moveGenre('109')" >어린이</a>
				<a href="javascript:rSongList.moveGenre('110')" >종교</a>
				<a href="javascript:rSongList.moveGenre('111')" >기타</a>
  			</div>
  		</li>
  	</ul>
  	<div class="tar mt30">
  		<a href="javascript:rSongList.colMore()" class="btn_more">더보기</a>
  	</div>
  	
  	<div class="tb_type" id="topSongDiv">
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
  	
  	 <div class="tb_type" id="newSongDiv">
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
  	
  	<div class="tb_type" id="genreSongDiv">
  		<input type="hidden" id="selectedGenre" value="100"/>
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
