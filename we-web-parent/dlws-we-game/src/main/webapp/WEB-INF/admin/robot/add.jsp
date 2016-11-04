<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/common/500.jsp"%>
<%@include file="/WEB-INF/common/tld.jsp"%>
<div class="panel_box">
	<div class="panel_content nopadding">
	
		<form method="post" action="${base}/admin/robot/add.html" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		  	<div class="form_item">
				<label class="form_label">开始参于日期：</label>
				<div class="form_ctrl">
					<input type="text" name="beginDate" class="date dateicon required" minDate="%y-%M-{%d+1}" readonly="readonly"/>
				</div>
			</div>
			<div class="form_item">
				<label class="form_label">参于天数：</label>
				<div class="form_ctrl">
					<input name="days" type="number" min="1" class="required" maxlength="4"/>
				</div>
			</div>
			
			<div class="form_item">
				<label class="form_label">参于金额：</label>
				<div class="form_ctrl">
					<input name="inAmount" type="double" class="required number" />
				</div>
			</div>
			
			<div class="form_item">
				<label class="form_label">状态：</label>
				<div class="form_ctrl">
					<select id="select_status" name="status" class="required">
						<c:forEach var="map" items="${obj.robotStatusEnum}" >
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
				<button type="cancel" class="btn btn_del closeDialog">取消</button>
            </div>
          </form>
	</div>
</div>