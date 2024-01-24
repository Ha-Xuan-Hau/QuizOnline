<%-- 
    Document   : updatesubject
    Created on : Jan 22, 2024, 10:24:50 PM
    Author     : admin
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "Model.DAOSubject" %>
<%@page import= "Entity.Subject"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <form action="subjectcontroller" method="post">
            <input type="hidden" name="go" value="updatesubject">
            <input type="hidden" name="SubjectId" value="${data.getSubjectId()}">
            SubjectCode<input type="text" name="Subjectcode" value="${data.getSubjectCode()}"><br>
            SubjectName<input type="text" name="Subjectname" value="${data.getSubjectName()}"><br>
            <input type="submit" name="submit" value="update">
        </form>


    </body>
</html>
