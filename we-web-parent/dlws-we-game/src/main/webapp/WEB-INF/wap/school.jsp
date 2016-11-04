<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html> 
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<meta name="format-detection" content="telephone=no" />
	<title>起床</title>
	<link rel="stylesheet" href="<%=basePath%>up/css/school/style.css">
	<script src="<%=basePath%>up/js/school/zepto.min.js"></script>
	<script src="<%=basePath%>up/js/school/touch-0.2.14.min.js"></script>
	<script src="<%=basePath%>up/js/school/iscroll.js"></script>
	<script src="<%=basePath%>up/js/school/jquery-1.7.2.min.js"></script>
	
	
</head>
<body>
	<div id="wrapper">
		<article class="school">
			<div class="schoolClose hide">×</div>
			<section class="schoolSearchForm">
				<form action="">
					<input type="text" id="schoolName" placeholder="搜索学校">
					<input type="button">
				</form>
			</section><!-- schoolSearchForm -->
			<section class="schoolSelect hide show">
				<nav class="schoolSelect-city" id="cityScroll">
					<div class="scroller">
						<ul>
						<c:forEach items="${obj.citys }" var="city">
							<c:if test="${city.current==true}">
									<li class="active" data-tab="${city.id}">${city.cityName}</li>
							</c:if>
							<c:if test="${city.current==false}">
									<li data-tab="${city.id}">${city.cityName}</li>
							</c:if>
						</c:forEach>
						</ul>
					</div>
				</nav>
				
				
				<c:forEach var="city" items="${obj.citys}">
					<c:if test="${city.current==true}">
						<article class="schoolSelect-content hide show" id="scroll_${city.id}">
						<div class="scroller">
						<section class="schoolSelect-item">
							<h2 class="schoolSelect-district">${city.cityName}</h2>
							<ul class="schoolList" id = "${city.id}">
							<c:forEach items="${city.schools}" var="school">
								<li onclick="schoolClick(${school.id},${city.id})"><a href="<%=basePath%>wap/home/saveSchool.html?openId=${obj.openId}&schoolId=${school.id}&cityId=${city.id}">${school.schoolName}</a></li>
							</c:forEach>
							</ul>
						</section>
						</div>
						</article>	
					</c:if>
					<c:if test="${city.current==false}">
						<article class="schoolSelect-content hide" id="scroll_${city.id}">
						<div class="scroller">
						<section class="schoolSelect-item">
							<h2 class="schoolSelect-district">${city.cityName}</h2>
							<ul class="schoolList" id = "${city.id}">
							<c:forEach items="${city.schools}" var="school">
								<li onclick="schoolClick(${school.id},${city.id})"><a href="<%=basePath%>wap/home/saveSchool.html?schoolId=${school.id}&cityId=${city.id}">${school.schoolName}</a></li>
							</c:forEach>
							</ul>
						</section>
						</div>
						</article>
					</c:if>
				</c:forEach>
			</section><!-- schoolSelect -->
			<input type="hidden" id = "openId" name = "openId" value="${obj.openId}">
			<%-- <input type="hidden" id = "isAttendGroup" name = "isAttendGroup" value="${isAttendGroup}"> --%>
			<section class="schoolSearch hide">
				<ul class="schoolList" id = "schoolList">
					
				</ul>
			</section><!-- schoolSearch -->
		</article><!-- school -->
	</div>
	<!-- 选择学校js -->
	<script src='<%=basePath%>up/js/school/school.js'></script>
	<!-- 选择学校js -->
	<script type="text/javascript">
	
		$('#schoolName').bind('input propertychange', function() {   
			
			var schoolName = $("#schoolName").val();
			var openId = $("#openId").val();
			//调用ajax获取分页加载的内容
	    	$.ajax({
			type : "post",
			url : "<%=basePath%>wap/ajax/home/toSelectSchool.html",
			dataType : "json",
			async : false,//异步
			data : {schoolName:schoolName},
			success : function(data) {
				
				if(data.ajax_status == "ajax_status_success"){
					var tempHTML = "";
					
					//获取成功后的一些操作
					 for (var i = 0; i <data.list.length; i++) {
						 //../weixinTgCustomer/toIndex.html?openId={"+openId+"}&schoolId=${"+data.list[i].id+"}&cityId=${"+data.list[i].cityId+"}id
						 tempHTML = tempHTML+"<li><a href='<%=basePath%>wap/home/saveSchool.html?openId="+openId+"&schoolId="+data.list[i].id+"&cityId="+data.list[i].cityId+"'>"+data.list[i].schoolName+"</a></li>";
	         		 }
					 schoolList.innerHTML = tempHTML;
				}
				if(data.ajax_status == "ajax_status_failure"){
					alert(data.info);
				};
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
			  //通常情况下textStatus和errorThrown只有其中一个包含信息
		   }
		 });	
		});
		function schoolClick(schoolId,cityId){
			
			//var openId = $("#openId").val();
			
			window.location.href='<%=basePath%>wap/home/saveSchool.html?schoolId='+schoolId+'&cityId='+cityId;
		}
		
		
	</script>
</body>
</html>
