<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta content="width=device-width, minimum-scale=1,initial-scale=1, maximum-scale=1, user-scalable=1" id="viewport" name="viewport" />
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
    <link rel="stylesheet" type="text/css" href="css/mui.min.css"/>
     <link rel="stylesheet" type="text/css" href="css/startPK.css"/>
     <link rel="stylesheet" href="css/PK.css" />
     <link rel="stylesheet" href="css/pkcenter.css" />
    <script src="js/mui.min.js"></script>
    <script src="js/mui.pullToRefresh.js"></script>
    <script src="js/mui.pullToRefresh.material.js"></script>
   	<script src="http://cdn.bootcss.com/jquery/3.1.0/jquery.js"></script>
    <title>发起pk3-5</title>
</head>
<style>

</style>
<body> 
	<div class="header">
		<span></span>
		好友PK中
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
	<div class="pkCentreMain">
		<div class="center">
			<img class="pkimg" src="img/pkcenter.png" alt="" />
		<div class="centerone togethercenter w60">
			<img src="img/personHead.png"/>
			<!--<p>庞欢key</p>
			<p>中央财经大学</p>-->
		</div>
		<!--<div class="centertwo togethercenter w40">
			<img src="img/personHead.png" class="ml10"/>
		<!--	<p>庞欢key</p>
			<p>中央财经大学</p>-- >
		</div>-->
		<div class="centerthree togethercenter w60">
			<div class="personradius"><img src="img/person.png"/></div>
		<!--<p>庞欢key</p>
			<p>中央财经大学</p>-->
		</div>
		<div class="centerfour togethercenter w60">
			<div class="personradius"><img src="img/person.png"/></div>
		<!--<p>庞欢key</p>
			<p>中央财经大学</p>-->
		</div>
		<div class="centerfive togethercenter w60">
			<div class="personradius"><img src="img/person.png"/></div>
			<!--<p>庞欢key</p>
			<p>中央财经大学</p>-->
		</div>
		<div class="centertwo togethercenter w60">
			<div class="personradius"><img src="img/person.png"/></div>
			<!--<p>庞欢key</p>
			<p>中央财经大学</p>-->
		</div>
		<div class="centersix togethercenter w40">
			<div class="personradius1"><img src="img/person.png"/></div>
		<!--<p>庞欢key</p>
			<p>中央财经大学</p>-->
		</div>
		<div class="centerseven togethercenter w40">
			<div class="personradius1"><img src="img/person.png"/></div>
		<!--<p>庞欢key</p>
			<p>中央财经大学</p>-->
		</div>
		<div class="centereight togethercenter w40">
			<div class="personradius1"><img src="img/person.png"/></div>
		<!--<p>庞欢key</p>
			<p>中央财经大学</p>-->
		</div>
		<div class="centernine togethercenter w40">
			<div class="personradius1"><img src="img/person.png"/></div>
		<!--<p>庞欢key</p>
			<p>中央财经大学</p>-->
		</div>
		<div class="centerten togethercenter w40">
			<div class="personradius1"><img src="img/person.png"/></div>
		<!--<p>庞欢key</p>
			<p>中央财经大学</p>-->
		</div>
		</div>
		
	</div>
	<div class="allpersonPktime">请在明天xxx准时签到</div>
	<div class="characterBg"></div>
<script type="text/javascript">

    $(function () {
        var winW=document.documentElement.clientWidth;
        console.log(winW)
        var fontSize=100;
        var dew=375;
        var rem=dew/fontSize;
        //    if(winW>dew){
        //        winW = dew;
        //        alert(winW);
        //    }
        document.documentElement.style.fontSize=winW/rem+"px"
    })
</script>
</body>
</html>
