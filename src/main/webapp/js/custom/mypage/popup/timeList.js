$(function () {
	rTimeList.getTimeList();
	rTimeSet.setPage();
});

var rTimeList = {
		getTimeList : function(){
			$.ajax({
				url : '/mypage/popup/time_list.do',
				dataType : 'Json',
				asysnc : false,
				cache : false,
				success : function(result){
					rTimeList.addList(result);
				}, error : function(error){
					//console.log(error);
				}
			});
		},
		
		addList : function(result){
			$('#tList tr').remove();
			var resultArray = result.pageData;
			var timeList = '';
			var song_title =  '';
			var singer_name = '';
			
			if(resultArray != null && resultArray.length >0){
				for(var i=0; i<resultArray.length; i++){
					var list_s_time = resultArray[i].s_time;
					var list_e_time = resultArray[i].e_time;
					if(list_s_time < 10){
						list_s_time = '0'+ list_s_time;
					}
					
					if(list_e_time < 10){
						list_e_time = '0'+list_e_time;
					}
					
					song_title =rCommon.replaceWord(resultArray[i].song_title);
					singer_name = rCommon.replaceWord(resultArray[i].singer_name);
					
					timeList += '<tr>';
					timeList += '<td class="tac">'+ (i+1) +'</td>';
					timeList += '<td class="tac">'+ list_s_time +' : 00 - '+ list_e_time +' : 00</td>';
					timeList += '<td class="tac ellipsis">'+ song_title +' ('+ resultArray[i].longplay_yn +'/'+ resultArray[i].song_part+')</td>';
					timeList += '<td class="tac ellipsis">'+ singer_name +'</td>';
					//timeList += '<td class="tac"><a href="javascript:rTimeList.timeEditPopup(\''+resultArray[i].s_time + '\',\''+ resultArray[i].e_time+'\')"><img src="/img/design/btn_edit.gif" alt="수정" /></a></td>';
					timeList += '<td class="tac"><a href="javascript:rTimeList.deleteConfirm(\''+ resultArray[i].s_time +'\')"><img src="/img/design/btn_del.gif" alt="삭제" /></a></td>';
					timeList += '</tr>';
					if(i == 2){
						//$('#timeSetRegDiv').remove();
						$('#timeButtonDiv').remove();
						$('#timeFullDiv').show();
					}
				}
			}else {
				timeList += '<tr><td colspan="6" height="50" class="tac">설정된 시간대가 없습니다.</td></tr>';
			}
			$('#tList').append(timeList);
		},
		
		deleteConfirm : function(s_time){
			var message = confirm("해당 시간대를 삭제하시겠습니까?");
			if(message == true){
				rTimeList.deleteTime(s_time);
			}
		},
		
		deleteTime : function(s_time){
			var data = {
					s_time : s_time
			};
			$.ajax({
				url : '/mypage/popup/time_delete.do',
				type : 'GET',
				data : data,
				datatype : 'Json',
				success : function(data){
					if(data.code == '1000'){
						alert(data.msg);
						window.location.reload(true);
					}else if(data.code == '0000'){
						alert(data.msg);
					}
				}, error : function(error){
					alert(error);
				}
			});
		},
		
		closePopup : function(){
			window.close();
		},
		
		timeEditPopup : function(s_time, e_time){
			location.href="/mypage/popup/time_edit_popup.do?s_time=" + s_time + "&e_time=" + e_time;
		},
		
		timeAddPopup : function(){
			location.href="/mypage/popup/time_set_popup.do";
		}
};

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
			
			$('#set_s_time').append(hour);
			
			hour = '';
			
			//End hour setting
			for(var i=1; i<25; i++) {
				var time=i;
				if(i<10) {
					time='0'+i;
				}
				hour += '<option value="'+time+'">'+time+' : 00</option>'; 
			}
			
			$('#set_e_time').append(hour);
			
			$.ajax({
				url : '/mypage/popup/purchasesong.do',
				type : 'GET',
				asysnc : false,
				cache : false,
				success : function(result) {
					rTimeSet.addList(result);
				},
				error : function(error) {
					//console.log(error);
				}
			});
		},
		addList : function(result) {
			$('#songList option').remove();
			var songArray = result.pageData;
			var songList = '';
			if(songArray != null & songArray.length > 0) {
				var song_title = '';
				var singer_name = '';
				
				songList += '<option value="0">음원을 선택하세요.</option>';
				for(var i=0; i<songArray.length; i++) {
					song_title =  rCommon.replaceWord(songArray[i].song_title);
					singer_name = rCommon.replaceWord(songArray[i].singer_name);
					
					songList += '<option value="'+songArray[i].song_id+","+songArray[i].longplay_yn+","+songArray[i].song_part+","+songArray[i].vcode+'">'
					+ song_title +' - '+ singer_name +'  ('+songArray[i].ph_longplay_yn+'/'+songArray[i].ph_song_part+')</option>';
				}
			} else {
				songList += '<option value="0">보유한 음원이 없습니다.</option>';
			}
			$('#songList').append(songList);
		},
		
		regSet : function() {
			if($('#songList').val() != 0){
				var data = {
						s_time : $('#set_s_time').val(),
						e_time : $('#set_e_time').val(),
						song_inf : $('#songList').val()
				};
				
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
							//console.log(error);
						} 
					});
				}else{
					alert("시간을 확인해주세요.");
				}
			} else {
				alert("음원을 선택해주세요.");
			}
		},
		/*
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
		*/
		goBack : function(){
			history.back();
		}
};
