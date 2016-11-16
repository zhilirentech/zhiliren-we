<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/share/500.jsp"%>
<%@include file="/WEB-INF/share/tld.jsp"%>
<div class="panel_box">
	<div class="panel_content nopadding">
		<form method="post" action="/share/function/add.json" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input type="hidden" name="parentId" value="${obj }"/> 
			<div class="form_item">
				<label class="form_label">名称：</label>
              	<div class="form_ctrl">
                	<input name="name" type="text" class="required"/>
              	</div>
            </div>
            
            <div class="form_item">
				<label class="form_label">URL：</label>
              	<div class="form_ctrl">
                	<input name="url" type="text"/>
              	</div>
            </div>
            
            <div class="form_item">
				<label class="form_label">备注：</label>
              	<div class="form_ctrl">
                	<input name="remark" type="text"  maxlength="10"/>
              	</div>
            </div>
            <div class="form_item">
				<label class="form_label">排序号：</label>
              	<div class="form_ctrl">
                	<input class="number" name="orderNum" type="text"  maxlength="10" value="0"/>
              	</div>
            </div>
            <div class="form_actions">
              	<button type="submit" class="btn btn_add">保存</button>
				<button type="button" class="btn btn_del closeDialog">取消</button>
            </div>
          </form>
	</div>
</div>