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
    <link rel="stylesheet" type="text/css" href="${base}up/css/mui.min.css"/>
     <link rel="stylesheet" type="text/css" href="${base}up/css/startPK.css"/>
     <link rel="stylesheet" href="${base}up/css/PK.css" />
    <script src="${base}up/js/mui.min.js"></script>
    <script src="${base}up/js/mui.pullToRefresh.js"></script>
    <script src="${base}up/js/mui.pullToRefresh.material.js"></script>
   	<script src="http://cdn.bootcss.com/jquery/3.1.0/jquery.js"></script>
    <title>PK邀请</title>
</head>
<style>

</style>
<body>
	<!-- <div class="header">
		<span></span>
		来自好友的PK邀约
		<span></span>
	</div> -->
	<c:if test="${obj.isCanAtt}">
		<div class="comeFromPkMain">
			<p>投${obj.game.money}元红包，约你明天早起打开！</p>
			<p class="bgimg"><img src="${base}up/img/comfrom.png" alt="" /></p>
			<p class="money">当前奖金池的总金额<span>${obj.totalMoney}元</span></p>
			<p class="btn" onclick="detail()">查看详情</p>
		</div>
	</c:if>
	<c:if test="${!obj.isCanAtt}">
		<div class="comeFromPkMain">
			<p>该UP游戏失败，请参加其他UP游戏</p>
		</div>
	</c:if>
	
	
<script type="text/javascript">

    $(function () {
        var winW=document.documentElement.clientWidth;
        var fontSize=100;
        var dew=375;
        var rem=dew/fontSize;
        //    if(winW>dew){
        //        winW = dew;
        //        alert(winW);
        //    }
        document.documentElement.style.fontSize=winW/rem+"px" ;
    }) ;
    function detail(){
    	window.location.href = "${base}wap/game/toAttend?id=${obj.game.id}" ;
    }
    
</script>
</body>
</html>
