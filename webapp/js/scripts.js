$(".answerWrite input[type=submit]").click(addAnswer);

function addAnswer(e) {
    e.preventDefault();
    var queryString = $(".submit-write").serialize();

    $.ajax({
        type: 'post',
        url: '/api/qna/addAnswer',
        data: queryString,
        dataType: 'json',
        error: onError,
        success: onSuccess,
    });
}

function onSuccess(json, status) {
    var answerTemplate = $("#answerTemplate").html();
    // html 포맷팅
    var template = answerTemplate.format(json.writer, new Date(json.createdDate), json.contents, json.answerId, json.answerId);
    $(".qna-comment-kuit-articles").prepend(template);
    var countOfAnswer = document.getElementsByTagName("strong").item(0);
    let number = parseInt(countOfAnswer.innerText, 10);
    number += 1;
    countOfAnswer.textContent = number.toString();

    $("#contents").val("");
}

function onError(xhr, status) {
    alert("error");
}

// argument로 들어오는 값들로
String.prototype.format = function () {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function (match, number) {
        // match(첫번째 인자) : 패턴과 일치하는 전체 문자열
        // number(두번째 인자) : 캡처 그룹에 해당하는 부분 문자열. 정규 표현식에서 소괄호(())로 묶은 부분
        return typeof args[number] != 'undefined'
            ? args[number]
            : match
            ;
    });
};