define(function(require, exports, module){
	var init = function(){
		$(function(){
			$(".submitquestion").on("click",function(){
				var last = $("#divkno").children().last().find("select");
				last.attr("name","Knowledge");
			});
		});
	};
	module.exports = init;
});
