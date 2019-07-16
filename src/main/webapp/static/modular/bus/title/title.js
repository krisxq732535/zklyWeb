/**
 * 标题管理初始化
 */
var Title = {
    id: "TitleTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Title.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            //{title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '标题名字', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '父标题', field: 'pidName', visible: true, align: 'center', valign: 'middle'},
       /*     {title: '图片路径', field: 'filePath', visible: true, align: 'center', valign: 'middle'},*/
            {title: '排序', field: 'sortOrder', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'desc', visible: true, align: 'center', valign: 'middle'},
            //{title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            //{title: '创建人', field: 'createUser', visible: true, align: 'center', valign: 'middle'},
            //{title: '修改时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
            //{title: '修改人', field: 'updateUser', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Title.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Title.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加标题
 */
Title.openAddTitle = function () {
    var index = layer.open({
        type: 2,
        title: '添加标题',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/title/title_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看标题详情
 */
Title.openTitleDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '标题详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/title/title_update/' + Title.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除标题
 */
Title.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/title/delete", function (data) {
            Feng.success("删除成功!");
            Title.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("titleId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询标题列表
 */
Title.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Title.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Title.initColumn();
    var table = new BSTable(Title.id, "/title/list", defaultColunms);
    table.setPaginationType("client");
    Title.table = table.init();
});
