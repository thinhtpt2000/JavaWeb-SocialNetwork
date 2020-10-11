/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function clearReaction() {
    $("#likePost").removeClass("btn-primary");
    $("#dislikePost").removeClass("btn-danger");
    $("#likePost").addClass("btn-outline-primary");
    $("#dislikePost").addClass("btn-outline-danger");
}

function showReactionValues() {
    $.ajax({
        url: 'MainController',
        data: {
            articleId: $("#articleId").val(),
            action: "Show reaction"
        },
        type: "POST",
        success: function (response) {
            clearReaction();
            if (response.type === 1) {
                $("#likePost").removeClass("btn-outline-primary");
                $("#likePost").addClass("btn-primary");
            } else if (response.type === 2) {
                $("#dislikePost").removeClass("btn-outline-danger");
                $("#dislikePost").addClass("btn-danger");
            }
            $("#likeCounter").html(response.likeCounter);
            $("#dislikeCounter").html(response.dislikeCounter);
        }
    });
}

function reactArticle(type) {
    $.ajax({
        url: 'MainController',
        type: 'POST',
        data: {
            articleId: $("#articleId").val(),
            reactionType: type,
            action: "React article"
        },
        success: function (response) {
            if (response.status) {
                showReactionValues();
                showNotification(type + "d", "You " + type.toLowerCase() + "d this post.");
            } else {
                showNotification("Failed", "Your " + type.toLowerCase() + " is not saved.", "text-danger");
            }
        }
    });
}

$("#likePost").on("click", function (event) {
    reactArticle('Like', event.currentTarget.id);
});

$("#dislikePost").on("click", function (event) {
    reactArticle('Dislike', event.currentTarget.id);
});

function getMultipleline(text) {
    return text.replace(/(\n|\r|\r\n)/g, "<br />");
}

function postComment() {
    $.ajax({
        url: 'MainController',
        type: 'POST',
        data: {
            txtComment: $("#txtComment").val(),
            articleId: $("#articleId").val(),
            action: "Post a comment"
        },
        success: function (response) {
            if (response.status) {
                $("#txtComment").val("");
                $("#txtComment").removeClass("is-invalid");
                $("#commentErr").html("");
                showNotification("Commented", "You posted a comment on this article.");
                showCommentValues();
            } else {
                showNotification("Comment failed", "Your comment is not posted.", "text-danger");
                if (response.error) {
                    $("#txtComment").addClass("is-invalid");
                    $("#commentErr").html(response.error);
                } else {
                    $("#txtComment").removeClass("is-invalid");
                    $("#commentErr").html("");
                }
            }
        }
    });
}

function deleteComment(commentId) {
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#6c757d',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: 'MainController',
                data: {
                    commentId: commentId,
                    action: "Delete a comment"
                },
                success: function (response) {
                    if (response.status) {
                        showNotification("Deleted", "You comment is deleted.");
                        showCommentValues();
                    } else {
                        showNotification("Delete failed", "Your comment is not deleted.", "text-danger");
                    }
                }
            });
        }
    });
}

function renderComment( {commentId, userId, content}, fullName = 'No Name') {
    let currentUser = $('#currentUser').val();
    let htmlCommentText =
            '<div class="media mb-4">'
            + '<img class="d-flex mr-3 rounded-circle" src="images/user.png" alt="" width=50/>'
            + '<div class="media-body">'
            + '<h5 class="mt-0">' + fullName + '</h5>'
            + '<div class="comment-content">'
            + getMultipleline(content)
            + '</div>'
            + '</div>';
    if (currentUser === userId) {
        htmlCommentText += '<button class="btn btn-danger d-flex ml-3" type="submit" onclick=deleteComment("' + commentId + '")>'
                + '<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash" fill="currentColor" xmlns="http://www.w3.org/2000/svg">'
                + '<path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>'
                + '<path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>'
                + '</svg>'
                + '</button>';
    }
    htmlCommentText += '</div>';
    return htmlCommentText;
}

function renderPagination(currentPageNum, numOfPage, listPages) {
    let htmlPaginationText =
            '<nav>'
            + '<ul class="pagination justify-content-center">'
            + '<li class="page-item';
    if (currentPageNum === 1) {
        htmlPaginationText += ' disabled';
    }
    htmlPaginationText += '">'
            + '<a class="page-link" href="#">First</a>'
            + '</li>';
    for (let pageNum of listPages) {
        htmlPaginationText += '<li class="page-item';
        if (currentPageNum === pageNum) {
            htmlPaginationText += ' active';
        }
        htmlPaginationText += '">'
                + '<a class="page-link" href="#">' + pageNum + '</a>'
                + '</li>';
    }
    htmlPaginationText += '<li class="page-item';
    if (currentPageNum === numOfPage) {
        htmlPaginationText += ' disabled';
    }
    htmlPaginationText += '">'
            + '<a class="page-link" href="#">Last</a>'
            + '</li>'
            + '</ul>'
            + '</nav>';
    return htmlPaginationText;
}

function showCommentValues(pageInt = 1) {
    $.ajax({
        url: 'MainController',
        data: {
            articleId: $("#articleId").val(),
            page: pageInt,
            action: "Show comment"
        },
        success: function (response) {
            $("#commentList").empty();
            if (typeof (response) === 'object') {
                let commentData = JSON.parse(response.listComments);
                let fullNameData = JSON.parse(response.listFullName);
                let totalComments = response.totalComments;
                let commentContent = totalComments;
                commentContent += totalComments > 1 ? " comments" : " comment";
                $("#totalComments").html(commentContent);
                if (commentData) {
                    for (let comment of commentData) {
                        let commentHtml = renderComment(comment, fullNameData[comment.userId]);
                        $("#commentList").append(commentHtml);
                    }
                }
                let currentPage = response.currentPage;
                let numOfPage = response.numOfPage;
                let listPage = JSON.parse(response.listPage);
                if (numOfPage !== 0) {
                    let pageListHtml = renderPagination(currentPage, numOfPage, listPage);
                    $("#commentList").append(pageListHtml);

                    $(".page-link").on("click", function (event) {
                        event.preventDefault();
                        const page = event.currentTarget.innerHTML;
                        let pageInt = 0;
                        if (page === 'Last') {
                            pageInt = numOfPage;
                        } else if (page === 'First') {
                            pageInt = 1;
                        } else {
                            pageInt = parseInt(page);
                        }
                        showCommentValues(pageInt);
                    });
                }
            }
        }
    });
}

$("#commentForm").on("submit", function (event) {
    event.preventDefault();
    postComment();
});

$("#confirmDelete").on("click", function (event) {
    event.preventDefault();
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#6c757d',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $("#deletePostForm").submit();
        }
    })
});

$(document).ready(function () {
    showReactionValues();
    showCommentValues();
});