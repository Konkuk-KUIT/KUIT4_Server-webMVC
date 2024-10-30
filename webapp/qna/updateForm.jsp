<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">
<%@ include file="/include/header.jspf" %>
<body>
<%@ include file="/include/navigation.jspf" %>

<div class="container" id="main">
    <main class="form-signin">
        <form name="question-update" method="post" action="/qna/update">
            <input type="hidden" name="questionId" value="${question.questionId}">
            <div class="form-floating">
                <input type="text" class="form-control" id="title" name="title" value="${question.title}" required>
                <label for="title">제목</label>
            </div>
            <div class="form-floating">
                <textarea class="form-control" id="contents" name="contents" required>${question.contents}</textarea>
                <label for="contents">내용</label>
            </div>
            <button class="w-100 btn btn-lg btn-primary" type="submit">수정하기</button>
        </form>
    </main>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="../js/scripts.js"></script>
</body>
</html>
