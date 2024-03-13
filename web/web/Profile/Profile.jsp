<%@ page import="Entity.User" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Hashtable" %>
<%@ page import="Entity.Admin" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="/CSS/profile.css" />
        <title>Profile</title>

        <script>
            function confirmUpdate() {
                return confirm("Are you sure you want to save changes?");
            }
        </script>
    </head>

    <body>


        <div class="container-xl px-4 mt-4">
            <!-- Account page navigation-->
            <nav class="nav nav-borders">
                <a class="nav-link active ms-0"
                   href="https://www.bootdey.com/snippets/view/bs5-edit-profile-account-details" target="__blank">Profile</a>
            </nav>
            <hr class="mt-0 mb-4">
            <div class="row">
                <div class="col-xl-4">
                    <!-- Profile picture card-->
                    <div class="card mb-4 mb-xl-0">
                        <div class="card-header">Profile Picture</div>
                        <div class="card-body text-center">
                            <!-- Profile picture image-->
                            <img class="img-account-profile rounded-circle mb-2" src="http://bootdey.com/img/Content/avatar/avatar1.png"
                                 alt="">
                        </div>
                    </div>
                </div>
                <div class="col-xl-8">
                    <!-- Account details card-->
                    <div class="card mb-4">
                        <div class="card-header">Account Details</div>
                        <div class="card-body">

                            <c:set var="user" value="${u}" />
                            <c:set var="accountId" value="${user.accountId}" />
                            <c:set var="phone" value="${AdminMap[accountId]}" />
                            <c:set var="MyStudent" value="${studentMap[accountId]}"/>
                            <c:set var="Myteacher" value="${teacherMap[accountId]}"/>

                            <form action="UpdateProfile" method="post" onsubmit="return confirmUpdate();">
                                <!-- Form Group (username)-->
                                <div class="mb-3">
                                    <label class="small mb-1" for="inputUsername">Account Id</label>
                                    <input class="form-control" id="inputUsername" type="text" placeholder="Nhập tên người dùng"
                                           name="accId" value="${user.getAccountId()}" readonly>
                                </div>
                                <div class="mb-3">
                                    <label class="small mb-1" for="inputUsername">Tên người dùng</label>
                                    <input class="form-control" id="inputUsername" type="text" placeholder="Nhập tên người dùng"
                                           name="user" value="${user.getUsername()}">
                                </div>

                                <!-- Admin -->
                                <c:if test="${user.getRoleId()==3 }">
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputUsername">Điện thoại</label>
                                        <input class="form-control" id="inputUsername" type="text" placeholder="Nhập số điện thoại"
                                               name="phone" value="${phone}">
                                    </div>
                                     
                                </c:if>
                                
                                <!<!-- Teacher -->
                                <c:if test="${user.getRoleId()==1 }">
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputUsername">Teacher Name</label>
                                        <input class="form-control" id="inputUsername" type="text" placeholder=""
                                               name="tcName" value="${Myteacher.getTeacherName()}">
                                    </div>
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputUsername">Điện thoại</label>
                                        <input class="form-control" id="inputUsername" type="text" placeholder="Nhập số điện thoại"
                                               name="phonee" value="${Myteacher.getPhone()}">
                                    </div>
                                </c:if>
                                <!-- Student -->
                                 <c:if test="${user.getRoleId()==2 }">
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputUsername">Student Name</label>
                                        <input class="form-control" id="inputUsername" type="text" placeholder=""
                                               name="stName" value="${MyStudent.getStudentName()}">
                                    </div>
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputUsername">Điện thoại</label>
                                        <input class="form-control" id="inputUsername" type="text" placeholder="Nhập số điện thoại"
                                               name="phoneee" value="${MyStudent.getPhone()}">
                                        <p>${mess}</p>
                                    </div>
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputUsername">Dob</label>
                                        <input class="form-control" id="inputUsername" type="text" placeholder=""
                                               name="dob" value="${MyStudent.getDoB()}">
                                    </div>
                                </c:if>

                                <!-- Mật khẩu -->
                                <div class="mb-3">
                                    <label class="small mb-1" for="inputUsername">Mật khẩu</label>
                                    <input class="form-control" id="inputEmailAddress" type="text" placeholder="Mật khẩu" name="pass"
                                           value="${user.getPassword()}">
                                </div>

                                <!-- Nhóm biểu mẫu (địa chỉ email)-->
                                <div class="mb-3">
                                    <label class="small mb-1" for="inputEmailAddress">Địa chỉ email</label>
                                    <input class="form-control" id="inputEmailAddress" type="email" name="email"
                                           placeholder="Nhập địa chỉ email" value="${user.getEmail()}" readonly>
                                </div>
                                <div class="mb-3">
                                    <label class="small mb-1" for="inputEmailAddress">Role</label>
                                    <input class="form-control" id="inputEmailAddress" type="email" name="role"
                                           placeholder="Nhập địa chỉ email" value="${user.getRoleId()}" readonly>
                                </div>

                                <!-- Nút Lưu thay đổi-->
                                <button class="btn btn-primary" type="submit">Lưu thay đổi</button>
                            </form>





                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>

</html>
