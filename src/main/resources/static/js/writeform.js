$(function () {
    $("writeForm").validate();
    $.extend($.validator.messages, {
        required: "필수 항목입니다.",
        remote: "항목을 수정하세요",
        url: "유효하지 않은 URL입니다.",
        dateISO: "올바르지 않은 URL입니다.",

        maxlength: $.validator.format("{0}자를 넘을 수 없습니다."),
        minlength: $.validator.format("{0}자 이상 입력하세요")
    });
});
