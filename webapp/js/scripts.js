$(".answerWrite input[type=submit]").click(addAnswer);
//$("#askButton").click(addAnswer);

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

function formatDate(date) {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');

    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}

function onSuccess(json, status){
    var answerTemplate = $("#answerTemplate").html();
    var createdDate = formatDate(new Date(json.createdDate));
    var template = answerTemplate.format(json.writer, createdDate, json.contents, json.answerId, json.answerId);
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