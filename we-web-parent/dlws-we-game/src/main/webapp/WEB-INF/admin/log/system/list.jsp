<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/common/500.jsp"%>
<%@include file="/WEB-INF/common/tld.jsp"%>

<c:set var="url" value="${base}/admin/log/system" />

<form id="pagerForm" action="${url}/list.html" method="post">
	<input type="hidden" name="pageNumber" value="${obj.pager.pageNumber}" />
	<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
	<input type="hidden" name="operatorName" value="${obj.queryForm.operatorName}" />
	<input type="hidden" name="functionName" value="${obj.queryForm.functionName}" />
</form>

<div class="panel_search">
	<form action="${url}/list.html" method="post" onsubmit="return navTabSearch(this);">
		<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
		<input type="hidden" name="pageNumber" value="${obj.pager.pageNumber}" />
		<p><span class="tag">操作员名称：</span><span class="ipts"><input type="text" name="operatorName" value="${obj.queryForm.operatorName}"/></span></p>
		<p><span class="tag">功能名称：</span><span class="ipts"><input type="text" name="functionName" value="${obj.queryForm.functionName}"/></span></p>
		<p><span class="ipts"><input class="btn btn_add" type="submit" value="检索"/></span></p>
	</form>
</div>

<div class="panel_box noborder">
	<div class="panel_content nopadding">
	
		<ul class="tabletools">
		</ul>
		<table class="table" width="100%">
			<thead>
				<tr>
					<th><input type="checkbox" group="ids" class="ipt_checkbox checkboxCtrl"></th>
					<th>操作员名称</th>
					<th>功能名称</th>
					<th>访问路径</th>
					<th>操作时间</th>
					<th>IP地址</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${obj.list}" var="one" >
					<tr>
						<td class="txtc"><input name="ids" value="${one.id}" type="checkbox" class="ipt_checkbox"></td>
						<td>${one.operatorName}</td>
						<td>${one.functionName }</td>
						<td>${one.path }</td>
						<td><fmt:formatDate value="${one.operatorTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${one.ip }</td>
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
		<ccig:page pager="${obj.pager }" />
	</div>
</div>
