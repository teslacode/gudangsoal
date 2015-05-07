<%@include file="header.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script>
            $(function () {
                $("#tryAgain").click(function (e) {
                    e.preventDefault();
                    $.get("${fullPath}", function (data) {
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
                    <div class="col-md-4">
                        <a href="${fullPath}/${rfTingkat.id}">
                            <div class="tema alert alert-info text-center box-shadow">
                                ${rfTingkat.description}
                            </div>
                        </a>
                    </div>
                </c:forEach>
                <c:forEach items="${listRfKelas}" var="rfKelas">
                    <div class="col-md-4">
                        <a href="${fullPath}/${rfKelas.id}">
                            <div class="tema alert alert-info text-center box-shadow">
                                ${rfKelas.description}
                            </div>
                        </a>
                    </div>
                </c:forEach>
                <c:forEach items="${listRfPelajaran}" var="rfPelajaran">
                    <div class="col-md-4">
                        <a href="${fullPath}/${rfPelajaran.id}">
                            <div class="tema alert alert-info text-center box-shadow">
                                ${rfPelajaran.description}
                            </div>
                        </a>
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
                            <div id="myCarousel" class="carousel slide col-md-12" data-interval="0" data-ride="carousel">
                                <!-- Carousel items -->
                                <div class="carousel-inner">
                                    <c:forEach items="${listPertanyaan}" var="pertanyaan">
                                        <div class="<c:if test="${pertanyaan.nomorUrut == 0}">active</c:if> item">
                                            <div class="col-md-12">
                                                <h2>Soal ${pertanyaan.nomorUrut + 1}.</h2>
                                            </div>
                                            <div class="col-md-12">
                                                ${pertanyaan.description}
                                            </div>
                                            <div class="col-md-12">
                                                <c:forEach items="${pertanyaan.listPilihan}" var="pilihan">
                                                    <div class="col-md-12">
                                                        <input type="radio" name="pertanyaan${pilihan.pertanyaanId}" value="${pilihan.id}" <c:if test="${pertanyaan.pilihanId == pilihan.id}">checked</c:if>>
                                                        <span class="pilihan">${pilihan.description}</span>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <div class="col-md-12">
                                                <c:if test="${!empty pertanyaan.nilai}">
                                                    <c:if test="${pertanyaan.nilai > 0}"><img src="<c:url value="/resources/default/images/success.jpg"/>"/></c:if>
                                                    <c:if test="${pertanyaan.nilai <= 0}"><img src="<c:url value="/resources/default/images/failure.jpg"/>"/></c:if>
                                                </c:if>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <!-- Carousel nav -->
                                <a class="btn btn-warning" href="#myCarousel" data-slide="prev">
                                    Previous
                                </a>
                                <a class="btn btn-success" href="#myCarousel" data-slide="next">
                                    Next
                                </a>
                                <c:if test="${empty total}">
                                    <input type="submit" id="submit" name="submit" value="Submit" class="btn btn-primary"/>
                                </c:if>
                                <c:if test="${!empty total}">
                                    <input type="submit" id="tryAgain" name="tryAgain" value="Try Again" class="btn btn-default"/>
                                </c:if>
                            </div>
                        </form:form>
                    </div>
                </c:if>
            </div>
        </div>
    </body>
</html>
