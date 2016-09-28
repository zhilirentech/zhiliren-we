define(function (require, exports, module) {
	require('./js/uploadify');
	require('./css/uploadify.css');
	var uploadify = function(options){
		var op = $.extend({
			id:'#file',
		    swf:we.vr.static+'/common/js/we/plugins/uploadify/1.0.0/uploadify.swf',
			method:'post',
			width:120,
			height:30,
			btnText:'点我上传',
			type:'*.gif; *.jpg; *.png',
			auto:true,
			multi:false,
			random:false,
			removeList:false,
			rate:'percentage',
			queue:2,
			removeTimeout:0,
			size:'2MB',
			swfOk:function(){
				seajs.log('swf载入成功');
			},
			onInit:function(){
				seajs.log('上传文件初始化成功');
			},
			start:function(file){
				seajs.log('开始上传啦！');
			},
			success:function(file, data, response){
				seajs.log('文件上传成功啦！');
			},
			error:function(file, errorCode, errorMsg, errorString){
				seajs.log('文件上传失败啦！');
			},
			selError:function(file, errorCode, errorMsg){
				var error = {
						size:'File size exceeds allowed limit.',
						type:'File is not an allowed file type.',
						queue:0 
				}
				var udig = function(){
					$udialog.alert({
						skin:4,
						type:'warning',
						content:errorMsg
					})
				}
				this.queueData.errorMsg = '不知道那里出错了呢，看我上面提示吧^_^'; 
				if(errorMsg == error.size){
					errorMsg = '请选择小于'+op.size+'的文件';
					udig();
				}
				if(errorMsg == error.type){
					errorMsg = '请上传'+op.type+'类型的文件';
					udig();
				}
				if(errorMsg == error.queue){
					errorMsg = '上传队列超出'+op.queue+'列的限制';
					udig();	
				}
				seajs.log(errorMsg);
			},
			disable:true
		},options);
		
		$(op.id).uploadify({
	        'swf'            : op.swf,//uploadify.swf 文件的相对路径
	        'uploader'       : op.url,//后台处理程序的相对路径
	        'method'         : op.method,      //向后台脚本放送数据的表单方法
	        'width'          : op.width,//设置文件浏览按钮的宽度
	        'height'         : op.height,
	        'fileObjName'    : op.inputName,//文件上传对象的名称
	        'buttonText'     : op.btnText,//浏览按钮的文本
	        'fileTypeExts'   : op.type,//文件的类型
	        'auto'           : op.auto,//是否自动上传true/false
	        'multi'          : op.multi,  //是否上传多张图片true/false
	        'preventCaching' : op.random,	//是否添加随机字符串
	        'progressData'	 : op.rate,//设置上传进度显示方式，percentage显示上传百分比，speed显示上传速度
	        'queueSizeLimit' : op.queue,//上传队列中最多显示的任务数量
	        'removeCompleted': op.removeList,//上传后移除列表
	        'removeTimeout'  : op.removeTimeout,//从完成到被移除的时间间隔
	        'fileSizeLimit'  : op.size,//上传文件的大小限制
	        'uploadLimit'	 : op.length,//最大上传文件数量
	        'formData'       : op.formData,//JSON格式上传每个文件的同时提交到服务器的额外数据，可在’onUploadStart’事件中使用’settings’方法动态设置
	        'onSWFReady'	 : op.swfOk,//Flash文件载入成功后触发
	        'onInit'		 : op.onInit,//首次初始化Uploadify结束时触发
	        'onDialogOpen'   : op.open,//当文件选择对话框弹出时立即出发
	        'onUploadStart'	 : op.start,//当文件即将开始上传时立即触发
	        'onUploadSuccess': op.success,//当文件上传成功时触发
	        'onUploadError'	 : op.error,//文件上传出错时触发，参数由服务端程序返回
	        'onSelectError'	 : op.selError,//选择文件后向队列中添加每个上传任务时如果失败都会触发
	        'destroy'		 : op.destroy,//销毁Uploadify实例并将文件上传按钮恢复到原始状态
	        'disable'		 : op.disable,//禁用或启用文件浏览按钮
	    });
	}
	
	return{
		uploadify:uploadify
	}
	
});