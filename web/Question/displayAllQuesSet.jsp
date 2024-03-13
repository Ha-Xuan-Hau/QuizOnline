<%-- 
    Document   : displayAllQuesSet
    Created on : Jan 26, 2024, 6:08:15 AM
    Author     : hieul
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "Entity.QuestionSet"%>
<!DOCTYPE html>
<html>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            /*            margin: 10px;*/

            background: #F4F6FC;
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
            font-size: 28px;
            margin-left: 20px;

        }

        table {
            width: 95%;
            border-collapse: collapse;
            margin-top: 20px;
            margin-left: auto;
            margin-right: auto
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;

        }

        th {
            justify-content: center;
            align-items: center;
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        h2 {
            margin-top: 20px;
        }

        a {
            text-decoration: none;
            color: #007BFF;
        }

        a:hover {
            text-decoration: underline;
        }
        .action-container {
            margin-top: 2rem;
            display: flex;
            gap: 0.5rem;
            margin-left: auto;
            margin-right: auto
        }
        .btn-import {
            background: #fcd980;
            border: 1px solid #fcd980;
            color: black;
            padding: 0.5rem 2rem;
            margin: 0.2rem;
            border-radius: 4px;
        }
        .btn-add {
            background: #fcd980;
            border: 1px solid #fcd980;
            color: black;
            padding: 0.5rem 2rem;
            margin: 0.2rem;
            border-radius: 4px;
        }
        .action-container1 {
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 10px;
        }

        .btn-edit {
            margin: 10px;
        }
        .btn-delete {
            margin: 10px;
        }


    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Question set</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.datatables.net/2.0.0/css/dataTables.dataTables.css" />

    </head>
    <body>
        <%@include file="/Home/header.jsp" %> 

        <h1><span>Set list</span></h1>
        <div class="action-container">
            <button type="button" data-bs-toggle="modal" data-bs-target="#exampleModal" style="color: #007BFF; text-decoration: none; font-weight: bold;" class="btn-import">Import</button>
            <a href="QuestionSetURL?go=addNewSet" style="color: #007BFF; text-decoration: none; font-weight: bold;" class="btn-add" >Add new Set</a>

            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Import questions</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div style="display:flex; align-items: center; gap:10px;">
                                <button type="button" class="btn btn-primary" id="chooseFileBtn" >Choose file</button>
                                <div class="spinner-border" role="status"  id="loading" style="display:none;">
                                    <span class="visually-hidden">Loading...</span>
                                </div>    
                                <input type="file" id="questionList" accept=".xls,.xlsx" style="visibility: hidden; opacity: 0;"/>
                            </div>

                            <div style="margin-top:10px">
                                <table id="examplee" class="display" style="width:100%">
                                    <thead>
                                        <tr>
                                            <th>Question</th>
                                            <th>Answer</th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-dark" data-bs-dismiss="modal" id='downloadBtn'>Download example file</button>
                            <button type="button" class="btn btn-primary" id='saveQuestions'>Save changes</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--        <p><a href="importQues.jsp" style="color: #007BFF; text-decoration: none; font-weight: bold;">Import Question</a></p>
                <p><a href="QuestionSetURL?go=addNewSet" style="color: #007BFF; text-decoration: none; font-weight: bold;" >Add a new Set</a></p>
        -->        <table border="1">
            <tr>
                <th>Title</th>
                <th>Edit</th>
            </tr>
            <c:forEach items="${data}" var="content">
                <tr>
<!--                    <td>${content.getSetId()}</td>-->
                    <td><a href="QuestionSetURL?go=setDetails&SetId=${content.getSetId()}" >${content.getTitle()}</a></td>
                    <td>
                        <div class="action-container1">
                            <a class="btn-edit" href="EditQuestionSetURL?setId=${content.getSetId()}"><i class="fa-solid fa-pen-to-square"></i>Edit</a>
                        </div>
                    </td>
                    <!--                    <td>
                                            <div class="action-container1">
                                                <a class="btn-edit" href="EditQuestionSetURL?setId=${content.getSetId()}"><i class="fa-solid fa-pen-to-square"></i></a>
                                                <a class="btn-delete" href="QuestionSetURL?go=deleteSet&setId=${content.getSetId()}"><i class="gg-trash"></i></a>
                                            </div>
                                        </td>-->
                </tr>
            </c:forEach>
        </table>

    </body>
    <link href='https://unpkg.com/css.gg@2.0.0/icons/css/trash.css' rel='stylesheet'>
    <script
        src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
    crossorigin="anonymous"></script>
    <script src="https://cdn.datatables.net/2.0.0/js/dataTables.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            var chooseFileBtn = document.getElementById("chooseFileBtn");
            var questionListInput = document.getElementById("questionList");
            var loading = document.getElementById("loading");
            var saveChangesBtn = document.getElementById("saveQuestions");

            let data = "";

            chooseFileBtn.addEventListener("click", function () {
                questionListInput.click();
            });
            questionListInput.addEventListener("change", function () {
                var file = this.files[0];
                var formData = new FormData();
                formData.append("file", file);

                loading.style.display = "block";

                var xhr = new XMLHttpRequest();
                xhr.open("POST", "uploadFile", true);
                xhr.onload = function () {
                    loading.style.display = "none";

                    if (xhr.status === 200) {
                        let persons = JSON.parse(xhr.responseText);
                        data = persons;
                        if (dataTable === "")
                            renderTable(persons)
                        else {
                            dataTable.clear().rows.add(persons).draw();
                        }

                        questionListInput.value = "";
                    } else {
                        console.error("Request failed with status: " + xhr.status);
                        questionListInput.value = "";
                    }
                };

                xhr.send(formData);
            });

            saveChangesBtn.addEventListener("click", () => {
                if (data === "") {
                    alert("No data");
                } else {
                    var xhr = new XMLHttpRequest();
                    xhr.open("POST", "QuestionSetURL", true);
                    xhr.onload = function () {
                        loading.style.display = "none";

                        if (xhr.status === 200) {
                            alert("Add question successfully");
                            data = "";
                            if (dataTable !== "") {
                                dataTable.clear().draw();
                            }
                        } else {
                            console.error("Request failed with status: " + xhr.status);

                        }
                    };

                    xhr.send(JSON.stringify(data));
                }
            })

        });

        let dataTable = "";
        const renderTable = (persons) => {

            dataTable = $("#examplee").DataTable({
                data: persons,
                searching: false,
                info: false,
                drawCallback: function (settings) {
                    console.log('Table redrawn');
                },
                columnDefs: [{"targets": -1, searchable: false, orderable: false}],
                "columns": [
                    {"data": "question"
                    },
                    {"data": "answer"}

                ]
            });
        }

        document.getElementById("downloadBtn").addEventListener("click", function () {

            var excelFilePath = "./example.xls";

            var downloadAnchor = document.createElement("a");
            downloadAnchor.style.display = "none";
            document.body.appendChild(downloadAnchor);


            downloadAnchor.href = excelFilePath;


            downloadAnchor.download = "example.xls";

            downloadAnchor.click();


            document.body.removeChild(downloadAnchor);
        });
    </script>
</html>
