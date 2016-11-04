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
		<title>提示关注3-2</title>
	</head>
	<style>

	</style>

	<body>
		<div class="header">
			<span></span> 来自好友的PK邀约
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
		<p class="bgpkup"></p>
		<div class="comeFromPkMain  wxcomeFromPkMain">
			<p class="wxone">温馨提示</p>
			<p class="wxtwo">由于微信限制，</p>
			<p class="wxthree">接受PK需先关注公众号。</p>
			<p class="wxbgimg"><img src="img/comefrom-wixin.png" alt="" /></p>

			<p>长按识别关注</p>
		</div>
		<script type="text/javascript">
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