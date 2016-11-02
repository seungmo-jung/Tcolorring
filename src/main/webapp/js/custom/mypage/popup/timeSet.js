$(function() {
	rTimeSet.setPage();
});

var rTimeSet = {
		setPage : function(){
			var hour = '';
			
			//Start hour setting
			for(var i=0; i<24; i++) {
				var time=i;
				if(i<10) {
					time='0'+i;
				}
				hour += '<option value="'+time+'">'+time+' : 00</option>'; 
			}
			
			$('#s_time').append(hour);
			
			hour = '';
			
			//End hour setting
			for(var i=1; i<25; i++) {
				var time=i;
				if(i<10) {
					time='0'+i;
				}
				hour += '<option value="'+time+'">'+time+' : 00</option>'; 
			}
			
			$('#e_time').append(hour);
			
			$.ajax({
				url : '/mypage/popup/purchasesong.do',
				type : 'GET',
				asysnc : false,
				cache : false,
				success : function(result) {
					console.log(result);
					rTimeSet.addList(result);
				},
				error : function(error) {
					console.log(error);
				}
			});
		},
		addList : function(result) {
			$('#songList option').remove();
			var songArray = result.pageData;
			var songList = '';
			if(songArray != null & songArray.length > 0) {
				songList += '<option value="0">음원을 선택하세요.</option>';
				for(var i=0; i<songArray.length; i++) {
					songList += '<option value="'+songArray[i].song_id+","+songArray[i].longplay_yn+","+songArray[i].song_part+","+songArray[i].vcode+'">'
					+songArray[i].song_title +' - '+songArray[i].singer_name+'  ('+songArray[i].ph_longplay_yn+'/'+songArray[i].ph_song_part+')</option>';
				}
			} else {
				songList += '<option value="0">보유한 음원이 없습니다.</option>';
			}
			$('#songList').append(songList);
		},
		
		regSet : function() {
			var data = {
					s_time : $('#s_time').val(),
					e_time : $('#e_time').val(),
					song_inf : $('#songList').val()
			};
			
			console.log(data);
			
			if(data.s_time != data.e_time){
				$.ajax({
					url : '/mypage/popup/time_set.do',
					data : data,
					dataType : 'Json',
					type : 'POST',
					asysnc : false,
					cache : false,
					success : function(result) {
						if(result.code == '1000'){
							alert(result.msg);
							location.replace('/mypage/popup/time_list_popup.do');
						}else if(result.code == '0000'){
							alert(result.msg);
						}
					},
					error : function(error) {
						console.log(error);
					} 
				});
			}else{
				alert("시간을 확인해주세요.");
			}
		},
		
		regEdit : function() {
			var data = {
					s_time : $('#s_time').val(),
					e_time : $('#e_time').val(),
					original_s_time : $('#original_s_time').val(),
					original_e_time : $('#original_e_time').val(),
					song_inf : $('#songList').val()
			};
			
			console.log(data);
			
			if(data.s_time != data.e_time){
				$.ajax({
					url : '/mypage/popup/time_edit.do',
					data : data,
					dataType : 'Json',
					type : 'POST',
					asysnc : false,
					cache : false,
					success : function(result) {
						if(result.code == '1000'){
							alert(result.msg);
							location.replace('/mypage/popup/time_list_popup.do');
						}else if(result.code == '0000'){
							alert(result.msg);
						}
					},
					error : function(error) {
						console.log(error);
					} 
				});
			}else{
				alert("시간을 확인해주세요.");
			}
		},
		goBack : function(){
			history.back();
		}
};
