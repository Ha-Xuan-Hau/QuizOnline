<%-- 
    Document   : displaytakeclass
    Created on : Jan 18, 2024, 4:05:24 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "Entity.TakeClass"%>
<%@page import= "Model.DAOTakeClass" %>
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
        DAOTakeClass st = new DAOTakeClass();
        Vector<TakeClass> vector = st.getAll();
        
        %>
        <br><a href="addtakeclass.jsp">Add new</a>
        <table border="1">
            <thead>
                <tr>
                    <th>TakeClassId</th>
                    <th>StudentAccountId</th>
                    <th>ClassId</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%for (int i = 0; i < vector.size(); i++) {%>
                <tr>
                    <td><%=vector.get(i).getTakeClassId()%></td>
                    <td><%=vector.get(i).getStudentAccountId()%></td>
                    <td><%=vector.get(i).getClassId()%></td>
                    <td><a href="takeclassrequest?TakeClassId=<%=vector.get(i).getTakeClassId()%>&go=delete">Delete</a></td>
                    <td><a href="takeclassrequest?TakeClassId=<%=vector.get(i).getTakeClassId()%>&go=update">Update</a></td>
                </tr>
                <%}%>
            </tbody>
        </table>


    </body>
</html>
