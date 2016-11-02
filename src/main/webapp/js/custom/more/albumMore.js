$(function() {
	rAlbumMore.getList();
});

var rAlbumMore = {
		getList : function() {
			var url = '/more/album_data.do';
			var data = {
					currentPage : $("#listDiv-currentPage").val(),
					rowPerPage : 12
			};
			$.ajax({
				url : url,
				type : 'GET',
				data : data,
				asysnc : false,
				cache : false,
		        dataType: "json",
				success : function(s) {
					rAlbumMore.listRes(s);
				},
				error : function(error) {
					//console.log(error);
				} 
			}); 
		},
		
		listRes : function(result)  {
			$('#AlbumListMore li').remove();
			var cnt = 0;
			if(result.pageData.list != null && result.pageData.list != '') {
				cnt =parseInt(result.pageData.list.length, 10);
			}
			for(var i=0; i<cnt; i++) {
				rAlbumMore.addListRow(result.pageData.list[i]);
			}
			
			$("#listDiv-paging").jqueryPager({
				divId: "listDiv-paging",
				pageSize: parseInt(result.pageData.rowPerPage, 10),
				currentPage: parseInt(result.pageData.currentPage, 10),
				pageTotal: parseInt(result.pageData.totalCnt, 10),
				pageBlock: 10
			});

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
			innerHtml += '<dl class="album-info">';
			innerHtml += '<dt class="ellipsis"><strong><a href="javascript:rCommon.getAlbumInfo('+dataObject.ALBUM_ID+')">' + album_name +'</a></strong></dt>';
			innerHtml += '<dd class="ellipsis"><a href="javascript:rCommon.getSingerList('+dataObject.SINGER_ID+')">' + singer_name +'</a></dd>';
			if(dataObject.R_DATE != null){
				innerHtml += '<dd>'+dataObject.R_DATE+'</dd>';
			}else{
				innerHtml += '<dd> - </dd>';
			}
			innerHtml += '<dd><span>　</span></dd>';
			innerHtml += '<dd><a href="javascript:rCommon.getAlbumInfo('+dataObject.ALBUM_ID+')"><img src="/img/design/album_btn.gif" alt="앨범보기" /></a></dd>';
			innerHtml += '</dl>';
			innerHtml += '</li>';
			$('#ulAlbumListMore').append(innerHtml);
		}
};
//<button onclick="javascript:rCommon.getAlbumInfo('+dataObject.ALBUM_ID+')">앨범보기</button>
var goPage = function(pageNum) {
	$("#listDiv-currentPage").val(parseInt(pageNum, 10));
	rAlbumMore.getList();
};

