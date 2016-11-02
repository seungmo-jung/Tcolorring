$(function() {
	rDefaultSongSet.setPage();
});

var rDefaultSongSet = {
		setPage : function() {
			var url = '/mypage/popup/purchasesong.do';
			$.ajax({
				url : url,
				type : 'GET',
				success : function(s) {
					console.log(s);
					rDefaultSongSet.songRes(s);
				},
				error : function(error) {
					console.log(error);
				}
			});
		},
		
		songRes : function(result) {
			$('#songList option').remove();
			var dataObject = result.pageData;
			var songList = '';
			if(dataObject != null & dataObject.length > 0) {
				songList += '<option value="0">음원을 선택하세요.</option>';
				for(var i=0; i<dataObject.length; i++) {
					songList += '<option value="'+dataObject[i].song_id+","+dataObject[i].longplay_yn+","+dataObject[i].song_part+","+dataObject[i].vcode+'">'
					+dataObject[i].song_title +' - '+dataObject[i].singer_name+'  ('+dataObject[i].ph_longplay_yn+'/'+dataObject[i].ph_song_part+')</option>';
				}
			} else {
				songList += '<option value="0">보유한 음원이 없습니다.</option>';
			}
			$('#songList').append(songList);
		},
		
		regSet : function() {
			var data = {
					song_inf : $('#songList').val(),
			};
			$.ajax({
				url : '/mypage/popup/default_song_set.do',
				data : data,
				dataType : 'Json',
				type : 'POST',
				success : function(data) {
					if(data.code == '1000'){
						alert(data.msg);
					}else if(data.code == '0000'){
						alert(data.msg);
					}
					window.opener.location.reload();
					window.close();
				},
				error : function(error) {
					console.log(error);
				} 
			});
		}
};

