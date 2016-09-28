<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/common/500.jsp"%>
<%@include file="/WEB-INF/common/tld.jsp"%>
<div class="panel_box">
	<div class="panel_content nopadding">
		<%--
			form上的属性不要修改
		 --%>
		<form method="post" action="${base}/admin/keyvalue/update.html" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div class="form_item">
				<label class="form_label">类型：</label>
			  	<div class="form_ctrl">
			  		<select name="name" class="required">
						<c:forEach var="map" items="${obj.keyValueEnum}" >
							<c:choose>
							   <c:when test="${map.key == obj.keyValue.name}">
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
				<label class="form_label">开始时间：</label>
				<div class="form_ctrl">
					<input type="time" name="nameVal" class="required" value=${obj.strs[0] }>
				</div>
			</div>
			<div class="form_item">
				<label class="form_label">结束时间：</label>
				<div class="form_ctrl">
				<input type="time" name="nameVal" class="required" value=${obj.strs[1] }>
				</div>
			</div>
			
			
            <div class="form_actions">
            	<input name="id" type="hidden" value="${obj.keyValue.id}"/>
              	<input type="submit" class="btn btn_add" value="保存"/>
				<button type="button" class="btn btn_del closeDialog">取消</button>
            </div>
          </form>
	</div>
</div>
<jsp:include page="/WEB-INF/common/webupload_resource.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function(){
		var urllist = [] ;
		urllist.push('${obj.Carousel.url}');
		if($("#uploader_00").length>0){
			inituploader("","00",urllist);
		}
	});
</script>
