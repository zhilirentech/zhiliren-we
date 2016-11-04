<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/common/500.jsp"%>
<%@include file="/WEB-INF/common/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理平台</title>
<link href="${base}/common/css/style.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/common/css/core.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/common/css/login.css" rel="stylesheet" type="text/css">

<!-- form验证 -->
<link rel="stylesheet" href="${base}/common/css/validationEngine.css" type="text/css">
<script src="${base}/common/js/jquery-1.js" type="text/javascript"></script>
<script src="${base}/common/js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script src="${base}/common/js/jquery_002.js" type="text/javascript" charset="utf-8"></script>
<script>
    jQuery(document).ready(function(){
        jQuery("#formID").validationEngine();
    });
    jQuery(document).ready(function(){
    	$("#captcha").click(function(){
    		$(this).attr("src", "/Captcha.jpg?time=" + new Date());
    		return false;
    	});
    });
    
    function changeValidateCode(){
        var timenow = new Date().getTime(); 
        var _obj = $("#confirmCode");
        _obj.attr("src","${base}/validateImage.html?d="+timenow);
    }

</script>
</head>
<body>
<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<%-- 
				<img src="${base}/common/images/logo1.png">
				--%>
			</h1>

			<div class="login_headerContent">
				<h2 class="login_title">请登录</h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form method="post" action="${base}/admin/login.html" id="formID">
					<!-- 
					登录消息提示区
					<p style="color: red; margin-left: 10px;"></p>
					 -->
					<c:if test="${obj != null}">
						<p style="color: red; margin-left: 10px;">${obj.errMsg}</p>
					</c:if>
					<p>
						<label>用户名:</label>
						<input name="loginName" style="width: 150px;" class="validate[required] login_input" id="username" type="text" value="${obj.loginName}" maxlength="32">
					</p>
					<p>
						<label>密&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
						<input name="password" style="width: 150px;" class="validate[required] login_input" id="password" type="password" value="${obj.password}" maxlength="32">
					</p>	
					
					<p>
						<label>验证码:</label>
						<input name="validateCode" style="width: 150px;" class="validate[required] login_input" id="validateCode" type="text" value="${obj.validateCode}" maxlength="4">
					</p>
					<p>
						<label>&nbsp;</label>
						<img style="width:150px;" title="看不清，点击换一张" onclick="changeValidateCode()" id="confirmCode" src="${base}/validateImage.html"/>
					</p>
								
					<div class="login_bar" style="disply:block;float:left;">
						<input class="sub" value="" type="submit">
					</div>
				</form>
			</div>
			<div class="login_banner"><img src="${base}/common/images/login_banner.jpg"></div>
			
		</div>
		<div id="login_footer">
			校咖
		</div>
	</div>

</body>
</html>
<% 
	/*
		解决安全性:会话标识未更新的问题(登录前后) 
		将用户进入登陆页面时所产生的会话也就是session清空，然后让跟踪这个会话的cookie过期，这样服务器就不再掌握有关这个会话的任何信息了。
		要想与服务器继续通信，就要产生一个新的会话才行。于是会话标示就更新了
		目前测试在服务器启动以后，第一次访问的时候会报错，因此暂时注释了
	 */
	 
/*
	if(null != request){
		session = request.getSession();
		if(session != null){
			session.invalidate();//清空session
			Cookie cookie = request.getCookies()[0];//获取cookie
			if(cookie != null){
				cookie.setMaxAge(0);//让cookie过期
			}
		}
	}
*/
%>