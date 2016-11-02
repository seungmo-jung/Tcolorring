<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<script type="text/javascript">
$(function(){
	$(".search_sel a").click(function(){
		$(this).parent("li").siblings().toggle();
		$(".search_sel a").hover(function(){
			$(this).parent().addClass('on');
		},function(){
			$(this).parent().removeClass('on');
		});
	});
	
	$(".search_sel li").click(function(){
		$(".search_sel").find(".selected").removeClass('selected');
	    $(this).addClass('selected');
	});
	
});
</script>
<%request.setCharacterEncoding("utf-8");%>
	<div class="search_area">
		<div class="search_input">
			<ul class="search_sel">
				<li class="selected" data-value="0"><a href="#">곡</a></li>
				<li class="" data-value="1"><a href="#">가수</a></li>
				<li class="" data-value="2"><a href="#">앨범</a></li>
			</ul>
			<p class="search_ip"><input type="text" id="searchDiv-value" placeholder="검색어 입력"/></p>
		</div>
		<!-- ############## -->
		<div id="auto">
			<ul id="autoList">
				<li id="li0" class="liC"></li>
				<li id="li1" class="liC"></li>
				<li id="li2" class="liC"></li>
				<li id="li3" class="liC"></li>
				<li id="li4" class="liC"></li>
			</ul>
		</div>
		<!-- ############## -->
		<a href="javascript:rCommon.search()" class="btn_search"><img src="/img/design/btn_search.gif" alt="검색" /></a>
	</div>


