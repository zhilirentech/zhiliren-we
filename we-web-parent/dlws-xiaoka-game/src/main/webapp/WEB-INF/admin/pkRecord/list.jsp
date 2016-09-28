<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/common/500.jsp"%>
<%@include file="/WEB-INF/common/tld.jsp"%>

<c:set var="url" value="${base}/admin/pkRecord" />

<form id="pagerForm" action="${url}/list.html" method="post">
	<input type="hidden" name="pageNumber" value="${obj.pager.pageNumber}" />
	<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
	<%-- <input type="hidden" name="typeCode" value="${obj.queryForm.typeCode}" />
	<input type="hidden" name="dictCode" value="${obj.queryForm.dictCode}" />
	<input type="hidden" name="dictName" value="${obj.queryForm.dictName}" /> --%>
</form>

<div class="panel_search"> 
	<%-- <form action="${url}/list.html" method="post" onsubmit="return navTabSearch(this);">
		<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
		<input type="hidden" name="pageNumber" value="${obj.pager.pageNumber}" />
		
		<p>
			<span class="tag">字典类型：</span>
			<span class="ipts">
				<select name="typeCode">
					<option value="">--不限--</option>
					<c:forEach items="${obj.typeList}" var="type" >
						<c:choose>
						   <c:when test="${type.typeCode == obj.queryForm.typeCode}">
						   		<option value="${type.typeCode}" selected="selected">${type.typeName}</option>
						   </c:when>
						   <c:otherwise>
						   		<option value="${type.typeCode}">${type.typeName}</option>
						   </c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</span>
		</p>
		<p><span class="tag">字典代码：</span><span class="ipts"><input type="text" name="dictCode" value="${obj.queryForm.dictCode}"/></span></p>
		<p><span class="tag">字典名称：</span><span class="ipts"><input type="text" name="dictName" value="${obj.queryForm.dictName}"/></span></p>
		<p><span class="ipts"><input class="btn btn_add" type="submit" value="检索"/></span></p>
	</form> --%>
</div>

<div class="panel_box noborder">
	<div class="panel_content nopadding">
		<%-- <ul class="tabletools">
			<li><span class="ipts"><a href="${url}/add.html" target="dialog" class="btn btn_mini btn_add">新增字典</a></span></li>
			<li><span class="ipts"><a href="${url}/batchDelete" target="selectedTodo" rel="ids" postType="map" title="是否要批量删除" class="btn btn_mini btn_del">批量删除</a></span></li>
		</ul> --%>
		<table class="table" width="100%">
			<thead>
				<tr>
					<th><input type="checkbox" group="ids" class="ipt_checkbox checkboxCtrl"></th>
					<th>发起用户openId</th>
					<th>学校</th>
					<th>发起金额</th>
					<th>发起时间</th>
					<th>PK状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${obj.list}" var="one" >
					<tr>
						<td class="txtc"><input name="ids" value="${one.id}" type="checkbox" class="ipt_checkbox"></td>
						<td>${one.founder}</td>
						<td>${one.schoolName}</td>
						<td>${one.money}</td>
						<td>${one.createTime}</td>
						<td>
							<c:if test="${ one.status == 0}">
								未开始
							</c:if>
							<c:if test="${ one.status == 1}">
								正在运行
							</c:if>
							<c:if test="${ one.status == 2}">
								已结束
							</c:if>
						</td>
						<td>
							<a target="dialog" rel="dlgId1" href="${url}/signInByGameIdList.html?gameId=${one.id}" class="btn btn_mini btn_modify">查看详情</a>
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
