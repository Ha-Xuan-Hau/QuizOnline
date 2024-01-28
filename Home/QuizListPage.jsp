<%-- 
    Document   : QuizListPage
    Created on : Jan 20, 2024, 5:05:37 PM
    Author     : Asus
--%>
<%--
<%@page pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="utils.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    /* Pagination container */
.d-flex.justify-content-center {
    margin-top: 20px; /* Thêm kho?ng cách t? trên xu?ng */
}

/* Pagination list */
.pagination {
    display: flex;
    list-style: none;
    padding: 0;
}

/* Pagination item */
.page-item {
    margin: 0 5px; /* Thêm kho?ng cách gi?a các trang */
}

/* Pagination link */
.page-link {
    display: block;
    padding: 10px 15px;
    background-color: #f8f9fa; /* Màu n?n */
    border: 1px solid #dee2e6; /* Vi?n */
    color: #495057; /* Màu ch? */
    text-decoration: none;
    border-radius: 5px; /* ???ng vi?n cong */
}

/* Active page */
.page-item.active .page-link {
    background-color: #007bff; /* Màu n?n c?a trang ?ang active */
    color: #fff; /* Màu ch? c?a trang ?ang active */
    border: 1px solid #007bff; /* Vi?n c?a trang ?ang active */
}

    </style>
    <form class="course-pagination">
        <nav aria-label="Page navigation example" class="d-flex justify-content-center">
            <ul class="pagination">
                <li class="page-item"><a class="page-link" href="HomeController?page=${page-1}">Previous</a></li>
                    <c:forEach begin="1" end="${totalPage}" var="i">
                    <li class="page-item ${i == page?"active":""}"><a class="page-link" href="HomeController?page=${i}">${i}</a></li>
                    </c:forEach>
                <li class="page-item"><a class="page-link" href="HomeController?page=${page+1}">Next</a></li>
            </ul>
        </nav>
        
                         
</form>

->