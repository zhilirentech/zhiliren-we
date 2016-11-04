define(function(require){
	$(function(){
		var list = {
			init:function(){
				$(".upMove").live("click",function(){
					//视觉上的移动
					list.upMove($(this));
				});
				$(".nextMove").live("click",function(){
					//视觉上的移动
					list.nextMove($(this));
				});
			},
			nextMove:function(obj){
				if (!we.utils.isEmpty(obj.parent().parent().next())) {
					var count = obj.attr("count");
					var name = obj.attr("tbody");
					var url = obj.attr("url");
					var orderNum = document.getElementById(name).rows[count - 1].cells[0].childNodes[1].value;
					var nextorderNum = document.getElementById(name).rows[count].cells[0].childNodes[1].value;
					var id = document.getElementById(name).rows[count - 1].cells[0].childNodes[2].value;
					var nextid = document.getElementById(name).rows[count].cells[0].childNodes[2].value;
					list.getvkoAjax(orderNum, nextorderNum, id, nextid, url, obj);
				}
			},
			upMove:function(obj){
				if (!we.utils.isEmpty(obj.parent().parent().prev())) {
					var count = obj.attr("count");
					var name = obj.attr("tbody");
					var url = obj.attr("url");
					var orderNum = document.getElementById(name).rows[count - 1].cells[0].childNodes[1].value;
					var nextorderNum = document.getElementById(name).rows[count - 2].cells[0].childNodes[1].value;
					var bankid = document.getElementById(name).rows[count - 1].cells[0].childNodes[2].value;
					var nextbankid = document.getElementById(name).rows[count - 2].cells[0].childNodes[2].value;
					list.getvkoAjax(orderNum, nextorderNum, bankid, nextbankid, url, obj);
				}
			},
			getvkoAjax:function(number,nextnumber,id,nextid,url,obj){
				if(we.utils.isEmpty(number)&&we.utils.isEmpty(nextnumber)&&we.utils.isEmpty(id)&&we.utils.isEmpty(nextid)){
					$dialog.alert("参数有误,暂不能移动 ！", "warning");
				    return false;
				}else if(we.utils.isEqual(nextnumber,"0")&&we.utils.isEqual(number,"0")){
					$dialog.alert("参数有误,暂不能移动 ！", "warning");
					 return false;
				}else{
				$ajax.vkoAjax({
					url: '/'+url+'/move.json',
					data:{
					number: number,
					nextnumber: nextnumber,
					id: id,
					nextid: nextid
					},
					type:'post',
					success:function(data){
						
					}
				});
				}
			},
			movementup:function(obj){
				var text = obj.parent().parent().children(":eq(0)").text();
				var prevtext = obj.parent().parent().prev().children(":eq(0)").text();
				if(!we.utils.isEmpty(obj.parent().parent().prev())){
					obj.parent().parent().children(":eq(0)").text(prevtext);
					obj.parent().parent().prev().children(":eq(0)").text(text);
					obj.parent().parent().prev().before(obj.parent().parent());
				}else{
					$dialog.alert("不能移动了 ！", "warning");
				}
			},
			movementnext:function(obj){
				var text = obj.parent().parent().children(":eq(0)").text();
				var nexttext = obj.parent().parent().next().children(":eq(0)").text();
				if(!we.utils.isEmpty(obj.parent().parent().next())){
					obj.parent().parent().children(":eq(0)").text(nexttext);
					obj.parent().parent().next().children(":eq(0)").text(text);
					obj.parent().parent().before(obj.parent().parent().next());
				}else{
					$dialog.alert("不能移动了 ！", "warning");
				}
			},
			invoke:function(){
				list.init();
			}
		}
		list.invoke();
	});
});
