<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/share/500.jsp"%>
<%@include file="/WEB-INF/share/tld.jsp"%>
<div class="panel_box">
	<div class="panel_content nopadding">
		<form method="post" action="/share/user/add.json" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div class="form_item">
				<label class="form_label">用户名：</label>
              	<div class="form_ctrl">
                	<input name="username" type="text"  class="required chars {remote:'/share/user/isexists.json',messages:{remote:'用户名被占用，请换一个！'}}" maxlength="10"/>
              	</div>
            </div>
              	<div class="form_item">
				<label class="form_label">密码：</label>
				<div class="form_ctrl">
					<input type="password" name="pwd" class="required unchinese {rangelength:[6,18]}" placeholder="请输入"/>
					<span class="help-block">密码为6~18位哦~</span>
				</div>
			</div>
			<div class="form_item">
				<label class="form_label">确认密码：</label>
				<div class="form_ctrl">
					<input type="password" name="confirmpwd" class="required {rangelength:[6,18],equalTo:'[name=pwd]',messages:{equalTo:'两次输入不一致哦'}}" placeholder="请确认"/>
				</div>
			</div>
            <div class="form_actions">
              	<button type="submit" class="btn btn_add">保存</button>
				<button type="button" class="btn btn_del closeDialog">取消</button>
            </div>
          </form>
	</div>
</div>