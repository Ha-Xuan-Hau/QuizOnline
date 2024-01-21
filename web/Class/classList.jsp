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
</head>

<body class="sub_page">
<!-- Header sesion-->


<!-- End Header sesion-->
  <!-- Class List -->

  <section class="service_section layout_padding">
    <div class="service_container">
      <div class="container ">
        <div class="row">
                <%
                 ArrayList<Class> classList = (ArrayList<Class> ) request.getAttribute("data");
                if (classList != null) {
                    for (Class myclass : classList) {
                %>        
            
                <div class="col-md-4 ">
              <div class="box " >
                <div class="detail-box" style="text-align: left">
                <h5>
                  <%= myclass.getClassName() %>
                </h5>
                <p>
                  <%= myclass.getCreateDate() %>
                </p>
                <a href="">                 
                </a>
              </div>
            </div>
          </div>
                <%
                    }
                } else {
                    out.println("Currently not enrolled in any classes!");
                }
                %>           
            
        </div>
        <div class="btn-box">
          <a href="">
            View All
          </a>
        </div>
      </div>
    </div>
  </section>

  <!-- end Class List -->


  <!-- footer section -->

  <!-- footer section -->
</body>

</html>