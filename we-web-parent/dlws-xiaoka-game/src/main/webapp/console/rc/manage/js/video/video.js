define(function (require, exports, module) {
		var init = function(){
			$("input[name=status]:radio").live("is.Checked",function(){
				$("#searchForm").submit();
			});
			$('.form_ctrl label').live('click',function(){
				$(this).find($("input[name=status]:radio")).iCheck('check');
			})
			require.async('icheck',function($icheck){
				$icheck.init({
					items:$(':checkbox.custom-iCheck,:radio.custom-iCheck'),   			// 【必填】
					// 要美化的元素
					type: 'square',  		// 【选填】 美化的类型
					color: 'custom',			// 【选填】 美化时使用的颜色
					increaseArea: '20%'    // 【选填】 覆盖周围的大小百分比
				});
			});
			
			$("#functTree a").bind("click",function(){
				var knowledgeId=$(this).attr("tvalue");
				$("input[type='hidden'][name='knowledgeId']").attr("value",knowledgeId);
				$("#searchForm").submit();
			});
			
			$(".selectChange").live("change",function(){
				$("#searchForm").submit();
			});
		}
		
		return {
			init:init
		}
		
});