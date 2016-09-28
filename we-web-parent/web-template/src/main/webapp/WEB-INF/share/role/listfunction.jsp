<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/share/500.jsp"%>
<%@include file="/WEB-INF/share/tld.jsp"%>
<!-- 这个必须有 -->
<div class="panel_box">
	<div class="panel_content">
	<form method="post" action="/share/role/updatefunctions.json" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="roleId" value="${obj.roleId }"/>
			<ul id="functTree" class="tree treeFolder treeCheck expand" style="padding-top: 0x" treeMenu="managetree">
				<we:tree items="${obj.list }" var="por">
					<a tname="functionIds" tvalue="${por.id}" <c:if test="${por.checked }">checked="checked"</c:if> >${por.name }</a>
				</we:tree>
			</ul>
			 <div class="form_actions">
              	<button type="submit" class="btn btn_add">保存</button>
				<button type="button" class="btn btn_del closeDialog">取消</button>
            </div>
		</form>
	</div>
</div>