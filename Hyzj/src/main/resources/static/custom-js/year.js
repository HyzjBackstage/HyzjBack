/**
 * Created by 14260 on 2018/6/11.
 *
 * --年龄段管理--
 */
$(document).ready(function(){

    var table = $("#age_table");
    table.dataTable({
        "columnDefs": [{ // set default column settings
            'orderable': false,
            'targets': [2]
        }, {
            "searchable": false,
            "targets": [2]
        }],
        "order": [
            [0, "asc"]
        ]
    });
    var params={};
    $.ajax({
        async: false,
        type: "POST",
        url: "../year/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            // console.log(JSON.stringify(data.data,null,4));
            for (var i = 0; i < data.data.length; i++) {
                var itm = data.data[i];
                table.fnAddData([
                    itm.y_id,
                    itm.yaer,
                    '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>' +
                    '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                    '<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
                ]);
            }
        },
        error: function (data) {
            console.log(JSON.stringify(data,null,4));
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
    $("#loading-age").css('display','none');


    /**
     * 添加
     */
    $("#btn_add_save").click(function(e){
        var delok = true;
        var params={};
        params.yid = $('#year_add_age').val();
        params.name = $('#year_add_name').val();

        if(params.yid == '' || params.name == '' ){
            swal({
                title: "规格编号不能为空，规格不能为空！",
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
            url: "../year/add",          //注意路径
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
        table.fnAddData([
            $('#year_add_age').val(),
            $('#year_add_name').val(),
            '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>' +
            '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
            '<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
        ]);

        // console.log($('#age_add_age').val());
        // console.log($('#age_add_name').val());
        // console.log($('#age_add_min').val());
        // console.log($('#age_add_max').val());

        table.fnDraw();
        $('#year_add_age').val();
        $('#year_add_name').val();
        $('#year_add_modal').css('display','none');
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
        $('#year_add_modal').modal('hide');
        // $('#year_add_modal').css('display','none');
    });

    /**
     * 编辑
     */
    var  EditRow = -1;
    table.on('click', '.edit', function (e) {
        e.preventDefault();

        var nRow = $(this).parents('tr')[0];
        EditRow = nRow;
        var aData = table.fnGetData(nRow);

        $('#year_edit_age').val(aData[0]);
        $('#year_edit_name').val(aData[1]);
        $('#year_edit_modal').modal('show')
    });


    $("#btn_edit_save").click(function(e){
        var nRow = EditRow;
        var delok = true;
        var params={};
        params.yid = $('#year_edit_age').val();
        params.name =$('#year_edit_name').val();

        // console.log(params.agesection);
        // console.log(params.name);
        // console.log(params.minage);
        // console.log(params.maxage);

        if(params.name == '' || params.yid==''){
            swal({
                title: "年份不能为空！",
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
            url: "../year/update",       //注意路径
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

        table.fnUpdate($('#year_edit_age').val(), nRow, 0, false);
        table.fnUpdate($('#year_edit_name').val(), nRow, 1, false);
        table.fnDraw();
        $('#year_edit_age').val();
        $('#year_edit_name').val();
        $('#year_edit_modal').modal('hide');
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
            params.y_id = aData[0];

            $.ajax({
                async: false,
                type: "POST",
                url: "../year/delete",      //注意路径
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
