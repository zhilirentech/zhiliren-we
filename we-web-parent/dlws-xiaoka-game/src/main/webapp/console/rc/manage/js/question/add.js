define(function(require, exports, module){
	var init = function(){
		$(function(){
			var load={
				createSubSelecte:function(){
				     $("#knowledge").on("change",function(){
					 var $this = $(this);
					 $this.parent().nextAll().remove();
					 $this.parent().nextAll().empty();
					 $('[name=KnowledgeId]').initUI();
					 load.createSub($this,$this.val());
					});	
				},
				createSub:function(obj,id){
					$ajax.weAjax({
				    	url: "/question/getKnowledge.json",
				        data: {
				        	id: id
				        },
				        type: "POST",
				        cache: false,
				        success: function(data){
				        	if(we.utils.isEmpty(data)){
				            	obj.parent().next().nextAll().remove();
				            	obj.parent().next().nextAll().empty();
				            	$('[name=KnowledgeId]').initUI();
				            	return;
				            }
				             var sk = $("<select name='KnowledgeTree' id='knowledgetreeid'></select>");
				             sk.on("change",function(){
				             	$(this).parent().nextAll().remove();
				             	$(this).parent().nextAll().empty();
				             	$('[name=KnowledgeId]').initUI();
				             	load.createSubSelecteTree($(this),$(this).val());
				             });
				             obj.parent().parent().append(sk);
				             for(var i=0;i<data.length;i++){
				                sk.append("<option value='"+data[i].kid+"'>"+data[i].name+"</option>"); 	
				             }
				              $('[name=KnowledgeId]').initUI();
				        } 
				  	});
				},
				createSubSelecteTree:function(obj,id){
					$ajax.weAjax({
				            url: "/question/getKnowledgetree.json",
				            data: {
				                id: id
				            },
				            type: "POST",
				            cache: false,
				            success: function(data){
				            if(we.utils.isEmpty(data)){
				            	obj.parent().next().nextAll().remove();
				            	obj.parent().next().nextAll().empty();
				            	$('[name=KnowledgeId]').initUI();
				            	return;
				            }
				             var sk = $("<select name='KnowledgeTree' id='knowledgetreeid'></select>");
				             sk.change(function(){
				             	$(this).parent().nextAll().remove();
				             	$(this).parent().nextAll().empty();
				             	$('[name=KnowledgeId]').initUI();
				             	load.createSubSelecteTree($(this),$(this).val());
				             	var val = $("#divkno").children().last().find("select").val();
				             });
				             obj.parent().parent().append(sk);
				             for(var i=0;i<data.length;i++){
				                sk.append("<option value='"+data[i].id+"'>"+data[i].name+"</option>"); 	
				             }
				              $('[name=KnowledgeId]').initUI();
				        } 
				  	});
				},
				submit:function(){
					$(".submitquestion").on("click",function(){
						var last = $("#divkno").children().last().find("select");
						last.attr("name","Knowledge");
					});
				},
				initKnolegde: function(){
					$("select[name='bankId']").live("change",function(){
						var bankId = $(this).val();
						if(!we.utils.isEmpty(bankId)){
							load.ajaxQuestionType(bankId);
							//加载知识点
							load.AjaxKnolegde(bankId);
						}
					});
				},
				AjaxKnolegde: function(bankId){
					$ajax.weAjax({
				            url: "/question/intKnolegde.json",
				            data: {
				                id: bankId
				            },
				            type: "POST",
				            cache: false,
				            success: function(data){
				               load.appendKnolegde(data);
				        } 
				  	});
				},
				ajaxQuestionType: function(bankId){
					$ajax.weAjax({
				            url: "/question/intQuestionType.json",
				            data: {
				                id: bankId
				            },
				            type: "POST",
				            cache: false,
				            success: function(data){
				               load.appendType(data);
				        } 
				  	});
				},
				appendKnolegde: function(obj){
					load.delKnowledge($("select[name='KnowledgeId']"));
					for(var i = 0 ; i < obj.length; i ++){
						$("select[name='KnowledgeId']").append("<option value='"+obj[i].id+"'>"+obj[i].name+"</option>");
					    $("select[name='KnowledgeId']").selectRefresh();
					}
				},
				appendType: function(obj){
					load.delKnowledge($("select[name='type']"));
					for(var i = 0 ; i < obj.length; i ++){
						$("#questiontype").append("<option value='"+obj[i].id+"'>"+obj[i].name+"</option>");
					    $("#questiontype").selectRefresh();
					}
				},
				delType: function(obj){
					obj.children().remove();
					obj.parent().next().remove();
					obj.selectRefresh();
				},
				delKnowledge: function(obj){   //编辑的时候不需要保留   ":not(:first)"
					obj.children().remove();
					obj.parent().next().remove();
					obj.selectRefresh();
				},
			    invoke:function(){
					load.initKnolegde();
					load.createSubSelecte();
					load.submit();
				}
			}
			load.invoke();
		});
	};
	module.exports = init;
	
});
		