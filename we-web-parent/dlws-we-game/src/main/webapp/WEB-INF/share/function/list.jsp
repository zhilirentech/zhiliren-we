<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/share/500.jsp"%>
<%@include file="/WEB-INF/share/tld.jsp"%>
<!-- 这个必须有 -->
	<form id="pagerForm" method="post" action="/share/function/list.html">
<div class="panel_box">
	<div class="panel_content">
		<ul id="functTree" class="tree treeFolder expand" style="padding-top: 0x" treeMenu="managetree">
			<we:tree items="${obj }" var="por">
				<a tname="authIds" tvalue="${por.id}" <c:if test="${por.checked }">checked="checked"</c:if> >${por.name }</a>
			</we:tree>
		</ul>
		<!-- see new_tree.html -->
		<ul class="managetree">
			<!-- {id}是占位符，  这个就是节点对应 的id，即${por.id} -->
			<li><a href="/share/function/add.html?id={id}" target="dialog" rel="dlgId" title="添加">添加</a></li>
			<li><a target='navTab'  href='/share/function/manageauth.html?functionId={id}' rel="dlgId" title='维护权限'>维护权限</a></li>
			<li><a href="/share/function/modify.html?id={id}" target="dialog" rel="dlgId" title="修改">修改</a></li>
			<li><a href="/share/function/delete.html?id={id}" target="ajaxTodo" rel="dlgId" title="删除">删除</a></li>
		</ul>
	</div>
</div>