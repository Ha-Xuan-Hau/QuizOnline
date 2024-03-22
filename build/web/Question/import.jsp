<%-- 
    Document   : import
    Created on : Mar 22, 2024, 9:32:52 AM
    Author     : ACER
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="vi">
    <head>
        <link rel="stylesheet" href="assets/css/Importxlsx.css">
    <body>
        <c:set var="firstSubject" value="" />
        <c:forEach var="entry" items="${subjectMap}" varStatus="loop">
            <c:if test="${loop.first}">
                <c:set var="firstSubject" value="${entry.key}" />
            </c:if>
        </c:forEach>
        <input type="hidden" id="status" value="${status}">
        <div class="container_upload">
            <div class="card">
                <h3>Upload Files</h3>
                <a href="QuetionSetImportURL?go=downLoadFile">Download sample</a>
                <label style="width: 100px" for="exam-name">Chooses Subject</label>
<!--                <input type="text" id="exam-name" name="subjectId" value="${set.getSubjectId()}" required/>-->
                <select name="subjectId" style="width: 100px" id="subjectId">
                    <c:forEach var="entry" items="${subjectMap}">
                        <option value="${entry.key}">${entry.value}</option>
                    </c:forEach>
                </select>
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
                        // Thay đổi giá trị của trường ẩn khi phần select thay đổi
                        document.getElementById("subjectId").addEventListener("change", function () {
                            var selectedValue = this.value;
                            document.getElementById("hiddenSubjectId").value = selectedValue;
                        });
                    </script>

                    <form id="uploadForm" method="post" action="QuetionSetImportURL" enctype="multipart/form-data"
                          onsubmit="return validateFile()">
                        <input id="input-file" type="file" name="file" />
                        <input type="hidden" id="hiddenSubjectId" name="subjectId" value="${firstSubject}"/>
                        <button class="btn btn-sm btn-outline-success float-right" type="submit">
                            Upload
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="stylesheet" href="alert/dist/sweetalert.css">
    <script>
                              var status = document.getElementById("status").value;
                              if (status == "fail") {
                                  swal("Sorry", "Email Has Not Registered", "error");
                              }
    </script>
</html>
