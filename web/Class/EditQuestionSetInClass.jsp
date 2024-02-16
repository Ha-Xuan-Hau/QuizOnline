<!DOCTYPE html>
<html lang="en">
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="Entity.Class" %>
    <%@ page import="Entity.QuestionSet" %>
    <%@ page import="java.util.HashMap" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <head>
        <meta charset="utf-8">


        <title> Class </title>
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/Class/images/quiz.png">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css"/>
        <style type="text/css">
            .btn-save {
                background-color: #f5f5f5;
                color: #333333;
            }

            .btn-save:hover {
                background-color: #cccccc;
            }

            h1,h2,h3{
                text-align: center;
                color: white;
            }
            li{
                color: #2c3e50;
                font-size: 18px;
                line-height: 30px;
                text-align: justify;
                letter-spacing: 1px;
            }
            /*SG = style grid*/
            .SG{
                margin: 0;
                padding: 0;
                text-align: center;
                display: flex;
                flex-wrap: wrap;
                justify-content: center;
            }
            .SG .sgLi {
                width: 300px;
                margin: 1%;
            }
            .SG .box{
                padding: 5px;
                height: 150px;
                background-color: #1c1e53;
                border-radius: 5px;
            }
            .SG .sgLi:hover .box {
                background-color: rgba(28, 30, 83, 0.9);
            }

            /*Styles */
            .df{
                list-style-type: disc;
            }
            .s1{
                list-style-type: square;
            }
            .s2{
                list-style-type: circle;
            }
            .s3{
                list-style-type: decimal;
            }
            .s4{
                list-style-type: decimal-leading-zero;
            }
            .s5{
                list-style-type: lower-alpha;
            }
            .s6{
                list-style-type: upper-alpha;
            }
            .s7{
                list-style-type: lower-roman;
            }
            .s8{
                list-style-type: upper-roman;
            }
            .s9{
                list-style-type: lower-greek;
            }
            .s10{
                list-style-type: georgian;
            }
            .s11{
                list-style-type: hebrew;
            }
            .s12{
                list-style-type: hiragana;
            }
            .s13{
                list-style-type: hiragana-iroha;
            }
            .s14{
                list-style-type: katakana;
            }
            .s15{
                list-style-type: katakana-iroha;
            }
            .s16{
                list-style-type: cjk-ideographic;
            }
            .s17{
                list-style-image: url(//goo.gl/L3tqpe);
            }
            .s18{
                list-style: none;
            }
            .s18 li:before{
                content: '';
                width: 20px;
                height: 20px;
                margin-right: 15px;
                display: inline-block;
                background: url(//goo.gl/lcPSVD);
                background-position: 50%;
            }
            .s19{
                list-style: none;
            }
            .s19 li:before{
                content: '\f0a9';
                margin-right: 15px;
                font-family: FontAwesome;
            }

            #toggleContainer {
                background-color: white;
                color: black;
                border: none;
                border-radius: 0px;
                padding: 10px 20px;
                cursor: pointer;
                transition: background-color 0.3s ease;
                outline: none;
            }
            #toggleContainer:hover {
                background-color: #cccccc;
                border-radius: 0px;
            }
            #searchInput,
            button {
                margin: 10px;
                padding: 8px 12px;
                border: none;
                border-radius: 0;
                outline: none;
                font-size: 16px;
                background-color: #f2f2f2;
                color: #333;
            }

            button:hover {
                background-color: #ddd;
            }
            .search-container {
                display: flex;
                align-items: center;
                justify-content: center;
            }

            #searchInput {
            }
        </style>
    </head>
    <body>
        <%@include file="/Home/header.jsp" %> 
        <!--        Show Hide Edit-->
        <button type="button" id="toggleContainer" class="btn btn-info">Show Edit</button>
        <!--Search-->
        <form id="searchForm" action="EditQSClassURL" method="Post">
            <input type="hidden" name="go" value="search">
            <div class="search-container">
                <input type="text" id="searchInput" name="searchQuery" placeholder="Enter search ...">
                <button type="submit">Search</button>
            </div>
        </form>
        <!--        List All Question Set -->
        <ul class="SG" id="questionSetListAll">
            <c:forEach var="questionSetAll" items="${questionSetListAll}">
                <li class="sgLi" onclick="saveSetId('${questionSetAll.getSetId()}')">
                    <div class="box">
                        <h4 style="color: #f5f5f5; margin-left: 10px">${questionSetAll.getTitle()}</h4>
                        <h5 style="color: #f5f5f5; margin-left: 10px">${subjectMapAll[questionSetAll.getSubjectId()].getSubjectName()} (${subjectMapAll[questionSetAll.getSubjectId()].getSubjectCode()})</h5>
                        <br>
                        <h5 style="color: #f5f5f5; margin-left: 10px;">
                            Star Rate : ${questionSetAll.getSetVote()} <img style="margin-bottom: 5px" src="${pageContext.request.contextPath}/Class/images/star.png" width="20px"/> 
                        </h5>
                    </div>
                </li>
            </c:forEach>
        </ul>
        <div id="paginationControls" class="row">
            <button onclick="previousPageAll()" class="btn btn-save" style="outline: none;">Previous</button>
            <span id="currentPageAll" class="pagination-info"></span>
            <button onclick="nextPageAll()" class="btn btn-save" style="outline: none;">Next</button>
        </div>
        <div class="container" style="display: none; position: fixed; bottom: 0; width: 100%; justify-content: center">
            <div class="row">
                <div class="col-xs-12">
                    <div class="panel panel-info">
                        <div class="panel-body">
                            <c:forEach var="questionSet" items="${questionSetList}">
                                <div class="row question-set" id="questionSet_${questionSet.getSetId()}" data-setId="${questionSet.getSetId()}">
                                    <div class="col-xs-2"><img class="img-responsive" src="${pageContext.request.contextPath}/Class/images/subject.jpg" style="margin-bottom: 5px">
                                    </div>
                                    <div class="col-xs-4">
                                        <h4 class="product-name"><strong>${questionSet.getTitle()}</strong></h4><h4><small>${subjectMap[questionSet.getSubjectId()].getSubjectName()}</small></h4>
                                    </div>
                                    <div class="col-xs-6">
                                        <div class="col-xs-6 text-right">
                                            <h6><strong>${subjectMap[questionSet.getSubjectId()].getSubjectCode()}<span class="text-muted"></span></strong></h6>
                                        </div>
                                        <div class="col-xs-4">
                                        </div>
                                        <div class="col-xs-2">    
                                            <div class="col-xs-2">
                                                <button type="button" class="btn btn-link btn-xs delete-question-set" onclick="deleteQuestionSet(${questionSet.getSetId()})">
                                                    <span class="glyphicon glyphicon-trash"></span>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="panel-footer">
                            <div class="row">
                                <div class="col-xs-10 text-right">
                                    <form id="cleanForm" method="POST" action="EditQSClassURL">
                                        <input type="hidden" name="go" value="cleanAll">
                                        <button type="submit" class="btn btn-save" style="outline: none;">
                                            Clean
                                        </button>
                                    </form>
                                </div>
                                <div class="col-xs-1 text-right">
                                    <button type="button" class="btn btn-save" style="outline: none;" onclick="sendDataToServer()">
                                        Save
                                    </button>

                                </div>
                            </div>
                            <div class="panel-footer">
                                <div class="row">
                                    <div class="col-xs-4 text-right">
                                        <!-- Thêm nút Previous -->
                                        <button type="button" class="btn btn-save" onclick="previousPage()" style="outline: none;">Previous</button>
                                    </div>
                                    <div class="col-xs-4 text-center" style="margin-top: 6px">
                                        <span id="currentPage"></span>
                                    </div>                                    
                                    <div class="col-xs-4 text-left">
                                        <!-- Thêm nút Next -->
                                        <button type="button" class="btn btn-save" onclick="nextPage()" style="outline: none;">Next</button>
                                    </div>
                                    <!-- Hiển thị trang hiện tại -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
                                            function deleteQuestionSet(setId) {
                                                if (confirm("Are you sure you want to delete this Set?")) {
                                                    $.ajax({
                                                        url: "EditQSClassURL",
                                                        method: "POST",
                                                        data: {go: "delete", setId: setId},
                                                        success: function (response) {
                                                            // Xóa phần tử câu hỏi khỏi giao diện người dùng
                                                            $("#questionSet_" + setId).remove();
                                                        },
                                                        error: function (xhr, status, error) {
                                                        }
                                                    });
                                                }
                                            }
    </script>   
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
                                                var selectedSetIds = [];

                                                function saveSetId(setId) {
                                                    var exists = false;

                                                    if (selectedSetIds.includes(setId)) {
                                                        alert('Question Set is already selected.');
                                                        return;
                                                    }

                                                    var questionSets = document.querySelectorAll(".question-set");
                                                    questionSets.forEach(function (questionSet) {
                                                        var setIdFromDiv = questionSet.getAttribute("data-setId");
                                                        if (setIdFromDiv === setId) {
                                                            exists = true;
                                                            return;
                                                        }
                                                    });

                                                    if (exists) {
                                                        alert('Question Set is not available.');
                                                        return;
                                                    }

                                                    selectedSetIds.push(setId);
                                                    console.log(selectedSetIds);
                                                    alert('Question Set has been saved.');
                                                    return;
                                                }

                                                function sendDataToServer() {
                                                    $.ajax({
                                                        url: 'EditQSClassURL',
                                                        method: 'POST',
                                                        data: {go: "add", selectedSetIds: selectedSetIds},
                                                        success: function (response) {
                                                            location.reload();
                                                            console.log('Data sent successfully');
                                                        },
                                                        error: function (xhr, status, error) {
                                                            console.error('Error sending data:', error);
                                                        }
                                                    });
                                                }
    </script>


    <script>
        var currentPage = 1; // Trang hiện tại
        var itemsPerPage = 5; // Số lượng mục trên mỗi trang

        function displayPage(page) {
            var startIndex = (page - 1) * itemsPerPage;
            var endIndex = startIndex + itemsPerPage;
            var questionSets = document.getElementsByClassName("question-set");
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
            var totalQuestions = document.getElementsByClassName("question-set").length;
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
        document.getElementById("toggleContainer").addEventListener("click", function () {
            var container = document.querySelector(".container");
            // Nếu container đang ẩn, hiển thị container và thay đổi nội dung của nút
            if (container.style.display === "none") {
                container.style.display = "block";
                this.innerHTML = "Hide Edit";
            } else { // Nếu container đang hiển thị, ẩn container và thay đổi nội dung của nút
                container.style.display = "none";
                this.innerHTML = "Show Edit";
            }
        });
    </script>
    <script>
        var currentPageAll = 1; // Trang hiện tại cho danh sách questionSetListAll
        var itemsPerPageAll = 8; // Số lượng mục trên mỗi trang cho questionSetListAll

        function displayPageAll(page) {
            var startIndex = (page - 1) * itemsPerPageAll;
            var endIndex = startIndex + itemsPerPageAll;
            var questionSetsAll = document.getElementById("questionSetListAll").getElementsByTagName("li");
            for (var i = 0; i < questionSetsAll.length; i++) {
                if (i >= startIndex && i < endIndex) {
                    questionSetsAll[i].style.display = "block";
                } else {
                    questionSetsAll[i].style.display = "none";
                }
            }

            // Hiển thị trang hiện tại
            document.getElementById("currentPageAll").innerText = currentPageAll;
        }

        // Hiển thị trang đầu tiên khi trang được tải
        displayPageAll(currentPageAll);
        function nextPageAll() {
            var totalQuestionsAll = document.getElementById("questionSetListAll").getElementsByTagName("li").length;
            var totalPagesAll = Math.ceil(totalQuestionsAll / itemsPerPageAll);
            if (currentPageAll < totalPagesAll) {
                currentPageAll++;
                displayPageAll(currentPageAll);
            }
        }

        function previousPageAll() {
            currentPageAll--;
            if (currentPageAll < 1) {
                currentPageAll = 1;
            }
            displayPageAll(currentPageAll);
        }
    </script>
</html>