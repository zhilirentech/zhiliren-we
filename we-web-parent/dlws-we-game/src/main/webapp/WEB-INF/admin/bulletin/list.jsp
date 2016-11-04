<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/common/500.jsp"%>
<%@include file="/WEB-INF/common/tld.jsp"%>

<c:set var="url" value="${base}/admin/bulletin" />

<form id="pagerForm" action="${url}/list.html" method="post">
	<input type="hidden" name="pageNumber" value="${obj.pager.pageNumber}" />
	<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
	<input type="hidden" name="type" value="${obj.queryForm.type}" />
</form>


<div class="panel_box noborder">
	<div class="panel_content nopadding">
	
		<ul class="tabletools">
			<li><span class="ipts"><a href="${url}/add.html" target="dialog" class="btn btn_mini btn_add">新增公告</a></span></li>
		</ul>
		<table class="table" width="100%">
			<thead>
				<tr>
					<th>内容</th>
					<th>类型</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${obj.list}" var="one" >
					<tr>
						<td>${one.content}</td>
						<td><we:enum key="${one.type}" className="com.xiaoka.game.common.enums.BulletinTypeEnum"/></td>
						<td>
							<a target="dialog" rel="dlgId1" href="${url}/update.html?id=${one.id}" class="btn btn_mini btn_modify">修改</a>
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
		<ccig:page pager="${obj.pager }" />
		 
	</div>
</div>
