define(function (require) {

    /**
	 * @name Init
	 * @class 全站公用，页面初始化
	 * @requires jQuery
	 * @author 李博龙
	 * @version v1.0.0
	 */
    var $ = require('jquery');
    
    /*修改域名*/
	var we={vr:{}};
    /* 全局化常用方法 */
	window.we = we;
	$.extend(we,require('we'));
    window.$ajax = require('ajax');
    window.$dialog = require('dialog');
    window.$cookie = require('cookie');
    window.$select = require('select');
    window.$placeholder = require('placeholder');
	
	
    
    /* 扩展vko */
    we.utils = require('utils');
    we.ui = require('./we.ui');
    require('./we.utils');
    require('./we.fn.extend');
    
	/* 页面初始化 */
    $(function () {
    	var _doc = document;
    	
    	/* ie6背景图片缓存解决闪烁问题 */
    	if(we.browser.msie && we.browser.ie6){
    		try{
    			document.execCommand('BackgroundImageCache', false, true);
    		}catch(e){}
    	}
    	/* 清理浏览器内存,只对IE起效 */
    	if(we.browser.msie) {
    		try{ 
    			window.setInterval("CollectGarbage();", 10000);
    		}catch(e){}
    	}
    	
    	/* 添加自定义事件 */
    	var _doc = $(document);
		if (!_doc.isBind(we.eventType.pageClear)) {
			_doc.bind(we.eventType.pageClear, function(event){
				var box = event.target;
				//seajs.log('pageClear');
			});
		}
    	
    	/* 主题切换 */
    	require('theme');
    	if(!weFileLoader.isonline){
    		$('.head_quit:last').after('<a class="head_quit" target="navTab" href="html/home.html">API帮助</a>');
    	}
    	
//    	/*编辑器*/
//    	require.async('editor',function($ueditor){
//			$ueditor.init(
//					$('textarea.editor'), //要初始化编辑器  ，取到要初始化成编辑器的textarea元素对象
//					we.vr.www+'/upload.html' //上传的接口，原则上别动它。
//			);
//		});
        /* 浏览器resize */
        we.utils.winResize([{name:we.ui.getBrowserInfo, param:null},{name:initLayout, param:null}]);
        
        we.ui.container();
    	we.ui.init(document,function(){
    		$('#sidebar a[href]:first').attr('rel','home').css('background','none').click();
    	});
		
		$placeholder();
		
		/*----- 业务初始化 -----*/
		
		seajs.log('--- init complete ---');
		require('iconfig');
		
		/*快捷键设置*/
		/* 增加支持Ctrl+s提交表单操作 */
		$(document).bind('keydown.bmpkey',function(e){
			if(e.ctrlKey && e.keyCode==83){
				we.utils.stopBubble(e);
				we.utils.stopDefault(e);
				var $dlg = $.dialog.getCurrent(),
					$panel = $('.tabs_panel:visible'),$form;
				if($dlg.size()>0){
					$form = $('form',$dlg).not('#pagerForm').not($('.panel_search form',$dlg));
					if($form.size()==1)$('form',$dlg).submit();
					return;
				}
				if($panel.size()>0){
					$form = $('form',$panel).not('#pagerForm').not($('.panel_search form',$panel));
					if($('form',$panel).size()==1)$('form',$panel).submit();
				}
			}
		});
    });
    
    var initLayout = function(){
    	we.ui.header();
    	we.ui.sidebar();
    }
});