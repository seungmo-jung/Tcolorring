if (typeof (Page) == "undefined") var Page = {};

(function($) {
	Page.MusicList = function() {
		this.initialize.apply(this, arguments);
	};

	Page.MusicList.prototype = {
		initialize : function(hash) {
		},
		
		// 검색
		search: function() {
			var searchData = $('#searchDiv-value').val().trim();
			if(searchData != null && searchData != ""){
				//console.log(encodeURI($('#searchDiv-value').val(), 'UTF-8'));
				location.href='/search.do?type='+$(".search_sel").find(".selected").data("value")+'&query='+encodeURI(encodeURIComponent($('#searchDiv-value').val()));
			}else{
				$('#searchDiv-value').val("");
				$('#searchDiv-value').focus();
				alert('검색어를 입력해주세요.');
			}
			
		},

		getSingerList : function(singer_id) {
			location.href='/singer.do?singer='+singer_id;
		},
		getAlbumInfo : function(album_id) {
			location.href='/album.do?album='+album_id;
		},
		
		//미가입자 알람창.
		serviceAlert : function(){
			alert('티플링 서비스를 가입해주세요.');
		},
		toMypage : function(){
			location.href='/mypage/mypage.do';
		},
		
		//album_id 로 앨범 이미지 가져오기.
		editImagePath : function (album_id){
			var zero = '';
			var bfresult = '';
			var path = '';
			
			album_id = album_id.toString();
			
			if(album_id.length < 9){
				for(var i=0; i<9-album_id.length; i++){
					zero += '0';
				}
			}
			bfresult = zero +album_id;
			
			path = '/' +bfresult.substring(0, 3) + '/' + bfresult.substring(3, 5) + '/' + bfresult.substring(5, 7) + '/' + bfresult.substring(7, 9) + '/' + album_id + '_w.jpg';
			
			return path;
		},
		
		//일정 특수 문자 변환. (HTML 코드에 영향을 줌)
		replaceWord : function (word){
			var  result = word.replace("&", "&amp;");
			result = result.replace(">", "&gt;");
			result = result.replace("<", "&lt;");
			
			return result;
		},
		
		// 전화번호 하이픈 처리.
		appendHyphen : function (mdn){
			var result = '';
			if(mdn.length == 11){
				result = mdn.substring(0,3) + '-' + mdn.substring(3,7) + '-' + mdn.substring(7,11);
			}else if(mdn.length == 10){
				result = mdn.substring(0,3) + '-' + mdn.substring(3,6) + '-' + mdn.substring(6,10);
			}
			return result;
		},
		
		// BrowserType 확인.
		getBrowserType : function(){
			 var _ua = navigator.userAgent;
		        var rv = -1;
		         
		        //IE 11,10,9,8
		        var trident = _ua.match(/Trident\/(\d.\d)/i);
		        if( trident != null )
		        {
		            if( trident[1] == "7.0" ) return rv = 11;
		            if( trident[1] == "6.0" ) return rv = 10;
		            if( trident[1] == "5.0" ) return rv = 9;
		            if( trident[1] == "4.0" ) return rv = 8;
		        }
		         
		        //IE 7...
		        if( navigator.appName == 'Microsoft Internet Explorer' ) return rv = 7;
		         
		        /*
		        var re = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
		        if(re.exec(_ua) != null) rv = parseFloat(RegExp.$1);
		        if( rv == 7 ) return rv = "IE" + 7; 
		        */
		         
		        //other
		        var agt = _ua.toLowerCase();
		        if (agt.indexOf("chrome") != -1) return 'Chrome';
		        if (agt.indexOf("opera") != -1) return 'Opera'; 
		        if (agt.indexOf("staroffice") != -1) return 'Star Office'; 
		        if (agt.indexOf("webtv") != -1) return 'WebTV'; 
		        if (agt.indexOf("beonex") != -1) return 'Beonex'; 
		        if (agt.indexOf("chimera") != -1) return 'Chimera'; 
		        if (agt.indexOf("netpositive") != -1) return 'NetPositive'; 
		        if (agt.indexOf("phoenix") != -1) return 'Phoenix'; 
		        if (agt.indexOf("firefox") != -1) return 'Firefox'; 
		        if (agt.indexOf("safari") != -1) return 'Safari'; 
		        if (agt.indexOf("skipstone") != -1) return 'SkipStone'; 
		        if (agt.indexOf("netscape") != -1) return 'Netscape'; 
		        if (agt.indexOf("mozilla/5.0") != -1) return 'Mozilla';
		}
		
	};
})(jQuery);

var rCommon = new Page.MusicList();
