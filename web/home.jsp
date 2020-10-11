<%-- 
    Document   : home
    Created on : Sep 17, 2020, 7:48:01 AM
    Author     : ThinhTPT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Mini Social Network</title>
        <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
            integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
            crossorigin="anonymous"
            />
        <style>
            textarea {
                resize: none !important;
            }
        </style>
    </head>
    <body>
        <!-- HEADER -->
        <jsp:include page="./templates/header.jsp" />
        <!-- CONTAINER -->
        <div class="container mt-2">
            <div class="row">
                <div class="card col-lg-9 col-sm-12 mx-auto my-2">
                    <article class="card-body">
                        <h4 class="card-title mb-4 mt-1">Post an article</h4>
                        <form id="postForm" class="needs-validation" method="POST" autocomplete="off">
                            <div class="form-group">
                                <input
                                    id="title"
                                    name="txtTitle"
                                    class="form-control"
                                    placeholder="Your title (max 150 chars)"
                                    type="text"
                                    required
                                    maxlength="150"
                                    />
                                <div class="invalid-feedback" id="titleErr">
                                </div>
                            </div>
                            <div class="form-group">
                                <textarea
                                    id="description"
                                    name="txtDescription"
                                    class="form-control"
                                    placeholder="Your description (max 1000 chars)"
                                    rows="6"
                                    required
                                    maxlength="1000"
                                    ></textarea>
                                <div class="invalid-feedback" id="descriptionErr">
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group custom-file col-md-8 mb-4">
                                    <input id="fileInput" type="file" class="form-control-file" name="txtFile" accept="image/*" style="display:none" >
                                    <a href="#" id="fileSelect">Select an image (.png, .jpg or .jpeg) - Size less than 2MB</a>
                                    <div class="invalid-feedback" id="fileErr">
                                    </div>
                                </div>
                                <div id="previewImageContainer" class="col-md-4">
                                    <div id="progress" class="progress">
                                        <div id="progress-bar" class="progress-bar progress-bar-striped bg-success" role="progressbar"></div>
                                    </div>
                                    <div id="previewImage" class="d-flex justify-content-center align-items-center mt-3">
                                    </div>
                                    <a id="deleteImage" href="#" class="d-flex justify-content-center align-items-center text-danger">Delete image</a>
                                </div>
                            </div>

                            <div class="form-group">
                                <input
                                    id="fileUrl"
                                    name="fileUrl"
                                    class="form-control"
                                    type="hidden"
                                    />
                            </div>

                            <div class="form-group">
                                <input type="submit" class="btn btn-success btn-block" 
                                       name="action" value="Post an article" />
                            </div>
                        </form>
                    </article>
                </div>
                <div id="notiSidebar" class="card col-lg-3 col-sm-12 mx-auto px-0 my-2" style="height: 85vh">
                    <div class="card-header text-success bg-light d-flex align-items-center">
                        <strong>Notifications</strong>
                        <button id="reloadNotiBtn" class="btn text-primary float-right">
                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-arrow-clockwise" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z"/>
                            <path d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z"/>
                            </svg>
                        </button>
                    </div>
                    <div
                        id="containerNoti"
                        class="list-group list-group-flush"
                        style="overflow-y: auto"
                        >
                    </div>
                </div>
            </div>
        </div>
        <!--IMPORT JS-->
        <jsp:include page="templates/footer.html" flush="true"/>
        <script src="js/home.js">
        </script>
        <script src="js/uploadImage.js">
        </script>
        <c:if test="${not empty requestScope.MSG}">
            <script>
                Swal.fire({
                    icon: 'success',
                    title: '${requestScope.MSG}',
                    showConfirmButton: false,
                    timer: 1500
                })
            </script>
        </c:if>
    </body>
</html> 
