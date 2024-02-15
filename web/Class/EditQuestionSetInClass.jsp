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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <style type="text/css">
            .btn-save {
                background-color: #f5f5f5;
                color: #333333;
            }

            .btn-save:hover {
                background-color: #cccccc;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-xs-12">
                    <div class="panel panel-info">
                        <div class="panel-body">
                            <c:forEach var="questionSet" items="${questionSetList}">
                                <div class="row" id="questionSet_${questionSet.getSetId()}">
                                    <div class="col-xs-2"><img class="img-responsive" src="${pageContext.request.contextPath}/Class/images/subject.jpg">
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
                                <hr>
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
                                    <button type="button" class="btn btn-save" style="outline: none;">
                                        Save
                                    </button>
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
</html>