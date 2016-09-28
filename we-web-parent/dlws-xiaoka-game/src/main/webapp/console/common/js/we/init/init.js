define('init',['jquery','ajax','config','utils','./we.utils','udialog','dialog','wedialog'],function (require) {

    /**
	 * @name Init
	 * @class 全站公用，页面初始化,全局化
	 * @requires jQuery
	 * @author 李博龙
	 * @version v1.0.0
	 */

    var $ = require('jquery'),
    	$ajax = require('ajax'),
    	$dialog = require('dialog'),
    	$udialog = require('udialog'),
    	$wedialog = require('wedialog'),
		we={};
    
    /* 全局化常用方法 */
	window.we = we;
    window.$ajax = $ajax;
    window.$dialog = $dialog;
    window.$udialog = $udialog;
    window.$wedialog = $wedialog;
    
//    $.fn.upload=$uploadify.init;
    
    /* 扩充we */
	
	$.extend(we,require('config'));
    we.utils = require('utils');
    require('./we.utils');
    we.utils.initUI();
    /* 扩展jQuery方法 */
    //TODO we.extend暂时注释掉
    //require('./we.extend');
    
    /* 页面初始化 */
    /*$(function () {
    	 ie6背景图片缓存解决闪烁问题 
    	if(we.browser.msie && we.browser.ie6){
    		try{
    			document.execCommand('BackgroundImageCache', false, true);
    		}catch(e){}
    	}
    	 清理浏览器内存,只对IE起效 
    	if(we.browser.msie) {
    		try{ 
    			window.setInterval("CollectGarbage();", 10000);
    		}catch(e){}
    	}
    	
         浏览器resize 
        we.utils.winResize({
            name: we.utils.getBrowserInfo,
            param: null
        });
        seajs.log('common init complete!')
    });*/
});
