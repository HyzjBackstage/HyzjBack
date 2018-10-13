$(document).ready(function () {

    //上下文路径问题
    var pathName=window.document.location.pathname;
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    var table = $("#agent_table");
    table.dataTable({
        "columnDefs": [{ // set default column settings
            'orderable': false,
            'targets': [4]
        }, {
            "searchable": false,
            "targets": [4]
        }],
        "order": [
            [1, "asc"]
        ]
    });


    /**
     * 获取角色数据，String转int
     * @param role
     * @returns {*}
     */
    function roleToNum(role) {
        if (role == "项目发起人") {
            return 1;
        } else if (role == "项目投资人") {
            return 2;
        } else if (role == "商城用户") {
            return 3;
        } else if (role == "代理商") {
            return 4;
        }
    }


    var params = {};
    $.ajax({
        async: false,
        type: "POST",
        url: projectName+"/agent/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            // console.log(JSON.stringify(data.data,null,4));
            for (var i = 0; i < data.data.length; i++) {
                var itm = data.data[i];

                table.fnAddData([
                    itm.user.m_id,
                    itm.user.name,
                    itm.user.phone,
                    itm.role.name,
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
        $('#agent_edit_role').val(roleToNum(aData[3]));

        malluserid = aData[0];

        $('#agent_edit_modal').modal('show')


    });


    $("#btn_edit_save").click(function (e) {
        $("#loading-agent").css('display', 'block');
        var nRow = EditRow;
        var delok = true;
        var params = {};
        params.role = $('#agent_edit_role').val();
        params.mid = malluserid;
        // alert(JSON.stringify(params,null,4));

        if (params.role == '' || params.mid == '') {
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
            url: projectName+"/agent/update",//注意路径
            data: params,
            dataType: "json",
            success: function (data) {


                $("#loading-agent").css('display', 'none');

                var itm = data.data;

                table.fnUpdate(itm.user.m_id, nRow, 0, false);
                table.fnUpdate(itm.user.name, nRow, 1, false);
                table.fnUpdate(itm.user.phone, nRow, 2, false);
                table.fnUpdate(itm.role.name, nRow, 3, false);
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