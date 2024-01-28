<%-- 
    Document   : addclas
    Created on : Jan 14, 2024, 11:35:29 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="classrequest">
            <input type="hidden" name="go" value="add">
            ClassName<input type="text" name="ClassName"><br>
            TeacherAccountId<input type="number" name="TeacherAccountId"><br>
            CreateDate<input type="text" name="CreateDate"><br>
            <input type="submit" value="Add">
            
            
        </form>
    </body>
</html>
