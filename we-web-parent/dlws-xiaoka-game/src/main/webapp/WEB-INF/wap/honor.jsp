<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="url" value="${base}/wap" />

 <form id="pagerForm" action="${url}/live.html" method="post">
	<input type="hidden" name="pageNumber" value="${obj.pager.pageNumber}" />
	<input type="hidden" name="pageSize" value="${obj.pager.pageSize}" />
	<input type="hidden" name="pageCount" value="${obj.pager.pageCount}" />
</form>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta content="width=device-width, minimum-scale=1,initial-scale=1, maximum-scale=1, user-scalable=1" id="viewport" name="viewport" />
		<meta content="yes" name="apple-mobile-web-app-capable" />
		<meta content="black" name="apple-mobile-web-app-status-bar-style" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>up/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>up/css/self.css" />
		<link rel="stylesheet" href="<%=basePath%>up/css/index.css" />
		<link rel="stylesheet" href="<%=basePath%>up/css/honor.css" />
		<link rel="stylesheet" href="<%=basePath%>up/css/backadd.css"/>
		<script src="<%=basePath%>up/js/mui.min.js"></script>
		<script src="<%=basePath%>up/js/mui.pullToRefresh.js"></script>
		<script src="<%=basePath%>up/js/mui.pullToRefresh.material.js"></script>
		<script src="<%=basePath%>up/js/jquery-1.6.2.min.js"></script>
		<script src="<%=basePath%>up/js/template.js"></script>
		<title>荣誉榜</title>
	</head>
	<style>

	</style>

	<body>
		<!-- <div class="header">
			<span></span> 荣誉
			<span></span>
		</div> -->
		<%-- <%@include file="/WEB-INF/wap/common/nav.jsp"%><!-- 底部导航 --> --%>
		<div class="honorbgheader">
			<img src="<%=basePath%>up/img/honorHeader.png" alt="" />
			<p>我的勋章</p>
		</div>
		<div class="myMain">
			<p class="one"><img src=${obj.dtoLst[0].headImgUrl } /></p>
			<p class="two">${obj.dtoLst[0].wxname}</p>
			<p class="three">${obj.dtoLst[0].schoolName}</p>
		</div>
		<div class="medalShow clearfix">
		<c:forEach var="map" items="${obj.list}" varStatus="status">
			<c:choose>
				 <c:when test="${obj.signDayList[0].signDays>=map.levelNo}">
					<p><img src=${map.brightIcon }></p>
				</c:when>
				<c:otherwise>
					<p><img src=${map.grayIcon } ></p>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		</div>
		<!-- <div class="medalShow clearfix mt10">
			<p><img src="img/honormedal4.png" /></p>
			<p><img src="img/honormedal5.png" /></p>
			<p><img src="img/honormedal6.png" /></p>
			<p><img src="img/honormedal4.png"/></p>
		</div> -->
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