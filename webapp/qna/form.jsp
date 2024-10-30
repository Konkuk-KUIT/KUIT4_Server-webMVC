<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ko">
<%@ include file="/include/header.jspf"%>
<body>
<%@ include file="/include/navigation.jspf" %>

<div class="container" id="main">
    <main class="form-qna">
        <form name="qna-form" method="post" action="/qna/create">
            <div class="form-floating">
                <!-- 로그인한 사용자의 userId를 글쓴이 필드에 미리 넣어 수정 불가하게 설정 -->
                <input type="text" class="form-control" value="${sessionScope.user.userId}" id="writer" name="writer" placeholder="글쓴이" readonly/>
                <label for="writer">글쓴이</label>
            </div>
            <div class="form-floating">
                <input type="text" class="form-control" id="title" name="title" placeholder="제목">
                <label for="title">제목</label>
            </div>
            <div class="form-floating">
                <textarea class="form-control" id="contents" name="contents" placeholder="내용을 입력하세요" rows="5" style="height: 150px;"></textarea>
                <label for="contents">내용</label>
            </div>
            <div style="height:10px;"></div>
            <button class="w-100 btn btn-lg btn-primary" type="submit">질문 등록</button>
        </form>
    </main>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="../js/scripts.js"></script>
</body>
</html>