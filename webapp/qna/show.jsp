<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">
<%@ include file="/include/header.jspf" %>
<body>
<%@ include file="/include/navigation.jspf" %>

<div class="container" id="main">
    <header class="qna-header">
        <h2 class="qna-title">${question.title}</h2>
    </header>
    <div class="content-main">
        <article class="article">
            <div class="article-header">
                <span class="article-author-name">${question.writer}</span>
                <span class="article-header-time">${question.createdDate}</span>
            </div>
            <div class="article-doc">
                <p>${question.contents}</p>
            </div>

            <c:if test="${user != null && user.userId == question.writer}">
                <a href="/qna/updateForm?questionId=${question.questionId}" class="link-modify-article">수정</a>
                <form class="form-delete" action="/qna/delete?questionId=${question.questionId}" method="POST" style="display:inline;">
                    <button class="link-delete-article" type="submit">삭제</button>
                </form>
            </c:if>
        </article>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="../js/scripts.js"></script>
</body>
</html>
