<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/common/500.jsp"%>
<%@include file="/WEB-INF/common/tld.jsp"%>

<c:set var="url" value="${base}/admin/signIn" />

<form id="pagerForm" action="${url}/list.html" method="post">
	<input type="hidden" name="pageNumber" value="${obj.pager.pageNumber}" />
	<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
</form>

<div class="panel_search"> 
	<form action="${url}/list.html" method="post" onsubmit="return navTabSearch(this);">
		<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
		<input type="hidden" name="pageNumber" value="${obj.pager.pageNumber}" />
		<p><span class="tag">昵称：</span><span class="ipts"><input type="text" name="name" value="${obj.customerName}"/></span></p>
		<p><span class="ipts"><input class="btn btn_add" type="submit" value="检索"/></span></p>
		
	</form>
</div>


<div class="panel_box noborder">
	<div class="panel_content nopadding">
	
		<table class="table" width="100%">
			<thead>
				<tr>
					<th><input type="checkbox" group="ids" class="ipt_checkbox checkboxCtrl"></th>
					<th>昵称</th>
					<th>签到投入金额</th>
					<th>分成金额</th>
					<th>签到时间</th>
					<th>签到状态</th>
					<th>类型</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${obj.list}" var="one" >
					<tr>
						<td class="txtc"><input name="ids" value="${one.id}" type="checkbox" class="ipt_checkbox"></td>
						<td>${one.customerName}</td>
						<td>${one.inAmount}</td>
						<td>${one.outAmount}</td>
						<%-- <td>${one.signTime}</td> --%>
						<td><fmt:formatDate value="${one.signTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td><we:enum className="com.xiaoka.game.admin.signin.enums.SignStatus" key="${one.status}"/></td>
						<td><we:enum className="com.xiaoka.game.admin.signin.enums.SiginType" key="${one.type}"/></td>
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
