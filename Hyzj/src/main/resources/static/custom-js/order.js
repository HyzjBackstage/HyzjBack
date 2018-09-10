/**
 * Created by 14260 on 2018/6/11.
 */
$(document).ready(function () {

    var table = $("#order_table");
    table.dataTable({
        "columnDefs": [{ // set default column settings
            'orderable': false,
            'targets': [4]
        }/*, {
            "searchable": false,
            "targets": [2]
        }*/],
        "order": [
            [0, "asc"]
        ]
    });

    $("#order_table_wrapper").find('.row')[0].remove();
    //查看全部订单
    var params = {};
    $.ajax({
        async: false,
        type: "POST",
        url: "../order/list",//注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            console.log(JSON.stringify(data, null, 4));
            for (var i = 0; i < data.length; i++) {

                var itm = data[i];
                table.fnAddData([
                    itm.order,
                    itm.sure_time,
                    itm.voucher,
                    itm.totalfee,
                    '<a class="View"  ><i class="fa fa-eye"></i>&nbsp;查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
                ]);
            }
        },
        error: function (data) {
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
    $("#loading-order").css('display', 'none');
    //初始化时间
    var time = {};

    function init() {
        //定义locale汉化插件
        var locale = {
            "format": 'YYYY-MM-DD',
            "separator": " -222 ",
            "applyLabel": "确定",
            "cancelLabel": "取消",
            "fromLabel": "起始时间",
            "toLabel": "结束时间'",
            "customRangeLabel": "自定义",
            "weekLabel": "W",
            "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
            "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            "firstDay": 1
        };
        //初始化显示当前时间
        $('#daterange-btn span').html(moment().subtract('hours', 1).format('YYYY-MM-DD') + ' - ' + moment().format('YYYY-MM-DD'));
        //日期控件初始化
        $('#daterange-btn').daterangepicker(
            {
                'locale': locale,
                //汉化按钮部分
                ranges: {
                    '今日': [moment(), moment()],
                    '昨日': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                    '最近7日': [moment().subtract(6, 'days'), moment()],
                    '最近30日': [moment().subtract(29, 'days'), moment()],
                    '本月': [moment().startOf('month'), moment().endOf('month')],
                    '上月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                },
                startDate: moment(),
                endDate: moment()
            },
            function (start, end) {
                $('#daterange-btn span').html(start.format('YYYY-MM-DD') + ' - ' + end.format('YYYY-MM-DD'));
                time.start = start.format('YYYY-MM-DD');
                time.end = end.format('YYYY-MM-DD');
            }
        );
    };
    init();
    //将已有的查询框掩藏
    $("#order_table_wrapper.row").hide();


    //通过时间查找查看
    $("#seachByTime").click(function (e) {

        // alert(time.start+":"+time.end);
        $.ajax({
            async: false,
            type: "POST",
            url: "../order/seachByTime",//注意路径
            data: time,
            dataType: "json",
            success: function (data) {
                console.log(JSON.stringify(data, null, 4));
                if (data != null) {
                    table.dataTable().fnClearTable();
                    for (var i = 0; i < data.length; i++) {

                        var itm = data[i];
                        table.fnAddData([
                            itm.order,
                            itm.sure_time,
                            itm.voucher,
                            itm.totalfee,
                            '<a class="View"  ><i class="fa fa-eye"></i>&nbsp;查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
                        ]);
                    }
                }
                if (data == null) {
                    table.dataTable().fnClearTable();
                }
            },
            error: function (data) {
                console.log(data);
                //   alert("数据获取失败 ！");
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
    });
    //通过订单号
    $("#seachByid").click(function (e) {
        var seachOrder = {};
        seachOrder.order = $("#seachValue").val();
        $.ajax({
            async: false,
            type: "POST",
            url: "../order/seachById",//注意路径
            data: seachOrder,
            dataType: "json",
            success: function (data) {
                console.log(JSON.stringify(data, null, 4));

                if (data != null) {
                    table.dataTable().fnClearTable();
                    var itm = data;
                    table.fnAddData([
                        itm.order,
                        itm.sure_time,
                        itm.voucher,
                        itm.totalfee,
                        '<a class="View"  ><i class="fa fa-eye"></i>&nbsp;查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
                    ]);
                }

                if (data == null) {
                    table.dataTable().fnClearTable();
                }
            },
            error: function (data) {
                console.log(data);
                //   alert("数据获取失败 ！");
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
    });
    //查看详情

    var EditRow = -1;
    table.on('click', '.View', function (e) {
        $("#page_order_list").hide();
        $("#loading-order").css('display', 'block');
        e.preventDefault();
        var nRow = $(this).parents('tr')[0];
        EditRow = nRow;
        var aData = table.fnGetData(nRow);
        var itm = {};
        itm.order = '' + aData[0];
        // alert(itm.occasion);
        var datas;
        $.ajax({
            async: false,
            url: '../orders/loadById',
            type: 'POST',
            dataType: "json",
            data: itm,
            success: function (data) {
                console.log(JSON.stringify(data, null, 5));
                if (data == null) {
                    return;
                }
                if (data != null) {
                    var item = data;
                    $("#view_edit_consignee").text(item.t_address.consignee);
                    $("#view_edit_address").text(item.t_address.provincename + ":" + item.t_address.cityname + ":" + item.t_address.countyname);
                    $("#view_edit_street").val(item.t_address.street);
                    $("#view_edit_phone").text(item.t_address.phone);
                    $("#view_edit_sure_time").text(item.t_order.sure_time);
                    $("#view_edit_order").text(item.t_order.order);
                    var price = 0;
                    var voucher = item.t_order.voucher;

                    var str = '';

                    for (var i = 0; i < item.mywearVos.length; i++) {

                        var param = item.mywearVos[i];

                        str += ' <table class="table table-hover "  style="border-top:10px solid #F0F0F0; ">\n' +
                            '                        <tbody>\n' +
                            '                        <tr>\n' +
                            '                            <td class="project-people" style="width:60px;">\n' +
                            '                                <img alt="image" class="img-circle" src="../goods/'+param.goodsVo.t_attachments[0].path+'">\n' +
                            '                            </td>\n' +
                            '                            <td class="project-title bold">\n' +
                            param.goodsVo.t_goods.name + "(" + param.goodsVo.t_goods.models + ")" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;颜色:" + param.t_color.name + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;脸型:" + param.t_face.name + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;场景:" + param.t_occasion.name + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;品牌:" + param.goodsVo.t_brand.name +
                            '                                <br/>\n' +
                            '\n' +
                            '                            </td>\n' +
                            '                            <td class="project-completion">\n' +
                            '                                <small>数量：1</small>\n' +
                            '                            </td>\n' +
                            '                            <td class="project-completion">\n' +
                            '                                <small>价格：￥' + param.goodsVo.t_goods.price + '</small>\n' +
                            '                            </td>\n' +
                            '                        </tr>\n' +
                            '                        <tr>\n' +
                            '                            <td class="project-people " style="width: 60px;">\n' +
                            '                                <img alt="image" class="img-circle" src="../goods/'+param.leftEyeglass.eyeglassVo.t_attachments[0].path+'">\n' +
                            '                            </td>\n' +
                            '                            <td class="project-title bold">\n' +
                            param.leftEyeglass.eyeglassVo.t_eyeglass.name + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;功能:" + param.leftEyeglass.eyeglassVo.t_efficacy.name + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;样式:" + param.leftEyeglass.eyeglassVo.t_style.name + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;膜层:" + param.leftEyeglass.eyeglassVo.t_mask.name + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;品牌:" + param.leftEyeglass.eyeglassVo.t_brand.name +
                            '                                <br/>\n' +
                            '\n' +
                            '                            </td>\n' +
                            '                            <td class="project-completion">\n' +
                            '                                <small>数量：1</small>\n' +
                            '                            </td>\n' +
                            '                            <td class="project-completion">\n' +
                            '                                <small>价格：￥' + param.leftEyeglass.eyeglassVo.t_eyeglass.price + '</small>\n' +
                            '                            </td>\n' +
                            '\n' +
                            '                        </tr>\n' +
                            '  <tr>\n' +
                            '                            <td class="project-people text-right"  >\n' +
                            '                                左眼: ' +
                            '                            </td>\n' +
                            '                            <td class="project-title" colspan="3">\n' +
                            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + param.leftEyeglass.eyeglassVo.t_eyeglass.name + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度数:" + param.leftEyeglass.t_wearglass.degress + "度" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;散光度数:" + param.leftEyeglass.t_wearglass.asdegress + "度" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;散光轴位:" + param.leftEyeglass.t_wearglass.axis + "度" +
                            '                            </td>\n' +
                            '\n' +
                            '                        </tr>\n' +
                            '                        <tr>\n' +
                            '                            <td class="project-people text-right">\n' +
                            '                                右眼:' +
                            '                            </td>\n' +
                            '                            <td class="project-title" colspan="3">\n' +
                            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + param.rightEyeglass.eyeglassVo.t_eyeglass.name + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度数:" + param.rightEyeglass.t_wearglass.degress + "度" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;散光度数:" + param.rightEyeglass.t_wearglass.asdegress + "度" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;散光轴位:" + param.rightEyeglass.t_wearglass.axis + "度" +
                            '                            </td>\n' +
                            '\n' +
                            '                        </tr>\n' +
                            '                        </tbody>\n' +
                            '                    </table>\n' +
                            '                    <br/>'


                        price += param.goodsVo.t_goods.price + param.leftEyeglass.eyeglassVo.t_eyeglass.price;
                    }

                    $('#mywearlist').html(str);

                    $("#view_edit_price").text(price);
                    $("#view_edit_voucher").text(voucher);
                    $("#view_edit_lastPrice").text(item.t_order.totalfee);

                }


            },
            error: function (data) {
                console.log(JSON.stringify(data, null, 5));
                swal({
                    title: "数据获取失败！",
                    text: "",
                    type: "warning",
                    allowOutsideClick: true,
                    showConfirmButton: true,
                    showCancelButton: false,
                    confirmButtonClass: "btn-success",
                    confirmButtonText: "OK",
                });
                return;
            }

        });

        $("#page_order_view").show();
        $("#loading-order").css('display', 'none');
    });

    //返回，将添加界面，关闭，开启List

    $("#btn-back-a").click(function () {
        $("#page_order_list").show();
        $("#page_order_view").hide();
    });

    /**
     * 编辑
     */
    var EditRow = -1;
    table.on('click', '.edit', function (e) {

        var nRow = $(this).parents('tr')[0];
        EditRow = nRow;
        var aData = table.fnGetData(nRow);
        $('#order_edit_order').val(aData[0]);
        $('#order_edit_price').val(aData[3]);
        $('#order_edit_modal').modal('show')
    });

    /**
     * 在详情里面去编辑
     */
    $("#edit_order").click(function (e) {
        var order = $("#view_edit_order").text();
        var price = $("#view_edit_lastPrice").text();
       // alert(order +":" + price);
        $('#order_edit_order').val(order);
        $('#order_edit_price').val(price);
        $('#order_edit_modal').modal('show');

    });

    //修改总计
    $("#btn_edit_save").click(function (e) {
        var nRow = EditRow;
        var delok = true;
        var itm = {};
        itm.order = $("#order_edit_order").val();
        itm.price = $("#order_edit_price").val();
        alert(itm.order + "::" + itm.price);
        $.ajax({
            async: false,
            url: '../order/update3',
            type: 'POST',
            dataType: "json",
            data: itm,
            success: function (data) {
                console.log(JSON.stringify(data, null, 5));
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
        table.fnUpdate($('#order_edit_order').val(), nRow, 0, false);
        table.fnUpdate($('#order_edit_price').val(), nRow, 3, false);
        table.fnDraw();
        $('#order_edit_order').val('');
        $('#order_edit_price').val('');
        $('#order_edit_modal').modal('hide');
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
        //alert("delete:"+aData[0]);
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
            //保存返回的删除是否
            var delok = true;
            var params = {};
            params.order = aData[0];
            $.ajax({
                async: false,
                type: "POST",
                url: "../orders/delete",//注意路径
                data: params,
                dataType: "json",
                success: function (data) {
                    if (!data) {
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
            //删除完了，就将显示界面删除一行
            if (nRow.nextSibling != null && nRow.nextSibling.innerHTML != null && nRow.nextSibling.innerHTML.indexOf('footable-row-detail-cell') != -1) {
                nRow.nextSibling.remove();
            }
            nRow.remove();
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
