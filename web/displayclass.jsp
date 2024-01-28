<%-- 
    Document   : displayclass
    Created on : Jan 14, 2024, 11:28:21 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "Entity.Class"%>
<%@page import= "Model.DAOClass" %>
<%@page import= "java.util.Vector" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Display Class</h1>
                 <%
            String m = "";
            if(request.getAttribute("ms") != null){
            m = (String)request.getAttribute("ms");
            }
        %>
        <%=m%>
        <% 
        DAOClass c = new DAOClass();
        Vector<Class> vector = c.getAll();
        %>
        <br><a href="addclass.jsp">Add new </a>
        <table border="1">
            <thead>
                <tr>
                    <th>ClassID</th>
                    <th>ClassName</th>
                    <th>TeacherAccountID</th>
                    <th>CreateDate</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%for (int i = 0; i < vector.size(); i++) {%>
    

                <tr>
                    <td><%=vector.get(i).getClassId()%></td>
                    <td><%=vector.get(i).getClassName()%></td>
                    <td><%=vector.get(i).getTeacherAccountId()%></td>
                    <td><%=vector.get(i).getCreateDate()%></td>
                    <td><a href="classrequest?ClassId=<%=vector.get(i).getClassId()%>&go=delete">Delete</a></td>
                    <td><a href="classrequest?ClassId=<%=vector.get(i).getClassId()%>&go=update">Update</a></td>
               
                </tr>
<%}%>
            </tbody>
        </table>

    </body>
</html>
