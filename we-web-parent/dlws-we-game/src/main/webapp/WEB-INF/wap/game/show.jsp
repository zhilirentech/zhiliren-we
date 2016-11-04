<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/wap/common/tld.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta content="width=device-width, minimum-scale=1,initial-scale=1, maximum-scale=1, user-scalable=1" id="viewport" name="viewport" />
		<meta content="yes" name="apple-mobile-web-app-capable" />
		<meta content="black" name="apple-mobile-web-app-status-bar-style" />
		<link rel="stylesheet" type="text/css" href="${base}up/css/mui.min.css" />
		<link rel="stylesheet" href="${base}up/css/maneyPK.css" />
		<link rel="stylesheet" type="text/css" href="${base}up/css/self.css" />
		<link rel="stylesheet" href="${base}up/css/index.css" />
		<link rel="stylesheet" type="text/css" href="${base}up/css/backadd.css"/>
		<script src="${base}up/js/mui.min.js"></script>
		<script src="${base}up/js/mui.pullToRefresh.js"></script>
		<script src="${base}up/js/mui.pullToRefresh.material.js"></script>
		<script src="http://cdn.bootcss.com/jquery/3.1.0/jquery.js"></script>
		<script src="${base}up/js/time.js"></script>
		<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script src="${base}up/js/weixin-share.js"></script>
		<script>
			var shareInfo = [{title:"敢不敢接招？",desc:"小样，就赌你赖床，一起来PK早起呀！"},{title:"约不约？",desc:"你丑你赖床，我美我早起，有胆就来PK早起啊！"}];
			var timestamp=new Date().getTime();
			var index = timestamp%2;
			var clientUrl = window.location.href;
	   		var targetUrl = "${base}wap/game/entrance.html?id=${obj.game.id}";
	        var reqPath="${base}wap/home/getJSConfig.html";
	        var link="${base}wap/home/wxShare.html";
	        var title = shareInfo[index].title;
	        var desc = shareInfo[index].desc;
	        var imgUrl = "${base}up/img/redpacket.png";
	  		window.onload=weixinShare({
	  			clientUrl:clientUrl,//当前页面所在的浏览器URL全路径
	  			targetUrl:targetUrl,//跳转目标url
	  			reqPath:reqPath,//获取微信js授权的请求路径
	  			title: title, // 分享标题
			    desc: desc, // 分享描述
			    link: link, // 分享链接
			    imgUrl:imgUrl, // 分享图标
			    type: '', // 分享类型,music、video或link，不填默认为link
			    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
	  		});
		</script>
		
		<title>PK</title>
		<style type="text/css">
        body, div {
            margin: 0;
            padding: 0;
           
        }

        html, body {
            width: 100%;
        }

        #timeBox {
             margin: 10px auto;
    width: 80%;
    height: 40px;
    line-height: 40px;
    text-align: center;
    border: 1px;
    border-radius: 50px;
    box-shadow: 3px 3px 10px 0 lightcoral;
    background: #f18e00;
    font-size: 15px;
          }

        #timeSpan {
            color: #fff;
            font-weight: bold;
        }
    </style>
	</head>
	
	<body>
		<!-- <div class="header">
			<span></span> 
			<span></span>
		</div> -->
			
		<div class="main_time">
			<c:if test="${obj.game.status!=0}">
				<span>请在</span><span><fmt:formatDate value="${obj.beginTime }"  pattern="MM-dd HH:mm" />-<fmt:formatDate value="${obj.endTime }" pattern="HH:mm" /></span><span>准时签到</span>
			</c:if>
			<c:if test="${obj.game.status==0}">
				<span>邀请超时</span>
			</c:if>
		</div>
	<div class="main ">
		<div class="main_con clearfix">
			<div class="first-show">
				<c:forEach items="${obj.list}" var="one" begin="0" end="3">
					<div class="main_img fl">
						<c:if test="${not empty one.headImgUrl}">
							<img class="medalnumbertwo" src='${one.headImgUrl}' alt="" />
						</c:if>
						<c:if test="${empty one.headImgUrl}">
							<img class="medalnumbertwo" src='${base}up/img/personHead.png' alt="" />
						</c:if>
						<p>${one.customerName}</p>
					</div>
				</c:forEach>
			</div>	
			<div class="two-hide" style="display: none;">
				<c:forEach items="${obj.list}" var="one" begin="4">
					<div class="main_img fl">
						<c:if test="${not empty one.headImgUrl}">
							<img class="medalnumbertwo" src='${one.headImgUrl}' alt="" />
						</c:if>
						<c:if test="${empty one.headImgUrl}">
							<img class="medalnumbertwo" src='${base}up/img/personHead.png' alt="" />
						</c:if>
						<p>${one.customerName}</p>
					</div>
				</c:forEach>
			</div>
			<div class="add_img">查看全部成员</div>
		</div>
		
	</div>
	<c:if test="${fn:length(obj.signList) > 0}"><!-- 已签到列表 -->
		<div class="main_con clearfix pb5 mt20">
			<div class="SchoollList">
			<c:forEach items="${obj.signList}" var="one">
					<div class="one">
					<div class="medalnumbertwo">
					<c:if test="${not empty one.headImgUrl}">
						<img class="medalnumbertwo" src='${one.headImgUrl}' alt="" />
					</c:if>
					<c:if test="${empty one.headImgUrl}">
						<img class="medalnumbertwo" src='${base}up/img/personHead.png' alt="" />
					</c:if>
					</div>
					<div class="nameThree">
						<p>${one.customerName}</p>
						<p>${one.schoolName}</p>
					</div>
					<div class="dayFour">
						<div class="dayFourtips">
							<span><fmt:formatDate value="${one.signTime}"  pattern="HH:mm:ss" /></span>
						</div>
					</div>
				</div>	
			</c:forEach>
			<div class="SchoollList_finish">已签到</div>
		</div>
	</c:if>
	</div>
	<c:if test="${obj.game.status == 2}"><!-- 游戏正在进行中 -->
		<c:if test="${obj.isAttend}"><!-- 已接受pk -->
			<div class="shareExplain">点击右上角微信按钮，发起PK吧！建立微信打卡群有利于互相监督哦~</div>
		</c:if>
		<c:if test="${!obj.isAttend}"><!-- 没有接受pk -->
			<div class="PK_signIn mb100 accept" >
				<p>接受</p>
				<p>PK</p>
			</div>
		</c:if>
		<div id="timeBox">距离邀请结束时间:[ <span id="timeSpan">00:00:00</span> ]</div>
	</c:if>
	<c:if test="${obj.game.status == 1}"><!-- 游戏成功 -->
		<c:if test="${obj.isAttend}">
			<c:if test="${obj.status == 0}">
				<div class="PK_signIn mb100 sign">
					<p>pk</p>
					<p>签到</p>
				</div>
			</c:if>
			<c:if test="${obj.status == 1}">
				<div class="PK_signIn mb100 signed">
					<p>已签到</p>
				</div>
			</c:if>
			<c:if test="${obj.status == 3}">
				<div class="PK_signIn mb100 overtime">
					<p>签到超时</p>
				</div>
			</c:if>
		</c:if>
		<c:if test="${!obj.isAttend}">
			<div class="PK_signIn mb100 gameover">
				<p>邀请超时</p>
			</div>
		</c:if>
	</c:if>
	<%@include file="/WEB-INF/wap/common/nav.jsp"%><!-- 底部导航 -->
		<script type="text/javascript">
			var b ;
			var beginTime = "<fmt:formatDate value="${obj.game.beginTime}"  pattern="yyyy-MM-dd HH:mm:ss" />" ;
			var endTime = "<fmt:formatDate value="${obj.game.endTime}"  pattern="yyyy-MM-dd HH:mm:ss" />" ;
			var createTime = "<fmt:formatDate value="${obj.game.createTime}"  pattern="yyyy/MM/dd HH:mm:ss" />" ;
			$(function() {
				var winW = document.documentElement.clientWidth;
				var fontSize = 100;
				var dew = 375;
				var rem = dew / fontSize;
				document.documentElement.style.fontSize = winW / rem + "px";
			});
			$(function(){
				$(".sign").click(function(){
					//判断当前时间是否在签到时间范围内
					var res = isInScpoe(beginTime,endTime);
					if(res == "MIDDLE" || res == "AFTER"){
						//去签到
						$.ajax({
							url: "${base}/wap/game/signIn.html",
							type: "POST",
							dataType: "json",
							cache: false,
							success: function(data) {
								var msg = data.message ;
								if(msg == "NOATTEND"){
									alert("亲，你没有参加今日签到") ;
								}
								if(msg == "SIGNINED"){
									alert("亲，你今日已经签到过了") ;
								}
								if(msg == "NOTIME"){
									alert("没有系统签到时间") ;
								}
								if(msg == "YES"){
									alert("签到成功");
									window.location.reload(true);
								}
								if(msg == "BEFORE"){
									alert("亲，还没有到签到时间") ;
								}
								if(msg == "AFTER"){
									alert("亲，签到时间已过") ;
									window.location.reload(true);
								}
							},
							error: function() {
								document.write("非法操作");
							}
						});
					};
					if(res == "BEFORE"){
						alert("亲，还没有到签到时间") ;
					}
					
				});
				
				//倒计时
				showtime();
				
				$("#ruleok").click(function(){
					$(".rulePopup").hide();
				});
				$(".add_img").click(function(){
					console.log("ceshi");
					if($(".two-hide").css("display")=="none"){
						$(".two-hide").css("display","block");
					}else{
						$(".two-hide").css("display","none");
					}
					//console.log($(".two-hide").css("display"));
					//$(".two-hide").slideToggle();
				}) ;
				//接受PK
				$(".accept").click(function(){
					//去签到
					$.ajax({
						url: "${base}/wap/game/acceptPK.html",
						type: "POST",
						dataType: "json",
						cache: false,
						data:{
							id:"${obj.game.id}"
						},
						success: function(data) {
							var msg = data.message ;
							if(data.flag==false){
								alert(data.msg);
							}
							if(msg == "NOMONEY"){
								alert("余额不足，请到个人中心充值") ;
							}
							if(msg == "CLOSED"){
								alert("PK已结束") ;
							}
							if(msg == "OK"){
								alert("参加成功!");
								window.location.reload(true);
							}
						},
						error: function() {
							document.write("非法操作");
						}
					});
				}) ;
			});
			function isInScpoe(begin,end){
				var currTime = new Date();
			
				var beginStr = begin;
				var beginTime =  new Date(beginStr.replace(/-/ig,'/'));
				var endStr = end;
				var endTime =  new Date(endStr.replace(/-/ig,'/'));
				
				if(beginTime<=currTime&&currTime<=endTime){
					return "MIDDLE";
				}
				if(currTime<beginTime){
					return "BEFORE";
				}
				if(currTime>endTime){
					return "AFTER";
				}
			};
			
			function showtime(){
				function addZero(value) {
			        return value < 10 ? "0" + value : value;
			    }
					
			    function getRemainTime() {
			      	var nowTime = new Date();
			        var tarTime = new Date(createTime);
			        var spanTime = tarTime - nowTime + 30*60*1000;//->目标时间和当前时间之间相差的毫秒数
			        //如果当前的时间已经超过目标时间了,我们就不在计算了
			        if (spanTime <= 0) {
			            window.clearInterval(timer);//->当到达目标时间后我们停止定时器,不在倒计时了
			            return "00:00:00";//->如果只写一个return后面什么都没有写的话,返回的是undefined
			        }

			        //1、计算总相差时间中包含了多少个小时
			        var hour = Math.floor(spanTime / (1000 * 60 * 60));

			        //2、计算相差时间中包含了多少个分钟(需要把小时占用的时间减去,剩下的时间里在计算还有多少个分钟)
			        spanTime = spanTime - (hour * 60 * 60 * 1000);
			        var minute = Math.floor(spanTime / (1000 * 60));

			        //3、计算相差时间中包含了多少个秒(需要把分钟占用的时间减去,剩下的时间里在计算还有多少个秒)
			        spanTime = spanTime - (minute * 60 * 1000);
			        var second = Math.floor(spanTime / 1000);
					var t = addZero(hour) + ":" + addZero(minute) + ":" + addZero(second);
			        return t;
			    }

			    var timeSpan = document.getElementById("timeSpan");
			    timeSpan.innerHTML = getRemainTime();

			    var timer = window.setInterval(function () {
			        timeSpan.innerHTML = getRemainTime();
			    }, 1000);
				
			}
			
			
			
		</script>
	</body>

</html>
