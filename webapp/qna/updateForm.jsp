<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">
<%@ include file="/include/header.jspf" %>
<body>
<%@ include file="/include/navigation.jspf" %>

<div class="container" id="main">
    <main class="form-qna">
        <form name="update-qna-form" method="post" action="/qna/update">
            <input type="hidden" name="questionId" value="${question.questionId}">

            <div class="form-group">
                <label for="writer">글쓴이</label>
                <input type="text" class="form-control" value="${sessionScope.user.userId}" id="writer" name="writer"
                       readonly/>
            </div>
            <div class="form-group">
                <label for="title">제목</label>
                <input type="text" class="form-control" value="${question.title}" id="title" name="title">
            </div>

            <div class="form-group">
                <label for="contents">내용</label>
                <textarea class="form-control" id="contents" name="contents" rows="5">${question.contents}</textarea>
            </div>
            <button type="submit" class="btn btn-success clearfix pull-right">수정하기</button>
            <div class="clearfix"/>
        </form>
    </main>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="../js/scripts.js"></script>
</body>
</html>