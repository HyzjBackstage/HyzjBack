/**
 * Created by 14260 on 2018/7/05.
 *
 * --镜片管理--
 */
$(document).ready(function () {

    //上下文路径问题
    var pathName=window.document.location.pathname;
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

    $(".footable").footable();

    var initJs = false;

    function dynamicLoadJs(url, callback) {
        var head = document.getElementsByTagName('head')[0];
        var script = document.createElement('script');
        script.type = 'text/javascript';
        script.src = url;
        if (typeof(callback) == 'function') {
            script.onload = script.onreadystatechange = function () {
                if (!this.readyState || this.readyState === "loaded" || this.readyState === "complete") {
                    callback();
                    script.onload = script.onreadystatechange = null;
                }
            };
        }
        head.appendChild(script);
    }

    //如果是点击添加，就会显示添加界面，如果是编辑，就会是编辑界面
    $("#btn-add-a").click(function () {

        if (!initJs) {
            dynamicLoadJs('js/demo/webuploader-demo.min.js', function (e) {
                console.log('加载成功')
            });
            initJs = true;
        }

        $("#commodity").removeAttr('readonly');
        $("#addname").val('');
        $("#stock").val('');
        $("#price").val("");
        $("#purchase_price").val("");
        $("#productionDate").val("");
        $("#edit_img").attr("src","");
        //动态修改标签，将添加和编辑是同一个界面，只是根据title来区分，哪个是编辑、添加
        $("#commodity_title").text('商品信息添加');


        $("#page_commodity_list").css('display', 'none');
        $("#page_commodity_add").css('display', 'block');
    });
    //返回，将添加界面，关闭，开启List

    $("#btn-back-a").click(function () {
        $("#page_commodity_list").css('display', 'block');
        $("#page_commodity_add").css('display', 'none');
    });


    /**
     *初始化
     */
    var params = {};
    $.ajax({
        async: false,
        type: "POST",
        url: projectName+"/commodity/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            console.log(JSON.stringify(data.data, null, 4));
            //循环显示data里面的List<EyeglassVo>数据
            for (var i = 0; i < data.data.length; i++) {
                var datas = data.data[i];
                console.log(JSON.stringify(datas, null, 4));
                // //获取附件里面的图片
                // var attachStr = '';
                // for (var j = 0; j < datas.t_attachments.length; j++) {
                //     var itm = datas.t_attachments[j];
                //     //图片上传的路径是：jx.lczj/goods/
                //     attachStr += '&nbsp;&nbsp;&nbsp;<image style="width: 45px;height: 45px;" src="../goods/' + itm.path + '"></image>'
                // }

                //显示一条条数据
                console.log(str1);

                //
                //alert(attachStr);
                var str1 = '<tr>' +
                    '<td>' + datas.commodity.c_id + '</td>' +
                    '<td>' + datas.commodity.name + '</td>' +
                    '<td>' + datas.commodity.stock + '</td>' +
                    '<td>' + datas.commodity.purchase_price + '</td>' +
                    '<td>' + datas.commodity.price + '</td>' +
                    '<td>' + datas.commodity.shelfDate + '</td>年' +
                    '<td>' + datas.commodity.productionDate + '</td>' +
                    '<td>' + datas.year.year + '</td>' +
                    '<td>' + '<img src="' +"../goods/"+ datas.commodity.image + '" style="width: 45px;height: 45px;cursor:pointer;" alt="图片未存在" onclick="javascript:window.open(this.src) "></td>' +
                    '<td>' + datas.commodity.addTime + '</td>' +
                    //id，动态添加数据可以相同
                    '<td><a class="edit"  id="' + datas.commodity.c_id+ '"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" id="' + datas.commodity.c_id+ '" ><i class="fa fa-trash"></i>&nbsp;删除</a></td> ' +
                    '</tr>';
                $("#tbd").prepend(str1).trigger('footable_redraw');
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


    /**
     * 规格功能
     * 提前将数据读取，放到下拉单中
     */
    var params = {};
    $.ajax({
        async: false,
        type: "POST",
        url: projectName+"/year/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            var str = '';
            //console.log(JSON.stringify(data,null,4));
            for (var i = 0; i < data.data.length; i++) {
                var itm = data.data[i];
                str += '<option value="' + itm.y_id + '">' + itm.year + '</option>';
            }
            //添加到功能下拉单中
            $("#add_year").html(str);

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

    //将加载动态效果掩藏
    $("#loading-CommodityStorage").css('display', 'none');

    /**
     * 添加/修改
     */
    $("#btn_add_save").click(function (e) {

        //获取<form id= eyeglass_add>表单的数据
        var formData1 = new FormData($("#eyeglass_add")[0]);
        //判断数据是否为空
        if (formData1.get("commodity") == null ||
            formData1.get("addname") == null ||
            formData1.get("year") == null ||
            formData1.get("stock") == null ||
            formData1.get("price") == null ||
            formData1.get("purchaseprice") == null ||
            formData1.get("shelfDate") == null ||
            formData1.get("productionDate") == null ||
            formData1.get("detailfile") == null

        ) {
            swal({
                title: "输入框不能为空！",
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

        /**
         * 点击添加或者修改将title.text()改变，然后这里判断点击了什么，做相对应处理
         */
        if ($("#commodity_title").text() == '商品信息添加') {
            //眼镜信息添加
            var delok = true;
            $.ajax({
                async: false,
                url: projectName+'/commodity/add',//路径
                type: 'POST',
                dataType: "json",
                contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置(详见：#1)
                processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post，详见：#2)
                data: formData1,
                success: function (data) {
                    /**
                     * 添加成功，获取得到的数据，立马显示浏览界面
                     */
                    console.log(JSON.stringify(data, null, 4));
                    // var attachStr = '';
                    // for (var j = 0; j < datas.t_attachments.length; j++) {
                    //     var itm = datas.t_attachments[j];
                    //     attachStr += '&nbsp;&nbsp;&nbsp;<image style="width: 45px;height: 45px;" src="../goods/' + itm.path + '"></image>'
                    // }
                    var datas = data.data;

                    //alert(attachStr);
                    var str1 = '<tr>' +
                        '<td>' + datas.commodity.c_id + '</td>' +
                        '<td>' + datas.commodity.name + '</td>' +
                        '<td>' + datas.commodity.stock + '</td>' +
                        '<td>' + datas.commodity.purchase_price + '</td>' +
                        '<td>' + datas.commodity.price + '</td>' +
                        '<td>' + datas.commodity.shelfDate + '</td>年' +
                        '<td>' + datas.commodity.productionDate + '</td>' +
                        '<td>' + datas.year.year + '</td>' +
                        '<td>' + '<img src="' + "../goods/"+ datas.commodity.image + '" style="width: 45px;height: 45px;cursor:pointer;" alt="图片未存在" onclick="javascript:window.open(this.src) "></td>' +
                        '<td>' + datas.commodity.addTime + '</td>' +
                        //id，动态添加数据可以相同
                        '<td><a class="edit"  id="' + datas.commodity.c_id+ '"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" id="' + datas.commodity.c_id+ '" ><i class="fa fa-trash"></i>&nbsp;删除</a></td> ' +
                        '</tr>';
                    //解除edit 、 delete 原有的点击事件

                    console.log(str1);
                    $("#tbd").prepend(str1).trigger('footable_redraw');

                    setEvents();

                    $("#commodity").val('');
                    $("#addname").val('');
                    $("#stock").val("");
                    $("#price").val("");
                    $("#productionDate").val("");

                    $("#page_commodity_list").css('display', 'block');
                    $("#page_commodity_add").css('display', 'none');
                    $('[data-dismiss="fileinput"]').click();
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
                    return;

                },
                error: function (data) {
                    console.log(data.data)
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
            });
        } else {
            //眼镜信息修改
            var delok = true;
            $.ajax({
                async: false,
                url: projectName+'/commodity/update',//路径
                type: 'POST',
                dataType: "json",
                contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置(详见：#1)
                processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post，详见：#2)
                data: formData1,
                success: function (data) {
                    console.log(JSON.stringify(data.data, null, 4));
                    //删除原有一条记录，再重新添加
                    var nRow = EditRow;
                    if (nRow.nextSibling != null && nRow.nextSibling.innerHTML != null && nRow.nextSibling.innerHTML.indexOf('footable-row-detail-cell') != -1) {
                        nRow.nextSibling.remove();
                    }
                    nRow.remove();

                    //alert(ageStr);
                    //获取附件
                    // var attachStr = '';
                    // for (var j = 0; j < datas.t_attachments.length; j++) {
                    //     var itm = datas.t_attachments[j];
                    //     attachStr += '&nbsp;&nbsp;&nbsp;<image style="width: 45px;height: 45px;" src="../goods/' + itm.path + '"></image>'
                    // }
                    var datas = data.data;
                    //alert(attachStr);
                    var str1 = '<tr>' +
                        '<td>' + datas.commodity.c_id + '</td>' +
                        '<td>' + datas.commodity.name + '</td>' +
                        '<td>' + datas.commodity.stock + '</td>' +
                        '<td>' + datas.commodity.purchase_price + '</td>' +
                        '<td>' + datas.commodity.price + '</td>' +
                        '<td>' + datas.commodity.shelfDate + '</td>年' +
                        '<td>' + datas.commodity.productionDate + '</td>' +
                        '<td>' + datas.year.year + '</td>' +
                        '<td>' + '<img src="' + "../goods/"+ datas.commodity.image + '" style="width: 45px;height: 45px;cursor:pointer;" alt="图片未存在" onclick="javascript:window.open(this.src) "></td>' +
                        '<td>' + datas.commodity.addTime + '</td>' +
                        //id，动态添加数据可以相同
                        '<td><a class="edit"  id="' + datas.commodity.c_id+ '"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" id="' + datas.commodity.c_id+ '" ><i class="fa fa-trash"></i>&nbsp;删除</a></td> ' +
                        '</tr>';


                    console.log(str1);
                    $("#tbd").prepend(str1).trigger('footable_redraw');
                    //解除edit 、 delete 原有的点击事件，重新添加
                    setEvents();
                    //修改完后，将添加界面去掉，显示list
                    $("#page_commodity_list").css('display', 'block');
                    $("#page_commodity_add").css('display', 'none');
                    $('[data-dismiss="fileinput"]').click();
                    swal({
                        title: "修改成功！",
                        text: "",
                        type: "success",
                        allowOutsideClick: true,
                        showConfirmButton: true,
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: "OK",
                    });
                    return;

                },
                error: function (data) {
                    console.log(data.data)
                    swal({
                        title: "修改失败！",
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
        }

    });

    var EditRow = '-1';
    setEvents();

    function setEvents() {
        $(".delete").unbind("click");
        $(".edit").unbind("click");
        /**
         * 删除
         */
        $(".delete").click(function (e) {

            var nRow = $(this).parents('tr')[0];
            //获取eyeglass
            var id = $(this).attr('id');


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
                params.cid = id;
                $.ajax({
                    async: false,
                    type: "POST",
                    url: projectName+"/commodity/delete",//注意路径
                    data: params,
                    dataType: "json",
                    success: function (data) {
                        if (!data.data) {
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
        /**
         * 编辑
         */
        $(".edit").click(function (e) {

            if (!initJs) {
                dynamicLoadJs('js/demo/webuploader-demo.min.js', function (e) {
                    console.log('加载成功')
                });
                initJs = true;
            }
            var nRow = $(this).parents('tr')[0];
            EditRow = nRow;
            //获取eyeglass
            var id = $(this).attr('id') + '';
            /*alert(id);
             alert(nRow.innerHTML);*/
            $("#div-alter").css("display", 'block');
            $("#div-list").css("display", 'none');

            params = {};
            params.cid = id;
            $.ajax({
                async: false,
                type: "POST",
                url: projectName+"/commodity/loadById",//注意路径
                data: params,
                dataType: "json",
                success: function (data) {
                    var datas = data.data;
                    console.log(JSON.stringify(datas, null, 4));
                    $("#commodity").val(datas.commodity.c_id);
                    $("#addname").val(datas.commodity.name);
                    $("#add_year").val(datas.year.y_id);
                    $("#stock").val(datas.commodity.stock);
                    $("#price").val(datas.commodity.price);
                    $("#purchase_price").val(datas.commodity.purchase_price);
                    $("#shelfDate").val(datas.commodity.shelfDate);
                    $("#productionDate").val(datas.commodity.productionDate);

                    $("#edit_img").attr('src', 'goods/' + datas.commodity.image);
                    //alert(ageStr);

                   /*  $("#atts_list").html('');*/
                    // //将附件里面的图片获取出来
                    // var attachStr = '';
                    // for (var j = 0; j < datas.t_attachments.length; j++) {
                    //     var itm = datas.t_attachments[j];
                    //     var response = itm.attachment;
                    //     var str = '<div class="col-sm-12" id="' + response + '">' +
                    //         '<div class="col-sm-8">' +
                    //         '<input name="fileName" value="' + response + '" readonly style="background-color: white;border: none;outline: none;" class="form-control" type="hidden">' +
                    //         '<input  value="' + response + '.png" readonly style="background-color: white;border: none;outline: none;" class="form-control" type="text">' +
                    //         '</div>' +
                    //         '<div class="col-sm-4" style="padding-top: 3px;" >' +
                    //         '<a  style="float: right;"    class="atts_deletes" ><i class="fa fa-times-circle"></i></a>' +
                    //         '</div>' +
                    //         '</div>';
                    //     //添加图片
                    //     $("#atts_list").append(str);
                    // }

                    //将删除解除click，重新添加事件
                   /* $(".atts_deletes").unbind('click');

                    $(".atts_deletes").click(function (e) {
                        var divs = $(this).parents('div')[0].parentNode;
                        //alert(divs.id);

                        var params = {};
                        params.code = divs.id + '';
                        $.ajax({
                            async: false,
                            type: "POST",
                            url: "../eyeglass/deleteAttach",//注意路径
                            data: params,
                            dataType: "json",
                            success: function (datas) {
                                if (datas) {
                                    $('#' + divs.id).remove();
                                } else {
                                    swal({
                                        title: "移除失败！",
                                        text: "",
                                        type: "error",
                                        allowOutsideClick: true,
                                        showConfirmButton: true,
                                        showCancelButton: false,
                                        confirmButtonClass: "btn-danger",
                                        confirmButtonText: "OK",
                                    });
                                }

                            }, error: function (err) {
                                console.log(JSON.stringify(err, null, 4));
                                swal({
                                    title: "移除失败！",
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
                    });*/

                    $("#commodity_title").text('商品信息修改');
                    $("#commodity").attr('readonly', 'readonly');
                    $("#page_commodity_list").css('display', 'none');
                    $("#page_commodity_add").css('display', 'block');

                }, error: function (err) {
                    console.log(JSON.stringify(err.data, null, 4))
                }
            });
        });
    }


});
