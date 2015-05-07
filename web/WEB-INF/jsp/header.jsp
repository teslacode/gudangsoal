<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap-theme.min.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/default/css/jquery-ui.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/datatables/css/jquery.dataTables_themeroller.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/default/css/style.css" />">
        <script src="<c:url value="/resources/default/js/jquery-1.9.1.js" />"></script>
        <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
        <script src="<c:url value="/resources/default/js/jquery-ui.js" />"></script>
        <script src="<c:url value="/resources/default/js/jquery.validate.min.js" />"></script>
        <script src="<c:url value="/resources/datatables/js/jquery.dataTables.js" />"></script>
    </head>
    <body>
        <ul class="breadcrumb">
            <c:forEach items="${listBreadCrumbs}" var="breadCrumbs">
                <li><a href="${breadCrumbs.link}">${breadCrumbs.description}</a></li>
            </c:forEach>
        </ul>
    </body>
</html>
