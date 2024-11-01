<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">
<%@ include file="/include/header.jspf" %>
<body>
<%@ include file="/include/navigation.jspf" %>

<div class="container" id="main">

    <main class="form-signin">

        <form name="question" method="post" action="/qna/create">
            <div class="form-floating">
                <input type="text" class="form-control" value="${sessionScope.user.userId}" id="writer" name="writer"
                       placeholder="글쓴이" readonly/>
                <label for="writer">글쓴이</label>
            </div>
            <div class="form-floating">
                <input type="text" class="form-control" id="title" name="title" placeholder="제목"/>
                <label for="title">제목</label>
            </div>
            <div class="form-floating">
                <textarea name="contents" id="contents" rows="5" class="form-control"></textarea>
                <label for="contents">내용</label>

            </div>
            <div style="height:10px;">
            </div>
            <button type="submit" class="btn btn-success clearfix pull-right">질문하기</button>
        </form>
    </main>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="../js/scripts.js"></script>
</body>
</html>