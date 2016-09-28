<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/wap/common/tld.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta content="width=device-width, minimum-scale=1,initial-scale=1, maximum-scale=1, user-scalable=1" id="viewport" name="viewport" />
		<meta content="yes" name="apple-mobile-web-app-capable" />
		<meta content="black" name="apple-mobile-web-app-status-bar-style" />
		<link rel="stylesheet" type="text/css" href="${base}up/css/mui.min.css" />
		<link rel="stylesheet" href="${base}up/css/login.css" />
		<link rel="stylesheet" type="text/css" href="${base}up/css/self.css" />
		<link rel="stylesheet" href="${base}up/css/index.css" />
		<link rel="stylesheet" href="${base}up/css/backadd.css"/>
		<script src="${base}up/js/mui.min.js"></script>
		<script src="${base}up/js/mui.pullToRefresh.js"></script>
		<script src="${base}up/js/mui.pullToRefresh.material.js"></script>
		<script src="http://cdn.bootcss.com/jquery/3.1.0/jquery.js"></script>
		<script src="${base}up/js/time.js"></script>
		<title>UP首页</title>
	</head>
	<style>
		.gra{
			background: gray;
			border-radius: 50%;
		}
		.orange{
			background: orchid;
			border-radius: 50%;
		}
	</style>

	<body>
		<!-- <div class="header">
			<span></span> 首页
			<span></span>
		</div> -->
		<div class="main clearfix">
			<img style="max-width:100%" src="${base}up/img/banner1.png" alt="" />
			<div class="personage clearfix">
				<%-- <a href="${base}wap/center/toCenter.html"><p class="first"></p></a> --%>
				<a href="${base}wap/mynews.html"><p class="two"></p></a>
			</div>
			<div class="signin">
				<p class="first">
					<span>签到天数</span><span id="days">${obj.days}天</span>
				</p>
				<c:if test="${obj.signStatus != 2}"><!-- 未参与签到不显示 -->
					<div class="two">
						<p>签到为每天</p>
						<p>${obj.keyValue}</p>
					</div>
				</c:if>
			</div>
			<div class="money">
				<span>奖金池总额</span>
				<span>${obj.total==null?0:obj.total}元</span>
			</div>
		</div>
		<%@include file="/WEB-INF/wap/common/nav.jsp"%><!-- 底部导航 -->
		<div class="menu" >
			<div class="submenu">
				<p class="one" id="upbutn">
				<c:choose>
					<c:when test="${obj.signStatus == 0}"><!--未签到  -->
						我要UP
					</c:when>
					<c:otherwise>
						参与UP
					</c:otherwise>
				</c:choose>
				</p>
				<a href="${base}wap/game/game.html">
					<p class="two">找人UP</p>
				</a>
				<p class="three " id="rulebtn">参与规则</p>
				<p class="four" id="balance">余额￥${obj.balance}元</p>
			</div>
		</div>
		<div class="rulePopup">
			<p class="one">参与规则</p>
			<p class="three">玩法介绍：${obj.bulletin}</p>
			<p class="two" id="ruleok">我知道了</p>
		</div>

		<div class="sel_cal">
			<a class="fl prev_month">
				<</a>
					<p class="showdate fl"></p>
					<a class="fr next_month">></a>
		</div>
		<div class="text"></div>
		<script type="text/javascript">
			var signStatus = ${obj.signStatus};
			var timeScpoe = "${obj.keyValue}";//签到时间范围
			var currDate = "${obj.currDate}";
			var signListJson = ${obj.signListJson};
			$(function() {
				var arr = signListJson ;//0:未签到,1:已签到
				console.log(arr);
				//var arytime = ["2016-7-1", "2016-7-3", "2016-7-4", "2016-7-5", "2016-8-1", "2016-8-2", "2016-8-31", "2016-9-1", "2017-1-2"]
				var arytime0=[];
				var arytime1=[];
				var arytime2=[];
				for(var i=0;i<arr.length;i++){
					var state=arr[i]["status"];
					if(state==0){
						arytime0.push(arr[i]["signDate"])
					}else if(state==1)
					{
						arytime1.push(arr[i]["signDate"])
					}else{
						arytime2.push(arr[i]["signDate"])
					}
				}
				gooverPK(arytime0,0);
				gooverPK(arytime1,1);
				gooverPK(arytime2,2);
				var returnArytime=[];
				function gooverPK(arytime,one){
					for (var i = 0; i < arytime.length; i++) {
						var list = arytime[i].split("-");
						if ($(".showdate").attr("year") == list[0] && $(".showdate").attr("month") == list[1]) {
							for (var k = 0; k < $(".sel_cal span").length; k++) {
								if ($($(".sel_cal span")[k]).html() == list[2]) {
									if(one==0){
										$($(".sel_cal span")[k]).addClass("gra");
										$($(".sel_cal span")[k]).attr("flagday", "true");
									}else if(one==1){
										$($(".sel_cal span")[k]).addClass("yello");
										$($(".sel_cal span")[k]).attr("flagday", "true");
									}else{
										$($(".sel_cal span")[k]).addClass("orange");
										$($(".sel_cal span")[k]).attr("flagday", "true");
									}	
								}
							}
						}
					}
				};
				
				
				
				
//				gooverPK(arytime);
				$(".prev_month").click(function() {
					gooverPK(arytime0,0);
					gooverPK(arytime1,1);
					gooverPK(arytime2,2);
					
				});
				$(".next_month").click(function() {
					gooverPK(arytime0,0);
					gooverPK(arytime1,1);
					gooverPK(arytime2,2);
					
				});
				
			});
			
			function  showDate(){
				
			}
			
			
			
			$(function() {
				/* document.getElementById("rulebtn").addEventListener("tap", function() {
					$(".rulePopup").show();
				});
				document.getElementById("ruleok").addEventListener("tap", function() {
					$(".rulePopup").hide();
				}); */
				
					$("#rulebtn").click(function(){
						$(".rulePopup").show();
						return false;
					});
					$("#ruleok").click(function(){
						$(".rulePopup").hide();
						return false;
					});
				
				//参与up/我要up
				document.getElementById("upbutn").addEventListener("tap", function() {
					if(signStatus == 0){//未签到
						//判断当前时间是否在签到时间范围内
						var res = isInScpoe(timeScpoe);
						if(res == "MIDDLE" || res == "AFTER"){
							//去签到
							$.ajax({
								url: "${base}/wap/home/signIn.html",
								type: "POST",
								dataType: "json",
								cache: false,
								success: function(data) {
									var msg = data.message ;
									if(msg == "NOATTEND"){
										alert("亲，你没有参加今日签到") ;
									}
									if(msg == "SIGNINED"){
										alert("亲，你今日已经签到过了") ;
									}
									if(msg == "NOTIME"){
										alert("没有系统签到时间") ;
									}
									if(msg == "YES"){
										alert("亲，签到成功") ;
										/* signStatus = 1;
										$("#upbutn").text("参与UP");
										$("#days").text(data.data.days+"天");
										$("#balance").text(data.data.balance); */
										window.location.reload(true) ;
									}
									if(msg == "BEFORE"){
										alert("亲，还没有到签到时间") ;
									}
									if(msg == "AFTER"){
										alert("亲，签到时间已过") ;
										window.location.reload(true) ;
									}
								},
								error: function() {
									document.write("非法操作");
								}
							});
						};
						if(res == "BEFORE"){
							alert("亲，还没有到签到时间") ;
						}
					}else{//未参与或者已签到
						window.location.href = "${base}wap/home/attend.html" ;
					}
				});
				
			});
			$(function() {
				var winW = document.documentElement.clientWidth;
				var fontSize = 100;
				var dew = 375;
				var rem = dew / fontSize;
				document.documentElement.style.fontSize = winW / rem + "px";
			});
			
			//判断当前时间是否在签到时间范围内
			function isInScpoe(scope){
				var currTime = new Date();
			
				//获取签到的开始时间和结束时间
				var timeArr = scope.split("-");
				var beginStr = currDate + " " + timeArr[0];
				var beginTime =  new Date(beginStr.replace(/-/ig,'/'));
				
				var endStr = currDate + " " + timeArr[1];
				var endTime =  new Date(endStr.replace(/-/ig,'/'));
				
				if(beginTime<=currTime&&currTime<=endTime){
					return "MIDDLE";
				}
				if(currTime<beginTime){
					return "BEFORE";
				}
				if(currTime>endTime){
					return "AFTER";
				}
			};
		</script>
	</body>

</html>
