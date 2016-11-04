define('we/plugins/dialog/2.0.0/jquery-dialog/jquery-dialog-plugins',function (require, exports, module) {
	
    /*!
     * artDialog 5 plugins
     * Date: 2012-03-16
     * https://github.com/aui/artDialog
     * (c) 2009-2012 TangBin, http://www.planeArt.cn
     *
     * This is licensed under the GNU LGPL, version 2.1 or later.
     * For details, see: http://creativecommons.org/licenses/LGPL/2.1/
     */
	var $ = require('jquery');
    (function ($) {
    	
    	/**
         * 获取当前弹框
         * @param   {String, HTMLElement}   消息内容
         * @param   {Function}              确定按钮回调函数
         * @param   {Function}              取消按钮回调函数
         */
    	$.dialog.getCurrent = function () {
    		if(we.utils.isEmpty($.dialog.getFocus())){
    			return $('nNll');
    		}else{
    			return $.dialog.getFocus().dom.wrap;
    		}
        };
        $.dialog.getFocus = function(){
        	if($.dialog.focus!=null){
        		return $.dialog.focus;
        	}
        	var focusDlg = null, zindex=-9999;
    		$.each($.dialog.list,function(key,value){
    			if(zindex < value.dom.wrap.css('z-index')){
    				focusDlg = value;
    			}
    		});
    		return focusDlg;
        }
        $.dialog.closeCurrent = function () {
        	$.dialog.getFocus().close();
        };
        $.dialog.reload = function (url, options) {
        	var op = $.extend({data:{}, dialogId:"", callback:null}, options);
			var dialog = $.dialog.getCurrent();
			if (dialog){
				$ajax.vkoAjax({
    				url: url,
    				data: op.data,
    				type: 'post',
    				success: function(data){
    					$('.d-content', dialog).html(data).initUI();
    					if ($.isFunction(op.callback)) op.callback(data);
    				}
    			});
			}
        };

        /**
         * 警告
         * @param   {String, HTMLElement}   消息内容
         * @param   {Function}              (可选) 回调函数
         */
        $.alert = $.dialog.alert = function (options) {
			var op = $.extend({
				zIndex:1989
			},options);
        	var time_def = 3000,
        		title;
        	
        	if(typeof op.content == 'number'){
        		op.content += '';
        	}
        	if(typeof op.content != 'string'){
        		seajs.log("调用 $dialog.alert 时类型出错！暂只支持字符型，请检查。");
        		return;
        	}
        	
        	//关闭前一个弹框
        	if(!we.utils.isEmpty($dialog.get('Alert'))){
        		$dialog.get('Alert').close();
        	}
        	
        	if(typeof op.type == 'number'){
        		time_def = op.type;
        		op.type = 'message';
        	}else if(typeof op.type == 'function'){
        		op.callback = op.type;
        		op.type = 'message';
	    	}
        	if(typeof time == 'function'){
        		op.callback = time;
        	}
        	if(typeof time == 'number'){
        		time_def = time;
        	}
        	
        	if(op.type=='success'){
    			title = '成功啦';
    			op.content = '<span class="alert-success"></span><span>' + op.content + '</span>';
    		}else if(op.type=='warning'){
    			title = '提醒';
    			op.content = '<span class="alert-warning"></span><span>' + op.content + '</span>';
    		}else if(op.type=='error'){
    			title = '出错啦';
    			op.content = '<span class="alert-error"></span><span>' + op.content + '</span>';
    		}else if(op.type=='loading'){
    			title = false;
    			esc = false;
    			op.content = '<span class="alert-loading">' + op.content + '<span class="loading-blue"></span></span>';
    			setTimeout(function(){
            		$('.d-mask').unbind('dblclick');
            	},200);
    			return $.alert({
    				content: op.content,
    				type: op.type,
    				callback:op.callback,
    				okValue: '知道la'
                });
    		}else{
    			title = '消息';
    		}
        	setTimeout(function(){
    			if(we.browser.ie6)$('.d-mask').bgiframe();
    		},100);
            return  $.dialog({
            	title: title,
                id: 'Alert',
                fixed: true,
                lock: true,
                time: time_def,
                padding: '15px 30px',
                content: op.content,
                okValue: '知道',
                ok: true,
				zIndex : op.zIndex,
                beforeunload: op.callback
            });
        };


        /**
         * 确认选择
         * @param   {String, HTMLElement}   消息内容
         * @param   {Function}              确定按钮回调函数
         * @param   {Function}              取消按钮回调函数
         */
        $.confirm = $.dialog.confirm = function (options) {
        	options = $.extend({
        		id: 'Confirm',
        		title: '提醒',
        		content: '亲，你确定要执行本操作？',
        		fixed: true,
                lock: true,
                padding: '15px 30px',
        		okValue: '确定',
        		ok: true,
        		cancelValue: '取消',
        		cancel: true
            }, options);
        	
        	options.content = '<span class="d-confirm"></span><span>' + options.content + '</span>';
        	setTimeout(function(){
    			if(we.browser.ie6)$('.d-mask').bgiframe();
    		},100);
            return $.dialog(options);
        };


        /**
         * 输入框
         * @param   {String, HTMLElement}   消息内容
         * @param   {Function}              确定按钮回调函数。函数第一个参数接收用户录入的数据
         * @param   {String}                输入框默认文本
         */
        $.prompt = $.dialog.prompt = function (content, ok, defaultValue) {
            defaultValue = defaultValue || '';
            var input;

            return $.dialog({
                id: 'Prompt',
                fixed: true,
                lock: true,
                content: [
                        '<div style="margin-bottom:5px;font-size:12px">',
                        content,
                        '</div>',
                        '<div>',
                        '<input type="text" class="d-input-text" value="',
                        defaultValue,
                        '" style="width:18em;padding:6px 4px" />',
                        '</div>'
                ].join(''),
                initialize: function () {
                    input = this.dom.content.find('.d-input-text')[0];
                    input.select();
                    input.focus();
                },
                ok: function () {
                    return ok && ok.call(this, input.value);
                },
                cancel: function () {}
            });
        };


        /** 抖动效果 */
        $.dialog.prototype.shake = (function () {

            var fx = function (ontween, onend, duration) {
                var startTime = +new Date;
                var timer = setInterval(function () {
                    var runTime = +new Date - startTime;
                    var pre = runTime / duration;

                    if (pre >= 1) {
                        clearInterval(timer);
                        onend(pre);
                    } else {
                        ontween(pre);
                    };
                }, 13);
            };

            var animate = function (elem, distance, duration) {
                var quantity = arguments[3];

                if (quantity === undefined) {
                    quantity = 6;
                    duration = duration / quantity;
                };

                var style = elem.style;
                var from = parseInt(style.marginLeft) || 0;

                fx(function (pre) {
                    elem.style.marginLeft = from + (distance - from) * pre + 'px';
                }, function () {
                    if (quantity !== 0) {
                        animate(
                            elem,
                            quantity === 1 ? 0 : (distance / quantity - distance) * 1.3,
                            duration,
                        --quantity);
                    };
                }, duration);
            };

            return function () {
                animate(this.dom.wrap[0], 40, 600);
                return this;
            };
        })();


        // 拖拽支持
        var DragEvent = function () {
            var that = this,
                proxy = function (name) {
                    var fn = that[name];
                    that[name] = function () {
                        return fn.apply(that, arguments);
                    };
                };

            proxy('start');
            proxy('over');
            proxy('end');
        };


        DragEvent.prototype = {

            // 开始拖拽
            // onstart: function () {},
            start: function (event) {
                $(document)
                    .bind('mousemove', this.over)
                    .bind('mouseup', this.end);

                this.x = event.clientX;
                this.y = event.clientY;
                this.onstart(event.clientX, event.clientY);

                return false;
            },

            // 正在拖拽
            // onover: function () {},
            over: function (event) {
                this.onover(
                    event.clientX - this.x,
                    event.clientY - this.y);

                return false;
            },

            // 结束拖拽
            // onend: function () {},
            end: function (event) {
                $(document)
                    .unbind('mousemove', this.over)
                    .unbind('mouseup', this.end);

                this.onend(event.clientX, event.clientY);
                return false;
            }

        };

        var $window = $(window),
            $document = $(document),
            html = document.documentElement,
            isIE6 = !('minWidth' in html.style),
            isLosecapture = !isIE6 && 'onlosecapture' in html,
            isSetCapture = 'setCapture' in html,
            dragstart = function () {
                return false
            };

        var dragInit = function (event) {

            var dragEvent = new DragEvent,
                api = artDialog.focus,
                dom = api.dom,
                $wrap = dom.wrap,
                $title = dom.title,
                $main = dom.main,
                wrap = $wrap[0],
                title = $title[0],
                main = $main[0],
                wrapStyle = wrap.style,
                mainStyle = main.style;


            var isResize = event.target === dom.se[0] ? true : false;
            var isFixed = wrap.style.position === 'fixed',
                minX = isFixed ? 0 : $document.scrollLeft(),
                minY = isFixed ? 0 : $document.scrollTop(),
                maxX = $window.width() - wrap.offsetWidth + minX,
                maxY = $window.height() - wrap.offsetHeight + minY;


            var startWidth, startHeight, startLeft, startTop;


            // 对话框准备拖动
            dragEvent.onstart = function (x, y) {

                if (isResize) {
                    startWidth = main.offsetWidth;
                    startHeight = main.offsetHeight;
                } else {
                    startLeft = wrap.offsetLeft;
                    startTop = wrap.offsetTop;
                };

                $document.bind('dblclick', dragEvent.end)
                    .bind('dragstart', dragstart);

                if (isLosecapture) {
                    $title.bind('losecapture', dragEvent.end)
                } else {
                    $window.bind('blur', dragEvent.end)
                };

                isSetCapture && title.setCapture();

                $wrap.addClass('d-state-drag');
                api.focus();
            };

            // 对话框拖动进行中
            dragEvent.onover = function (x, y) {

                if (isResize) {
                    var width = x + startWidth,
                        height = y + startHeight;

                    wrapStyle.width = 'auto';
                    mainStyle.width = Math.max(0, width) + 'px';
                    wrapStyle.width = wrap.offsetWidth + 'px';

                    mainStyle.height = Math.max(0, height) + 'px';

                } else {
                    var left = Math.max(minX, Math.min(maxX, x + startLeft)),
                        top = Math.max(minY, Math.min(maxY, y + startTop));

                    wrapStyle.left = left + 'px';
                    wrapStyle.top = top + 'px';
                };


            };

            // 对话框拖动结束
            dragEvent.onend = function (x, y) {

                $document.unbind('dblclick', dragEvent.end)
                    .unbind('dragstart', dragstart);

                if (isLosecapture) {
                    $title.unbind('losecapture', dragEvent.end);
                } else {
                    $window.unbind('blur', dragEvent.end)
                };

                isSetCapture && title.releaseCapture();

                $wrap.removeClass('d-state-drag');
            };


            dragEvent.start(event);

        };


        // 代理 mousedown 事件触发对话框拖动
        $(document).bind('mousedown', function (event) {
            var api = artDialog.focus;
            if (!api) return;

            var target = event.target,
                config = api.config,
                dom = api.dom;

            if (config.drag !== false && target === dom.title[0] || config.resize !== false && target === dom.se[0]) {
                dragInit(event);

                // 防止firefox与chrome滚屏
                return false;
            };
        });


    }(this.art || this.jQuery));
});