/**
 * Created by 14260 on 2018/6/11.
 */

$(document).ready(function () {

    var table = $("#order_table");
    table.dataTable({
        "columnDefs": [{
            'orderable':false,
            'targets':[8]
        }],
        "order": [
            [0, "asc"]
        ]
    });

    //查看全部订单
    var params = {};
    $.ajax({
        async: false,
        type: "POST",
        url: "order/list",//注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            console.log(JSON.stringify(data, null, 4));
            for (var i = 0; i < data.length; i++) {
                var itm = data[i];

                var time = itm.order_time;
                table.fnAddData([
                    itm.t_orders.o_id,
                    itm.t_orders.order_time,
                    itm.t_receiver.name,
                    itm.t_mallUser.name,
                    itm.coupon.condition_use,
                    itm.t_orders.price,
                    itm.t_orders.status,
                    itm.t_orders.self_lifting,
                    '<a class="View"  ><i class="fa fa-eye"></i>&nbsp;查看</a>' +
                    '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
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
                time.time_start = start.format('YYYY-MM-DD');
                time.time_end = end.format('YYYY-MM-DD');
            }
        );
    };
    init();
    //将已有的查询框掩藏
    $("#order_table_wrapper.row").hide();


    //通过时间查找查看
    $("#seachByTime").click(function (e) {

        $.ajax({
            async: false,
            type: "POST",
            url: "order/seachByTime",//注意路径
            data: time,
            dataType: "json",
            success: function (data) {
                console.log(JSON.stringify(data, null, 4));
                if (data != null) {
                    table.dataTable().fnClearTable();
                    for (var i = 0; i < data.length; i++) {

                        var itm = data[i];
                        table.fnAddData([
                            itm.t_orders.o_id,
                            itm.t_orders.order_time,
                            itm.t_receiver.name,
                            itm.t_mallUser.name,
                            itm.coupon.condition_use,
                            itm.t_orders.price,
                            itm.t_orders.status,
                            itm.t_orders.self_lifting,
                            '<a class="View"  ><i class="fa fa-eye"></i>&nbsp;查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                            '<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
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
        seachOrder.o_id = $("#seachValue").val();
        $.ajax({
            async: false,
            type: "POST",
            url: "order/seachById",//注意路径
            data: seachOrder,
            dataType: "json",
            success: function (data) {
                console.log(JSON.stringify(data, null, 4));

                if (data != null) {
                    table.dataTable().fnClearTable();
                    var itm = data;
                    table.fnAddData([
                        itm.t_orders.o_id,
                        itm.t_orders.order_time,
                        itm.t_receiver.name,
                        itm.t_mallUser.name,
                        itm.coupon.condition_use,
                        itm.t_orders.price,
                        itm.t_orders.status,
                        itm.t_orders.self_lifting,
                        '<a class="View"  ><i class="fa fa-eye"></i>&nbsp;查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                        '<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
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
            params.o_id = aData[0];
            $.ajax({
                async: false,
                type: "POST",
                url: "order/delete",//注意路径
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
