$(function () {
	rCallerSet.getPurchaseSongList();
});

var rCallerSet = {
		getPurchaseSongList : function(){
			$.ajax({
				url : '/mypage/popup/purchasesong.do',
				type : 'GET',
				asysnc : false,
				cache : false,
				success : function(result){
					console.log(result);
					rCallerSet.addList(result);
				}, error : function(error){
					console.log(error);
				}
			});
		},
		
		addList : function(result){
			$('#songList option').remove();
			var resultArray = result.pageData;
			var songList = '';
			
			if(resultArray != null && resultArray.length > 0){
				songList += '<option value="0">음원을 선택하세요. </option>';
				for(var i=0; i<resultArray.length; i++){
					songList += '<option value="'+resultArray[i].song_id+","+resultArray[i].longplay_yn+","+resultArray[i].song_part+","+resultArray[i].vcode+'">'
					+resultArray[i].song_title +' - '+resultArray[i].singer_name+'  ('+resultArray[i].ph_longplay_yn+'/'+resultArray[i].ph_song_part+')</option>';
				}
			} else {
				songList += '<option value="0">보유한 음원이 없습니다. </option>';
			}
			$('#songList').append(songList);
		},
		
		regSet : function(){
			if(rCallerSet.phoneCheck('caller_mdn', '', 'remove')){
				if($('#songList').val() != 0){
					var data = {
							caller_mdn: $('#caller_mdn').val(),
							song_inf : $('#songList').val()
					};
					
					if(data.caller_mdn != mdn){
						$.ajax({
							url : '/mypage/popup/caller_set.do',
							data : data,
							dataType : 'Json',
							type : 'POST',
							asysnc : false,
							cache : false,
							success : function(data){
								if(data.code == '1000'){
									window.alert(data.msg);
									location.replace('/mypage/popup/caller_list_popup.do');
								}else if(data.code == '0000'){
									alert(data.msg);
								};
							}, error : function(error){
								alert(error);
								console.log(error);
							}
						});
					}else{
						$('#caller_mdn').focus();
						$('#caller_mdn').val('');
						alert("자신의 번호를 설정할 수 없습니다.");
					}
				}else{
					alert("음원을 선택해주세요.");
				}
			}
		},
		
		
		regEdit : function(mdn){
			if(rCallerSet.phoneCheck('caller_mdn', $('#original_caller_mdn').val(), 'remove')){
				if($('#songList').val() != 0){
					var data = {
							caller_mdn: $('#caller_mdn').val(),
							original_caller_mdn: $('#original_caller_mdn').val(),
							song_inf : $('#songList').val()
					};
					
					if(data.caller_mdn != mdn){
						$.ajax({
							url : '/mypage/popup/caller_edit.do',
							data : data,
							dataType : 'Json',
							type : 'POST',
							asysnc : false,
							cache : false,
							success : function(data){
								if(data.code == '1000'){
									alert(data.msg);
									location.replace('/mypage/popup/caller_list_popup.do');
								}else if(data.code == '0000'){
									alert(data.msg);
								}
							}, error  : function(error){
								alert(error);
								console.log(error);
							}
						});
					}else{
						$('#caller_mdn').focus();
						$('#caller_mdn').val(data.original_caller_mdn);
						alert("자신의 번호를 설정할 수 없습니다.");
					}
				}else{
					alert("음원을 선택해주세요.");
				}
			}
		},
		
		onlyNumber : function(event){
			event = event || window.event;
			var keyID = (event.which) ? event.which : event.keyCode;
			if((keyID >= 48 && keyID <=57) || (keyID >= 96 && keyID <= 105) || keyID ==8 || keyID == 46 || keyID == 37 || keyID == 39) return;
			else return false;
		},
		
		removeChar : function(event){
			event = event || window.event;
			var keyID = (event.which) ? event.which : event.keyCode;
			if (keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39) return;
			else event.target.value = event.target.value.replace(/[^0-9]/g, "");
		},
		
		phoneCheck : function(id, content, remove) {
			console.log('폰 넘버 체크 하러옴!');
			var flag = true;
			if (!/^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/.test($('#' + id).val())) {
				alert('전화번호 형식이 어긋납니다. 전화번호를 확인해주세요.\n (c. f. 010-1234-1234)');
				$('#' + id).focus();
				if (remove) $('#' + id).val(content);
				flag = false;
			}
			
			console.log(flag);
			return flag;
		},
		goBack : function(){
			history.back();
		}
};




