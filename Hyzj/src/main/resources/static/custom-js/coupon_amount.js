/**
 * Created by 14260 on 2018/6/11.
 *
 * --金额管理--
 */
$(document).ready(function () {
    //上下文路径问题
    var pathName=window.document.location.pathname;
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

    var table = $("#age_table");
    table.dataTable({
        "columnDefs": [{ // set default column settings
            'orderable': false,
            'targets': [5]
        }, {
            "searchable": false,
            "targets": [5]
        }],
        "order": [
            [0, "asc"]
        ]
    });
    var params = {};
    $.ajax({
        async: false,
        type: "POST",
        url: projectName+"/coupon/amountlist",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            console.log(JSON.stringify(data, null, 4));
            for (var i = 0; i < data.data.length; i++) {
                var itm = data.data[i];

                table.fnAddData([
                    itm.co_id,
                    itm.amount,
                    itm.starting_time,
                    itm.end_time,
                    itm.condition_use,
                    '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>' +
                    '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                    '<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
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
            });
        }
    });
    $("#loading-coupon").css('display', 'none');


    /**
     * 添加
     */
    $("#btn_add_save").click(function (e) {
        var delok = true;
        var params = {};
        params.cid = $('#coupon_add_id').val();
        params.amount = $('#coupon_add_amount').val();
        params.strat = ($('#coupon_add_strat').val());
        params.end = ($('#coupon_add_end').val());
        params.condition = $('#coupon_add_condition').val();
        // console.log(JSON.stringify(params,null,4));
        if (params.cid < 999 || params.cid > 2000 || params.cid == "") {
            swal({
                title: "编号格式不对",
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
        if (params.amount < 0) {
            swal({
                title: "金额书写格式有问题！",
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
        if (params.cid == '' || params.amount == '' || params.strat == '' ||
            params.end == '' || params.condition == '') {
            swal({
                title: "编号、折扣、日期、使用条件不能为空！",
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
            url: projectName+"/coupon/amountadd",          //注意路径
            data: params,
            dataType: "json",
            success: function (data) {
                if (data.data) {
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
            $('#coupon_add_id').val(),
            $('#coupon_add_amount').val(),
            $('#coupon_add_strat').val(),
            $('#coupon_add_end').val(),
            $('#coupon_add_condition').val(),
            '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>' +
            '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
            '<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
        ]);

        // console.log($('#age_add_age').val());
        // console.log($('#age_add_name').val());
        // console.log($('#age_add_min').val());
        // console.log($('#age_add_max').val());

        table.fnDraw();
        $('#coupon_add_id').val();
        $('#coupon_add_amount').val();
        $('#coupon_add_strat').val();
        $('#coupon_add_end').val();
        $('#coupon_add_condition').val();
        $('#coupon_add_modal').modal('hide');
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

    /**
     * 编辑
     */
    var EditRow = -1;
    table.on('click', '.edit', function (e) {
        e.preventDefault();

        var nRow = $(this).parents('tr')[0];
        EditRow = nRow;
        var aData = table.fnGetData(nRow);

        $('#coupon_edit_id').val(aData[0]);
        $('#coupon_edit_amount').val(aData[1]);
        $('#coupon_edit_strat').val(aData[2]);
        $('#coupon_edit_end').val(aData[3]);
        $('#coupon_edit_condition').val(aData[4]);
        $('#coupon_edit_modal').modal('show')
    });


    $("#btn_edit_save").click(function (e) {
        var nRow = EditRow;
        var delok = true;
        var params = {};
        params.cid = $('#coupon_edit_id').val();
        params.amount = $('#coupon_edit_amount').val();
        params.strat = ($('#coupon_edit_strat').val());
        params.end = ($('#coupon_edit_end').val());
        params.condition = $("#coupon_edit_condition").val();
        // console.log(params.agesection);
        // console.log(params.name);
        // console.log(params.minage);
        console.log(parseInt(params.end) >= parseInt(params.strat));
        console.log(JSON.stringify(params, null, 4));
        if (params.cid == '' || params.amount == '' || params.condition == '' || params.end <= params.strat) {
            swal({
                title: "折扣、使用条件、时间不能为空、结束时间不能小于开始时间！",
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
            url: projectName+"/coupon/amountupdate",       //注意路径
            data: params,
            dataType: "json",
            success: function (data) {
                if (data.data) {
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

        table.fnUpdate($('#coupon_edit_id').val(), nRow, 0, false);
        table.fnUpdate($('#coupon_edit_discount').val(), nRow, 1, false);
        table.fnUpdate($('#coupon_edit_strat').val(), nRow, 2, false);
        table.fnUpdate($('#coupon_edit_end').val(), nRow, 3, false);
        table.fnUpdate($('#coupon_edit_condition').val(), nRow, 4, false);
        table.fnDraw();
        $('#coupon_edit_id').val();
        $('#coupon_edit_discount').val();
        $('#coupon_edit_strat').val();
        $('#coupon_edit_end').val();
        $('#coupon_edit_condition').val();
        $('#coupon_edit_modal').modal('hide')
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
            var params = {};
            params.coid = aData[0];

            $.ajax({
                async: false,
                type: "POST",
                url: projectName+"/coupon/amountdelete",      //注意路径
                data: params,
                dataType: "json",
                success: function (data) {
                    if (data.data) {
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
