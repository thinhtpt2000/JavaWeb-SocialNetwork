<%-- 
    Document   : error
    Created on : Sep 21, 2020, 11:17:43 AM
    Author     : ThinhTPT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>
            <c:if test="${not empty requestScope.TITLE}">
                ${requestScope.TITLE}
            </c:if>
            <c:if test="${empty requestScope.TITLE}">
                Resource Not Found
            </c:if>
        </title>
    </head>
    <body>
        <!--HEADER-->
        <jsp:include page="./templates/header.jsp"/>
        <!--CONTENT-->
        <div class="container mt-2">
            <div class="row">
                <div class="card col-sm-10 col-md-6 mx-auto d-flex justify-content-center">
                    <div class="card-body">
                        <h5 class="card-title">
                            <c:if test="${not empty requestScope.TITLE}">
                                ${requestScope.TITLE}
                            </c:if>
                            <c:if test="${empty requestScope.TITLE}">
                                Resource Not Found
                            </c:if>
                        </h5>
                        <p class="card-text">
                            <c:if test="${not empty requestScope.CONTENT}">
                                ${requestScope.CONTENT}
                            </c:if>
                            <c:if test="${empty requestScope.CONTENT}">
                                Sorry, resource is not exist.
                            </c:if>
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <!--IMPORT JS-->
        <jsp:include page="./templates/footer.html" flush="true" />
    </body>
</html>
