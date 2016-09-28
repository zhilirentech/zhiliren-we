<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/common/500.jsp"%>
<%@include file="/WEB-INF/common/tld.jsp"%>
<div class="panel_box">
	<div class="panel_content nopadding">
		<%--
			form上的属性不要修改
		 --%>
		<form method="post" action="${base}/admin/bulletin/update.html" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div class="form_item">
				<label class="form_label">类型：</label>
			  	<div class="form_ctrl">
			  		<select name="type" class="required">
						<c:forEach var="map" items="${obj.bulletinTypeEnum}" >
							<c:choose>
							   <c:when test="${map.key == obj.bulletin.type}">
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
				<label class="form_label">内容：</label>
				<div class="form_ctrl">
					<textarea name="content" type="text" class="required" value="" id="editor">${obj.bulletin.content }</textarea>
				</div>
			</div>
			
			
            <div class="form_actions">
            	<input name="id" type="hidden" value="${obj.bulletin.id}"/>
              	<input type="submit" class="btn btn_add" value="保存"/>
				<button type="button" class="btn btn_del closeDialog">取消</button>
            </div>
          </form>
	</div>
</div>
<jsp:include page="/WEB-INF/common/webupload_resource.jsp"></jsp:include>
<%--  UEDITOR START --%>
<!-- 配置文件 -->
<script>window.PROJECT_CONTEXT = "${base}";</script>
<script type="text/javascript" src="${base}/common/js/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="${base}/common/js/ueditor/ueditor.all.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="${base}/common/js/ueditor/lang/zh-cn/zh-cn.js"></script>
    
<!-- 实例化编辑器 -->
<script type="text/javascript">
  $(document).ready(function() { 
      var ue = UE.getEditor('editor',{  
            wordCount:true, //开启字数统计  
            initialFrameWidth:550, //初始化编辑器宽度,默认1000
            initialFrameHeight:320, //初始化编辑器高度,默认320
            elementPathEnabled : false,//是否启用元素路径，默认是显示  
            maximumWords:10000,       //允许的最大字符数  
            initialContent:'',    //初始化编辑器的内容,也可以通过textarea/script给值，  
            autoClearinitialContent:false, //是否自动清除编辑器初始内容，注意：如果focus属性设置为true,这个也为真，那么编辑器一上来就会触发导致初始化的内容看不到了  
            pasteplain:false,  //是否默认为纯文本粘贴。false为不使用纯文本粘贴，true为使用纯文本粘贴  
       });  
  });
 </script>
 <%--  UEDITOR END --%>
<script type="text/javascript">
	$(document).ready(function(){
		var urllist = [] ;
		urllist.push('${obj.Carousel.url}');
		if($("#uploader_00").length>0){
			inituploader("","00",urllist);
		}
	});
</script>
