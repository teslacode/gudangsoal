<%@include file="header.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script>
            $(function() {
                $("#tryAgain").click(function(e){
                    e.preventDefault();
                    $.get("${fullPath}", function(data){
                        data = jQuery('<div>').html(data);
                        $("#content").html(data.find("div#content"));
                    });
                });
            });
        </script>
    </head>
    <body>
        <div id="content">
            <div class="col-md-12">
                <c:forEach items="${listRfTingkat}" var="rfTingkat">
                    <div class="col-md-2">
                        <a href="${fullPath}/${rfTingkat.id}">${rfTingkat.description}</a>
                    </div>
                </c:forEach>
                <c:forEach items="${listRfKelas}" var="rfKelas">
                    <div class="col-md-2">
                        <a href="${fullPath}/${rfKelas.id}">${rfKelas.description}</a>
                    </div>
                </c:forEach>
                <c:forEach items="${listRfPelajaran}" var="rfPelajaran">
                    <div class="col-md-2">
                        <a href="${fullPath}/${rfPelajaran.id}">${rfPelajaran.description}</a>
                    </div>
                </c:forEach>
                <c:if test="${!empty listRfNilai}">
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <c:forEach items="${listRfNilai}" var="rfNilai">
                                    <div class="col-md-4">
                                        ${rfNilai.description} : ${rfNilai.nilai}
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${!empty listPertanyaan}">
                    <div class="col-md-12">
                        <form:form method="POST" id="pertanyaanForm">
                            <table class="table table-bordered table-serial">
                                <tbody>
                                    <c:forEach items="${listPertanyaan}" var="pertanyaan">
                                        <tr>
                                            <td class="col-xs-8">
                                                <div class="col-md-12 seq">
                                                    ${pertanyaan.description}
                                                </div>
                                                <div class="col-md-12 pilihan-serial">
                                                    <c:forEach items="${pertanyaan.listPilihan}" var="pilihan">
                                                        <div class="col-md-4">
                                                            <input type="radio" name="pertanyaan${pilihan.pertanyaanId}" value="${pilihan.id}" <c:if test="${pertanyaan.pilihanId == pilihan.id}">checked</c:if>>
                                                            <span class="pilihan">${pilihan.description}</span>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </td>
                                            <c:if test="${!empty pertanyaan.nilai}">
                                                <td class="col-xs-1">
                                                    ${pertanyaan.nilai}
                                                </td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td>
                                            <c:if test="${empty total}">
                                                <input type="submit" id="submit" name="submit" value="Submit" class="btn btn-primary"/>
                                            </c:if>
                                            <c:if test="${!empty total}">
                                                <input type="submit" id="tryAgain" name="tryAgain" value="Try Again" class="btn btn-default"/>
                                            </c:if>
                                        </td>
                                        <c:if test="${!empty total}">
                                            <td>
                                                ${total}
                                            </td>
                                        </c:if>
                                    </tr>
                                </tfoot>
                            </table>
                        </form:form>
                    </div>
                </c:if>
            </div>
        </div>
    </body>
</html>
