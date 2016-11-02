$(function() {
	$(document).on('click', '#topSongList', rSongList.topSong);
	$(document).on('click', '#newSongList', rSongList.newSong);
	$(document).on('click', '#genreSongList', rSongList.genreSong);
	
	rSongList.topSong();
});

var rSongList = {
		topSong : function() {
			$('#topSongDiv').show();
			$('#newSongDiv').hide();
			$('#genreSongDiv').hide();
			$('#selectedType').val(0);
			rSongList.getList();
		},
		
		newSong : function() {
			$('#topSongDiv').hide();
			$('#newSongDiv').show();
			$('#genreSongDiv').hide();
			$('#selectedType').val(1);
			rSongList.getList();
		},
		
		genreSong : function() {
			$('#topSongDiv').hide();
			$('#newSongDiv').hide();
			$('#genreSongDiv').show();
			$('#selectedType').val(2);
			rSongList.getList();
		},
		moveGenre : function(genre) {
			$('#selectedGenre').val(genre);
			rSongList.getList();
		},
		
		getList : function(divId, genre) {
			var url = '/main/song.do';
			var data = {
					type : $('#selectedType').val(),
					limit : 10
			};
			if($('#selectedType').val() == 2) {
				data.genre=$('#selectedGenre').val();
			}
			$.ajax({
				url : url,
				type : 'GET',
				data : data,
				asysnc : false,
				cache : false,
		        dataType: "json",
				success : function(s) {
					rSongList.listRes(s);
				},
				error : function(error) {
					//console.log(error);
				} 
			});
		},
		listRes : function(result){
			var id='';
			if($('#selectedType').val() == 0) {
				id = 'topSongDiv';
			} else if($('#selectedType').val() == 1) {
				id = 'newSongDiv';
			} else {
				id = 'genreSongDiv';
			}
			$('#'+id+' tbody tr').remove();
			var cnt = 0;
			if(result.pageData.list != null && result.pageData.list != '') {
				cnt=parseInt(result.pageData.list.length, 10);
			}
			for(var i=0; i<cnt; i++) {
				rSongList.addListRow(result.pageData.list[i], i, id);
			}
		},
		
		addListRow : function (dataObject, cnt, id){
			var song_title =  rCommon.replaceWord(dataObject.SONG_TITLE);
			var singer_name = rCommon.replaceWord(dataObject.SINGER_NAME);
			
			var innerHtml = '<tr>';
			innerHtml += '<td class="tac" >'+ ++cnt +'</td>';
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
			$('#'+id+' tbody').append(innerHtml);
		},
		
		colMore: function() {
			var type = 'top';
			if($('#selectedType').val() == 1) {
				type = 'new';
			} else if($('#selectedType').val() == 2){
				type = 'genre';
			}
			var url = '/more/'+type+'.do';
			if(type == 'genre'){
				url += '?genre='+$('#selectedGenre').val();
			}
			location.href=url;
		}
};
