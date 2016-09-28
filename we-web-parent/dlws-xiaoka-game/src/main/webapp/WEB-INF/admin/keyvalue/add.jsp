<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/common/500.jsp"%>
<%@include file="/WEB-INF/common/tld.jsp"%>

<div class="panel_box">
	<div class="panel_content nopadding">
		<%--
			form上的属性不要修改
		 --%>
		<form method="post" action="${base}/admin/keyvalue/add.html" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div class="form_item">
				<label class="form_label">类型：</label>
			  	<div class="form_ctrl">
			  		<select name="name" class="required">
						<c:forEach var="map" items="${obj.keyValueEnum}" >
					        <option value="${map.key}">${map.value}</option>
						</c:forEach>  
					</select>
			  	</div>
			</div>
			<div class="form_item">
				<label class="form_label">开始时间：</label>
				<div class="form_ctrl">
					<input type="time" name="nameVal" class="required">
				</div>
			</div>
			<div class="form_item">
				<label class="form_label">结束时间：</label>
				<div class="form_ctrl">
				<input type="time" name="nameVal" class="required">
				</div>
			</div>
			
			
            <div class="form_actions">
              	<button type="submit" class="btn btn_add">保存</button>
				<button type="button" class="btn btn_del closeDialog">取消</button>
            </div>
          </form>
	</div>
</div>
<jsp:include page="/WEB-INF/common/webupload_resource.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function(){
		if($("#uploader_00").length>0){
			inituploader("","00",[]);
		}
	});
</script>