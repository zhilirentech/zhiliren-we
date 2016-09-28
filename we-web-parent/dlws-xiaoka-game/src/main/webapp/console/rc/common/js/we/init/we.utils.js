define(function (require, exports, module) {

    /**
     * @name we.utils
     * @class 扩展全局utils方法集。
     * @requires jQuery
     * @author 李博龙
     * @version v1.0.0
     */
	
    /*----- 扩展全局utils方法集 -----*/
	we.utils = $.extend(we.utils, {
	    /* 集体加hoverClass */
	    hoverClass: function(str){
			var array = new Array();
			array = str.split(",");
			for(var index in array){
				$(array[index]).hoverClass();
			}
		}
    });
});