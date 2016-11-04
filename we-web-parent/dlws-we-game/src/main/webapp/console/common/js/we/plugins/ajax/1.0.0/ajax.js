define('ajax',['parsejson','we/plugins/ajax/1.0.0/jquery-form'],function(require, exports, module) {

    /**
     * @name Ajax
     * @class 提供 vkoAjax, vkoAjaxP, vkoSubmit, vkoSubmitP 处理返回的 json 。
     * @requires jquery, parsejson
     * @author 李博龙
     * @version v1.0.0  
     */
    var $ = require('jquery'),
		$parsejson = require('parsejson'),
		statusCode = {
	        ok: 200,
	        error: 300,
	        timeout: 301
	    }
    /**
     * @name Ajax#vkoAjax
     * @function   
     * @desc 普通 ajax 请求。
     * @param {Object} options
     * @example
     * define(function(require) {
     *     $(function() {
     *         var  $ajax = require('ajax');
     *         $ajax.vkoAjax({
     *     	       url: 'test.html',           //【必填】请求地址
     *     	       type: 'get',                //【选填】请求方式，默认值：get
     *     	       cache: false,               //【选填】是否缓存，默认值：false	
     *     	       data: {                     //【必填】请求参数
     *     		       name: 'dragon',
     *     		       age: 100
     *     	       },
     * 		       success: function(data){    //【选填】请求成功回调
     * 			       seajs.log(data);
     * 		       },
     * 		       error: function(data){      //【选填】请求出错回调
     * 			       seajs.log(data);
     * 		       },
     * 		       complete: function(data){   //【选填】请求完成回调
     * 			       seajs.log(data);
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
            cache: true,
            jsonp: 'callback',
            hidemsg: false
        }, options);
        if(we.utils.isEmpty(op.url) || _ajaxSended(op)) return;
        //加 loading
        //require('loading').init.add();
        $.ajax({
        	url: op.url,
			type: op.type,
			data: op.data,
			dataType: op.dataType,
			cache: op.cache,
			jsonp: op.jsonp,
			success: function(data, textStatus) {
				_success(data, op);
				_ajaxDone(data, op);
			},
			complete: function(XMLHttpRequest, textStatus) {
				_complete(XMLHttpRequest, op);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				_error(XMLHttpRequest, op);
				_ajaxDone(XMLHttpRequest, op);
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
     *     	       url: 'test.html',           //【必填】请求地址
     *     	       type: 'get',                //【选填】请求方式，默认值：get
     *     	       cache: false,               //【选填】是否缓存，默认值：false	
     *     	       data: {                     //【必填】请求参数
     *     		       name: 'dragon',
     *     		       age: 100
     *     	       },
     * 		       success: function(data){    //【选填】请求成功回调
     * 			       seajs.log(data);
     * 		       },
     * 		       error: function(data){      //【选填】请求出错回调
     * 			       seajs.log(data);
     * 		       },
     * 		       complete: function(data){   //【选填】请求完成回调
     * 			       seajs.log(data);
     * 		       }
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
     * @param {Object} options
     * @example
     * define(function(require) {
     *     $(function() {
     *         var  $ajax = require('ajax');
     *         $ajax.vkoSubmit($('#form'),{
     *     	       url: 'test.html',           //【选填】请求地址，默认值：form 的 action
     *     	       type: 'post',               //【选填】请求方式，默认值：post
     *     	       cache: false,               //【选填】是否缓存，默认值：false	
     * 		       success: function(data){    //【选填】请求成功回调
     * 			       seajs.log(data);
     * 		       },
     * 		       error: function(data){      //【选填】请求出错回调
     * 			       seajs.log(data);
     * 		       },
     * 		       complete: function(data){   //【选填】请求完成回调
     * 			       seajs.log(data);
     * 		       }
     *         });
     *     });
     * });
     */
    require('./jquery-form');
    var vkoSubmit = function(form, options) {
    	var op = $.extend({
            type: 'post',
            cache: true,
            jsonp: 'callback',
            hidemsg: false
        }, {
            type: form.attr('method'),
            url: form.attr('action')
        }, options);
        
        if(we.utils.isEmpty(op.url) || _ajaxSended(op)) return;
        //加 loading
        //require('loading').init.add();
        form.ajaxSubmit({
        	url: op.url,
			type: op.type,
			dataType: op.dataType,
			data:op.data,
			cache: op.cache,
			jsonp: op.jsonp,
			success: function(data, textStatus) {
				_success(data, op);
				_ajaxDone(data, op);
			},
			complete: function(XMLHttpRequest, textStatus) {
				_complete(XMLHttpRequest, op);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				_error(XMLHttpRequest, op);
				_ajaxDone(XMLHttpRequest, op);
			}
        });
    };

    /**
     * @name Ajax#vkoSubmitP
     * @function   
     * @desc form 表单跨域 ajax 提交。
     * @param {Object} options
     * @example
     * define(function(require) {
     *     $(function() {
     *         var  $ajax = require('ajax');
     *         $ajax.vkoSubmitP($('#form'),{
     *     	       url: 'test.html',           //【选填】请求地址，默认值：form 的 action
     *     	       type: 'post',               //【选填】请求方式，默认值：post
     *     	       cache: false,               //【选填】是否缓存，默认值：false	
     * 		       success: function(data){    //【选填】请求成功回调
     * 			       seajs.log(data);
     * 		       },
     * 		       error: function(data){      //【选填】请求出错回调
     * 			       seajs.log(data);
     * 		       },
     * 		       complete: function(data){   //【选填】请求完成回调
     * 			       seajs.log(data);
     * 		       }
     *         });
     *     });
     * });
     */
    var vkoSubmitP = function(form, options) {
        options.dataType = 'jsonp';
        vkoSubmit(form, options);
    }

    /**
     * @name Ajax#_ajaxSended
     * @desc ajax 请求或表单提交 <i style="color:green">前</i> 触发。
     * @event
     * @param {Object} options
     * 
     */
    var _ajaxSended = function(options) {
        if(!we.utils.isEmpty(we.ajax) && we.utils.inArray(options,we.ajax)!=-1) {
            return true;
        }
        we.ajax.push(options);
        return false;
    }

    /**
     * @name Ajax#_checkStatusCode
     * @desc 检查返回值status。
     * @event
     * @param {JSON} json
     * 
     */
    var _checkStatusCode = function(json) {
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
     * @name Ajax#_ajaxDone
     * @desc ajax成功提示。
     * @event
     * @param {JSON} json
     * 
     */
    var _ajaxDone = function(data, options){
    	//TODO
	}


    /**
     * @name Ajax#_success
     * @desc ajax 请求或表单提交 <i style="color:green">成功</i> 后触发</br>根据返回 json 中的 statusCode{ok: 200, error: 300, timeout: 301} 处理。
     * @event
     * @param {JSON} json
     * 
     */
    var _success = function(data, options) {
        var status;
        data = $parsejson(data);
        status = _checkStatusCode(data);
        if(status == statusCode.ok) {
            if(!we.utils.isEmpty(options.success) && $.isFunction(options.success)){
                options.success.call(this, data);
            }else{
                seajs.log('Ajax Success: ');
                seajs.log(data);
            }
        }else if(status == statusCode.error) {
        	_error.call(this, data, options);
        }else if(status == statusCode.timeout) {
            if(!we.utils.isEmpty(options.timeout) && $.isFunction(options.timeout)) {
                options.timeout.call(this, data, options);
            }else if(!we.utils.isEmpty(we.ajaxTimeout) && $.isFunction(we.ajaxTimeout)){
            	we.ajaxTimeout.call(this, data, options);
            }else{
            	_error.call(this, data, options);
            }
        }else{
        	_error.call(this, data, options);
        }
    }

    /**
     * @name Ajax#_complete
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
         //删除loading
         //require('loading').init.remove();
        //seajs.log('Ajax Complete: ');
        //seajs.log(options);
    }

    /**
     * @name Ajax#_error
     * @desc ajax 请求或表单提交 <i style="color:green">错误</i> 后触发
     * @event
     */
    var _error = function(XMLHttpRequest, options) {
    	if(!we.utils.isEmpty(options.error) && $.isFunction(options.error)) {
            options.error.call(this, XMLHttpRequest);
        }else{
        	seajs.log('Ajax Error: ');
        	seajs.log(XMLHttpRequest);
        }
    }
    
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