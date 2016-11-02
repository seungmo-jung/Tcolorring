
$(function() {
	$(document).on('click', '#singerSongList', rSingerList.getSong);
	$(document).on('click', '#singerAlbumList', rSingerList.getAlbum);
	
	rSingerList.getSong();
});

var rSingerList = {
		getSong : function() {
			$('#singerSongDiv').show();
			$('#singerAlbumDiv').hide();
			
			$('#singerDiv-type').val(0);
			$('#listDiv-currentPage').val(1);
			rSingerList.getMusicList();
		},
		
		getAlbum : function() {
			$('#singerSongDiv').hide();
			$('#singerAlbumDiv').show();
			
			$('#singerDiv-type').val(1);
			$('#listDiv-currentPage').val(1);
			rSingerList.getMusicList();
		},
		
		getMusicList : function() {
			var PerPageUnit = 15;
			
			if($('#singerDiv-type').val() == 0){
				PerPageUnit = 15;
			}else if($('#singerDiv-type').val() == 1){
				PerPageUnit = 12;
			}
			
			var data = {
					type : $('#singerDiv-type').val(),
					singerId : $('#singerDiv-value').val(),
					currentPage : $('#listDiv-currentPage').val(),
					rowPerPage : PerPageUnit
			};
			
			$.ajax({
				url : '/singer/list.do',
				type : 'GET',
				data : data,
		        dataType: "json",
				asysnc : false,
				cache : false,
				success : function(s) {
					rSingerList.listRes(s);
				},
				error : function(error) {
					//console.log(error);
				} 
			}); 
				
		},
		
		listRes : function(result) {
			if($('#singerDiv-type').val() == 0) {
				if(result.pageData.list != null && result.pageData.list != ''){
					$('#singerSongDiv tbody tr').remove();
					
					var cnt = 0;
					if(result.pageData.list != null && result.pageData.list != '') {
						cnt = parseInt(result.pageData.list.length, 10);
					}
					for(var i=0; i<cnt; i++) {
						rSingerList.addListRow(result.pageData.list[i], parseInt(result.pageData.currentPage, 10), i);
					}
					
					$("#listDiv-paging0").jqueryPager({
						divId: "listDiv-paging0",
						pageSize: parseInt(result.pageData.rowPerPage, 10),
						currentPage: parseInt(result.pageData.currentPage, 10),
						pageTotal: parseInt(result.pageData.totalCnt, 10),
						pageBlock: 10
					});
				}else{
					rSingerList.notFoundSingerSong();
				}
				
			} else if($('#singerDiv-type').val() == 1) {
				if(result.pageData.list != null && result.pageData.list != ''){
					$('#singerAlbumDiv ul li').remove();
					
					var cnt = 0;
					if(result.pageData.list != null && result.pageData.list != '') {
						cnt = parseInt(result.pageData.list.length, 10);
					}
					for(var i=0; i<cnt; i++) {
						rSingerList.addAlbumRow(result.pageData.list[i]);
					}
					
					$("#listDiv-paging1").jqueryPager({
						divId: "listDiv-paging1",
						pageSize: parseInt(result.pageData.rowPerPage, 10),
						currentPage: parseInt(result.pageData.currentPage, 10),
						pageTotal: parseInt(result.pageData.totalCnt, 10),
						pageBlock: 10
					});
				}else {
					rSingerList.notFoundSingerAlbum();
				}
				
			}

//			$("#listDiv-paging").jqueryPager({
//				divId: "listDiv-paging",
//				pageSize: parseInt(result.pageData.rowPerPage, 10),
//				currentPage: parseInt(result.pageData.currentPage, 10),
//				pageTotal: parseInt(result.pageData.totalCnt, 10),
//				pageBlock: 10
//			});
		},
		
		notFoundSingerSong : function(){
			$('#singerSongDiv tbody tr').remove();
			
			var innerHtml = '<tr>';
			innerHtml += '<td colspan="6" height="50" class="tac"> 해당 가수의 곡 목록이 존재하지 않습니다.</td>';
			innerHtml += '</tr>';
			
			$('#singerSongDiv tbody').append(innerHtml);
		},
		
		
		notFoundSingerAlbum : function(){
			//$('#singerAlbumDiv').remove();
			$('#notFoundSingerAlbum').remove();
			
			var innerHtml = '<div id="notFoundSingerAlbum" align="center">';
			innerHtml += '<p class="tac">해당 가수의 앨범 목록이 존재하지 않습니다. </p>';
			innerHtml += '</div>';
			
			$('#singerAlbumDiv').append(innerHtml);
		},
		addListRow : function (dataObject, page, cnt) {
			var song_title =  rCommon.replaceWord(dataObject.SONG_TITLE);
			var singer_name = rCommon.replaceWord(dataObject.SINGER_NAME);
			
			var innerHtml = '<tr>';
			innerHtml += '<td class="tac" >'+ ((page-1)*15 + ++cnt)  +'</td>';
			innerHtml += '<td class="tac ellipsis"><nobr><a href="Javascript:rPopup.playerPopup(\''+dataObject.SONG_ID +'\');">'+ song_title +'</a></nobr></td>';
			innerHtml += '<td class="tac ellipsis"><nobr><a href="javascript:rCommon.getSingerList('+dataObject.SINGER_ID+')">'+ singer_name +'</a></nobr></td>';
			if(dataObject.ALBUM_ID != 0 && dataObject.ALBUM_ID != '' && dataObject.ALBUM_ID != null){
				innerHtml += '<td class="tac"><a href="javascript:rCommon.getAlbumInfo('+dataObject.ALBUM_ID+')"><img src="/img/design/ico_album.gif" alt="앨범"></a></td>';
			}else{
				innerHtml += '<td class="tac"><img src="/img/design/ico_non_album.gif" alt="앨범"></td>';
			}
			innerHtml += '<td class="tac"><a href="Javascript:rPopup.playerPopup(\''+dataObject.SONG_ID +'\');"><img src="/img/design/ico_listening.gif" alt="듣기" /></a></td>';
			innerHtml += '<td class="tac"><a href="Javascript:rPopup.purchasePopup(\''+dataObject.SONG_ID +'\');"><img src="/img/design/ico_buy.gif" alt="구매" /></a></td>';
			innerHtml += '</tr>';
			$('#singerSongDiv tbody').append(innerHtml);
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
			
			$('#singerAlbumDiv ul').append(innerHtml);
		}
};

var goPage = function(pageNum) {
	$("#listDiv-currentPage").val(parseInt(pageNum, 10));
	rSingerList.getMusicList();
};
