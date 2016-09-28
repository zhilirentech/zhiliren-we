<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/common/500.jsp"%>
<%@include file="/WEB-INF/common/tld.jsp"%>

<c:set var="url" value="${base}/admin/robot" />

<form id="pagerForm" action="${url}/show.html" method="post">
	<input type="hidden" name="pageNumber" value="${obj.pager.pageNumber}" />
	<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
	<input type="hidden" name="openId" value="${obj.list[0].openId}" />
</form>

<div class="panel_box noborder">
	<div class="panel_content nopadding">
	
		<table class="table" width="100%">
			<thead>
				<tr>
					<!-- <th><input type="checkbox" group="ids" class="ipt_checkbox checkboxCtrl"></th> -->
					<th>投入金额</th>
					<th>分成金额</th>
					<th>签到时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${obj.list}" var="one" >
					<tr>
						<%-- <td class="txtc"><input name="ids" value="${one.id}" type="checkbox" class="ipt_checkbox"></td> --%>
						<td>${one.inAmount}</td>
						<td>${one.outAmount}</td>
						<td>${one.signDate}</td>
						<td>
							<c:if test="${one.outAmount == 0}">
								<a target="ajaxTodo" rel="dlgId1" href="${url}/delsign?id=${one.id}" title='是否要删除' class='btn btn_mini btn_del'>删除</a>
							</c:if>
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
