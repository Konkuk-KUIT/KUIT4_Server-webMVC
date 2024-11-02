<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ko">
<%@ include file="/include/header.jspf"%>
<body>
<%@ include file="/include/navigation.jspf" %>

<div class="container" id="main">
    <div class="col-md-12 col-sm-12 col-lg-10 col-lg-offset-1">
        <div class="panel panel-default content-main">
            <form name="question" method="post" action="/qna/create">
                <div class="form-group">
                    <label for="writer">글쓴이</label>
                    <input type="text" class="form-control" value="${sessionScope.user.userId}" id="writer" name="writer" placeholder="글쓴이" readonly/>
                </div>
                <div class="form-group">
                    <label for="title">제목</label>
                    <input type="text" class="form-control" id="title" name="title" placeholder="제목"/>
                </div>
                <div class="form-group">
                    <label for="contents">내용</label>
                    <textarea name="contents" id="contents" rows="5" class="form-control" placeholder="내용을 입력해 주세요"></textarea>
                </div>
                <div class="row">
                    <div class="col-md-12 text-right">
                        <button type="submit" class="w-100 btn btn-lg btn-primary">질문하기</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="../js/scripts.js"></script>
<script>
    document.querySelector('form[name="question"]').addEventListener('submit', function(e) {
        const title = document.getElementById('title').value.trim();
        const contents = document.getElementById('contents').value.trim();

        if (!title) {
            e.preventDefault();
            alert('제목을 입력해주세요.');
            document.getElementById('title').focus();
            return;
        }

        if (!contents) {
            e.preventDefault();
            alert('내용을 입력해주세요.');
            document.getElementById('contents').focus();
            return;
        }
    });
</script>
</body>
</html>