<%--       <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="utils.*" %>
<%@ page import="Entity.QuestionSet" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Admin" %>
<%@page pageEncoding="UTF-8" %>

<style>
    /* Ch?n ph?n container chung */
    .custom-subject {
        border-radius: 15px; 
        display: block;
        width: 100%;
        width: 304px;
        height: 149px;
        background-color: #1c1e53;
        margin-bottom: 15px;
        text-decoration: none;
        border: solid 1px #000000;
    }

    /* Ch?n ph?n header (tiêu ??) */
    .custom-subject > .custom-subject-info {
        background-color: #666666;
        border-bottom: 1px solid #444444;
        border-radius: 10px 10px 0px 0px ;
    }

    /* Ch?n ph?n n?i dung */
    .custom-subject > .custom-subject-info > .custom-subject-title {
        color: #ffffff;
    }

    .custom-subject a.custom-subject {
        background-color: #1C1E53;
        color: white; /* Màu ch? mong mu?n, ? ?ây là tr?ng */
        /* Thêm b?t k? thu?c tính CSS khác n?u c?n thi?t */
    }

    .custom-subject a {
        outline: 2px solid #ff0000; /* Thêm ???ng vi?n màu ?? (ho?c màu b?n mu?n) bên ngoài */
        display: block; /* ??m b?o th? a chi?m toàn b? chi?u ngang c?a cha (div.custom-subject) */
        height: 100%; /* ??m b?o th? a chi?m toàn b? chi?u cao c?a cha (div.custom-subject) */
        text-decoration: none; /* Lo?i b? ???ng g?ch chân m?c ??nh cho liên k?t */
        border-radius: 8px; /* ?? cong c?a ???ng vi?n (?i?u ch?nh giá tr? theo ý mu?n c?a b?n) */
    }

    .custom-subject-info,
    .custom-subject-meta {
        border-radius: 8px; /* ?? cong c?a ???ng vi?n (?i?u ch?nh giá tr? theo ý mu?n c?a b?n) */
    }

    /* Ch?n ph?n thu?c tính c?a tác gi? */
    .custom-subject > .custom-subject-meta > .custom-author-info {
        padding: 10px;
    }

    /* Ch?n hình ?nh avatar */
    .custom-subject > .custom-subject-meta > .custom-author-info > .custom-author-avatar img {
        border-radius: 50%;
    }

    /* Ch?n tên ng??i ??ng và tên tài kho?n */
    .custom-subject > .custom-subject-meta > .custom-author-info > .custom-author-details > .custom-author-name,
    .custom-subject > .custom-subject-meta > .custom-author-info > .custom-author-details > .custom-author-username {
        color: #ffffff;
    }

    /* Ch?n icon tài li?u */
    .custom-subject > .custom-subject-meta > .custom-document-icon {
        width: 8px;
        height: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        opacity: 0.1;
    }
</style>

<div class="course-container container">
    <div class="row">
        <c:forEach var="item" items="${QuestionSet}" varStatus="loopStatus">
        
            <div class="col-md-3">
                <a href="course?id=#" class="custom-subject">
                    <div class="custom-subject-info bg-gray-100 p-3 border-b border-gray-200 dark:border-gray-800 text-white dark:bg-gray-800" style="background-color: #1C1E53; text-align: center;">
                        <p class="custom-subject-title font-bold text-xl min-h-[84px] line-clamp-3 text-gray-700 dark:text-gray-200">${item.getTitle()}</p>
                        <p class="custom-subject-details font-normal text-sm leading-4 lowercase"></p>
                    </div>
                    <div class="custom-subject-meta flex items-center justify-between p-2">
                        <div class="custom-author-info flex items-center justify-center gap-2">
                            <span class="custom-author-avatar relative inline-flex items-center justify-center flex-shrink-0 rounded-full h-8 w-8 text-sm opacity-70"></span>
                            <div class="custom-author-details flex flex-col">
                                <span class="custom-author-name font-medium text-sm"></span>
                                <span class="custom-author-username font-normal text-xs text-gray-500"></span>
                            </div>
                        </div>
                        <span class="custom-document-icon i-heroicons-document-solid w-8 h-8 shadow-lg opacity-10"></span>
                    </div>
                </a>
            </div>

            <!-- Close the row and start a new one after every 3 items -->
            <c:if test="${loopStatus.index % 3 == 2 or loopStatus.last}">
                </div>
                <div class="row">
            </c:if>

        </c:forEach>
    </div>
</div>


-%>