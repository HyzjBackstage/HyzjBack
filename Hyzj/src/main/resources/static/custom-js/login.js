/**
 * Created by 14260 on 2018/6/12.
 */
$(document).ready(function() {

    //上下文路径问题
    var pathName=window.document.location.pathname;
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);


    $("#phone").on('input', function (e) {
        var pattern = /^1[34578]\d{9}$/;
        var b = pattern.test($("#phone").val());
        if (b == false) {
            $("#tip").val("手机格式错误");
            $("#format_phone").show();
            $("#btn_login").attr('disabled', true);
            $("#btn_login").css("background-color", "#ececec");
        }
        if (b == true) {
            // console.log("123");
            $("#format_phone").hide();
            $("#btn_login").attr('disabled', false);
            $("#btn_login").css("background-color", "#0c9076");
        }
    });

    // alert("123");
    $("#loding-login").css('display','none');

    $("#btn_login").click(function(){
        $("#loding-login").css('display','block');
        var t = 1000;
        setTimeout(function(e){
            var params={};
            params.phone =$("#phone").val();
            params.pwd =$("#pwd").val();

            if(params.phone =='' || params.pwd == ''){

                $("#loding-login").css('display','none');
                swal({
                    title: "账号和密码不能为空！",
                    text: "",
                    type: "warning",
                    
                    allowOutsideClick: true,
                    showConfirmButton: true,
                    showCancelButton: false,
                    confirmButtonClass: "btn-danger",
                    confirmButtonText: "OK",
                });
                // clearInterval(int);

                return ;
            }
            // alert(JSON.stringify(params,null,4));

            console.log(projectName);
            $.ajax({
                async: false,
                type: "POST",
                url: projectName+"/MallUserLogin",//注意路径
                data: params,
                dataType: "json",
                success: function (data) {
                    //console.log(JSON.stringify(data,null,4));
                    var itm = data.data;
                    if(itm == "4"){
                        $("#loding-login").css('display','none');
                        swal({
                            title: "账号不存在或无效！",
                            text: "",
                            type: "warning",
                            allowOutsideClick: true,
                            showConfirmButton: true,
                            showCancelButton: false,
                            confirmButtonClass: "btn-danger",
                            confirmButtonText: "OK",
                        });

                        //clearInterval(int);
                    }else if (itm=="2"){
                        $("#loding-login").css('display','none');
                        swal({
                            title: "密码错误！",
                            text: "",
                            type: "error",
                            allowOutsideClick: true,
                            showConfirmButton: true,
                            showCancelButton: false,
                            confirmButtonClass: "btn-danger",
                            confirmButtonText: "OK",
                        });

                        //clearInterval(int);
                    }else if (itm == "1") {
                        $("#loding-login").css('display','none');

                        window.location.href="index";
                        return;
                    }else if (itm == "3"){
                        $("#loding-login").css('display','none');
                        swal({
                            title: "没有权限！",
                            text: "",
                            type: "error",
                            allowOutsideClick: true,
                            showConfirmButton: true,
                            showCancelButton: false,
                            confirmButtonClass: "btn-danger",
                            confirmButtonText: "OK",
                        });

                        //clearInterval(int);
                    }
                    else  {
                        $("#loding-login").css('display','none');
                        swal({
                            title: "未知错误！",
                            text: "",
                            type: "error",
                            allowOutsideClick: true,
                            showConfirmButton: true,
                            showCancelButton: false,
                            confirmButtonClass: "btn-danger",
                            confirmButtonText: "OK",
                        });
                    }
                },
                error: function (data) {
                    //console.log(JSON.stringify(data,null,4));
                    $("#loding-login").css('display','none');
                    swal({
                        title: "登陆出错！",
                        text: "",
                        type: "error",
                        allowOutsideClick: true,
                        showConfirmButton: true,
                        showCancelButton: false,
                        confirmButtonClass: "btn-danger",
                        confirmButtonText: "OK",
                    });
                    $("#loding-login").css('display','none');
                    //clearInterval(int);
                }
            });


         },t);

    });



});
