<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="Entity.User" %>
<%@ page import="Entity.Admin" %>
<%@ page import="Entity.Teacher" %>
<%@ page import="Entity.Student" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
    <table border="1">
        <thead>
            <tr>
                <th>AccountId</th>
                <th>UserName</th>
                <th>StudentName</th>
                <th>TeacherName</th>
                <th>Phone</th>
                <th>Gmail</th>
                <th>Password</th>
                <th>Active</th>
                <th>RoleId</th>
                <th>Update</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${data}">
                <c:set var="accountId" value="${user.accountId}" />
                <c:set var="phone" value="${AdminMap[accountId]}" />
                <c:set var="MyStudent" value="${studentMap[accountId]}"/>
                <c:set var="Myteacher" value="${teacherMap[accountId]}"/>

                <tr>
                    <td>${user.accountId}</td>
                    <td>${user.username}</td>
                    <td>${Myteacher.getTeacherName()}</td>
                    <td>${MyStudent.getStudentName()}</td>
                    <td>${phone}${Myteacher.getPhone()}${MyStudent.getPhone()}</td>
                    <td>${user.email}</td>
                    <td>${user.password}</td>
                    <td>${user.active}</td>
                    <td>${user.roleId}</td>
                    <td><a href="UpdateProfile?sid=${user.accountId}">Update</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
