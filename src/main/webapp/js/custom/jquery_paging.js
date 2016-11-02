
(function($) {

 $.fn.jqueryPager = function(options) {
  // 기본값 설정
  var defaults = {
   divId: "paging",
   pageSize: 15,
   currentPage: 1,
   pageTotal: 0,
   pageBlock: 10
  };

  var subOption = $.extend(true, defaults, options); // defaults와 option을 병합한다.

  return this.each(function() {
   var divId = subOption.divId;
   var currentPage = subOption.currentPage*1;  // 현재 페이지
   var pageSize = subOption.pageSize*1;   // 출력 리스트 수(한페이지의 출력 레코드수)
   var pageBlock = subOption.pageBlock*1;  // 1~10까지 블락 
   var pageTotal = subOption.pageTotal*1;  // 총 데이터 수
   
   if (!pageSize) pageSize = 10;     // 출력 리스트수가 없으면 초기값 10으로 설정
   if (!pageBlock) pageBlock = 10;     // 블락 초기값이 없으면 10으로 설정

   var pageTotalCnt = Math.ceil(pageTotal/pageSize);
   var pageBlockCnt = Math.ceil(currentPage/pageBlock);
   var sPage, ePage;
   var html = "";

   if (pageBlockCnt > 1) {
    sPage = (pageBlockCnt-1)*pageBlock+1;
   } else {
    sPage = 1;
   }

   if ((pageBlockCnt*pageBlock) >= pageTotalCnt) {
    ePage = pageTotalCnt;
   } else {
    ePage = pageBlockCnt*pageBlock;
   }

   if (sPage > 1) {
	html +='<a href="javascript:goPage(' + 1 + ');"><img src="/img/design/btn_first.gif" alt="맨 처음" /></a>';
	html +='<a href="javascript:goPage(' + (sPage-pageBlock) + ');"><img src="/img/design/btn_prev.gif" alt="이전" /></a>';
   }

   html += '<span class="num">';
   
   for (var i=sPage; i<=ePage; i++) {
    if (currentPage == i) {
     html += '<a class="on">' + i + '</a>';
    } else {
     html += '<a href="javascript:goPage(' + i + ');">' + i + '</a>';
    }
   }
   
   html += '</span>';
   
   if (ePage < pageTotalCnt) {
	html += '<a href="javascript:goPage(' + (ePage+1) + ');"><img src="/img/design/btn_next.gif" alt="다음" /></a>';
	html += '<a href="javascript:goPage(' + pageTotalCnt + ');"><img src="/img/design/btn_last.gif" alt="맨 뒤로" /></a>';
   }

   $("#"+divId).empty().append(html);

  });

 };

})(jQuery);