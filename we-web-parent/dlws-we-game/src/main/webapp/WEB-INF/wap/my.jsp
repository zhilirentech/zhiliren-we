<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="format-detection" content="telephone=no" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta content="width=device-width, minimum-scale=1,initial-scale=1, maximum-scale=1, user-scalable=1" id="viewport" name="viewport" />
		<meta content="yes" name="apple-mobile-web-app-capable" />
		<meta content="black" name="apple-mobile-web-app-status-bar-style" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>up/css/mui.min.css" />
		<link rel="stylesheet" href="<%=basePath%>up/css/my.css" />
		<link rel="stylesheet" href="<%=basePath%>up/css/backadd.css"/>
		<script src="<%=basePath%>up/js/mui.min.js"></script>
		<script src="<%=basePath%>up/js/mui.pullToRefresh.js"></script>
		<script src="<%=basePath%>up/js/mui.pullToRefresh.material.js"></script>
		<script src="http://cdn.bootcss.com/jquery/3.1.0/jquery.js"></script>
		<title>个人中心</title>
	</head>
	<style>
	</style>
	<script src="<%=basePath%>up/js/template.js"></script>
	<body>
		<script type="text/javascript">
		var pageNumber = 0;
		var pageSize = 3;
			$(function() {
				document.getElementById("myadd").addEventListener("tap", function() {
					add();
				});
				function add() {
					var baseP ="<%=basePath%>";
					var data = "";
					$.ajax({
						type : "post",
						url : baseP+"wap/ajax/center/queryAccount.html",
						dataType : "json",
						data : {pageNumber:pageNumber,pageSize:pageSize},
						async : false,//异步
						success : function(d) {
							 data = {
								datetime: d.list
							 };
							 pageNumber = d.pager.pageNumber+1;
							 
						}
					});
					var html = template('mytest', data);
					$(".myList").append(html);
				};
				add();
			});
			function saveWithdraw(){
				alert(0);
			};
		</script>
		<script id="mytest" type="text/html">
			{{each datetime as value i}}
				<ul class="rowlist clearfix">
					{{if value.type==1}}
						<li>充值</li>
					{{/if}}
					{{if value.type==2}}
						<li>提现</li>
					{{/if}}
					{{if value.type==3}}
						<li>投入</li>
					{{/if}}
					{{if value.type==4}}
						<li>返现</li>
					{{/if}}
					{{if value.type==5}}
						<li>分成</li>
					{{/if}}
					{{if value.type==6}}
						<li>退款</li>
					{{/if}}
					<li>{{value.createtime}}</li>
					<li>{{value.money}}</li>
				</ul>
			{{/each}}
		</script>
		<!-- <div class="header">
			<span></span> 个人中心
			<span></span>
		</div> -->
		<%@include file="/WEB-INF/wap/common/nav.jsp"%><!-- 底部导航 -->
		<div class="myMain">
			<p class="one"><img src="${obj.headImgUrl }" /></p>
			<p class="two">姓名：${obj.customerName}</p>
			<p class="three">学校:${obj.schoolName }</p>
			<p class="four">当前余额:${obj.balance }</p>
		</div>
		<div class="mymenu">
			<a href="<%=basePath%>wap/center/toPayPage.html">
				<p class="one">充值</p>
			</a>
			<%-- <a href="<%=basePath%>wap/center/toWithdraw.html"> --%>
				<p class="two goMoney" id="goMoney">提现</p>
			<!-- </a> -->
			<a href="<%=basePath%>wap/honor.html">
				<p class="three">荣誉</p>
			</a>
		</div>
		<div class="myList" id="mylist">
		</div>
		<p class="myadd" id="myadd">加载更多</p>

		<!--弹窗-->
		<div class="gomoney">
			<img src="<%=basePath%>up/img/redpacket.png" alt="" />
			<p class="one">提现金额</p>
			<input type="number" name="" id="moeny" value="" placeholder="2~200元" />
			<p class="two">
				<span id="sure" >提交</span>
				<span id="cancel">取消</span>
			</p>
		</div>
		<div class="mui-backdrop-tips"></div>
		
		<script type="text/javascript">
			$(function() {
				document.getElementById("goMoney").addEventListener("tap", function() {
					$(".gomoney").show();
					$(".mui-backdrop-tips").show();
				});
				document.getElementById("sure").addEventListener("tap", function() {
					var baseP ="<%=basePath%>";
					var money = $("#moeny").val();
					if(""==money||money.length==0){
						alert("请输入金额!");
						return "";
					}
					var m = Number(money);
					if(money>200||money<2){
						alert("提现金额不在范围!");
						return "";
					}
					$.ajax({
						type : "post",
						url : baseP+"wap/ajax/center/addWithdraw.html",
						dataType : "json",
						data : {money:money},
						async : false,//异步
						success : function(data) {
							if(data){
								alert("申请提交成功!");								
							}else{
								alert("申请金额已达账户余额!请等待审核!");
							}
						}
					});
					$(".gomoney").hide();
					$(".mui-backdrop-tips").hide();
				});
				document.getElementById("cancel").addEventListener("tap", function() {
					$(".gomoney").hide();
					$(".mui-backdrop-tips").hide();
					
				});
			});
			$(function() {
				var winW = document.documentElement.clientWidth;
		
				var fontSize = 100;
				var dew = 375;
				var rem = dew / fontSize;
			
				document.documentElement.style.fontSize = winW / rem + "px";
			});
		</script>

	</body>

</html>