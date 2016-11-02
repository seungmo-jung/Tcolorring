<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.Statement, java.sql.ResultSet"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom/mypage/mypage.js"></script>

  <input type="hidden" id="listDiv-currentPage" name="listDiv-currentPage" value="1" />
  	<h2 class="mt40"><img src="/img/design/tit_paylist.gif" alt="구매내역" /></h2>
  		<div id="pur_hist" class="tb_type">
 			<table summary="번호, 곡명, 가수, 금액, 구매일, 설정 셀로 구성">
				<caption>곡 목록</caption>
				<colgroup>
					<col width="15%" />
					<col width="27%" />
					<col width="7%" />
					<col width="18%" />
					<col width="9%" />
					<col width="12%" />
					<col width="12%" />
				</colgroup>
				<thead>
					<tr>
						<th scope="col" style="margin:auto; text-align:center">설정</th>
						<th scope="col" colspan="2">곡명</th>
						<th scope="col">가수</th>
						<th scope="col">금액</th>
						<th scope="col">구매일</th>
						<th scope="col">설정</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
  		</div>
		
  		<div id="listDiv-paging" class="paging"></div>	
  		
  		<p class="mt10">※ 이용중인 통화연결음을 삭제하시는 경우 통화연결음은 기본음원으로 변경됩니다.</p>
  		  	
  		<h2 class="mt40"><img src="/img/design/tit_set.gif" alt="내폰설정하기" /></h2>
  		<div class="phone_set">
  			<div class="fl">
  				<img src="/img/design/txt_set_send.gif" alt="발신자별 음악설정" />
  				<p class="hidden">최대 5명까지 지정하신 번호로부터 전화가 올 경우 이용중인 컬러링을 들려드립니다.</p>
  				<a href="Javascript:rMypage.callerSetPopup();" class="btn_set"><img src="/img/design/btn_setting.gif" alt="설정"/></a>
  			</div>
  			<div class="fl">
  				<img src="/img/design/txt_set_time.gif" alt="시간대별 음악설정" />
  				<p class="hidden">시간대 별로 최대 3구간의 시간을 지정하여 음악을 선택할 수 있습니다.</p>
  				<a href="Javascript:rMypage.timeSetPopup();" class="btn_set"><img src="/img/design/btn_setting.gif" alt="설정"/></a>
  			</div>
  		</div>
 
 <%@ include file="/WEB-INF/views/common/footer.jsp"%>
