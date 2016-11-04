<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="we" uri="http://www.we.cn"%>
<c:set var="base" value='${pageContext.request.scheme}${"://" }${pageContext.request.serverName}${":"}${pageContext.request.serverPort}${pageContext.request.contextPath}${"/"}'></c:set>
