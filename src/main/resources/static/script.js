$("#btn").click(function () {
    var phoneNumber = $(".phone-num-text").val();

    var jsonObj = {telephone: phoneNumber};
    $.ajax({
        url: 'http://localhost:8080/get-country',
        type: "post",
        contentType: "application/json",
        data: JSON.stringify(jsonObj),
        success: function(data) {
              $("#country").text(data.country);

              if (data.errorList != null){
                $("#error").text(data.errorList[0].errorMsg);
              } else {
                $("#error").text("");
              }
        }
    })
});