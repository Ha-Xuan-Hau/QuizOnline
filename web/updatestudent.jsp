<%-- 
    Document   : updatestudent
    Created on : Jan 18, 2024, 11:57:37 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "Entity.Student"%>
<%@page import= "java.sql.ResultSet" %>
<%@page import= "Model.DAOStudent" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%Student obj = (Student) request.getAttribute("data");%>
        <h1>Update Account Id = <%=obj.getAccountId()%></h1>
        <form action="studentrequest">
            <input type="hidden" name="go" value="updatestudent">
            <input type="hidden" name="AccountId" value="<%=obj.getAccountId()%>"><br>
            StudentName<input type="text" name="StudentName" value="<%=obj.getStudentName()%>"><br>
            Phone<input type="text" name="Phone" value="<%=obj.getPhone()%>"><br>
            DoB<input type="text" name="DoB" value="<%=obj.getDoB()%>"><br>
            <input type="submit" value="Update">
        </form>
    </body>
</html>
