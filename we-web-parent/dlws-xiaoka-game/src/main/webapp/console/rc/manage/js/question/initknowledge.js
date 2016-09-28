define(function(require, exports, module){
	var init = function(){
		$(function(){
			var view = {
				initAjax: function(){
					var  $ajax = require('ajax'); 
			 		var kvalue = $('.Initknowledge').val();
						if(!we.utils.isEmpty(kvalue)){
			     			$ajax.vkoAjax({ 
					 			url: '/question/getKnowledgeById.json',   
					 			type: 'POST',   
						        data: { 
						            id : kvalue
						        },
						     	success: function(data){
			             		var sk = $("<select name='KnowledgeTree' id='knowledgetreeid'></select>");
			             		$("#knowledge").parent().parent().append(sk);
			             		sk.append("<option value='"+data.id+"'>"+data.name+"</option>"); 
			             		$('[name=KnowledgeId]').initUI();
								view.selectFunt(data.objectId);
			             		view.parentKnowledge(data.parentId,sk.parent());
			         		}, 
			     		});
			 		}
				},
				parentKnowledge: function(id,obj){
			    	$ajax.vkoAjax({ 
					 	url: '/question/getKnowledgeByParentId.json',   
					 	type: 'POST',   
			         	data: { 
			            	id : id
			         	},
			         	success: function(data){  
			            	if(!we.utils.isEmpty(data)){
			               		var sk = $("<select name='KnowledgeTree' id='knowledgetreeid'></select>");
			             		obj.before(sk);
			             		sk.append("<option value='"+data.id+"'>"+data.name+"</option>"); 
			             		$('[name=KnowledgeId]').initUI();
			             		view.parentKnowledge(data.parentId , sk.parent());
			            	}
			         	}, 
			     	});
			 	},
				selectFunt: function(objectId){
					var childrens = $("select[name='KnowledgeId']").children();
					for(var i=0;i<childrens.length;i++){
						if(we.utils.isEqual($(childrens[i]).val(),objectId+"")){
						    $(childrens[i]).attr("selected","selected");
							$("select[name='KnowledgeId']").selectRefresh();
						}
					}
				},
				invoke : function(){
					view.initAjax();
				}	
			}
			view.invoke();
		});
	};
	module.exports = init;
});
