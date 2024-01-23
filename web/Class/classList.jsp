<%-- 
    Document   : classList
    Created on : Jan 20, 2024, 1:24:13 PM
    Author     : phamg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Entity.Class" %>
<!DOCTYPE html>
<html>

    <head>
        <!-- Basic -->
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- Mobile Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <!-- Site Metas -->
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/Class/images/quiz.png">
        <title> Class </title>
        <!-- bootstrap core css -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Class/css/bootstrap.css" />
        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/Class/css/style.css" rel="stylesheet" />

        <style>
            .box{
                height: 200px;
                align-items: flex-start;
                border: 0.0625rem solid #dadce0;
                border-radius: 10px 10px 0px 0px;
            }
            .detail-box {
                width: 100%;
                background-image: url('${pageContext.request.contextPath}/Class/images/img_read.png');
                background-size: cover;
                background-position: center;
                background-repeat: no-repeat;
                text-align: left;
                border-radius: 10px 10px 0px 0px;
                padding: 10px 0 0 10px;
            }
            .circle {
                width: 70px;
                height: 70px;
                background-color: rgb(132,204,229);
                border-radius: 50%;
                margin: 5px 5px 5px auto;
                text-align: center;
                display: flex;
                justify-content: center;
                align-items: center;
                color: white;
                font-size: 40px;
            }
            .outer-box {
                flex-direction: column;
                justify-content: center;
                background-color: rgb(32,32,33);
                height: 50px;
                border: 0.0625rem solid #dadce0;
                border-top: 0px;
                border-bottom-left-radius: 10px;
                border-bottom-right-radius: 10px;
                padding-left: 10px
            }
            .outer-box img{
                margin: 10px;
            }
            .outer-box img:hover {
                filter: brightness(50%);
            }
            .select_class{
                border-bottom: white solid 1px;
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .select_class a {
                text-decoration: none;
                color: #333;
                padding: 8px 16px;
                display: inline-block;
                margin-right: 10px;
                transition: background-color 0.3s;
            }
            .select_class a:hover {
                background-color: rgb(18,18,18);
                filter: brightness(70%);
            }
            .create_plus {
                background-image:url( ${pageContext.request.contextPath}/Class/images/add.png)

            }
            .current-page {
                color: #3498db;
                border-bottom: solid #3498db;
                border-radius: 0px
            }
            .current-page {
                color: #3498db;
                border-bottom: solid #3498db;
                border-radius: 0px
            }

            .pagination {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }

            .pagination a {
                text-decoration: none;
                color: white;
                padding: 8px 16px;
                display: inline-block;
                margin-right: 5px;
                border-radius: 50%;
                transition: background-color 0.3s;
            }

            .pagination a:hover {
                background-color: rgb(18,18,18);
                color: white;
                filter: brightness(70%);
            }

            .pagination .current-page {
                background-color: rgb(18,18,18);
                color: white;
                filter: brightness(150%);
                border-radius: 50%;
                border-bottom: none;
            }
            .Popup {
                position: relative;
                text-align: center;
                width: 100%;
            }
            .behind {
                width: 100%;
                height: 100%;
                position: fixed;
                top: 0;
                left: 0;
                background-color: rgba(255, 255, 255, 0.7);
                z-index: 9;
                display: none;
            }
            .formPopup {
                display: none;
                position: fixed;
                left: 50%;
                top: 10%;
                transform: translate(-50%, 10%);
                z-index: 10;
                border-radius: 10px;
            }
            .formContainer {
                width: 500px;
                height: 400px;
                padding: 20px;
                background-color: rgb(25,25,25);
                border-radius: 10px;
                position: relative;
                box-shadow: 0 0 10px rgba(207, 201, 201, 0.1);
            }
            .inputGroup {
                display: flex;
                flex-direction: column;
                margin: 20px 10px;
            }

            .inputGroup input {
                height: 60px;
                border-top: none;
                border-right: none;
                border-left: none;
                border-bottom: solid rgb(88, 88, 88) 1px;
                border-top-left-radius: 5px;
                border-top-right-radius: 5px;
                padding: 0 16px;
                font-size: 16px;
            }

            .inputGroup::before {
                content: attr(title);
                position: absolute;
                top: 0;
                left: 16px;
                font-size: 14px;
                color: rgb(88, 88, 88);
                transform: translateY(50%);
                background-color: #fff;
                padding: 0 8px;
            }

            .inputGroup input:focus {
                outline: none;
                box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
            }
            .btn_cancel {
                background-color: rgba(25,25,25,0);
                width: 60px;
                position: absolute;
                bottom: 10px;
                right:  14%;
                border: none;
                margin: 2px;
                color: rgb(128,131,135);
            }

            .btn_add {
                background-color: rgba(25,25,25,0);
                width: 60px;
                position: absolute;
                bottom: 10px;
                right: 1%;
                border: none;
                margin: 2px;
                color: rgb(128,131,135);
            }
            .btn_cancel:active,
            .btn_add:active {
                background-color: rgba(25,25,25,0.5);
                outline: none;
                filter: brightness(150%);
                margin: 2px;
                color: rgb(128,131,135);
            }
            .btn_cancel:hover,
            .btn_add:hover {
                background-color: rgba(25,25,25,0.5);
                outline: none;
                filter: brightness(150%);
                margin: 2px;
                color: rgb(128,131,135);
            }
            .inputGroup {
                position: relative;
                margin-bottom: 20px;
            }

            .inputGroup input {
                width: 100%;
                height: 60px;
                padding: 10px;
                font-size: 16px;
                border: none;
                border-top-left-radius: 5px;
                border-top-right-radius: 5px;
                background-color: #f5f5f5;
            }

            .inputGroup input:focus {
                outline: none;
                border-bottom: 2px solid #3498db;
            }

            .title {
                position: absolute;
                top: 50%;
                left: 16px;
                font-size: 16px;
                color: rgb(88, 88, 88);
                pointer-events: none;
                transition: top 0.3s, font-size 0.3s, color 0.3s;
                transform: translateY(-50%);
            }

            .inputGroup input:placeholder-shown + .title {
                top: 50%;
                font-size: 16px;
                color: rgb(88, 88, 88);
                transform: translateY(-50%);
            }

            .inputGroup input:focus + .title {
                top: 0;
                font-size: 12px;
                color: #3498db;
                transform: translateY(0);
            }

        </style>
    </head>

    <body class="sub_page">
        <!-- Header sesion-->

        <!-- End Header sesion-->       
        <div class="select_class">
            <!-- My Class -->
            <a href="ClassListURL" target="_self" class="current-page">My Class</a>

            <!-- Learn Class -->
            <a href="ClassListURL" target="_self">Learn Class</a>

            <img style="height:20px; margin: 5px 0px " onclick="openForm()"
                 src="${pageContext.request.contextPath}/Class/images/add.png" alt="alt" />
        </div>
        <!-- Class List -->
        <section class="service_section layout_padding">
            <div class="service_container">
                <div class="container">
                    <div class="row">
                        <%
                        String nameTeacher = (String) session.getAttribute("nameTeacher");
                        ArrayList<Class> classList = (ArrayList<Class>) request.getAttribute("data");
                        char firstCharacter = nameTeacher.charAt(0);

                        int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
                        int itemsPerPage = 6; 
                        int startIdx = (currentPage - 1) * itemsPerPage;
                        int endIdx = Math.min(startIdx + itemsPerPage, classList.size());

                        for (int i = startIdx; i < endIdx; i++) {
                            Class myclass = classList.get(i);
                        %>

                        <div class="col-md-4">
                            <div class="box" style="padding: 0px ">
                                <div class="detail-box">
                                    <h5 style="font-size: 1.375rem;">
                                        <a href="">
                                            <%= myclass.getClassName() %>
                                        </a>
                                    </h5>
                                    <p style="font-size: 0.8125rem;">
                                        <%= nameTeacher %>
                                    </p>
                                    <p style="font-size: 0.8125rem;">
                                        Created on:
                                        <%= myclass.getCreateDate() %>
                                    </p>
                                </div>
                                <div class="circle"><%= firstCharacter %></div>
                            </div>
                            <div class="outer-box">
                                <a href="" target="_blank">
                                    <img style="height:20px" src="${pageContext.request.contextPath}/Class/images/increase.png"
                                         title="Core Board"  alt="alt" />
                                </a>
                                <a href="#" onclick="showDeleteConfirmation('<%= myclass.getClassId()%>')">
                                    <img style="height:20px"
                                         src="${pageContext.request.contextPath}/Class/images/delete.png" title="Delete Class" alt="alt" />
                                </a>
                            </div>
                        </div>

                        <%
                        }
                        %>
                    </div>
                </div>
            </div>
        </section>
        <!-- end Class List -->

        <!--Popup-->
        <div class="Popup">
            <div class="behind" id="popupBehind" onclick="closeForm()"></div>
            <div class="formPopup" id="popupForm">    
                <form action="/ClassDetailURL" method="post" class="formContainer">
                    <p style="text-align: left; font-weight: bold">Create a Class</p>
                    <input type="hidden" name="go" value="addClass">
                    <div class="inputGroup">
                        <input type="text" id="className" name="className" placeholder=" " required>
                        <span class="title">Class Name (required)</span>
                    </div>

                    <div class="inputGroup">
                        <input type="text" id="subject" name="subject" placeholder=" " required>
                        <span class="title">Subject</span>
                    </div>

                    <button type="button" class="btn_cancel" onclick="closeForm()">Cancel</button>
                    <button type="submit" class="btn_add">Add</button>
                </form>
            </div>
        </div>
        <!--End Popup-->

        <!-- Pagination -->
        <%
        if (classList.isEmpty()) {
        %>
        <div class="pagination">
            <img src="${pageContext.request.contextPath}/Class/images/EmptyClass.png" alt="alt"/>
        </div>
        <%
        } else {
        %>
        <div class="pagination">
            <%
            int totalPages = (int) Math.ceil((double) classList.size() / itemsPerPage);
            int previousPage = currentPage - 1;
            String firstPageLink = "ClassListURL?page=1"; 
            String previousPageLink = "ClassListURL?page=" + previousPage;
            if (previousPage > 0) {
            %>
            <a href="<%= firstPageLink %>">First</a> 
            <a href="<%= previousPageLink %>"><%= currentPage - 1 %></a>
            <%
            }
            String currentPageLink = "ClassListURL?page=" + currentPage;
            %>
            <a href="<%= currentPageLink %>" class="current-page"><%= currentPage %></a>
            <%
            int nextPage = currentPage + 1;
            String lastPageLink = "ClassListURL?page=" + totalPages; 
            String nextPageLink = "ClassListURL?page=" + nextPage;
            if (nextPage <= totalPages) {
            %>
            <span class="next-page-container">
                <a href="<%= nextPageLink %>"><%= nextPage %></a>
            </span>
            <a href="<%= lastPageLink %>">Last</a> 
            <%
            }
            %>
        </div>
        <%
            }
        %>
        <!-- End Pagination -->


        <!-- footer section -->

        <!-- footer section -->
    </body>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
            function showDeleteConfirmation(classId) {
                Swal.fire({
                    title: 'Delete Class',
                    text: 'Are you sure you want to delete this class?',
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonText: 'Delete',
                    cancelButtonText: 'Cancel',
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.href = "ClassListURL?go=Delete&ClassId=" + classId;
                    }
                });
            }
    </script>

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            var currentPath = window.location.pathname;
            var links = document.querySelectorAll('a');
            links.forEach(function (link) {
                if (link.getAttribute('href') === currentPath) {
                    link.classList.add('current-page');
                }
            });
        });
    </script>

    <script>
        function openForm() {
            document.getElementById("popupForm").style.display = "block";
            document.getElementById("popupBehind").style.display = "block";
        }
        function closeForm() {
            document.getElementById("popupForm").style.display = "none";
            document.getElementById("popupBehind").style.display = "none";
        }
    </script>
</html>