define('editor',function (require, exports, module) {
	/**
     * @name editor
     * @class 编辑器插件
     * @author 庄君祥
     * @version v1.0.0  
     * @data 2015年3月25日
     * 使用说明
     * 		<textarea id="editor" class="editor" name="content" words="200" placeholder="我想对学生说的..."></textarea>
	 * 		<!--
	 * 			属性说明：
	 * 			class="editor"      【必填】 ——这个是固定的不能修改
	 * 			id="editor"       　【必填】 ——请确保每一个编辑器id不一样，这个属性
	 * 			width="100"       　【选填】 ——你懂的，不解释了
	 * 			height="100"     　 【选填】 ——你懂的，不解释了
	 * 			words="100"       　【选填】 ——可以输入多少个字
	 * 			placeholder="xxx"   【选填】 ——编辑器在没有文字时的提示信息
	 * 		-->
     */
	//为了解决兼容性问题，延长时间
	var lazyTime=400;
	var path = module.id.substring(0,module.id.lastIndexOf('/')+1);
	//初始化，全局化参数。。。
	var pasterMgr;
	var init = function($textareas,postUrl){
		require('./WordPaster/css/WordPaster.css');
		require('./css/editor.css');
		require('./WordPaster/css/ui-lightness/jquery-ui-1.8.11.custom.css');
		require('./ckeditor/ckeditor.js');
		require('./WordPaster/js/WordPaster.js');
		setTimeout(function(){
			if(we.utils.isEmpty(pasterMgr)){	
				pasterMgr = new PasterManager();
				window.pasterMgr=pasterMgr;
				pasterMgr.Config["PostUrl"] = postUrl;
				pasterMgr.Load();//加载控件
				
				CKEDITOR.config.extraPlugins = 'imagepaster,netpaster';		//在工具栏增加插件按钮
				
				//CKEditor初始化完毕
				CKEDITOR.on( 'instanceReady', function( ev ) {
					pasterMgr.Init(ev.editor);
					ev.editor.on('change',function(){
						$('#'+ev.editor.name).val(ev.editor.getData());
						statWords(ev.editor);
					});
					setupTips(ev.editor);
					placeholder(ev.editor);
					statWords(ev.editor);
					
				});
				CKEDITOR.on('currentInstance', function(ev){
					if(CKEDITOR.currentInstance){
						pasterMgr.SetEditor(CKEDITOR.currentInstance);
					}
				});
				//自定义快捷键
				CKEDITOR.config.keystrokes = [
                     [CKEDITOR.CTRL + 86/*V*/,'imagepaster']
                ];
			}
			add($textareas);
		},lazyTime);
	}
	var setupTips = function($editor){
		var $ckEditor = $('#cke_'+$editor.name);
		if($ckEditor.find('.tips').size()<=0){
			var html = $('<span class="tips"></span>')
				.html('如果要使用ctrl+c+v直接操作图片，请先下载<a href="'+path+'/WordPaster/setup/WordPaster.exe">图片上传插件</a>，然后关闭所有浏览器安装，安装后就可以使用了。<em></em><a class="step">如果您是chrome用户，确保您的插件始终可用。如有使用问题，可以联系我们</a>');
			$ckEditor.append(html);
			//tips提示
			$('.tips em').mouseover(function(){
				$(this).next().show();
			});
			$('.tips em').mouseout(function(){
				$('.tips .step').hide();
			});
		}
	}
	
	//提示文字 
	var placeholder = function($editor){
		var msg="说点什么吧";
		var $textarea=$('#'+$editor.name);
		//超出的字数
		if(!we.utils.isEmpty($textarea.attr('placeholder'))){
			msg=$textarea.attr('placeholder');
		}
		var $ckEditor = $('#cke_'+$editor.name);
		if(we.utils.isEmpty($ckEditor)){
			if($ckEditor.find('.hint').size()<=0){
				var html = $('<span class="hint"></span>').html(msg);
				$ckEditor.append(html);
			}
		}
		//显示
        if(!we.utils.isEmpty($editor.getData())){
    		$('.hint').hide();
    	}
		$editor.on('focus',function(ev){
			$('.hint').hide();
		});
		$editor.on('blur',function(ev){
			if(we.utils.isEmpty($editor.getData())){
				$('.hint').show();
				if($('label.error').is(':visible')){
					$('.hint').hide();
				}
	    	}else{
	    		$('.hint').hide();
	    	}
		});
		$editor.on('change',function(){
			$('label.error').hide()
		})
	}
	//统计字数
	var  statWords = function($editor){
		var maxlength = 20;
		//超出的字数
		if(!we.utils.isEmpty($('#'+$editor.name).attr('words'))){
			maxlength=$('#'+$editor.name).attr('words');
		}
		//TODO 再重构下。
		_editor=$editor;
		var oldhtml = _editor.document.getBody().getHtml();
		var description = oldhtml.replace(/<.*?>/ig,"");
		
		var defaultHTML = $('<span class="strlen fl" style="display: inline;">您还可以输入<em>'+maxlength+'</em>字</span>');
		var $tip = $('#cke_'+$editor.name).next(".strlen");
		if($tip.length<1){
			$('#cke_'+$editor.name).after(defaultHTML);
		}
		if(description.length>maxlength){
			$tip.html("已经超出<em>"+(description.length-maxlength)+"</em>字");
		}else{
			$tip.html("还可以输入<em>"+(maxlength-description.length)+"</em>字");
		}
	}
	//判断是否超出了最大的字数
	var IsBeyond=function($editor){
		var maxlength = 200;
		if(!we.utils.isEmpty($('#'+$editor.name).attr('words'))){
			maxlength=$('#'+$editor.name).attr('words');
		}
		_editor=$editor;
		var oldhtml = _editor.document.getBody().getHtml();
		var description = oldhtml.replace(/<.*?>/ig,"");
		return description.length>maxlength;
	}
	//添加编辑器实例
	var add=function($textareas){
		var time=0;
		if(we.utils.isEmpty(CKEDITOR.config.keystrokes)){
			time=lazyTime;
		}
		setTimeout(function(){
			$textareas.each(function(){
				var attrs ={};
				//宽
				if(!we.utils.isEmpty($(this).attr('width'))){
					attrs.width=$(this).attr('width');
				}
				//高
				if(!we.utils.isEmpty($(this).attr('height'))){
					attrs.height=$(this).attr('height');
				}
				//图层
				if(!we.utils.isEmpty($(this).attr('zIndex'))){
					attrs.baseFloatZIndex =$(this).attr('zIndex');
				}
				//加载CKEditor编辑器
				var id = $(this).attr('id');
				CKEDITOR.replace(id,attrs);
			});
		},time);
	}
	return {
		init:init,
		add:add,
		IsBeyond:IsBeyond
	}

});