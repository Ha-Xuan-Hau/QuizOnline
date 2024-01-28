<%-- 
    Document   : displayclassquestionset
    Created on : Jan 18, 2024, 5:55:40 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "Entity.ClassQuestionSet"%>
<%@page import= "Model.DAOClassQuestionSet" %>
<%@page import= "java.util.Vector" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
                   String m = "";
                   if(request.getAttribute("ms") != null){
                   m = (String)request.getAttribute("ms");
                   }
        %>
        <%=m%>
        <% 
        DAOClassQuestionSet c = new DAOClassQuestionSet();
        Vector<ClassQuestionSet> vector = c.getAll();
        %>
        <br><a href="addclassquestionset.jsp">Add new </a>
        <table border="1">
            <thead>
                <tr>
                    <th>ClassSetID</th>
                    <th>ClassID</th>
                    <th>SetID</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%for (int i = 0; i < vector.size(); i++) {%>
                <tr>
                    <td><%=vector.get(i).getClassSetId()%></td>
                    <td><%=vector.get(i).getClassId()%></td>
                    <td><%=vector.get(i).getSetId()%></td>
                    <td><a href="classquestionsetrequest?ClassSetId=<%=vector.get(i).getClassSetId()%>&go=delete">Delete</a></td>
                    <td><a href="classquestionsetrequest?ClassSetId=<%=vector.get(i).getClassSetId()%>&go=update">Update</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>

    </body>
</html>
