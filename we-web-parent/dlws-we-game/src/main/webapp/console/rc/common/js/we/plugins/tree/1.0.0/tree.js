define(function(require){
	/**
     * @name Tree
     * @class 提供树形处理 。
     * @requires jquery
     * @author 李博龙
     * @version v1.0.0  
     */
	
	require('./style.css');
	
	(function($){
		var menuName, menu, shadow, hash;
	 	$.extend($.fn, {
			jTree:function(options) {
				var op = $.extend({checkFn:null, selected:"selected", exp:"expandable", coll:"collapsable", firstExp:"first_expandable", firstColl:"first_collapsable", lastExp:"last_expandable", lastColl:"last_collapsable", folderExp:"folder_expandable", folderColl:"folder_collapsable", endExp:"end_expandable", endColl:"end_collapsable",file:"file",ck:"checked", unck:"unchecked", disck:"disabled"}, options);
				var $tree = $(this);
				if($('li',$tree).size()<1){
					$tree._showMenu();
					return;
				}
				setTimeout(function(){
					if($tree.height()<$tree.parent().parent().height() && $tree.children().size()<1){
						$tree.height($tree.parent().parent().height()-50);
					}
				},500);
				if($tree.children().size()<1){
					$tree._showMenu();
				}
				if($tree.hasClass("treeCheck")){
					$('a',$tree).each(function(){
						var $input = $(this),
							tname = $input.attr("tname"), 
							tvalue = $input.attr("tvalue"),
							tcheck = $input.attr("checked");
						var attrs = "text='"+$input.text()+"' ";
						if (tname) attrs += "name='"+tname+"' ";
						if (tvalue) attrs += "value='"+tvalue+"' ";
						if (tcheck) attrs += "checked='"+tcheck+"' ";
						$(this).append("<input type='checkbox' style='display:none;' " + attrs + "/>");
					});
				}
				return this.each(function(){
					var $this = $(this);
					var cnum = $this.children().length;
					$(">li", $this).each(function(){
						var $li = $(this);
						var first = $li.prev()[0]?false:true;
						var last = $li.next()[0]?false:true; 
						$li.genTree({
							icon:$this.hasClass("treeFolder"),
							ckbox:$this.hasClass("treeCheck"),
							nckbox:$this.hasClass("nodeCheck"),
							options: op,
							level: 0,
							exp:(cnum>1?(first?op.firstExp:(last?op.lastExp:op.exp)):op.endExp),
							coll:(cnum>1?(first?op.firstColl:(last?op.lastColl:op.coll)):op.endColl),
							showSub:(!$this.hasClass("collapse") && ($this.hasClass("expand") || (cnum>1?(first?true:false):true))),
							isLast:(cnum>1?(last?true:false):true)
						});
					});
					setTimeout(function(){
						if($this.hasClass("treeCheck")){
							var checkFn = eval($this.attr("oncheck"));
							if(checkFn && $.isFunction(checkFn)) {
								$("div.ckbox", $this).each(function(){
									var ckbox = $(this);
									ckbox.click(function(){
										var checked = $(ckbox).hasClass("checked");
										var items = [];
										if(checked){
											var tnode = $(ckbox).parent().parent();
											var boxes = $("input", tnode);
											if(boxes.size() > 1) {
												$(boxes).each(function(){
													items[items.length] = {name:$(this).attr("name"), value:$(this).val(), text:$(this).attr("text")};
												});
											} else {
												items = {name:boxes.attr("name"), value:boxes.val(), text:boxes.attr("text")};
											}		
										}								
										checkFn({checked:checked, items:items});														
									});
								});
							}
						}
						$("a", $this).click(function(event){
							$("div." + op.selected, $this).removeClass(op.selected);
							var parent = $(this).parent().addClass(op.selected);
							$(".ckbox",parent).trigger("click");
							event.stopPropagation();
							$(document).trigger("click");
							if (!$(this).attr("target")) return false;
						});
					},1);
					//$(">li>div",$this).hoverClass();
				});
			},
			subTree:function(op, level) {
				return this.each(function(){
					$(">li", this).each(function(){
						var $this = $(this);
						
						var isLast = ($this.next()[0]?false:true);
						$this.genTree({
							icon:op.icon,
							ckbox:op.ckbox,
							nckbox:op.nckbox,
							exp:isLast?op.options.lastExp:op.options.exp,
							coll:isLast?op.options.lastColl:op.options.coll,
							options:op.options,
							level:level,
							space:isLast?null:op.space,
							showSub:op.showSub,
							isLast:isLast
						});
					});
				});
			},
			genTree:function(options) {
				var op = $.extend({icon:options.icon,ckbox:options.ckbox,nckbox:options.nckbox,exp:"", coll:"", showSub:false, level:0, options:null, isLast:false}, options);
				return this.each(function(){
					var node = $(this);
					var tree = $(">ul", node);
					var parent = node.parent().prev();
					var checked = 'unchecked';
					if(op.ckbox) {
						if($(">.checked",parent).size() > 0) checked = 'checked';
					}
					if (tree.size()>0) {
						tree.addClass("line");
						node.children(":first").wrap("<div></div>");
						var ii='';
						if(node.find("a[checked='checked']").size()>0&&node.find("a[checked='checked']").size()!=node.find("a").size()){
							ii='indeterminate';
						};
						$(">div", node).prepend("<div class='" + (op.showSub ? op.coll : op.exp) + "'></div>"+(op.ckbox ?"<div class='ckbox " + checked + " " +ii + "'></div>":""));
						$(">div>a", node).wrap("<div class='ncont'></div>");
						$(">div>div:last", node).prepend((op.icon?"<span class='"+ (op.showSub ? op.options.folderColl : op.options.folderExp) +"'></span>":""));
						op.showSub ? tree.show() : tree.hide();
						$(">div>div:first,>div>a", node).click(function(){
							var $fnode = $(">li:first",tree);
							if($fnode.children(":first").isTag('a')) tree.subTree(op, op.level + 1);
							var $this = $(this);
							var isA = $this.isTag('a');
							var $this = isA?$(">div>div", node).eq(op.level):$this;
							if (!isA || tree.is(":hidden")) {
								$this.toggleClass(op.exp).toggleClass(op.coll);
								if (op.icon) {
									$(">div>div:last", node).toggleClass(op.options.folderExp).toggleClass(op.options.folderColl);
								}
							}
							(tree.is(":hidden"))?tree.slideDown("fast"):(isA?"":tree.slideUp("fast"));
							return false;
						});
						if(op.showSub) tree.subTree(op, op.level + 1);
					} else {
						node.children().wrap("<div></div>");			
						$(">div", node).prepend("<div class='node'></div>"+(op.ckbox?"<div class='ckbox "+checked+"'></div>":""));
						$(">div>a", node).wrap("<div class='ncont'></div>");
						$(">div>div:last", node).prepend((op.icon?"<span class='file'></span>":""));
						if(op.isLast)$(node).addClass("last");
					}
					if (op.ckbox) node._check(op);
					$(">div>.ncont",node).hoverClass();
					//$(">ul>li>div",node).hoverClass();
					if($.browser.msie)
						$(">div",node).click(function(){
							$("a", this).trigger("click");
							return false;
						});
					$(node)._showMenu();
				});
			},
			_check:function(op) {
				var node = $(this);
				var ckbox = $(">div>.ckbox", node);
				var $input = node.find("a");
				/*var tname = $input.attr("tname"), tvalue = $input.attr("tvalue");
				var attrs = "text='"+$input.text()+"' ";
				if (tname) attrs += "name='"+tname+"' ";
				if (tvalue) attrs += "value='"+tvalue+"' ";*/
				ckbox.siblings('.ncont').find('input:checkbox').appendTo(ckbox);
				//ckbox.append("<input type='checkbox' style='display:none;' " + attrs + "/>").click(function(){
				ckbox.click(function(){
					var cked = ckbox.hasClass("checked");
					var disck = ckbox.hasClass("disabled");
					var aClass = cked?"unchecked":"checked";
					var rClass = cked?"checked":"unchecked";
					if(!disck){
						ckbox.removeClass(rClass).removeClass(!cked?"indeterminate":"").addClass(aClass);
						$("input", ckbox).attr("checked", !cked);
						if(!op.nckbox){
							$(">ul", node).find("li").each(function(){
								var box = $("div.ckbox", this);
								if(box.size()<1){
									$("a,input:checkbox", this).attr("checked", !cked);
								}else{
									box.removeClass(rClass).removeClass(!cked?"indeterminate":"").addClass(aClass).find("input").attr("checked", !cked);
								}
							});
							$(node)._checkParent()
						};
					}
					return false;
				});
				var cAttr = $input.attr("checked") || false;
				var disAttr = $input.attr("disabled") || false;
				if (cAttr) {
					ckbox.find("input").attr("checked", true);
					ckbox.removeClass("unchecked").addClass("checked");
					if(!op.nckbox){$(node)._checkParent()};
				}else{
					ckbox.removeClass("checked").addClass("unchecked");	
				}
				if(disAttr){ 
					ckbox.find("input").attr("disabled", "disabled");
					ckbox.removeClass("unchecked").addClass("disabled");
					if(!op.nckbox){$(node)._checkParent()};
				}
			},
			_checkParent:function(){
				if($(this).parent().hasClass("tree")) return;
				var parent = $(this).parent().parent();
				var stree = $(">ul", parent);
				var ckbox = stree.find(">li>a").size()+stree.find("div.ckbox").size();
				var ckboxed = stree.find("div.checked").size();
				var aClass = (ckboxed==ckbox?"checked":(ckboxed!=0?"indeterminate":"unchecked"));
				var rClass = (ckboxed==ckbox?"indeterminate":(ckboxed!=0?"checked":"indeterminate"));
				$(">div>.ckbox", parent).removeClass("unchecked").removeClass("checked").removeClass(rClass).addClass(aClass);
				parent._checkParent();
			},
			_showMenu:function(){
				var node = $(this);
				var menuName = getMenu(node), isSub = isSubMenu(node), id;
				if(we.utils.isEmpty(menuName))return;
				$('.'+menuName).hide();
				$(node).bind('mousedown',{isroot: true}, nodeMenu);
				$(">div>.ncont",node).bind('mousedown', {isroot: false}, nodeMenu);
				function nodeMenu(event){
					if (event.button==2){
						event.stopPropagation();
						if(isSub == 'true' && ($(node).find('ul').size()>0 || event.data.isroot)){
							$(this).bind('contextmenu', function(e) {
								$("#treemenu").hide();
								$("#treemenuShadow").hide();
								return false;
							});
							return;
						}
						if(menuName!=undefined){
							id=$(">div>.ncont>a",node).attr("tvalue");
							if (!menu) {
								menu = $('<div id="treemenu"></div>').appendTo('body').hide();
							}
							if (!shadow) {
								shadow = $('<div id="treemenuShadow"></div>').appendTo('body').hide();
							}
							hash = hash || [];
							hash.push({});
							$(this).bind('contextmenu', function(e) {
								$("#contextmenu").hide();
								$("#contextmenuShadow").hide();
								if(menu.children("."+menuName).size()>0){
									menu.children("."+menuName).remove();
								}
								menu.append($("."+menuName).clone()).initUI();
								$("."+menuName, menu).siblings().hide();
								$("."+menuName, menu).show().children("li").hoverClass();
								if(event.data.isroot){
									$("."+menuName, menu).children("li").hide();
									$("."+menuName, menu).children("li:first").show();
								}else{
									$("."+menuName, menu).children("li").show();	
								}
								$("."+menuName, menu).children("li").each(function(){
									if(!$(this).children('a').attr('oldhref')){
										$(this).children('a').attr('oldhref',$(this).children('a').attr('href'))
									}
									if(!event.data.isroot){
										var temp = $(this).children('a').attr('oldhref').replace("{id}",id);
									}else{
										var temp = $(this).children('a').attr('oldhref').replace("={id}","=0");
									}
									$(this).children('a').attr('href',temp);
								});
								$("."+menuName, menu).children("li").children("a").bind('click',hide);
								var posX = e.pageX;
								var posY = e.pageY;
								if ($(window).width() < posX + menu.width()) posX -= menu.width();
								if ($(window).height() < posY + menu.height()) posY -= menu.height();
						
								menu.css({'left':posX,'top':posY}).show();
								shadow.css({width:menu.width(),height:menu.height(),left:posX+3,top:posY+3}).show();
								$(document).one('click', hide);
								return false;
							});
				  		}
				  		function hide() {
							menu.hide();
							shadow.hide();
				  		}
					}
				}
				function getMenu(node){
					var tempName;
					if(node.hasClass("tree")){
						if(node.attr("treeMenu")!=undefined){
							tempName = node.attr('treeMenu');
						}
						return tempName;
					}
					var nparent = node.parent();
					while(!nparent.hasClass("tree")){
						if(nparent.attr("treeMenu")!=undefined){
							tempName = nparent.attr('treeMenu');
							break;
						}
						nparent = nparent.parent();
					}
					if(nparent.hasClass("tree") && nparent.attr("treeMenu")!=undefined && tempName==undefined){
						tempName = nparent.attr('treeMenu');
					}
					return tempName;
				}
				function isSubMenu(node){
					var tempName;
					if(node.hasClass("tree")){
						if(node.attr("isSubMenu")!=undefined){
							tempName = node.attr('isSubMenu');
							return tempName;
						}
						return 'false';
					}
					var nparent = node.parent();
					while(!nparent.hasClass("tree")){
						if(nparent.attr("isSubMenu")!=undefined){
							tempName = nparent.attr('isSubMenu');
							break;
						}
						nparent = nparent.parent();
					}
					if(nparent.hasClass("tree") && nparent.attr("isSubMenu")!=undefined && tempName==undefined){
						tempName = nparent.attr('isSubMenu');
					}
					return tempName;
				}
				function hide() {
					menu.hide();
					shadow.hide();
				}
			},
			_createNode:function(tvalue){
				var node = $('.focusNode');
				var nlevel = 1;
				var nparent = node.parent();
				getLevel(node);
				addNew(nlevel);
				alertMsg.correct('您的数据提交成功！')
				$.pdialog.close('createNode');
				function addNew(nlevel){
					var node = $('.focusNode');
					var tree = $(">ul", node);
					var checked = 'unchecked';
					if (tree.size()>0) {
						$("li.last",tree).removeClass("last");
						tree.append("<li class='last'><div><div class='node'></div><a title='' rel='page1' target='navTab' tvalue='"+tvalue+"' href='"+$('#newNode_href').val()+"'>"+$('#newNode_name').val()+"</a></div></li>");
					} else {
						if($(node).hasClass("last")){
							$(">div>.node",node).addClass('last_collapsable').removeClass('node');						
						}else{
							$(">div>.node",node).addClass('collapsable').removeClass('node');
						}
						node.append("<ul><li class='last'><div><div class='node'></div><a title='' rel='page1' target='navTab' tvalue='"+tvalue+"' target='navTab' href='"+$('#newNode_href').val()+"'>"+$('#newNode_name').val()+"</a></div></li></ul>");
						$(">div>.collapsable", node).click(function(){
							$(this).toggleClass('collapsable').toggleClass('expandable');	
							if(node.hasClass('last')){
								$(this).toggleClass('last_collapsable').toggleClass('last_expandable');	
							}else{
								
							}
							var temp = $(this).parent().siblings("ul");
							(temp.is(':hidden'))?temp.slideDown('fast'):temp.slideUp('fast');
							return false;
						});
						$(">div>.last_collapsable", node).click(function(){
							$(this).toggleClass('last_collapsable').toggleClass('last_expandable');	
							var temp = $(this).parent().siblings("ul");
							(temp.is(':hidden'))?temp.slideDown('fast'):temp.slideUp('fast');
							return false;
						});
					}
					$(">ul>li:last a",node).click(function(event){
						var $this = $(this);
						var title = $this.attr("title") || $this.text();
						var tabid = $this.attr("rel") || "_blank";

						var fresh = eval($this.attr("fresh") || "true");
						var external = eval($this.attr("external") || "false");
						var url = unescape($this.attr("href")).replaceTmById($(event.target).parents(".unitBox:first"));
						DWZ.debug(url);
						if (!url.isFinishedTm()) {
							alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
							return false;
						}
						navTab.openTab(tabid, url,{title:title, fresh:fresh, external:external});
			
						event.preventDefault();
					});
					$(">ul>li>div",node).mouseover(function(){
						$(".focusNode").each(function(){
							$(this).removeClass('focusNode');	
						});
						$(this).parent("li").addClass('focusNode');
						$(this).addClass("hover");
					}).mouseout(function(){
						$(this).removeClass("hover");
					});
					if($.browser.msie)
						$(">ul>li>div",node).click(function(){
							$("a", this).trigger("click");
							return false;
						});
					$('.focusNode')._showMenu();
				}
				function getLevel(node){
					var nparent = node.parent()
					while(!nparent.hasClass("tree")){
						nlevel+=1
						nparent = nparent.parent().parent()
					}
				}
			},
			_modifyNode:function(){
				var node = $('.focusNode');
				$(">div>a",node).text($('#newNode_name').val())
				$(">div>a",node).attr('href',$('#newNode_href').val())
				alertMsg.correct('您的数据提交成功！')
				$.pdialog.close('modifyNode');
			},
			_deleteNode:function(){
				if($(".focusNode>ul").size()>0){
					alertMsg.warn("节点 【"+$(".focusNode>div>a").text()+"】 下存在子节点，请检查！")	
				}else{
					alertMsg.confirm("您确定要删除节点 【"+$(".focusNode>div>a").text()+"】 吗？", {
						okCall: function(){
							if($(".focusNode").siblings().size()==0){
								$(".focusNode").parent().parent().children("div:last").removeAttr("class");
								$(".focusNode").parent().remove();
							}else{
								$(".focusNode").remove()	
							}
							
						}
					});
				}	
			}
		});
	 	$.fn.treeUp=function(){
			$("li",this).each(function(){
				$(">ul",this).slideUp("fast");
			});	
		};
	})(jQuery);
});