/**
 * Created by 14260 on 2018/6/11.
 */
$(document).ready(function(){

    var table = $("#stock_table");
    table.dataTable({
        "columnDefs": [{ // set default column settings
            'orderable': false,
            'targets': [3]
        }, {
            "searchable": false,
            "targets": [3]
        }],
        "order": [
            [2, "asc"]
        ]
    });
    var params={};
    $.ajax({
        async: false,
        type: "POST",
        url: "../stock/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            // console.log(JSON.stringify(data.data,null,4));
            for (var i = 0; i < data.data.length; i++) {
                var itm = data.data[i];

                table.fnAddData([
                    itm.c_id,
                    itm.name,
                    itm.stock,
                    '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +'<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>'
                ]);
            }
        },
        error: function (data) {
            console.log(data.data);
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
    $("#loading-stock").css('display','none');




    /**
     * 编辑
     */
    var  EditRow = -1;
    table.on('click', '.edit', function (e) {
        e.preventDefault();

        var nRow = $(this).parents('tr')[0];
        EditRow = nRow;
        var aData = table.fnGetData(nRow);

        $('#stock_edit_cid').val(aData[0]);
        $('#stock_edit_name').val(aData[1]);
        $('#stock_edit_number').val(aData[2]);
        $('#stock_edit_modal').modal('show')
    });


    $("#btn_edit_save").click(function(e){
        var nRow = EditRow;
        var delok = true;
        var params={};
        params.cid = $('#stock_edit_cid').val();
        params.stock =$('#stock_edit_number').val();

        if(params.cid == '' || params.stock == '' ){
            swal({
                title: "数量不能为空！",
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
            url: "../stock/update",//注意路径
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
                console.log(data.data);
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

        table.fnUpdate($('#stock_edit_cid').val(), nRow, 0, false);
        table.fnUpdate($('#stock_edit_name').val(), nRow, 1, false);
        table.fnUpdate($('#stock_edit_number').val(), nRow, 2, false);
        table.fnDraw();
        $('#stock_edit_cid').val('');
        $('#stock_edit_name').val('');
        $('#stock_edit_number').val('');
        $('#stock_edit_modal').modal('hide')
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




});
