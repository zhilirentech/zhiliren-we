<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/common/500.jsp"%>
<%@include file="/WEB-INF/common/tld.jsp"%>

<c:set var="url" value="${base}/admin/authority/function" />

<form id="pagerForm" action="${url}/list.html" method="post">
	<input type="hidden" name="pageNumber" value="${obj.pager.pageNumber}" />
	<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
	<input type="hidden" name="name" value="${obj.queryForm.name}" />
	<input type="hidden" name="parentId" value="${obj.queryForm.parentId}" />
</form>

<div class="panel_search">
	<form action="${url}/list.html" method="post" onsubmit="return navTabSearch(this);">
		<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
		<input type="hidden" name="pageNumber" value="${obj.pager.pageNumber}" />
		<p><span class="tag">功能名称：</span><span class="ipts"><input type="text" name="name" value="${obj.queryForm.name}"/></span></p>
		<p><span class="tag">上级功能：</span>
			<span class="ipts">
				<select name="parentId">
					<option value="-1">--不限--</option>
					<c:forEach items="${obj.functions}" var="pro" >
						<c:choose>
						   <c:when test="${pro.id == obj.queryForm.parentId}">
						   		<option value="${pro.id}" selected="selected">${pro.name}</option>
						   </c:when>
						   <c:otherwise>
						   		<option value="${pro.id}">${pro.name}</option>
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
			<li><span class="ipts"><a href="${url}/add.html" target="dialog" class="btn btn_mini btn_add">新增功能</a></span></li>
			<li><span class="ipts"><a href="${url}/batchDelete" target="selectedTodo" rel="ids" postType="map" title="是否要批量删除" class="btn btn_mini btn_del">批量删除</a></span></li>
		</ul>
		<table class="table" width="100%">
			<thead>
				<tr>
					<th><input type="checkbox" group="ids" class="ipt_checkbox checkboxCtrl"></th>
					<th>功能名称</th>
					<th>访问地址</th>
					<th>上级功能</th>
					<th>功能等级</th>
					<th>创建时间</th>
					<th>修改时间</th>
					<th>备注</th>
					<th>序号</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${obj.list}" var="one" >
					<tr>
						<td class="txtc"><input name="ids" value="${one.id}" type="checkbox" class="ipt_checkbox"></td>
						<td>${one.name}</td>
						<td>${one.url }</td>
						<td>${one.parentName }</td>
						<td>${one.level }</td>
						<td><fmt:formatDate value="${one.createTime}" pattern="yyyy-MM-dd"/></td>
						<td><fmt:formatDate value="${one.updateTime}" pattern="yyyy-MM-dd"/></td>
						<td>${one.remark}</td>
						<td>${one.sort}</td>
						<td>
							<a target="dialog" rel="dlgId1" href="${url}/update.html?id=${one.id}" class="btn btn_mini btn_modify">修改</a>
							<%--
								这里如果有写title，则需要确认才会操作
							 --%>
							<a target="ajaxTodo" rel="dlgId1" href="${url}/delete?id=${one.id}" title='是否要删除' class='btn btn_mini btn_del'>删除</a>
						</td>
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
