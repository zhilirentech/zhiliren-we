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
		<title>消息列表</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<!--标准mui.css-->
		<link rel="stylesheet" href="<%=basePath%>up/css/mui.min.css">
		<link rel="stylesheet" href="<%=basePath%>up/css/list.css">
		<script src="<%=basePath%>up/js/jquery-1.6.2.min.js"></script>
		<script src="<%=basePath%>up/js/template.js"></script>
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
		
	<script id="HisActiv" type="text/html">
	{{each data as value i}}
	<li class="mui-table-view-cell mui-media">
		<a href="${url}/mynewsdetail.html?messageId={{value.messageid}}">
		{{if value.readtime==null||value.readtime==''}}
		<div style="width:42px;height:42px;position:relative;float:left;margin-right:10px;">
		<img class="mui-media-object mui-pull-left" src="<%=basePath%>up/img/icon_message.png">
		<span class="mui-badge"></span>
		</div>
		{{else}}
		<div style="width:42px;height:42px;position:relative;float:left;margin-right:10px;">
		<img class="mui-media-object mui-pull-left" src="<%=basePath%>up/img/icon_message.png">
		</div>
		{{/if}}
		<div class="mui-media-body">
			<span>{{value.title}}</span>
			<p class='mui-ellipsis'>{{value.createtime}}</p>
		</div>
		</a>
	</li>
	{{/each}}
	</script>
	
		<%@include file="/WEB-INF/wap/common/nav.jsp"%><!-- 底部导航 -->
		<!-- <div class="header">
			<span></span>我的消息
			<span></span>
		</div> -->
		<div class="mui-content">
			<ul class="mui-table-view addList">
			<c:forEach items="${obj}" var="pro">
				<li class="mui-table-view-cell mui-media">
					<a href="${url}/mynewsdetail.html?messageId=${pro.messageid}">
					<c:choose>
						<c:when test="${pro.readTime==null||pro.readTime==''}">
							<div style="width:42px;height:42px;position:relative;float:left;margin-right:10px;">
								<img class="mui-media-object mui-pull-left" src="<%=basePath%>up/img/icon_message.png">
								<span class="mui-badge"></span>
							</div>
						</c:when>
						<c:otherwise>
							<div style="width:42px;height:42px;position:relative;float:left;margin-right:10px;">
								<img class="mui-media-object mui-pull-left" src="<%=basePath%>up/img/icon_message.png">
							</div>
						</c:otherwise>
					</c:choose>
						<div class="mui-media-body">
							<span>${pro.title}</span>
							<p class='mui-ellipsis'><fmt:formatDate value="${pro.createtime}" pattern="yyyy-MM-dd HH:mm"/></p>
						</div>
					</a>
				</li>
			</c:forEach>
			</ul>
		</div>
			<a class="nomore" style="font-size:14px;color:#666;height:80px;display:block;text-align:center;">加载更多...</a>
	</body>
	<script src="<%=basePath%>up/js/mui.min.js"></script>
	<script>
		mui.init({
			swipeBack:true //启用右滑关闭功能
		});
	</script>
	
	
	<script type="text/javascript">
		var pageNumber = 2;
		var pageCount = ${obj.pager.pageCount};
			$(function() {
				$(".nomore").click(function(){
					$.ajax({
						url: "${url}/mynewsAjax.html",
						type: "GET",
						dataType: "json",
						cache: false,
						data:{
							 pageNumber: pageNumber
						},
						success: function(data) {
							pageNumber++;
							var HisActiv=data.list;
				        	var obj = new Object();
				        	obj.data=HisActiv;
				        	var html = template('HisActiv',obj);
							$(html).appendTo($(".addList"));
							 if(pageNumber>data.pager.pageCount){
							$(".nomore").remove();
							$(".addList").after("<a class='more' style='font-size:14px;color:#666;height:80px;display:block;text-align:center;'>没有更多了...</a>");
							} 
						},
						error: function() {
							document.write("非法操作"); 
						}
					});
					
					
				})
			})
		</script>
</html>