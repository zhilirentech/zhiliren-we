define(function(require, exports, module) {

    /**
     * @name Ajax
     * @class 提供 vkoAjax, vkoAjaxP, vkoSubmit, vkoSubmitP 处理返回的 json 。
     * @requires jquery, parsejson
     * @author 李博龙
     * @version v1.0.0  
     */
    var $ = require('jquery');
    var statusCode = {
	        ok: 200,
	        error: 300,
	        timeout: 301
	    },
	    error_404 = {
    		status: '404',
    		statusText: '非常抱歉，您访问的页面不存在！',
    		message: '可能网页已经被关闭或者您输入的网址错误。'
    	},
    	error_500 = {
    		status: '500',
    		statusText: '非常抱歉，程序出错，请稍后重试！',
    		message: '很抱歉让您看到这个页面，我们会继续改进。'
    	},
    	errorTmpl = ['<div class="panel_box">',
	    	             '<div class="panel_content">',
	    	             	'<div class="error_ex">',
	    	             		'<h1>{{ status }}</h1>',
	    	             		'<h3>{{ statusText }}</h3>',
	    	             		'<p>{{ message }}</p>',
	    	             		'<li>{{ btn }}</li>',
	    	             	'</div>',
		             	'</div>',
	             	'</div>'].join('');
    /**
     * @name Ajax#vkoAjax
     * @function   
     * @desc 执行 ajax 请求。
     * @param {Object} options
     * @example
     * define(function(require) {
     *     $(function() {
     *         var  $ajax = require('ajax');
     *         $ajax.vkoAjax({
     *     	       url:'test.html',
     *     	       data:{
     *     		       name: 'dragon',
     *     		       age: 100
     *     	       },
     * 		       success: function(data){
     * 			       alert(data);
     * 		       }
     *         });
     *     });
     * });
     */
    var vkoAjax = function(options) {
    	var op = $.extend({
        	url: '',
            type: 'get',
            data: {},
            cache: false,
            jsonp: 'callback',
            hidemsg: false
        }, options);
        if(we.utils.isEmpty(op.url) || ajaxSended(op)) return;
        $.ajax({
        	url: encodeURI(op.url),
			type: op.type,
			data: op.data,
			dataType: op.dataType,
			cache: op.cache,
			jsonp: op.jsonp,
			success: function(data, textStatus) {
				_success(data, op);
				ajaxDone(data, op);
			},
			complete: function(XMLHttpRequest, textStatus) {
				_complete(XMLHttpRequest, op);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				_error(XMLHttpRequest, op);
				ajaxDone(XMLHttpRequest, op);
			}
        });
    }

    /**
     * @name Ajax#vkoAjaxP
     * @function   
     * @desc 跨域 ajax 请求。
     * @param {Object} options
     * @example
     * define(function(require) {
     *     $(function() {
     *         var  $ajax = require('ajax');
     *         $ajax.vkoAjaxP({
     *     	       url:'test.html',
     *     	       data:{
     *     		       name: 'dragon',
     *     		       age: 100
     *     	       },
     * 		       success: function(data){
     * 			       alert(data);
     *             }
     *         });
     *     });
     * });
     */
    var vkoAjaxP = function(options) {
        options.dataType = 'jsonp';
        vkoAjax(options);
    }


    /**
     * @name Ajax#vkoSubmit
     * @function   
     * @desc form 表单 ajax 提交。
     * @requires jQuery.form
     * @param {Object} options
     * @example
     * define(function(require) {
     *     $(function() {
     *         var  $ajax = require('ajax');
     *         $ajax.vkoSubmit($('#form'),{
     *             url: 'text.html',   //默认值 form 的 action
     *             type: 'POST',       //默认值 POST
     *             cache： false,      //默认值 false
     *             success: function(data){
     *                 alert(data);
     *             },
     *             error: function(){},
     *             complete: function(){}
     *         });
     *     });
     * });
     */
    require('./jquery-form');
    var vkoSubmit = function(form, options) {
    	var op = $.extend({
            type: 'post',
            cache: false,
            jsonp: 'callback',
            hidemsg: false
        }, {
            type: form.attr('method'),
            url: form.attr('action')
        }, options);
        
        if(we.utils.isEmpty(op.url) || ajaxSended(op)) return;
        form.ajaxSubmit({
        	url: op.url,
			type: op.type,
			data: op.data,
			dataType: op.dataType,
			cache: op.cache,
			jsonp: op.jsonp,
			success: function(data, textStatus) {
				_success(data, op);
				ajaxDone(data, op);
			},
			complete: function(XMLHttpRequest, textStatus) {
				_complete(XMLHttpRequest, op);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				_error(XMLHttpRequest, op);
				ajaxDone(XMLHttpRequest, op);
			}
        });
    };

    /**
     * @name Ajax#vkoSubmitP
     * @function   
     * @desc form 表单跨域 ajax 提交。
     * @requires jQuery.form
     * @param {Object} options
     * @example
     * define(function(require) {
     *     $(function() {
     *         var  $ajax = require('ajax');
     *         $ajax.vkoSubmitP($('#form'),{
     *             url: 'text.html',   //默认值 form 的 action
     *             type: 'POST',       //默认值 POST
     *             cache： false,      //默认值 false
     *             success: function(data){
     *                 alert(data);
     *             },
     *             error: function(){},
     *             complete: function(){}
     *         });
     *     });
     * });
     */
    var vkoSubmitP = function(form, options) {
        options.dataType = 'jsonp';
        vkoSubmit(form, options);
    }

    /**
     * @name Ajax#ajaxSended
     * @desc ajax 请求或表单提交 <i style="color:green">前</i> 触发。
     * @event
     * @param {Object} options
     * 
     */
    var ajaxSended = function(options) {
        if(!we.utils.isEmpty(we.ajax) && we.utils.inArray(options,we.ajax)!=-1) {
            return true;
        }
        we.ajax.push(options);
        return false;
    }

    /**
     * @name Ajax#checkStatusCode
     * @desc 检查返回值status。
     * @event
     * @param {JSON} json
     * 
     */
    var checkStatusCode = function(json) {
        if(we.utils.isEmpty(json) || we.utils.isEmpty(json.status)) {
            return statusCode.ok;
        }
        if(json.status == statusCode.error) {
            return statusCode.error;
        }else if(json.status == statusCode.timeout) {
            return statusCode.timeout;
        }
        return statusCode.ok;
    }
    
    /**
     * @name Ajax#ajaxDone
     * @desc ajax成功提示。
     * @event
     * @param {JSON} json
     * 
     */
    var ajaxDone = function(data, options){
    	var status, type, $parsejson = require('parsejson');
        data = $parsejson(data);
        if(we.utils.isEmpty(data) || typeof data!='object')return;
        status = checkStatusCode(data);
        if(!options.hidemsg && !we.utils.isEmpty(data.message)){
        	if(status == statusCode.ok){
        		type = 'success';
        	}else if(status == statusCode.error){
        		type = 'error';
        	}
        	if(status == statusCode.timeout) {
            	we.ui.loadLogin({
            		loaded: function(){
            			window.loadLoginDialog.dom.wrap.find('.vkolm-logpwd').append('<span class="vkolm-error" style="display: inline;">'+data.message+'</span>');
        			}
            	});
            }else{
            	if($.dialog){
            		$.dialog.alert(data.message,type);
            	}else{
            		alert(type+': '+data.message);
            	}
            }
        }
    }


    /**
     * @name Ajax#success
     * @desc ajax 请求或表单提交 <i style="color:green">成功</i> 后触发</br>根据返回 json 中的 statusCode{ok: 200, error: 300, timeout: 301} 处理。
     * @event
     * @param {JSON} json
     * 
     */
    var _success = function(data, options) {
        var status, $parsejson = require('parsejson');
        data = $parsejson(data);
        status = checkStatusCode(data);
        if(status == statusCode.ok) {
            if(!we.utils.isEmpty(options.success) && $.isFunction(options.success)){
                options.success.call(this, data);
            }else{
                seajs.log('Ajax Success: ');
                seajs.log(data);
            }
        }else if(status == statusCode.error) {
            if(!we.utils.isEmpty(options.error) && $.isFunction(options.error)) {
                options.error.call(this, data);
            }else{
               _error.call(this, data, options);
            }
        }else if(status == statusCode.timeout) {
            if(!we.utils.isEmpty(options.timeout) && $.isFunction(options.timeout)) {
                options.timeout.call(this, data, options);
            }else{
            	//_error.call(this, data, options);
            }
        }
    }

    /**
     * @name Ajax#complete
     * @desc ajax 请求或表单提交 <i style="color:green">完成</i> 后触发，用于放置ajax重复请求。
     * @event
     */
    var _complete = function(XMLHttpRequest, options) {
    	 if(!we.utils.isEmpty(options.complete && $.isFunction(options.complete))) {
             options.complete.call(this);
         }
         if(!we.utils.isEmpty(we.ajax) && we.utils.inArray(options,we.ajax)!=-1) {
         	we.ajax.splice(we.utils.inArray(options,we.ajax), 1);
         }
        //seajs.log('Ajax Complete: ');
        //seajs.log(options);
    }

    /**
     * @name Ajax#error
     * @desc ajax 请求或表单提交 <i style="color:green">错误</i> 后触发
     * @event
     */
    var _error = function(XMLHttpRequest, options) {
    	var tmpl = errorTmpl;
    	var _showErrorPage = function(errorMsg){
	        	tmpl = tmpl.replace('{{ status }}', errorMsg.status);
	    		tmpl = tmpl.replace('{{ statusText }}', errorMsg.statusText);
	    		tmpl = tmpl.replace('{{ message }}', errorMsg.message);
	    		tmpl = tmpl.replace('{{ btn }}', $('#sidebar a[href]:first').clone().addClass('btn btn_big btn_invalid').attr('title',$('#sidebar a[href]:first').text()).text('返回'+$('#sidebar a[href]:first').text())[0].outerHTML);
	    		if(!we.utils.isEmpty(options.success) && $.isFunction(options.success)){
	    			options.success.call(this, tmpl);
	    		}else{
	    			$.dialog({content:tmpl}); 
	    		}
			}
    	tmpl = tmpl.replace('{{ status }}',XMLHttpRequest.status);
    	if(XMLHttpRequest.status=='404'){
    		_showErrorPage(error_404);
    	}else if(XMLHttpRequest.status=='500'){
    		_showErrorPage(error_500);
    	}
    	
    	 if(!we.utils.isEmpty(options.error) && $.isFunction(options.error)) {
             options.error.call(this, XMLHttpRequest);
         }else{
        	 if(!we.utils.isEmpty(XMLHttpRequest.message)){
        		 $dialog.alert(XMLHttpRequest.message,'error');
        	 }
        	 seajs.log('Ajax Error: ');
         	seajs.log(XMLHttpRequest);
         }
    }
    
    
    /**-------------------------- 全局方法 --------------------------**/
    /**
     * 普通ajax表单提交
     * @param {Object} form
     * @param {Object} callback
     * @param {String} confirmMsg 提示确认信息
     */
    window.validateCallback = function(form, callback, confirmMsg) {
    	var $form = $(form);
    	//we.currentDialog = $form.parent
    	if(!$form.valid()) {
    		return false;
    	}
    	var _submitFn = function(){
    		vkoSubmit($form, {success: callback});
    	}
    	if(confirmMsg) {
    		$.confirm({content:confirmMsg, ok:_submitFn});
    	}else{
    		_submitFn();
    	}
    	return false;
    }

    /**
     * navTabAjaxDone是框架中预定义的表单提交回调函数．
     * 服务器转回navTabId可以把那个navTab标记为reloadFlag=1, 下次切换到那个navTab时会重新载入内容. 
     * callbackType如果是closeCurrent就会关闭当前tab
     * 只有callbackType="forward"时需要forwardUrl值
     * navTabAjaxDone这个回调函数基本可以通用了，如果还有特殊需要也可以自定义回调函数.
     * 如果表单提交只提示操作是否成功, 就可以不指定回调函数. 框架会默认调用ajaxDone()
     * <form action="/user.do?method=save" onsubmit="return validateCallback(this, navTabAjaxDone)">
     * 
     * form提交后返回json数据结构statusCode=statusCode.ok表示操作成功, 做页面跳转等操作. statusCode=statusCode.error表示操作失败, 提示错误原因. 
     * statusCode=statusCode.timeout表示session超时，弹出登录框
     * {"statusCode":"200", "message":"操作成功", "navTabId":"navNewsLi", "forwardUrl":"", "callbackType":"closeCurrent", "rel"."xxxId"}
     * {"statusCode":"300", "message":"操作失败"}
     * {"statusCode":"301", "message":"会话超时"}
     * 
     */
    window.navTabAjaxDone = function(data){
    	if(data.navTabId){ //把指定navTab页面标记为需要“重新载入”。注意navTabId不能是当前navTab页面的
			we.ui.navTab.reloadFlag(data.navTabId);
		}else{ //重新载入当前navTab页面
			var $panel = we.ui.navTab.getCurrentPanel();
			var $pagerForm = $('#pagerForm', $panel).not($('.uintBox #pagerForm', $panel));
			var args = $pagerForm.size()>0 ? $pagerForm.serializeArray() : {}
			navTabPageBreak(args, data.rel);
		}
		if('closeCurrent' == data.callbackType) {
			setTimeout(function(){we.ui.navTab.closeCurrentTab(data.navTabId);}, 100);
		}else if('forward' == data.callbackType) {
			we.ui.navTab.reload(data.forwardUrl);
		}else if('forwardConfirm' == data.callbackType) {
			$dialog.confirm({
				content: data.confirmMsg || '亲，你确定要执行本操作？',
				ok:function(){
					we.ui.navTab.reload(data.forwardUrl);
				},
				cancel:function(){
					we.ui.navTab.closeCurrentTab(data.navTabId);
				}
			});
		}else{
			we.ui.navTab.getCurrentPanel().find(':input[initValue]').each(function(){
				var initVal = $(this).attr('initValue');
				$(this).val(initVal);
			});
		}
		if(data.callbackType!='keepOpen' && $.dialog.getFocus()!=null) {
			$.dialog.closeCurrent()
		}else{
			if(data.refreshDialog == 'refreshDialog'){
				dialogPageBreak({}, data.rel);
			}
		}
    }
    
    /**
     * dialog上的表单提交回调函数
     * 服务器转回navTabId，可以重新载入指定的navTab. statusCode=statusCode.ok表示操作成功, 自动关闭当前dialog
     * 
     * form提交后返回json数据结构,json格式和navTabAjaxDone一致
     */
    window.dialogAjaxDone = function(data){
		if(data.navTabId){
			we.ui.navTab.reload(data.forwardUrl, {navTabId: data.navTabId});
		}else if(data.rel) {
			var $panel = we.ui.navTab.getCurrentPanel();
			var $pagerForm = $('#pagerForm', $panel).not($('.uintBox #pagerForm', $panel));
			var args = $pagerForm.size()>0 ? $pagerForm.serializeArray() : {}
			navTabPageBreak(args, data.rel);
		}
		if(data.callbackType == 'closeCurrent') {
			$.dialog.closeCurrent();
		}else{
			if(data.refreshDialog == 'refreshDialog'){
				dialogPageBreak({}, data.rel);
			}
		}
    }
    
    /**
     * 处理navTab上的查询, 会重新载入当前navTab
     * @param {Object} form
     */
    window.navTabSearch = function(form, navTabId){
    	var $form = $(form);
    	if(form[we.pageInfo.pageNum]) form[we.pageInfo.pageNum].value = 1;
    	we.ui.navTab.reload($form.attr('action'), {data: $form.serializeArray(), navTabId:navTabId});
    	return false;
    }
    
    /**
     * 处理dialog弹出层上的查询, 会重新载入当前dialog
     * @param {Object} form
     */
    window.dialogSearch = function(form){
    	var $form = $(form);
    	if(form[we.pageInfo.pageNum]) form[we.pageInfo.pageNum].value = 1;
    	$.dialog.reload($form.attr('action'), {data: $form.serializeArray()});
    	return false;
    }
    
    /**
     * 处理div上的查询, 会重新载入当前div
     * @param {Object} form
     */
    window.divSearch = function(form, rel){
    	var $form = $(form);
    	if(form[we.pageInfo.pageNum]) form[we.pageInfo.pageNum].value = 1;
    	if (rel) {
    		var $box = $("#" + rel);
    		vkoAjax({
				url: $(form).attr('action'),
				data: $(form).serializeArray(),
				success: function(data){
					$box.html(data).initUI();
				}
			});
    	}
    	return false;
    }
    
    window.vkoSearch = function (form, targetType){
    	if(targetType == 'dialog') dialogSearch(form);
    	else navTabSearch(form);
    	return false;
    }
    
    /**
     * 
     * @param {Object} args {pageNum:"",numPerPage:"",orderField:"",orderDirection:""}
     * @param String formId 分页表单选择器，非必填项默认值是 "pagerForm"
     */
    var _getPagerForm = function($parent, args) {
    	var form = $('#pagerForm', $parent).not($('.uintBox #pagerForm', $parent)).get(0);

    	if(form) {
    		if(args['pageNum']) form[we.pageInfo.pageNum].value = args['pageNum'];
    		if(args['numPerPage']) form[we.pageInfo.numPerPage].value = args['numPerPage'];
    		if(args['orderField']) form[we.pageInfo.orderField].value = args['orderField'];
    		if(args['orderDirection'] && form[we.pageInfo.orderDirection]) form[we.pageInfo.orderDirection].value = args['orderDirection'];
    	}
    	
    	return form;
    }
    
    /**
     * 处理navTab中的分页和排序
     * targetType: navTab 或 dialog
     * rel: 可选 用于局部刷新div id号
     * data: pagerForm参数 {pageNum:"n", numPerPage:"n", orderField:"xxx", orderDirection:""}
     * callback: 加载完成回调函数
     */
    window.vkoPageBreak = function(options){
    	var op = $.extend({ targetType:'navTab', rel:'', data:{pageNum:'', numPerPage:'', orderField:'', orderDirection:''}, callback:null}, options);
    	var $parent = op.targetType == 'dialog' ? $.dialog.getCurrent() : we.ui.navTab.getCurrentPanel();

    	if(op.rel) {
    		var $box = $parent.find('#' + op.rel);
    		var form = _getPagerForm($box, op.data);
    		if(form) {
    			vkoAjax({
    				url: $(form).attr('action'),
    				data: $(form).serializeArray(),
    				success: function(data){
    					$box.html(data).initUI();
    				}
    			});
    		}
    	}else{
    		var form = _getPagerForm($parent, op.data);
    		var params = $(form).serializeArray();
    		
    		if(op.targetType == 'dialog') {
    			if(form) $.dialog.reload($(form).attr('action'), {data: params, callback: op.callback});
    		}else{
    			if(form) we.ui.navTab.reload($(form).attr('action'), {data: params, callback: op.callback});
    			setTimeout(function(){
    				$("html, body").animate({ scrollTop: 0 }, 300);
    			},500);
    		}
    	}
    }
    
    /**
     * 处理navTab中的分页和排序
     * @param args {pageNum:"n", numPerPage:"n", orderField:"xxx", orderDirection:""}
     * @param rel： 可选 用于局部刷新div id号
     */
    window.navTabPageBreak = function(args, rel){
    	vkoPageBreak({targetType:'navTab', rel:rel, data:args});
    }
    /**
     * 处理dialog中的分页和排序
     * 参数同 navTabPageBreak 
     */
    window.dialogPageBreak = function(args, rel){
    	vkoPageBreak({targetType:'dialog', rel:rel, data:args});
    }
    
    /**
     * 当前navTab中链接ajax post扩展
     */
    window.ajaxTodo = function(url, callback){
    	var $callback = callback || navTabAjaxDone;
    	if(! $.isFunction($callback)) $callback = eval('(' + callback + ')');
    	vkoAjax({
    		url:url,
    		type:'post',
    		success: $callback
    	});
    }
    
    $(function(){
    	if($('#ajaxLoading').size()<1){
    		$('body').append('<div id="ajaxLoading"></div>')
    		$('#ajaxLoading').bind('ajaxSend',function(){
    			$(this).show();
    		}).ajaxComplete(function(){
    			$(this).hide();
    		});
    	}
    });
    
    /**********************************************************************************/

    return {
		weAjax: vkoAjax,
        weAjaxP: vkoAjaxP,
        weSubmit: vkoSubmit,
        weSubmitP: vkoSubmitP,
        vkoAjax: vkoAjax,
        vkoAjaxP: vkoAjaxP,
        vkoSubmit: vkoSubmit,
        vkoSubmitP: vkoSubmitP
    }
});