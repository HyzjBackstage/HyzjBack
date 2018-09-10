/**
 * Created by 14260 on 2018/7/05.
 *
 * --镜片管理--
 */
$(document).ready(function () {

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

        $("#add_eyeglass").removeAttr('readonly');
        $("#atts_list").html('');
        $("#add_eyeglass").val('');
        $("#add_name").val('');
        $("#add_refraction").val("");
        $("#add_price").val("");
        $("#edit_img").attr("src","");
        //动态修改标签，将添加和编辑是同一个界面，只是根据title来区分，哪个是编辑、添加
        $("#eyeglass_title").text('镜片信息添加');


        $("#page_eyeglass_list").css('display', 'none');
        $("#page_eyeglass_add").css('display', 'block');
    });
    //返回，将添加界面，关闭，开启List

    $("#btn-back-a").click(function () {
        $("#page_eyeglass_list").css('display', 'block');
        $("#page_eyeglass_add").css('display', 'none');
    });


    /**
     *初始化
     */
    var params = {};
    $.ajax({
        async: false,
        type: "POST",
        url: "../eyeglass/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            console.log(JSON.stringify(data, null, 4));
            //循环显示data里面的List<EyeglassVo>数据
            for (var i = 0; i < data.length; i++) {
                var datas = data[i];
                console.log(JSON.stringify(datas, null, 4));
                //获取附件里面的图片
                var attachStr = '';
                for (var j = 0; j < datas.t_attachments.length; j++) {
                    var itm = datas.t_attachments[j];
                    //图片上传的路径是：jx.lczj/goods/
                    attachStr += '&nbsp;&nbsp;&nbsp;<image style="width: 45px;height: 45px;" src="../goods/' + itm.path + '"></image>'
                }

                //显示一条条数据
                //alert(attachStr);
                var str1 = '<tr>' +
                    '<td>' + datas.t_eyeglass.name + '</td>' +
                    '<td>' + datas.t_brand.name + '</td>' +
                    '<td>' + datas.t_mask.name + '</td>' +
                    '<td>' + datas.t_eyeglass.refraction + '</td>' +
                    '<td>' + datas.t_eyeglass.price + '</td>' +
                    '<td>' + datas.t_eyeglass.eyeglass + '</td>' +
                    '<td>' + datas.t_efficacy.name + '</td>' +
                    '<td>' + datas.t_style.name + '</td>' +
                    '<td>' + attachStr + '</td>' +
                    '<td>' + '<img src="' + "../goods/" + datas.t_eyeglass.detailphoto + '" style="width: 45px;height: 45px;cursor:pointer;" alt="图片未存在" onclick="javascript:window.open(this.src) "></td>' +
                    //id，动态添加数据可以相同
                    '<td><a class="edit"  id="' + datas.t_eyeglass.eyeglass + '"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" id="' + datas.t_eyeglass.eyeglass + '" ><i class="fa fa-trash"></i>&nbsp;删除</a></td> ' +
                    '</tr>';

                console.log(str1);
                //
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
     * 镜片材质
     * 提前将数据读取，放到下拉单中
     */
    /*    var params = {};
        $.ajax({
            async: false,
            type: "POST",
            url: "../category/list",       //注意路径
            data: params,
            dataType: "json",
            success: function (data) {
                var str = '';
                //console.log(JSON.stringify(data,null,4));
                for (var i = 0; i < data.length; i++) {
                    var itm = data[i];
                    str += '<option value="' + itm.category + '">' + itm.name + '</option>';
                }
                //添加到材质下拉单中
                $("#add_category").html(str);

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
        });*/

    /**
     * 镜片功能
     * 提前将数据读取，放到下拉单中
     */
    var params = {};
    $.ajax({
        async: false,
        type: "POST",
        url: "../efficacy/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            var str = '';
            //console.log(JSON.stringify(data,null,4));
            for (var i = 0; i < data.length; i++) {
                var itm = data[i];
                str += '<option value="' + itm.efficacy + '">' + itm.name + '</option>';
            }
            //添加到功能下拉单中
            $("#add_efficacy").html(str);

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
     * 镜片品牌
     * 提前将数据读取，放到下拉单中
     */
    var params = {};
    $.ajax({
        async: false,
        type: "POST",
        url: "../brand/list1",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            var str = '';
            //console.log(JSON.stringify(data,null,4));
            for (var i = 0; i < data.length; i++) {
                var itm = data[i];
                str += '<option value="' + itm.brand + '">' + itm.name + '</option>';
            }
            //添加到品牌下拉单中
            $("#add_brand").html(str);

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
     * 镜片膜层
     * 提前将数据读取，放到下拉单中
     */
    var params = {};
    $.ajax({
        async: false,
        type: "POST",
        url: "../mask/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            var str = '';
            //console.log(JSON.stringify(data,null,4));
            for (var i = 0; i < data.length; i++) {
                var itm = data[i];
                str += '<option value="' + itm.mask + '">' + itm.name + '</option>';
            }
            //添加到膜层下拉单中
            $("#add_mask").html(str);

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
     * 镜片设计样式
     * 提前将数据读取，放到下拉单中
     */
    var params = {};
    $.ajax({
        async: false,
        type: "POST",
        url: "../style/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            var str = '';
            //console.log(JSON.stringify(data,null,4));
            for (var i = 0; i < data.length; i++) {
                var itm = data[i];
                str += '<option value="' + itm.style + '">' + itm.name + '</option>';
            }
            //添加到样式下拉单中
            $("#add_style").html(str);

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

    //将加载动态效果掩藏
    $("#loading-eyeglass").css('display', 'none');

    /**
     * 添加/修改
     */
    $("#btn_add_save").click(function (e) {

        //获取<form id= eyeglass_add>表单的数据
        var formData1 = new FormData($("#eyeglass_add")[0]);
        //判断数据是否为空
        if (formData1.get("eyeglass") == null ||
            formData1.get("price") == null ||
            formData1.get("efficacy") == null ||
            formData1.get("brand") == null ||
            formData1.get("mask") == null ||
            formData1.get("add_style") == null ||
            formData1.get("refraction") == null ||
            formData1.get("add_name") == null ||
            formData1.get("fileName") == null ||
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
        if ($("#eyeglass_title").text() == '镜片信息添加') {
            //眼镜信息添加
            var delok = true;
            $.ajax({
                async: false,
                url: '../eyeglass/add',//路径
                type: 'POST',
                dataType: "json",
                contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置(详见：#1)
                processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post，详见：#2)
                data: formData1,
                success: function (datas) {
                    /**
                     * 添加成功，获取得到的数据，立马显示浏览界面
                     */
                    console.log(JSON.stringify(datas, null, 4));
                    var attachStr = '';
                    for (var j = 0; j < datas.t_attachments.length; j++) {
                        var itm = datas.t_attachments[j];
                        attachStr += '&nbsp;&nbsp;&nbsp;<image style="width: 45px;height: 45px;" src="../goods/' + itm.path + '"></image>'
                    }


                    //alert(attachStr);
                    var str1 = '<tr>' +
                        '<td>' + datas.t_eyeglass.name + '</td>' +
                        '<td>' + datas.t_brand.name + '</td>' +
                        '<td>' + datas.t_mask.name + '</td>' +
                        '<td>' + datas.t_eyeglass.refraction + '</td>' +
                        '<td>' + datas.t_eyeglass.price + '</td>' +
                        '<td>' + datas.t_eyeglass.eyeglass + '</td>' +
                        '<td>' + datas.t_efficacy.name + '</td>' +
                        '<td>' + datas.t_style.name + '</td>' +
                        '<td>' + attachStr + '</td>' +
                        '<td>' + '<img src="' + "../goods/" + datas.t_eyeglass.detailphoto + '" style="width: 50px;height: 50px;cursor:pointer;" onclick="javascript:window.open(this.src)" ></td>' +
                        '<td><a class="edit"  id="' + datas.t_eyeglass.eyeglass + '"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" id="' + datas.t_eyeglass.eyeglass + '" ><i class="fa fa-trash"></i>&nbsp;删除</a></td> ' +
                        '</tr>';
                    //解除edit 、 delete 原有的点击事件

                    console.log(str1);
                    $("#tbd").prepend(str1).trigger('footable_redraw');

                    setEvents();

                    $("#eyeglass").val('');
                    $("#add_name").val('');
                    $("#add_refraction").val("");
                    $("#add_price").val("");
                    $("#page_eyeglass_list").css('display', 'block');
                    $("#page_eyeglass_add").css('display', 'none');
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
                    console.log(data)
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
                url: '../eyeglass/update',//路径
                type: 'POST',
                dataType: "json",
                contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置(详见：#1)
                processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post，详见：#2)
                data: formData1,
                success: function (datas) {
                    console.log(JSON.stringify(datas, null, 4));
                    //删除原有一条记录，再重新添加
                    var nRow = EditRow;
                    if (nRow.nextSibling != null && nRow.nextSibling.innerHTML != null && nRow.nextSibling.innerHTML.indexOf('footable-row-detail-cell') != -1) {
                        nRow.nextSibling.remove();
                    }
                    nRow.remove();

                    //alert(ageStr);
                    //获取附件
                    var attachStr = '';
                    for (var j = 0; j < datas.t_attachments.length; j++) {
                        var itm = datas.t_attachments[j];
                        attachStr += '&nbsp;&nbsp;&nbsp;<image style="width: 45px;height: 45px;" src="../goods/' + itm.path + '"></image>'
                    }

                    //alert(attachStr);
                    var str1 = '<tr>' +
                        '<td>' + datas.t_eyeglass.name + '</td>' +
                        '<td>' + datas.t_brand.name + '</td>' +
                        '<td>' + datas.t_mask.name + '</td>' +
                        '<td>' + datas.t_eyeglass.refraction + '</td>' +
                        '<td>' + datas.t_eyeglass.price + '</td>' +
                        '<td>' + datas.t_eyeglass.eyeglass + '</td>' +
                        '<td>' + datas.t_efficacy.name + '</td>' +
                        '<td>' + datas.t_style.name + '</td>' +
                        '<td>' + attachStr + '</td>' +
                        '<td>' + '<img src="' + "../goods/" + datas.t_eyeglass.detailphoto + '" style="width: 45px;height: 45px;cursor:pointer;" alt="图片未存在" onclick="javascript:window.open(this.src)" ></td>' +
                        '<td><a class="edit"  id="' + datas.t_eyeglass.eyeglass + '"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" id="' + datas.t_eyeglass.eyeglass + '" ><i class="fa fa-trash"></i>&nbsp;删除</a></td> ' +
                        '</tr>';


                    console.log(str1);
                    $("#tbd").prepend(str1).trigger('footable_redraw');
                    //解除edit 、 delete 原有的点击事件，重新添加
                    setEvents();
                    //修改完后，将添加界面去掉，显示list
                    $("#page_eyeglass_list").css('display', 'block');
                    $("#page_eyeglass_add").css('display', 'none');
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
                    console.log(data)
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
                params.code = id;
                $.ajax({
                    async: false,
                    type: "POST",
                    url: "../eyeglass/delete",//注意路径
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
            params.code = id;
            $.ajax({
                async: false,
                type: "POST",
                url: "../eyeglass/loadById",//注意路径
                data: params,
                dataType: "json",
                success: function (datas) {

                    console.log(JSON.stringify(datas, null, 4));
                    $("#add_name").val(datas.t_eyeglass.name);
                    $("#add_eyeglass").val(datas.t_eyeglass.eyeglass);
                    $("#add_brand").val(datas.t_brand.brand);
                    $("#add_efficacy").val(datas.t_efficacy.efficacy);
                    $("#add_mask").val(datas.t_mask.mask);
                    $("#add_style").val(datas.t_style.style);
                    $("#add_refraction").val(datas.t_eyeglass.refraction);
                    $("#add_price").val(datas.t_eyeglass.price);
                    $("#edit_img").attr('src', '../goods/' + datas.t_eyeglass.detailphoto);
                    //alert(ageStr);

                    $("#atts_list").html('');
                    //将附件里面的图片获取出来
                    var attachStr = '';
                    for (var j = 0; j < datas.t_attachments.length; j++) {
                        var itm = datas.t_attachments[j];
                        var response = itm.attachment;
                        var str = '<div class="col-sm-12" id="' + response + '">' +
                            '<div class="col-sm-8">' +
                            '<input name="fileName" value="' + response + '" readonly style="background-color: white;border: none;outline: none;" class="form-control" type="hidden">' +
                            '<input  value="' + response + '.png" readonly style="background-color: white;border: none;outline: none;" class="form-control" type="text">' +
                            '</div>' +
                            '<div class="col-sm-4" style="padding-top: 3px;" >' +
                            '<a  style="float: right;"    class="atts_deletes" ><i class="fa fa-times-circle"></i></a>' +
                            '</div>' +
                            '</div>';
                        //添加图片
                        $("#atts_list").append(str);
                    }

                    //将删除解除click，重新添加事件
                    $(".atts_deletes").unbind('click');

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
                    });

                    $("#eyeglass_title").text('镜片信息修改');
                    $("#add_eyeglass").attr('readonly', 'readonly');
                    $("#page_eyeglass_list").css('display', 'none');
                    $("#page_eyeglass_add").css('display', 'block');

                }, error: function (err) {
                    console.log(JSON.stringify(err, null, 4))
                }
            });
        });
    }


});
