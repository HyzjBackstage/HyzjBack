/**
 *
 * xiaoyi 2018.9.22
 *
 **/

$(document).ready(function(){

    //上下文路径问题
    var pathName=window.document.location.pathname;
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

    //左侧，树状图
    var dataTank = [];
    refreshTree();
    function refreshTree() {
        dataTank = [];
        var param = {};
        $.ajax({
            async: false,
            type: "POST",
            url: projectName+"/express/OrdersList",
            data: param,
            dataType: "json",
            success:function (data) {
                // console.log(JSON.stringify(data,null,4));

                var oldData = data;
                for (var i = 0; i <oldData.length; i++){
                    var item = {};
                    var itm = oldData[i];
                    item.id = itm.o_id;
                    item.text = itm.o_id;
                    item.children = [];
                    dataTank.push(item);
                }
            },
            error:function(data){
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
    }

    //初始化树
    $('#using_json').data('jstree', false);
    $("#loading-express").css('display', 'none');
    $("#using_json").jstree({

        'plugins': ["wholerow", "types"],
        'core': {
            "themes": {
                "responsive": false
            },
            'data': dataTank
        },
        "types": {
            "default": {
                "icon": "glyphicon glyphicon-th-large "
            },
            "file": {
                "icon": "fa fa-file "
            }
        }
    });


    //点击事件
    var curtId = '';
    $('#using_json').on("changed.jstree", function (e, data) {

        var  id= data.selected;
        curtId = id;
        if(id=='') return;
        // console.log("select:" + id);
        loadByOrder(id);

    });

    //通过订单编号查询
    $("#btn_search").click(function(){
        var id = $("#order_code").val();
        if(id==''){
            swal({
                title: "订单编号不能为空！",
                text: "",
                type: "warning",
                allowOutsideClick: true,
                showConfirmButton: true,
                showCancelButton: false,
                confirmButtonClass: "btn-success",
                confirmButtonText: "OK",
            });
            return;
        }else{
            loadByOrder(id);
            console.log(id+'');
        }
    })

    // /**
    //  * 订单状态
    //  * @param status
    //  * @returns {*}
    //  */
    // function orderStatus(status){
    //     if (status == 1){
    //         return "已支付";
    //     }else if (status == 2){
    //         return "待发货";
    //     }else if (status == 3){
    //         return "已发货";
    //     }else if (status == 4){
    //         return "已完成";
    //     }
    // }

    /**
     * 通过id查询订单
     * @param id
     */
    function loadByOrder(id) {
        var param = {};
        param.o_id = ''+id;
        // console.log(param.o_id);
        $.ajax({
            async: false,
            type: "POST",
            url: projectName+"/express/SearchByOrder",
            data: param,
            dataType: "json",
            success:function (data) {
                var showdata = data;
                console.log(JSON.stringify(showdata,null,4));

                $("#order_code").val(showdata.t_orders.o_id);
                $("#customer_name").val(showdata.t_mallUser.name);
                $("#address_name").val(showdata.t_receiver.name);
                $("#address_text").val(showdata.t_receiver.province+showdata.t_receiver.city+showdata.t_receiver.county+showdata.t_receiver.address_details);

                // var status = orderStatus(showdata.t_orders.status);
                $("#order_status").val(showdata.t_orders.status);

                if(showdata.t_logistics_company != null ){
                    $("#express_name").val(showdata.t_logistics_company.lc_id);
                }else {
                    $("#express_name").val("kong");
                }
                if (showdata.t_logistics == null){
                    $("#express_id").val("");
                }else {
                    $("#express_id").val(showdata.t_logistics.shipment_number);
                }


            },
            error:function (data) {
                delok = false;

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
    }

    //提交按钮，进行修改
    $("#btn_alter_express").click(function(e){
        var delok = true;
        var params={};
        params.o_id = $("#order_code").val();
        params.lc_id =$('#express_name').val();
        params.status = $('#order_status').val();
        params.shipment_number =$('#express_id').val();

        console.log(params.lc_id);
        console.log(params.status);

        if(params.o_id =='' || params.lc_id  == '' || params.shipment_number  == '' || params.status == ''){
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
            url: projectName+"/express/updateOrder",//注意路径
            data: params,
            dataType: "json",
            success: function (data) {
                delok = data;
            },
            error: function (data) {
                //  console.log(JSON.stringify(data,null,4));

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

