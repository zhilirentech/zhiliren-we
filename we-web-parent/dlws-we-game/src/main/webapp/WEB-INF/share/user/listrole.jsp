<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/500.jsp"%>
<%@include file="/WEB-INF/share/tld.jsp"%>
<c:set var="url" value="${base }/share/user/listrole.html"/>
<form id="pagerForm" action="${url}" method="post">
	<input type="hidden" name="pageNumber" value="${obj.pager.pageNumber}" />
	<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
	<input type="hidden" name="userId" value="${obj.queryForm.userId}" />
	<input type="hidden" name="rel" value="${obj.queryForm.rel}" />
	<input type="hidden" name="inside" value="${obj.queryForm.inside}" />
	<input type="hidden" name="name" value="${obj.queryForm.name}" />
</form>
<div class="panel_search">
	<form action="${url }" method="post" onsubmit="return divSearch(this,'${obj.queryForm.rel }');">
		<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
		<input type="hidden" name="userId" value="${obj.queryForm.userId}" />
		<input type="hidden" name="rel" value="${obj.queryForm.rel}" />
		<input type="hidden" name="inside" value="${obj.queryForm.inside}" />
		<p><span class="tag">名称：</span><span class="ipts"><input type="text" name="name" value="${obj.queryForm.name }"/></span></p>
		<p><span class="ipts"><input class="btn btn_add" type="submit" value="检索"/></span></p>
	</form>
</div>
<div class="panel_box noborder">
	<div class="panel_content nopadding">
		<ul class="tabletools">
			<c:if test="${obj.queryForm.inside }">
				<li><a title="批量删除" target="selectedTodo" rel="roleIds" postType="map" href="${base}/share/user/removeroles.html?userId=${obj.queryForm.userId}" class="btn btn_mini btn_del">批量删除</a></li>
			</c:if>
			<c:if test="${!obj.queryForm.inside }">
				<li><a title="批量添加" target="selectedTodo" rel="roleIds" postType="map" href="${base}/share/user/addroles.json?userId=${obj.queryForm.userId}" class="btn btn_mini btn_add">批量添加</a></li>
			</c:if>
		</ul>
		<table class="table" width="100%" layoutH="148">
			<thead>
				<tr>
					<th width="26"><input type="checkbox" group="roleIds" class="ipt_checkbox checkboxCtrl"></th>
					<th>名称</th>
					<th>描述</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${obj.list}" var="pro">
					<tr target="sid_product" rel="${pro.id }">
						<td class="txtc"><input name="roleIds" value="${pro.id}" type="checkbox" class="ipt_checkbox"></td>
						<td>${pro.name}</td>
						<td>${pro.remark}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<we:page pager="${obj.pager }" rel="${obj.queryForm.rel }" />
	</div>
</div>