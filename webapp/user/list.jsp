<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<<<<<<< HEAD
<%@ page import="java.util.Collection" %>
<%@ page import="jwp.model.User"%>
=======
>>>>>>> 71712f932257569f12cd69a1f6d9c4fc2587288d


<!doctype html>
<html lang="ko">
<%@ include file="/include/header.jspf" %>
<body>
<%@ include file="/include/navigation.jspf" %>

<div class="container" id="main">
    <table class="table table-striped">
        <thead class="col-md-12">
        <tr>
            <th class="col-md-3">아이디</th>
            <th class="col-md-3">이름</th>
            <th class="col-md-3">이메일</th>
            <th class="col-md-3">#</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
        <tr>
            <th class="col-md-3">${user.userId}
            </th>
            <th class="col-md-3">${user.name}
            </th>
            <th class="col-md-3">${user.email}
            </th>
            <th class="col-md-3"><a href="/user/updateForm?userId=${user.userId}" class="btn btn-success" role="button">수정</a></th>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<<<<<<< HEAD
        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="/index.html" class="nav-link px-2 link-secondary">Q&A</a></li>
            <li><a href="#" class="nav-link px-2 link-dark">FAQs</a></li>
            <li><a href="/user/userList" class="nav-link px-2 link-dark">User List</a></li>
            <li><a href="#" class="nav-link px-2 link-dark">About</a></li>
        </ul>

        <div class="col-md-3 text-end">
            <a href="/user/login.html" type="button" class="btn btn-outline-primary me-2">Login</a>
            <a href="/user/form.html" type="button" class="btn btn-primary">Sign-up</a>
        </div>
    </header>

    <div class="container" id="main">
        <table class="table table-striped">
            <thead class="col-md-12">
            <tr>
                <th class="col-md-3">아이디</th>
                <th class="col-md-3">이름</th>
                <th class="col-md-3">이메일</th>
                <th class="col-md-3">#</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
            <tr>
                <th class="col-md-3">${user.userId}</th>
                <th class="col-md-3">${user.name}</th>
                <th class="col-md-3">${user.email}</th>
                <th class="col-md-3"><a href="#" class="btn btn-success" role="button">수정</a></th>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="../js/scripts.js"></script>
=======
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="../js/scripts.js"></script>
>>>>>>> 71712f932257569f12cd69a1f6d9c4fc2587288d
</body>
</html>