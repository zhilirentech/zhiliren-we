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
		<link rel="stylesheet" type="text/css" href="${base}up/css/self.css" />
		<link rel="stylesheet" href="${base}up/css/index.css" />
		<link rel="stylesheet" href="${base}up/css/my.css" />
		<link rel="stylesheet" href="${base}up/css/backadd.css"/>
		<script src="${base}up/js/mui.min.js"></script>
		<script src="${base}up/js/mui.pullToRefresh.js"></script>
		<script src="${base}up/js/mui.pullToRefresh.material.js"></script>
		<script src="http://cdn.bootcss.com/jquery/3.1.0/jquery.js"></script>
		<title>参与签到</title>
	</head>
	<style>
		.mui-slider-indicator .mui-indicator {
			left: -38px;
		}
		.sel_cal{
			padding-bottom: 30px;
		}
		.timebtn{
			position: absolute;
			bottom: 5px;
			left: 10px;
			height: 20px;
			width: 50px;
			background: white;
			text-align: center;
			line-height: 20px;
			font-size: 12px;
			border-radius: 5px;
			
		}
		.green{
				background: orange;
				border-radius: 50%;
			}
		.yello{
				background: #d94336;
				border-radius: 50%;
			}
		.gra{
			background: gray;
			border-radius: 50%;
		}
		.sel_cal table{
			font-size: 12px;
		    width: 100%;
		    background: #eeeeef;
		    height: 1.4rem;
		    margin: 0px auto;
		}
		.orange1{
			background: orchid;
			border-radius: 50%;
		}
		.submitBtn{
			margin-top: 222px;
		}
		.inpmoney .one{
			text-align: center;
		    padding-bottom: 3px;
		    color: #8f8f94;
		}
		.mypayhint {
		          width: 80%;
		    padding-top: 10px;
		    padding-bottom: 10px;
		    font-size: 15px;
		    color: black;
		    text-align: center;
		    background: white;
		    margin-left: -40%;
		    border: 1px solid gainsboro;
		    margin-top: 90px;
		    margin-bottom: 100px;
		    position: absolute;
		    z-index: -1;
    	}
	</style>

	<body>
		<!-- <div class="header">
			<span></span> 参与
			<span></span>
		</div> -->
		<%@include file="/WEB-INF/wap/common/nav.jsp"%><!-- 底部导航 -->
		<div class="inpmoney clearfix">
			<div class="one">
				<p>账户余额:</p>
				 <input class="tipsOne" type="number" name="" id="" value="${obj.balance}" placeholder="￥充值金额1~100元" readonly="readonly"/> 
				
			</div>
			<div class="one">
				<p>参与金额:</p>
				<input  type="number" name="" id="deposit" value="" placeholder="参与金额应小于账户余额" />
			</div>
		</div>
		<div class="inpmoney clearfix inpmoney2">
			<div class="one">
				参与日期
				<!-- <input type="number" name="" id="" value=""  placeholder="请选择日期"/>
				<div class="selectDat">
					<p>请选择日期</p>
				</div> -->
			</div>
		</div> 
		<div class="sel_cal">
			<a class="fl prev_month"><</a>
			<p class="showdate fl"></p>
			<a class="fr next_month">></a>
			<!-- <botton class="timebtn">确定</botton> -->
		</div>
		<div class="submitBtn">
			<p>平均每日金额:<span id="avgMonmey">0</span>元</p>
			<p onclick="subdata()">提交</p>
		</div>
		<div class="mypayhint">注意：平均每天金额需要大于1元小于200元</div>
		<script src="${base}up/js/time1.js"></script>
		<script type="text/javascript">
			/*时间插件*/
			
			var signListJson = ${obj.signListJson};
			var returnArytime = [];
			var balance = ${obj.balance} ;
			/*时间插件*/
			$(function() {
				$(".sel_cal").css("display","block");
				function daysBetween(DateOne,DateTwo){   
					    var OneMonth = DateOne.substring(5,DateOne.lastIndexOf ('-'));  
					    var OneDay = DateOne.substring(DateOne.length,DateOne.lastIndexOf ('-')+1);  
					    var OneYear = DateOne.substring(0,DateOne.indexOf ('-'));  
					  
					    var TwoMonth = DateTwo.substring(5,DateTwo.lastIndexOf ('-'));  
					    var TwoDay = DateTwo.substring(DateTwo.length,DateTwo.lastIndexOf ('-')+1);  
					    var TwoYear = DateTwo.substring(0,DateTwo.indexOf ('-'));  
					  
					    var cha=((Date.parse(OneMonth+'/'+OneDay+'/'+OneYear)- Date.parse(TwoMonth+'/'+TwoDay+'/'+TwoYear))/86400000);   
					    return cha;
				} ;
				
		
				var arr = signListJson ;//0:未签到,1:已签到
				
				var arytime0=[];
				var arytime1=[];
				var arytime2=[];
				for(var i=0;i<arr.length;i++){
					var state=arr[i]["status"];
					
					
					if (state == 0) {
						arytime0.push(arr[i]["signDate"]);
					} else if(state == 1) {
						arytime1.push(arr[i]["signDate"]);
					}else{
						arytime2.push(arr[i]["signDate"]);
					}
				}
				gooverPK(arytime0,0);
				gooverPK(arytime1,1); 
				gooverPK(arytime2,2);
				function gooverPK(arytime,one){
					for (var i = 0; i < arytime.length; i++) {
						var list = arytime[i].split("-");
						if ($(".showdate").attr("year") == list[0] && $(".showdate").attr("month") == list[1]) {
							for (var k = 0; k < $(".sel_cal span").length; k++) {
								if ($($(".sel_cal span")[k]).html() == list[2]) {
								
								if (one == 0) {
									$($(".sel_cal span")[k]).addClass("gra");
									$($(".sel_cal span")[k]).attr("flagday", "true");
								} 
								if(one == 1){
									$($(".sel_cal span")[k]).addClass("yello");
									$($(".sel_cal span")[k]).attr("flagday", "true");
								}
								if(one==2){
									$($(".sel_cal span")[k]).addClass("orange1");
									$($(".sel_cal span")[k]).attr("flagday", "true");
								}
								}
							}
						}
					}
				} ;
				
				function leftAndRightClickGooverPK(arytime){
					for (var i = 0; i < arytime.length; i++) {
						var list = arytime[i].split("-");
						if ($(".showdate").attr("year") == list[0] && $(".showdate").attr("month") == list[1]) {
							for (var k = 0; k < $(".sel_cal span").length; k++) {
								if ($($(".sel_cal span")[k]).html() == list[2]) {
									$($(".sel_cal span")[k]).addClass("green");
//									$($(".sel_cal span")[k]).attr("flagday", "true");
								}
							}
						}
					}
				} ;
				function timebad(dt){
					var myDate=new Date() ;
					var myYear=myDate.getFullYear();  //获取当前年份(2位)
					var myMonth=myDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
					var myDay=myDate.getDate(); 
					var nowTime=myYear+"-"+myMonth+"-"+myDay;
					return daysBetween(nowTime,dt);
				} ;
				
				timebad("2016-8-19");
				function clickGooverPK(temp,arytime){
				if(arytime.length==0){
					var year=$(".showdate").attr("year");
					var month=$(".showdate").attr("month");
					var day=temp.html();
					var clickTime=year+"-"+month+"-"+day;
					if(timebad(clickTime)>=0){
						return;
					}
				}
				if(arytime.length>0){
						for(var i=0;i<arytime.length;i++){
						
						var year=$(".showdate").attr("year");
						var month=$(".showdate").attr("month");
						var day=temp.html();
						var clickTime=year+"-"+month+"-"+day;
		
						if(arytime[i].signDate==year+"-"+month+"-"+day){
							return;
						}
						if(timebad(clickTime)>=0){
							return;
						}
					}
				
				}
					
					if(temp.html()!="&nbsp;"){
						if(temp.attr("class")=="green"||temp.attr("class")=="today green"){
							temp.removeClass("green");
							var year=$(".showdate").attr("year");
							var month=$(".showdate").attr("month");
							var day=temp.html();
							for(var i=0;i<returnArytime.length;i++){
								if(returnArytime[i]==year+"-"+month+"-"+day){
									returnArytime.splice(i,1);
									$("#avgMonmey").text(avgMoneyFun()) ;
								}
							}
						}else{
							temp.addClass("green");
							var year=$(".showdate").attr("year");
							var month=$(".showdate").attr("month");
							var day=temp.html();
							returnArytime.push(year+"-"+month+"-"+day);	
							$("#avgMonmey").text(avgMoneyFun()) ;
						}
					}
				} ;
				//弹出日历控件
				/* $(".selectDat").click(function(){
					$(".sel_cal").slideToggle();
				}) ; */
				//gooverPK(arr);
				$(".prev_month").click(function() {
					gooverPK(arytime0,0);
					gooverPK(arytime1,1);
					gooverPK(arytime2,2);
					leftAndRightClickGooverPK(returnArytime);
				}) ;
				$(".next_month").click(function() {
					gooverPK(arytime0,0);
					gooverPK(arytime1,1);
					gooverPK(arytime2,2);
					leftAndRightClickGooverPK(returnArytime);
				}) ;
				
				
				mui(".sel_cal").on("tap","span",function(){
					var temp=$(this);
					clickGooverPK(temp,arr);
					console.log(returnArytime);
				}) ;
				$(".timebtn").click(function(){
					$(".sel_cal").slideToggle();
				}) ;
				//验证输入金额
				$("#deposit").blur(function(){
					var depositVal = $(this).val() ;
					valDeposit(parseFloat(depositVal)) ;
				});
				
			}) ;

			$(function() {
				var winW = document.documentElement.clientWidth;
				
				var fontSize = 100;
				var dew = 375;
				var rem = dew / fontSize;
				//    if(winW>dew){
				//        winW = dew;
				//        alert(winW);
				//    }
				document.documentElement.style.fontSize = winW / rem + "px" ;
			}) ;
			
			function avgMoneyFun (){
				var days = returnArytime.length ;
				if(days == 0) return 0;
				if($("#deposit").val() == "") return 0;
				var money = parseFloat($("#deposit").val()) ;
				var avgNum = money / days + "";
				if(avgNum.indexOf(".") == -1){
					return avgNum ;
				}
				return avgNum.substr(0,avgNum.indexOf(".")+3);
			}
			
			function valDeposit(money){
				if(money > balance){
					alert("余额不足，请到个人中心给账户充值") ;
					return false;
				}
				//平均金额大于1小于200
				var days = returnArytime.length ;
				var avgMoney = avgMoneyFun();
				if(avgMoney == 0){
					$("#avgMonmey").text(avgMoney) ;
					return false;
				}
				if(avgMoney < 1 || avgMoney > 200){
					alert("平均每天金额需要大于1元小于200元") ;
					return false;
				}else{
					$("#avgMonmey").text(avgMoney) ;
					return true;
				}
			} ;
			
			
			//提交表单
			function subdata(){
				if(returnArytime.length == 0){
					alert("请选择日期") ;
					return;
				}
				var depositVal = $("#deposit").val() ;
				if(depositVal == ""){
					alert("请输入参与金额") ;
					return;
				}
				if(!valDeposit(parseFloat(depositVal))) return;
				//发送请求
				$.ajax({
					url: "${base}/wap/home/attendSignIn.html",
					type: "POST",
					dataType: "json",
					cache: false,
					data:{
						totalMoney:depositVal,
						attendDays:returnArytime.toString()
					},
					success: function(res) {
						var msg = res.message ;
						if(msg == "OK"){
							alert("参与成功") ;
							window.location.reload(true);
						}
						if(msg == "NOMONEY"){
							alert("参与失败，余额不足");
						}
						if(msg == "NOTAFTER"){
							alert(res.data + "小与当前日期");
						}
					}	
				});
			} ;
			
		</script>
	</body>

</html>