<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta content="width=device-width, minimum-scale=1,initial-scale=1, maximum-scale=1, user-scalable=1" id="viewport" name="viewport" />
		<meta content="yes" name="apple-mobile-web-app-capable" />
		<meta content="black" name="apple-mobile-web-app-status-bar-style" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>up/css/mui.min.css" />
		<script src="<%=basePath%>up/js/mui.min.js"></script>
		<script src="<%=basePath%>up/js/mui.pullToRefresh.js"></script>
		<script src="<%=basePath%>up/js/mui.pullToRefresh.material.js"></script>
		<script src="http://cdn.bootcss.com/jquery/3.1.0/jquery.js"></script>
		<title>UP新人注册页</title>
	</head>
	<script type="text/javascript">
	
			var jsBasePath="<%=basePath%>";
			
			function saveCustomerInfo(){
				var cusName = document.getElementById("customerName").value;
				cusName = cusName.replace(/\s+/g,"");  
				if(cusName == ""){
					alert("请输入姓名") ;
					return ;
				}
				if("" != cusName){
					
					window.location.href=jsBasePath+"wap/home/saveCustomerInfo.html?customerName="+cusName;	
				}
				
			}
	</script>
	<style>
		html {
			font-size: 100px;
		}
		
		.header {
			height: 44px;
			width: 100%;
			background: #d94336;
		}
		
		.main {
			width: 100%;
		}
		
		.main1 {
			width: 0.48rem;
			margin: 0 auto;
		}
		
		.main1 img {
			width: 0.48rem;
			height: 0.29rem;
		}
		
		p {
			text-align: center;
			margin-bottom: 0px;
			font-size: 18px;
			color: black;
		}
		
		.sizeShort {
			font-size: 12px;
		}
		
		.pt20 {
			padding-top: 20px;
		}
		
		.mt100 {
			margin-top: 100px;
		}
		
		input[type=text] {
			margin: 0px;
		}
		
		.main3 {
			width: 70%;
			margin: 10px auto;
		}
		
		.btn {
			width: 70%;
			height: 40px;
			color: white;
			text-align: center;
			font-size: 14px;
			line-height: 40px;
			margin: 10px auto;
			border-radius: 5px;
			background: #d94336;
		}
	</style>

	<body>
		<!-- <div class="header"></div> -->
		<div class="main mt100">
			<div class="main1"><img src="<%=basePath%>up/img/up.png" /></div>
			<div class="main2">
				<p class="pt20">每天进步一点点</p>
				<p class="sizeShort">Good good start Day day up</p>
				<p class="pt20">完善个人信息</p>
			</div>
			<div class="main3">
				<input type="text" id="customerName" placeholder="姓名" />
				<!-- <input type="text" placeholder="学校" /> -->
			</div>
		</div>
		<!-- <a href="#" onclick="saveCustomerInfo();"> -->
		<!-- <div class="btn"><label onclick="saveCustomerInfo();">开始UP</label></div> -->
		<div class="btn" onclick="saveCustomerInfo();"><label>开始UP</label></div>
		<!-- </a> -->
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