define(function(require, exports, module) {
	var init = function(){
		$(function(){
			$('.uploadImg').live('click',function(){
				if(we.utils.isEmpty($('[name=img]').val())){
					$dialog.alert('请先上传图片','warning');
					return ;
				}
				$ajax.weSubmit($('#uploadImgForm'),{
					url:"/activityphoto/upload.json",
					success:function(data){
						if(we.utils.isEmpty(data)){
							$dialog.alert('上传图片失败','error');
							return;
						}
						$dialog.alert('上传图片成功','success');
						$('[name=url]').val(data.url);
						$('.uploadImgName').val(data.name);
					},
					error:function(){
						$dialog.alert('上传图片失败','error');
					}
				});
			});
			$('.submit').click(function(){
				$('[name=img]').remove();
				$ajax.weSubmit($('#uploadImgForm'),{
					success:function(data){
						$dialog.alert('上传成功','success');
						$('.closeDialog').click();
					},
					error:function(){
						$dialog.alert('上传失败','error');
					}
				});
			});
		});
	}
	return {
		init:init
	}
});
