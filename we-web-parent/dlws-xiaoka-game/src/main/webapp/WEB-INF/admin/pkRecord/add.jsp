<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/common/500.jsp"%>
<%@include file="/WEB-INF/common/tld.jsp"%>
<div class="panel_box">
	<div class="panel_content nopadding">
		<%--
			form上的属性不要修改
		 --%>
		<form method="post" action="${base}/dict/info/add.html" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div class="form_item">
				<label class="form_label">字典类型：</label>
			  	<div class="form_ctrl">
			  		<select name="typeCode" class="required">
					<option value="-1">--请选择--</option>
					<c:forEach items="${obj}" var="pro" >
						<option value="${pro.typeCode}">${pro.typeName}</option>
					</c:forEach>
				</select>
			  	</div>
			</div> 
			<div class="form_item">
				<label class="form_label">字典代码：</label>
			  	<div class="form_ctrl">
			    	<input name="dictCode" class="required" type="text" maxlength="8"> 
			  	</div>
			</div>
			<div class="form_item">
				<label class="form_label">字典名称：</label>
				<div class="form_ctrl">
					<input name="dictName" type="text" class="required" maxlength="32"/>
				</div>
			</div>
			
			<div class="form_item">
				<label class="form_label">描述：</label>
				<div class="form_ctrl">
					<textarea name="description" maxlength="64"></textarea>
				</div>
			</div>
			
            <div class="form_actions">
              	<button type="submit" class="btn btn_add">保存</button>
				<button type="button" class="btn btn_del closeDialog">取消</button>
            </div>
          </form>
	</div>
</div>