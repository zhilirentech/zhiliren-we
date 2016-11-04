define(function (require, exports, module) {
	$(function() { 
		/**
		 * 为卡的类型添加单击事件
		 */
		$(".carType").live("click",function(){
			var carType=$(this).attr("typeValue");
			$("input[name=cardType]").val(carType);
			$(".submitBtn").click();
		});
		
		/**
		 * 重置密码
		 */
		$(".resetpass").live("click",function(){
			var $this = $(this);
			var userId = $this.attr('follow');
			$ajax.weAjax({
				url:'/query/resetPass.json',
				data:{
					userId:userId
				},
				success:function(data){
				},
				error:function(data){
					$udialog.alert({
						content:data.message,
						type:'error',
						padding:'35px 40px'
					});
				}
			});
			
			
			
			
			
			
			
			
			
			
			
		});
	});
});