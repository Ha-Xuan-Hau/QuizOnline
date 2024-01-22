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
                background-image: url('${pageContext.request.contextPath}/Class/images/img_backtoschool.png');
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

            <a href="ClassListURL" target="_self" style="border-radius:50% ;" title="Create or join a class"><img style="height:20px; margin: 5px 0px "
                                                                                                                  src="${pageContext.request.contextPath}/Class/images/add.png" alt="alt" /></a>
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
                                    <img style="height:20px" src="${pageContext.request.contextPath}/Class/images/exam_icon.png"
                                         alt="alt" />
                                </a>
                                <a href="#" onclick="showDeleteConfirmation('<%= myclass.getClassId()%>')">
                                    <img style="height:20px"
                                         src="${pageContext.request.contextPath}/Class/images/delete.png" alt="alt" />
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
</html>