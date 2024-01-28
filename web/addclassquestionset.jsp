<%-- 
    Document   : addclassquestionset
    Created on : Jan 18, 2024, 8:01:08 PM
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
        <form action="classquestionsetrequest">
            <input type="hidden" name="go" value="add">

            ClassId<input type="number" name="ClassId"><br>

            SetId<input type="number" name="SetId"><br>
            <input type="submit" value="Add">
        </form>
    </body>
</html>
