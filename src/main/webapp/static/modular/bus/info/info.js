/**
 * 管理初始化
 */
var Info = {
    id: "InfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Info.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            //{title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '所属类型', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
            {title: '内容名字', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '图片', field: 'iconPath', visible: true, align: 'center', valign: 'middle'},
            {title: '文本', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: '简介', field: 'desc', visible: true, align: 'center', valign: 'middle'},
            {title: '浏览量', field: 'hot', visible: true, align: 'center', valign: 'middle'},
/*            {title: '创建人', field: 'createUser', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人', field: 'modifyUser', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'modifyTime', visible: true, align: 'center', valign: 'middle'},*/
            {title: '0不在底部显示 1在底部显示', field: 'isMenu', visible: true, align: 'center', valign: 'middle'},
            {title: '顺序', field: 'sortOrder', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Info.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Info.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
Info.openAddInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/info/info_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
Info.openInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/info/info_update/' + Info.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Info.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/info/delete", function (data) {
            Feng.success("删除成功!");
            Info.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("infoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
Info.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Info.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Info.initColumn();
    var table = new BSTable(Info.id, "/info/list", defaultColunms);
    table.setPaginationType("client");
    Info.table = table.init();
});
