<%-- 
    Document   : displayAllQuesSet
    Created on : Jan 26, 2024, 6:08:15 AM
    Author     : hieul
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "Entity.QuestionSet"%>
<%@page import= "Model.DAOQuestionSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
  <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 20px;
            text-align: center;
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
            font-size: 28px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }

        th {
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
    </style>
</head>
<body>
    <!-- Your existing HTML content -->
    <h1><span style="color: #007BFF;">Set list</span></h1>
    <table border="1">
        <tr>
            <th>SetID</th>
            <th>Title</th>
        </tr>
        <c:forEach items="${data}" var="content">
            <tr>
                <td>${content.getSetId()}</td>
                <td><a href="QuestionSetURL?go=setDetails&SetId=${content.getSetId()}">${content.getTitle()}</a></td>
            </tr>
        </c:forEach>
    </table>
    <h2><a href="addQuesSet.jsp">Add a new Set</a></h2>
</body>
</html>
