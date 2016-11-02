$(function() {
	rMypage.getPurchaseHist();
});

var rMypage = {
		getPurchaseHist : function(){
			var url = '/mypage/purchasehist.do';
			var data = {
					currentPage : $("#listDiv-currentPage").val(),
					rowPerPage : 5
			};
			$.ajax({
				url : url,
				dataType : 'Json',
				type : 'GET',
				data : data,
				asysnc : false,
				cache : false,
				success : function(s){
					rMypage.listPurHist(s);
				},
				error : function(error){
					//console.log(error);
				}
			});
		},
		
		listPurHist : function(result){
			$('#pur_hist tbody tr').remove();
			var cnt = 0;
			if(result.pageData.list != null && result.pageData.list != ''){
				$('#notFoundPurchaseHist').hide();
				cnt = parseInt(result.pageData.list.length, 10);
				for(var i=0; i<cnt; i++){
					rMypage.addPurchaseHist(result.pageData.list[i]);
				}
				
				$("#listDiv-paging").jqueryPager({
					divId: "listDiv-paging",
					pageSize: parseInt(result.pageData.rowPerPage, 10),
					currentPage: parseInt(result.pageData.currentPage, 10),
					pageTotal: parseInt(result.pageData.totalCnt, 10),
					pageBlock: 5
				});
			}else{
				rMypage.notFoundPurchaseHist();
			} 
			
		},
		
		addPurchaseHist : function(dataObject){
			var song_title =  rCommon.replaceWord(dataObject.song_title);
			var singer_name = rCommon.replaceWord(dataObject.singer_name);
			
			var innerHtml = '<tr>';
			if(dataObject.br_vcode != null && dataObject.br_vcode != ''){
				//innerHtml += '<td class="tac"><input type="radio" checked="checked" onclick="Javascript:rMypage.regDefaultSongSet(\'' + dataObject.vcode + '\', \'' + dataObject.song_id+ '\', \'' + dataObject.longplay_yn+ '\', \'' + dataObject.song_part+ '\', \'' + dataObject.song_title+ '\', \'' + dataObject.c_longplay_yn+ '\', \'' + dataObject.c_song_part+ '\');" /></td>';
				innerHtml += '<td class="tac"><input type="radio" checked="checked" onclick="Javascript:rMypage.regDefaultSongSet(\'' + dataObject.vcode + '\', \'' + dataObject.song_id+ '\', \'' + dataObject.longplay_yn+ '\', \'' + dataObject.song_part+ '\');" /></td>';
			}else{
				//innerHtml +=  '<td class="tac"><input type="radio" onclick="Javascript:rMypage.regDefaultSongSet(\'' + dataObject.vcode + '\', \'' + dataObject.song_id+ '\', \'' + dataObject.longplay_yn+ '\', \'' + dataObject.song_part+ '\', \'' + dataObject.song_title+ '\', \'' + dataObject.c_longplay_yn+ '\', \'' + dataObject.c_song_part+ '\');" /></td>';
				innerHtml +=  '<td class="tac"><input type="radio" onclick="Javascript:rMypage.regDefaultSongSet(\'' + dataObject.vcode + '\', \'' + dataObject.song_id+ '\', \'' + dataObject.longplay_yn+ '\', \'' + dataObject.song_part+ '\');" /></td>';
			}
			innerHtml += '<td class="ellipsis"><b>' + song_title + '</b>  <font size="2">('+ dataObject.c_longplay_yn+'/'+dataObject.c_song_part+')</font></td>';
			innerHtml += '<td class="tac"><a href="Javascript:rMypage.ListenPlayerPopup(\'' + dataObject.song_id+ '\', \'' + dataObject.song_part+ '\',  \'' + dataObject.c_longplay_yn+ '\', \'' + dataObject.c_song_part+ '\');"><img src="/img/design/ico_listening.gif" alt="듣기" /></a></td>';
			innerHtml += '<td class="tac ellipsis">' + singer_name + '</td>';
			innerHtml += '<td class="tac">' + comma(parseInt(dataObject.price*1.1)) + '원</td>';
			innerHtml += '<td class="tac">' + dataObject.p_date + '</td>';
			if(dataObject.br_vcode != null && dataObject.br_vcode != ''){
				innerHtml += '<td class="tac"><span class="txt_pink">이용중</span>'
					+'<a href="Javascript:rMypage.DefaultSongDel();"><img src="/img/design/btn_del.gif" alt="삭제" /></a></td>';
			}else{
				innerHtml += '<td class="tac"></td>';
			}
			innerHtml += '</tr>';
			
			$('#pur_hist tbody').append(innerHtml);
		},
		
		notFoundPurchaseHist : function(){
			$('#pur_hist tbody tr').remove();
			
			var innerHtml = '<tr>';
			innerHtml += '<td colspan="7" height="50" class="tac">구매내역이 존재하지 않습니다.</td>';
			innerHtml += '</tr>';
			
			$('#pur_hist tbody').append(innerHtml);
		},
		
		regDefaultSongSet : function(vcode, song_id, longplay_yn, song_part){
			//var message = confirm(song_title + "("+c_longplay_yn + "/"+ c_song_part +")를 \n통화연결음으로 설정하시겠습니까?");
			var message = confirm("해당곡을 통화연결음으로 설정하시겠습니까?");
			if(message == true){
				var data = {
						vcode : vcode,
						song_id : song_id,
						longplay_yn : longplay_yn,
						song_part : song_part
				};
				$.ajax({
					url : '/mypage/default_song_set.do',
					data : data,
					asysnc : false,
					cache : false,
					dataType : 'Json',
					type : 'GET',
					success : function(data){
						if(data.code == '1000'){
							alert(data.msg);
							location.reload(true);
						}else if(data.code == '0000'){
							alert(data.msg);
							location.reload(true);
						}
					},
					error : function(error) {
						alert("설정에 실패하셨습니다.");
						location.reload(true);
					} 
				});
			}else{
				location.reload(true);
			}
		},
		
		DefaultSongDel : function(){
			var message = confirm("통화연결음을 삭제하시겠습니까? \n삭제 시 기본음원으로 변경됩니다.");
			if(message == true){
				$.ajax({
					url : '/mypage/default_song_del.do',
					dataType : 'Json',
					success : function(data){
						if(data.code == '1000'){
							alert(data.msg);
							location.reload(true);
						}else if(data.code == '0000'){
							alert(data.msg);
							location.reload(true);
						}
					},
					error : function(error){
						alert("기본음원 변경에 실패하셨습니다.");
						location.reload(true);
					}
				});
			}
		},
		callerSetPopup : function(){
			var width = "455";
			var height = "455";
			
			LeftPosition=(screen.width)?(screen.width-width)/2:100;
		    TopPosition=(screen.height)?(screen.height-height)/2:100;
		    
		    url = encodeURI("/mypage/popup/caller_list_popup.do");
		    
		  	winOpts="scrollbars=yes,toolbar=no,location=no,directories=no,width="+width+",height="+height+",resizable=no,mebar=no,left="+LeftPosition+",top="+TopPosition;
		  	window.document.body.scroll = "auto";
		  	window.open(url,'popup', winOpts);
		},
		timeSetPopup : function(){
			var width = "600";
			var height = "280";
			
			LeftPosition=(screen.width)?(screen.width-width)/2:100;
		    TopPosition=(screen.height)?(screen.height-height)/2:100;
		    
		    url = encodeURI("/mypage/popup/time_list_popup.do");
		    
		  	winOpts="scrollbars=yes,toolbar=no,location=no,directories=no,width="+width+",height="+height+",resizable=no,mebar=no,left="+LeftPosition+",top="+TopPosition;
		  	window.document.body.scroll = "auto";
		  	window.open(url,'popup', winOpts);
		},
		ListenPlayerPopup : function(song_id, song_part, c_longplay_yn, c_song_part){
			var width = "455";
			var height = "258";
			if(rCommon.getBrowserType() == 7 ){
				width = "438";
				height =" 160";
			}else if(rCommon.getBrowserType() == 8){
				width = "438";
				height =" 160";
			}else if(rCommon.getBrowserType() == 9){
				width = "436";
				height = "165";
			}else if(rCommon.getBrowserType() == 10){
				width = "437";
				height = "165";
			}else if(rCommon.getBrowserType() == 11){
				width = "436.5";
				height = "186.5";
			}else if(rCommon.getBrowserType() == 'Chrome'){
				width = "440";
				height = "147.5";
			}else if(rCommon.getBrowserType() == 'Safari'){
				width = "440";
				height = "184";
			}else if(rCommon.getBrowserType() == 'Firefox'){
				width = "440";
				height = "146.5";
			}
			
			LeftPosition=(screen.width)?(screen.width-width)/2:100;
		    TopPosition=(screen.height)?(screen.height-height)/2:100;
		    
		    url = encodeURI("/mypage/popup/listen_player_popup.do?song_id="+song_id+"&song_part="+song_part+"&c_longplay_yn="+ c_longplay_yn +"&c_song_part="+ c_song_part);
		    
		  	winOpts="scrollbars=yes,toolbar=no,location=no,directories=no,width="+width+",height="+height+",resizable=yes,mebar=no,left="+LeftPosition+",top="+TopPosition;
		  	window.document.body.scroll = "auto";
		  	window.open(url,'popup', winOpts);
		}
};

var goPage = function(pageNum) {
	$("#listDiv-currentPage").val(parseInt(pageNum, 10));
	rMypage.getPurchaseHist();
};

//천의 자리 콤마 찍기
function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}
