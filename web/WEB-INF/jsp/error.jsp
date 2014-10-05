<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap-theme.min.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">
        <script src="<c:url value="/resources/js/jquery-1.9.1.js" />"></script>
        <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
    </head>
    <body>
        <div class="form-login">
            <div class="container">
                <div class="col-md-4 col-md-offset-4 text-center">
                    Please contact your administrator.
                    <br>Message: ${exception}
                </div>
            </div>
        </div>
    </body>
</html>