
<%@ page import="Entity.User" %>
<%@ page import="Entity.Admin" %>
<%@ page import="Entity.Teacher" %>
<%@ page import="Entity.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="Entity.Admin" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html >

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="/CSS/profile.css" />
        <!-- font awesome 5.13.1 -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css" />
        <title>Profile</title>

        <script>
            function confirmUpdate() {
                return confirm("Are you sure you want to save changes?");
            }

        </script>
        <script>
            // step 1
            const ipnElement = document.querySelector('#ipnPassword')
            const btnElement = document.querySelector('#btnPassword')

            // step 2
            btnElement.addEventListener('click', function () {
                // step 3
                const currentType = ipnElement.getAttribute('type')
                // step 4
                ipnElement.setAttribute(
                        'type',
                        currentType === 'password' ? 'text' : 'password'
                        )
            })
        </script>
    </head>

    <body>
        <c:set var="accId" value="${sessionScope.acc.getAccountId()}" />
        <div class="container-xl px-4 mt-4">
            <!-- Account page navigation-->
            <nav class="nav nav-borders">
                <a class="nav-link active ms-0"
                   href="HomeController"><- Back to home</a>
            </nav>
            <hr class="mt-0 mb-4">
            <div class="row">
                <div class="col-xl-4">
                    <!-- Profile picture card-->
                    <div class="card mb-4 mb-xl-0">
                        <div class="card-header">Profile Picture</div>
                        <div class="card-body text-center">
                           <!-- Profile picture image-->
                            <c:if test="${data['RoleId'] == 1}">
                                <img class="img-account-profile rounded-circle mb-2" src="https://img.freepik.com/premium-vector/avatar-graduate-student-icon-vector-illustration-flat-style-isolated-white_647193-1752.jpg"
                                     style="width: 315px; height: 315px;"  alt="">
                            </c:if>
                            <c:if test="${data['RoleId'] == 2}">
                                <img class="img-account-profile rounded-circle mb-2" src="https://cdn1.iconfinder.com/data/icons/flat-education-icons-1/512/37-512.png"
                                     style="width: 315px; height: 315px;"  alt="">
                            </c:if>
                            <c:if test="${data['RoleId'] == 3}">
                                <img class="img-account-profile rounded-circle mb-2" src="https://cdn4.iconfinder.com/data/icons/web-design-and-development-1-7/64/25-512.png"
                                     style="width: 315px; height: 315px;"  alt="">
                            </c:if>
                            <a href="/QuizzesOnline/change?username=${acc.getUsername()}">Change Password</a>
                        </div>

                    </div>
                </div>
                <div class="col-xl-8">
                    <!-- Account details card-->
                    <div class="card mb-4">
                        <div class="card-header">Account Details</div>
                        <div class="card-body">

                            <form action="UpdateProfile" method="post" onsubmit="return validateForm();">
                                <!-- Form Group (username)-->
                                <input class="form-control" id="inputAccountId" type="text" placeholder="Nhập tên người dùng"
                                       name="accId" value="${data['AccountId']}" readonly>

                                <div class="mb-3">
                                    <label class="small mb-1" for="inputUsername">User name</label>
                                    <input class="form-control" id="inputUsername" type="text" placeholder="Nhập tên người dùng"
                                           name="user" value="${data['Username']}" readonly>
                                </div>

                                <!-- Admin -->
                                <c:if test="${data['RoleId'] == 3}">
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputAdminName">Admin Name</label>
                                        <input class="form-control" id="inputAdminName" type="text" placeholder=""
                                               name="adName" value="${data['AdminName']}">
                                    </div>
                                </c:if>

                                <!-- Teacher -->
                                <c:if test="${data['RoleId'] == 2}">
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputTeacherName">Teacher Name</label>
                                        <input class="form-control" id="inputTeacherName" type="text" placeholder=""
                                               name="tcName" value="${data['TeacherName']}">
                                    </div>
                                </c:if>

                                <!-- Student -->
                                <c:if test="${data['RoleId'] == 1}">
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputStudentName">Student Name</label>
                                        <input class="form-control" id="inputStudentName" type="text" placeholder=""
                                               name="stName" value="${data['StudentName']}">
                                    </div>

                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputDob">Dob</label>
                                        <input class="form-control" id="inputDob" type="date" placeholder="" name="dob" value="${data['Dob']}">
                                        <span class="form-message text-danger" id="dob-error"></span>
                                    </div>
                                </c:if>

                                <!-- Phone -->
                                <div class="mb-3">
                                    <label class="small mb-1" for="inputPhone">Phone</label>
                                    <input class="form-control" id="inputPhone" type="text" placeholder="Nhập số điện thoại"
                                           name="phone" value="${data['Phone']}">
                                    <span class="form-message text-danger" id="phone-error"></span>
                                </div>

                                <!-- Nhóm biểu mẫu (địa chỉ email)-->
                                <div class="mb-3">
                                    <label class="small mb-1" for="inputEmailAddress">Email</label>
                                    <input class="form-control" id="inputEmailAddress" type="email" name="email"
                                           placeholder="Nhập địa chỉ email" value="${data.get('Email')}" readonly>
                                </div>

                                <!-- Nút Lưu thay đổi-->
                                <button class="btn btn-primary" type="submit">Save</button>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
   <script>
    function validateForm() {
        // Reset error messages
        document.getElementById("dob-error").innerText = "";
        document.getElementById("phone-error").innerText = "";

        // Get the value of Dob field
        var dob = document.getElementById("inputDob").value;
        // Check if Dob field is empty
        if (dob.trim() === "") {
            document.getElementById("dob-error").innerText = "Please enter your date of birth.";
            return false;
        }

        // Convert Dob string to Date object
        var dobDate = new Date(dob);
        // Get current date
        var currentDate = new Date();

        // Check if Dob is greater than current date
        if (dobDate > currentDate) {
            document.getElementById("dob-error").innerText = "Date of birth cannot be in the future.";
            return false;
        }

        // Get the value of Phone field
        var phoneNumber = document.getElementById("inputPhone").value;
        // Check if Phone field is empty
        if (phoneNumber.trim() === "") {
            document.getElementById("phone-error").innerText = "Please enter your phone number.";
            return false;
        }
        // Check if Phone field contains only digits
        if (!/^\d+$/.test(phoneNumber)) {
            document.getElementById("phone-error").innerText = "Phone number must contain digits only.";
            return false;
        }
        // Check the length of Phone number
        if (phoneNumber.length !== 10) {
            document.getElementById("phone-error").innerText = "Phone number must have 10 digits.";
            return false;
        }
        // If all validation conditions are met, allow form submission
        return true;
    }
</script>



</html>

