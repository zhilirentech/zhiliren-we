<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/common/500.jsp"%>
<%@include file="/WEB-INF/common/tld.jsp"%>
<div class="panel_box">
	<div class="panel_content nopadding">
	
		<form method="post" action="${base}/admin/authority/user/add.html" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div class="form_item">
				<label class="form_label">登录名：</label>
			  	<div class="form_ctrl">
			    	<input name="username" type="text" class="required"  maxlength="10"/>
			  	</div>
			</div>
			<div class="form_item">
				<label class="form_label">登录密码：</label>
				<div class="form_ctrl">
					<input name="password" type="password" class="required" maxlength="32"/>
				</div>
			</div>
			
			<div class="form_item">
				<label class="form_label">用户类型：</label>
				<div class="form_ctrl">
					<select id="select_userType" name="userType" class="required">
						<c:forEach var="map" items="${obj.userTypeEnum}" >
							<c:choose>
							   <c:when test="${map.key == 1}">
							   		<option value="${map.key}" selected="selected">${map.value}</option>
							   </c:when>
							   <c:otherwise>
							   		<option value="${map.key}">${map.value}</option>
							   </c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="form_item">
				<label class="form_label">用户状态：</label>
				<div class="form_ctrl">
					<select id="select_status" name="status" class="required">
						<c:forEach var="map" items="${obj.userStatusEnum}" >
							<c:choose>
							   <c:when test="${map.key == 1}">
							   		<option value="${map.key}" selected="selected">${map.value}</option>
							   </c:when>
							   <c:otherwise>
							   		<option value="${map.key}">${map.value}</option>
							   </c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			
            <div class="form_actions">
              	<button type="submit" class="btn btn_add">保存</button>
				<button type="button" class="btn btn_del closeDialog">取消</button>
            </div>
          </form>
	</div>
</div>