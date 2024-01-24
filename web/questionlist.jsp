<%-- 
    Document   : questionlist
    Created on : Jan 21, 2024, 11:08:19 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import= "Entity.QuestionSet"%>
<%@page import= "Model.DAOQuestionSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Question List</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>a</th>
                    <th>a</th>


                </tr>
            </thead>
            <tbody>
                <c:forEach items="${data}" var="content">
                    <tr>
                        <td>${content.getQuizContent()}</td>
                        <td>${content.getQuizAnswer()}</td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
