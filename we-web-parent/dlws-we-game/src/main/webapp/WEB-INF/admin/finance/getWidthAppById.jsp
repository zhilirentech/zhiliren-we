<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/common/500.jsp"%>
<%@include file="/WEB-INF/common/tld.jsp"%>
<div class="panel_box">
	<div class="panel_content nopadding">
	
		<form method="post" action="${base}/admin/authority/function/add.html" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			<div class="form_item">
				<label class="form_label">提现人姓名：</label>
			  	<div class="form_ctrl">
			    	<input name="name" type="text"  disabled="disabled" value="${obj.customerName }"  maxlength="10"/>
			  	</div>
			</div>
			<div class="form_item">
				<label class="form_label">提现金额：</label>
				<div class="form_ctrl">
					<input name="url" type="text" id="checkMoney" disabled="disabled" value="${obj.money }" maxlength="64"/>
				</div>
			</div>
			
			<div class="form_item">
				<label class="form_label">提现状态：</label>
				<div class="form_ctrl">
					<c:if test="${obj.checkStatus==0}">
						<input name="level" type="text" disabled="disabled"  value="未审核"  maxlength="2"/>
					</c:if>
					<c:if test="${obj.checkStatus==1}">
						<input name="level" type="text" disabled="disabled"  value="通过"  maxlength="2"/>
					</c:if>
					<c:if test="${obj.checkStatus==2}">
						<input name="level" type="text" disabled="disabled"  value="不通过"  maxlength="2"/>
					</c:if>
				</div>
			</div>
			
			<div class="form_item">
				<label class="form_label">审核信息：</label>
				<div class="form_ctrl">
					<input name="sort" id="checkInfo" type="text"  value="${obj.checkInfo }"  maxlength="4"/>
				</div>
			</div>
			<input type="hidden" id="checkId" value="${obj.id}">
			<input type="hidden" id="openId" value="${obj.openId}">
            <div class="form_actions">
              	<button type="button" onclick="checkSuccess();" class="btn btn_add closeDialog">审核通过</button>
				<button type="button" onclick="checkFail();" class="btn btn_del closeDialog">审核不通过</button>
            </div>
          </form>
          <form id="todoForm" action="${base}/admin/finance/list.html">
		  </form>
	</div>
</div>
<script type="text/javascript">
	/* 审核失败 */
	function checkFail(){
		var checkId = $("#checkId").val();
		var checkInfo = $("#checkInfo").val();
		if(""==checkInfo.trim()||null==checkInfo){
			alert("请输入审核不通过原因!");
			return false;
		}
		$.ajax({
			url: "${path}/admin/finance/ajax/checkFail.html",
			type: "POST",
			dataType: "json",
			cache: false,
			data:{
				id:checkId,checkInfo:checkInfo
			},
			success: function(data) {
				 $("#todoForm").submit();
			},
			error: function() {
				alert("审核异常")
			}
		});
	}
	/* 审核成功 */
	function checkSuccess(){
		
		var checkId = $("#checkId").val();
		var checkMoney = $("#checkMoney").val();
		var openId = $("#openId").val();
		$.ajax({
			url: "${path}/admin/finance/ajax/checkSuccess.html",
			type: "POST",
			dataType: "json",
			cache: false,
			data:{
				id:checkId,openId:openId,checkMoney:checkMoney
			},
			success: function(data) {
				if(!data.flag){
					alert(data.msg);
				}else{
					alert(data.msg);
				}
			},
			error: function() {
				alert("审核异常")
			}
		});
	}

</script>