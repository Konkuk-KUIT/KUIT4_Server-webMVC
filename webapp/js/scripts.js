$(".answerWrite input[type=submit]").click(addAnswer);

function addAnswer(e) {
    e.preventDefault();
    var queryString = $(".submit-write").serialize();

    $.ajax({
        type : 'post',
        url : '/api/qna/addAnswer',
        data : queryString,
        dataType : 'json',
        error: onError,
        success : onSuccess,
    });
}

function onSuccess(json, status){
    var answerTemplate = $("#answerTemplate").html();
    // json resp 할때 이제는 answer객체를 반환하므로 answer 객체가 데이터들을 한층 더 묶어서 반환하게 됨.
    // 그래서 answer로 한번 더 파싱을 해야한다. json->answer-> field1, field2,....
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