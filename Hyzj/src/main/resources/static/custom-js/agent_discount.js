$(document).ready(function () {
    //上下文路径问题
    var pathName=window.document.location.pathname;
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

    var table = $("#agent_table");
    table.dataTable({
        "columnDefs": [{ // set default column settings
            'orderable': false,
            'targets': [6]
        }, {
            "searchable": false,
            "targets": [6]
        }],
        "order": [
            [4, "asc"]
        ]
    });
    /**
     * 提前将折扣拿到下拉框
     * @type {{}}
     */
    var params = {};
    $.ajax({
        async: false,
        type: "POST",
        url: projectName+"/agent/ShowDiscount",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {

            console.log(JSON.stringify(data,null,4));

            var temp = data.data;

            var str = '';
            for (var i = 0; i < temp.length; i++) {
                var itm = data.data[i];
                str += '<option value="' + itm + '">' + itm+'折' + '</option>';
            }


            //添加到功能下拉单中
            $("#agent_edit_discount").html(str);

        },
        error: function (data) {
            console.log(JSON.stringify(data.data,null,4));
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
    var param = {};
    $.ajax({
        async: false,
        type: "POST",
        url: projectName+"/agent/load",       //注意路径
        data: param,
        dataType: "json",
        success: function (data) {
            // console.log(JSON.stringify(data.data,null,4));
            for (var i = 0; i < data.data.length; i++) {
                var itm = data.data[i];

                table.fnAddData([
                    itm.agent.mid,
                    itm.mallUser.name,
                    itm.mallUser.phone,
                    itm.agent.addTime,
                    itm.agent.discount,
                    itm.stationer.name,
                    '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>'
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
    $("#loading-agent").css('display', 'none');
    /**
     * 编辑
     */
    var malluserid;
    var EditRow = -1;
    table.on('click', '.edit', function (e) {
        e.preventDefault();

        var nRow = $(this).parents('tr')[0];
        EditRow = nRow;
        var aData = table.fnGetData(nRow);

        $('#agent_edit_agent').val(aData[1]);
        $('#agent_edit_phone').val(aData[2]);

        malluserid = aData[0];

        $('#agent_edit_modal').modal('show')


    });


    $("#btn_edit_save").click(function (e) {
        $("#loading-agent").css('display', 'block');
        var nRow = EditRow;
        var delok = true;
        var params = {};
        params.discount = $('#agent_edit_discount').val();
        params.mid = malluserid;
        // alert(JSON.stringify(params,null,4));

        if (params.discount == '' || params.mid == '') {
            swal({
                title: "角色不能为空！",
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
            url: projectName+"/agent/updateDiscount",//注意路径
            data: params,
            dataType: "json",
            success: function (data) {


                $("#loading-agent").css('display', 'none');

                var itm = data.data;
                table.fnUpdate(itm.agent.mid, nRow, 0, false);
                table.fnUpdate(itm.mallUser.name, nRow, 1, false);
                table.fnUpdate(itm.agent.phone, nRow, 2, false);
                table.fnUpdate(itm.agent.addTime, nRow, 3, false);
                table.fnUpdate(itm.agent.discount, nRow, 4, false);
                table.fnUpdate(itm.stationer.name, nRow, 5, false);
                table.fnDraw();
                delok = true;

            },
            error: function (data) {

                $("#loading-agent").css('display', 'none');

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



        $('#agent_edit_agent').val('');
        $('#agent_edit_phone').val('');
        $('#agent_edit_modal').modal('hide')

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