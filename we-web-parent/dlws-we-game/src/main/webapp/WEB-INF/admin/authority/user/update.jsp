<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/common/500.jsp"%>
<%@include file="/WEB-INF/common/tld.jsp"%>
<div class="panel_box">
	<div class="panel_content nopadding">
		<form method="post" action="${base}/admin/authority/user/update.html" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input name="user.id" type="hidden" value="${obj.user.id}"/>
			
			<div class="form_item">
				<label class="form_label">登录名：</label>
			  	<div class="form_ctrl">
			    	<input name="user.username" type="text" class="required"  maxlength="10" value="${obj.user.username}"/>
			  	</div>
			</div>
			<div class="form_item">
				<label class="form_label">登录密码：</label>
				<div class="form_ctrl">
					<input name="user.password" type="password" maxlength="32" />&nbsp;&nbsp;&nbsp;&nbsp;如果不填写密码，表示不修改原密码
				</div>
			</div>
			
			<div class="form_item">
				<label class="form_label">用户类型：</label>
				<div class="form_ctrl">
					<select id="select_userType" name="user.userType" class="required">
						<c:forEach var="map" items="${obj.userTypeEnum}" >
							<c:choose>
							   <c:when test="${map.key == obj.user.userType}">
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
					<select id="select_status" name="user.status" class="required">
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
			
			<div class="form_item">
				<label class="form_label">角色设置：</label>
				<div class="form_ctrl">
					<c:forEach items="${obj.roleList}" var="each" varStatus="st" >
						<label>
							<c:choose> 
							<c:when test="${each.checked == true}"> 
								<input name="user.map[${st.index}].roleId" type="checkbox" value="${each.id}" checked="checked"/>${each.name}
							</c:when> 
							<c:otherwise> 
								<input name="user.map[${st.index}].roleId" type="checkbox" value="${each.id}"/>${each.name}
							</c:otherwise> 
							</c:choose> 
						</label>
						<c:if test="${st.count % 6 == 0}"> 
							<br>
						</c:if> 
					</c:forEach>
				</div>
			</div>
			
            <div class="form_actions">
              	<button type="submit" class="btn btn_add ">保存</button>
				<button type="button" class="btn btn_del closeNavTab">取消</button>
            </div>
          </form>
	</div>
</div>