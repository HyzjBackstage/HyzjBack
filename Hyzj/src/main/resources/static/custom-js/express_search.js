/**
 * xiaoyi  2018.9.23
 */

$(document).ready(function () {

    $("#loading-express").css('display', 'none');

    function  loadByOrder(id){
        var params={};
        params.o_id = ''+id ;
        $.ajax({
            async: false,
            type: "POST",
            url: "express/SearchByOrder",//注意路径
            data: params,
            dataType: "json",
            success: function (data) {
                console.log(JSON.stringify(data,null,4));

                $("#loading-express").css('display', 'none');

                if(data == null){
                    $('#expresslist').html('');
                    $('#express-info').html('');
                    $('#express-warning').html('该订单暂无物流信息！');
                    return;
                }
                $('#express-warning').html('');

                var ss =
                    '<div class="timeline-item " style="margin:10px auto;width: 70%;">'+
                    '<div class="col-sm-3 ">'+
                    '快递编号：'+
                    '</div>'+
                    '<div class="col-sm-9  no-top-border">'+
                    '<p class="m-b-xs"><strong id="expressid">'+data.t_logistics.shipment_number+'</strong></p>'+
                    '</div>'+
                    '</div>'+
                    '<div class="timeline-item " style="margin:10px auto;width: 70%;">'+
                    '<div class="col-sm-3 ">'+
                    '收货人：'+
                    '</div>'+
                    '<div class="col-sm-9  no-top-border">'+
                    '<p class="m-b-xs"><strong id="expressid">'+data.t_receiver.name+'</strong></p>'+
                    '</div>'+
                    '</div>'+
                    '<div class="timeline-item " style="margin:10px auto;width: 70%;">'+
                    '<div class="col-sm-3 ">'+
                    '下单人：'+
                    '</div>'+
                    '<div class="col-sm-9  no-top-border">'+
                    '<p class="m-b-xs"><strong id="expressid">'+data.t_mallUser.name+'</strong></p>'+
                    '</div>'+
                    '</div>'+
                    '<div class="timeline-item " style="margin:10px auto;width: 70%;">'+
                    '<div class="col-sm-3 ">'+
                    '下单时间：'+
                    '</div>'+
                    '<div class="col-sm-9  no-top-border">'+
                    '<p class="m-b-xs"><strong id="expressid">'+data.t_orders.order_time+'</strong></p>'+
                    '</div>'+
                    '</div>'+
                    '<div class="timeline-item " style="margin:10px auto;width: 70%;">'+
                    '<div class="col-sm-3 ">'+
                    '收货地址：'+
                    '</div>'+
                    '<div class="col-sm-9  no-top-border">'+
                    '<p class="m-b-xs"><strong id="expressid">'+data.t_receiver.province + '省' + data.t_receiver.city + '市' +
                    data.t_receiver.county + '县' + data.t_receiver.address_details + '</strong></p>'+
                    '</div>'+
                    '</div>'+
                    '<div class="timeline-item " style="margin:10px auto;width: 70%;">'+
                    '<div class="col-sm-3 ">'+
                    '快递公司：'+
                    '</div>'+
                    '<div class="col-sm-9  no-top-border">'+
                    '<p class="m-b-xs"><strong id="expressname">'+data.t_logistics_company.name+'</strong></p>'+
                    '</div>'+
                    '</div>'+
                    '<div class="timeline-item " style="margin:10px auto;width: 70%;">'+
                    '<div class="col-sm-3 ">'+
                    '快递查询网址：'+
                    '</div>'+
                    '<div class="col-sm-9  no-top-border">'+
                    '<p class="m-b-xs"><strong id="express-state">'+data.t_logistics_company.url  +'</strong></p>'+
                    '</div>'+
                    '</div>';
                $('#express-info').html(ss);

            }, error: function (data) {
                // console.log(JSON.stringify(data,null,4));
                //alert("数据获取失败 ！");
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
        }
        $("#loading-express").css('display', 'block');

        loadByOrder(id);
    })


});