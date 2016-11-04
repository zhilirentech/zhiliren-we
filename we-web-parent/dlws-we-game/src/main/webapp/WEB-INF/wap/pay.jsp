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
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta content="width=device-width, minimum-scale=1,initial-scale=1, maximum-scale=1, user-scalable=1" id="viewport" name="viewport" />
		<meta content="yes" name="apple-mobile-web-app-capable" />
		<meta content="black" name="apple-mobile-web-app-status-bar-style" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>up/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>up/css/self.css" />
		<link rel="stylesheet" href="<%=basePath%>up/css/index.css" />
		<link rel="stylesheet" href="<%=basePath%>up/css/my.css" />
		<script src="<%=basePath%>up/js/mui.min.js"></script>
		<script src="<%=basePath%>up/js/mui.pullToRefresh.js"></script>
		<script src="<%=basePath%>up/js/mui.pullToRefresh.material.js"></script>
		<script src="http://cdn.bootcss.com/jquery/3.1.0/jquery.js"></script>
		<title>账户充值</title>
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
				background: green;
				border-radius: 50%;
			}
			.yello{
				background: #d94336;
				border-radius: 50%;
			}
	</style>
	<script type="text/javascript">
		function aa(){
			var money = $("#moneyId").val();
			if(isNaN(money)){
			   alert("请输入合法数字!");
			   return ;
			}
			m = Number(money);
			if(m<1||m>200){
				alert("金额输入有误!");
				return ;
			}
			$("#formId").submit();
		};
		$(function() {
			//					alert(1);
			
			var baseP ="<%=basePath%>";
			var data = "";
				$.ajax({
					type : "post",
					url : baseP+"wap/ajax/center/queryBannerList.html",
					dataType : "json",
					async : false,//异步
					success : function(d) {
						 data = d;
					}
				});
			<%-- var data = {
				list: [{
					"src": "<%=basePath%>up/img/focus_image_01.png",
					"desc": "11"
				}, {
					"src": "<%=basePath%>up/img/banner1.png",
					"desc": "22"
				}, {
					"src": "<%=basePath%>up/img/honorHeader.png",
					"desc": "33"
				}, {
					"src": "<%=basePath%>up/img/honormedal4.png",
					"desc": "44"
				}]
			}; --%>
			
			var html = template('mytest', data);
			$(".addban").append(html);
			$(".del").remove();
			/* var html1 = template("mytest1", data);
			$(".banCircle").append(html1); */
		});
	</script>
	<script id="mytest" type="text/html">
		<%-- 
		<div class="mui-slider-item ">
			<a href="#">
				<img src={{list[list.length-1].imgUrl}}>
				<p class="mui-slider-title"></p>
			</a>
		</div>
		{{each list as value i}}
		<div class="mui-slider-item">
			<a href="#">
				<img src={{value.imgUrl}}>
				<p class="mui-slider-title"></p>
			</a>
		</div>
		{{/each}}
		--%>
		<div class="mui-slider-item ">
			<a href="#">
				<img src={{list[0].imgUrl}}>
			</a>
		</div>
	</script>
	<%-- 
	<script id="mytest1" type="text/html">
		{{each list as value i}} {{if i==0}}
		<div class="mui-indicator mui-active"></div>
		{{else}}
		<div class="mui-indicator"></div>
		{{/if}} {{/each}}

	</script>
 --%>
	<body>
		<!-- <div class="header">
			<span></span> 账户充值
			<span></span>
		</div> -->
		<div id="slider" class="mui-slider">
			<div class="mui-slider-group mui-slider-loop addban">
				<!-- 额外增加的一个节点(循环轮播：第一个节点是最后一张轮播) -->

				<div class="mui-slider-item mui-slider-item-duplicate del">
					<%-- <a href="#">

						<img src="<%=basePath%>up/img/banner_10.jpg">
						<p class="mui-slider-title">44</p>
					</a> --%>
				</div>

			</div>
			<div class="mui-slider-indicator mui-text-right tips banCircle">
				<!--<div class="mui-indicator mui-active"></div>
				<div class="mui-indicator"></div>
				<div class="mui-indicator"></div>
				<div class="mui-indicator"></div>-->
			</div>
		</div>
		<form id="formId" action="<%=basePath%>/weixin/pay/pay.html">
			<div class="inpmoney clearfix">
				<div class="one">
					<p>充值金额:</p>
					<input type="text" name="amount" id="moneyId" value="" placeholder="￥充值金额1~200元" />
				</div>
			</div>
			<div class="sel_cal">
				<a class="fl prev_month"><</a>
				<p class="showdate fl"></p>
				<a class="fr next_month">></a>
				<botton class="timebtn">确定</botton>
			</div>
			<div class="submitBtn">
				<!-- <p>平均每日金额:50元</p> -->
				<!-- <p><leable onclick="aa();">提交</leable></p> -->
				<p onclick="aa();">提交</p>
				<!-- <p><input type="submit" value = "提交"></p> -->
			</div>
		</form>
		<script src="<%=basePath%>up/js/time1.js"></script>
		<script src="<%=basePath%>up/js/template.js"></script>
		<script type="text/javascript">
			/*自动轮播*/
			$(function() {
				var slider = mui("#slider");
				slider.slider({
					interval: 4000
				});
			});
			$(function() {
				var winW = document.documentElement.clientWidth;
				console.log(winW)
				var fontSize = 100;
				var dew = 375;
				var rem = dew / fontSize;
				document.documentElement.style.fontSize = winW / rem + "px"
			});
		</script>
	</body>

</html>