<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">
<%@ include file="/include/header.jspf"%>
<body>
<%@ include file="/include/navigation.jspf" %>

<div class="container" id="main">
    <main class="form-signin">

        <form name="update-question" method="post" action="/qna/update">

            <input type="hidden" name="questionId" value="${question.questionId}" />

            <div class="form-floating">

                <input type="text" class="form-control" value="${sessionScope.user.userId}" id="writer" name="writer" placeholder="글쓴이" readonly />
                <label for="writer">글쓴이</label>
            </div>
            <div class="form-floating">

                <input type="text" class="form-control" id="title" name="title" value="${question.title}" placeholder="제목" required />
                <label for="title">제목</label>
            </div>
            <div class="form-group" style="padding:10px 0;">

                <textarea class="form-control" id="contents" name="contents" placeholder="내용" rows="10" required>${question.contents}</textarea>
            </div>

            <button class="w-100 btn btn-lg btn-primary" type="submit">수정 완료</button>
        </form>
    </main>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="../js/scripts.js"></script>
</body>
</html>
