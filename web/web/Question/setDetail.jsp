<%-- 
    Document   : setDetail
    Created on : Jan 26, 2024, 6:17:02 AM
    Author     : hieul
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css"/>

    </head>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            display: flex;
            text-align: center;
            justify-content: center;
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
<script>
    var currentPage = 1;
    var itemsPerPage = 1;

    function displayPage(page) {
        var startIndex = (page - 1) * itemsPerPage;
        var endIndex = startIndex + itemsPerPage;
        var questionSets = document.getElementsByClassName("wrapper");
        for (var i = 0; i < questionSets.length; i++) {
            if (i >= startIndex && i < endIndex) {
                questionSets[i].style.display = "block";
            } else {
                questionSets[i].style.display = "none";
            }
        }
        document.getElementById("currentPage").innerText = currentPage;
    }
    displayPage(currentPage);
    function nextPage() {
        var totalQuestions = document.getElementsByClassName("wrapper").length;
        var totalPages = Math.ceil(totalQuestions / itemsPerPage);
        if (currentPage < totalPages) {
            currentPage++;
            displayPage(currentPage);
        }
    }
    function previousPage() {
        currentPage--;
        if (currentPage < 1) {
            currentPage = 1;
        }
        displayPage(currentPage);
    }
</script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        var checkboxes = document.querySelectorAll('.flip-checkbox');
        checkboxes.forEach(function (checkbox) {
            checkbox.addEventListener('change', function () {
                var checkboxId = this.getAttribute('id');
                var card = document.querySelector('label[for="' + checkboxId + '"] .card');
                if (this.checked) {
                    card.style.transform = 'rotatex(180deg)';
                } else {
                    card.style.transform = 'rotatex(0deg)';
                }
            });
        });
    });
</script>
<script>
    function openForm() {
        document.getElementById("popupForm").style.display = "block";
        document.getElementById("popupBehind").style.display = "block";
    }
    function closeForm() {
        document.getElementById("popupForm").style.display = "none";
        document.getElementById("popupBehind").style.display = "none";
    }
</script>
</html>
