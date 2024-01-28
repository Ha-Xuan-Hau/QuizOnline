
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="utils.*" %>
<%@page import="Entity.User" %>
<%@page import="Entity.QuestionSet" %>
<%@ page import="Entity.Subject" %>
<%@ page import="java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../assets/css/base.css"/>
        <link rel="stylesheet" href="../assets/css/index.css"/>
        <link rel="stylesheet" href="../assets/css/search.css"/>
        <link rel="stylesheet" href="../assets/css/adminCourseNav.css"/>
        <link rel="stylesheet" href="../assets/css/courseEditorBase.css"/>
        <script src="https://kit.fontawesome.com/4008f7ead4.js" crossorigin="anonymous"></script>
        <script src="/assets/js/base.js"></script>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;500&display=swap" rel="stylesheet">
        <title>OnlineQuiz</title>
        <link rel="icon" href="../assets/img/coc-cham-hoc-logo.png" type="image/gif" sizes="16x16">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
        <link rel="stylesheet" href="../assets/css/sliderStyle3.css" />
        <link rel="stylesheet" href="/CSS/home.css"/>

    </head>
    <style>
  body{
      background-color: white;
   }
.container {
    display: flex;
    justify-content: space-between;
}
.container {
    position: relative;
  }
  
  #prev, #next {
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
  }
  
  #prev {
    left: 10px;
  }
  
  #next {
    right: 0;
  }

.container .direction {
    text-align: center;
}

.container #formList {
    width:1200px;
    max-width: 1000%;
    overflow: auto;
    margin:100px auto 50px;
    scroll-behavior: smooth;
    scroll-snap-type: both;
}

/* .direction{
    text-align: center;
} */
.direction button{
    font-family: cursive;
    font-weight: bold;
    background-color: #ffffff44;
    border:none;
    width:50px;
    height:50px;
    border-radius: 50%;
    transition: 0.5s;
    margin:0 10px;
}
.direction button:hover{
    background-color: #ffffff;
}
.item{
    border-radius: 15px;
    width: 358px;
    height: 180px;
    background-color: #1C1E53;
    overflow: hidden;
    transition: 0.5s;
    margin:10px;
    scroll-snap-align: start;
}
.item .avatar{
    display: block;
    margin:50px auto 10px;
    width:100px;
    height:100px;
    object-fit: cover;
    border-radius: 20px;
    box-shadow: 0 10px 15px #7e878d;
}
.item .content{
    padding:30px;
    font-family: monospace;
    color: white;
}
.item .content table td{
    padding:10px 0;
    border-bottom: 1px solid #AEC0CE;
}
.item .content table td:nth-child(2){
    text-align: right;
}
.item .nameGroup{
    text-align: center;
    border-bottom:none!important;
}
#list{
    display: flex;
    width:max-content;
}
/* #formList{
    width:1280px;
    max-width: 100%;
    overflow: auto;
    margin:100px auto 50px;
    scroll-behavior: smooth;
    scroll-snap-type: both;
} */
#formList::-webkit-scrollbar{
    display: none;
}
@media screen and (max-width: 1024px){
    .item{
        width: calc(33.3vw - 20px);
    }
    .direction{
        display: none;
    }
}
@media screen and (max-width: 768px){
    .item{
        width: calc(50vw - 20px);
    }
    .direction{
        display: none;
    }
}
</style>
    <body>
        <div class="header-container">
            <div class="navbar">
                <div class="bold">
                    <a href="#">

                        <label style="cursor: pointer;">QuizOnline</label>
                    </a>
                </div>
                <nav class="nav-header">
                    <ul>
                        <li><a href="#">HOME</a></li>
                        <li><a href="#">BLOG</a></li>
                        <li><a href="#">QUIZ</a></li>
                    </ul>
                </nav>
                <div class="user-items">
                    <c:if test="${user != null}">
                        <div class="dropdown">
                            <div class="profile-picture"></div>
                            <button class="dropbtn" onclick="toggleDropdown()">
                                Hi, ${user.getFullName()}
                            </button>
                            <br>
                            <span class="role"><c:choose>
                                    <c:when test="${user.getRole() eq 1}">USER</c:when>
                                    <c:when test="${user.getRole() eq 2}">DESIGNER</c:when>
                                    <c:when test="${user.getRole() eq 3}">ADMIN</c:when>
                                    <c:otherwise>UNKNOWN</c:otherwise>
                                </c:choose>
                            </span>
                            <div class="dropdown-content" id="myDropdown">
                                <a class="dropdown-item" href="/profile"><i class="fa-solid fa-user"></i>  Profile</a>
                                <a class="dropdown-item" href="/mycourse"><i class="fa-solid fa-book"></i>  My Course</a>
                                <c:if test="${user.role == '3'}">
                                    <a class="dropdown-item admin-item" href="/admin"><i class="fa-solid fa-pen-to-square"></i> Administration</a>
                                </c:if>
                                <c:if test="${user.role == '2'}">
                                    <a class="dropdown-item admin-item" href="/admin"><i class="fa-solid fa-pen-to-square"></i> Manage Courses</a>
                                </c:if>
                                <a class="dropdown-item" href="/logout"><i class="fa-solid fa-right-from-bracket"></i>  Logout</a>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${user == null}">
                        <div class="button normal"><a href="#"><i class="fa-solid fa-right-to-bracket"></i>  Login</a></div>
                    </c:if>
                </div>
            </div>
        </div>



        <div class="document-center">
<!--            <h2 class="carousel-title">Hot</h2>-->
            <div class="carousel-container">
                <div class="carousel" id="carousel-1" auto-scroll="10000">


                    <section class="carousel-screen">
                        <img src="https://toquoc.mediacdn.vn/280518851207290880/2022/12/22/12-1671683430473740576022.jpg" alt="" />
                        <section class="text-container">
                            <p>MATH</p>
                            <p>Lecturer:LE VAN KIEN </p>
                        </section>
                    </section>
                    </a>

                    <!--These are not screens. They will always be on carousel-->
                    <section class="circle-container">
                        <!--These 'circles' need to match the number of carousel screens-->
                        <c:forEach items="${sliderList}" var="course">
                            <div class="circle"></div>
                        </c:forEach>
                    </section>
                    <div class="left-arrow">
                        <span class="chevron left"></span>
                    </div>
                    <div class="right-arrow">
                        <span class="chevron right"></span>
                    </div>
                </div>
            </div>
        </div>
        
        
<!--         <h2 class="carousel-title">Quiz Least</h2>-->
        <div class="container">
            <div id="formList">
                <div id="list">
                    <c:forEach items="${listS}" var="item">
                    <div class="item">

                        <div class="content">
                            <table width="100%" cellspacing="0">
                                <tr>
                                    <td>${item.getTitle()}</td>
                                    <td>100points</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                        </c:forEach>
                </div>

            </div>
            <div class="direction">
                <button id="prev"><</button>

            </div>
            <div class="direction">
                <button id="next">></button>
            </div>
        </div>
         
        <%@include file="/Home/QuizFinder.jsp" %> 
        <%@include file="/Home/footer.jsp" %>



    </body>
   <script>
    function toggleDropdown() {
        var dropdownContent = document.getElementById("myDropdown");
        dropdownContent.classList.toggle("show");
    }

    window.onclick = function (event) {
        if (!event.target.matches('.dropbtn')) {
            var dropdowns = document.getElementsByClassName("dropdown-content");
            for (var i = 0; i < dropdowns.length; i++) {
                var openDropdown = dropdowns[i];
                if (openDropdown.classList.contains('show')) {
                    openDropdown.classList.remove('show');
                }
            }
        }
    };

    document.getElementById('next').onclick = function () {
        const widthItem = document.querySelector('.item').offsetWidth;
        document.getElementById('formList').scrollLeft += widthItem;
    };
    
    document.getElementById('prev').onclick = function () {
        const widthItem = document.querySelector('.item').offsetWidth;
        document.getElementById('formList').scrollLeft -= widthItem;
    };
    
    // Include the content of imageSlider2.js here if needed
    // You can either copy the code from imageSlider2.js or provide its content directly.

</script>

    <script src="../assets/js/imageSlider2.js"></script>
</html>
