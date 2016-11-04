<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/common/500.jsp"%>
<%@include file="/WEB-INF/common/tld.jsp"%>

<div class="panel_box">
	<div class="panel_content nopadding">
		<%--
			form上的属性不要修改
		 --%>
		<form method="post" action="${base}/admin/banner/add.html" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div class="form_item">
				<label class="form_label">标题：</label>
				<div class="form_ctrl">
					<input name="title" type="text" class="required"/>
				</div>
			</div>
			
			<div class="form_item">
				<label class="form_label">类型：</label>
			  	<div class="form_ctrl">
			  		<select name="type" class="required">
						<c:forEach var="map" items="${obj.bannerTypeEnum}" >
					        <option value="${map.key}">${map.value}</option>
						</c:forEach>  
					</select>
			  	</div>
			</div>
			<div class="form_item">
				<label class="form_label">图片：</label>
				<div class="form_ctrl">
					<%-- uploader :
					--%>
					<input type="hidden" id="webupload_picture" name="imgUrl" value=""/>
			        <div class="wu-example" id="uploader_00">
			        	<div id="imgUrlMessage" name="imgUrlMessage"></div> 
			        </div>
					<%-- uploader --%>
				</div>
			</div>
			<div class="form_item">
				<label class="form_label">序号：</label>
				<div class="form_ctrl">
					<input type="number" name="sort" type="number" min="1" class="required number" maxlength="5"/>
				</div>
			</div>
			<div class="form_item">
				<label class="form_label">状态：</label>
			  	<div class="form_ctrl">
			  		<select name="status" class="required">
						<c:forEach var="map" items="${obj.bannerStatusEnum}" >
					        <option value="${map.key}">${map.value}</option>
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
<jsp:include page="/WEB-INF/common/webupload_resource.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function(){
		if($("#uploader_00").length>0){
			inituploader("","00",[]);
		}
	});
</script>