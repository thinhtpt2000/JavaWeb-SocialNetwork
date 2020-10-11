<%-- 
    Document   : signIn
    Created on : Sep 16, 2020, 11:27:21 PM
    Author     : ThinhTPT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Sign In</title>
        <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
            integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
            crossorigin="anonymous"
            />
    </head>
    <body>
        <h2 class="text-center my-4">Mini Social Network</h2>
        <div class="container">
            <div class="row">
                <!-- SIGN IN -->
                <div id="signInForm" class="card col-sm-6 mx-auto">
                    <article class="card-body">
                        <a href="signUp.html" class="float-right btn btn-outline-success"> Sign up </a>
                        <h4 class="card-title mb-4 mt-1">Sign in</h4>
                        <form class="needs-validation" method="POST" action="MainController">
                            <c:set var="errorMsg" value="${requestScope.ERR_MSG}" />
                            <div class="form-group">
                                <label>Your email</label>
                                <input
                                    name="txtEmail"
                                    class="form-control 
                                    <c:if test="${not empty errorMsg}">
                                        is-invalid
                                    </c:if>
                                    "
                                    placeholder="Email"
                                    type="email"
                                    required
                                    value="${param.txtEmail}"
                                    />
                            </div>
                            <div class="form-group">
                                <label>Your password</label>
                                <input
                                    name="txtPassword"
                                    class="form-control 
                                    <c:if test="${not empty errorMsg}">
                                        is-invalid
                                    </c:if>"
                                    placeholder="******"
                                    type="password"
                                    required
                                    />
                                <div class="invalid-feedback">
                                    ${errorMsg}
                                </div>
                            </div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-success btn-block" 
                                       name="action" value="Sign in"/>
                            </div>
                        </form>
                    </article>
                </div>
            </div>
        </div>
        <script
            src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"
        ></script>
    </body>
</html>
