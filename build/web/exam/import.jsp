<%-- 
    Document   : import
    Created on : Mar 22, 2024, 9:32:52 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="vi">
    <head>
        <link rel="stylesheet" href="assets/css/Importxlsx.css">
    <body>
        <input type="hidden" id="status" value="${status}">
        <div class="container_upload">
            <div class="card">
                <h3>Upload Files</h3>
                <a href="ExcelURL?go=downLoadFile">Download sample</a>
                <div class="drop_box">
                    <header>
                        <h4>Select File here</h4>
                    </header>
                    <p>Files Supported: XLSX</p>
                    <script>
                        function validateFile() {
                            var fileName = document.getElementById("input-file").value;
                            var idxDot = fileName.lastIndexOf(".") + 1;
                            var extFile = fileName.substr(idxDot, fileName.length).toLowerCase();
                            if (extFile == "xlsx") {
                                //TO DO
                            } else {
                                alert("Only xlsx files are allowed!");
                                return false;
                            }
                        }
                    </script>

                    <form id="uploadForm" method="post" action="ExcelURL" enctype="multipart/form-data"
                          onsubmit="return validateFile()">
                        <input id="input-file" type="file" name="file" />
                        <button class="btn btn-sm btn-outline-success float-right" type="submit">
                            Upload
                        </button>
                    </form>
                </div>
            </div>F
        </div>
    </body>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="stylesheet" href="alert/dist/sweetalert.css">
    <script>
        var status = document.getElementById("status").value;
        if (status == "fail") {
            swal("Sorry", "Wrong format!", "error");
        }
    </script>
</html>
