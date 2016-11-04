<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="/WEB-INF/share/500.jsp"%>
<%@include file="/WEB-INF/share/tld.jsp"%>
<form id="pagerForm" action="${base }/share/user/managerole.html?userId=${obj}" mehtod="post"></form>
<we:toggleTable url="/share/user/listrole.html?userId=${obj}"/>