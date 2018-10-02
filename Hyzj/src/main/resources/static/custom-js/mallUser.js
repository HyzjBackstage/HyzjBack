/**
 *
 * xiaoyi  2018.9.18
 *
 */

$(document).ready(function () {

    var table = $("#user_table");
    table.dataTable({
        "columnDefs": [{
            'orderable':false,
            'targets':[6]
        }],
        "order":[
            [0,"acs"]
        ]
    })


    /**
     * 角色选择，int转成String
     * @param role
     * @returns {*}
     */
    function numToRole(role){
        if (role == 1){
            return "项目发起人";
        }else if (role == 2){
            return "项目投资人";
        }else if(role == 3){
            return "商城用户";
        }
    }

    /**
     * 获取角色数据，String转int
     * @param role
     * @returns {*}
     */
    function roleToNum(role){
        if (role == "项目发起人"){
            return 1;
        }else if (role == "项目投资人"){
            return 2;
        }else if(role == "商城用户"){
            return 3;
        }
    }

    var param = {};
    $.ajax({
        async: false,
        type: "post",
        url: "../malluser/list",
        data: param,
        dataType: "json",
        success: function(data){
            //data为一个集合
            for(var i = 0;i < data.length; i++){
                var itm = data[i];
                console.log(JSON.stringify(itm,null,4));

                var numRole = numToRole(itm.r_id);
                console.log('numRole:'+numRole);
                table.fnAddData([
                    itm.m_id,
                    itm.name,
                    itm.phone,
                    itm.id_card,
                    itm.password,
                    numRole,
                    '<a class="edit"><i class="fa fa-edit"></i>&nbsp;编辑</a>' +
                    '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                    '<a class="delete"><i class="fa fa-trash"></i>&nbsp;删除</a>'
                ]);
            }
        },
        error: function (data) {
            swal({
                title: "数据获取失败！",
                text: "",
                type: "error",
                allowOutsideClick: true,
                showConfirmButton: true,
                showCancelButton: false,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "OK",
            })
        }

    })

    $("#loading-user").css('display','none');


    /**
     * 添加用户
     */
    $("#btn_add_save").click(function (e) {
        var delok = true;
        var params = {};
        params.m_id = $('#user_add_user').val();
        params.name = $('#user_add_name').val();
        params.phone = $('#user_add_phone').val();
        params.id_card = $('#user_add_ID').val();
        params.r_id = $('#user_add_role').val();
        params.password = $('#user_add_password').val();

        var numRole = numToRole($('#user_add_role').val());

        console.log($('#user_add_role').val());
        console.log(numRole);

        if(params.M_id == '' || params.name == '' ||param.ID_card == '' || param.phone=='' || param.r_id==''|| params.password ==''){
            swal({
                title: "不能为空！",
                text: "",
                type: "warning",
                allowOutsideClick: true,
                showConfirmButton: true,
                showCancelButton: false,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "OK",
            });
            return;
        }

        $.ajax({
            async: false,
            type: "POST",
            url: "../malluser/add",
            data: params,
            dataType: "json",
            success:function(data){
                if (data.data){
                    return;
                }else {
                    delok:false;
                }
            },
            error:function(data){
                delok = false;
            }
        });
        if (!delok){
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
        table.fnAddData([
            $("#user_add_user").val(),
            $('#user_add_name').val(),
            $('#user_add_phone').val(),
            $('#user_add_ID').val(),
            numRole,
            '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>' +
            '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
            '<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
        ]);

        table.fnDraw();
        $("#user_add_user").val(),
        $('#user_add_name').val(),
        $('#user_add_phone').val(),
        $('#user_add_ID').val(),
        numRole,
        $('#user_add_modal').css('display','none');
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
        $('#user_add_modal').modal('hide');
    });


    /**
     * 编辑
     */
    var EditRow = -1;
    table.on('click','.edit',function(e){
        e.preventDefault();

        var nRow = $(this).parents('tr')[0];
        EditRow = nRow;
        var aData = table.fnGetData(nRow);

        $('#user_edit_user').val(aData[0]);
        $('#user_edit_name').val(aData[1]);
        $('#user_edit_phone').val(aData[2]);
        $('#user_edit_ID').val(aData[3]);
        $('#user_edit_password').val(aData[4]);
        console.log("roleToNum(aData[5]):"+roleToNum(aData[5]));
        // $('#user_edit_role').html(roleToNum(aData[5]));


        console.log(aData[4]);
        var roleNum = roleToNum(aData[5]);

        $('#user_edit_role').val(roleNum);
        $('#user_edit_modal').modal('show')
    });


    $("#btn_edit_save").click(function(e){
        var nRow = EditRow;
        var delok = true;
        var param = {};

        param.m_id = $("#user_edit_user").val();
        param.name = $("#user_edit_name").val();
        param.phone = $("#user_edit_phone").val();
        param.id_card = $("#user_edit_ID").val();
        param.r_id = $("#user_edit_role").val();
        param.password = $('#user_edit_password').val();
        if(param.m_id == '' || param.name==''||param.id_card==''||param.phone==''||param.r_id==''|| param.password ==''){
            swal({
                title: "不能为空！",
                text: "",
                type: "warning",
                allowOutsideClick: true,
                showConfirmButton: true,
                showCancelButton: false,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "OK",
            });
            return;
        }

        $.ajax({
            async: false,
            type: "POST",
            url: "../malluser/update",       //注意路径
            data: param,
            dataType: "json",
            success: function (data) {
                if (data) {
                    return;
                } else {
                    delok = false;
                }
            },
            error: function (data) {
                delok = false;
            }
        });
        if (!delok) {
            swal({
                title: "保存失败！",
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
        var numRole2 = numToRole($('#user_edit_role').val());
        table.fnUpdate($('#user_edit_user').val(), nRow, 0, false);
        table.fnUpdate($('#user_edit_name').val(), nRow, 1, false);
        table.fnUpdate($('#user_edit_phone').val(), nRow, 2, false);
        table.fnUpdate($('#user_edit_ID').val(), nRow, 3, false);
        table.fnUpdate($('#user_edit_password').val(), nRow, 4, false);

        table.fnUpdate(numRole2, nRow, 5, false);
        table.fnDraw();
        $("#user_edit_user").val('');
        $('#user_edit_name').val('');
        $('#user_edit_phone').val('');
        $('#user_edit_ID').val('');
        numRole2;
        $('#user_edit_modal').modal('hide');
        swal({
            title: "保存成功！",
            text: "",
            type: "success",
            allowOutsideClick: true,
            showConfirmButton: true,
            showCancelButton: false,
            confirmButtonClass: "btn-success",
            confirmButtonText: "OK",
        });
    });

    /**
     * 删除
     */
    table.on('click', '.delete', function (e) {
        e.preventDefault();
        var nRow = $(this).parents('tr')[0];
        var aData = table.fnGetData(nRow);

        swal({
            title: "确认删除这一行数据 ?",
            text: "",
            type: "warning",
            allowOutsideClick: true,
            showConfirmButton: true,
            showCancelButton: true,
            confirmButtonClass: "btn-info",
            cancelButtonClass: "btn-danger",
            confirmButtonText: "确认",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function (isConfirm) {
            if (!isConfirm) return;
            var delok = true;
            var params={};
            params.m_id = aData[0];
            console.log(params.m_id);
            $.ajax({
                async: false,
                type: "POST",
                url: "../malluser/delete",      //注意路径
                data: params,
                dataType: "json",
                success: function (data) {
                    if (data) {
                        return;
                    } else {
                        delok = false;
                    }
                },
                error: function (data) {
                    console.log(data);
                    delok = false;
                }
            });
            if (!delok){
                swal({
                    title: "删除失败！",
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
            table.fnDeleteRow(nRow);
            swal({
                title: "删除成功！",
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

});