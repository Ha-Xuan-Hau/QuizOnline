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
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Set list</h1>
        <table border="1">
            <tr>
                <th>SetId</th>
                <th>Title</th>
            </tr>
            <c:forEach items="${data}" var="content">
                <tr>
                    <td>${content.getSetId()}</td>
                    <td><a href="QuestionSetURL?go=setDetails&SetId=${content.getSetId()}" >${content.getTitle()}</a></td>
                </tr>
            </c:forEach>
        </table>
        <h2><a href="QuestionSetURL?go=addNewSet">Add a new Set</a></h2>
    </body>
</html>
