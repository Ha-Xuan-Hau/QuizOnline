<%-- 
    Document   : displaystudent
    Created on : Jan 17, 2024, 12:38:18 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "Entity.Student"%>
<%@page import= "Model.DAOStudent" %>
<%@page import= "java.util.Vector" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Display Student</h1>
        <%
String m = "";
if(request.getAttribute("ms") != null){
m = (String)request.getAttribute("ms");
} 
        %>
        <%=m%>
        <%
        DAOStudent st = new DAOStudent();
        Vector<Student> vector = st.getAll();
        
        %>
        <br><a href="addstudent.jsp">Add new</a>
        <table border="1">
            <thead>
                <tr>
                    <th>AccountID</th>
                    <th>StudentName</th>
                    <th>Phone</th>
                    <th>DoB</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%for (int i = 0; i < vector.size(); i++) {%>
                <tr>
                    <td><%=vector.get(i).getAccountId()%></td>
                    <td><%=vector.get(i).getStudentName()%></td>
                    <td><%=vector.get(i).getPhone()%></td>
                    <td><%=vector.get(i).getDoB()%></td>
                    <td><a href="studentrequest?AccountId=<%=vector.get(i).getAccountId()%>&go=delete">Delete</a></td>
                    <td><a href="studentrequest?AccountId=<%=vector.get(i).getAccountId()%>&go=update">Update</a></td>

                </tr>
                <%}%>
            </tbody>
        </table>

    </body>
</html>
