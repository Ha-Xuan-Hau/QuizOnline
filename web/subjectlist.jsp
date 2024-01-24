<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import= "Entity.Subject"%>
<%@page import= "Model.DAOSubject" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Subject List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        h1 {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 10px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        a {
            text-decoration: none;
            color: #333;
        }

        a:hover {
            color: #0066cc;
        }

        .watch-btn, .delete-btn, .add-new-btn, .update-btn {
            display: inline-block;
            padding: 5px 10px;
            margin-left: 10px;
            background-color: #4caf50;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            text-decoration: none;
        }

        .delete-btn {
            background-color: #f44336;
        }
        .add-new-btn{
            margin-left: 90%;

        }
    </style>
</head>
<body>

    <h1>
        Subject List
        
    </h1>
    <a href="addsubject.jsp" class="add-new-btn" >New Subject</a>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Subject Code</th>
                <th>Subject Name</th>
                <th>Action</th>
                <th>Action</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${data}" var="c">
            <tr>
                
                <td>${c.getSubjectId()}</td>
                <td>${c.getSubjectCode()}</td>
                <td>${c.getSubjectName()}</td>
                <td><a href="questionlist.jsp" class="watch-btn">Review</a></td>
                <td><a href="subjectcontroller?go=updatesubject&SubjectId=${c.getSubjectId()}" class="update-btn">Update</a></td>
                <td><a href="subjectcontroller?go=delete&SubjectId=${c.getSubjectId()}" class="delete-btn">Delete</a></td>
               
            </tr>
             </c:forEach>
        </tbody>
    </table>

</body>
</html>
