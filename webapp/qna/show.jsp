<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!doctype html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>KUIT</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/styles.css" rel="stylesheet">
  </head>
  <body>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark" aria-label="Fourth navbar example">
      <div class="container-fluid">
        <a class="navbar-brand" href="/"> KUIT </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample04" aria-controls="navbarsExample04" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
  
        <div class="collapse navbar-collapse" id="navbarsExample04">
          <ul class="navbar-nav me-auto mb-2 mb-md-0">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-bs-toggle="dropdown" aria-expanded="false">Home</a>
              <ul class="dropdown-menu" aria-labelledby="dropdown04">
                <li><a class="dropdown-item" href="#">Profile</a></li>
                <li><a class="dropdown-item" href="#">Settings</a></li>
              </ul>
            </li>
          </ul>
          <form>
            <input class="form-control" type="text" placeholder="Search" aria-label="Search">
          </form>
        </div>
      </div>
    </nav>
    <div class="navbar-default">
        <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
            <a href="/" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
                <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"/></svg>
            </a>

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
    </div>

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
                        <span class="article-author-name">${question.writer}</span>
                        <span class="article-header-time">
                            ${question.createdDate}
                        </span>
                    </div>
                </div>
                <div class="article-doc">
                    <p>${question.contents}</p>
                </div>
                <div class="article-util">
                    <ul class="article-util-list">
                        <c:if test="${sessionScope.user.userId eq question.writer}">
                            <li>
                                <a class="link-modify-article" href="/qna/updateForm?questionId=${question.questionId}">수정</a>
                            </li>
                            <li>
                                <a class="link-modify-article"
                                   href="/qna/delete?questionId=${question.questionId}">삭제</a>
                            </li>
                        </c:if>
                        <li>
                            <a class="link-modify-article" href="../home.jsp">목록</a>
                        </li>
                    </ul>
                </div>
            </article>

            <div class="qna-comment">
                <div class="qna-comment-kuit">
                    <p class="qna-comment-count"><strong>${question.countOfAnswer}</strong>개의 의견</p>
                    <div>
                        <c:forEach items="${answers}" var="answer">
                            <article class="article" id="answer-${answer.answerId}">
                                <div class="article-header">
                                    <div class="article-header-thumb">
                                        <img src="../img/picture.jpeg" class="article-author-thumb" alt="">
                                    </div>
                                    <div class="article-header-text">
                                        <span class="article-author-name">${answer.writer}</span>
                                        <span class="article-header-time">
                                                ${answer.createdDate}
                                        </span>
                                    </div>
                                </div>
                                <div class="article-doc comment-doc">
                                    <p>${answer.contents}</p>
                                </div>
                                <div class="article-util">
                                    <ul class="article-util-list">
                                        <c:if test="${sessionScope.user.userId eq answer.writer}">
                                            <li>
                                                <a class="link-modify-article"
                                                   href="/api/qna/updateAnswer?answerId=${each.answerId}">수정</a>
                                            </li>
                                            <li>
                                                <form class="form-delete" action="/api/qna/deleteAnswer" method="POST">
                                                    <input type="hidden" name="answerId" value="${each.answerId}"/>
                                                    <button type="submit" class="link-delete-article">삭제</button>
                                                </form>
                                            </li>

                                        </c:if>
                                    </ul>
                                </div>
                            </article>
                        </c:forEach>
                        <form class="submit-write">
                            <div class="form-group" style="padding:14px;">
                                <textarea class="form-control" placeholder="Update your status"></textarea>
                            </div>
                            <button class="btn btn-primary pull-right" type="button">답변하기</button>
                            <div class="clearfix" />
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="../js/scripts.js"></script>
  </body>
</html>