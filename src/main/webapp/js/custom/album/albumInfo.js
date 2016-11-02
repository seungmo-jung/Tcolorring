
$(function() {
	rAlbumInfo.getAlbum();
});

var rAlbumInfo = {
		getAlbum : function() {
			var data = {
				albumId : $('#albumDiv-value').val()
			};
			
			$.ajax({
				url : '/album/info.do',
				type : 'GET',
				data : data,
				asysnc : false,
				cache : false,
		        dataType: "json",
				success : function(s) {
					rAlbumInfo.listRes(s);
				},
				error : function(error) {
					//console.log(error);
				} 
			}); 
				
		},
		
		listRes : function(result) {
			$('#albumInfoDiv div').remove();
			var album_name =  rCommon.replaceWord(result.pageData.album.ALBUM_NAME);
			var singer_name = rCommon.replaceWord(result.pageData.album.SINGER_NAME);

			var innerHtml = '<div>';
			if(result.pageData.album.IMG_YN == true){
				innerHtml += '<p class="img_album"><img src="/crbt/img/album'+rCommon.editImagePath(result.pageData.album.ALBUM_ID)+'" onError="this.src=\'/img/album/no_album_img.PNG\'" alt="" /></p>';
			}else{
				innerHtml += '<p class="img_album"><img src="/img/album/no_album_img.PNG" alt="" /></p>';
			}
			innerHtml += '<dl class="info_detail">';
			innerHtml += '<dt>' + album_name +'</dt>';
			innerHtml += '<dd><span>아티스트</span>' + singer_name +'</dd>';
			if(result.pageData.album.R_DATE != null){
				innerHtml += '<dd><span>발매일</span>'+result.pageData.album.R_DATE+'</dd>';
			}else{
				innerHtml += '<dd><span>발매일</span> - </dd>';
			}
			
			innerHtml += '<dd><span></span></dd>';
			innerHtml += '<dd><span></span></dd>';
			innerHtml += '<dd><span></span></dd>';
			//innerHtml += '<dd>'+result.pageData.album.ALBUM_DESC+'</dd>';
			innerHtml += '</div>';
			$('#albumInfoDiv').append(innerHtml);
			
			$('#albumSongListDiv tbody tr').remove();
			var cnt = 0;
			if(result.pageData.list != null && result.pageData.list != '') {
				cnt = parseInt(result.pageData.list.length, 10);
				
				for(var i=0; i<cnt; i++) {
					rAlbumInfo.addListRow(result.pageData.list[i], i);
				}
			}else{
				rAlbumInfo.notFoundAlbumSong();
			}
			
		},
		
		notFoundAlbumSong : function(){
			$('#albumSongListDiv tbody tr').remove();
			
			var innerHtml = '<tr>';
			innerHtml += '<td colspan="6" height="50" class="tac"> 해당 앨범의 곡 목록이 존재하지 않습니다.</td>';
			innerHtml += '</tr>';
			
			$('#albumSongListDiv tbody').append(innerHtml);
		},
		
		addListRow : function (dataObject, cnt) {
			var song_title =  rCommon.replaceWord(dataObject.SONG_TITLE);
			var singer_name = rCommon.replaceWord(dataObject.SINGER_NAME);
			
			var innerHtml = '<tr>';
			innerHtml += '<td class="tac" >'+ ++cnt +'</td>';
			innerHtml += '<td class="tac ellipsis"><nobr><a href="Javascript:rPopup.playerPopup(\''+dataObject.SONG_ID +'\');">'+ song_title +'</a></nobr></td>';
			innerHtml += '<td class="tac ellipsis"><nobr><a href="javascript:rCommon.getSingerList('+dataObject.SINGER_ID+')">'+ singer_name +'</a></nobr></td>';
			innerHtml += '<td class="tac"><a href="javascript:rCommon.getAlbumInfo('+dataObject.ALBUM_ID+')"><img src="/img/design/ico_album.gif" alt="앨범"></a></td>';
			innerHtml += '<td class="tac"><a href="Javascript:rPopup.playerPopup(\''+dataObject.SONG_ID +'\');"><img src="/img/design/ico_listening.gif" alt="듣기" /></a></td>';
			innerHtml += '<td class="tac"><a href="Javascript:rPopup.purchasePopup(\''+dataObject.SONG_ID +'\');"><img src="/img/design/ico_buy.gif" alt="구매" /></a></td>';
			innerHtml += '</tr>';
			$('#albumSongListDiv tbody').append(innerHtml);
		}
};
