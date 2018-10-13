/**
 *create by xiaoyi 2018.10.5
 */

$(document).ready(function () {
    //上下文路径问题
    var pathName=window.document.location.pathname;
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    var table = $("#orderAdd_add_table");
    table.dataTable({
        "columnDefs": [{
            'orderable':false,
            'targets':[8]
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
        url: projectName+"/orderAdd/list",
        data: params,
        dataType: "json",
        success:function (data) {
            console.log(JSON.stringify(data,null,4));

            for (var i = 0;i < data.length;i++){
                var itm = data[i];

                table.fnAddData([
                    itm.add_record,
                    itm.single_people,
                    itm.single_phone,
                    itm.c_id,
                    itm.number,
                    itm.price,
                    itm.add_time,
                    itm.add_describe,
                    '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>' +
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
        params.single_people = $('#orderAdd_add_people').val();
        params.single_phone = $('#orderAdd_add_phone').val();
        params.c_id = $('#orderAdd_add_Cid').val();
        params.number = $('#orderAdd_add_number').val();
        params.price = $('#orderAdd_add_price').val();
        params.add_describe = $('#orderAdd_add_describe').val();

        console.log(params);

        if(params.single_people == '' || params.single_phone == '' || params.c_id=='' ||
            params.number =='' || params.price=='' || params.add_describe == ''){
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
            url: projectName+"/orderAdd/add",
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
            "AR----",
            $('#orderAdd_add_people').val(),
            $('#orderAdd_add_phone').val(),
            $('#orderAdd_add_Cid').val(),
            $('#orderAdd_add_number').val(),
            $('#orderAdd_add_price').val(),
            $('#orderAdd_add_time').val(),
            $('#orderAdd_add_describe').val(),
            '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>' +
            '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
            '<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
        ]);

        table.fnDraw();
        "AR----";
        $('#orderAdd_add_people').val();
        $('#orderAdd_add_phone').val();
        $('#orderAdd_add_Cid').val();
        $('#orderAdd_add_number').val();
        $('#orderAdd_add_price').val();
        $('#orderAdd_add_time').val();
        $('#orderAdd_add_describe').val();

        $('#orderAdd_add_modal').css('display','none');
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
        $('#orderAdd_add_modal').modal('hide');
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

        $('#orderAdd_edit_order').val(aData[0]);
        $('#orderAdd_edit_people').val(aData[1]);
        $('#orderAdd_edit_phone').val(aData[2]);
        $('#orderAdd_edit_Cid').val(aData[3]);
        $('#orderAdd_edit_number').val(aData[4]);
        $('#orderAdd_edit_price').val(aData[5]);
        $('#orderAdd_edit_time').val(aData[6]);
        $('#orderAdd_edit_describe').val(aData[7]);

        $('#orderAdd_edit_modal').modal('show')
    });


    $("#btn_edit_save").click(function(e){
        var nRow = EditRow;
        var delok = true;
        var params = {};

        params.add_record = $('#orderAdd_edit_order').val();
        params.single_people = $('#orderAdd_edit_people').val();
        params.single_phone = $('#orderAdd_edit_phone').val();
        params.c_id = $('#orderAdd_edit_Cid').val();
        params.number = $('#orderAdd_edit_number').val();
        params.price = $('#orderAdd_edit_price').val();
        params.add_describe = $('#orderAdd_edit_describe').val();

        if(params.add_record == '' || params.single_people == '' || params.single_phone == '' || params.c_id==''
            || params.number =='' || params.price=='' || params.add_describe == ''){
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
            url: projectName+"/orderAdd/update",
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

        table.fnUpdate($('#orderAdd_edit_order').val(), nRow, 0, false);
        table.fnUpdate($('#orderAdd_edit_people').val(), nRow, 1, false);
        table.fnUpdate($('#orderAdd_edit_phone').val(), nRow, 2, false);
        table.fnUpdate($('#orderAdd_edit_Cid').val(), nRow, 3, false);
        table.fnUpdate($('#orderAdd_edit_number').val(), nRow, 4, false);
        table.fnUpdate($('#orderAdd_edit_price').val(), nRow, 5, false);
        table.fnUpdate($('#orderAdd_edit_time').val(), nRow, 6, false);
        table.fnUpdate($('#orderAdd_edit_describe').val(), nRow, 7, false);
        table.fnDraw();
        $('#orderAdd_edit_order').val('');
        $('#orderAdd_edit_people').val('');
        $('#orderAdd_edit_phone').val('');
        $('#orderAdd_edit_Cid').val('');
        $('#orderAdd_edit_number').val('');
        $('#orderAdd_edit_price').val('');
        $('#orderAdd_edit_time').val('');
        $('#orderAdd_edit_describe').val('');

        $('#orderAdd_edit_modal').modal('hide');
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
            params.add_record = aData[0];

            console.log(params.add_record);
            $.ajax({
                async: false,
                type: "POST",
                url: projectName+"/orderAdd/delete",
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