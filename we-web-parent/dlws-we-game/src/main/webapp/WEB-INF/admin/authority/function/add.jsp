<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/common/500.jsp"%>
<%@include file="/WEB-INF/common/tld.jsp"%>
<div class="panel_box">
	<div class="panel_content nopadding">
	
		<form method="post" action="${base}/admin/authority/function/add.html" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div class="form_item">
				<label class="form_label">功能名称：</label>
			  	<div class="form_ctrl">
			    	<input name="name" type="text" class="required"  maxlength="10"/>
			  	</div>
			</div>
			<div class="form_item">
				<label class="form_label">访问地址：</label>
				<div class="form_ctrl">
					<input name="url" type="text" maxlength="64"/>
				</div>
			</div>
			
			<div class="form_item">
				<label class="form_label">上级功能：</label>
				<div class="form_ctrl">
					<select name="parentId">
						<option value="-1">--请选择--</option>
						<c:forEach items="${obj}" var="pro" >
							<option value="${pro.id}">${pro.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="form_item">
				<label class="form_label">功能等级：</label>
				<div class="form_ctrl">
					<input name="level" type="text" class="required" maxlength="2"/>
				</div>
			</div>
			
			<div class="form_item">
				<label class="form_label">序号：</label>
				<div class="form_ctrl">
					<input name="sort" type="text" class="required number" maxlength="4"/>
				</div>
			</div>
			
			<div class="form_item">
				<label class="form_label">备注：</label>
				<div class="form_ctrl">
					<input name="remark" type="text" class="required" maxlength="32"/>
				</div>
			</div>
			
            <div class="form_actions">
              	<button type="submit" class="btn btn_add">保存</button>
				<button type="button" class="btn btn_del closeDialog">取消</button>
            </div>
          </form>
	</div>
</div>