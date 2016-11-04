<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%-- <c:set var="url" value="${base}/wap"/> --%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta content="width=device-width, minimum-scale=1,initial-scale=1, maximum-scale=1, user-scalable=1" id="viewport" name="viewport" />
		<meta content="yes" name="apple-mobile-web-app-capable" />
		<meta content="black" name="apple-mobile-web-app-status-bar-style" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>up/css/mui.min.css" />
		<link rel="stylesheet" href="<%=basePath%>up/css/activepage1.css" />
		<script src="<%=basePath%>up/js/mui.min.js"></script>
		<script src="<%=basePath%>up/js/mui.pullToRefresh.js"></script>
		<script src="<%=basePath%>up/js/mui.pullToRefresh.material.js"></script>
		<script src="http://cdn.bootcss.com/jquery/3.1.0/jquery.js"></script>
		<title>活动内容</title>
	</head>
	<style>
		.main_two{
			width: 80%;
			border: 1px solid gainsboro;
			margin: 30px auto 0px;
			text-align: center;
		}
	</style>

	<body>
		<%-- <div class="header">
			<span></span>${obj.HotHis}
			<span></span>
		</div> --%>
		<%@include file="/WEB-INF/wap/common/nav.jsp"%><!-- 底部导航 -->
		<!-- <div class="main_one">
			<img src=${obj.acdeital.cover} alt="" />
		</div> -->
		<div class="main_two">
				${obj.acdeital.content}
		</div>
		<%-- <script src="<%=basePath%>up/js/time.js"></script> --%>
		<script type="text/javascript">
			
			$(function() {
				var winW = document.documentElement.clientWidth;
				console.log(winW);
				var fontSize = 100;
				var dew = 375;
				var rem = dew / fontSize;
				//    if(winW>dew){
				//        winW = dew;
				//        alert(winW);
				//    }
				document.documentElement.style.fontSize = winW / rem + "px";
			})
		</script>
	</body>

</html>