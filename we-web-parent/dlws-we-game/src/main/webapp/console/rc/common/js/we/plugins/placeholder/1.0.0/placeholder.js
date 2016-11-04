define(function (require, exports, module) {

    /**
     * @name Placeholder
     * @class 对不支持 Html5 placeholder 的占位符兼容解决方案。（已经支持 placeholder 的浏览器下不做任何事）
     * @requires jQuery
     * @author 李博龙
     * @version v1.0.1
     */

    /**
     * @name Placeholder#placeholder
     * @function   
     * @desc element 可以是标签或选择器，如果不选，会默认选择整个 body 下的所有input及textarea。
     * @param {element} element
     * @example
     * //全局调用
     * seajs.use('placeholder');
     * @example
     * //部分调用
     * seajs.use('placeholder', function(placeholder) {
     *     placeholder('#test'); 
     * });
     */
    var $ = require('jquery'),
    	hasError = false,
        placeholder;

    // 以下代码引用，稍微修改了点
    var ret = (function ($) {

        var isInputSupported = 'placeholder' in document.createElement('input'),
            isTextareaSupported = 'placeholder' in document.createElement('textarea'),
            // 这里的修改是为了防止修改$.fn
            // prototype = $.fn,
            prototype = {},
            valHooks = $.valHooks,
            hooks,
            placeholder;

        if (isInputSupported && isTextareaSupported) {

            placeholder = prototype.placeholder = function () {
                return this;
            };

            placeholder.input = placeholder.textarea = true;

        } else {

            placeholder = prototype.placeholder = function () {
                var $this = this;
                $this.filter((isInputSupported ? 'textarea' : ':input') + '[placeholder]')
                    .unbind({
                    'focus.placeholder': clearPlaceholder,
                    'blur.placeholder': setPlaceholder
                })
                    .bind({
                    'focus.placeholder': clearPlaceholder,
                    'blur.placeholder': setPlaceholder
                })
                    .data('placeholder-enabled', true)
                    .trigger('blur.placeholder');
                return $this;
            };

            placeholder.input = isInputSupported;
            placeholder.textarea = isTextareaSupported;

            hooks = {
                'get': function (element) {
                    var $element = $(element);
                    return $element.data('placeholder-enabled') && $element.hasClass('placeholder') ? '' : element.value;
                },
                'set': function (element, value) {
                    var $element = $(element);
                    if (!$element.data('placeholder-enabled')) {
                        return element.value = value;
                    }
                    if (value == '') {
                        element.value = value;
                        // Issue #56: Setting the placeholder causes problems if the element continues to have focus.
                        if (element != document.activeElement) {
                            // We can't use `triggerHandler` here because of dummy text/password inputs :(
                            setPlaceholder.call(element);
                        }
                    } else if ($element.hasClass('placeholder')) {
                        clearPlaceholder.call(element, true, value) || (element.value = value);
                    } else {
                        element.value = value;
                    }
                    // `set` can not return `undefined`; see http://jsapi.info/jquery/1.7.1/val#L2363
                    return $element;
                }
            };


            //isInputSupported || (valHooks.input = hooks);
            //isTextareaSupported || (valHooks.textarea = hooks);

            // 这里的修改是为了防止别的hooks被覆盖
            if (!isInputSupported) {
                var _old = valHooks.input;

                if (_old) {
                    valHooks.input = {
                        'get': function () {
                            if (_old.get) {
                                _old.get.apply(this, arguments);
                            }

                            return hooks.get.apply(this, arguments);
                        },
                        'set': function () {
                            if (_old.set) {
                                _old.set.apply(this, arguments);
                            }

                            return hooks.set.apply(this, arguments);
                        }
                    };
                } else {
                    valHooks.input = hooks;
                }
            }
            if (!isTextareaSupported) {
                var _old = valHooks.textarea;

                if (_old) {
                    valHooks.textarea = {
                        'get': function () {
                            if (_old.get) {
                                _old.get.apply(this, arguments);
                            }

                            return hooks.get.apply(this, arguments);
                        },
                        'set': function () {
                            if (_old.set) {
                                _old.set.apply(this, arguments);
                            }

                            return hooks.set.apply(this, arguments);
                        }
                    };
                } else {
                    valHooks.textarea = hooks;
                }
            }

            $(function () {
                // Look for forms
//                $(document).delegate('form', 'submit.placeholder', function () {
//                    // Clear the placeholder values so they don't get submitted
//                    var $inputs = $('.placeholder', this).each(clearPlaceholder);
//                    setTimeout(function () {
//                        $inputs.each(setPlaceholder);
//                    }, 10);
//                });
            });

            // Clear placeholder values upon page reload
            $(window).bind('beforeunload.placeholder', function () {
                $('.placeholder').each(function () {
                    this.value = '';
                });
            });

        }

        function args(elem) {
            // Return an object of element attributes
            var newAttrs = {},
                rinlinejQuery = /^jQuery\d+$/;
            $.each(elem.attributes, function (i, attr) {
                if (attr.specified && !rinlinejQuery.test(attr.name)) {
                    newAttrs[attr.name] = attr.value;
                }
            });
            return newAttrs;
        }

        function clearPlaceholder(event, value) {
            var input = this,
                $input = $(input);
            //alert('I am placeholder focus:'+input.value+'  '+((input.value == $input.attr('placeholder') || input.value == '') && $input.hasClass('placeholder')));
            // 修改演示四出现的问题
            if ((input.value == $input.attr('placeholder') || input.value == '') && $input.hasClass('placeholder')) {
                if ($input.data('placeholder-password')) {
                    $input = $input.hide().next().show().attr('id', $input.removeAttr('id').data('placeholder-id'));
                    // If `clearPlaceholder` was called from `$.valHooks.input.set`
                    if (event === true) {
                        return $input[0].value = value;
                    }
                    try{$input.focus();}catch(e){}
                } else {
                    input.value = '';
                    $input.removeClass('placeholder');
                    input == document.activeElement && input.select();
                }
            }
        }

        function setPlaceholder() {
        	//alert('I am placeholder blur');
            var $replacement,
                input = this,
                $input = $(input),
                $origInput = $input,
                id = this.id;
            if ($(input).val() == '') {
                if (input.type == 'password') {
                	if(hasError)return;
                    if (!$input.data('placeholder-textinput')) {
                        try {
                            $replacement = $input.clone().attr({
                                'type': 'text'
                            });
                        } catch (e) {
                            $replacement = $('<input>').attr($.extend(args(this), {
                                'type': 'text'
                            }));
                        }
                        $replacement.removeAttr('name')
                            .data({
                            'placeholder-password': true,
                            'placeholder-id': id
                        })
                            .bind('focus.placeholder', clearPlaceholder);
                        $input.data({
                            'placeholder-textinput': $replacement,
                            'placeholder-id': id
                        })
                            .before($replacement);
                    }
                    $input = $input.removeAttr('id').hide().prev().attr('id', id).show();
                    // Note: `$input[0] != input` now!
                }
                $input.addClass('placeholder');
                $input[0].value = $input.attr('placeholder');
            } else {
                $input.removeClass('placeholder');
            }
        }

        return placeholder;

    })($);

    // 做简单的api封装
    placeholder = function(){
    	try{
	    	(!ret.input || !ret.textarea) ? function (element) {
	            if (!element) {
	                element = $('input, textarea');
	            }
	            if (element) {
	                ret.call($(element));
	            }
	        }() : '';
    	}catch(e){
    		hasError = true;
		}
    }

    // 默认运行，这样就不需要手动调用
    

    module.exports = placeholder;
});