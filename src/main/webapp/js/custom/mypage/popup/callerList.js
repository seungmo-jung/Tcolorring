$(function () {
	rCallerList.getCallerList();
	rCallerSet.getPurchaseSongList();
});

var rCallerList = {
		getCallerList : function(){
			$.ajax({
				url : '/mypage/popup/caller_list.do',
				type : 'GET',
				asysnc : false,
				cache : false,
				dataType : 'Json',
				success : function(result){
					rCallerList.addList(result);
				}, error : function(error){
					//console.log(error);
				}
			});
		},
		
		addList : function(result){
			$('#tList tr').remove();
			var resultArray = result.pageData;
			var callerList = '';
			
			if(resultArray != null && resultArray.length >0){
				var song_title = '';
				var singer_name = '';
				for(var i=0; i<resultArray.length; i++){
					song_title =  rCommon.replaceWord(resultArray[i].song_title);
					singer_name = rCommon.replaceWord(resultArray[i].singer_name);
					
					callerList += '<tr>';
					callerList += '<td class="tac">'+ (i+1) +'</td>';
					callerList += '<td class="tac">'+ rCommon.appendHyphen(resultArray[i].caller_mdn) +'</td>';
					callerList += '<td class="tac ellipsis">'+ song_title +' ('+ resultArray[i].longplay_yn +'/'+ resultArray[i].song_part+')</td>';
					callerList += '<td class="tac ellipsis">'+ singer_name+'</td>';
					//callerList += '<td class="tac"><a href="javascript:rCallerList.callerEditPopup(\''+ resultArray[i].caller_mdn +'\')"><img src="/img/design/btn_edit.gif" alt="수정" ></a></td>';
					callerList += '<td class="tac"><a href="javascript:rCallerList.deleteConfirm(\''+ resultArray[i].caller_mdn +'\')"><img src="/img/design/btn_del.gif" alt="삭제" /></a></td>';
					callerList += '</tr>';
					
					if(i == 4){
						//$('#callerSetRegDiv').remove();
						$('#callerButtonDiv').remove();
						$('#callerFullDiv').show();
					}
				}
			}else {
				callerList += '<tr><td colspan="6" height="50" class="tac">설정된 발신자가 없습니다.</td></tr>';
			}
			$('#tList').append(callerList);
		},
		
		deleteConfirm : function(caller_mdn){
			var message = confirm(caller_mdn+" 번호를 삭제하시겠습니까?");
			if(message == true){
				rCallerList.deleteCaller(caller_mdn);
			}
		},
		
		deleteCaller : function(caller_mdn){
			var data = {
					caller_mdn : caller_mdn
			};
			
			$.ajax({
				url : '/mypage/popup/caller_delete.do',
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
		callerAddPopup : function(){
			location.href="/mypage/popup/caller_set_popup.do";
		},
		
		callerEditPopup : function(caller_mdn){
			location.href="/mypage/popup/caller_edit_popup.do?caller_mdn=" + caller_mdn;
		},
		closePopup : function(){
			window.close();
		}
};

var rCallerSet = {
		getPurchaseSongList : function(){
			$.ajax({
				url : '/mypage/popup/purchasesong.do',
				type : 'GET',
				asysnc : false,
				cache : false,
				success : function(result){
					rCallerSet.addList(result);
				}, error : function(error){
					//console.log(error);
				}
			});
		},
		
		addList : function(result){
			$('#songList option').remove();
			var resultArray = result.pageData;
			var songList = '';
			
			if(resultArray != null && resultArray.length > 0){
				var song_title =  '';
				var singer_name = '';
				songList += '<option value="0">음원을 선택하세요. </option>';
				for(var i=0; i<resultArray.length; i++){
					song_title = rCommon.replaceWord(resultArray[i].song_title);
					singer_name = rCommon.replaceWord(resultArray[i].singer_name);
					songList += '<option value="'+resultArray[i].song_id+","+resultArray[i].longplay_yn+","+resultArray[i].song_part+","+resultArray[i].vcode+'">'
					+ song_title +' - '+ singer_name+'  ('+resultArray[i].ph_longplay_yn+'/'+resultArray[i].ph_song_part+')</option>';
				}
			} else {
				songList += '<option value="0">보유한 음원이 없습니다. </option>';
			}
			$('#songList').append(songList);
		},
		
		regSet : function(mdn){
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
									window.location.reload(true);
								}else if(data.code == '0000'){
									alert(data.msg);
								};
							}, error : function(error){
								alert(error);
								//console.log(error);
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
		
		/*
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
		*/
		
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
			var flag = true;
			if($('#' + id).val().length >= 10){
				if (!/^0([1|7]?)([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/.test($('#' + id).val())) {
					alert('전화번호 형식이 어긋납니다. 전화번호를 확인해주세요.\n (c. f. 010-1234-1234)');
					$('#' + id).focus();
					if (remove) $('#' + id).val(content);
					flag = false;
				}
			} else{
				alert('전화번호 형식이 어긋납니다. 전화번호를 확인해주세요.\n (c. f. 010-1234-1234)');
				$('#' + id).focus();
				if (remove) $('#' + id).val(content);
				flag = false;
			}

			return flag;
		},
		goBack : function(){
			history.back();
		}
};