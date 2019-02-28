/**
 * 初始化标题详情对话框
 */
var TitleInfoDlg = {
    titleInfoData : {}
};

/**
 * 清除数据
 */
TitleInfoDlg.clearData = function() {
    this.titleInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TitleInfoDlg.set = function(key, val) {
    this.titleInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TitleInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TitleInfoDlg.close = function() {
    parent.layer.close(window.parent.Title.layerIndex);
}

/**
 * 收集数据
 */
TitleInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('pid')
    .set('filePath')
    .set('sortOrder')
    .set('createTime')
    .set('createUser')
    .set('file')
    .set('updateTime')
    .set('updateUser');
}

/**
 * 提交添加
 */
TitleInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    var form = new FormData(document.getElementById("forms"));
    $.ajax({
        url:"/title/add",
        type:"post",
        data:form,
        processData:false,
        contentType:false,
        success:function(data){
            if(data.code==200){
                Feng.success("添加成功!");
                window.parent.Title.table.refresh();
                TitleInfoDlg.close();
            }else {
                Feng.error("添加失败!" + data.responseJSON.message + "!");
            }
            window.clearInterval(timer);
            console.log("over..");
        },
        error:function(e){
            alert("错误！！");
            window.clearInterval(timer);
        }
    });
    ajax.set(this.titleInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TitleInfoDlg.editSubmit = function() {
    this.clearData();
    this.collectData();

    var form = new FormData(document.getElementById("forms"));
    $.ajax({
        url:"/title/update",
        type:"post",
        data:form,
        processData:false,
        contentType:false,
        success:function(data){
            if(data.code==200){
                Feng.success("修改成功!");
                window.parent.Title.table.refresh();
                TitleInfoDlg.close();
            }else {
                Feng.error("修改失败!" + data.responseJSON.message + "!");
            }
            window.clearInterval(timer);
            console.log("over..");
        },
        error:function(e){
            alert("错误！！");
            window.clearInterval(timer);
        }
    });
    ajax.set(this.titleInfoData);
    ajax.start();
}

$(function() {

});
