/**
 * create by xiaoyi 2018.10.07
 */

$(document).ready(function () {
    //上下文路径问题
    var pathName=window.document.location.pathname;
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    //通过用户手机号查询
    $("#btn_search").click(function(){
        var phone = $("#platToMall_phone").val();
        if(phone==''){
            swal({
                title: "手机号不能为空！",
                text: "",
                type: "warning",
                allowOutsideClick: true,
                showConfirmButton: true,
                showCancelButton: false,
                confirmButtonClass: "btn-success",
                confirmButtonText: "OK",
            });
            return;
        }else{
            loadByOrder(phone);
        }
    });

    /**
     * loadByOrder()函数：通过手机号查询
     */
    function loadByOrder(phone) {
        var delok = false;
        var param = {};
        param.phone = ''+phone;
        console.log(param.phone);
        $.ajax({
            async: false,
            type: "POST",
            url: projectName+"/platformUser/searchByPhone",
            data: param,
            dataType: "json",
            success:function (data) {
                var showdata = data;
                console.log(JSON.stringify(showdata,null,4));

                $("#platToMall_phone").val(showdata.phone);
                $("#platToMall_password").val(showdata.password);
                $("#platToMall_Pid").val(showdata.p_id);
                $("#platToMall_nickName").val(showdata.nickname);
                $("#platToMall_name").val(showdata.name);
                $("#platToMall_IDcard").val(showdata.id_card);

                delok = true;
            },
            error:function (data) {
                swal({
                    title: "该手机号没有注册信息！",
                    text: "",
                    type: "error",
                    allowOutsideClick: true,
                    showConfirmButton: true,
                    showCancelButton: false,
                    confirmButtonClass: "btn-danger",
                    confirmButtonText: "OK",
                });
            }
        });
        if(delok == true){
            checkphone(phone);
        }
    };

    /**
     * checkPhone(phone)函数:检测mall_user表中是否有该手机号
     */
    function checkphone(phone){
        var delok = false;
        var param = {};
        param.phone = ''+phone;
        console.log(param.phone);
        $.ajax({
            async: false,
            type: "POST",
            url: projectName+"/platformUser/checkPhone",
            data: param,
            dataType: "json",
            success:function (data) {
                console.log(data);

                delok = data;

            },
            error:function (data) {
                swal({
                    title: "检测失败",
                    text: "",
                    type: "error",
                    allowOutsideClick: true,
                    showConfirmButton: true,
                    showCancelButton: false,
                    confirmButtonClass: "btn-danger",
                    confirmButtonText: "OK",
                });
            }
        });
        if (delok == true){
            swal({
                title: "检测到该手机号已在商城中注册过，点击右下角按钮确认添加，将覆盖原商城用户！！！",
                text: "",
                type: "error",
                allowOutsideClick: true,
                showConfirmButton: true,
                showCancelButton: false,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "OK",
            });
        }
    }

    /**
     * 添加按钮，进行添加
     */
    $("#btn_add_platToMall").click(function(e){

        var delok = true;
        var params={};
        params.phone = $("#platToMall_phone").val();
        params.password =$('#platToMall_password').val();
        params.p_id = $('#platToMall_Pid').val();
        params.nickname =$('#platToMall_nickName').val();
        params.name = $("#platToMall_name").val();
        params.id_card =$('#platToMall_IDcard').val();

        $.ajax({
            async: false,
            type: "POST",
            url: projectName+"/platformUser/addUser",
            data: params,
            dataType: "json",
            success: function (data) {
                delok = data;
            },
            error: function (data) {
                console.log(JSON.stringify(data,null,4));

                delok = false;
            }
        });
        if (!delok) {

            swal({
                title: "添加失败！",
                text: "",
                type: "error",
                allowOutsideClick: true,
                showConfirmButton: true,
                showCancelButton: false,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "OK",
            });
            return;
        }
        swal({
            title: "添加成功！",
            text: "",
            type: "success",
            allowOutsideClick: true,
            showConfirmButton: true,
            showCancelButton: false,
            confirmButtonClass: "btn-success",
            confirmButtonText: "OK",
        });
    });


});