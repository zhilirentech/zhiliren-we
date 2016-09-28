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
    <link rel="stylesheet" type="text/css" href="<%=basePath%>up/css/mui.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>up/css/self.css"/>
    <link rel="stylesheet" href="<%=basePath%>up/css/index.css" />
    <link rel="stylesheet" href="<%=basePath%>up/css/Ranking.css" />
    <link rel="stylesheet" href="<%=basePath%>up/css/backadd.css"/>
    <script src="<%=basePath%>up/js/mui.min.js"></script>
    <script src="<%=basePath%>up/js/mui.pullToRefresh.js"></script>
    <script src="<%=basePath%>up/js/mui.pullToRefresh.material.js"></script>
    <script src="<%=basePath%>up/js/template.js"></script>
    <script src="<%=basePath%>up/js/jquery-1.6.2.min.js"></script>
    <title>排行榜</title>
</head>
<style>
	.cblack{
	color:black;
	}
</style>
<script type="text/javascript">
var pageNumber = 1;
var pageNumber1 = 1;
	$(function() {
				document.getElementById("allrankingbottom").addEventListener("tap", function() {
					pageNumber++;
					addAll();
				});
					document.getElementById("schollrankingbottom").addEventListener("tap", function() {
					pageNumber1++;
					addschool();
				});

				function addAll() {
					var pageCount = ${obj.pager.pageCount};
					$.ajax({
						url: "${url}/rankingAjax.html",
						type: "GET",
						dataType: "json",
						cache: false,
						 data:{
							 pageNumber: pageNumber
						}, 
						success: function(data) {
							var HisActiv=data.list;
							var dataPage=data.pager.pageNumber;
				        	var obj = new Object();
				        	obj.data=HisActiv;
				        	obj.dataPager=dataPage; 
				        	var html = template('allCountry',obj);
							$(html).appendTo($(".allList"));
							if(pageNumber>=pageCount){
							$("#allrankingbottom").css("background","none");
							$("#allrankingbottom").css("height","0");
							/* $("#allrankingbottom").remove(); */
							} 
							var oneLengh = $(".allList .one").length;
							var nuberLengh = $(".allList .nuber").length;
							 for(var j=0;j<nuberLengh;j++){
							  $(".allList .nuber").eq(j).html(4+j);
							} 
						},
						error: function() {
							document.write("非法操作"); 
						}
					});
	}


				function addschool() {
					var pageCount = ${obj.pager.pageCount};
					$.ajax({
						url: "${url}/SchoolAjax.html",
						type: "GET",
						dataType: "json",
						cache: false,
						 data:{
							 pageNumber: pageNumber1
						},
						success: function(data) {
							var pageCount = data.pager.pageCount;
							var HisActiv=data.list;
							var dataPage=data.pager.pageNumber;
				        	var obj = new Object();
				        	obj.data=HisActiv; 
				        	obj.dataPager=dataPage; 
				        	var html = template('allschool',obj);
							$(html).appendTo($(".SchoollList"));
							 if(pageNumber1>=pageCount){
							  $("#schollrankingbottom").css("background","none");
							  $("#schollrankingbottom").css("height","0");
							  /* $("#schollrankingbottom").remove(); */
							 } 
							var oneLengh = $(".SchoollList .one").length;
							var nuberLengh = $(".SchoollList .nuber").length;
							for(var j=0;j<nuberLengh;j++){
							  $(".SchoollList .nuber").eq(j).html(4+j);
							} 
						},
						error: function() {
							document.write("非法操作"); 
						}
					});
				
			}
				addAll();
				addschool();
})
</script>
<script id='allCountry' type="text/html">
	{{each data as value i}}
			<div class="one">
			{{if i==0&&dataPager==1}}
				<img class="medalnumberone" src="<%=basePath%>up/img/ranking1.png"/>
			{{else if i==1&&dataPager==1}}
				<img class="medalnumberone" src="<%=basePath%>up/img/ranking2.png"/>
			{{else if i==2&&dataPager==1}}
				<img class="medalnumberone" src="<%=basePath%>up/img/ranking3.png"/>
			{{else}}
			<div class="medalnumberone nuber">
				{{i+1}}
			</div>
			{{/if}}

			<div class="medalnumbertwo">
					<img class="medalnumbertwo" src={{value.headimgurl}} alt="" />
			</div>

			<div class="nameThree">
				<p>{{value.wxname}}</p>
				<p>{{value.schoolname}}</p>
			</div>

			<div class="dayFour">
				<div class="nameThree">
					<p>连续签</p>
					<p>到天数</p>
				</div>
			{{if i>=3}}
				<div class="dayFourtips cblack">
					{{value.signdays}} <span>天</span>
				</div>
			{{/if}}
			{{if i<3 &&dataPager==1}}
				<div class="dayFourtips ">
					{{value.signdays}} <span>天</span>
				</div>
			{{/if}}
			{{if i<3 &&dataPager!=1}}
				<div class="dayFourtips cblack">
					{{value.signdays}} <span>天</span>
				</div>
			{{/if}}
			</div>
			</div>
    {{/each}}
</script>

<script id='allschool' type="text/html">
	{{each data as value i}}
			<div class="one">
			{{if i==0&&dataPager==1}}
				<img class="medalnumberone" src="<%=basePath%>up/img/ranking1.png"/>
			{{else if i==1&&dataPager==1}}
				<img class="medalnumberone" src="<%=basePath%>up/img/ranking2.png"/>
			{{else if i==2&&dataPager==1}}
				<img class="medalnumberone" src="<%=basePath%>up/img/ranking3.png"/>
			{{else}}
			<div class="medalnumberone nuber">
				{{i+1}}
			</div>
			{{/if}}
			<div class="medalnumbertwo">
					<img class="medalnumbertwo" src={{value.headimgurl}} alt="" />
			</div>
			<div class="nameThree">
				<p>{{value.wxname}}</p>
				<p>{{value.schoolname}}</p>
			</div>
			<div class="dayFour">
				<div class="dayFourtips">
					今日签到: <span>{{value.signtime}}</span>
				</div>
			</div>
		</div>	
	{{/each}}
</script>

<body>
	<!-- <div class="header">
		<span></span>
		
		<span></span>
	</div> -->
	<%@include file="/WEB-INF/wap/common/nav.jsp"%><!-- 底部导航 -->
	<div class="rankingMain">
		<img src=${obj.banerList[0].imgUrl } alt="" />
		<div class="one">
			<p>全国共有${obj.dtoLst[0].count}人一起UP</p>
		</div>
		<div class="twobtn">
			<span class="btnall" flag="all">全国</span>
			<span class="btnschool">本校</span>
		</div>
	</div>
	<div class="allList">
	
	
	</div>
	<div class="SchoollList">
	
	</div>
	<p class="rankingbottom " id="allrankingbottom"></p>
	<p class="rankingbottom hide" id="schollrankingbottom"></p>
	<script type="text/javascript">	
	
	$(function(){
		/* mui(".twobtn").on("tap","span",function(){ */
			$(".twobtn span").click(function(){
				 console.log($(this).attr("flag")); 
				$(".twobtn span").css({"background":"white","color":"#d94336"});
				$(this).css({"background":"#d94336","color":"white"});
				if($(this).attr("flag")=="all"){
					$(".allList").show();
					$(".SchoollList").hide();
					$("#allrankingbottom").show();
					$("#schollrankingbottom").hide();
				}else{
					$(".allList").hide();
					$(".SchoollList").show();
					$("#allrankingbottom").hide();
					$("#schollrankingbottom").show();
				}
			});
	/*	document.getElementById("allrankingbottom").addEventListener("tap",function(){
			$("	<div class='one'><div class='medalnumberone'>4</div>"+
	            "<img class='medalnumbertwo' src='img/personHead.png' alt='' /><div class='nameThree'>"+
				 "<p>庞欢key</p><p>山西财经大学</p></div><div class='dayFour'><div class='nameThree'>"+
					 "<p>连续签</p><p>到天数</p></div><div class='dayFourtips'>99 <span>天</span></div></div></div>").appendTo($(".allList"))
		})
		document.getElementById("schollrankingbottom").addEventListener("tap",function(){
			$("	<div class='one'><div class='medalnumberone'>4</div>"+
	            "<img class='medalnumbertwo' src='img/personHead.png' alt='' /><div class='nameThree'>"+
				 "<p>庞欢key</p><p>山西财经大学</p></div><div class='dayFour'>"+
					 "<div class='dayFourtips'>今日签到: <span>5:30</span></div></div></div>").appendTo($(".SchoollList"))
		})*/
	});
    $(function () {
        var winW=document.documentElement.clientWidth;
        console.log(winW);
        var fontSize=100;
        var dew=375;
        var rem=dew/fontSize;
        //    if(winW>dew){
        //        winW = dew;
        //        alert(winW);
        //    }
        document.documentElement.style.fontSize=winW/rem+"px";
    });
</script>
</body>
</html>
