<%@include file="index.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div class="col-md-12">
            <c:forEach items="${listRfNilai}" var="rfNilai">
                <form:form method="POST" commandName="rfNilai" class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-xs-2">${rfNilai.description}</label>
                        <div class="col-xs-8">
                            <input type="hidden" name="id" value="${rfNilai.id}" class="form-control"/>
                            <input type="text" name="nilai" value="${rfNilai.nilai}" class="form-control"/>
                        </div>
                        <div class="col-xs-2">
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>
                    </div>
                </form:form>
            </c:forEach>
        </div>
    </body>
</html>
