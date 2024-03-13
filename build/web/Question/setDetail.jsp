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

        .wrapper {
            max-width: 50rem;
            width: 100%;
            margin: 5rem auto;
            justify-content: center;
        }
        [id^="flip_"] {
            display: none;
        }
        .card {
            padding: 0;
            display: block;
            width: 800px;
            height: 400px;
            margin: 0 auto;
            transform-style: preserve-3d;
        }
        [id^="flip_"]:not(:checked) + label .card {
            transform: rotatex(0deg);
            transition: transform 0.25s;
        }
        [id^="flip_"]:checked + label .card {
            transform: rotatex(180deg);
            transition: transform 0.25s;
        }
        .front,
        .back {
            overflow: hidden;
            position: absolute;
            width: 100%;
            height: 100%;
        }
        .front {
            transform: translateZ(2px);
            background: #0277bd;
            border-radius: 10px;
            color: #cddbea;
            border: 2px solid #0a092d;
            text-align: left;
            padding: 34px;
        }
        .back {
            border-radius: 10px;
            transform: translateZ(-2px) rotateX(180deg);
            background: #0277bd;
            border: 2px solid #0a092d;
            text-align: left;
            padding: 34px;
        }
        .btn {
            border-radius: 50%;
            width: 40px;
            height: 40px;
            background-color: #cccccc;
            color: white;
            border: none;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s, color 0.3s, border-color 0.3s;
        }

        .btn:hover {
            background-color: #808387;
            color: #ffffff;
            border: none;
        }
        li {
            list-style-type: none;
        }
        .Popup {
            position: relative;
            text-align: center;
            width: 100%;
        }
        .behind {
            width: 100%;
            height: 100%;
            position: fixed;
            top: 0;
            left: 0;
            background-color: rgba(18, 18, 18, 0.7);
            z-index: 9;
            display: none;
        }
        .formPopup {
            display: none;
            position: fixed;
            left: 50%;
            top: 10%;
            transform: translate(-50%, 10%);
            z-index: 10;
            border-radius: 10px;
        }
        .formContainer {
            width: 500px;
            height: 300px;
            padding: 20px;
            background-color: white;
            border-radius: 10px;
            position: relative;
        }
        .enroll h1 {
            margin: 0;
        }

        .enroll button,.formContainer button {
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 10px 24px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            margin-top: 10px;
        </style>
    </head>
<body>
<%@include file="/Home/header.jsp" %> 
    <!-- Your existing HTML content -->
    <div class="head" style="display: flex; justify-content: center; align-items: center;">
    <div class="enroll" style="width: 800px; display: flex;">
    <h1>${name.getTitle()}</h1>
    <button style="margin-left: auto;" onclick="openForm()">Enroll</button>
    </div>
    </div>
    </div>
        <!--Popup-->
        <div class="Popup">
            <div class="behind" id="popupBehind" onclick="closeForm()"></div>
            <div class="formPopup" id="popupForm"> 
                <form action="/QuizzesOnline/ClassListURL" method="post" class="formContainer" onsubmit="return validateForm()" >
                    <h1 style="text-align: left; font-weight: bold">${name.getTitle()}</h1>
                    <h3 style="text-align: left; margin-top: 20px ">${subject.getSubjectName()} (${subject.getSubjectCode()})</3>
                    <input type="hidden" name="go" value="addClass"> 
                    <ul style="display: flex;margin-top: 20px">
                                <c:if test="${name.getSetVote() == 1}">
                                    <li><img style="vertical-align: middle; width: 30px; float: right; margin : 5px" src="${pageContext.request.contextPath}/Class/images/star.png" alt="Star"></li>
                                    </c:if>
                                    <c:if test="${name.getSetVote() == 2}">
                                    <li><img style="vertical-align: middle; width: 30px; float: right; margin : 5px" src="${pageContext.request.contextPath}/Class/images/star.png" alt="Star"></li>
                                    <li><img style="vertical-align: middle; width: 30px; float: right; margin : 5px" src="${pageContext.request.contextPath}/Class/images/star.png" alt="Star"></li>
                                    </c:if>
                                    <c:if test="${name.getSetVote() == 3}">
                                    <li><img style="vertical-align: middle; width: 30px; float: right; margin : 5px" src="${pageContext.request.contextPath}/Class/images/star.png" alt="Star"></li>
                                    <li><img style="vertical-align: middle; width: 30px; float: right; margin : 5px" src="${pageContext.request.contextPath}/Class/images/star.png" alt="Star"></li>
                                    <li><img style="vertical-align: middle; width: 30px; float: right; margin : 5px" src="${pageContext.request.contextPath}/Class/images/star.png" alt="Star"></li>
                                    </c:if>
                                    <c:if test="${name.getSetVote() == 4}">
                                    <li><img style="vertical-align: middle; width: 30px; float: right; margin : 5px" src="${pageContext.request.contextPath}/Class/images/star.png" alt="Star"></li>
                                    <li><img style="vertical-align: middle; width: 30px; float: right; margin : 5px" src="${pageContext.request.contextPath}/Class/images/star.png" alt="Star"></li>
                                    <li><img style="vertical-align: middle; width: 30px; float: right; margin : 5px" src="${pageContext.request.contextPath}/Class/images/star.png" alt="Star"></li>
                                    <li><img style="vertical-align: middle; width: 30px; float: right; margin : 5px" src="${pageContext.request.contextPath}/Class/images/star.png" alt="Star"></li>
                                    </c:if>
                                    <c:if test="${name.getSetVote() == 5}">
                                    <li><img style="vertical-align: middle; width: 30px; float: right; margin : 5px" src="${pageContext.request.contextPath}/Class/images/star.png" alt="Star"></li>
                                    <li><img style="vertical-align: middle; width: 30px; float: right; margin : 5px" src="${pageContext.request.contextPath}/Class/images/star.png" alt="Star"></li>
                                    <li><img style="vertical-align: middle; width: 30px; float: right; margin : 5px" src="${pageContext.request.contextPath}/Class/images/star.png" alt="Star"></li>
                                    <li><img style="vertical-align: middle; width: 30px; float: right; margin : 5px" src="${pageContext.request.contextPath}/Class/images/star.png" alt="Star"></li>
                                    <li><img style="vertical-align: middle; width: 30px; float: right; margin : 5px" src="${pageContext.request.contextPath}/Class/images/star.png" alt="Star"></li>
                                    </c:if>
                            </ul>
                    <div class="under" style=" display: flex; margin-top: 50px" >
                    <h4>Create by: ${teacher.getTeacherName()}</h4>
                    <button style="margin: 0 0 0 auto; ">Enroll</button>
                    </div>
                </form>
            </div>
        </div>
        <!--End Popup-->
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
        function openForm(){
        document.getElementById("popupForm").style.display = "block";
        document.getElementById("popupBehind").style.display = "block";
        }
        function closeForm() {
        document.getElementById("popupForm").style.display = "none";
        document.getElementById("popupBehind").style.display = "none";
        }
</script>
</html>
