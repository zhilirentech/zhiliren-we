define('wedialog',['we/plugins/artdialog/1.0.0/js/dialog-plus.js','we/plugins/artdialog/1.0.0/js/dialog.js','we/plugins/artdialog/1.0.0/css/ui-dialog.css'],function (require, exports, module) {
	
	/**
     * @name 		Dialog
     * @class 		提供 dialog 对话框组件。
     * @requires 	jquery, select
     * @author 		汪洋
     * @version 	v1.0.0
     */
	
	require('./js/dialog.js');
	require('./js/dialog-plus.js');
	require('./css/ui-dialog.css');
	
	var dialog = function(options){
		return $.wedialog(options);
	}
	
	return{
		dialog:$.wedialog.dialog,
		alert:$.wedialog.alert,
		confirm:$.wedialog.confirm,
		prompt:$.wedialog.prompt,
		follow:$.wedialog.follow
	}
});