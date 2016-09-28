<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/common/500.jsp"%>
<%@include file="/WEB-INF/common/tld.jsp"%>
<div class="panel_box">
	<div class="panel_content nopadding">
		<form method="post" action="${base}/admin/gameLevel/update.html" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<input name="id" type="hidden" value="${obj.gameLevel.id}"/>
			
			<div class="form_item">
				<label class="form_label">等级名称：</label>
			  	<div class="form_ctrl">
			    	<input name="levelName" type="text" class="required"  maxlength="10" value="${obj.gameLevel.levelName}"/>
			  	</div>
			</div>
			<div class="form_item">
				<label class="form_label">达到等级需连续签到天数:</label>
			  	<div class="form_ctrl">
			    	<input name="levelNo" type="text" class="required"  maxlength="10" value="${obj.gameLevel.levelNo}"/>
			  	</div>
			</div>
			<div class="form_item">
				<label class="form_label">未达到图标：</label>
				<div class="form_ctrl" >
					<input type="hidden" id="uploader_00_picture" name="grayIcon" value="${obj.gameLevel.grayIcon}"/>
			        <div class="wu-example" id="uploader_00" style="width:300px;height:200px;">
			        	<div id="imgUrlMessage" name="imgUrlMessage"></div> 
			        </div>
				</div>
			</div>
			<div class="form_item">
				<label class="form_label">达到图标：</label>
				<div class="form_ctrl" >
					<input type="hidden" id="uploader_01_picture" name="brightIcon" value="${obj.gameLevel.brightIcon}"/>
			        <div class="wu-example" id="uploader_01" style="width:300px;height:200px;">
			        	<div id="imgUrlMessage" name="imgUrlMessage"></div> 
			        </div>
				</div>
			</div>
			<br>	<br>
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
	var urllist = [] ;
	urllist.push('${obj.gameLevel.grayIcon}');
	var teacherlist = [] ;
	teacherlist.push('${obj.gameLevel.brightIcon}');
	if($("#uploader_00").length>0){
		inituploader("uploader_00_picture","00",urllist);
	}
	if($("#uploader_01").length>0){
		inituploader("uploader_01_picture","01",teacherlist);
	}
});
</script>