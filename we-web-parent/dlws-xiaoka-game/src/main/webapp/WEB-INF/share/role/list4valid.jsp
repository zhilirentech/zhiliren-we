<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/share/500.jsp"%>
<jsp:include page="/WEB-INF/share/role/list.jsp" flush="true">
	<jsp:param value="/share/role/list4valid.html" name="url"/>
	<jsp:param name="mutilfunction" value="
		<li><a href='/share/role/add.html' target='dialog' rel='dlgId' title='添加' class='btn btn_mini btn_add'>添加</a></li>
			<li><a href='/share/role/batchinvalid.json' target='selectedTodo' rel='ids'  postType='map' title='确定要批量禁用？' class='btn btn_mini btn_invalid'>批量禁用</a></li>
	" />
	<jsp:param name="singlefunction" value="
		<a target='navTab' rel='share.role.listfunction'  href='/share/role/listfunction.json?id=#id' title='维护功能树' class='btn btn_mini btn_invalid'>维护功能树</a>
		<a target='ajaxTodo'  href='/share/role/invalid.json?id=#id' title='是否要禁用' class='btn btn_mini btn_invalid'>禁用</a>
	" />
</jsp:include>