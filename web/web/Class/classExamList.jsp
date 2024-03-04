 <%-- 
    Document   : classList
    Created on : Jan 20, 2024, 1:24:13 PM
    Author     : phamg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Entity.Class" %>
<%@ page import="Entity.QuestionSet" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Mobile M   etas -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />                                  
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/Class/images/quiz.png">
        <title> Class </title>
        <!-- bootstrap core css -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Class/css/bootstrap.css" />
        <!-- Custom styles for this template -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css"/>
        <link href="${pageContext.request.contextPath}/Class/css/mainStyle.css" rel="stylesheet" />
        <style>
            .info {
                position: relative;
                height: 400px;
                background-image: url('${pageContext.request.contextPath}/Class/images/img_read.png');
                background-size: cover;
                background-position: center;
                background-repeat: no-repeat;
                text-align: left;
                border-radius: 10px;
                margin: 20px;
            }

            .info h1 {
                position: absolute;
                bottom: 10%;
                margin: 40px;
                margin-bottom: 70px;
            }

            .info h2 {
                position: absolute;
                right: 10px;
                top: 10px;
                font-family: Arial, sans-serif;
                font-size: 16px;
                color: #333;
                background-color: #f0f0f0;
                padding: 5px 10px;
                border-radius:0 10px 0 10px;
                transition: background-color 0.3s;
            }

            .info h2:hover {
                background-color: #ddd;
            }

            .info h5 {
                position: absolute;
                bottom: 5%;
                margin: 40px;
            }

            .question-set-list {
                width: 100%;
                list-style-type: none;
                padding: 0;
                align-items: center;
            }

            .question-set-item {
                background-color: #f9f9f9;
                border: 1px solid #ddd;
                margin: 30px;
                padding: 10px;
                border-radius: 8px;
                height: 70px;
            }

            .question-set-item:hover {
                background-color: rgba(91, 145, 243, 0.1);
                border: 1px solid #ddd;
                margin: 30px;
                padding: 10px;
                border-radius: 8px;
                height: 70px;
            }

            .question-set-item h3 {
                margin: 10px;
                font-size: 20px;
                color: #333;
                line-height: 20px;
            }

            .question-set-item:hover a {
                text-decoration: none;
            }

            .outer-box {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }

            .outer-box a {
                margin-right: 10px;
            }

        </style>
    </head>

    <body class="sub_page">
        <!-- Header sesion-->
        <%@include file="/Home/header.jsp" %> 
        <!-- End Header sesion-->       
        <div class="select_class">

            <a href="ClassDetailURL?classId=${classId}" target="_self" >Practice</a>


            <a href="" target="_self">Exam</a>

            
            <a href="ClassStudentListURL" target="_self">People</a>
        </div>
        <ul class="question-set-list">
            <li>
                <c:if test="${accId == teacher.getAccountId()}">
                    <div class="question-set-item">
                        <a style="display: flex;justify-content: center " href="EditQSClassURL">
                            <img src="${pageContext.request.contextPath}/Class/images/add.png" width="30px" style="margin: 10px" />                            
                        </a>
                    </div>
                </c:if>    
            </li>
            <c:forEach var="questionSet" items="${questionSetList}">
                <li>
                    <div class="question-set-item">                     
                        <a href="${pageContext.request.contextPath}/QuestionSetURL?go=setDetails&SetId=${questionSet.getSetId()}">
                            <h3><img src="${pageContext.request.contextPath}/Class/images/studying.png" width="20px" style="margin-right: 30px ;flex-direction: row " />
                                ${teacher.getTeacherName()} posted "${questionSet.getTitle()}"    
                            </h3>      
                        </a>
                    </div>
                </li>
            </c:forEach>
        </ul>
        <!-- footer section -->

        <!-- footer section -->
    </body>
</html>