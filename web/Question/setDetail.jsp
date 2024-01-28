<%-- 
    Document   : setDetail
    Created on : Jan 26, 2024, 6:17:02 AM
    Author     : hieul
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
    li {
        list-style-type: none;
    }

</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/search.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/adminCourseNav.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/courseEditorBase.css"/>
        <script src="https://kit.fontawesome.com/4008f7ead4.js" crossorigin="anonymous"></script>
        <script src="/assets/js/base.js"></script>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;500&display=swap" rel="stylesheet">
        <title>OnlineQuiz</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sliderStyle3.css" />
        <link rel="stylesheet" href="/CSS/home.css"/>
    </head>
<style>
        body {
            font-family: 'Arial', sans-serif;
/*            margin: 20px;*/
            text-align: center;
        }

        h1 {
            color: #333;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
        }

        li {
            margin-bottom: 5px;
            color: #333; /* Default color for list items */
        }

        li.Result {
            font-weight: bold;
            color: #007BFF; /* Blue color for "Result" label */
        }

        li.Correct {
            color: #008000; /* Green color for correct answers */
        }
    </style>
</head>
<body>
     <%@include file="/Home/header.jsp" %> 
    <!-- Your existing HTML content -->
    <h1>Set Details</h1>
    <table border="1">
        <tr>
            <th>Question</th>
            <th>Content</th>
        </tr>
        <c:forEach items="${question}" var="ques" varStatus="status">
            <tr>
                <td style="color: #FF5733;">${ques.getContent()}</td>
                <td>
                    <ul>
                        <c:forEach items="${content[status.index]}" var="answer">
                            <li>${answer.getContent()}</li>
                        </c:forEach>
                        <li class="Result">Result</li>
                        <c:forEach items="${content[status.index]}" var="answer">
                            <c:if test="${answer.isCorrect() == true}">
                                <li class="Correct">${answer.getContent()}</li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
