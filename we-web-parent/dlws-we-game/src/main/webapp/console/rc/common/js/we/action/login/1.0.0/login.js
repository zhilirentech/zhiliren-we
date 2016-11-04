define(function(require, exports, module){
	
	/**
     * @name Login
     * @class 提供登录及验证方法。
     * @requires jquery
     * @author 李博龙
     * @version v1.0.0
     * @example 
     * define(function(require) {
     *     var $login = require('login');
     * });
     */
	var $ = require('jquery');
	require('./style.css');
	
	var loginTmpl = ['<div class="vkoLoginModule">',
	         			'<h2>登录</h2>',
	                 	'<form action="/share/login.html" onsubmit="return false;">',
	        				'<div class="vkolm-item vkolm-logname"><input type="text" name="loginName" placeholder="用户名"/></div>',
	        				'<div class="vkolm-item vkolm-logpwd"><input type="password" name="password" placeholder="密码"/></div>',
	        				'<div class="vkolm-item vkolm-rememberme"><label><input type="checkbox" name="rememberme"/>记住密码</label></div>',
	        				'<div class="vkolm-item vkolm-submit"><input type="submit" value="登录"/></div>',
	        				//'<div class="vkolm-item vkolm-help"><a href="#" target="_blank">忘记密码？</a><a href="#" target="_blank">登录失败？</a></div>',
	                 	'</form>',
	                 '</div>'].join('');
	
	var loadLoginInit = function(options, callback){
		loadLogin(function(data){
			callback(data);
			loadEvent(options);
		});
	}
	
	var loadLogin = function(callback){
		callback(loginTmpl);
	}
	
	var loadEvent = function(options){
		options = $.extend({
			item: null,
			event: null,
    		callback: null
    	},options);
		
		$('.vkoLoginModule').each(function(){
			var $this = $(this);
			if(!we.utils.isEmpty($this.data('hasEvent')))return;
			$this.data('hasEvent', true);			
			_checkLoginData($this.find('form'), options); //数据检查
		});
	}
	
	var _checkLoginData = function($form, options){
		var $loginName = $('[name=loginName]', $form),
			$password = $('[name=password]', $form),
			$rememberme = $('[name=rememberme]', $form),
			$submitbtn = $('[type=submit]', $form),
			_errTmpl = '<span class="vkolm-error">{{ msg }}</span>';
		
		$loginName.bind({
	    	'blur.vkologin': function(){
	    		var $this = $(this);
	    		
	    		if(we.utils.isEmpty($this.val())){
	    			_removeError($this);
	    			return;
	    		}
	    		_removeError($this);
	    	}
    	});
		$password.bind('keyup.vkologin', function(e){
			if(e.keyCode==13){
				$submitbtn.click();
			}else{
				_removeError($(this));
			}
		});
		$rememberme.bind('change.vkologin', function(){
			var $label = $(this).parent();
			if($(this)[0].checked){
				$label.addClass('checked');
			}else{
				$label.removeClass('checked');
			}
		});
		$submitbtn.bind('click.vkologin', function(){
			if(we.utils.isEmpty($loginName.val())){
				_showError($loginName, '亲，请输入用户名');
				return;
			}
			if(we.utils.isEmpty($password.val())){
				_showError($password, '亲，请输入密码');
				return;
			}
			if(!we.utils.isEmpty($password.val()) && !(/^[^\s]{6,18}$/.test($password.val()))){
				_showError($password, '亲，密码在6-18之间哦');
				return;
			}
			if($rememberme[0].checked){
				$cookie.set('loginName',$loginName.val());
				$cookie.set('loginPwd',$password.val());
			}else{
				$cookie.remove('loginName');
				$cookie.remove('loginPwd');
			}
			$submitbtn.attr('disabled','disabled').val('登录中...');
            $ajax.vkoSubmit($form, {
            	hidemsg: true,
                success: function(json){
                	_success(json, options);
                	$submitbtn.removeAttr('disabled').val('登录');
                	/*if(!we.utils.isEmpty(json)) {
                		if(json.result == "1") {
                			we.utils.gotoUrl(we.vr.tiku+'/admin/home');
                		}
                	}*/
                },
                error: function(data) {
                	_showError($password, data.message);
                	if(!we.utils.isEmpty(window.loadLoginDialog)){
                    	window.loadLoginDialog.shake();
                    }
                	$submitbtn.removeAttr('disabled').val('登录');
                    seajs.log(data);
                }
            });
		});
		
		if($cookie.get('loginName') && $cookie.get('loginPwd')){
			$loginName.val($cookie.get('loginName'));
			$password.val($cookie.get('loginPwd'));
			$rememberme.attr('checked','checked').parent().addClass('checked');
//			$submitbtn.click();
		}
		
		var _showError = function($item, msg){
    		if($item.siblings('.vkolm-error').size()>0){
    			$item.siblings('.vkolm-error').html(msg);
    			return;
    		}
    		$item.after(_errTmpl.replace('{{ msg }}', msg));
    		$item.siblings('.vkolm-error').slideDown('fast');
    	}
		var _removeError = function($item){
			$item.siblings('.vkolm-error').slideUp('fast', function(){
				$(this).remove();
			});
    	}
	}
	
	var _success = function(data, options){
        if(!we.utils.isEmpty(options.callback)){
        	if(!we.utils.isEmpty(options.item)){
        		options.callback.call(options.item[0],options.event);
        	}else{
        		options.callback.call();
        	}
        }else{
        	window.location.reload();
        }
        // 刷新头部
        we.ui.refreshHead(data);
        if(!we.utils.isEmpty(window.loadLoginDialog)){
        	window.loadLoginDialog.close();
        }
        //seajs.log(data);
	}
	
	return{
		init: loadLoginInit,
		load: loadLogin,
		event: loadEvent
	}
});