<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/common/500.jsp"%>
<%@include file="/WEB-INF/common/tld.jsp"%>

<c:set var="url" value="${base}/admin/account" />

<form id="pagerForm" action="${url}/list.html" method="post">
	<input type="hidden" name="pageNumber" value="${obj.pager.pageNumber}" />
	<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
	<input type="hidden" name="name" value="${obj.queryForm.name}" />
	<input type="hidden" name="createTime" value="${obj.queryForm.createTime}" />
</form>

<div class="panel_search">
	<form action="${url}/list.html" method="post" onsubmit="return navTabSearch(this);">
		<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
		<input type="hidden" name="pageNumber" value="${obj.pager.pageNumber}" />
		<p>
			<span class="tag">创建时间：</span><span class="ipts">
				<input name="createTime" type="text" readonly="readonly" class="date readonly" value="${obj.queryForm.createTime}"/>
			</span>
		</p>
		<p><span class="tag">姓名/openId：</span><span class="ipts"><input type="text" name="name" value="${obj.queryForm.name}"/></span></p>
		<p><span class="tag">操作类型：</span>
			<span class="ipts">
				<select id="select_courseType" name="type">
					<option value="">--不限--</option>
					<c:forEach var="map" items="${obj.accountTypeEnum}" >
						<c:choose>
						   <c:when test="${map.key == obj.queryForm.type}">
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
	
		<ul class="tabletools">
			<li><span class="ipts"><a href="${url}/add.html" target="dialog" class="btn btn_mini btn_add">新增</a></span></li>
		</ul>
		<table class="table" width="100%">
			<thead>
				<tr>
					<th>姓名</th>
					<th>openId</th>
					<th>学校</th>
					<th>类型</th>
					<th>金额</th>
					<th>时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${obj.list}" var="one" >
					<tr>
						<td>${one.customerName}</td>
						<td>${one.openId }</td>
						<td>${one.schoolName}</td>
						<td><we:enum className="com.xiaoka.game.common.enums.AccountTypeEnum" key="${one.type}"/></td>
						<td>${one.money}</td>
						<td>${one.createTime}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<!-- 分页 start-->
		<we:page pager="${obj.pager}"/>
		<!-- 分页 end-->
		 
	</div>
</div>
