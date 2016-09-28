<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta content="width=device-width, minimum-scale=1,initial-scale=1, maximum-scale=1, user-scalable=1" id="viewport" name="viewport" />
		<meta content="yes" name="apple-mobile-web-app-capable" />
		<meta content="black" name="apple-mobile-web-app-status-bar-style" />
		<link rel="stylesheet" type="text/css" href="css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="css/self.css" />
		<link rel="stylesheet" href="css/index.css" />
		<link rel="stylesheet" href="css/my.css" />
		<script src="js/mui.min.js"></script>
		<script src="js/mui.pullToRefresh.js"></script>
		<script src="js/mui.pullToRefresh.material.js"></script>
		<script src="http://cdn.bootcss.com/jquery/3.1.0/jquery.js"></script>
		<title>4-2充值页面</title>
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
		$(function() {
			//					alert(1);
			var data = {
				list: [{
					"src": "img/focus_image_01.png",
					"desc": "11"
				}, {
					"src": "img/banner1.png",
					"desc": "22"
				}, {
					"src": "img/honorHeader.png",
					"desc": "33"
				}, {
					"src": "img/honormedal4.png",
					"desc": "44"
				}]
			}
			var html = template('mytest', data);
			$(".addban").append(html);
			$(".del").remove();
			var html1 = template("mytest1", data);
			$(".banCircle").append(html1);
		})
	</script>
	<script id="mytest" type="text/html">
		<div class="mui-slider-item ">
			<a href="#">
				<img src={{list[list.length-1][ "src"]}}>
				<p class="mui-slider-title">{{list[list.length-1].desc}}</p>
			</a>
		</div>
		{{each list as value i}}
		<div class="mui-slider-item">
			<a href="#">
				<img src={{value.src}}>
				<p class="mui-slider-title">{{value.desc}}</p>
			</a>
		</div>
		{{/each}}
		<div class="mui-slider-item ">
			<a href="#">
				<img src={{list[0][ "src"]}}>
				<p class="mui-slider-title">{{list[0].desc}}</p>
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

	<body>
<%-- <%@include file="/WEB-INF/wap/common/nav.jsp"%><!-- 底部导航 --> --%>
		<div id="slider" class="mui-slider">
			<div class="mui-slider-group mui-slider-loop addban">
				<!-- 额外增加的一个节点(循环轮播：第一个节点是最后一张轮播) -->

				<div class="mui-slider-item mui-slider-item-duplicate del">
					<a href="#">

						<img src="img/banner_10.jpg">
						<p class="mui-slider-title">44</p>
					</a>
				</div>

			</div>
			<div class="mui-slider-indicator mui-text-right tips banCircle">
				<!--<div class="mui-indicator mui-active"></div>
				<div class="mui-indicator"></div>
				<div class="mui-indicator"></div>
				<div class="mui-indicator"></div>-->
			</div>
		</div>

		<div class="inpmoney clearfix">
			<div class="one">
				<p>充值金额:</p>
				<input type="number" name="" id="" value="" placeholder="￥充值金额1~100元" />
			</div>
		</div>
		<div class="inpmoney clearfix inpmoney2">
			<div class="one">
				<p>参与日期:</p>
				<!--<input type="number" name="" id="" value=""  placeholder="请选择日期"/>-->
				<div class="selectDat">
					<p>请选择日期</p>
				</div>
			</div>
		</div>
		<div class="sel_cal">
			<a class="fl prev_month"><</a>
			<p class="showdate fl"></p>
			<a class="fr next_month">></a>
			<botton class="timebtn">确定</botton>
		</div>
		<div class="submitBtn">
			<p>平均每日金额:50元</p>
			<p>提交</p>
		</div>
		<div class="mypayhint">注意：平均每天金额需要大于1元</div>
		<script src="js/time1.js"></script>
		<script src="js/template.js"></script>
		<script type="text/javascript">
			/*自动轮播*/
			$(function() {
				var slider = mui("#slider");
				slider.slider({
					interval: 4000
				});
			})

		/*时间插件*/
			$(function() {
		
				var arytime = ["2016-7-1", "2016-7-3", "2016-7-4", "2016-7-5", "2016-8-1", "2016-8-2", "2016-8-31", "2016-9-1", "2017-1-2"]
				var returnArytime=[];
				function gooverPK(arytime){
					for (var i = 0; i < arytime.length; i++) {
						var list = arytime[i].split("-");
						if ($(".showdate").attr("year") == list[0] && $(".showdate").attr("month") == list[1]) {
							for (var k = 0; k < $(".sel_cal span").length; k++) {
								if ($($(".sel_cal span")[k]).html() == list[2]) {
									$($(".sel_cal span")[k]).addClass("yello");
									$($(".sel_cal span")[k]).attr("flagday", "true");
								}
							}
						}
					}
				}
				function leftAndRightClickGooverPK(arytime){
					for (var i = 0; i < arytime.length; i++) {
						var list = arytime[i].split("-");
						if ($(".showdate").attr("year") == list[0] && $(".showdate").attr("month") == list[1]) {
							for (var k = 0; k < $(".sel_cal span").length; k++) {
								if ($($(".sel_cal span")[k]).html() == list[2]) {
									$($(".sel_cal span")[k]).addClass("green");
//									$($(".sel_cal span")[k]).attr("flagday", "true");
								}
							}
						}
					}
				}
				function clickGooverPK(temp,arytime){
					console.log(temp.html());
					for(var i=0;i<arytime.length;i++){
						var year=$(".showdate").attr("year");
						var month=$(".showdate").attr("month");
						var day=temp.html();
						if(arytime[i]==year+"-"+month+"-"+day){
							return;
						}
					}
					if(temp.html()!="&nbsp;"){
						if(temp.attr("class")=="green"){
							temp.removeClass("green");
							var year=$(".showdate").attr("year");
							var month=$(".showdate").attr("month");
							var day=temp.html();
							for(var i=0;i<returnArytime.length;i++){
								if(returnArytime[i]==year+"-"+month+"-"+day){
									returnArytime.splice(i,1);
									console.log(returnArytime);
								}
							}
						}else{
						temp.addClass("green");
						console.log(temp.attr("class"))
						console.log($(".showdate").attr("year")+"...");
						var year=$(".showdate").attr("year");
						var month=$(".showdate").attr("month");
						var day=temp.html();
						returnArytime.push(year+"-"+month+"-"+day);	
						console.log(returnArytime);					}
					}
				}
				gooverPK(arytime);
				$(".prev_month").click(function() {
					gooverPK(arytime);
					leftAndRightClickGooverPK(returnArytime);
				})
				$(".next_month").click(function() {
					gooverPK(arytime);
					leftAndRightClickGooverPK(returnArytime);
				})
				console.log(arytime);
				console.log(arytime.join());
				
				mui(".sel_cal").on("tap","span",function(){
					var temp=$(this);
				
				clickGooverPK(temp,arytime);	
			
				})
				$(".timebtn").click(function(){
					$(".sel_cal").slideToggle();
				})
			})
			





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