


var markupStr=$("#content").html(); $('#summernote').summernote( {
    height: 300,
});
/**
 * 初始化详情对话框
 */
var InfoInfoDlg = {
    infoInfoData : {}
};

/**
 * 清除数据
 */
InfoInfoDlg.clearData = function() {
    this.infoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
InfoInfoDlg.set = function(key, val) {
    this.infoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
InfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
InfoInfoDlg.close = function() {
    parent.layer.close(window.parent.Info.layerIndex);
}

/**
 * 收集数据
 */
InfoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('type')
    .set('title')
    .set('iconPath')
    .set('content')
    .set('desc')
    .set('hot')
     .set('file')
    .set('createUser')
    .set('createTime')
    .set('modifyUser')
    .set('modifyTime')
    .set('isMenu')
    .set('sortOrder');
}

/**
 * 提交添加
 */
InfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    // var form = new FormData(document.getElementById("forms"));
    // $.ajax({
    //     url:"/info/add",
    //     type:"post",
    //     data:form,
    //     processData:false,
    //     contentType:false,
    //     success:function(data){
    //         if(data.code==200){
    //             Feng.success("添加成功!");
    //             window.parent.Info.table.refresh();
    //             InfoInfoDlg.close();
    //         }else {
    //             Feng.error("添加失败!" + data.responseJSON.message + "!");
    //         }
    //         window.clearInterval(timer);
    //         console.log("over..");
    //     },
    //     error:function(e){
    //         alert("错误！！");
    //         window.clearInterval(timer);
    //     }
    // });
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/info/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Info.table.refresh();
        InfoInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.infoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
InfoInfoDlg.editSubmit = function() {
    this.clearData();
    this.collectData();
    var ajax = new $ax(Feng.ctxPath + "/info/update", function (data) {
        Feng.success("修改成功!");
        window.parent.Info.table.refresh();
        InfoInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.infoInfoData);
    ajax.start();
}

$(function() {
    var cover = new $WebUpload("iconPath");
    cover.init();
});
