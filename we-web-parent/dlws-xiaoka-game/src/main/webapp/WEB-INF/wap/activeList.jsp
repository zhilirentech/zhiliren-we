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
		<link rel="stylesheet" href="<%=basePath%>up/css/activepage1.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>up/css/self.css" />
		<link rel="stylesheet" href="<%=basePath%>up/css/index.css" />
		<link rel="stylesheet" href="<%=basePath%>up/css/backadd.css"/>
		<script src="<%=basePath%>up/js/mui.min.js"></script>
		<script src="<%=basePath%>up/js/mui.pullToRefresh.js"></script>
		<script src="<%=basePath%>up/js/mui.pullToRefresh.material.js"></script>
		<script src="<%=basePath%>up/js/jquery-1.6.2.min.js"></script>
		<script src="<%=basePath%>up/js/template.js"></script>
		<title>活动列表</title>
	</head>
	<style>
	.mui-slider-indicator .mui-indicator{
		margin: -1px 1px ;
	}
	.mui-slider-indicator .mui-indicator:last-child{
		margin-right: 10px;
	}
	.mui-slider .mui-slider-group .mui-slider-item{
	height:20%;
	}
	</style>

	<body>
	
	<script id="HisActiv" type="text/html">
	{{each data as value i}}
	<li class='mui-table-view-cell mui-media'>
	<a href="${url}/activeDetail.html?id={{data[i].id}}&HotHis=历史活动">
	<img class='mui-media-object mui-pull-right' src={{data[i].cover}}>
	<div class='mui-media-body'>
	<p class='mui-ellipsis'>{{data[i].title}}</p>
	</div>
	</a>
	</li>
	{{/each}}
	</script>
	
	<script id="mytest" type="text/html">
		<div class="mui-slider-item ">
			<a href="#">
				<img src={{list[list.length-1].imgUrl}}>
				
			</a>
		</div>
		{{each list as value i}}
		<div class="mui-slider-item">
			<a href="#">
				<img src={{value.imgUrl}}>
				
			</a>
		</div>
		{{/each}}
		<div class="mui-slider-item ">
			<a href="#">
				<img src={{list[0].imgUrl}}>
				
			</a>
		</div>
	</script>
	<script id="mytest1" type="text/html">
		{{each list as value i}} {{if i==0}}
		<div class="mui-indicator mui-active"></div>
		{{else}}
		<div class="mui-indicator"></div>
		{{/if}} {{/each}}

	</script>
	
	
		<!-- <div class="header">
			<span></span> 最新活动
			<span></span>
		</div> -->
		<%@include file="/WEB-INF/wap/common/nav.jsp"%><!-- 底部导航 -->
		<div class="rankingMain">
			<%-- <img src='${obj.banerList[0].imgUrl}' alt="" />--%>
			
			
			<div id="slider" class="mui-slider">
			<div class="mui-slider-group mui-slider-loop addban">

			</div>
			<div class="mui-slider-indicator mui-text-right tips banCircle">
			</div>
		</div>
		
		<div class="one">
				<p>热门活动</p>
			</div> 	
			
		</div>
		<ul class="mui-table-view">
		<c:forEach items="${obj.hotList}" var="pro" >
			<li class="mui-table-view-cell mui-media">
				<a href="${url}/activeDetail.html?id=${pro.id}&HotHis=热门活动">
					<img class="mui-media-object mui-pull-right" src="${pro.cover}">
					<div class="mui-media-body">
						<p class="mui-ellipsis">${pro.title}</p>
					</div>
				</a>
			</li>
		</c:forEach>
		</ul>
		</div>
		<div class="rankingMain1">
			<div class="one">
				<p>历史活动</p>
			</div>
		</div>
		<ul class="mui-table-view addhistory">
			<c:forEach items="${obj.list}" var="pro" >
				<li class="mui-table-view-cell mui-media">
					<a href="${url}/activeDetail.html?id=${pro.id}&HotHis=历史活动">
						<img class="mui-media-object mui-pull-right" src="${pro.cover}">
						<div class="mui-media-body">
							<p class="mui-ellipsis">${pro.title}</p>
						</div>
					</a>
				</li>
			</c:forEach>
		</ul>
		
	<div id="HisActivDiv">
   <!-- 历史活动的外部div -->
   </div>
   
		<p class="rankingbottom " id="allrankingbottom"></p>
		<%-- <script src="<%=basePath%>up/js/time.js"></script> --%>
		<script type="text/javascript">
		var pageNumber = 2;
		var pageCount = ${obj.pager.pageCount};
			$(function() {
				if(pageCount==1){
					$(".rankingbottom").css("background","none");
					$(".rankingbottom").css("height","0");
				}
				document.getElementById("allrankingbottom").addEventListener("tap", function() {
					$.ajax({
						url: "${url}/activeListAjax.html",
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
							$(html).appendTo($(".addhistory"));
							if(pageNumber>pageCount){
							$(".rankingbottom").css("background","none");
							$(".rankingbottom").css("height","0");
							}
						},
						error: function() {
							document.write("非法操作"); 
						}
					});
					
					
				});
				
				var dataa = "";
				var s = "<%=basePath%>";
				$.ajax({
					type : "post",
					url : s+"wap/ajax/center/ActiveBannerList.html",
					dataType : "json",
					async : false,
					success : function(d) {
						dataa = d;
					}
				});
				var html = template('mytest', dataa);
				$(".addban").append(html);
				/* $(".del").remove(); */
				var html1 = template("mytest1", dataa);
				$(".banCircle").append(html1);
			});
			/*  $(function() {
				document.getElementById("rulebtn").addEventListener("tap", function() {
					$(".rulePopup").show();
				})
				document.getElementById("ruleok").addEventListener("tap", function() {
					$(".rulePopup").hide();
				})
			})  */
			$(function() {
				/*自动轮播*/
				  var slider = mui("#slider");
				slider.slider({
					interval: 4000
				}); 
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
			});
		</script>
	</body>

</html>