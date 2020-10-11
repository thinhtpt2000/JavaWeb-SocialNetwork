<%-- 
    Document   : signUp
    Created on : Sep 16, 2020, 10:49:32 PM
    Author     : ThinhTPT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Sign Up</title>
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
                <!-- SIGN UP -->
                <div id="signUpForm" class="card col-sm-6 mx-auto">
                    <article class="card-body">
                        <a href="signIn.html" class="float-right btn btn-outline-success">
                            Sign in
                        </a>
                        <h4 class="card-title mb-4 mt-1">Sign up</h4>
                        <form class="needs-validation" method="POST" action="MainController">
                            <c:set var="errors" value="${requestScope.CREATE_ERROR}" />
                            <div class="form-group">
                                <c:set value="${empty errors.emailInvalid}" var="isEmailValid"/>
                                <c:set value="${not empty errors.emailIsExisted}" var="isEmailExisted"/>
                                <label>Your email</label>
                                <input
                                    name="txtEmail"
                                    class="form-control 
                                    <c:if test="${(not isEmailValid) or (isEmailExisted)}">is-invalid</c:if>
                                    <c:if test="${isEmailValid and (not isEmailExisted)}">is-valid</c:if>
                                        "
                                        placeholder="Email"
                                        type="email"
                                        required
                                        value="${param.txtEmail}"
                                    />
                                <div class="invalid-feedback">
                                    ${errors.emailInvalid}
                                    ${errors.emailIsExisted}
                                </div>
                            </div>
                            <div class="form-group">
                                <c:set value="${empty errors.passwordLengthErr}" var="isValidPassword"/>
                                <label>Your password</label>
                                <input
                                    name="txtPassword"
                                    class="form-control 
                                    <c:if test="${not isValidPassword}">is-invalid</c:if>
                                    <c:if test="${isValidPassword}">is-valid</c:if>
                                        "
                                        placeholder="(e.g 6-30 chars)"
                                        type="password"
                                        required
                                        value="${param.txtPassword}"
                                        />
                                    <div class="invalid-feedback">
                                    ${errors.passwordLengthErr}
                                </div>
                            </div>
                            <div class="form-group">
                                <c:set value="${empty errors.confirmNotMatched}" var="isValidConfirm"/>
                                <label>Confirm password</label>
                                <input
                                    name="txtConfirmPassword"
                                    class="form-control
                                    <c:if test="${isValidPassword}">
                                        <c:if test="${not isValidConfirm}">is-invalid</c:if>
                                        <c:if test="${isValidConfirm}">is-valid</c:if>
                                    </c:if>
                                    "
                                    placeholder="******"
                                    type="password"
                                    required
                                    value="${param.txtConfirmPassword}"
                                    />
                                <div class="invalid-feedback">
                                    ${errors.confirmNotMatched}
                                </div>
                            </div>
                            <div class="form-group">
                                <c:set value="${empty errors.fullNameLengthErr}" var="isValidName"/>
                                <label>Your name</label>
                                <input
                                    name="txtFullName"
                                    class="form-control
                                    <c:if test="${not isValidName}">is-invalid</c:if>
                                    <c:if test="${isValidName}">is-valid</c:if>
                                        "
                                        placeholder="(e.g 2-50 chars)"
                                        type="text"
                                        required
                                        value="${param.txtFullName}"
                                    />
                                <div class="invalid-feedback">
                                    ${errors.fullNameLengthErr}
                                </div>
                            </div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-success btn-block" 
                                       name="action" value="Sign up"/>
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
