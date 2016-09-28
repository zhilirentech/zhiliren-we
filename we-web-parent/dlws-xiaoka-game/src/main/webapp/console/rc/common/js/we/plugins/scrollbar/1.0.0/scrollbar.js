define(function(require, exports, module){
	
	/**
     * @name Scrollbar
     * @class 提供系统scrollbar美化方法。
     * @requires jQuery
     * @author 李博龙
     * @version v1.0.1
     * @example 
     * define(function(require) {
     *     var $scrollbar = require('scrollbar');
     *     $scrollbar('.pop-plan .content');
     * });
     */
	var $ = require('jquery');
	require('./perfect-scrollbar');
	
	var initBar = function(item, speed, propagation){
		var $item = (typeof item=='string'?$(item):item);
		
		if(we.utils.isEmpty(item) || $(item).size<1)return;
		if(we.utils.isEmpty(speed)) speed = 100;
		if(we.utils.isEmpty(propagation)) propagation = true;
		$item.perfectScrollbar({
			wheelSpeed: speed,
			wheelPropagation: propagation
		});
	}
	
	var updateBar = function(item){
		var $item = (typeof item=='string'?$(item):item);
		$item.perfectScrollbar('update');
	}
	
	var destroyBar = function(item){
		var $item = (typeof item=='string'?$(item):item);
		$item.perfectScrollbar('destroy');
	}
	
	return {
		init: initBar,
		update: updateBar,
		destroy: destroyBar
	}
});