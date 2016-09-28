<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta content="width=device-width, minimum-scale=1,initial-scale=1, maximum-scale=1, user-scalable=1" id="viewport" name="viewport" />
		<meta content="yes" name="apple-mobile-web-app-capable" />
		<meta content="black" name="apple-mobile-web-app-status-bar-style" />
		<link rel="stylesheet" type="text/css" href="css/mui.min.css" />
		<link rel="stylesheet" href="css/login.css" />
		<link rel="stylesheet" type="text/css" href="css/self.css" />
		<link rel="stylesheet" href="css/index.css" />
		<script src="js/mui.min.js"></script>
		<script src="js/mui.pullToRefresh.js"></script>
		<script src="js/mui.pullToRefresh.material.js"></script>
		<script src="http://cdn.bootcss.com/jquery/3.1.0/jquery.js"></script>
		<script src="js/time.js"></script>
		<title>登入1-3</title>
	</head>
	<style>
		
	</style>

	<body>
		<div class="header">
			<span></span> 首页
			<span></span>
		</div>
		<div class="main clearfix">
			<img style="max-width:100%" src="img/banner1.png" alt="" />
			<div class="personage clearfix">
				<p class="first"></p>
				<p class="two"></p>
			</div>
			<div class="signin">
				<p class="first">
					<span>签到天数</span><span>1天</span>
				</p>
				<div class="two">
					<p>签到为每天</p>
					<p>5:30-8:00</p>
				</div>
			</div>
			<div class="money">
				<span>奖金池总额</span>
				<span>999元</span>
			</div>
		</div>
		<div class="footer">
			<a href="login1-3.html">
				<p class="first">
				首页
				</p>
			</a>
			<a href="Ranking.html">
				<p class="two">排行榜</p>
			</a>
			<a href="my.html">
				<p class="three">我的</p>
			</a>
		</div>
		<div class="menu">
			<div class="submenu">
				<p class="one">已up</p>
				<a href="cstartPK2-1.html">
					<p class="two">找人up</p>
				</a>
				<p class="three " id="rulebtn">参与规则</p>
				<p class="four">余额</p>
			</div>
		</div>
		<div class="rulePopup">
			<p class="one">参与规则</p>
			<p class="three">玩法介绍：发起人选择PK时间与金额确认 后邀请他人进行PK，接受方须于倒计时结 束前接受PK并确认，若次日均按时进行签 到，则平局；否则按时签到的人得到自己的 契约金并平分未签到者的契约金。</p>
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
		
		
		$(function() {
				
				var arytime = ["2016-7-1", "2016-7-3", "2016-7-4", "2016-7-5", "2016-8-1", "2016-8-2", "2016-8-31", "2016-9-1", "2017-1-2"]
				var returnArytime=[];
				function gooverPK(arytime){
					for (var i = 0; i < arytime.length; i++) {
						var list = arytime[i].split("-");
						if ($(".showdate").attr("year") == list[0] && $(".showdate").attr("month") == list[1]) {
							for (var k = 0; k < $(".sel_cal span").length; k++) {
								if ($($(".sel_cal span")[k]).html() == list[2]) {
									$($(".sel_cal span")[k]).addClass("yello");
									$($(".sel_cal span")[k]).attr("flagday", "true");
								}
							}
						}
					}
				}
				gooverPK(arytime);
				$(".prev_month").click(function() {
					gooverPK(arytime);
					
				})
				$(".next_month").click(function() {
					gooverPK(arytime);
					
				})
				console.log(arytime);
				console.log(arytime.join());
				
			})

			
	
			$(function() {
				document.getElementById("rulebtn").addEventListener("tap", function() {
					$(".rulePopup").show();
				})
				document.getElementById("ruleok").addEventListener("tap", function() {
					$(".rulePopup").hide();
				})
			})
			$(function() {
				var winW = document.documentElement.clientWidth;
				console.log(winW)
				var fontSize = 100;
				var dew = 375;
				var rem = dew / fontSize;
				//    if(winW>dew){
				//        winW = dew;
				//        alert(winW);
				//    }
				document.documentElement.style.fontSize = winW / rem + "px"
			})
		</script>
	</body>

</html>