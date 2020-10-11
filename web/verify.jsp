<%-- 
Document   : verify
Created on : Sep 24, 2020, 10:27:22 AM
Author     : ThinhTPT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Verify Account</title>
        <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
            integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
            crossorigin="anonymous"
            />
    </head>
    <body>
        <h2 class="text-center  my-4">Mini Social Network</h2>
        <div class="container">
            <div class="row">
                <div class="card col-sm-6 mx-auto">
                    <article class="card-body">
                        <a
                            href="signUp.html"
                            class="float-right btn btn-outline-success ml-2"
                            >
                            Sign up
                        </a>
                        <a href="signIn.html" class="float-right btn btn-outline-success">
                            Sign in
                        </a>
                        <h4 class="card-title mb-4 mt-1">Verify account</h4>
                        <form id="verifyForm">
                            <div class="form-group">
                                <input
                                    id="txtEmail"
                                    name="txtEmail"
                                    class="form-control"
                                    placeholder="Email"
                                    type="hidden"
                                    required
                                    value="${requestScope.VERIFY_EMAIL}"
                                    />
                            </div>
                            <div class="form-group">
                                <label>Your code</label>
                                <input
                                    id="txtCode"
                                    name="txtCode"
                                    class="form-control 
                                    <c:if test="${not empty requestScope.ERR_CODE}" var="invalidCode">
                                        is-invalid
                                    </c:if>"
                                    placeholder="6-digit code"
                                    type="text"
                                    required
                                    maxlength="6"
                                    value="${param.txtCode}"
                                    />
                                <c:if test="${invalidCode}">
                                    <div class="invalid-feedback"></div>
                                </c:if>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-success btn-block">
                                    Verify
                                </button>
                            </div>
                            <p class="text-center">
                                You don't receive code?
                                <a  id="resendEmail" href="#" class="text-primary">Resend code</a>
                                <br/>
                                <span id="resendEmailMsg"></span>
                            </p>
                            <c:if test="${not empty requestScope.SEND_MSG}">
                                <p class="text-center text-success">
                                    ${requestScope.SEND_MSG}
                                </p>
                            </c:if>
                            <c:if test="${not empty requestScope.ERR_MSG}">
                                <p class="text-center text-danger">
                                    ${requestScope.ERR_MSG}
                                </p>
                            </c:if>
                        </form>
                        <form id="resendForm" method="POST" action="MainController">
                            <div class="form-group">
                                <input
                                    id="txtResendEmail"
                                    name="txtEmail"
                                    class="form-control"
                                    type="hidden"
                                    value="${requestScope.VERIFY_EMAIL}"
                                    />
                            </div>
                            <div class="form-group">
                                <input                                     
                                    name="action" 
                                    value="Send email" 
                                    type="hidden" 
                                    class="form-control" 
                                    />
                            </div>
                        </form>
                    </article>
                </div>
            </div>
        </div>
        <jsp:include page="templates/footer.html" flush="true"/>
        <script
            src="js/verifyAccount.js"
        ></script>
    </body>
</html>

