<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">
<%@ include file="/include/header.jspf" %>
<body>
<%@ include file="/include/navigation.jspf" %>

<div class="container" id="main">

    <main class="form-signin">
        <form name="question" method="post" action="/qna/update">
            <input type="hidden" name="questionId" value="${question.questionId}" />
            <div class="form-group">
                <label>작성자</label>
                ${question.writer}
            </div>
            <div class="form-group">
                <label for="title">제목</label>
                <input type="text" class="form-control" id="title" name="title" placeholder="제목" value="${question.title}"/>
            </div>
            <div class="form-group">
                <label for="contents">내용</label>
                <textarea name="contents" id="contents" rows="5" class="form-control">${question.contents}</textarea>
            </div>
            <button type="submit" class="btn btn-success clearfix pull-right">수정하기</button>
            <div class="clearfix" />

        </form>
    </main>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="../js/scripts.js"></script>
</body>
</html>