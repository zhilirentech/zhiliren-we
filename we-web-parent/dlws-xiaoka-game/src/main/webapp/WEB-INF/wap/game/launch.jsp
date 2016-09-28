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
		<link rel="stylesheet" type="text/css" href="${base}up/css/app.css" />
		<link rel="stylesheet" type="text/css" href="${base}up/css/mui.picker.min.css" />
		<link rel="stylesheet" type="text/css" href="${base}up/css/startPK.css" />
		<link rel="stylesheet" href="${base}up/css/PK.css" />
		<script src="${base}up/js/mui.min.js"></script>
		<script src="${base}up/js/mui.pullToRefresh.js"></script>
		<script src="${base}up/js/mui.pullToRefresh.material.js"></script>
		<script src="${base}up/js/mui.picker.min.js"></script>
		<script src="http://cdn.bootcss.com/jquery/3.1.0/jquery.js"></script>
		<title>发起PK</title>
	</head>
	<style>
		select{
			widows: 100%;
			height: 30px;
			border: 1px solid gainsboro!important;
			padding: 0;
			margin: 0;
			line-height: 22px;
			font-size: 10px;
			
		}
		.mui-btn-block{
			height: 30px;
		    border: 1px solid gainsboro!important;
		    padding: 0;
		    margin: 0;
		    line-height: 22px;
		    font-size: 10px;
		    color: darkgrey;
		}
		.PKtime .addfirst{
			font-size: 0.16rem;
            color: #d94336;
		}
		.PKtime .addfirst span:last-child{
			margin-left: 15px;
			color: black;
		}
		
	</style>

	<body>
	<!-- 	<div class="header">
			<span></span> 找人UP
			<span></span>
		</div> -->
		
		<div class="main mt20">
			<div class="main1"><img src="${base}up/img/up.png" /></div>
			<div class="main2">
				<p class="pt20">每天早上UP一下</p>
				<p class="sizeShort">Good good start Day day up</p>
			</div>
			<div class="findPeopleRuleTips">
				<div class="findPeopleRule">
					<p>找人UP规则介绍</p>
				</div>
				<div class="ruleRecommend">
					<p class="title">发起PK，第二天在约定的时间内签到者获胜</p>
					<p class="summmary">玩法介绍：${obj.bulletin}</p>
					<p class="findPeopleRuleBtn">GET</p>
				</div>
			</div>
			<div class="PKtimeAndcommentTips">
				<div class="PKtime">
					<p class="addfirst">
						<span>余&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额</span>
						<span>${obj.balance}</span>
					</p>
					<p class="first">
						<span>PK金额</span>
						<input type="text" id="money" placeholder="金额不小于1元" />
					</p>

					<div class="two">
						<span>PK时间</span>
						<span class="left">
							<button id='demo5' data-options='{"type":"time"}' class="btn mui-btn mui-btn-block">开始时间</button>
						</span>
						<span class="cen">——</span>
						<span class="right">
						<button id='demo6' data-options='{"type":"time"}' class="btn mui-btn mui-btn-block">结束时间</button>
					</div>

				</div>
				<div class="comment">
					<p>注：PK日期默认为第二天早上，参与PK的 人数不限，但如果没有好友接受邀约，则PK 发起失败，此金额会24小时内退还。
					</p>
					<a href="#">
						<p class="commentBtn">确认充值</p>
					</a>
				</div>
			</div>

			<script type="text/javascript">
			var balance = ${obj.balance} ;
			$(function() {
				mui.init();
				var result = mui('#demo5')[0];
				var btns = mui('.btn');
				btns.each(function(i, btn) {
					btn.addEventListener('tap', function() {
						var temp=$(this)[0] ;
						var optionsJson = this.getAttribute('data-options') || '{}';
						var options = JSON.parse(optionsJson);
						var id = this.getAttribute('id');
						var picker = new mui.DtPicker(options);
						picker.show(function(rs) {
							$(temp).text(rs.text);
							picker.dispose();
						});
					}, false);
				});
				
				$(".commentBtn").click(function(){
					var mStr = $("#money").val();
					if(mStr.trim() == ""){
						alert("请输入PK金额") ;
						return ;
					}
					var money = parseFloat(mStr);
					if(money < 1){
						alert("PK金额必须大于1元") ;
						return ;
					}
					if(money > balance){
						alert("余额不足，请到个人中心给账户充值") ;
						return ;
					}
					var t1=$("#demo5").text();
					if(t1 == "开始时间"){
						alert("请选择开始时间");
						return ;
					}
					var t2=$("#demo6").text();
					if(t2 == "结束时间"){
						alert("请选择结束时间");
					}
					if(t1>=t2){
						alert("开始时间应小于结束时间");
						return ;
					}else{
						sendAjax(money,t1,t2);
					}
				}) ;
			});
				$(function() {
					//	$(".ruleRecommend").toggle();
					mui(".findPeopleRule").on("tap", "p", function() {
						$(".ruleRecommend").slideToggle();
					}) ;
					mui(".ruleRecommend").on("tap", ".findPeopleRuleBtn", function() {
						$(".ruleRecommend").slideToggle();
					}) ;
					
				}) ;
				$(function() {
					var winW = document.documentElement.clientWidth;
					var fontSize = 100;
					var dew = 375;
					var rem = dew / fontSize;
					document.documentElement.style.fontSize = winW / rem + "px" ;
				}) ;
				function sendAjax(money,beginTime,endTime){
					//发送请求
					$.ajax({
						url: "${base}/wap/game/add.html",
						type: "POST",
						dataType: "json",
						cache: false,
						data:{
							money : money,
							beginTimeStr : beginTime,
							endTimeStr : endTime
						},
						success: function(res) {
							var msg = res.message ;
							if(msg == "OK"){
								alert("参与成功") ;
								window.location.href = "${base}wap/game/show.html?id="+res.data;
							}
							if(msg == "NOMONEY"){
								alert("参与失败，余额不足");
							}
							if(msg == "REPEAT"){
								alert("已经投注过了");
							}
						}	
					});
				}
			</script>
	</body>

</html>
