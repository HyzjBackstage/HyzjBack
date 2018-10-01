/**
 * Created by 14260 on 2018/6/12.
 */
$(document).ready(function() {

    $("#btn_login").click(function(){
        $("#loding-login").css('display','block');
        var t = 0;
        setTimeout(function(e){
            var params={};
            params.username =$("#username").val();
            params.pwd =$("#pwd").val();

            if(params.username =='' || params.pwd == ''){
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
            alert(JSON.stringify(params,null,4));

            $.ajax({
                async: false,
                type: "POST",
                url: "../user/MallUserLogin",//注意路径
                data: params,
                dataType: "json",
                success: function (data) {
                    //console.log(JSON.stringify(data,null,4));
                    var itm = data.data;
                    if(itm.user == null){
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
                        $("#loding-login").css('display','none');
                        //clearInterval(int);
                    }else if (itm.menuVos==null){
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
                        $("#loding-login").css('display','none');
                        //clearInterval(int);
                    }else{
                        window.location.href="index.html";
                    }
                },
                error: function (data) {
                    //console.log(JSON.stringify(data,null,4));
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
