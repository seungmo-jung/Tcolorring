
$(function() {
	rAlbumList.getList();
});

var rAlbumList = {
		getList : function() {
			var url = '/main/album.do';
			var data = {
					limit : 5
			};
			
			$.ajax({
				url : url,
				data : data,
				dataType : 'Json',
				asysnc : false,
				cache : false,
				type : 'GET',
				success : function(s) {
					rAlbumList.listRes(s);
				},
				error : function(error) {
					//console.log(error);
				} 
			});
		},
		listRes : function(result)  {
			
			$('#newAlbumList li').remove();
			var cnt = 0;
			if(result.pageData.list != null && result.pageData.list != '') {
				cnt=parseInt(result.pageData.list.length, 10);
			}
			for(var i=0; i<cnt; i++) {
				rAlbumList.addListRow(result.pageData.list[i]);
			}
		},
		
		addListRow : function (dataObject) {
			var album_name =  rCommon.replaceWord(dataObject.ALBUM_NAME);
			var singer_name = rCommon.replaceWord(dataObject.SINGER_NAME);
			
			var innerHtml = '<li>';
			if(dataObject.IMG_YN == true){
				innerHtml += '<a href="javascript:rCommon.getAlbumInfo('+dataObject.ALBUM_ID+')"><img src="/crbt/img/album'+rCommon.editImagePath(dataObject.ALBUM_ID)+'" onError="this.src=\'/img/album/no_album_img.PNG\'" class="album-img"></a>';
			}else {
				innerHtml += '<a href="javascript:rCommon.getAlbumInfo('+dataObject.ALBUM_ID+')"><img src="/img/album/no_album_img.PNG" class="album-img"></a>';
			}
			innerHtml += '<p class="singer"><strong><a href="javascript:rCommon.getAlbumInfo('+dataObject.ALBUM_ID+')" class="ellipsis">'+ album_name +'</a></strong>';
			innerHtml += '<span><a href="javascript:rCommon.getSingerList('+dataObject.SINGER_ID+')" class="ellipsis">'+ singer_name +'</a></span></p>';
			innerHtml += '</li>';
			$('#newAlbumList').append(innerHtml);
		},
		//class="new-album-info"
		colMore: function() {
			location.href='/more/album.do';
		}
};
