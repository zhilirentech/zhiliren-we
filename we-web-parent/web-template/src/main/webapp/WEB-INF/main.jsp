
<%@include file="/WEB-INF/common/tld.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/500.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta http-equiv="Access-Control-Allow-Origin" content="*" />
<meta name="alexaVerifyID" content="" />
<title>后台管理</title>

<%--加载默认主题样式 --%>
<link href="${static_path}/rc/common/themes/css/base.css" rel="stylesheet" type="text/css" />
<link href="${static_path}/rc/common/themes/css/common.css" rel="stylesheet" type="text/css" />
<link href="${static_path}/rc/common/themes/default/style.css" rel="stylesheet" type="text/css" id="themeStyle" />

<%-- 加载日志、文件加载器、seajs、配置--%>
<script type="text/javascript" src="${static_path}/common/js/stacktrace.js"></script>
<script type="text/javascript" src="${static_path}/common/js/wefileloader.js"></script>
<script type="text/javascript" src="${static_path}/common/js/seajs/seajs/2.2.0/sea.js"></script>
<script type="text/javascript" src="${static_path}/common/js/config.js"></script>
<script type="text/javascript" src="${static_path}/rc/common/js/config.js"></script>
</head>
<body>
<!-- header start -->
<div id="header">
	<h1 class="logo"><a href="#" title="后台管理" target="_blank"><img src="${base}/common/images/logo.jpg" width="200" height="60" alt="XIAOKA" /></a></h1>
	<div class="head">
		<div class="head_bg_left"></div>
		<div class="head_userinfo">你好 <b uid="${loginuser.id}"><font color="red">${loginuser.username}</font></b>，欢迎登录管理平台！</div>
		<a class="head_quit" href="${base}/admin/logout.html" 0title="退出">退出</a>
		<div class="head_search">
			<%-- <label><input type="text" name="search" class="head_searchkey" placeholder="输入关键字"/></label>
			<button class="head_searchbtn">搜索</button> --%>
		</div>
	</div>
</div>
<!-- header end -->

<!-- sidebar start -->
<div id="sidebar">
  <ul>
  	<li><a href="${base }/html/home.html" target="navTab" icon="home">首页</a></li>
  	<c:forEach items="${menus}" var="menu" >
	  	<c:if test="${menu.level == 1 }">
	  		<li>
	  			 <c:choose>
				   <c:when test="${menu.url != null && '' != menu.url}">
				   		<a  icon="table" href="${base}${menu.url}" target="navTab" rel="function${menu.id}">${menu.name}</a> 
				   </c:when>
				   <c:otherwise>
				   		<a  icon="table">${menu.name}</a> 
				   </c:otherwise>
			    </c:choose>
				<c:forEach var="function" items="${functionMap[menu.id]}" varStatus="stat">
					<ul><li><a icon="table" href="${base}${function.url}" target="navTab" rel="function${function.id}">${function.name}</a></li></ul>
				</c:forEach>
			</li>
	  	</c:if>
  	</c:forEach>
  </ul>
</div>
<!-- sidebar end -->

<!-- container start -->
<div id="container"></div>

<!-- footer start -->
<div id="footer">Copyright &copy; 2014 有限公司. 版权所有. Ver:${version}</div>
<!-- footer end -->
</body>
</html>
