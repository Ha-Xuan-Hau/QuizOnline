<%-- 
    Document   : editExam
    Created on : Feb 26, 2024, 8:59:56 AM
    Author     : ACER
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<c:set var="examId" value="${empty param.examId ? -1 : param.examId}"></c:set>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Edit Exam</title>
            <link href='https://unpkg.com/css.gg@2.0.0/icons/css/trash.css' rel='stylesheet'>
            <style>
                .question{
                    display: flex;
                    align-items: center;
                }
                .choice{
                    display: flex;
                    align-items: center;
                }
                .btn-delete{
                    margin: 10px
                }
                body {
                    background: #F4F6FC;
                }

                .course-editor-main {
                    padding: 2rem;

                }
                input[type=text], select {
                    width: 100%;
                    padding: 12px 20px;
                    margin: 8px 0;
                    display: inline-block;
                    border: 1px solid #ccc;
                    border-radius: 4px;
                    box-sizing: border-box;
                }
                input[type=number], select {
                    width: 100%;
                    padding: 12px 20px;
                    margin: 8px 0;
                    display: inline-block;
                    border: 1px solid #ccc;
                    border-radius: 4px;
                    box-sizing: border-box;
                }
                .time-input-container {
                    display: flex;
                    align-items: center;
                }

                .time-input-container label {
                    margin-right: 0.5em;
                }

                .time-input-container input {
                    width: 6em;
                }

                .time-input-container span {
                    margin-left: 0.2em;
                    margin-right: 0.2em;
                }
                .form-message {
                    font-size: 14px;
                    color: rgb(237, 61, 61);
                    padding: 20px;
                }
            </style>
        </head>
        <body style="overflow-y: hidden">
        <%@include file="/components/headCommon.jspf" %>
        <%@include file="/Home/header.jsp" %> 
        <div class="course-editor-frame">
            <div class="course-editor-title-bar">        
                <h1 class="editor-default-title">
                    <a href="ClassExamListURL">Edit Exam</a>
                </h1>
            </div>
            <main class="course-editor-main" style="overflow-y: auto; max-height: 500px;">
                <form action="EditExamURL" method="post" id="form-1" onsubmit="return validateForm(event)">

                    <input type="hidden" name="go" value="saveExam">
                    <input type="hidden" name="examId" value="${param.examId}">
                    <div>
                        <div class="form-group"> 
                            <label style="width: 100px" for="exam-title">Title</label>
                            <input type="text" id="exam-title" name="ExamTitle" value="${exam.title}" required/>
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <label  style="width: 100px" for="exam-summary">Summary</label>
                            <input type="text" id="exam-summary" name="ExamSummary" value="${exam.summary}"  required/>
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <label  style="width: 100px" for="exam-timer">Timer</label>

                            <div class="time-input-container">
                                <label for="hour">Hour: </label>
                                <input type="number" id="hour" name="hour" min="0" max="23" step="1" value="${fn:substringBefore(exam.timer/3600, '.')}">

                                <label for="minute">Minute: </label>
                                <input type="number" id="minute" name="minute" min="0" max="59" step="1" value="${fn:substringBefore(exam.timer%3600/60, '.')}">

                                <label for="second">Second: </label>
                                <input type="number" id="second" name="second" min="0" max="59" step="1" value="${exam.timer%60}">

                            </div>
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <label style="width: 100px" for="exam-name">Start Date</label>
                            <input type="datetime" id="exam-start" name="ExamStart" value="${fn:substringBefore(exam.startDate, ' ')}" placeholder="YYYY/MM/DD" required/>
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <label  style="width: 100px" for="exam-duration">End Date</label>
                            <input type="datetime" id="exam-end" name="ExamEnd" value="${fn:substringBefore(exam.endDate, ' ')}" placeholder="YYYY/MM/DD" required/><br/>
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <label style="width: 100px" for="exam-score">Score</label>
                            <input type="number" id="exam-score" name="ExamScore" value="${exam.score}" required/>  
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <label style="width: 100px" for="exam-taking-time">Taking Timers</label>
                            <input type="number" id="exam-taking-time" name="ExamTakingTimers" value="${exam.takingTimes}" required/>
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <label  style="width: 100px" for="exam-permission">Permission</label>
                            <select name="permission " class="form-select">
                                <option value="false" ${exam.permission==false?"selected":""}>unavailable</option>
                                <option value="true" ${exam.permission==true?"selected":""}>available</option>
                            </select>
                            <span class="form-message"></span>
                        </div>
                        <div style="border:#ccc 1px solid; padding:10px">
                            <ul>
                                <c:forEach var="question" items="${questions}" varStatus="questionStatus">
                                    <li style="border:#ccc 1px solid; padding:10px; margin: 10px">
                                        <label for="exam-question">Question: ${questionStatus.count}</label>

                                        <div class="question">
                                            <input type="text" id="exam-question" name="ExamQuestionDetail${questionStatus.index}" value="${question.getContent()}"  required/>
                                            <a class="btn-delete" href="DeleteQuestionExamURL?go=deleteQuestion&examId=${param.examId}&questionId=${question.getQuesId()}"><i class="gg-trash"></i></a>
                                        </div>

                                        <input type="hidden" name="QuestionId${questionStatus.index}" value="${question.getQuesId()}"/>
                                        <a href="ExamAnswerURL?&examId=${param.examId}&questionId=${question.getQuesId()}">Add new Answer</a>
                                        <div style="margin: 10px">
                                            <ul style="border: 10px #000">
                                                <c:forEach var="answer" items="${questionAnswer.get(question.getQuesId())}" varStatus="answerStatus">
                                                    <li style="margin: 10px">
                                                        <label for="choice-description">${answerStatus.count}</label>
                                                        <div class="choice">
                                                            <input type="text" id="choice-description" name="AnswerDetail${questionStatus.index}-${answerStatus.index}" value="${answer.getContent()}"  required/>
                                                            <a class="btn-delete" href="DeleteQuestionExamURL?go=deleteAnswer&examId=${param.examId}&answerId=${answer.getAnswerId()}"><i class="gg-trash"></i></a>
                                                        </div>
                                                        <input type="checkbox" name="IsTrueAnswer${questionStatus.index}-${answerStatus.index}" ${answer.isCorrect() ? "checked":""} value="true" id="choice-radio"+""/>
                                                        <label>True answer</label>
                                                        <input type="hidden" name="AnswerId${questionStatus.index}-${answerStatus.index}" value="${answer.getAnswerId()}"/>
                                                    </li> 
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </li>    
                                </c:forEach>
                            </ul>
                            

                            <a  href="NewQuestionExamURL?examId=${param.examId}"> Add new Question</a>
                        </div>
                    </div>

                        <span id="exam-end-error" class="form-message"></span>
                    <div class="action-container">
                        <input type="submit" name="action" value="Save" class="btn-save"/>
                    </div>
                    <!--                    <div>
                                        </div>-->
                </form>
                <form action="EditExamURL" method="post">
                    <div class="action-container">
                        <input type="hidden" name="go" value="deleteExam">
                        <input type="hidden" name="examId" value="${param.examId}">
                        <input type="button" name="action" value="Delete" class="btn-del" onclick="showDeleteConfirmation(${param.examId})"/>
                    </div>
                </form>
            </main>
        </div>        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="assets/js/register.js"></script>
        <script src="assets/js/base.js"></script>
        <script src="assets/js/editExam.js"></script>
                        
    </body>
</html>
