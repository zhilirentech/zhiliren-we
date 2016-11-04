define(function(require, exports, module){
	/**
	 * 
	 * @author ZhangHuihua@msn.com
	 * @param {Object} opts Several options
	 */
	
//	var $ = require('jquery'),
//	defaultOp = {
//		targetType:"navTab",	// navTab, dialog
//		rel:"",					//用于局部刷新div id号
//		totalCount:0,
//		numPerPage:10,
//		pageNumShown:10,
//		currentPage:1,
//		callback:function(){return false;}
//	},
//	pageTmpl = ['<div class="vkop_listlen">',
//	            	'<span class="vkop_listlen_tag"></span>',
//	            	'<select class="vkop_listlen_combox" name="numPerPage"></select>',
//	            	'<span class="vkop_listlen_tag">条，共{{ total }}条</span>',
//	            '</div>',
//	            '<ul>',
//		        	'<li><a class="vkop_first" href="javascript:;">首页</a></li>',
//			    	'<li><a class="vkop_prev" href="javascript:;">上一页</a></li>',
//			    	'{{ pageNumFrag }}',
//			    	'<li><a class="vkop_next" href="javascript:;">下一页</a></li>',
//			    	'<li><a class="vkop_last" href="javascript:;">末页</a></li>',
//			    	'<li>',
//			    		'<input class="vkop_jumpto" type="text" size="4" value="{{ currentPage }}" />',
//			    		'<input class="btn" type="button" value="确定" />',
//		    		'</li>',
//			    '</ul>'].join('');
//
//
//var Pagination = function(options){
//	var op = $.extend({},defaultOp,options);
//	
//}
	(function($){
		$.fn.extend({
			pagination: function(opts){
				var setting = {
					first$:"li.vkop_first", prev$:"li.vkop_prev", next$:"li.vkop_next", last$:"li.vkop_last", nums$:"li.vkop_num>a", jumpto$:"li.jumpto",
					pageNumFrag:'<li class="#liClass#"><a href="javascript:;">#pageNum#</a></li>'
				},
				pageTmpl = ['<ul>',
					        	'<li class="vkop_first">',
					    		'<a class="first" href="javascript:;">首页</a>',
					    		'<span class="first">首页</span>',
					    	'</li>',
					    	'<li class="vkop_prev">',
					    		'<a class="previous" href="javascript:;">上一页</a>',
					    		'<span class="previous">上一页</span>',
					    	'</li>',
					    	'#pageNumFrag#',
					    	'<li class="vkop_next">',
					    		'<a class="next" href="javascript:;">下一页</a>',
					    		'<span class="next">下一页</span>',
					    	'</li>',
					    	'<li class="vkop_last">',
					    		'<a class="last" href="javascript:;">末页</a>',
					    		'<span class="last">末页</span>',
					    	'</li>',
					    	'<li class="jumpto"><input class="textInput" type="text" size="4" value="#currentPage#" /><input class="btn" type="button" value="确定" /></li>',
					    '</ul>'].join('');
				return this.each(function(){
					var $this = $(this);
					var pc = new Pagination(opts);
					var interval = pc.getInterval();
		
					var pageNumFrag = '';
					for (var i=interval.start; i<interval.end;i++){
						pageNumFrag += setting.pageNumFrag.replaceAll("#pageNum#", i).replaceAll("#liClass#", i==pc.getCurrentPage() ? 'selected vkop_num' : 'vkop_num');
					}
					$this.html(pageTmpl.replaceAll("#pageNumFrag#", pageNumFrag).replaceAll("#currentPage#", pc.getCurrentPage())).find("li").hoverClass();
		
					var $first = $this.find(setting.first$);
					var $prev = $this.find(setting.prev$);
					var $next = $this.find(setting.next$);
					var $last = $this.find(setting.last$);
					
					if (pc.hasPrev()){
						$first.add($prev).find(">span").hide();
						_bindEvent($prev, pc.getCurrentPage()-1, pc.targetType(), pc.rel());
						_bindEvent($first, 1, pc.targetType(), pc.rel());
					} else {
						$first.add($prev).addClass("disabled").find(">a").hide();
					}
					
					if (pc.hasNext()) {
						$next.add($last).find(">span").hide();
						_bindEvent($next, pc.getCurrentPage()+1, pc.targetType(), pc.rel());
						_bindEvent($last, pc.numPages(), pc.targetType(), pc.rel());
					} else {
						$next.add($last).addClass("disabled").find(">a").hide();
					}
		
					$this.find(setting.nums$).each(function(i){
						_bindEvent($(this), i+interval.start, pc.targetType(), pc.rel());
					});
					$this.find(setting.jumpto$).each(function(){
						var $this = $(this);
						var $inputBox = $this.find(":text");
						var $button = $this.find(":button");
						$button.click(function(event){
							var pageNum = $inputBox.val();
							if (pageNum && pageNum.isPositiveInteger()) {
								vkoPageBreak({targetType:pc.targetType(), rel:pc.rel(), data: {pageNum:pageNum}});
							}
						});
						$inputBox.keyup(function(event){
							if (event.keyCode == we.keyCode.ENTER) $button.click();
						});
					});
				});
				
				function _bindEvent($target, pageNum, targetType, rel){
					$target.bind("click", {pageNum:pageNum}, function(event){
						vkoPageBreak({targetType:targetType, rel:rel, data:{pageNum:event.data.pageNum}});
						event.preventDefault();
					});
				}
			},
			
			orderBy: function(options){
				var op = $.extend({ targetType:"navTab", rel:"", asc:"asc", desc:"desc"}, options);
				return this.each(function(){
					var $this = $(this).css({cursor:"pointer"}).click(function(){
						var orderField = $this.attr("orderField");
						var orderDirection = $this.hasClass(op.asc) ? op.desc : op.asc;
						vkoPageBreak({targetType:op.targetType, rel:op.rel, data:{orderField: orderField, orderDirection: orderDirection}});
					});
					
				});
			},
			pagerForm: function(options){
				var op = $.extend({pagerForm$:"#pagerForm", parentBox:document}, options);
				var frag = '<input type="hidden" name="#name#" value="#value#" />';
				return this.each(function(){
					var $searchForm = $(this), $pagerForm = $(op.pagerForm$, op.parentBox);
					var actionUrl = $pagerForm.attr("action").replaceAll("#rel#", $searchForm.attr("action"));
					$pagerForm.attr("action", actionUrl);
					$searchForm.find(":input").each(function(){
						var $input = $(this), name = $input.attr("name");
						if (name && (!$input.is(":checkbox,:radio") || $input.is(":checked"))){
							if ($pagerForm.find(":input[name='"+name+"']").length == 0) {
								var inputFrag = frag.replaceAll("#name#", name).replaceAll("#value#", $input.val());
								$pagerForm.append(inputFrag);
							}
						}
					});
				});
			}
		});
		
		var Pagination = function(opts) {
			this.opts = $.extend({
				targetType:"navTab",	// navTab, dialog
				rel:"", //用于局部刷新div id号
				totalCount:0,
				numPerPage:10,
				pageNumShown:10,
				currentPage:1,
				callback:function(){return false;}
			}, opts);
		}
		
		$.extend(Pagination.prototype, {
			targetType:function(){return this.opts.targetType},
			rel:function(){return this.opts.rel},
			numPages:function() {
				return Math.ceil(this.opts.totalCount/this.opts.numPerPage);
			},
			getInterval:function(){
				var ne_half = Math.ceil(this.opts.pageNumShown/2);
				var np = this.numPages();
				var upper_limit = np - this.opts.pageNumShown;
				var start = this.getCurrentPage() > ne_half ? Math.max( Math.min(this.getCurrentPage() - ne_half, upper_limit), 0 ) : 0;
				var end = this.getCurrentPage() > ne_half ? Math.min(this.getCurrentPage()+ne_half, np) : Math.min(this.opts.pageNumShown, np);
				return {start:start+1, end:end+1};
			},
			getCurrentPage:function(){
				var currentPage = parseInt(this.opts.currentPage);
				if (isNaN(currentPage)) return 1;
				return currentPage;
			},
			hasPrev:function(){
				return this.getCurrentPage() > 1;
			},
			hasNext:function(){
				return this.getCurrentPage() < this.numPages();
			}
		});
	})(jQuery);
});