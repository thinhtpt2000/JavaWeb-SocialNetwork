/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
const CLOUD_NAME = 'thinhtpt';
const UPLOAD_PRESET = 'social_network';
const SUB_FOLDER = 'social-network-images-upload';

function resetImage() {
    $("#fileInput").val("");
    $("#previewImageContainer").hide();
    $("#previewImage").html("");
    $("#fileInput").removeClass("is-invalid");
    $("#fileInput").removeClass("is-valid");
    $("#fileErr").html("");
    $("#fileUrl").val("");
}

function uploadFile(file) {
    var url = `https://api.cloudinary.com/v1_1/${CLOUD_NAME}/upload`;
    var xhr = new XMLHttpRequest();
    var fd = new FormData();
    xhr.open('POST', url, true);
    xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');

    // Reset the upload progress bar
    $("#progress-bar").width(0);
    // Clear preview image
    $("#previewImage").html("");

    // Update progress (can be used to show progress indicator)
    xhr.upload.addEventListener("progress", function (e) {
        let progress = Math.round((e.loaded * 100.0) / e.total);
        $("#progress-bar").width(progress + "%");
    });

    xhr.onreadystatechange = function (e) {
        console.log(xhr.readyState);
        if (xhr.readyState === 4 && xhr.status === 200) {
            // File uploaded successfully
            let response = JSON.parse(xhr.responseText);
            
            let url = response.secure_url;
            
            // Create a thumbnail of the uploaded image, with 150px width
            let tokens = url.split('/');
            tokens.splice(-3, 0, 'w_150,c_scale');
            
            let img = new Image(); // HTML5 Constructor
            img.src = tokens.join('/');
            img.alt = response.public_id;
            $("#previewImage").html(img);
            $("#fileUrl").val(url);
        } else if (xhr.readyState === 4 && xhr.status === 0) {
            $("#previewImage").html("Please check your internet!");
        }
    };

    fd.append('upload_preset', UPLOAD_PRESET);
    fd.append('folder', SUB_FOLDER);
    fd.append('tags', 'lab_web'); // Optional tag
    fd.append('file', file);
    xhr.send(fd);
}

$("#fileSelect").on("click", function (event) {
    event.preventDefault();
    if ($("#fileInput")) {
        $("#fileInput").click();
    }
});

$('#fileInput').bind('change', function () {
    if (this.files && this.files[0] && this.files[0].name.toLowerCase().match(/\.(jpg|jpeg|png)$/)) {
        if (this.files[0].size > 2 * 1024 * 1024) { // 2MB
            $("#fileInput").addClass("is-invalid");
            $("#fileErr").html("Size must be less than 2MB");
        } else {
            $("#fileInput").removeClass("is-invalid");
            $("#fileErr").html("");
            $("#previewImageContainer").show();
            uploadFile(this.files[0]);
        }
    } else {
        $("#fileInput").addClass("is-invalid");
        $("#fileErr").html("Your file is not an acceptable image");
    }
});

$("#deleteImage").on("click", function (event) {
    event.preventDefault();
    resetImage();
});

$(document).ready(function () {
    resetImage();
});