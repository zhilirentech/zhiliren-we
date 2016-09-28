define(function (require, exports, module) {

    /**
     * @name Select
     * @class 提供系统select美化方法。
     * @requires jQuery
     * @author 李博龙
     * @version v1.0.1
     * @example 
     * define(function(require) {
     *     require('select')();
     * });
     */
	var $ = require('jquery');
	require('./select.css');
	
	/*
	 * DropKick 1.3.2
	 *
	 * Highly customizable <select> lists
	 * https://github.com/robdel12/DropKick
	 *
	 * Created by: Jamie Lottering <http://github.com/JamieLottering> <http://twitter.com/JamieLottering>
	 *
	 *
	*/

	(function ($, window, document) {
	  'use strict';

	  var
	    msVersion = navigator.userAgent.match(/MSIE ([0-9]{1,}[\.0-9]{0,})/),
	    msie = !!msVersion,
	    ie6 = msie && parseFloat(msVersion[1]) < 7,
	    isMobile = navigator.userAgent.match(/iPad|iPhone|Android|IEMobile|BlackBerry/i),

	    // Public methods exposed to $.fn.dropkick()
	    methods = {},

	    // Cache every <select> element that gets dropkicked
	    lists   = [],

	    // Convenience keys for keyboard navigation
	    keyMap = {
	      'left'  : 37,
	      'up'    : 38,
	      'right' : 39,
	      'down'  : 40,
	      'enter' : 13,
	      'tab'   : 9,
	      'zero'  : 48,
	      'z'     : 90,
	      'last'  : 221  //support extend charsets such as Danish, Ukrainian etc.
	    },

	    // HTML template for the dropdowns
	    dropdownTemplate = [
	      '<div class="vkoslct_container" id="vkoslct_container_{{ id }}" tabindex="{{ tabindex }}">',
	      '<a class="vkoslct_toggle">',
	      '<span class="vkoslct_label">{{ label }}</span>',
	      '</a>',
	      '<div class="vkoslct_options">',
	      '<ul class="vkoslct_options_inner">',
	      '</ul>',
	      '</div>',
	      '</div>'
	    ].join(''),

	    // HTML template for dropdown options
	    optionTemplate = '<li class="{{ current }} {{ disabled }}"><a data-dk-dropdown-value="{{ value }}">{{ text }}</a></li>',

	    // Some nice default values
	    defaults = {
	      startSpeed    : 400,  // I reccomend a low value (lowest is probably 100) to stop a "fade in" effect.
	      theme         : false,
	      changes       : false,
	      syncReverse   : true,
	      nativeMobile  : true
	    },

	    // Make sure that only one dropdown is open the document
	    $opened = null,

	    // Make sure that only one dropdown has focus in the document
	    $focused = null,

	    // private
	    // Update the <select> value, and the dropdown label
	    updateFields = function(option, $dk, reset) {
	      var value, label, data, $select;

	      value = option.attr('data-dk-dropdown-value');
	      label = option.text();
	      data  = $dk.data('dropkick');

	      $select = data.$select;
	      $select.val(value).trigger('change'); // Added to let it act like a normal select [Acemir >> In fact, I suggest that this must always be true]
	      
	      methods.getOptions($select);
	      
	      $dk.find('.vkoslct_label').text(label);

	      reset = reset || false;

	      if (data.settings.change && !reset && !data.settings.syncReverse) {
	        data.settings.change.call($select, value, label);
	      }
	    },

	    // Close a dropdown
	    closeDropdown = function($dk) {
	      $dk.removeClass('vkoslct_open').parents('.form_item:first').css('z-index','1');
	      $opened = null;
	    },

	    // Report whether there is enough space in the window to drop down.
	    enoughSpace = function($dk)  {
	      var
	        $vkoslct_toggle = $dk.find('.vkoslct_toggle'),
	        optionsHeight = $dk.find('.vkoslct_options').outerHeight(),
	        spaceBelow = $(window).height() - $vkoslct_toggle.outerHeight() - $vkoslct_toggle.offset().top + $(window).scrollTop(),
	        spaceAbove = $vkoslct_toggle.offset().top - $(window).scrollTop()
	      ;
	      // [Acemir] If no space above, default is opens down. If has space on top, check if will need open it to up
	      return !(optionsHeight < spaceAbove) ? true : (optionsHeight < spaceBelow);
	    },

	    setScrollPos = function($dk, anchor, e) {
	      var
	        wrapper = $dk.find('.vkoslct_options_inner'),
	        height = anchor.prevAll('li').outerHeight() * anchor.prevAll('li').length,
	        minHeight = wrapper.scrollTop(),
	        maxHeight = wrapper.height() + wrapper.scrollTop() - anchor.outerHeight()
	      ;

	      if ( (e && e.type === 'keydown') || (height < minHeight || height > maxHeight) ) {
	        wrapper.scrollTop(height); // A more direct approach
	      }
	    },

	    // Open a dropdown
	    openDropdown = function($dk,e) {
	      if($dk.hasClass('disabled') || $dk.hasClass('readonly'))return;
	      var hasSpace = enoughSpace($dk); // Avoids duplication of call to _enoughSpace
	      //TODO 这个z-index
	      $dk.parents('.form_item:first').css('z-index','100000');
	      $dk.find('.vkoslct_options').css({
	        top : hasSpace ? $dk.find('.vkoslct_toggle').outerHeight() - 1 : '',
	        bottom : hasSpace ? '' : $dk.find('.vkoslct_toggle').outerHeight() - 1
	      });
	      $opened = $dk.toggleClass('vkoslct_open');
	      setScrollPos($dk,$dk.find('.vkoslct_option_current'),e); // IE8+ needs to set scrollTop only after the dropdow is opened
	    },

	    // Set the currently selected option
	    setCurrent = function($current, $dk, e) {
	      $dk.find('.vkoslct_option_current').removeClass('vkoslct_option_current');
	      $current.addClass('vkoslct_option_current');
	      setScrollPos($dk, $current, e);
	    },

	    handleKeyBoardNav = function(e, $dk) {
	      var
	        code     = e.keyCode,
	        data     = $dk.data('dropkick'),
	        letter   = String.fromCharCode(code),
	        options  = $dk.find('.vkoslct_options'),
	        open     = $dk.hasClass('vkoslct_open'),
	        lis      = options.find('li'),
	        current  = $dk.find('.vkoslct_option_current'),
	        first    = lis.first(),
	        last     = lis.last(),
	        next,
	        prev,
	        now,
	        list,
	        i,
	        l,
	        $a
	      ;

	      switch (code) {
	      case keyMap.enter:
	        if (open) {
	          if(!current.hasClass('disabled')){
	            updateFields(current.find('a'), $dk);
	            closeDropdown($dk);
	          }
	        } else {
	          openDropdown($dk,e);
	        }
	        e.preventDefault();
	        break;

	      case keyMap.tab:
	        if(open){
	          updateFields(current.find('a'), $dk);
	          closeDropdown($dk);
	        }
	        break;

	      case keyMap.up:
	        prev = current.prev('li');
	        if (open) {
	          if (prev.length) {
	            setCurrent(prev, $dk, e);
	          } else {
	            setCurrent(last, $dk, e);
	          }
	        } else {
	          openDropdown($dk,e);
	        }
	        e.preventDefault();
	        break;

	      case keyMap.down:
	        if (open) {
	          next = current.next('li').first();
	          if (next.length) {
	            setCurrent(next, $dk, e);
	          } else {
	            setCurrent(first, $dk, e);
	          }
	        } else {
	          openDropdown($dk,e);
	        }
	        e.preventDefault();
	        break;

	      default:
	        break;
	      }
	      //if typing a letter
	      if (code >= keyMap.zero && code <= keyMap.z) {
	        //update data
	        now = new Date().getTime();
	        if (data.finder === null) {
	          data.finder = letter.toUpperCase();
	          data.timer = now;

	        }else {
	          if (now > parseInt(data.timer, 10) + 1000) {
	            data.finder = letter.toUpperCase();
	            data.timer =  now;
	          } else {
	            data.finder = data.finder + letter.toUpperCase();
	            data.timer = now;
	          }
	        }
	        //find and switch to the appropriate option
	        list = lis.find('a');
	        for(i = 0, l = list.length; i < l; i++){
	          $a = $(list[i]);
	          if ($a.html().toUpperCase().indexOf(data.finder) === 0) {
	            updateFields($a, $dk);
	            setCurrent($a.parent(), $dk, e);
	            break;
	          }
	        }
	        $dk.data('dropkick', data);
	      }
	    },

	    notBlank = function(text) {
	      return ($.trim(text).length > 0) ? text : false;
	    },

	    // Turn the dropdownTemplate into a jQuery object and fill in the variables.
	    build = function (tpl, view) {
	      var
	        // Template for the dropdown
	        template  = tpl.replace('{{ id }}', view.id).replace('{{ label }}', view.label).replace('{{ tabindex }}', view.tabindex),
	        // Holder of the dropdowns options
	        options   = [],
	        $dk,
	        i,
	        l,
	        $option,
	        oTemplate
	      ;

	      if (view.options && view.options.length) {
	        for (i = 0, l = view.options.length; i < l; i++) {
	          $option   = $(view.options[i]);

	          (i === 0 && $option.attr('selected') !== undefined && $option.attr('disabled') !== undefined)?
	          oTemplate = null
	          :
	          oTemplate = optionTemplate.replace('{{ value }}', $option.val())
	                                    .replace('{{ current }}', (notBlank($option.val()) === view.value) ? 'vkoslct_option_current' : '')
	                                    .replace('{{ disabled }}', ($option.attr('disabled') !== undefined) ? 'disabled' : '')
	                                    .replace('{{ text }}', $option.html().trim())
	          ;

	          options[options.length] = oTemplate;
	        }
	      }
	      
	      if(view.refesh){
	    	  $dk = $('#vkoslct_container_'+view.id);
	    	  $dk.find('.vkoslct_label').text(view.label);
	      }else{
	    	  $dk = $(template);
	      }
	      $dk.find('.vkoslct_options_inner').empty().html(options.join(''));

	      return $dk;
	    }
	  ;

	  // Help prevent flashes of unstyled content
	  if (!ie6) {
	    document.documentElement.className = document.documentElement.className + ' vkoslct_fouc';
	  }

	  // Called by using $('foo').dropkick();
	  methods.init = function (settings) {
	    settings = $.extend({}, defaults, settings);

	    return this.each(function () {
	      var
	        // The current <select> element
	        $select = $(this),

	        // Store a reference to the originally selected <option> element
	        $original = $select.find(':selected').first(),

	        // Save all of the <option> elements
	        $options = $select.find('option'),

	        // We store lots of great stuff using jQuery data
	        data = $select.data('dropkick') || {},

	        // This gets applied to the 'vkoslct_container' element
	        id = $select.attr('id') || $select.attr('name'),

	        // This gets updated to be equal to the longest <option> element
	        width  = settings.width || $select.outerWidth(),

	        // Check if we have a tabindex set or not set to 0
	        tabindex  = $select.attr('tabindex') || '0',

	        // The completed vkoslct_container element
	        $dk = false,

	        theme,

	        // The form relative to the select
	        $form
	      ;

	      // Dont do anything if we've already setup dropkick on this element
	      if (data.id && !data.refesh) {
	        return $select;
	      }
	      
	      //id去重
	      if(!data.refesh){
	    	  id+=we.utils.checkId('vkoslct_container_'+id);
	      }else{
	    	  id = data.id;
	      }

	      data.settings  = settings;
	      data.tabindex  = tabindex;
	      data.id        = id;
	      data.$original = $original;
	      data.$select   = $select;
	      data.value     = notBlank($select.val()) || notBlank($original.attr('value'));
	      data.label     = $original.text();
	      data.options   = $options;

	      // Build the dropdown HTML
	      $dk = build(dropdownTemplate, data);

	      // Make the dropdown fixed width if desired
	      $dk.find('.vkoslct_toggle').css({
	        'width' : (width - 8) + 'px'
	      });

	      // Hide the <select> list and place our new one in front of it
	      if(!data.refesh)$select.before($dk).appendTo($dk);

	      // Update the reference to $dk
	      $dk = $('div[id="vkoslct_container_' + id + '"]');
	      //if($('select[sub="'+$select.attr('name')+'"]').size()<1){
	    	  $dk.attr('style','display:inline-block;*display:inline;*zoom:1;');
	    	 /* if($dk.parents('.tabs_panel:first').size()>0){
	    		  var h =  $dk.offset().top-$dk.height()+250-109;//$('#header').height() = 109
	    		  if($dk.parents('.tabs_panel:first').height()<h){
	    			  $dk.parents('.tabs_panel:first').css('min-height',h);
	    		  }
	    	  }*/
	      //}

	      // Save the current theme
	      theme = settings.theme || 'default';
	      $dk.addClass('vkoslct_theme_' + theme);
	      data.theme = theme;

	      // Save the updated $dk reference into our data object
	      data.$dk = $dk;
	      
	      data.destroy = function(){
	    	  var $vkoslct =  $select.parents('.vkoslct_container:first');
	    	  $vkoslct.after($select.clone().css('visibility','visible'));
	    	  $vkoslct.remove();
	      }

	      // Save the dropkick data onto the <select> element
	      $select.data('dropkick', data);

	      //Adds original select class to vkoslct_container
	      $dk.addClass($select.attr('class'));

	      // Do the same for the dropdown, but add a few helpers
	      $dk.data('dropkick', data);

	      lists[lists.length] = $select;

	      // Focus events
	      $dk.bind('focus.dropkick', function () {
	        $focused = $dk.addClass('vkoslct_focus');
	      }).bind('blur.dropkick', function () {
	        $dk.removeClass('vkoslct_focus');
	        $focused = null;
	      });

	      //If isMobile, triggers native selects while still mantains the styled dropkick
	      if (isMobile && data.settings.nativeMobile) {
	        $dk.addClass('vkoslct_mobile');
	      }

	      // Sync to change events on the original <select> if requested
	      if (data.settings.syncReverse) {
	        $select.on('change', function (e) {
	          var
	            value = $select.val(),
	            option = $('a[data-dk-dropdown-value="'+value+'"]', $dk),
	            label = option.text()
	          ;

	          $dk.find('.vkoslct_label').text(label);
	          data.settings.change && data.settings.change.call($select, value, label);
	          setCurrent(option.parent(), $dk, e);
	        });
	      }

	      // Listen to a reset event to the form on the <select>
	      if ($select.attr('form') || $select.closest('form').length) {
	        $form = $select.attr('form') ? $('#'+$select.attr('form').replace(' ',', #')) : $select.closest('form');
	        $form.on('reset',function(){ $select.dropkick('reset') });
	      }

	      // [Issue #126] Validation do not fires in <select> is not (':visible')
	      // setTimeout(function () {
	      //   $select.hide();
	      // }, 0);
	    });
	  };

	  // Allows dynamic theme changes
	  methods.theme = function (newTheme) {
	    var
	      data      = $(this).data('dropkick'),
	      $dk       = data.$dk,
	      oldtheme  = 'vkoslct_theme_' + data.theme
	    ;

	    $dk.removeClass(oldtheme).addClass('vkoslct_theme_' + newTheme);
	    data.theme = newTheme;
	  };

	  // Reset respective <select> and dropdown
	  methods.reset = function () {
	    return this.each(function () {
	      var
	        data      = $(this).data('dropkick'),
	        $dk       = data.$dk,
	        $current  = $('a[data-dk-dropdown-value="'+data.value+'"]', $dk)
	      ;

	      !data.$original.eq(0).prop('selected') && data.$original.eq(0).prop('selected',true);
	      $dk.find('.vkoslct_label').text(data.label);
	      setCurrent($current.parent(), $dk);
	    });
	  };

	  methods.setValue = function (value) {
	    var
	      $dk = $(this).data('dropkick').$dk,
	      $option = $('.vkoslct_options a[data-dk-dropdown-value="' + value + '"]',$dk)
	    ;

	    if ($option.length) {
	      updateFields($option, $dk);
	      setCurrent($option.parent(), $dk);
	    } else {
	      console.warn('There is no option with this value in the <select>');
	    }
	  };

	  // Reload / rebuild, in case of dynamic updates etc.
	  // Credits to Jeremy (http://stackoverflow.com/users/1380047/jeremy-p)
	  // Regenerating dk wrapper to update it's content
	  // http://stackoverflow.com/a/13280151
	  methods.refresh = function(){
	    return this.each(function () {
	      var
	        data      = $(this).data('dropkick'),
	        $select   = data.$select,
	        $dk       = data.$dk
	      ;

	      data.settings.startSpeed = 0;

	      $select.removeData("dropkick").insertAfter($dk);
	      $dk.remove();
	      $select.dropkick(data.settings);
	    });
	  };
	  
	  /* 自定义扩展联动 */
	  methods.getOptions = function($slct){
			var sub = $slct.attr('sub'),
				name = $slct.attr('name'),
				skey = $slct.attr('skey'),
				url = $slct.attr('suburl'),
				value = $slct.val();
			
			if(we.utils.isEmpty(sub)) return;
			var $sub = $slct.parents('form:first').find('select[name='+sub+']');
			if($sub.size()<1)$sub = $slct.parents('form:first').find('.'+sub+'');
			var initSlct = function($select, data){
				$('option',$select).each(function(){
					if($(this).attr('value')!= -1){
						$(this).remove();
					}
				});
				if($('option',$select).size()<1){
					$select.append('<option value="-1">---请选择---</option>');
				}else if($('option',$select).size()>1){
					$('option:first',$select).siblings().remove();
				}
				$.each(data, function(i, n){
					// 
					var defaultValue = $select.attr('val');
					if(defaultValue==n.value){
						$select.append('<option value="'+n.value+'" selected="selected">'+n.text+'</option>');
					}else{
						$select.append('<option value="'+n.value+'">'+n.text+'</option>');
					}
				});
				if(!$select.data('dropkick'))return;
				var sub = $select.data('dropkick').$select.attr('sub');
				if(!we.utils.isEmpty(sub) && $('select[name="'+sub+'"]').size()>0){
					$('select[name="'+sub+'"]').data('dropkick').$dk.hide();
				}
				/*if(data.length<1){
					$sub.data('dropkick').$dk.hide();
					return;
				}*/
				$select.data('dropkick').$dk.attr('style','display:inline-block;*display:inline;*zoom:1;');
				$select.data('dropkick').refesh = true;
				$select.data('dropkick').options = [];
//				$select.val(-1);
				methods.init.apply($select);
			}
			if(we.utils.isEmpty(url)){
				$sub.each(function(){
					var $this = $(this), dataurl = $this.attr('dataurl');
					if(!we.utils.isEmpty(dataurl)){
						$ajax.vkoAjax({
							url: dataurl,
							//data: we.utils.newObj(we.utils.isEmpty(skey)?name:skey,value),
							data: {
								name: we.utils.isEmpty(skey)?name:skey,
								value: value
							},
							success: function(data){
								initSlct($this, data);
							}
						});
					}
				});
			}else{
				$ajax.vkoAjax({
					url: url,
					data: we.utils.newObj(we.utils.isEmpty(skey)?name:skey,value),
					success: function(data){
						initSlct($sub, data);
					}
				});
			}
		}

	  // Expose the plugin
	  $.fn.dropkick = function (method) {
	    if (!ie6) {
	      if (methods[method]) {
	        return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
	      }
	      if (typeof method === 'object' || ! method) {
	        return methods.init.apply(this, arguments);
	      }
	    }else{
	    	$(this).bind('change',function(){
	    		methods.getOptions($(this));
	    	});
	    }
	  };
	  $.fn.selectRefresh = methods.refresh;

	  $(function () {

	    // Handle click events on individual dropdown options
	    $(document).on((msie ? 'mousedown' : 'click'), '.vkoslct_options a', function () {
	      var
	        $option = $(this),
	        $dk     = $option.parents('.vkoslct_container').first()
	      ;

	      if(!$option.parent().hasClass('disabled')){
	        updateFields($option, $dk);
	        setCurrent($option.parent(), $dk); // IE8+, iOS4 and some Android [4.0] Browsers back to scrollTop 0 when an option is clicked and the dropdown is opened again
	        closeDropdown($dk);
	      }

	      return false;
	    });

	    // Setup keyboard nav
	    $(document).bind('keydown.vkoslct_nav', function (e) {
	      var $dk = null;

	      // If we have an open dropdown, key events should get sent to that one
	      if ($opened) {
	        $dk = $opened;
	      } else if ($focused && !$opened) {
	        // But if we have no open dropdowns, use the focused dropdown instead
	        $dk = $focused;
	      }

	      if ($dk) {
	        handleKeyBoardNav(e, $dk);
	      }
	    });

	    // Globally handle a click outside of the dropdown list by closing it.
	    $(document).on('click', null, function (e) {
	      if ($opened && $(e.target).closest(".vkoslct_container").length === 0 ) {
	        closeDropdown($opened); // Improves performance by minimizing DOM Traversal Operations
	      } else if ($(e.target).is(".vkoslct_toggle, .vkoslct_label")) {
	        var $dk = $(e.target).parents('.vkoslct_container').first();

	        if ($dk.hasClass('vkoslct_open')) {
	          closeDropdown($dk);
	        } else {
	          $opened && closeDropdown($opened);
	          openDropdown($dk,e);
	        } // Avoids duplication of call to _openDropdown
	        $dk.find('span.error').remove();
	        return false;
	      }
	    });

	    // Prevents window scroll when scrolling  through vkoslct_options, simulating native behaviour
	    var wheelSupport =  'onwheel' in window ? 'wheel' : // Modern browsers support "wheel"
	                        'onmousewheel' in document ? 'mousewheel' : // Webkit and IE support at least "mousewheel"
	                        "MouseScrollEvent" in window ? 'DOMMouseScroll MozMousePixelScroll' : // legacy non-standard event for older Firefox
	                        false // lacks support
	    ;

	    wheelSupport && $(document).on(wheelSupport, '.vkoslct_options_inner', function(event) {
	        var delta = event.originalEvent.wheelDelta || -event.originalEvent.deltaY || -event.originalEvent.detail; // Gets scroll ammount
	        if (msie) { this.scrollTop -= Math.round(delta/10); return false; } // Normalize IE behaviour
	        return (delta > 0 && this.scrollTop <= 0 ) || (delta < 0 && this.scrollTop >= this.scrollHeight - this.offsetHeight ) ? false : true; // Finally cancels page scroll when nedded
	    });
	  });

	}(jQuery, window, document));
	
	var select = function(){
		var $selects = $('select').not($('#calendar select')).not($('select[unformat]').css('visibility','visible'));//排除与其他插件冲突
		$selects.dropkick();
	}
	
	module.exports = select;
});