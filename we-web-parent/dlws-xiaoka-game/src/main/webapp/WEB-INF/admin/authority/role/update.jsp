<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/common/500.jsp"%>
<%@include file="/WEB-INF/common/tld.jsp"%>
<div class="panel_box">
	<div class="panel_content nopadding">
		<form method="post" action="${base}/admin/authority/role/update.html" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div class="form_item">
				<label class="form_label">角色名：</label>
			  	<div class="form_ctrl">
			  		<%-- 角色id --%>
			  		<input name="id" type="hidden" value="${obj.role.id}"/>
			  		<%-- 角色功能id --%>
			  		<input name="functionIds" type="hidden" value=""/>
			    	<input name="name" type="text" class="required"  maxlength="10" value="${obj.role.name}"/>
			  	</div>
			</div>
			<div class="form_item">
				<label class="form_label">备注：</label>
				<div class="form_ctrl">
					<input name="remark" type="text" class="required" maxlength="32" value="${obj.role.remark}"/>
				</div>
			</div>
			
			<div class="form_item">
				<label class="form_label"><button type="button" class="btn btn_modify" onclick="initTree()">功能</button></label>
				<div class="form_ctrl">
						<div id="ztreeDiv">
							<c:forEach var="p" items="${obj.list}">
								<li type="hidden" id="${p.id }" pId="${p.parentId }" name="${p.name }" check="${p.checked}"/>
							</c:forEach>
						</div>
						<ul id="treeDemo" class="ztree"></ul>
				</div>
			</div>
			
            <div class="form_actions">
              	<button type="submit" class="btn btn_add" onclick="setFunc()">保存</button>
				<button type="button" class="btn btn_del closeDialog">取消</button>
            </div>
          </form>
	</div>
</div>
<script>
seajs.use('${static_path}/xiaoka/dlws-xiaoka-game/authority/role/update');

var setting = {
		check: {
			enable: true,
			chkStyle: "checkbox",
			chkboxType: { "Y": "ps", "N": "ps" }
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};
		
	function initTree(){
		if(!we.utils.isEmpty($("#treeDemo").html())){
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			treeObj.expandAll(false);
			return ;
		}
		
		//获取zNodes
		var zNodes = new Array();
		$('#ztreeDiv li').each(function(){
			var $this = $(this);
			var node =  { id:$this.attr('id'), pId:$this.attr('pId'), name:$this.attr('name'),open:"true",checked:$this.attr('check')};
			//每个节点
			we.utils.pushOnly(node,zNodes);
		});
		
		//调用初始方法
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	}

	function setFunc(){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes =  treeObj.getCheckedNodes(true);
		
		var funcIds = "" ;
		for(var i = 0 ; i < nodes.length; i ++){
			funcIds += (nodes[i].id + ",") ;
		}
		$("input[name='functionIds']").val(funcIds) ;
	}
</script>