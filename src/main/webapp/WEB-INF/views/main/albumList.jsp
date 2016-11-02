<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/album.css" media="all"/>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/main/albumList.js"></script>
  	
  	<div class="lastest_album">
  		<div class="inner">
  			<h2><img src="/img/design/tit_album.gif" alt="최신앨범" /></h2>
  			<a href="javascript:rAlbumList.colMore()" class="btn_more">더보기</a>
  			<ul class="list" id="newAlbumList">
  			</ul>
  		</div>
  	</div>
  	
  	
  	<!--  
		<div align="center" class="new-album-div" >
			<h3 class="new-album-title">최신앨범 </h3>
			<a href="javascript:rAlbumList.colMore()" class="more">더보기</a>
			<ul id="newAlbumList" class="new-album-list">
			</ul>
		</div>
-->