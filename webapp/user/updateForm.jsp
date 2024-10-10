<!--jsp-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--jstl-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="ko">
<%@ include file="../include/header.jspf" %>
<body>
<%@ include file="../include/navigation.jspf" %>


<div class="container" id="main">
    <h2>Update User Information</h2>

    <main class="form-signin">
        <!-- 수정된 사용자 정보를 담아 /user/update 로 post 요청 -->
        <form name="update-user" method="post" action="/user/update">
            <!-- userId는 수정 불가 -->
            <div class="form-floating">
                <input type="text" class="form-control" id="userId" name="userId" value="${user.userId}" placeholder="Id" readonly>
                <label for="userId">User Id (Cannot be modified)</label>
            </div>
            <div class="form-floating">
                <input type="password" class="form-control" id="password" name="password" value="${user.password}" placeholder="Password">
                <label for="password">Password</label>
            </div>
            <div class="form-floating">
                <input type="text" class="form-control" id="name" name="name" value="${user.name}" placeholder="Name">
                <label for="name">Name</label>
            </div>
            <div class="form-floating">
                <input type="email" class="form-control" id="email" name="email" value="${user.email}" placeholder="name@example.com">
                <label for="email">Email address</label>
            </div>
            <div style="height:10px;"></div>
            <!-- 수정 제출 버튼 -->
            <button class="w-100 btn btn-lg btn-primary" type="submit">Update</button>
        </form>
    </main>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="../js/scripts.js"></script>
</body>
</html>