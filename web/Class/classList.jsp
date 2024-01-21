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
                display: flex;
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
        </style>
    </head>

    <body class="sub_page">
        <!-- Header sesion-->


        <!-- End Header sesion-->
        <!-- Class List -->

        <section class="service_section layout_padding">
            <div class="service_container">
                <div class="container">
                    <div class="row">
                        <%
                        String nameTeacher = (String) session.getAttribute("nameTeacher");
                        ArrayList<Class> classList = (ArrayList<Class> ) request.getAttribute("data");                      
                        char firstCharacter = nameTeacher.charAt(0);
                        if (classList != null) {
                            for (Class myclass : classList) {
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
                                <div class="circle"><%=firstCharacter%></div>
                            </div>
                            <div class="outer-box">
                                <a href=""  target="_blank">
                                    <img style="height:20px"
                                         src="${pageContext.request.contextPath}/Class/images/exam_icon.png" alt="alt"/>
                                </a>
                            </div>
                        </div>

                        <%
                            }
                        } else {
                            out.println("Currently not enrolled in any classes!");
                        }
                        %>           

                    </div>
                </div>
            </div>
        </section>

        <!-- end Class List -->


        <!-- footer section -->

        <!-- footer section -->
    </body>

</html>