<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ko">
<%@ include file="/include/header.jspf"%>
<body>
<%@ include file="/include/navigation.jspf" %>

<div class="container" id="main">
    <header class="qna-header">
        <h2 class="qna-title">${question.title}</h2>
    </header>
    <div class="content-main">
        <article class="article">
            <div class="article-header">
                <div class="article-header-thumb">
                    <img src="../img/picture.jpeg" class="article-author-thumb" alt="">
                </div>
                <div class="article-header-text">
                    <!-- 사용자 프로필 추가 할거면 span -> a 태그로 바꾸고 API 연결 -->
                    <span class="article-author-name">${question.writer}</span>
                    <span class="article-header-time">
                        ${question.formattedCreatedDate}
                    </span>
                </div>
            </div>
            <div class="article-doc">
                ${question.contents}
            </div>
            <div class="article-util">
                <ul class="article-util-list">

                    <c:choose>
                        <c:when test="${not empty sessionScope.user}">
                            <li>
                                <!-- 수정, 삭제 API 연결 필요 -->
                                <a class="link-modify-article" href="/qna/updateForm?questionId=${question.questionId}">수정</a>
                            </li>
                            <li>
                                <!-- 수정, 삭제 API 연결 필요 -->
                                <form class="form-delete" action="/questions/423" method="POST">
                                    <input type="hidden" name="_method" value="DELETE">
                                    <button class="link-delete-article" type="submit">삭제</button>
                                </form>
                            </li>
                            <li>
                                <a class="link-modify-article" href="/">목록</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a class="link-modify-article" href="/">목록</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </article>

        <div class="qna-comment">
            <div class="qna-comment-kuit">
                <p class="qna-comment-count"><strong>${question.countOfAnswer}</strong>개의 의견</p>
                <div class="qna-comment-slipp-articles">
                    <c:forEach items="${answers}" var="answer">
                        <article class="article">
                            <div class="article-header">
                                <div class="article-header-thumb">
                                    <img src="../img/picture.jpeg" class="article-author-thumb" alt="">
                                </div>
                                <div class="article-header-text">
                                    <span class="article-author-name">${answer.writer}</span>
                                    <span class="article-header-time">${answer.formattedCreatedDate}</span>
                                </div>
                            </div>
                            <div class="article-doc comment-doc">
                                    ${answer.contents}
                            </div>
                            <div class="article-util">
                                <ul class="article-util-list">
                                    <c:if test="${sessionScope.user.userId == answer.writer}">
                                        <li>
                                            <form class="delete-answer-form" action="/api/questions/${question.questionId}/answers/${answer.answerId}" method="POST">
                                                <input type="hidden" name="_method" value="DELETE">
                                                <button type="submit" class="link-delete-article">삭제</button>
                                            </form>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>
                        </article>
                    </c:forEach>
                </div>
                <form class="answer-write" method="post" action="/api/questions/${question.questionId}/answers">
                    <div class="form-group" style="padding:14px;">
                        <textarea class="form-control" placeholder="답변을 입력해주세요" name="contents"></textarea>
                    </div>
                    <input type="hidden" name="questionId" value="${question.questionId}">
                    <button class="btn btn-primary pull-right" type="submit">답변하기</button>
                    <div class="clearfix" />
                </form>
            </div>
        </div>
    </div>
</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="../js/scripts.js"></script>
</body>
</html>