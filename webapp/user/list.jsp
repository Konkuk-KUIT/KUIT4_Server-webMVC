<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>KUIT</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/styles.css" rel="stylesheet">
  </head>
  <body>
  <%@ include file="../include/navigation.jspf" %>
    <div class="navbar-default">
        <%@ include file="../include/header.jspf" %>

    <div class="container" id="main">
        <table class="table table-striped">
            <thead class="col-md-12">
                <tr>
                <th class="col-md-3">#</th>
                <th class="col-md-3">아이디</th>
                <th class="col-md-3">이름</th>
                <th class="col-md-3">이메일</th>
                </tr>
            </thead>
            <tbody>
            <tr>
                <th class="col-md-3">1</th>
                <th class="col-md-3">lyouxsun</th>
                <th class="col-md-3">이영선</th>
                <th class="col-md-3">lyouxsun@kuit.kr</th>
            </tr>
            <tr>
                <th class="col-md-3">2</th>
                <th class="col-md-3">crohasang</th>
                <th class="col-md-3">조하상</th>
                <th class="col-md-3">crohasang@kuit.kr</th>
            </tr>
            <tr>
                <th class="col-md-3">3</th>
                <th class="col-md-3">yunjeongiya</th>
                <th class="col-md-3">이윤정</th>
                <th class="col-md-3">yunjeongiya@kuit.kr</th>
            </tr>
            <tr>
                <th class="col-md-3">4</th>
                <th class="col-md-3">hamhyeongju</th>
                <th class="col-md-3">함형주</th>
                <th class="col-md-3">hamhyeongju@kuit.kr</th>
            </tr>
            </tbody>
        </table>
    </div>
    
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="../js/scripts.js"></script>
    </body>
</html>