<%-- 
    Document   : setDetail
    Created on : Jan 26, 2024, 6:17:02 AM
    Author     : hieul
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "Entity.QuestionSet"%>
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
                  .front {
                backface-visibility: hidden;
                position: absolute;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                margin: auto; /* Center the card vertically and horizontally */
                text-align: center; /* Center the text horizontally */
                display: flex;
                justify-content: center; /* Center content horizontally */
                align-items: center; /* Center content vertically */
            }
            .container {
                height: 320px;
                width: 600px;
                margin: auto;
                position: absolute;
                top: 0;
                bottom: 0;
                left: 0;
                right: 0;
            }

            .flip-container {
                perspective: 1000px;
                margin: 10px;
                float: left;
                cursor: pointer;
            }

            .flippable {
                transition: 0.5s;
                transform-style: preserve-3d;
                position: relative;
            }

            .flipme {
                transform: rotateX(180deg);
            }

            .flip-container,
            .front,
            .back {
                width: 600px; /* Increase width */
                height: 300px; /* Increase height */
                line-height: 50px; /* Match height for vertical centering */
                text-align: center;
                border-radius: 5px;
                vertical-align: middle; /* Align text vertically */
            }

            .front,
            .back {
                backface-visibility: hidden;
/*                position: absolute;*/
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                margin: auto; 
                text-align: center;
                border-radius: 10px;
                border: whitesmoke solid thick;
                font-family: Arial, sans-serif;
            }

            .front i {
                font-size: 20px;
                font-family: verdana;
            }

            .back {
                transform: rotateX(180deg);
                font-size: 18px;
                border: 1px solid;
                border: whitesmoke solid thick;
                color: black;
            }

            .ac-bicycle .front {
                background: whitesmoke;
            }

            .ac-bicycle .back {
                background: whitesmoke;
                border-color: whitesmoke;
            }

            .button-container {
                text-align: center;
                margin-top: 20px;
               
            }

            .button {
                display: inline-block;
                padding: 10px 20px;
                background-color: whitesmoke;
                color: black;
                font-size: 16px;
                border-radius: 10px;
                cursor: pointer;
                transition: background-color 0.3s;
                margin-right: 10px;
            }

            .button:hover {
                background-color: whitesmoke;
            }
            .content-front .p{
                font-size: 20px;
                margin: 0px;
                padding: 0px;
            }          
            #mySelect {
  padding: 10px; /* Khoảng cách giữa nội dung và biên của select */
  font-size: 16px; /* Kích thước chữ của select */
  border: 1px solid #ccc; /* Đường viền của select */
  border-radius: 5px; /* Bo tròn góc của select */
  width: 200px; /* Độ rộng của select */
  background-color: #fff; /* Màu nền của select */
  cursor: pointer; /* Con trỏ chuột khi di chuyển qua select */
  outline: none; /* Loại bỏ đường viền khi select được focus */
}

#mySelect:hover {
  border-color: #999; /* Đổi màu đường viền khi hover qua select */
}

#mySelect:focus {
  border-color: #333; /* Đổi màu đường viền khi select được focus */
}
    .custom-link {
        display: inline-block;
        padding: 10px 20px; /* Khoảng cách nội dung và viền */
        border-radius: 10px; /* Bo tròn góc */
        background-color: whitesmoke; /* Màu nền */
        color: black; /* Màu chữ */
        text-decoration: none; /* Loại bỏ gạch chân */
        margin-right: 10px; /* Khoảng cách bên phải */
    }

    .custom-link:hover {
        background-color: #ccc /* Màu nền khi di chuột qua */
    }
    </style>
</head>
<body>
    <%@include file="/Home/header.jsp" %> 
    <!-- Your existing HTML content -->
    <h1>Set Details</h1>&nbsp; &nbsp; 
   


    <div style="display: flex; ">
        <%
        String go = request.getParameter("SetId");
        if(go.length()!=0){
        %><a href="FlashCardURL?go=flashCard&SetId=<%=go%>" class="custom-link" style="margin-right: 20px; margin-left: 5%;">FLASHCARD</a><%
            }
        %>
        <a href="#" class="custom-link" >Test Exam</a>
            </div>
        <div class="container" >
            <c:forEach items="${question}" var="ques" varStatus="status">
                <div class="flip-container">
                    <div class="flippable ac-bicycle">
                        <div class="front" onclick="this.nextElementSibling.classList.toggle('flipme');">
                            <div class="content-front" style="display: flex; flex-direction: column;">
                                <h2>${ques.getContent()}</h2>
                                <c:forEach items="${content[status.index]}" var="answer">
                                    <p>${answer.getContent()}</p>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="back">
                            <div class="content-back" style="">
                                <c:forEach items="${content[status.index]}" var="answer">
                                    <c:if test="${answer.isCorrect() == true}">
                                        <p class="Correct">${answer.getContent()}</p>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

                    <div class="button-container">
                <div class="button" onclick="previousPage()" id="returnButton"><</div>
                <div class="button" onclick="nextPage()" id="nextButton">></div>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script> $(document).ready(function () {
                        $(".flippable").click(function () {
                            $(this).toggleClass("flipme");
                        });
                    });
        </script>
        <script>
            var currentPage = 1; // Trang hiện tại
            var itemsPerPage = 1; // Số lượng mục trên mỗi trang

            function displayPage(page) {
                var startIndex = (page - 1) * itemsPerPage;
                var endIndex = startIndex + itemsPerPage;
                var questionSets = document.getElementsByClassName("flip-container");
                for (var i = 0; i < questionSets.length; i++) {
                    if (i >= startIndex && i < endIndex) {
                        questionSets[i].style.display = "block";
                    } else {
                        questionSets[i].style.display = "none";
                    }
                }

                // Hiển thị trang hiện tại
                document.getElementById("currentPage").innerText = currentPage;
            }

            // Hiển thị trang đầu tiên khi trang được tải
            displayPage(currentPage);
            function nextPage() {
                var totalQuestions = document.getElementsByClassName("flip-container").length;
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
        <div style="margin-top: 25%">
    <table border="1">
        <tr>
            <th>Question</th>
            <th>Content</th>
        </tr>
        <c:forEach items="${question}" var="ques" varStatus="status">
            <tr>
                <td style="color: #000;">${ques.getContent()}</td>
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
            </div>
</body>
</html>
