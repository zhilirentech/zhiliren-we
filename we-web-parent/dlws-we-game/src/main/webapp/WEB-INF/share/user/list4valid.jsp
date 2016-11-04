<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/share/500.jsp"%>
<jsp:include page="/WEB-INF/share/user/list.jsp" flush="true">
	<jsp:param value="/share/user/list4valid.html" name="url"/>
	<jsp:param name="mutilfunction" value="
		<li><a href='/share/user/add.html' target='dialog' rel='dlgId' title='添加' class='btn btn_mini btn_add'>添加</a></li>
			<li><a href='/share/user/batchinvalid.json' target='selectedTodo' rel='ids'  postType='map' title='确定要批量禁用？' class='btn btn_mini btn_invalid'>批量禁用</a></li>
	" />
	<jsp:param name="singlefunction" value="
	    <a href='/share/user/modify?userId=#id' target='dialog' rel='dlgId' title='修改密码'  class='btn btn_mini btn_add'>修改密码</a>
		<a target='navTab'  href='/share/user/managerole?userId=#id' title='维护角色' class='btn btn_mini btn_manage'>维护角色</a>
		<a target='ajaxTodo'  href='/share/user/invalid.json?id=#id' title='是否要禁用' class='btn btn_mini btn_invalid'>禁用</a>
	" />
</jsp:include>