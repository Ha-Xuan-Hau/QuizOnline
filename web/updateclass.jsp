<%-- 
    Document   : updateclass
    Created on : Jan 15, 2024, 8:45:18 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "Entity.Class"%>
<%@page import= "java.sql.ResultSet" %>
<%@page import= "Model.DAOClass" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        Class obj = (Class) request.getAttribute("data");
        ResultSet rs = (ResultSet) request.getAttribute("rs");
        %>
        <h1>Update Class ID = <%=obj.getClassId()%></h1>
        <form action="classrequest">
            <input type="hidden" name="go" value="updateclass">
            <input type="hidden" name="ClassId" value="<%=obj.getClassId()%>"><br>
            ClassName<input type="text" name="ClassName" value="<%=obj.getClassName()%>"><br>
<!--            TeacherAccountID<input type="number" name="TeacherAccountId" value="<%=obj.getTeacherAccountId()%>"><br>-->
             TeacherName<select name="TeacherAccountId">
                <%while(rs.next()){%>
                <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
                <%}
                %>
            </select><br>
            CreateDate<input type="text" name="CreateDate" value="<%=obj.getCreateDate()%>"><br>
            <input type="submit" value="Update">
        </form>
    </body>
</html>
