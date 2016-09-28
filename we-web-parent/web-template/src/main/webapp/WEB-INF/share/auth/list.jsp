<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/share/500.jsp"%>
<%@include file="/WEB-INF/share/tld.jsp"%>
<c:set var="url" value="${params.url }" />
<form id="pagerForm" action="${url }" method="post">
	<input type="hidden" name="pageNumber" value="${obj.pager.pageNumber}" />
	<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
</form>
<!-- 存放表单和分页查询条件 end-->
<!-- 检索条 start-->
<div class="panel_search">
	<form action="${url }" method="post" onsubmit="return navTabSearch(this);">
		<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
		<p><span class="tag">名称：</span><span class="ipts"><input type="text" name="authkey" value="${obj.queryForm.authkey }"/></span></p>
		<p><span class="ipts"><input class="btn btn_add" type="submit" value="检索"/></span></p>
	</form>
</div>
<!-- 检索条 end-->
<div class="panel_box noborder">
	<div class="panel_content nopadding">
		<!-- 表格工具按钮 start-->
		<ul class="tabletools">${not empty param.mutilfunction ?param.mutilfunction:''}</ul>
		<!-- 表格工具按钮 start-->
		<!-- 表格 end-->
		<table class="table" width="100%">
			<thead>
				<tr>
					<th>权限名称</th>
					<th>所属项目</th>
					<th>权限备注</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${obj.list}" var="pro" >
					<tr>
						<td>${pro.authKey }</td>
						<td>${pro.type }</td>
						<td>${pro.remark }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- 表格 end-->
		<we:page pager="${obj.pager }"/>
	</div>
</div>
