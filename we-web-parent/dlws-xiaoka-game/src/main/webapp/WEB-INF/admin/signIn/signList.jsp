<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/common/500.jsp"%>
<%@include file="/WEB-INF/common/tld.jsp"%>

<c:set var="url" value="${base}/admin/signIn" />

<form id="pagerForm" action="${url}/signList.html" method="post">
	<input type="hidden" name="pageNumber" value="${obj.pager.pageNumber}" />
	<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
	<input type="hidden" name="name" value="${obj.queryForm.name}" />
	<input type="hidden" name="signDate" value="${obj.queryForm.signDate}" />
	<input type="hidden" name="status" value="${obj.queryForm.status}" />
</form>
<div class="panel_search">
	<form action="${url}/signList.html" method="post" onsubmit="return navTabSearch(this);">
		<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
		<input type="hidden" name="pageNumber" value="${obj.pager.pageNumber}" />
		<p>
			<span class="tag">签到日期：</span><span class="ipts">
				<input name=signDate type="text" readonly="readonly" class="date readonly" value="${obj.queryForm.signDate}"/>
			</span>
		</p>
		<p><span class="tag">姓名/openId：</span><span class="ipts"><input type="text" name="name" value="${obj.queryForm.name}"/></span></p>
		<p><span class="tag">状态：</span>
			<span class="ipts">
				<select id="select_courseType" name="status">
					<option value="">--不限--</option>
					<c:forEach var="map" items="${obj.signInStatusEnum}" >
						<c:choose>
						   <c:when test="${map.key == obj.queryForm.status}">
						   		<option value="${map.key}" selected="selected">${map.value}</option>
						   </c:when>
						   <c:otherwise>
						   		<option value="${map.key}">${map.value}</option>
						   </c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</span>
		</p>
		<p><span class="ipts"><input class="btn btn_add" type="submit" value="检索"/></span></p>
		
	</form>
</div>


<div class="panel_box noborder">
	<div class="panel_content nopadding">
	
		<table class="table" width="100%">
			<thead>
				<tr>
					<th><input type="checkbox" group="ids" class="ipt_checkbox checkboxCtrl"></th>
					<th>姓名</th>
					<th>openId</th>
					<th>学校</th>
					<th>签到状态</th>
					<th>签到日期</th>
					<th>签到时间</th>
					<th>分配奖金</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${obj.list}" var="one" >
					<tr>
						<td class="txtc"><input name="ids" value="${one.id}" type="checkbox" class="ipt_checkbox"></td>
						<td>${one.customerName}</td>
						<td>${one.openId}</td>
						<td>${one.schoolName}</td>
						<td><we:enum className="com.xiaoka.game.common.enums.SignInStatusEnum" key="${one.status}"/></td>
						<td><fmt:formatDate value="${one.signDate}" pattern="yyyy-MM-dd"/></td>
						<td><fmt:formatDate value="${one.signTime}" pattern="HH:mm"/></td>
						<td>${one.outAmount}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<%--
			分页标签需要注意的是：
				如果是divSearch，这里需要填充rel属性
				如果是dialogSearch，需要赋值dialog='true'
				如果是navTabSearch，则不需要再写其他
				其他参数自行参看
		--%>
		<!-- 分页 start-->
		<we:page pager="${obj.pager}"/>
		<!-- 分页 end-->
	</div>
</div>
