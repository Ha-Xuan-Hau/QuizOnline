<%-- 
    Document   : updatetakeclass
    Created on : Jan 18, 2024, 5:00:52 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "Entity.TakeClass"%>
<%@page import= "java.sql.ResultSet" %>
<%@page import= "Model.DAOTakeClass" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
              TakeClass obj = (TakeClass) request.getAttribute("data");
              ResultSet rs = (ResultSet) request.getAttribute("rs");
              ResultSet rs2 = (ResultSet) request.getAttribute("rs2");
        %>
        <h1>Update TakeClassId = <%=obj.getTakeClassId()%> </h1>
        <form action="takeclassrequest">
            <input type="hidden" name="go" value="updatetakeclass">
            <input type="hidden" name="TakeClassId" value="<%=obj.getTakeClassId()%>"><br>
<!--            StudentAccountId<input type="number" name="StudentAccountId" value="<%=obj.getStudentAccountId()%>"><br>-->
            StudentName<select name="StudentAccountId">
                <%while(rs2.next()){%>
                <option value="<%=rs2.getString(2)%>"><%=rs2.getString(1)%></option>
                <%}%>
            </select><br>
            ClassName<select name="ClassId">
                <%while(rs.next()){%>
                <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
                <%}%>
            </select><br>
            <input type="submit" value="Update">
        </form>
    </body>
</html>
