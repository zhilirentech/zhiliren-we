<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="url" value="${base}/wap"/>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>消息详情</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<!--标准mui.css-->
		<link rel="stylesheet" href="<%=basePath%>up/css/mui.min.css">
		<link rel="stylesheet" href="<%=basePath%>up/css/list.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="<%=basePath%>up/css/app.css"/>
		<style>
			.title {
				margin: 20px 15px 10px;
				color: #6d6d72;
				font-size: 15px;
			}
		</style>
	</head>

	<body>
	
		<%@include file="/WEB-INF/wap/common/nav.jsp"%><!-- 底部导航 -->
		<div class="listDetails">
			<div class="one">
				<p>${obj.sitMessage.title}</p>
				<p><fmt:formatDate value="${obj.sitMessage.createTime}" pattern="yyyy-MM-dd HH:mm"/></p>
			</div>
			<div class="two">
			${obj.sitMessage.content}
			</div>
		</div>
	</body>
	<script src="<%=basePath%>up/js/mui.min.js"></script>
	<script>
		mui.init({
			swipeBack:true //启用右滑关闭功能
		});
	</script>
</html>