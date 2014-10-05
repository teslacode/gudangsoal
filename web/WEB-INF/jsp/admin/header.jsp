<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/google-code-prettify/prettify.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap-combined.no-icons.min.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap-responsive.min.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/font-awesome-4.0.3/css/font-awesome.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap-theme.min.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/jquery-ui/css/jquery-ui.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/datatables/css/jquery.dataTables_themeroller.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/index.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/default/css/style.css" />">
        <script src="<c:url value="/resources/default/js/jquery-1.9.1.js" />"></script>
        <script src="<c:url value="/resources/default/js/jquery.hotkeys.js" />"></script>
        <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
        <script src="<c:url value="/resources/google-code-prettify/prettify.js" />"></script>
        <script src="<c:url value="/resources/bootstrap/js/bootstrap-wysiwyg.js"/>"></script>
        <script src="<c:url value="/resources/jquery-ui/js/jquery-ui.js" />"></script>
        <script src="<c:url value="/resources/default/js/jquery.validate.min.js" />"></script>
        <script src="<c:url value="/resources/datatables/js/jquery.dataTables.js" />"></script>
        <script>
            $(function(){
                $("#${activePath}").addClass("active");
            });
        </script>
    </head>
    <body>
        <nav id="menu" class="navbar navbar-default navbar-static-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbarCollapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">GUDANG SOAL</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <ul class="nav navbar-nav">
                        <li id="admin"><a href="${contextPathAdmin}">HOME</a></li>
                        <li id="tingkat"><a href="${contextPathAdmin}/tingkat">TINGKAT</a></li>
                        <li id="kelas"><a href="${contextPathAdmin}/kelas">KELAS</a></li>
                        <li id="pelajaran"><a href="${contextPathAdmin}/pelajaran">PELAJARAN</a></li>
                        <li id="soal"><a href="${contextPathAdmin}/soal">SOAL</a></li>
                        <li id="nilai"><a href="${contextPathAdmin}/nilai">NILAI</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="${logoutpath}">LOGOUT</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <ul class="breadcrumb">
            <c:forEach items="${listBreadCrumbs}" var="breadCrumbs">
                <li><a href="${breadCrumbs.link}">${breadCrumbs.description}</a></li>
            </c:forEach>
        </ul>
    </body>
</html>
