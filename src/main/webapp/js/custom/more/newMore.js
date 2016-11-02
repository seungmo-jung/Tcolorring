
$(function() {
	
	rNewMore.getMusicList();
});

var rNewMore = {
		getMusicList : function() {
			var data = {
					type : 1,
					currentPage : $("#listDiv-currentPage").val(),
					rowPerPage : 15
			};
			
			$.ajax({
				url : '/more/song_data.do',
				type : 'GET',
				data : data,
				asysnc : false,
				cache : false,
		        dataType: "json",
				success : function(s) {
					rNewMore.listRes(s);
				},
				error : function(error) {
					//console.log(error);
				} 
			}); 
				
		},
		
		listRes : function(result)  {
			$('#newSongDiv tbody tr').remove();
			
			var cnt = 0;
			if(result.pageData.list != null && result.pageData.list != '') {
				cnt =parseInt(result.pageData.list.length, 10);
			}
			for(var i=0; i<cnt; i++) {
				rNewMore.addListRow(result.pageData.list[i], parseInt(result.pageData.currentPage, 10), i);
			}

			$("#listDiv-paging").jqueryPager({
				divId: "listDiv-paging",
				pageSize: parseInt(result.pageData.rowPerPage, 10),
				currentPage: parseInt(result.pageData.currentPage, 10),
				pageTotal: parseInt(result.pageData.totalCnt, 10),
				pageBlock: 10
			});
		},
		
		addListRow : function (dataObject, page, cnt) {
			var song_title =  rCommon.replaceWord(dataObject.SONG_TITLE);
			var singer_name = rCommon.replaceWord(dataObject.SINGER_NAME);
			
			var innerHtml = '<tr>';
			innerHtml += '<td class="tac" >'+ ((page-1)*15 + ++cnt) +'</td>';
			innerHtml += '<td class="tac ellipsis"><nobr><a href="Javascript:rPopup.playerPopup(\''+dataObject.SONG_ID +'\');">'+ song_title +'</a></nobr></td>';
			innerHtml += '<td class="tac ellipsis"><nobr><a href="javascript:rCommon.getSingerList('+dataObject.SINGER_ID+')">'+ singer_name+'</a></nobr></td>';
			if(dataObject.ALBUM_ID != 0 && dataObject.ALBUM_ID != '' && dataObject.ALBUM_ID != null){
				innerHtml += '<td class="tac"><a href="javascript:rCommon.getAlbumInfo('+dataObject.ALBUM_ID+')"><img src="/img/design/ico_album.gif" alt="앨범"></a></td>';
			}else{
				innerHtml += '<td class="tac"><img src="/img/design/ico_non_album.gif" alt="앨범"></td>';
			}
			innerHtml += '<td class="tac"><a href="Javascript:rPopup.playerPopup(\''+dataObject.SONG_ID +'\');"><img src="/img/design/ico_listening.gif" alt="듣기" /></a></td>';
			innerHtml += '<td class="tac"><a href="Javascript:rPopup.purchasePopup(\''+dataObject.SONG_ID +'\');"><img src="/img/design/ico_buy.gif" alt="구매" /></a></td>';
			innerHtml += '</tr>';
			$('#newSongDiv tbody').append(innerHtml);
		}
};

var goPage = function(pageNum) {
	$("#listDiv-currentPage").val(parseInt(pageNum, 10));
	rNewMore.getMusicList();
};

