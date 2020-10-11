<%-- 
    Document   : search
    Created on : Sep 19, 2020, 9:44:54 PM
    Author     : ThinhTPT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Mini Social Network</title>
        <link rel="stylesheet" href="css/search.css" />
    </head>
    <body>
        <!-- HEADER -->
        <jsp:include page="./templates/header.jsp" />
        <!-- CONTAINER -->
        <div class="container mt-2">
            <c:if test="${not empty param.txtSearchValue}">
                <c:set var="result" value="${requestScope.LIST_ARTICLES}" />
                <c:if test="${not empty result}">
                    <c:forEach items="${result}" var="dto" varStatus="counter">
                        <c:if test="${(counter.count mod 4) eq 1}">
                            <div class="row my-2">
                            </c:if>
                            <div class="col-sm-3 my-2">
                                <div class="card text-white bg-secondary border-secondary ">
                                    <c:if test="${not empty dto.imageLink}">
                                        <img class="card-img-top" src="${dto.imageLink}"/>
                                    </c:if>
                                    <c:if test="${empty dto.imageLink}">
                                        <div class="card-img-top"></div>
                                    </c:if>
                                    <div class="card-body">
                                        <h5 class="card-title">${dto.title}</h5>
                                        <p class="card-text">
                                            ${dto.description}
                                        </p>
                                        <p class="card-text">
                                            <small class="text-muted">
                                                <fmt:formatDate pattern = "MMM dd, yyyy hh:mm:ss a" 
                                                                value = "${dto.date}" />
                                            </small>
                                        </p>
                                        <c:url var="viewLink" value="MainController">
                                            <c:param name="action" value="ViewPost" />
                                            <c:param name="articleId" value="${dto.id}" />
                                        </c:url>
                                        <a href="${viewLink}" class="btn btn-success">View more</a>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${((counter.count mod 4) eq 0) or (counter.count eq result.size())}">
                            </div>
                        </c:if>
                    </c:forEach>

                    <nav>
                        <ul class="pagination justify-content-center">
                            <c:set value="${requestScope.CURR_PAGE}" var="currentPageNum" />
                            <c:set value="${requestScope.NUM_OF_PAGE}" var="numOfPage" />
                            <c:set value="${requestScope.LIST_PAGES}" var="listPages" />
                            <li class="page-item 
                                <c:if test="${currentPageNum eq 1}">disabled</c:if>">
                                <c:url var="firstPageLink" value="MainController">
                                    <c:param name="txtSearchValue" value="${param.txtSearchValue}" />
                                    <c:param name="action" value="Search" />
                                    <c:param name="page" value="1" />
                                </c:url>
                                <a class="page-link" href="${firstPageLink}">First</a>
                            </li>
                            <c:forEach items="${listPages}" var="pageNum" >
                                <c:url var="pageLink" value="MainController">
                                    <c:param name="txtSearchValue" value="${param.txtSearchValue}" />
                                    <c:param name="action" value="Search" />
                                    <c:param name="page" value="${pageNum}" />
                                </c:url>
                                <li class="page-item 
                                    <c:if test="${pageNum eq currentPageNum}">active</c:if>">
                                    <a class="page-link" href="${pageLink}">${pageNum}</a>
                                </li>
                            </c:forEach>
                            <li class="page-item 
                                <c:if test="${currentPageNum eq numOfPage}">disabled</c:if>">
                                <c:url var="lastPageLink" value="MainController">
                                    <c:param name="txtSearchValue" value="${param.txtSearchValue}" />
                                    <c:param name="action" value="Search" />
                                    <c:param name="page" value="${numOfPage}" />
                                </c:url>
                                <a class="page-link" href="${lastPageLink}">Last</a>
                            </li>
                        </ul> 
                    </nav>
                </c:if>
                <c:if test="${empty result}">
                    <div class="row">
                        <h5 class="col-sm-12 text-center">No post found!</h5>
                    </div>
                </c:if>
            </c:if>
        </div>
        <!--IMPORT JS-->
        <jsp:include page="templates/footer.html" flush="true"/>
    </body>
</html> 
