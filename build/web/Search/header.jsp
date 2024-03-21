<%-- 
    Document   : header
    Created on : Jan 20, 2024, 4:53:30 PM
    Author     : Asus
--%>

<%-- any content can be specified here e.g.: --%>
<%@ page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
    .profile-picture {
        float: left;
        width: 40px;
        height: 40px;
        border-radius: 50%;
        background-color: #F1C40F;
        display: flex;
        margin-right: 10px;
    }

    .role {
        font-size: 12px;
        opacity: 0.8;
        font-weight: 300;
    }

</style>

<div class="header-container">
    <div class="navbar">
        <div class="bold">
            <a href="/HomeController">
                <label style="cursor: pointer;">Back to Home</label>
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

</script>
