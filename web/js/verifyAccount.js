/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$("#resendEmail").on("click", function (event) {
    event.preventDefault();
    $("#resendForm").submit();
});

$("#verifyForm").on("submit", function (event) {
    event.preventDefault();
    console.log($("#txtCode").val());
    $.ajax({
        url: "MainController",
        type: "POST",
        data: {
            txtEmail: $("#txtEmail").val(),
            txtCode: $("#txtCode").val(),
            action: "Verify"
        },
        success: function (response) {
            console.log(response);
            if (response.status) {
                $("#txtCode").removeClass("is-invalid");
                let timerInterval;
                Swal.fire({
                    title: 'Your account is active!',
                    icon: 'success',
                    html: 'Redirect to login page in <b>5</b> seconds.',
                    timer: 5000,
                    timerProgressBar: true,
                    footer: '<a href="signIn.html">Click here to sign in now</a>',
                    willOpen: () => {
                        Swal.showLoading();
                        timerInterval = setInterval(() => {
                            const content = Swal.getContent();
                            if (content) {
                                const b = content.querySelector('b');
                                if (b) {
                                    b.textContent = parseInt(Swal.getTimerLeft() / 1000);
                                }
                            }
                        }, 1000)
                    },
                    onClose: () => {
                        clearInterval(timerInterval);
                        window.location.replace("signIn.html");
                    }
                });
            } else {
                $("#txtCode").focus();
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Your code may not incorrect. Try again'
                });
            }
        }
    });
});