<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/share/500.jsp"%>
<jsp:include page="/WEB-INF/share/auth/list.jsp" flush="true">
	<jsp:param value="/share/user/show.html" name="url"/>
	<jsp:param name="mutilfunction" value="
		<li><a href='/share/auth/generate.json' target='ajaxTodo' rel='dlgId' title='重新生成权限' class='btn btn_add'>一键生成权限</a></li>
	" />
	<jsp:param name="singlefunction" value="
		<a target='navTab'  href='/share/user/managerole?userId=#id' title='维护角色' class='btn btn_mini btn_manage'>维护角色</a>
		<a target='ajaxTodo'  href='/share/user/invalid.json?id=#id' title='是否要禁用' class='btn btn_mini btn_invalid'>禁用</a>
	" />
</jsp:include>