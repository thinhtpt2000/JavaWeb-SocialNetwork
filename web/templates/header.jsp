<%-- 
    Document   : header
    Created on : Sep 17, 2020, 4:06:54 PM
    Author     : ThinhTPT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
            />
        <link rel="stylesheet" href="css/common.css" />
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-success sticky-top">
            <a class="navbar-brand" href="home.jsp"><strong>Mini Social Network</strong></a>
            <button
                class="navbar-toggler"
                type="button"
                data-toggle="collapse"
                data-target="#navbarNavDropdown"
                aria-controls="navbarNavDropdown"
                aria-expanded="false"
                aria-label="Toggle navigation"
                >
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <form class="form-inline my-2 my-lg-0 ml-auto" action="MainController">
                    <input
                        class="form-control mr-sm-2"
                        type="search"
                        placeholder="Enter your value . . ."
                        aria-label="Search"
                        required
                        name="txtSearchValue"
                        value="${param.txtSearchValue}"
                        />
                    <input class="btn btn-light my-2 my-sm-0" type="submit" name="action" value="Search"/>
                </form>
                <ul class="navbar-nav ml-auto">
                    <c:if test="${not empty sessionScope.FULL_NAME}">
                        <span class="nav-item navbar-text text-light"
                              >Welcome, ${sessionScope.FULL_NAME}!</span
                        >
                        <li class="nav-item">
                            <c:url var="logoutLink" value="/MainController">
                                <c:param name="action" value="Sign out" />
                            </c:url>
                            <a class="nav-link" href="${logoutLink}">Sign out</a>
                        </li>
                    </c:if>
                    <c:if test="${empty sessionScope.FULL_NAME}">
                        <li class="nav-item">
                            <a class="nav-link" href="signIn.html">Sign in</a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </nav>
    </body>
</html>