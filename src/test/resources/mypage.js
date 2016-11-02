$(function() {
	rMypage.getPurchaseHist();
	rMypage.getDefaultSong();
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
					console.log(s);
					rMypage.listPurHist(s);
				},
				error : function(error){
					console.log(error);
				}
			});
		},
		
		listPurHist : function(result){
			$('#pur_hist tbody tr').remove();
			console.log(result);
			var cnt = 0;
			if(result.pageData.list!= null && result.pageData.list != ''){
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
					pageBlock: 10
				});
			}else{
				rMypage.notFoundPurchaseHist();
			} 
			
		},
		
		addPurchaseHist : function(dataObject){
			var innerHtml = '<tr>';
			innerHtml += '<td class="tac"><input type="radio" onclick="" /></td>';
			innerHtml += '<td class="ellipsis"><b>' + dataObject.SONG_TITLE + '</b>  <font size="2">('+ dataObject.C_LONGPLAY_YN+'/'+dataObject.C_SONG_PART+')</font></td>';
			innerHtml += '<td class="tac"><a href="#"><img src="/img/design/ico_listening.gif" alt="듣기" /></a></td>';
			innerHtml += '<td class="tac ellipsis">' + dataObject.SINGER_NAME + '</td>';
			innerHtml += '<td class="tac">' + comma(dataObject.PRICE) + '원</td>';
			innerHtml += '<td class="tac">' + dataObject.P_DATE + '</td>';
			if(dataObject.BR_VCODE != null && dataObject.BR_VCODE != ''){
				innerHtml += '<td class="tac"><span class="txt_pink">이용중</span>'
					+'<a href="#none"><img src="/img/design/btn_del.gif" alt="삭제" /></a></td>';
			}else{
				innerHtml += '<td class="tac"></td>';
			}
			innerHtml += '</tr>';
			
			$('#pur_hist tbody').append(innerHtml);
		},
		
		notFoundPurchaseHist : function(){
			var innerHtml = '<tr>';
			innerHtml += '<td colspan="7" height="50" class="tac">구매내역이 존재하지 않습니다.</td>';
			innerHtml += '</tr>';
			
			$('#pur_hist tbody').append(innerHtml);
		},
		
		getDefaultSong : function(){
			var url = '/mypage/getDefaultSong.do';
			$.ajax({
				url : url,
				dataType : 'Json',
				type : 'GET',
				success : function(s){
					console.log(s);
					rMypage.setDefaultSongDiv(s);
				},
				error : function(error){
					console.log(error);
				}
			});
		},
		
		setDefaultSongDiv : function(result){
			console.log(result);
			if(result.pageData != null && result.pageData != ''){
				$('#notFoundDefaultSong').hide();
				rMypage.addDefaultSong(result.pageData[0]);
			}else{
				rMypage.notFoundDefaultSong();
			}
		},
		
		addDefaultSong : function(dataObject){
			console.log(dataObject);
			var  innerHtml = '<tr>';
			innerHtml += '<td width = "500px"><h3>"'+ dataObject.SONG_TITLE +'" - "'+ dataObject.SINGER_NAME +'"<br/>('+ dataObject.LONGPLAY_YN +'/'+ dataObject.SONG_PART +')</h3></td>';
			innerHtml += '<td width = "200px" align = "center"><button onclick="Javascript:rMypage.defaultSongSetPopup();">설정</button></td>';
			innerHtml += '</tr>'; 
			//var innerHtml = '<h2> "' + dataObject.title + '" - "' + dataObject.singer + '"</h2>';
			
			console.log(innerHtml);
			$('#default_song_set table').append(innerHtml);
		},
		
		notFoundDefaultSong : function(){
			$('#default_song_set').hide();
			$('#notFoundDefaultSong').show();
		},
		
		defaultSongSetPopup : function(){
			var width = "500";
			var height = "200";
			
			LeftPosition=(screen.width)?(screen.width-width)/2:100;
		    TopPosition=(screen.height)?(screen.height-height)/2:100;
		    
		    url = encodeURI("/mypage/popup/default_song_popup.do");
		    
		  	winOpts="scrollbars=yes,toolbar=no,location=no,directories=no,width="+width+",height="+height+",resizable=no,mebar=no,left="+LeftPosition+",top="+TopPosition;
		  	window.open(url,'popup', winOpts);
		},
		callerSetPopup : function(){
			var width = "600";
			var height = "280";
			
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
		bizSetPopup : function(){
			var width = "440";
			var height = "152";
			
			LeftPosition=(screen.width)?(screen.width-width)/2:100;
		    TopPosition=(screen.height)?(screen.height-height)/2:100;
		    
		    url = encodeURI("/mypage/popup/biz_set.do");
		    
		  	winOpts="scrollbars=yes,toolbar=no,location=no,directories=no,width="+width+",height="+height+",resizable=no,mebar=no,left="+LeftPosition+",top="+TopPosition;
	  
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
