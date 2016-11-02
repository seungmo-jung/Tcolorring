if (typeof (Page) == "undefined") var Page = {};

(function($) {
	var service_yn = "N";
	
	Page.Popup = function() {
		this.initialize.apply(this, arguments);
	};
	
	Page.Popup.prototype = {
		
		initialize : function(hash) {
		},
		
		service_yn_setting : function(in_service_yn){
			service_yn = in_service_yn;
		},
		
		playerPopup : function(song_id){
				var width = "455";
				var height = "258";
				if(rCommon.getBrowserType() == 7 ){
					width = "438";
					height =" 225";
				}else if(rCommon.getBrowserType() == 8){
					width = "438";
					height =" 225";
				}else if(rCommon.getBrowserType() == 9){
					width = "437";
					height = "233";
				}else if(rCommon.getBrowserType() == 10){
					width = "435";
					height = "233";
				}else if(rCommon.getBrowserType() == 11){
					width = "435";
					height = "255";
				}else if(rCommon.getBrowserType() == 'Chrome'){
					width = "438";
					height = "215";
				}else if(rCommon.getBrowserType() == 'Safari'){
					width = "455";
					height = "256";
				}else if(rCommon.getBrowserType() == 'Firefox'){
					width = "437";
					height = "217";
				}
				
				LeftPosition=(screen.width)?(screen.width-width)/2:100;
			    TopPosition=(screen.height)?(screen.height-height)/2:100;
			    
			    url = encodeURI("/popup/playerpopup.do?song_id="+song_id);
			    
			  	winOpts="scrollbars=no,toolbar=no,location=no,directories=no,width="+width+",height="+height+",resizable=yes,mebar=no,left="+LeftPosition+",top="+TopPosition;
		  
			  	window.open(url,'popup', winOpts);
		},
		purchasePopup : function(song_id){
			if(service_yn == 'Y'){
				var width = "455";
				var height = "258";
				if(rCommon.getBrowserType() == 7 ){
					width = "438";
					height =" 225";
				}else if(rCommon.getBrowserType() == 8){
					width = "438";
					height =" 225";
				}else if(rCommon.getBrowserType() == 9){
					width = "437";
					height = "233";
				}else if(rCommon.getBrowserType() == 10){
					width = "435";
					height = "233";
				}else if(rCommon.getBrowserType() == 11){
					width = "435";
					height = "255";
				}else if(rCommon.getBrowserType() == 'Chrome'){
					width = "438";
					height = "215";
				}else if(rCommon.getBrowserType() == 'Safari'){
					width = "455";
					height = "256";
				}else if(rCommon.getBrowserType() == 'Firefox'){
					width = "437";
					height = "217";
				}
				
				LeftPosition=(screen.width)?(screen.width-width)/2:100;
			    TopPosition=(screen.height)?(screen.height-height)/2:100;
			    
			    url = encodeURI("/popup/purchasepopup.do?song_id="+song_id);
			    
			  	winOpts="scrollbars=no,toolbar=no,location=no,directories=no,width="+width+",height="+height+",resizable=yes,mebar=no,left="+LeftPosition+",top="+TopPosition;
		  
			  	window.open(url,'popup', winOpts);
			}else{
				alert('티플링 서비스를 가입해주세요.');
			}
		},
		purchaseMusic : function(so_cust_no, mdn, song_id, vcode, song_part, price){
			var data = {
					so_cust_no:so_cust_no,
					mdn:mdn,
					song_id:song_id,
					vcode:vcode,
					song_part:song_part,
					price:price
			};
			$.ajax({
				url : '/music/purchase.do',
				type : 'GET',
				data : data,
		        dataType: "json",
				asysnc : false,
				cache : false,
				success : function(data) {
					if(data.code == '1000'){
						alert(data.msg);
					}else if(data.code == '2000'){
						alert(data.msg);
					}else if(data.code == '0000'){
						alert(data.msg);
					}
					window.close();
				},
				error : function(error) {
					alert(error);
				}
			});
		}
	};
})(jQuery);

var rPopup = new Page.Popup();
