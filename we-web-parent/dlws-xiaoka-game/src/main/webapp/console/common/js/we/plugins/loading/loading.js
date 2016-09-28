define('loading',function (require, exports, module) {
	require('./js/msgbox.js');
	require('./js/msgbox.css');
	
	var Init = function(){
		this.tip = "正在加载中，请稍后...";
	}
	Init.prototype.add = function(){
		ZENG.msgbox.show(this.tip, 6);
	}
	Init.prototype.remove = function(){
		ZENG.msgbox._hide();
	}
	var init = new Init();
	return{
		init:init
	}

});