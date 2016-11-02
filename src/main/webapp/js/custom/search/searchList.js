$(function() {
	if($('#searchDiv-type .selected').data("value") == 0) {
		$('#searchSongDiv').show();
		$('#searchSingerDiv').hide();
		$('#searchAlbumDiv').hide();
		$('#searchAlbumCounter').hide();
		$('#searchNotFoundDiv').hide();
		
		
	} else if($('#searchDiv-type .selected').data("value") == 1) {
		$('#searchSongDiv').hide();
		$('#searchSingerDiv').show();
		$('#searchAlbumDiv').hide();
		$('#searchAlbumCounter').hide();
		$('#searchNotFoundDiv').hide();
		
	} else if($('#searchDiv-type .selected').data("value")== 2) {
		$('#searchSongDiv').hide();
		$('#searchSingerDiv').hide();
		$('#searchAlbumDiv').show();
		$('#searchAlbumCounter').show();
		$('#searchNotFoundDiv').hide();
	}
	
	$(document).on('click', '#searchSongList', rSearchList.SongList);
	$(document).on('click', '#searchSingerList', rSearchList.SingerList);
	$(document).on('click', '#searchAlbumList', rSearchList.AlbumList);
	
	rSearchList.getMusicList();
});
//$('#searchDiv-type .selected').data("value")
var rSearchList = {
		SongList : function() {
			$("#listDiv-currentPage").val(1);
			
			$('#searchSongDiv').show();
			$('#searchSingerDiv').hide();
			$('#searchAlbumDiv').hide();
			$('#searchAlbumCounter').hide();
			$('#searchNotFoundDiv').hide();
			
			$(".search_sel").find(".selected").removeClass('selected');
			$("#s_song").addClass('selected');
			
			rSearchList.getMusicList();
		},
		sortType : function(type) {
			$('#searchSongDiv-sort').val(type);
			$("#listDiv-currentPage").val(1);
			rSearchList.getMusicList();
		},
		
		SingerList : function() {
			$("#listDiv-currentPage").val(1);
			
			$('#searchSongDiv').hide();
			$('#searchSingerDiv').show();
			$('#searchAlbumDiv').hide();
			$('#searchAlbumCounter').hide();
			$('#searchNotFoundDiv').hide();
			
			$(".search_sel").find(".selected").removeClass('selected');
			$("#s_singer").addClass('selected');
			rSearchList.getMusicList();
		},
		
		AlbumList : function() {
			$("#listDiv-currentPage").val(1);
			
			$('#searchSongDiv').hide();
			$('#searchSingerDiv').hide();
			$('#searchAlbumDiv').show();
			$('#searchAlbumCounter').show();
			$('#searchNotFoundDiv').hide();
			
			$(".search_sel").find(".selected").removeClass('selected');
			$("#s_album").addClass('selected');
			rSearchList.getMusicList();
		},
		
		getMusicList : function() {
			var PerPageUnit = 15;
			
			if($('#searchDiv-type .selected').data("value") == 0){
				PerPageUnit = 15;
			}else if($('#searchDiv-type .selected').data("value") == 1) {
				PerPageUnit = 20;
			}else if($('#searchDiv-type .selected').data("value") == 2) {
				PerPageUnit = 12;
			}
			
			var data = {
					type : $('#searchDiv-type .selected').data("value"),
					query : $('#searchDiv-value').val(),
					sortType : $('#searchSongDiv-sort').val(),
					currentPage : $('#listDiv-currentPage').val(),
					rowPerPage : PerPageUnit
			};
			$.ajax({
				url : '/search/list.do',
				type : 'GET',
				data : data,
		        dataType: "json",
				async : false,
				cache : false,
				success : function(s) {
					rSearchList.listRes(s);
				},
				error : function(error) {
					//console.log(error);
				} 
			}); 
				
		},
		
		listRes : function(result) {
			if($('#searchDiv-type .selected').data("value") == 0) {
				$('#searchResultSong').html('<h3>곡('+result.pageData.totalCnt+')</h3>');
				$('#searchSongDiv tbody tr').remove();
				
				var cnt = 0;
				if(result.pageData.list != null && result.pageData.list != '') {
					cnt = parseInt(result.pageData.list.length, 10);
				} else {
					rSearchList.notFound();
				}
				for(var i=0; i<cnt; i++) {
					rSearchList.addListRow(result.pageData.list[i], parseInt(result.pageData.currentPage, 10), i);
				}
				$("#listDiv-paging0").jqueryPager({
					divId: "listDiv-paging0",
					pageSize: parseInt(result.pageData.rowPerPage, 10),
					currentPage: parseInt(result.pageData.currentPage, 10),
					pageTotal: parseInt(result.pageData.totalCnt, 10),
					pageBlock: 10
				});
			} else if($('#searchDiv-type .selected').data("value") == 1) {
				$('#searchResultSinger').html('<h3>가수('+result.pageData.totalCnt+')</h3>');
				$('#searchSingerDiv ul li').remove();
				var cnt = 0;
				if(result.pageData.list != null && result.pageData.list != '') {
					cnt = parseInt(result.pageData.list.length, 10);
				} else {
					rSearchList.notFound();
				}
				for(var i=0; i<cnt; i++) {
					rSearchList.addSingerRow(result.pageData.list[i]);
				}
				$("#listDiv-paging1").jqueryPager({
					divId: "listDiv-paging1",
					pageSize: parseInt(result.pageData.rowPerPage, 10),
					currentPage: parseInt(result.pageData.currentPage, 10),
					pageTotal: parseInt(result.pageData.totalCnt, 10),
					pageBlock: 10
				});
			} else if($('#searchDiv-type .selected').data("value") == 2) {
				$('#searchResultAlbum').html('<h3>앨범('+result.pageData.totalCnt+')</h3>');
				$('#searchAlbumDiv ul li').remove();
				var cnt = 0;
				if(result.pageData.list != null && result.pageData.list != '') {
					cnt = parseInt(result.pageData.list.length, 10);
				} else {
					rSearchList.notFound();
				}
				for(var i=0; i<cnt; i++) {
					rSearchList.addAlbumRow(result.pageData.list[i]);
				}
				$("#listDiv-paging2").jqueryPager({
					divId: "listDiv-paging2",
					pageSize: parseInt(result.pageData.rowPerPage, 10),
					currentPage: parseInt(result.pageData.currentPage, 10),
					pageTotal: parseInt(result.pageData.totalCnt, 10),
					pageBlock: 10
				});
			}

//			$("#listDiv-paging").jqueryPager({
//				divId: "listDiv-paging",
//				pageSize: parseInt(result.pageData.rowPerPage, 10),
//				currentPage: parseInt(result.pageData.currentPage, 10),
//				pageTotal: parseInt(result.pageData.totalCnt, 10),
//				pageBlock: 10
//			});
		},
		
		notFound : function() {
			$('#searchSongDiv').hide();
			$('#searchSingerDiv').hide();
			$('#searchAlbumDiv').hide();
			$('#searchAlbumCounter').hide();
			$('#searchNotFoundDiv').show();
			$('#searchQuery').html(rCommon.replaceWord($('#searchDiv-value').val()));
		},
		
		addListRow : function (dataObject, page, cnt) {
			var song_title =  rCommon.replaceWord(dataObject.SONG_TITLE);
			var singer_name = rCommon.replaceWord(dataObject.SINGER_NAME);
			
			var innerHtml = '<tr>';
			innerHtml += '<td class="tac" >'+ ((page-1)*15 + ++cnt)  +'</td>';
			innerHtml += '<td class="tac ellipsis"><nobr><a href="Javascript:rPopup.playerPopup(\''+dataObject.SONG_ID +'\');">'+ song_title +'</a></nobr></td>';
			innerHtml += '<td class="tac ellipsis"><nobr><a href="javascript:rCommon.getSingerList('+dataObject.SINGER_ID+')">'+ singer_name +'</a></nobr></td>';
			if(dataObject.ALBUM_ID != '' && dataObject.ALBUM_ID != null){
				innerHtml += '<td class="tac"><a href="javascript:rCommon.getAlbumInfo('+dataObject.ALBUM_ID+')"><img src="/img/design/ico_album.gif" alt="앨범"></a></td>';
			}else{
				innerHtml += '<td class="tac"><img src="/img/design/ico_non_album.gif" alt="앨범"></td>';
			}
			innerHtml += '<td class="tac"><a href="Javascript:rPopup.playerPopup(\''+dataObject.SONG_ID +'\');"><img src="/img/design/ico_listening.gif" alt="듣기" /></a></td>';
			innerHtml += '<td class="tac"><a href="Javascript:rPopup.purchasePopup(\''+dataObject.SONG_ID +'\');"><img src="/img/design/ico_buy.gif" alt="구매" /></a></td>';
			innerHtml += '</tr>';
			$('#searchSongDiv tbody').append(innerHtml);
		},
		
		addSingerRow : function (dataObject) {
			var singer_name = rCommon.replaceWord(dataObject.SINGER_NAME);
			
			var innerHtml = '<li><a href="javascript:rCommon.getSingerList('+dataObject.SINGER_ID+')"> <strong>- </strong>'+ singer_name +'</a></li>';
			$('#searchSingerDiv ul').append(innerHtml);
		},
		
		addAlbumRow : function (dataObject) {
			var album_name =  rCommon.replaceWord(dataObject.ALBUM_NAME);
			var singer_name = rCommon.replaceWord(dataObject.SINGER_NAME);
			
			var innerHtml = '<li>';
			if(dataObject.IMG_YN == true){
				innerHtml += '<a href="javascript:rCommon.getAlbumInfo('+dataObject.ALBUM_ID+')"><img src="/crbt/img/album'+rCommon.editImagePath(dataObject.ALBUM_ID)+'" onError="this.src=\'/img/album/no_album_img.PNG\'" class="album-img"></a>';
			}else {
				innerHtml += '<a href="javascript:rCommon.getAlbumInfo('+dataObject.ALBUM_ID+')"><img src="/img/album/no_album_img.PNG" class="album-img"></a>';
			}
			innerHtml += '<dl class="album-info">';
			innerHtml += '<dt class="ellipsis"><strong><a href="javascript:rCommon.getAlbumInfo('+dataObject.ALBUM_ID+')">' + album_name +'</a></strong></dt>';
			innerHtml += '<dd class="ellipsis"><a href="javascript:rCommon.getSingerList('+dataObject.SINGER_ID+')">' + singer_name+'</a></dd>';
			if(dataObject.R_DATE != null){
				innerHtml += '<dd>'+dataObject.R_DATE+'</dd>';
			}else{
				innerHtml += '<dd> - </dd>';
			}
			innerHtml += '<dd><span>　</span></dd>';
			innerHtml += '<dd><a href="javascript:rCommon.getAlbumInfo('+dataObject.ALBUM_ID+')"><img src="/img/design/album_btn.gif" alt="앨범보기" /></a></dd>';
			innerHtml += '</dl>';
			innerHtml += '</li>';
			$('#searchAlbumDiv ul').append(innerHtml);
		}
};

var goPage = function(pageNum) {
	$("#listDiv-currentPage").val(parseInt(pageNum, 10));
	rSearchList.getMusicList();
};