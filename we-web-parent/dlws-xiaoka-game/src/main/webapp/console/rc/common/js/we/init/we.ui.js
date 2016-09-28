define(function (require, exports, module) {

    /**
     * @name vko
     * @class 扩展全局vko，增加ui方法集。
     * @requires jQuery
     * @author 李博龙
     * @version v1.0.0
     */
	
	var $cookie = require('cookie'),
		loadingAjaxLogin = false;
	
    /*----- 全站ui方法 -----*/
    we.ui = {
    	header: function(){
    		if(we.browser.w<1065){
    			$('#header').addClass('minheader');
    			$('#header .head').width(790-Math.round(125*300/we.browser.w));
    			//seajs.log('head: '+$('#header .head').width())
    			$('.head_searchkey').width(170-Math.round(105*300/we.browser.w));
    			$('.head_search').css('margin-right',25-Math.round(20*300/we.browser.w)+'px')
    		}else{
    			$('#header').removeClass('minheader');
    			$('#header .head').css('width','auto');
    		}
    	},
		sidebar: function(){
			$('#sidebar a').each(function(){
				var $this = $(this);
				// html
				if($('span',$this).size()<1){
					var icon = $this.attr('icon');
					$this.attr('title',$this.text()).html('<span>'+$this.text()+'</span>');
					if(!we.utils.isEmpty(icon)){
						$('span',$this).before('<i class="icon icon_'+icon+'"></i>');
					}
				}
				// event
				if(we.browser.w<970){
					if($this.siblings('ul').size()>0){
						$('#sidebar').addClass('minsidebar').css('z-index','4');
						$('#container').addClass('mincontainer');
	    				$this.addClass('submenu').unbind('mouseover.sidebar click.sidebar').bind('mouseover.sidebar',function(){
	    					$this.parent().siblings().find('.submenu').removeClass('open').siblings('ul').hide();
							$this.addClass('open').siblings('ul').show();
						});
					}else{
						$this.unbind('mouseover.sidebar click.sidebar').bind('mouseover.sidebar',function(){
							$this.parent().siblings().find('.submenu').removeClass('open').siblings('ul').hide();
						});
					}
					$(document).unbind('click.sidebar').bind('click.sidebar',function(){
						$('#sidebar .submenu').removeClass('open').siblings('ul').hide();
					});
				}else{
					if($this.siblings('ul').size()>0){
						$('#sidebar').removeClass('minsidebar').css('z-index','2');;
						$('#container').removeClass('mincontainer');
	    				$this.addClass('submenu').unbind('mouseover.sidebar click.sidebar').bind('click.sidebar',function(){
	    					$this.parent().siblings().find('.submenu').removeClass('open').siblings('ul').slideUp('fast');
							$this.toggleClass('open').siblings('ul').slideToggle('fast',function(){
								if($('#sidebar').height()>$('#container').height()){
									$('#container').css('min-height',$('#sidebar').height());
								}
							});
						});
					}else{
						$this.unbind('mouseover.sidebar click.sidebar').bind('click.sidebar',function(){
							$('#sidebar .current').removeClass('current');
							$this.parent().toggleClass('current');
						});
					}
					$(document).unbind('click.sidebar');
				}
			});
		},
		container: function(){
			if($('#sidebar').size()<1)return;
			var navTmpl = ['<div id="navTab" class="tabsPanel">',
					       		'<div class="tabsPanelHeader">',
								'<div class="tabsPanelHeaderContent">',
									'<ul class="tabs_tab">',
										'<li tabid="home" class="home"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>',
									'</ul>',
								'</div>',
								'<div class="tabsLeft">left</div>',
								'<div class="tabsRight">right</div>',
								'<div class="tabsMore">more</div>',
							'</div>',
							'<ul class="tabsMoreList">',
								'<li><a href="javascript:;">我的主页</a></li>',
							'</ul>',
							'<div class="tabsPanelContent"><div class="tabs_panel"></div></div>',
						'</div>'].join('');
			$('#container').empty().append(navTmpl).css('min-height', we.browser.h-$('#header').height()-30-40);
			we.ui.navTab.init();
		},
		navTab: require('navtab'),
		init: function(_box, callback){
	    	var $doc = $(_box || document);
	    	
	    	//Ajax链接扩展
	    	$('a[target=ajax]', $doc).each(function(){
	    		$(this).unbind('click.ajax').bind('click.ajax',function(event){
	    			var $this = $(this);
	    			var rel = $this.attr('rel');
	    			if (rel) {
	    				var $rel = $('#'+rel);
	    				$ajax.vkoAjax({
		    				url: $this.attr('href'),
		    				data: {},
		    				type: 'post',
		    				success: function(data){
		    					$rel.show().html(data).initUI();
		    				}
		    			});
	    			}
	    			event.preventDefault();
	    		});
	    	});
	    	$('textarea.editor', $doc).each(function(){
		    	require('editor').add(
	    			$(this)
	    		);
	    	});
	    	
	    	//当前navTab中链接ajax post扩展
	    	if ($.fn.ajaxTodo) $('a[target=ajaxTodo]', $doc).ajaxTodo();
	    	if ($.fn.dwzExport) $("a[target=dwzExport]", $doc).dwzExport();
	    	
	    	//dialog链接扩展
	    	$('a[target=dialog]', $doc).each(function(){
	    		var $this = $(this),
					isDiv = $this.attr('isDiv'),
					$div = null;
	    			
    			if(!we.utils.isEmpty(isDiv) && $(isDiv,$doc).size()>0){
    				$div = $(isDiv,$doc).hide();
    			}
    			$this.unbind('click.dialog').bind('click.dialog',function(event){
    				if(!we.utils.isEmpty($div)){
        				$this.showDlg($div[0]);
        				return false;
        			}
	    			$ajax.vkoAjax({
	    				url: $this.attr('href'),
	    				success: function(data){
	    					$this.showDlg(data);
	    				}
	    			});
	    			event.preventDefault();
	    		});
	    	});
	    	if($('.d-content .panel_box').size()>0){
				if($('.d-content .panel_box').width()<550) $('.d-content .panel_box').width(550);
				if($('.d-content .panel_box').height()<100) $('.d-content .panel_box').height(100);
			}
	    	
	    	//navTab链接扩展
	    	$('a[target=navTab]', $doc).each(function(){
	    		$(this).unbind('click.navtab').bind('click.navtab',function(event){
	    			var $this = $(this),
	    				tabid = $this.attr('rel') || '_blank',
	    				title = $this.attr('title') || $this.text(),
	    				method = $this.attr('method') || 'get',
						fresh = eval($this.attr('fresh') || 'true'),
						external = eval($this.attr('external') || 'false'),
	    				url = unescape($this.attr('href'));
	    			
	    			if(!we.utils.isEmpty(we.ui.navTab)){
	    				we.ui.navTab.openTab(tabid, url,{title:title, method:method, fresh:fresh, external:external});
	    			}
	    			if($('#sidebar').height()>$('#container').height()){
						$('#container').css('min-height',$('#sidebar').height());
					}
	    			we.ui.navTab.getCurrentPanel().css('min-height',($('#container').height()-100)+'px');
	    			event.preventDefault();
	    		});
	    	});
	    	
	    	//装载登录界面扩展
	    	$('div.vkologin', $doc).loadLogin();
	    	
	    	//tree
	    	if($('ul.tree', $doc).size()>0){
	    		require.async('tree',function(){
	    			setTimeout(function(){$('ul.tree', $doc).jTree();},1)
	    		});
	    	}
	    	require('tab');
			//auto bind tabs
			var $p = $(_box || document);
			$("div.tabs", $p).each(function(){
				var $this = $(this);
				var options = {};
				options.currentIndex = $this.attr("currentIndex") || 0;
				options.eventType = $this.attr("eventType") || "click";
				$this.tabs(options);
			});

	    	// navTab styles
			var jTabsPH = $("div.tabsPageHeader");
			jTabsPH.find(".tabsLeft").hoverClass("tabsLeftHover");
			jTabsPH.find(".tabsRight").hoverClass("tabsRightHover");
			jTabsPH.find(".tabsMore").hoverClass("tabsMoreHover");
			
	    	//tables
	    	require('table');
	    	$('table.table', $doc).jTable();
	    	$('table.list', $doc).cssTable();
	    	$('table tr', $doc).hoverClass();
	    	
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
	    	
	    	//pagination
	    	require('pagination');
	    	$('.pagination', $doc).each(function(){
	    		var $this = $(this);
	    		$this.pagination({
	    			targetType:$this.attr('targetType'),
	    			rel:$this.attr('rel'),
	    			totalCount:$this.attr('totalCount'),
	    			numPerPage:$this.attr('numPerPage'),
	    			pageNumShown:$this.attr('pageNumShown'),
	    			currentPage:$this.attr('currentPage')
	    		});
	    	});
	    	
	    	//自定义page
	    	if($('.page', $doc).size()>0){
	    		var $page = require('page');
	    		$('.page', $doc).each(function(){
	    			var $this = $(this);
	    			$page.ajaxPage({
	    				page: $this,
	    				list: $this.attr('list'),
	    				items: $this.attr('items'),
	    				url: $this.attr('url'),
	    				number: $this.attr('number'),
	    				size: $this.attr('size'),
	    				data: null,
	    				display:8,
	    				firstpage: eval($this.attr('firstpage') || 'true'),
	    				success:null,
	    				empty:callback
	    			});
	    		});
	    	}
	    	
	    	//validate form
	    	if($('form.required-validate', $doc).size()>0){
	    		require.async('validate',function(){
	    			$('form.required-validate', $doc).each(function(){
	    				var $form = $(this);
	    				$form.validate({
	    					onsubmit: false,
	    					focusInvalid: false,
	    					focusCleanup: true,
	    					errorElement: 'span',
	    					ignore:'.ignore',
	    					invalidHandler: function(form, validator) {
	    						var errors = validator.numberOfInvalids();
	    						if (errors) {
	    							//var message = DWZ.msg('validateFormError',[errors]);
	    							//alertMsg.error(message);
	    						} 
	    					}
	    				});
	    				
	    				$form.find('input[customvalid]').each(function(){
	    					var $input = $(this);
	    					$input.rules('add', {
	    						customvalid: $input.attr('customvalid')
	    					})
	    				});
	    			});
	    		});
	    	}
	    	if($('.form_ctrl').html()=='')$('.form_ctrl').append('&nbsp;');
	    	$('input[type=radio]').addClass('ipt_radio');
	    	$('input[type=checkbox]').addClass('ipt_checkbox');
	    	$('input[disabled=disabled],textarea[disabled=disabled],select[disabled=disabled]').addClass('disabled');
	    	$('input[readonly=readonly],textarea[readonly=readonly],select[readonly=readonly]').addClass('readonly');
	    	$('input,textarea,select', $doc).each(function(){
	    		if(!we.utils.isEmpty($(this).attr('class')) && $(this).attr('class').indexOf('required')!=-1){
	    			$(this).addClass('required');
	    		}
	    	});
	    	
	    	//select美化
	    	if($select)$select();
	    	
	    	//placeholder
	    	if($placeholder) $placeholder();
	    	
	    	//selectedtodo
	    	if ($.fn.selectedTodo) $('a[target=selectedTodo]', $doc).selectedTodo();
	    	
	    	//lookup
	    	if ($.fn.lookup) $('a[lookupGroup]', $doc).lookup();
			//multLookup
			if ($.fn.multLookup) $("[multLookup]:button",$doc).multLookup();
	    	
	    	//editor
	    	/*if($('textarea.editor', $doc).size()>0){
	    		var $area = $('textarea.editor', $doc);
	    		$area.css({
	    			'min-width':$area.width()+'px',
	    			'min-height':$area.height()+'px',
	    			'max-width':$area.width()+'px',
	    			'max-height':$area.height()+'px'
    			}).attr('disabled','disabled').addClass('vkoUMEditor_loading');
	    		require.async('editor',function($editor){
	    			$editor.init($area);
	    		});
	    	}
	    	*/
	    	//局部刷新
	    	$('.uintBox', $doc).each(function(){
	    		var $this = $(this),
	    			url = $this.attr('url');
	    		
	    		if(we.utils.isEmpty(url))return;
	    		$ajax.vkoAjax({
	    			url: url,
	    			success: function(data){
	    				$this.show().html(data).initUI();
	    			}
	    		});
	    	});
	    	
	    	
	    	//图表  支持：线型图type="line"，柱状图type="column"，饼状图：type="pie"
	    	if($('table.vkoChart',$doc).size()>0){
	    		require.async('chart',function(){
	    			$('table.vkoChart',$doc).each(function(){
	    				var $this = $(this),
	    				chartId = $this.data('vkoChart'),
	    				type = $this.attr('type'),
	    				title = $this.attr('title'),
	    				ytitle = $this.attr('ytitle'),
	    				hidex = $this.attr('hidex'),
	    				hidey = $this.attr('hidey');
	    				
	    				if(we.utils.isEmpty(chartId)){
	    					chartId = 'vkoChart'+we.utils.checkId('vkoChart');
	    				}
	    				if($('#'+chartId).size()>0){
	    					$('#'+chartId).empty();
	    				}else{
	    					$this.after('<div id="'+chartId+'" style="min-width:310px;min-height:400px;margin:0 auto"></div>');
	    				}
	    				type = we.utils.isEmpty(type)?'line':type;
	    				$('#'+chartId).highcharts({
	    					data: {table: $this[0]},
	    					chart: {type: type},
	    					title: {text: title},
	    					xAxis: {
	    			            labels: {
	    			            	enabled: (hidex=='true')?false:true
	    			            }
	    			        },
	    					yAxis: {
	    						allowDecimals: false,
	    						title: {
	    							text: ytitle
	    						},
	    						labels: {
	    							formatter: function() {
	    								return this.value
	    							},
	    							enabled: (hidey=='true')?false:true
	    						}
	    					},
	    					tooltip: {
	    						crosshairs: type=='line'?true:false
	    					}
	    				});
	    				
	    				$this.data('vkoChart',chartId);
	    			});
	    		});
	    	}
	    	
	    	//flash图表
	    	if($('table.flashchart').size()>0){
	    		require.async('flashchart',function($chart){
	    			$('table.flashchart').each(function(){
	    				var $this = $(this),chartType = $this.attr('chartType');
	    				$chart.tablechart($this);
	    			});
	    		});
	    	}
	    	
	    	//大数据图表（股票）  支持：线型图type="line"，柱状图type="column"
	    	if($('table.vkoStock',$doc).size()>0){
	    		require.async('stock',function($stock){
	    			$('table.vkoStock',$doc).each(function(){
	    				var $this = $(this),
	    				chartId = $this.data('vkoStock'),
	    				type = $this.attr('type'),
	    				title = $this.attr('title'),
	    				ytitle = $this.attr('ytitle');
	    				
	    				if(we.utils.isEmpty(chartId)){
	    					chartId = 'vkoStock'+we.utils.checkId('vkoStock');
	    				}
	    				if($('#'+chartId).size()>0){
	    					$('#'+chartId).empty();
	    				}else{
	    					$this.after('<div id="'+chartId+'" style="min-width:310px;min-height:400px;margin:0 auto"></div>');
	    				}
	    				type = we.utils.isEmpty(type)?'line':type;
	    				$stock.init({
	    					table: $this,
	    					type: type,
	    					title: title,
	    					ytitle: ytitle,
	    					container: '#'+chartId
	    				});
	    				$this.data('vkoStock',chartId);
	    			});
	    		});
	    	}
	    	
	    	/* 图片加载失败替换为404图 */
    		we.utils.checkImg($('.vkouser .vkouser_avatar img'),we.vr.static + '/platform/bmp/images/pic/avatar.jpg');
	    	
	    	// 回调
	    	if($.isFunction(callback)){
	    		callback.call();
	    	}
    	},
		/* 登录弹框 */
        loadLogin: function (options) {
        	options = $.extend({
        		item: null,
        		event: null,
        		loaded: null,
        		callback: null
        	},options);
    		
    		require.async('login',function($login){
    			if(!we.utils.isEmpty(window.loadLoginDialog))return;
    			$login.init(options, function(data){
    				if(!we.utils.isEmpty(window.loadLoginDialog))return;
    				$dialog.dialog({
                        title: '登录',
                        fixed: 'true',
                        lock: 'true',
                        content: data,
                        initialize: function(){
                        	window.loadLoginDialog = this;
                        	if(!we.utils.isEmpty(options.loaded)){
                				options.loaded.call();
                			}
                        },
                        beforeunload: function(){
                        	window.loadLoginDialog = null;
                        }
                    });
    			});
    		});
    		
        },
        /* 获取浏览器信息 */
        getBrowserInfo: function () {
        	var msVersion = navigator.userAgent.match(/MSIE ([0-9]{1,}[\.0-9]{0,})/),
  	      		msie = !!msVersion,
  	      		iev = msie ? parseFloat(msVersion[1]) : 0,
  	      		ie6 = msie && parseFloat(msVersion[1]) < 7;
        	$.extend(we.browser,{msie:msie, ie6:ie6, iev:iev},we.utils.getBrowserWH());
        },
        /* 重置页面高度 */
        resetPageH: function (arr) {
            var $cont = $('.container'),
                $side = $('.rightside'),
                footerH = $('#footer').height(),
                headerH = $('#header').height(),
                sideH = we.utils.isEmpty($side.height() - 74) ? 0 : $side.height() - 74,
                itemH = we.browser.h - headerH - footerH - 170;
            itemH = itemH < sideH ? sideH : itemH;
            if (arr.length) {
                $.each(arr, function (i, item) {
                    if ($(item, $cont).size() > 0 && $(item, $cont).height() < itemH) {
                        $(item, $cont).css('min-height', itemH);
                    }
                });
            }
        },
        /* 刷新顶部工具条 */
        refreshHead: function (data) {
        	var uid, $user = $('#header .head_userinfo b');
        	if($user.size()<1 || we.utils.isEmpty(data.user)) return;
    		uid = $user.attr('uid');
    		if(data.user.id){
    			if(data.user.id!=uid){
    				window.location.reload();
    			}else{
    				$user.attr('uid',data.user.id);
    			}
        	}
        	if(data.user.realName){
        		$user.html(data.user.realName);
        	}
        },
        reload: function(){window.location.reload()}
    }
    
    return we.ui;
});