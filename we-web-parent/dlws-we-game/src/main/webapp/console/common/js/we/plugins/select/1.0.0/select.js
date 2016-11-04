define('select',function (require) {
		require('./css/select.css');
		var $uuid = require('uuid');
		//根据传入的select对象构建美化UI
		//obj 支持id class 对象，数组
		var init = function(options){
			var op = $.extend({
				obj:{},
				width:238,
				height:34
			},options);
			var $select=$(op.obj);
			
			if(we.utils.isEmpty($select)){// utils.js  -->工具类
				return ;
			}
			buildSelect(op);
			
			/*模拟select子元素点击添加背景色以及赋值*/
			$(".selectDiv").find('li').die('click').live('click',function(){
				$(this).addClass('crt').siblings().removeClass('crt');
				var ulUUID = $(this).parent().attr('uuid');
				$("select[uuid='"+ulUUID+"']").val($(this).attr('val')).change();
				var subOp = $.extend(op,{
					obj:$("select[uuid='"+ulUUID+"']")
				});
				init(subOp);
				$(this).parent().prev().html($(this).html());
				$('select').next('label').hide();
			});
			
			/*模拟select*/
			$('.selectDiv').die('click').live('click',function(){
				var $this = $(this);
				$this.children().last().slideToggle(1);
				$this.children().eq(0).children().toggleClass('iconUp');
			})
			$('.selectDiv').die('mouseleave').live('mouseleave',function(){
				var $this = $(this);
				$this.children().last().hide();
				$this.children().eq(0).children().removeClass('iconUp');
			})
			
		}
		//加载下拉框
		var load = function($this,op){
			var selected=$this.children('option:selected').html();
			var uuid = $this.attr('uuid')||$uuid.getUUID()(15);
			var $boxDivHTML;
			//判断
			if($('[box='+uuid+']').size()>0){
				$boxDivHTML=$('[box='+uuid+']');
			}else{
				$boxDivHTML = $('<div box='+uuid+' class="selectWrap"></div>');
			}
			//如果标签上有width，去标签上的。否则去op.width
			var width =op.width;
			if(!we.utils.isEmpty($this.attr('width'))){
				width=$this.attr('width')
			}
			var height =op.height;
			if(!we.utils.isEmpty($this.attr('height'))){
				height=$this.attr('height')
			}
			
			
			$boxDivHTML.css('width',width).css('height',height);
			var   boxHTML ="<div class='selectDiv'>"+
							"   <div class='select-l'>"+
							selected+
							"</div>"+
							"<div class='select-r'><i></i></div>"+
							"<ul class='sel-sub' uuid='"+uuid+"'>";
			//遍历Li		
			$this.children('option').each(function(){
					if($(this).val()==$this.children('option:selected').val()){
						boxHTML+="<li title='"+$(this).html()+"' val='"+$(this).val()+"' class='crt'>"+$(this).html()+"</li>"
					}else{
						boxHTML+="<li title='"+$(this).html()+"' val='"+$(this).val()+"'>"+$(this).html()+"</li>"
					}
			});
			boxHTML+=		"   </ul>"+
							"</div>";
			$boxDivHTML.html($(boxHTML).css({"z-index":$this.attr('zIndex')}));
			
			$boxDivHTML.insertBefore($this);
			$this.attr('uuid',uuid).hide();
			
			//构建完成了
			_finish($this);
			
			var subUrl = $this.attr('subUrl');
			var sub = $this.attr('sub');
			if(we.utils.isEmpty(subUrl)||we.utils.isEmpty(sub)||we.utils.isEmpty($this.val())){
				destory($(sub));
				return;
			}
			subUrl = subUrl.replace('#val',$this.val());
			$ajax.weAjax({
				url:subUrl,
				success:function(data){
					destory($(sub));
					if(we.utils.isEmpty(data)){
						return;
					}
					var $sub = $(sub);
					createSelect($sub,data,false);
					var subOp = $.extend(op,{
						obj:$sub
					});
					buildSelect(op);
					
				}
			});
		}
		var _finish=function($select){
			var finish = $select.data('finish');
			//TODO 触发事情
			if(!we.utils.isEmpty(finish) && $.isFunction(finish)){
				finish.call(this);
			}
		}
		//删除子节点
		var destory = function(obj){
			obj.hide().empty();
			$('[box='+obj.attr('uuid')+']').remove();
			var sub = obj.attr('sub');
			if($(sub).size()>0){
				destory($(sub));
			}
		}
		//重新构建select下拉框 
		var createSelect = function($this,data){
			var val = $this.val();
			$this.empty();
			$.each(data,function(index,element){
				$this.append('<option value='+element.id+'>'+element.name+'</option>')
			})
			if(!we.utils.isEmpty(val)){
				$this.val(val);
			}
			selected($this);
		}
		var selected = function($this){
			var val = $this.attr('val');
			
			var uuid = $this.attr('uuid');
			var isFirst = true;
			//没加载过，则按val加载
			if(we.utils.isEmpty(uuid)){
				//如果有val优先，优先取val值
				if(!we.utils.isEmpty(val)){
					$.each($this.children('option'),function(index,element){
						if(val==$(element).attr('value')){
							isFirst=false;
							$(element).attr('selected','selected');
							//要不要加change
						}
					})
				}else{
					isFirst=false;
				}
			}else{
				$.each($this.children('option'),function(index,element){
					if($this.val()==$(element).attr('value')){
						isFirst=false;
						$(element).attr('selected','selected');
						//要不要加change
					}
				})
				//加载过，则还原数据加载
				
			}
			//如果没有值，则默认第一个
			if(isFirst){
				$this.children('option').eq(0).attr('selected','selected');
			}
			
//			if(we.utils.isEmpty(val)||val==0){
//				var isSelected=false;
//				$.each($this.children('option'),function(index,element){
//					if($this.val()==$(element).attr('value')){
//						isSelected=true;
//						$(element).attr('selected','selected');
//					}
//				})
//				if(!isSelected){
//					$this.children('option').eq(0).attr('selected','selected');
//				}
//			}else{
//				var uuid = $this.attr('uuid');
//				if(we.utils.isEmpty(uuid)){
//					var isSelected=false;
//					$.each($this.children('option'),function(index,element){
//						if(val==$(element).attr('value')){
//							isSelected=true;
//							$(element).attr('selected','selected');
//						}
//					})
//					if(!isSelected){
//						$this.children('option').eq(0).attr('selected','selected');
//					}
//					
//				}else{
//					$this.children('option').eq(0).attr('selected','selected')
//				}
//			}
		}
		//构造select美化，递归美化
		var buildSelect = function(op){
			var $select = $(op.obj);
			$select.each(function(){
				var $this = $(this);
				var url = $this.attr('selfUrl');
				var value = $this.attr('val');
				if(!we.utils.isEmpty(url)){
					$ajax.weAjax({
						url:url,
						success:function(data){
							if(we.utils.isEmpty(data)){
								return ;
							}
							createSelect($this,data);
							load($this,op);
						}
					});
				}else{
					selected($this);
					load($this,op);
				}
			});
		}
		return {
			init:init
		}

});
