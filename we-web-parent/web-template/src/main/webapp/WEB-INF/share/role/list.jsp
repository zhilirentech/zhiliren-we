<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/share/500.jsp"%>
<%@include file="/WEB-INF/share/tld.jsp"%>
<c:set var="url" value="${params.url }" />
<form id="pagerForm" action="${url }" method="post">
	<input type="hidden" name="pageNumber" value="${obj.pager.pageNumber}" />
	<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
	<input type="hidden" name="name" value="${obj.queryForm.name}" />
</form>
<!-- 存放表单和分页查询条件 end-->
<!-- 检索条 start-->
<div class="panel_search">
	<form action="${url }" method="post" onsubmit="return navTabSearch(this);">
		<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
		<p><span class="tag">名称：</span><span class="ipts"><input type="text" name="name" value="${obj.queryForm.name }"/></span></p>
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
					<th><input type="checkbox" group="ids" class="ipt_checkbox checkboxCtrl"></th>
					<th>角色名称</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${obj.list}" var="pro" >
					<tr>
						<td class="txtc"><input name="ids" value="${pro.id }" type="checkbox" class="ipt_checkbox"></td>
						<td>${pro.name }</td>
						<td><myfn:date date="${pro.createTime }"/></td>
						<!-- 用#id占位，传进来的时候替换id值即可 -->
						<td>${not empty param.singlefunction ?fn:replace(param.singlefunction,'#id', pro.id):''}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- 表格 end-->
		<we:page pager="${obj.pager }"/>
	</div>
</div>
