<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/share/500.jsp"%>
<jsp:include page="/WEB-INF/share/user/list.jsp" flush="true">
	<jsp:param value="/share/user/list4invalid.html" name="url"/>
	<jsp:param name="mutilfunction" value="
		<li><a href='/share/user/batchvalid.json' target='selectedTodo' rel='ids'  postType='map' title='确定要批量启用？' class='btn btn_mini btn_add'>批量启用</a></li>
	" />
	<jsp:param name="singlefunction" value="
		<a target='ajaxTodo'  href='/share/user/valid.json?id=#id' title='是否要启用' class='btn btn_mini btn_add'>启用</a>
	" />
</jsp:include>