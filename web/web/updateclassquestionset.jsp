<%-- 
    Document   : updateclassquestionset
    Created on : Jan 18, 2024, 8:05:23 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "Entity.ClassQuestionSet"%>
<%@page import= "java.sql.ResultSet" %>
<%@page import= "Model.DAOClassQuestionSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
               <%
              ClassQuestionSet obj = (ClassQuestionSet) request.getAttribute("data");
              ResultSet rs = (ResultSet) request.getAttribute("rs");
             
        %>
        <h1>
            Update Class SetId = <%=obj.getClassSetId()%>
        </h1>
        <form action="classquestionsetrequest">
            <input type="hidden" name="go" value="updateclassquestionset">
            <input type="hidden" name="ClassSetId" value="<%=obj.getClassSetId()%>"><br>
           ClassName<select name="ClassId">
                <%while(rs.next()){%>
                <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
                <%}%>
            </select><br>
            SetId<input type="text" name="SetId" value="<%=obj.getSetId()%>"><br>
            <input type="submit" value="Update">
            
        </form>
    </body>
</html>
