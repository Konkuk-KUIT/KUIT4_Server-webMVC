<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">
<%@ include file="/include/header.jspf" %>
<body>
<%@ include file="/include/navigation.jspf" %>

<div class="container" id="main">
    <h2>Q&A</h2>
    <div class="qna-list">
        <ul class="list">
            <c:forEach var="question" items="${questions}">
                <li>
                    <div class="wrap">
                        <div class="main">
                            <strong class="subject">
                                <!-- todo 1. href수정해야 -->
                                <a href="qna/show?questionId=${question.id}">${question.title}</a>
                            </strong>
                            <div class="auth-info">
                                <i class="icon-add-comment"></i>
                                <span class="time">${question.createdDate}</span>
                                <span class="author">${question.writer}</span>
                            </div>
                            <div class="reply" title="댓글">
                                <i class="icon-reply"></i>
                                <span class="point">${question.countOfAnswer}</span>
                            </div>
                        </div>
                    </div>
                </li>
            </c:forEach>

        </ul>
            </div>
            <div class="col-md-2 qna-write">
                <a href="/qna/form" class="btn btn-primary pull-right" role="button">질문하기</a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/scripts.js"></script>
</body>
</html>