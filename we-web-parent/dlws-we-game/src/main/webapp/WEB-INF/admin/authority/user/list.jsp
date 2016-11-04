<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/common/500.jsp"%>
<%@include file="/WEB-INF/common/tld.jsp"%>

<c:set var="url" value="${base}/admin/authority/user" />

<form id="pagerForm" action="${url}/list.html" method="post">
	<input type="text" name="pageNumber" value="${obj.pager.pageNumber}" />
	<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
	<input type="hidden" name="username" value="${obj.queryForm.username}" />
</form>

<div class="panel_search">
	<form action="${url}/list.html" method="post" onsubmit="return navTabSearch(this);">
		<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
		<input type="hidden" name="pageNumber" value="${obj.pager.pageNumber}" />
		<p><span class="tag">登录名：</span><span class="ipts"><input type="text" name="username" value="${obj.queryForm.username}"/></span></p>
		<p><span class="ipts"><input class="btn btn_add" type="submit" value="检索"/></span></p>
		
	</form>
</div>

<div class="panel_box noborder">
	<div class="panel_content nopadding">
	
		<ul class="tabletools">
			<li><span class="ipts"><a href="${url}/add.html" target="dialog" class="btn btn_mini btn_add">新增</a></span></li>
			<li><span class="ipts"><a href="${url}/batchDelete" target="selectedTodo" rel="ids" postType="map" title="是否要批量删除" class="btn btn_mini btn_del">批量删除</a></span></li>
		</ul>
		<table class="table" width="100%">
			<thead>
				<tr>
					<th><input type="checkbox" group="ids" class="ipt_checkbox checkboxCtrl"></th>
					<th>登录名</th>
					<th>用户类型</th>
					<th>用户状态</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${obj.list}" var="one" >
					<tr>
						<td class="txtc"><input name="ids" value="${one.id}" type="checkbox" class="ipt_checkbox"></td>
						<td>${one.username}</td>
						<td>${one.userTypeName }</td>
						<td>${one.userStatusName}</td>
						<td><fmt:formatDate value="${one.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>
							<a target="navTab" rel="user_update" href="${url}/update.html?id=${one.id}" class="btn btn_mini btn_modify">修改用户</a>
							<%--
								这里如果有写title，则需要确认才会操作
							 --%>
							<a target="ajaxTodo" rel="dlgId1" href="${url}/delete?id=${one.id}" title='是否要删除' class='btn btn_mini btn_del'>删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<!-- 分页 start-->
		<we:page pager="${obj.pager}"/>
		<!-- 分页 end-->
		 
	</div>
</div>
