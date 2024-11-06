$(".answerWrite input[type=submit]").click(addAnswer);      //'답변하기' 버튼을 누르면 'addAnswer' 함수를 작동 시킴

function addAnswer(e) {
    e.preventDefault();
    var queryString = $(".submit-write").serialize();

    $.ajax({
        type : 'post',
        url : '/api/qna/addAnswer',
        data : queryString,
        dataType : 'json',      //서버가 json형식으로 데이터 반환
        error: onError,
        success : onSuccess,
    });
}

function onSuccess(json, status){
    var answerTemplate = $("#answerTemplate").html();
    var template = answerTemplate.format(json.answer.writer, new Date(json.answer.createdDate), json.answer.contents, json.answer.answerId, json.answer.answerId);
    $(".qna-comment-kuit-articles").prepend(template);
    var countOfAnswer = document.getElementsByTagName("strong").item(0);
    let number = parseInt(countOfAnswer.innerText,10);
    number += 1;
    countOfAnswer.textContent = number.toString();
}

function onError(xhr, status) {
    alert("error");
}

String.prototype.format = function() {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function(match, number) {
        return typeof args[number] != 'undefined'
            ? args[number]
            : match
            ;
    });
};