/**
 *create by xiaoyi 2018.10.13
 */

$(document).ready(function () {

    var table = $("#businessReturn_table");
    table.dataTable({
        "columnDefs": [{
            'orderable':false,
            'targets':[3]
        }],
        "order":[
            [0,"acs"]
        ]
    });


    //查看全部补录记录
    var params = {};
    $.ajax({
        async: false,
        type: "POST",
        url: "../businessReturn/list",
        data: params,
        dataType: "json",
        success:function (data) {
            console.log(JSON.stringify(data,null,4));

            for (var i = 0;i < data.length;i++){
                var itm = data[i];

                table.fnAddData([
                    itm.f_id,
                    itm.rebate_time,
                    itm.rebate_amount,
                    // '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑&nbsp;</a>' +
                    '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                    '<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
                ]);
            }
        },
        error:function (data) {
            console.log(data);
            alert("数据获取失败 ！");
            swal({
                title: "数据获取失败！",
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
    });


    /**
     * 添加补录
     */
    $("#btn_add_save").click(function (e) {
        var delok = true;
        var params = {};
        params.rebate_amount = $('#businessReturn_add_Money').val();

        console.log(params);

        if(params.rebate_amount == ''){
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
            url: "../businessReturn/add",
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
            "AR0000000",
            "0000.00.0 00:00:00",
            $('#businessReturn_add_Money').val(),
            // '<a class="edit"><i class="fa fa-edit"></i>&nbsp;编辑&nbsp;</a>' +
            '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
            '<a class="delete"><i class="fa fa-trash"></i>&nbsp;删除</a>'
        ]);

        table.fnDraw();
        "AR0000000",
        "0000.00.0 00:00:00",
        $('#businessReturn_add_Money').val(),
        $('#businessReturn_add_modal').css('display','none');
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
        $('#businessReturn_add_modal').modal('hide');
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

        $('#businessReturn_edit_Id').val(aData[0]);
        $('#businessReturn_edit_Time').val(aData[1]);
        $('#businessReturn_edit_Money').val(aData[2]);

        $('#businessReturn_edit_modal').modal('show')
    });


    $("#btn_edit_save").click(function(e){
        var nRow = EditRow;
        var delok = true;
        var params = {};

        params.f_id = $('#businessReturn_edit_Id').val();
        params.rebate_time = $('#businessReturn_edit_Time').val();
        params.rebate_amount = $('#businessReturn_edit_Money').val();

        console.log(JSON.stringify(params,null,4));

        if(params.rebate_amount == ''){
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
            url: "../businessReturn/update",
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

        table.fnUpdate($('#businessReturn_edit_Id').val(), nRow, 0, false);
        table.fnUpdate($('#businessReturn_edit_Time').val(), nRow, 1, false);
        table.fnUpdate($('#businessReturn_edit_Money').val(), nRow, 2, false);
        table.fnDraw();
        $('#businessReturn_edit_Id').val('');
        $('#businessReturn_edit_Time').val('');
        $('#businessReturn_edit_Money').val('');

        $('#businessReturn_edit_modal').modal('hide');
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
            params.f_id = aData[0];
            console.log(params.f_id);

            $.ajax({
                async: false,
                type: "POST",
                url: "../businessReturn/delete",
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