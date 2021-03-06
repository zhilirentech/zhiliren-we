<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta content="width=device-width, minimum-scale=1,initial-scale=1, maximum-scale=1, user-scalable=1" id="viewport" name="viewport" />
		<meta content="yes" name="apple-mobile-web-app-capable" />
		<meta content="black" name="apple-mobile-web-app-status-bar-style" />
		<link rel="stylesheet" type="text/css" href="css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="css/startPK.css" />
		<link rel="stylesheet" href="css/PK.css" />
		<script src="js/mui.min.js"></script>
		<script src="js/mui.pullToRefresh.js"></script>
		<script src="js/mui.pullToRefresh.material.js"></script>
		<script src="http://cdn.bootcss.com/jquery/3.1.0/jquery.js"></script>
		<title>来自好友的pk邀约3-3</title>
	</head>
	<style>

	</style>

	<body>
		<div class="header">
			<span></span> 来自好友的pk邀约
			<span></span>
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
		<div class="main mt20">
			<div class="main1"><img src="img/up.png" /></div>
			<div class="main2">
				<p class="pt20">每天早上UP一下</p>
				<p class="sizeShort">Good good start Day day up</p>
			</div>
			<div class="findPeopleRuleTips">
				<div class="findPeopleRule">
					<p>投X元红包，约你明天早起打开</p>
				</div>
				<div class="ruleRecommend">
					<p class="title">发起PK，第二天在约定的时间内签到者获胜</p>
					<p class="summmary">玩法介绍：发起人选择PK时间与金额确认 后邀请他人进行PK，接受方须于倒计时结 束前接受PK并确认，若次日均按时进行签 到，则平局；否则按时签到的人得到自己的 契约金并平分未签到者的契约金。
					</p>
					<p class="findPeopleRuleBtn">GET</p>
				</div>
			</div>
			<div style="display: none;" class="comeformruleRecommend">
				<p class="title">好友邀请链接以失效</p>
				<p class="findPeopleRuleBtn">查看详情</p>
			</div>
			<div class="showPicture clearfix">
				<div class="one fl mtTips">
					<p><img src="img/UPtag.png" alt="" /></p>
					<p>庞欢key</p>
					<p>陕西财经大学</p>
				</div>
				<p class="two fl mtTips"><img src="img/pk2.png" /></p>
				<div class="three fl">
					<div class="one fl">
						<p><img src="img/person.png" alt="" /></p>
						<p>庞欢key</p>
						<p class="mbtips">陕西财经大学</p>
					</div>
					<div class="one fl">
						<p><img src="img/person.png" alt="" /></p>
						<p>庞欢key</p>
						<p class="mbtips">陕西财经大学</p>
					</div>
					<div class="one fl mt10">
						<p><img src="img/person.png" alt="" /></p>
						<p>庞欢key</p>
						<p class="mbtips">陕西财经大学</p>
					</div>
					<div class="one fl mt10">
						<p><img src="img/person.png" alt="" /></p>
						<p>庞欢key</p>
						<p class="mbtips">陕西财经大学</p>
					</div>
				</div>
			</div>
			<div class="comment">
				<p class="one">接收PK倒计时<span>30:00</span></p>
				<p class="two">超时邀约链接将失效，无法加入PK</p>
				<p class="commentBtn">接收，谁怕谁！</p>
			</div>

			<script type="text/javascript">
				$(function() {
					//	$(".ruleRecommend").toggle();
					mui(".findPeopleRule").on("tap", "p", function() {
						$(".ruleRecommend").slideToggle();
					})
					mui(".ruleRecommend").on("tap", ".findPeopleRuleBtn", function() {
						//		alert(" ");
						$(".ruleRecommend").slideToggle();
						//		$(".comment").hide();
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