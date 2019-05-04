/**
 *
 */
const $table = $('#category_list');

var category_list = {
    dataGrid: function () {
        $table.bootstrapTable({
            url: "/v1/product/category",
            method: "get",
            dataField: "rows",
            cache: false,               //是否使用缓存，默认为true
            striped: true,              //是否显示行间隔色
            pagination: true,           //是否显示分页
            pageSize: 10,               //每页的记录行数
            pageNumber: 1,              //初始化加载第一页，默认第一页
            pageList: [10, 20, 50],     //可供选择的每页的行数
            paginationPreText: '‹',
            paginationNextText: '›',
            search: true,               //是否显示表格搜索
            showRefresh: true,          //是否显示刷新按钮
            clickToSelect: true,        //是否启用点击选中行
            toolbar: "#toolbar",        //工具按钮用哪个容器
            sidePagination: "server",   //分页方式：client客户端分页，server服务端分页
            showPaginationSwitch:true,
            queryParamsType: "",        //查询参数组织方式, 设置为"" 发送pageSize和pageNumber参数
            data_local: "zh-US",
            // showToggle: "true",//是否显示 切换试图（table/card）按钮
            showColumns: true,
            singleSelect: true,
            clickToSelect: true,
            paginationLoop: false,
            clickToSelect: true,
            onCheck: function (row, $element) {
                $("#update_category").removeAttr('disabled');
                $("#remove_category").removeAttr('disabled');
            },
            onUncheck: function (row, $element) {
                $("#update_category").attr('disabled', true);
                $("#remove_category").attr('disabled', true);
            },
            columns: [{
                checkbox: true
            }, {
                field: 'id',
                title: 'ID'
            }, {
                field: 'name',
                title: '类别名称'
            }, {
                field: 'description',
                title: '描述'
            }, {
                field: 'state',
                title: '状态'
            }]
        });
    }
};

$(function () {
    $("#add_category").ajaxForm(function(data){
        if(data.code==200) {
            $('#add_category_modal').modal('hide');
            $("#category_list").bootstrapTable('refresh');
        }
    });
    category_list.dataGrid();

})