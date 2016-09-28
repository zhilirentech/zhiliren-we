define(function(require, exports, module){
	var init = function(){
		$(function(){
		    var init={
				initInfo: function(){
				   	$("input[name='bank.knowledge']").change(function(){
					   var knowledgeType = $("#knowledgeType").val();
				         if(we.utils.isEqual($(this).val(),knowledgeType)){
							$dialog.alert("不能与试题知识点树相同!","warning");
				            $(this).attr("checked",false);
						}
				   });
				   $("select[name='knowledgeType']").on("change",function(){
				   	   var obj = $(this).val();
					   $(".btn_modify").attr("href","/knowledgetree/addtree.json?id="+obj);
				   });
				},
				studyPhase:function(){
				    $("select[name='bank.studyPhase']").on("change",function(){
						init.studyPhaseAjax($(this).val());
					});
				},
				subject:function(){
					$("select[name='bank.subject']").on("change",function(){
						//调用学科的值
						var objval = init.subjectVal();
						if(!we.utils.isEmpty(objval)){
							init.subjectAjax(objval,$(this).val());
						}
					});
				},
				//学段ajax
				studyPhaseAjax: function(obj){
					$ajax.weAjax({
			      		url: "/bank/subject4StudyPhase.json",
			      		data: {
			             	id: obj
			            },
			      		type: "POST",
			      		success: function(data){
						   init.del();
					       init.append(data);
					       $("[name='bank.subject']").selectRefresh();
			      		} 
			  		});
				},
				//学科ajax
				subjectAjax: function(objval,obj){
					//根据学科查出
					$ajax.weAjax({
			      		url: "/bank/bank4Knowledge.json",
			      		data: {
							studyId: objval,
			             	id: obj
			            },
			      		type: "POST",
			      		success: function(data){
							init.knowledgeDel();
							init.appendknowledge(data);
							$("[name='bank.knowledgeType']").selectRefresh();
							init.delknowledge();
							init.knowledge(data);
							$("[name='knowledgeType']").selectRefresh();
			      		} 
			  		});
				},
				//添加
				append:function(obj){
					for(var i= 0;i<obj.length;i++){
				       $("select[name='bank.subject']").append("<option value='"+obj[i].id+"'>"+obj[i].name+"</option>");
					   $("select[name='bank.subject']").initUI();
					}
				},
				//添加到章节知识点后面
				appendknowledge:function(obj){
					for (var i = 0; i < obj.length; i++) {
						$("select[name='bank.knowledgeType']").append("<option value='"+obj[i].id+"'>"+obj[i].name+"</option>");
					    $(".knowledgecheckbox").append("<input class='knowledge' type='checkbox' name='bank.knowledge' value='"+obj[i].id+"'>"+obj[i].name+"");
						$("select[name='bank.knowledgeType']").initUI(); 
						$(".knowledgecheckbox").initUI();
					}
				},
				knowledge:function(obj){
					for(var i= 0;i<obj.length;i++){
				       $("select[name='knowledgeType']").append("<option value='"+obj[i].id+"'>"+obj[i].name+"</option>");
					   $("select[name='knowledgeType']").initUI();
					}
				},
				del:function(){
					$("select[name='bank.subject']").children(":not(:first)").remove();
				},
				delknowledge:function(){
					$("select[name='knowledgeType']").children(":not(:first)").remove();
				},
				knowledgeDel:function(){
					$("select[name='bank.knowledgeType']").children(":not(:first)").remove();
					$(".knowledgecheckbox").text("");
					$(".knowledgecheckbox").children().remove();
				},
				//获取段被选中的值
				subjectVal: function(){
					//获取学段选中的值
					var studyPhaseVal =  $("select[name='bank.studyPhase']").val();
					return studyPhaseVal;
				},
				knowledgeAjax:function(){
					$("select[name='knowledgeType']").on("change",function(){
					  $ajax.weAjax({
			      		url: "/question/bank4Knowledge.json",
			      		data: {
			             	id: $(this).val()
			            },
			      		type: "POST",
			      		success: function(data){
							  for (var i = 0; i < data.length; i++) {
							  	   var text = data[i].name+"      ";
							       $(".knowledgeTree").append(text);
							  }
			      			} 
			  			});
					});
				},
				invoke:function(){
				  init.initInfo();
				  init.studyPhase();
				  init.subject();
				  init.knowledgeAjax();
				}
			}
			init.invoke();
		});
	};
	module.exports = init;
});
