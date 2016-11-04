define(function(require){
	/* 自定义元素方法 */
    (function($){
    	$.extend($.fn,{
    		initUI: function(){
    			return this.each(function(){
    				we.ui.init(this);
    			});
    		},
			ajaxUrl: function(op){
				var $this = $(this);
				if ($.fn.xheditor) {
					$("textarea.editor", $this).xheditor(false);
				}
				
				$ajax.vkoAjax({
					type: op.type || 'GET',
					url: op.url,
					data: op.data,
					cache: false,
					success: function(json){
						$this.html(json).initUI();
					}
				});
			},
			loadUrl: function(url,data,callback){
				$(this).ajaxUrl({url:url, data:data, callback:callback});
			},
    		/* 鼠标经过className替换 */
        	hoverClass:function(className){
    			var _className = className || 'hover';
    			return this.each(function(){
    				$(this).hover(function(){
    					$(this).addClass(_className);
    				},function(){
    					$(this).removeClass(_className);
    				});
    			});
    		},
        	/* 加载登录 */
        	loadLogin:function(options){
        		var op = $.extend({
	        			loaded: null,
	            		callback: null
	            	},options),
	            	$this = $(this),
        			$login = require('login');
        		
        		op.callback = eval($this.attr('callback') || '')
        		if(!we.utils.isEmpty($this.data('loadLogin')) && $this.data('loadLogin'))return;
        		$login.init(op, function(data){
        			$this.empty().append($(data).html()).addClass('vkoLoginModule').data('loadLogin', true);
        			if(!we.utils.isEmpty(op.loaded)){
        				op.loaded.call();
        			}
    			});
        	},
        	/* 自定义右键菜单 */
        	contextMenu: function(options){
        		var op = $.extend({
        			id: 'userMenu',
        			content: null, 
        		    shadow : true,
        		    bindings:{},
        			ctrSub:null
        		}, options),
        		$this = $(this),
        		$menu = $('#contextMenu');
        	
        		if($menu.size()<1) {
        			$menu = $('<div id="contextMenu"></div>').appendTo('body').hide();
        		}
        		
        		$this.bind('contextmenu', function(e) {
        			var $content = $(op.content);
        				
        			if(!we.utils.isEmpty($content.attr('id'))){
        				op.id = $content.attr('id');
        			}
        			$content.attr('id',op.id).find('li').hoverClass();
        			$menu.html($content);
        			$.each(op.bindings, function(id, func) {
        				$('[rel='+id+']', $menu).bind('click', function(e) {
        					$menu.hide();
        					func($this, $('#'+op.id));
        				});
        			});
        			
        			var posX = e.pageX;
        			var posY = e.pageY;
        			
        			if($(window).width() < posX + $menu.width()) posX -= $menu.width();
        			if($(window).height() < posY + $menu.height()) posY -= $menu.height();
        			$menu.css({'left':posX,'top':posY}).show();
        			$(document).one('click', function(){
            			$menu.hide();
            		});
        			if($.isFunction(op.ctrSub)) {op.ctrSub($this, $('#'+op.id));}
        			
        			return false;
        		});
        		
        		return this;
        	},
        	/**
    		 * 判断当前元素是否已经绑定某个事件
    		 * @param {Object} type
    		 */
    		isBind:function(type) {
    			var _events = $(this).data("events");
    			return _events && type && _events[type];
    		},
        	/* 弹框 */
        	showDlg: function(data){
        		var $this = $(this);
				$dialog.dialog({
					id: $this.attr('rel'),
 	           		title: $this.attr('title') || $this.text(),
 	           		content: data,
 	           		width: ($this.attr('width')?$this.attr('width')+'px':'auto'),
 	           		height: ($this.attr('height')?$this.attr('height')+'px':'auto'),
 	           		padding: '20px 0',
 	           		fixed: $this.attr('fixed'),
 	           		lock: $this.attr('mask') || 'true',
 	           		follow: $($this.attr('follow'))[0],
 	           		initialize: function(){
 	           			var $dlg = this,
 	           				$cont = $dlg.dom.wrap,
 	           				$close = $cont.find('.closeDialog');
 	           			$cont.find('a[target=dialog][isDiv]').each(function(){
 	           				var $this = $(this),
 	           					$div = $($(this).attr('isDiv'),$cont);
 	           				if($div.size()>0){
 	           					$close = $close.not($div.find('.closeDialog'));
 	           				}
 	           			});
 	           			$close.bind('click',function(){
 	           				$dlg.close();
 	           			});
 	           			$cont.initUI();
 	           		},
 	           		ok: eval($this.attr('ok') || ''),
 	           		okValue: false,
 	           		cancel: eval($this.attr('cancel') || ''),
 	           		cancelValue: false,
 	           		beforeunload: eval($this.attr('close') || ''),
 	           		param: we.utils.jsonEval($this.attr('param') || '')
				});
			},
        	/* 标签判断 */
        	isTag:function(tn) {
    			if(!tn) return false;
    			return $(this)[0].tagName.toLowerCase() == tn?true:false;
    		},
    		/* z-index调整bgiframe方法 */
    		bgiframe: function(options){
    			options = $.extend({
    	            top         : 'auto', // auto == borderTopWidth
    	            left        : 'auto', // auto == borderLeftWidth
    	            width       : 'auto', // auto == offoptionsetWidth
    	            height      : 'auto', // auto == offsetHeight
    	            opacity     : true,
    	            src         : 'javascript:false;',
    	            conditional : /MSIE 6.0/.test(navigator.userAgent)
    	        }, options);

    	        if (!$.isFunction(options.conditional)) {
    	            var condition = options.conditional;
    	            options.conditional = function() { return condition; };
    	        }

    	        var $iframe = $('<iframe class="bgiframe" frameborder="0" tabindex="-1" src="'+options.src+'"'+
    	                           'style="display:block;position:absolute;z-index:-1;"/>');
    	        var prop = function(n) {
    		        return n && n.constructor === Number ? n + 'px' : n;
    		    }
    	        return this.each(function() {
    	            var $this = $(this);
    	            if ( options.conditional(this) === false ) { return; }
    	            var existing = $this.children('iframe.bgiframe');
    	            var $el = existing.length === 0 ? $iframe.clone() : existing;
    	            $el.css({
    	                'top': options.top == 'auto' ?
    	                    ((parseInt($this.css('borderTopWidth'),10)||0)*-1)+'px' : prop(options.top),
    	                'left': options.left == 'auto' ?
    	                    ((parseInt($this.css('borderLeftWidth'),10)||0)*-1)+'px' : prop(options.left),
    	                'width': options.width == 'auto' ? (this.offsetWidth + 'px') : prop(options.width),
    	                'height': options.height == 'auto' ? (this.offsetHeight + 'px') : prop(options.height),
    	                'opacity': options.opacity === true ? 0 : undefined
    	            });

    	            if ( existing.length === 0 ) {
    	                $this.prepend($el);
    	            }
    	        });
    		},
    		/* 当前navTab中链接ajax post扩展 */
    		ajaxTodo:function(){
    			return this.each(function(){
    				var $this = $(this);
    				$this.click(function(event){
    					var url = $this.attr('href');
    					var title = $this.attr('title');
    					if(title) {
    						$dialog.confirm({
    							title: title,
    							ok: function(){
    								ajaxTodo(url, $this.attr('callback'));
    							}
    						});
    					} else {
    						ajaxTodo(url, $this.attr('callback'));
    					}
    					event.preventDefault();
    				});
    			});
    		},
    		dwzExport: function(){
    			function _doExport($this) {
    				var $p = $this.attr("targetType") == "dialog" ?  $.dialog.getCurrent() : we.ui.navTab.getCurrentPanel();
    				var $form = $("#pagerForm", $p);
    				var url = $this.attr("href");
    				window.location = url+(url.indexOf('?') == -1 ? "?" : "&")+$form.serialize();
    			}
    			
    			return this.each(function(){
    				var $this = $(this);
    				$this.click(function(event){
    					var title = $this.attr("title");
    					if(title) {
    						$dialog.confirm({
    							title: title,
    							ok: function(){_doExport($this);}
    						});
    					} else {
    						_doExport($this);
    					}
    				
    					event.preventDefault();
    				});
    			});
    		},
    		/* 查找带回 */
    		lookup: function(){
    			return this.each(function(){
    				var $this = $(this);
    				
    				$this.click(function(event){
    					_lookup = $.extend(we._lookup, {
    						$target: $this
    					});
    					var url = $this.attr('href');
    					$ajax.vkoAjax({
    	    				url: url,
    	    				success: function(data){
    	    					$this.showDlg(data);
    	    				}
    	    			});
    					return false;
    				});
    			});
    		},
			multLookup: function(){
				return this.each(function(){
					var $this = $(this), args={};
					$this.click(function(event){
						var $unitBox = $this.parents(".lookupDiv:first");
						$unitBox.find("[name='"+$this.attr("multLookup")+"']").filter(":checked").each(function(){
							$parsejson = require('parsejson')
							var _args = $parsejson($(this).val());
							for (var key in _args) {
								var value = args[key] ? args[key]+"," : "";
								args[key] = value + _args[key];
							}
						});
	
						if ($.isEmptyObject(args)) {
							$dialog.alert($this.attr("warn"),'warning');
							return false;
						}
						$.bringBack(args);
					});
				});
			},
    		selectedTodo: function(){
    			function _getIds(selectedIds, targetType){
    				var ids = '';
    				var $box = targetType == 'dialog' ? $.dialog.getCurrent() : we.ui.navTab.getCurrentPanel();
    				$box.find('input:checked').filter('[name='+selectedIds+']').each(function(i){
    					var val = $(this).val();
    					ids += i==0 ? val : ','+val;
    				});
    				return ids;
    			}
    			return this.each(function(){
    				var $this = $(this);
    				var selectedIds = $this.attr('rel') || 'ids';
    				var postType = $this.attr('postType') || 'map';

    				$this.click(function(){
    					var targetType = $this.attr('targetType');
    					var ids = _getIds(selectedIds, targetType);
    					if(!ids) {
    						$dialog.alert($this.attr('warning') || '请选择信息','warning');
    						return false;
    					}
    					
    					var _callback = $this.attr('callback') || (targetType == 'dialog' ? dialogAjaxDone : navTabAjaxDone);
    					if(! $.isFunction(_callback)) _callback = eval('(' + _callback + ')');
    					
    					function _doPost(){
    						$ajax.vkoAjax({
    							url:$this.attr('href'),
    							data: function(){
    								if(postType == 'map'){
    									return $.map(ids.split(','), function(val, i) {
    										return {name: selectedIds, value: val};
    									})
    								} else {
    									var _data = {};
    									_data[selectedIds] = ids;
    									return _data;
    								}
    							}(),
    							type:'POST',
    							dataType:'json',
    							success: _callback
    						});
    					}
    					var title = $this.attr('title');
    					if(title) {
    						$dialog.confirm({
    							title: title,
    							ok: _doPost
    						});
    					} else {
    						_doPost();
    					}
    					return false;
    				});
    				
    			});
    		}
    	});
    	
    	we._lookup = {$target:null};
    	$.extend({
    		bringBackSuggest: function(args){
    			var $box = we._lookup['$target'].parents('.d-content');
    			if($box.size()<=0){
    				$box = we._lookup['$target'].parents('.lookup_panel');
    			}
    			$box.find(':input').each(function(){
    				var $input = $(this), inputName = $input.attr('name');
    				for (var key in args) {
    					if(key == inputName) {
    						$input.val(args[key]);
    						break;
    					}
    				}
    			});
    		},
    		bringBack: function(args){
    			$.bringBackSuggest(args);
    			$.dialog.closeCurrent();
    		}
    	});
	})(jQuery);
});