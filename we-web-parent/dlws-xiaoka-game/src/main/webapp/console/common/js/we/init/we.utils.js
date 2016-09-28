define('we/init/we.utils',['cookie','page','select'],function (require, exports, module) {

    /**
     * @name utils
     * @class 扩展全局utils方法。
     * @requires jquery, cookie
     */
	var $cookie = require('cookie'),
		loadingAjaxLogin = false;
	/*ajax超时时默认操作*/
	we.ajaxTimeout=function(){
		we.utils.loading();
	}
	
    /*----- 扩展全局utils方法集 -----*/
	we.utils = $.extend(we.utils, {
		/* 初始化UI */
		initUI: function(_box, callback){
	    	var $doc = $(_box || document);
	    	//分页，这个名字不要修改啊，我了个去啊。必须和AjaxPageTag里的AJAX_CSSNAME属性名一致
	    	if($('div.ipage', $doc).size()>0){
	    		require.async('page',function($page){
	    			$('div.ipage', $doc).each(function(){
						var $this = $(this),
							url = $this.attr('pageurl'),
							list = $this.attr('pagelist'),
							data = $this.attr('pagedata'),
							item = $this.attr('pageitem'),
							size = $this.attr('pagesize'),
							num = $this.attr('pagenum'),
							count = $this.attr('pagecount'),
							first = $this.attr('pagefirst'),
							ajaxp = $this.attr('pageajaxp'),
							style = $this.attr('pagestyle'),
							empty = $this.attr('pageempty');
						
						var initPage = function(data){
							$page.ajaxPage({
								page: $this,
								list: list,
								url: url,
								data:  $this.data('data'),
								items: item,
								size: size,
								number: num,
								count: count,
								style: style,
								firstpage: (first=='true'?true:false),
								ajaxp: (ajaxp=='true'?true:false),
								empty: function(data){
									if(!we.utils.isEmpty(empty)){
										$(list).empty().append(empty);
									}
									var emptyCallback = $this.data('empty');
									if(!we.utils.isEmpty(emptyCallback) && $.isFunction(emptyCallback)){
										emptyCallback.call(this,data);
									}
								},
								update: function(data){
									we.utils.initUI($(list));
									var updateCallback = $this.data('update');
									if(!we.utils.isEmpty(updateCallback) && $.isFunction(updateCallback)){
										updateCallback.call(this,data);
									}
									//平台层面回调
									var aopUpdateCallback = $this.data('aopUpdate');
									if(!we.utils.isEmpty(aopUpdateCallback) && $.isFunction(aopUpdateCallback)){
										aopUpdateCallback.call(this,data);
									}
								}
							});
						}
						if(!we.utils.isEmpty(data)){
							require.async('metadata',function(){
								data = $this.metadata({type:'pagedata',name:'pagedata'});
								initPage(data);
							});
						}else{
							initPage(data);
						}
						
					});
	    		});
	    	}
	    	
	    	//时间控件
	    	//datepicker
	    	if($('input.date', $doc).size()>0){
	    		require.async('datepicker',function(){
	    			var _add = function($this){
	    				var opts = {};
		    			if ($this.attr('dateFmt')) opts.pattern = $this.attr('dateFmt');
		    			if ($this.attr('minDate')) opts.minDate = $this.attr('minDate');
		    			if ($this.attr('maxDate')) opts.maxDate = $this.attr('maxDate');
		    			if ($this.attr('mmStep')) opts.mmStep = $this.attr('mmStep');
		    			if ($this.attr('ssStep')) opts.ssStep = $this.attr('ssStep');
		    			if ($this.attr('changeMinDate')) opts.changeMinDate = $this.attr('changeMinDate');
		    			if ($this.hasClass('dateicon') && $this.siblings('a.dateicon').size()<1){
		    				$this.before('<a class="dateicon" href="javascript:;">选择</a>');
		    			}
		    			
		    			$this.datepicker(opts);
	    			}
	    			$('input.date', $doc).each(function(){
		    			var $this = $(this);
		    			_add($(this));
		    		});
	    			if(we.utils.isEmpty(we.datepicker)){
	    				we.datepicker = function(input){
	    					setTimeout(function(){_add(input);},100)
	    				}
	    			}
	    		});
	    	}
	    	
	    	//下拉框只要有写class="xxx",即自动调用插件，功能标签化
	    	setTimeout(function(){
	    		if($('select.select').size()>0){
		    		require.async('select',function($select){
		    			$select.init({
		    				obj:$('select.select'),
		    				width:'auto',
		    				height:'auto'
		    			});
		    		});
		    	}
	    	},10)
	    	
	    	
	    	
		},
		/* 获取用户数据 */
        getUserData: function () {
            $ajax.vkoAjaxP({
                url: we.vr.sso + '/user.json',
                success: function(data){
                	_userDataComplete(data);
                	// 刷新头部
                	we.utils.refreshHead();
                },
                error: function (data) {
                    _userDataComplete(data);
                    // 悄悄的什么也不做~
                }
            });
        },
        loading:function(options){
        	options = $.extend({
        		item: null,
        		event: null,
        		loaded: null,
        		callback: null,
        		state: '',
        		success:function(data){
					 /*
					$dialog.dialog({
						title:'账号登录',
						content:data,
						initialize: function(){
	                    	window.loadLoginDialog = this;
	                    },
						lock:true
					});
					*/
					$udialog.dialog({
						skin:3,
						title:'账号登录',
						content:data,
						padding:0,
						initialize: function(){
	                    	window.loadLoginDialog = this;
	                    },
						lock:true
					});
					
        		}
        	},options);
        	//TODO login暂且注释掉了
        	//require('login').load(options);
        },
        userDataComplete: function(data){
        },
        /* 重置页面高度 */
        resetPageH: function (arr) {
            var $con = $('.container'),
                $side = $('.rightside'),
                footerH = $('#footer').height(),
                headerH = $('#header').height(),
                sideH = we.utils.isEmpty($side.height() - 74) ? 0 : $side.height() - 74,
                itemH = we.browser.h - headerH - footerH - 170;
            itemH = itemH < sideH ? sideH : itemH;
            if (arr.length) {
                $.each(arr, function (i, item) {
                    if ($(item, $con).size() > 0 && $(item, $con).height() < itemH) {
                        $(item, $con).css('min-height', itemH);
                    }
                });
            }
        },
        /* 刷新顶部工具条 */
        refreshHead: function () {
        	var home;
            if (we.isLogin) {
            	$('#loginbar').show();
            	var $info=$('.login-info-nick');
				if(!we.utils.isEmpty(we.user.name)){
					$info.find('.userName').html(we.user.name.replaceAll("\"",""));
					
	            	$('#loginbar').find('.user-name').html(we.user.name.replaceAll("\"",""));
				}
				if(!we.utils.isEmpty(we.user.face_url)){					
					$('#loginbar').find('.user-img').find('img').attr('src',we.vr.static+"/"+we.user.face_url.replaceAll("\"","")+'!small');
					$info.find('img').attr('src',we.vr.static+"/"+we.user.face_url.replaceAll("\"","")+'!small');
				}
				if(we.user.newMsgCount<=0){
					$('.newMsgCount').hide();
				}else{		
					$('.newMsgCount').show();
					$('.newMsgCount').html(we.user.newMsgCount);
				}
				//
				if(we.user.isVip){
					$info.find('i').addClass('king-vip');
				}
            } 
        },
        /* 添加登录监听 */
		addLoginEvent: function(options){
			options = $.extend({
				islogin: null,
				unlogin: null,
				complete:null,
				data: null
        	},options);
			
	    	if(we.utils.isEmpty(we.isLogin)){
				$event.on(vkoEvent.LOGIN, function(){
					_loginAction(options);
				});
			}else{
				_loginAction(options);
			}
	    }
    });
	we.alert=$.extend(we.alert, {
		/**
	     * @name error
	     * @function   
	     * @desc 警告级别的消息提示
	     * 		<p>
	     * 		错误提示和顺序:
	     * 		<ul>
	     * 			<li>如果后台出现业务异常（即BusinessException），则显示后台的错误信息（即data.message）</li>	
	     * 			<li>如果后台的异常不是业务异常，则显示输入的错误信息（即message）</li>	
	     * 		</ul>
	     * 		
	     * @param message 信息
	     * @param data 后台回传过来的数据
	     * @param time 消息提示显示的时长，单位是毫秒
	     * @param callback 回调函数
	     * @param options 选项
	     *
	     * @example
	     * define(function(require) {
	     *     $ajax.weAjax({
		 *     		url: 'url',
		 *    		data:{
		 *     			name:'name'
		 *     		},
		 *     		success:function(data){
		 *     			//dosomething();
		 *    		},
		 *   		error:function(data){
		 *     			we.alert.warning('生成订单失败，请稍后再试或者联系我们',data);
		 *     			//如果后台出现的是业务异常，则显示的是错误信息是：data.message
		 *     			//否则显示的是错误信息是：'生成订单失败，请稍后再试或者联系我们'
		 *    		 }
		 *     });
	     * });
	     */
		warning:function(message,data,time,callback,options){
			var msg = message;
			if(!we.utils.isEmpty(data)&&!we.utils.isEmpty(data.isBusinessError)&&data.isBusinessError){
				msg=data.message;
			}
			$udialog.alert(msg,'warning',time,callback,options);
		},
		/**
	     * @name error
	     * @function   
	     * @desc 错误级别的消息提示
	     * 		<p>
	     * 		错误提示和顺序:
	     * 		<ul>
	     * 			<li>如果后台出现业务异常（即BusinessException），则显示后台的错误信息（即data.message）</li>	
	     * 			<li>如果后台的异常不是业务异常，则显示输入的错误信息（即message）</li>	
	     * 		</ul>
	     * 		
	     * @param message 信息
	     * @param data 后台回传过来的数据
	     * @param time 消息提示显示的时长，单位是毫秒
	     * @param callback 回调函数
	     * @param options 选项
	     *
	     * @example
	     * define(function(require) {
	     *     $ajax.weAjax({
		 *     		url: 'url',
		 *    		data:{
		 *     			name:'name'
		 *     		},
		 *     		success:function(data){
		 *     			//dosomething();
		 *    		},
		 *   		error:function(data){
		 *     			we.alert.error('生成订单失败，请稍后再试或者联系我们',data);
		 *     			//如果后台出现的是业务异常，则显示的是错误信息是：data.message
		 *     			//否则显示的是错误信息是：'生成订单失败，请稍后再试或者联系我们'
		 *    		 }
		 *     });
	     * });
	     */
		error:function(message,data,time,callback,options){
			var msg = message;
			if(!we.utils.isEmpty(data)&&!we.utils.isEmpty(data.isBusinessError)&&data.isBusinessError){
				msg=data.message;
			}
			$udialog.alert(msg,'error',time,callback,options);
		}
	});
	
	 var _loginAction = function(options){
    	if(we.isLogin){
			if(!we.utils.isEmpty(options.islogin)){
				options.islogin.call(this, options.data);
			}
		}else{
			if(!we.utils.isEmpty(options.unlogin)){
				options.unlogin.call(this, options.data);
			}
		}
		if(!we.utils.isEmpty(options.complete)){
			options.complete.call(this, options.data);
		}
    }
    
    var _userDataComplete = function(data){
   	 	$.extend(we.user,data);
        we.isLogin = !we.utils.isEmpty(we.user.id);
        $event.trigger(vkoEvent.LOGIN);
   }
   
   var _success = function(data, $dlg, callback){
	   _userDataComplete(data);
   	
       if(!we.utils.isEmpty(callback)){
       	callback.call();
       	// 刷新头部
       	we.utils.refreshHead();
       }else{
       	window.location.reload();
       }
       if(!we.utils.isEmpty($dlg)){
       	$dlg.close();
       }
	}
 
});
