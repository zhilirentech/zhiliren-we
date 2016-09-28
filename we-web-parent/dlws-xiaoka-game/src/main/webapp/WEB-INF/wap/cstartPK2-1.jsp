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
		<title>发起pk2-1</title>
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
	</style>

	<body>
		<div class="header">
			<span></span> 找人UP
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
					<p>找人UP规则介绍</p>
				</div>
				<div class="ruleRecommend">
					<p class="title">发起PK，第二天在约定的时间内签到者获胜</p>
					<p class="summmary">玩法介绍：发起人选择PK时间与金额确认 后邀请他人进行PK，接受方须于倒计时结 束前接受PK并确认，若次日均按时进行签 到，则平局；否则按时签到的人得到自己的 契约金并平分未签到者的契约金。
					</p>
					<p class="findPeopleRuleBtn">GET</p>
				</div>
			</div>
			<div class="PKtimeAndcommentTips">
				<div class="PKtime">
					<p class="first">
						<span>PK金额</span>
						<input type="text" placeholder="金额不小于1元" />
					</p>

					<div class="two">
						<span>PK时间</span>
						<span class="left">
							<select name="" id="">
								<option value="">开始时间</option>
								<option value="">5:30</option>
								<option value="">6:00</option>
								<option value="">7:00</option>
								<option value="">8:00</option>
							</select>
						</span>
						<span class="cen">——</span>
						<span class="right">
							<select name="" id="">
								<option value="">结束时间</option>
								<option value="">5:30</option>
								<option value="">6:00</option>
								<option value="">7:00</option>
								<option value="">8:00</option>
							</select></span>
					</div>

				</div>
				<div class="comment">
					<p>注：PK日期默认为第二天早上，参与PK的 人数不限，但如果没有好友接受邀约，则PK 发起失败，此金额会24小时内退还。
					</p>
					<a href="cstartPK2-4.html">
						<p class="commentBtn">确认充值</p>
					</a>
				</div>
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