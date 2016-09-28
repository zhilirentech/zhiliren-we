<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/common/500.jsp"%>
<%@include file="/WEB-INF/common/tld.jsp"%>

<c:set var="url" value="${base}/admin/banner" />

<form id="pagerForm" action="${url}/list.html" method="post">
	<input type="hidden" name="pageNumber" value="${obj.pager.pageNumber}" />
	<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
	<input type="hidden" name="title" value="${obj.queryForm.title}" />
	<input type="hidden" name="type" value="${obj.queryForm.type}" />
</form>

<div class="panel_search">
	<form action="${url}/list.html" method="post" onsubmit="return navTabSearch(this);">
		<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
		<input type="hidden" name="pageNumber" value="${obj.pager.pageNumber}" />
		<p><span class="tag">标题：</span><span class="ipts"><input type="text" name="title" value="${obj.queryForm.title}"/></span></p>
		<p><span class="tag">轮播图类型：</span>
			<span class="ipts">
				<select id="select_courseType" name="type">
					<option value="">--不限--</option>
					<c:forEach var="map" items="${obj.bannerTypeEnum}" >
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
			<li><span class="ipts"><a href="${url}/add.html" target="dialog" class="btn btn_mini btn_add">新增轮播图</a></span></li>
		</ul>
		<table class="table" width="100%">
			<thead>
				<tr>
					<th>标题</th>
					<th>图片</th>
					<th>类型</th>
					<th>排序序号</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${obj.list}" var="one" >
					<tr>
						<td>${one.title}</td>
						<td><img src="${one.imgUrl}" width="150" height="50"></td>
						<td><we:enum key="${one.type}" className="com.xiaoka.game.common.enums.BannerTypeEnum"/></td>
						<td>${one.sort}</td>
						<td><we:enum key="${one.status}" className="com.xiaoka.game.common.enums.BannerStatusEnum"/></td>
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
