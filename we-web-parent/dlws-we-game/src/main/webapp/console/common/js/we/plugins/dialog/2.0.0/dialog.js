define('udialog',['jquery','we/plugins/dialog/2.0.0/jquery-dialog/jquery-dialog-plugins','we/plugins/dialog/2.0.0/jquery-dialog/jquery-dialog','we/plugins/dialog/2.0.0/dialog.css'],function (require, exports, module) {
	
	/**
     * @name Dialog
     * @class 提供 dialog 对话框组件。
     * @requires jquery, select
     * @author 李博龙
     * @version v1.0.0
     */
	var $ = require('jquery');
	require('./jquery-dialog/jquery-dialog');
	require('./jquery-dialog/jquery-dialog-plugins');
	require('./dialog.css');
	
	var init = function(options){
		var op = $.extend({
			skin : 4
		},options);
		var dlgHtml = $('.d-outer');
		dlgHtml.each(function(){
			var dlgPar = $(this).parent();
			var dlgFoot = dlgPar.find('.d-button');
			var dlgBtn = dlgPar.find('.d-buttons');
			
			if(!dlgFoot.parent().hasClass('red-btn')){
				dlgFoot.addClass('mn-bg');
				dlgFoot.wrap('<div class="red-btn"></div>');
				dlgFoot.before('<span class="lt-bg"></span>');
				dlgFoot.after('<span class="rt-bg"></span>');
			}
			if(!dlgPar.hasClass('dialogBlue') || !dlgPar.hasClass('dialogWhite') || !dlgPar.hasClass('dialogLoad') || !dlgPar.hasClass('dialogBtnBule')){
				if(op.skin==1){
					dlgPar.addClass('dialogBlue');
				}
				if(op.skin==2){
					dlgPar.addClass('dialogWhite');
				}
				if(op.skin==3){
					dlgPar.addClass('dialogLoad');
				}
				if(op.skin==4){
					dlgPar.addClass('dialogBtnBule');
				}
			}
			
		})
		
		/*function addBtn(){
			var iptHtml = $('.d-button');
			iptHtml.addClass('mn-bg');
			iptHtml.wrap('<div class="red-btn"></div>');
			iptHtml.before('<span class="lt-bg"></span>');
			iptHtml.after('<span class="rt-bg"></span>');
		}
		addBtn()*/
		
		seajs.log($('.d-button:eq(1)'))
	}
	
	
	var dialog = function(options){
		var dlg = $.dialog(options);
		init(options);
		setTimeout(function(){
			if(we.browser.ie6){
				$('.d-mask').bgiframe();
			};
		},100);
		
		return dlg;
	}
	
	var confirm = function(options) {
		var cfm = $.dialog.confirm(options);
		init(options);
		return cfm;
	}
	
	var alert = function(options) {
		var alt = $.dialog.alert(options);
		init(options);
		return alt;
	}
	
	window.closeParentDlg = function(){
		setTimeout(function(){
			$.dialog.closeCurrent();
		},0);

	}
	    
    /**
     * @name Dialog
     * @class 提供 dialog, alert, promote, confirm 对话框组件。
     * @requires jQuery，jquery.artDialog
     * @author 李博龙
     * @version v1.0.0  
     * @param {Object} options
     * @example
     * define(function(require){
     * 	$(function(){
     * 		var $dialog = require('dialog');
     * 	    var dialog = $dialog({
     * 
     * 			//内容
     * 	        title: '标题',
     * 	        content: 'hello world!',
     * 
     * 			//按钮
     * 	        okValue: '猛击我',
     * 	        ok: function () {
     * 	            alert('对喽！');
     * 	        },
     * 	        cancelValue: '取消我',
     * 	        cancel: function () {
     * 	        	 alert('关了吧！');
     * 	        },
     *	        button: [
     *                 {
     *                     value: '同意',
     *                     callback: function () {
     *                         this
     *                         .content('你同意了')
     *                         .button({
     *                             id: 'button-disabled',
     *                             value: '我变成有效按钮了',
     *                             disabled: false
     *                          });
     *                         return false;
     *                     },
     *                     focus: true
     *                 },
     *                 {
     *                     value: '不同意',
     *                     callback: function () {
     *                         alert('你不同意')
     *                     }
     *                 },
     *                 {
     *                     id: 'button-disabled',
     *                     value: '无效按钮',
     *                     disabled: true
     *                 }
     *            ],
     * 
     * 			//尺寸
     * 	        width: '460px',
     * 	        height: '200px',
     * 
     * 			//位置
     * 	        fixed: true,
     * 	        follow: $("#ajaxpage")[0],
     * 
     * 			//视觉
     * 	        lock: true,
     * 	        padding: 0,
     * 
     * 			//高级
     * 	        id: 'dialog-demo',
     * 	        zIndex: 87,
     * 	        initialize: function () {
     * 	            alert('对话框初始化完成');
     * 	        },
     * 	        beforeunload: function () {
     * 	            return confirm('确定关闭对话框吗');
     * 	        },
     * 	        visible: true,
     * 	        time: 20000,
     * 	        esc: false,
     * 	        focus: false
     * 	    });
     * 	});
     * });
     */
	
    /**
     * @name Dialog#alert
     * @function   
     * @desc 封装 alert
     * @param {内容，回调方法（可选）} content,[callback]
     * @example
     * define(function(require) {
     *     $(function() {
     * 		   var $dialog = require('dialog');
     * 		   $dialog.alert('那些年，我们一起追的女孩');
     *     });
     * });
     */
    
    /**
     * @name Dialog#confirm
     * @function   
     * @desc 封装 confirm
     * @param {内容，确定方法，默认值（可选）} content,ok,[defaultValue]
     * @example
     * define(function(require) {
     *     $(function() {
     * 		   var $dialog = require('dialog');
     * 		   $dialog.prompt('你的名字是什么？', function (value) {
     * 		       alert(value);
     * 		   }, '糖饼');
     *     });
     * });
     */
    
    /**
     * @name Dialog#shake
     * @function   
     * @desc 抖动效果
     * @example
     * define(function(require) {
     *     $(function() {
     * 		   var $dialog = require('dialog');
     * 		   $dialog.dialog({
     * 		       title: '登录',
     * 		       ok: function () {
     * 		           this.shake();
     * 		           return false;
     * 		       },
     * 		       okValue: '登录'
     * 		   });
     *     });
     * });
     */
    
    /**
     * @name Dialog#prompt
     * @function   
     * @desc 封装 prompt
     * @param {内容，确定方法，取消方法（可选）} content,ok,[cancel]
     * @example
     * define(function(require) {
     *     $(function() {
     * 		   var $dialog = require('dialog');
     * 		   $dialog.confirm('你再也不相信爱情了么？', function () {
     * 		       alert(0);
     * 		       }, function () {
     * 		       alert(1);
     * 		   });
     *     });
     * });
     */
    
    /**
     * @name Dialog#get
     * @function   
     * @desc 根据 id 获取 dialog 对象。
     * @param {对话框 id} String
     * @example
     * define(function(require) {
     *     $(function() {
     * 		   var $dialog = require('dialog');
     * 	       $dialog({
     * 			   id: 'demo-get'
     * 		   });
     * 	       var dialog = $dialog.get('demo-get');
     * 		   dialog.title('hello').content('I Love You!');
     *     });
     * });
     */

    /**
     * @name Dialog#close
     * @function   
     * @desc 关闭对话框
     * @example
     * define(function(require) {
     *     $(function() {
     * 		   var $dialog = require('dialog');
     * 	       var dialog = $dialog({
     * 			   content: 'hello world!'
     * 		   });
     * 	       dialog.close();
     *     });
     * });
     */
    
    /**
     * @name Dialog#hidden
     * @function   
     * @desc 隐藏对话框
     * @example
     * define(function(require) {
     *     $(function() {
     * 		   var $dialog = require('dialog');
     * 	       var dialog = $dialog({
     * 			   content: 'hello world!'
     * 		   });
     * 	       dialog.hidden();
     *     });
     * });
     */

    /**
     * @name Dialog#visible
     * @function   
     * @desc 把隐藏的对话框显示
     * @example
     * define(function(require) {
     *     $(function() {
     * 		   var $dialog = require('dialog');
     * 	       var dialog = $dialog({
     * 			   visible: false
     * 		   });
     * 	       dialog.visible();
     *     });
     * });
     */

    /**
     * @name Dialog#title
     * @function   
     * @desc 设置对话框标题
     * @param {标题内容} String
     * @example
     * define(function(require) {
     *     $(function() {
     * 		   var $dialog = require('dialog');
     * 	       var dialog = $dialog({
     * 			   title: '请等待..'
     * 		   });
     * 	       dialog.title('欢迎使用');
     *     });
     * });
     */

    /**
     * @name Dialog#content
     * @function   
     * @desc 设置消息内容
     * @param {消息内容} String
     * @example
     * define(function(require) {
     *     $(function() {
     * 		   var $dialog = require('dialog');
     * 	       var dialog = $dialog({
     * 			   title: '请等待..'
     * 		   });
     * 	       dialog.content('加载完成！');
     *     });
     * });
     */

    /**
     * @name Dialog#follow
     * @function   
     * @desc 让对话框依附在指定元素附近
     * @param {元素} element
     * @example
     * define(function(require) {
     *     $(function() {
     * 		   var $dialog = require('dialog');
     * 	       var dialog = $dialog({
     * 			   title: '请等待..'
     * 		   });
     * 	       dialog.follow(document.getElementById('demo'));
     *     });
     * });
     */

    /**
     * @name Dialog#size
     * @function   
     * @desc 让对话框依附在指定元素附近
     * @param {内容宽度，内容高度} width,height
     * @example
     * define(function(require) {
     *     $(function() {
     * 		   var $dialog = require('dialog');
     * 	       var dialog = $dialog({
     * 			   title: '请等待..'
     * 		   });
     * 	       dialog.size('200px', '200px');
     *     });
     * });
     */

    /**
     * @name Dialog#lock
     * @function   
     * @desc 开启锁屏遮罩
     * @example
     * define(function(require) {
     *     $(function() {
     * 		   var $dialog = require('dialog');
     * 	       var dialog = $dialog({
     * 			   title: '请等待..'
     * 		   });
     * 	       dialog.lock();
     *     });
     * });
     */

    /**
     * @name Dialog#unlock
     * @function   
     * @desc 关闭锁屏遮罩
     * @example
     * define(function(require) {
     *     $(function() {
     * 		   var $dialog = require('dialog');
     * 	       var dialog = $dialog({
     * 			   lock: true
     * 		   });
     * 	       dialog.unlock();
     *     });
     * });
     */

    /**
     * @name Dialog#time
     * @function   
     * @desc 关闭锁屏遮罩
     * @param {毫秒} value
     * @example
     * define(function(require) {
     *     $(function() {
     * 		   var $dialog = require('dialog');
     * 	       var dialog = $dialog({
     * 			   lock: true
     * 		   });
     * 	       dialog.time(2000);
     *     });
     * });
     */
    
    return {
    	dialog: dialog,
    	alert: alert,
    	prompt: $.dialog.prompt,
    	confirm: confirm,
    	get:$.dialog.get
    }
});