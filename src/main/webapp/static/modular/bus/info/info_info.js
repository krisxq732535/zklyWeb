


var markupStr=$("#content").html();
$('#summernote').summernote( { lang:'zh-CN', placeholder: '请输入内容', height: 300 ,
    callbacks: {
        onImageUpload: function (files) {
            debugger;
            sendFile(files[0]);
        }
    }
});
//ajax上传图片
function sendFile( file) {
    debugger
    var formData = new FormData();
    formData.append("file", file);
    $.ajax({
        url: "/mgr/upload",//路径是你控制器中上传图片的方法，下面controller里面我会写到
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        type: 'POST',
        success: function (data) {
            if (data.code ==null){
                $('#summernote').summernote('insertImage', '/kaptcha/'+data, data.split('.')[0]);
            }else {
                alert("上传本地图片失败")
            }
        }
    });
}
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
    var code1= $('#summernote').summernote('code');
    $('#content').val(code1)
    this.collectData();

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
    debugger
    var  code1=$('#summernote').summernote('code')
    $('#content').val($('#summernote').summernote('code'))
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
