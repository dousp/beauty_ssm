<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<%@include file="WEB-INF/jsp/common/tag.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="WEB-INF/jsp/common/head.jsp"%>
    <title>表格测试</title>

    <script type="application/javascript">

        $(function () {

            //1.初始化Table
            var oTable = new TableInit();
            oTable.Init();

            //2.初始化Button的点击事件
            var oButtonInit = new ButtonInit();
            oButtonInit.Init();

        });

        var TableInit = function () {
            var oTableInit = new Object();
            //初始化Table
            oTableInit.Init = function () {
                $('#tb_departments').bootstrapTable({
                    url: '',   //请求后台的URL（*）
                    method: 'post',        //请求方式（*）
                    toolbar: '#toolbar',   //工具按钮用哪个容器
                    striped: true,         //是否显示行间隔色
                    cache: false,          //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                    queryParams: oTableInit.queryParams,//传递参数（*）

                    sortName: 'name',      //定义排序列,通过url方式获取数据填写字段名，否则填写下标
                    sortOrder: "desc",     //排序方式
                    sortable: true,

                    pagination: true,     //是否显示分页（*）
                    sidePagination: "server",   //分页方式：client客户端分页，server服务端分页（*）
                    pageNumber: 1,      //初始化加载第一页，默认第一页
                    pageSize: 25,      //每页的记录行数（*）
                    pageList: [10, 25, 50, 100],  //可供选择的每页的行数（*）

                    search: true,      //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
                    strictSearch: true,
                    showColumns: true,     //是否显示所有的列
                    showRefresh: true,     //是否显示刷新按钮
                    minimumCountColumns: 2,    //最少允许的列数
                    clickToSelect: true,    //是否启用点击选中行
                    height: getHeight(),      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                    uniqueId: "ID",      //每一行的唯一标识，一般为主键列
                    showToggle: true,     //是否显示详细视图和列表视图的切换按钮
                    cardView: false,     //是否显示详细视图
                    detailView: false,     //是否显示父子表
                    columns: [
                        { checkbox: true },
                        { field: 'Name',       title: '名称', sortable: true },
                        { field: 'dataType',   title: '物理类型', sortable: true },
                        { field: 'javaName',   title: 'Java名称', sortable: true },
                        { field: 'javaType',   title: 'Java类型', sortable: true },
                        { field: 'formType',   title: '表单类型', sortable: true },
                        { field: 'parentCode', title: '字典编码', sortable: true },
                        { field: 'formart',    title: '格式化', sortable: true },
                        { field: 'null',       title: '可空', sortable: true },
                        { field: 'edit',       title: '编辑', sortable: true },
                        { field: 'list',       title: '列表', sortable: true },
                        { field: 'search',     title: '筛选', sortable: true },
                        { field: 'miniSearch', title: 'Mini查询', sortable: true },
                        { field: 'formula',    title: '查询公式', sortable: true },
                        { field: 'sort',       title: '排序', sortable: true }
                    ]
                });

                //var options = $('#tb_departments').bootstrapTable('getOptions');
                //console.info(options);
            };

            //得到查询的参数
            oTableInit.queryParams = function (params) {
                var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                    limit: params.limit, //页面大小
                    offset: params.offset, //页码
                    departmentname: $("#txt_search_departmentname").val(),
                    statu: $("#txt_search_statu").val()
                };
                return temp;
            };
            return oTableInit;
        };

        var ButtonInit = function () {
            var oInit = new Object();
            var postdata = {};

            oInit.Init = function () {
                //初始化页面上面的按钮事件
            };
            return oInit;
        };

        function getHeight() {
          return $(window).height() * 0.75; //$('col-md-10').outerHeight(true) - 70;
        }

    </script>


</head>
<body>
    <div class="panel-body" style="padding-bottom:0px;">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <div class="panel panel-default">
                        <div class="panel-heading">Code Gen</div>
                        <div class="panel-body">
                            <form id="formSearch" class="form-horizontal">
                                <div class="form-group" style="margin-top:0px;margin-bottom: 0px">

                                    <label class="control-label col-sm-1" for="txt_search_departmentname">Table名称</label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" id="txt_search_departmentname">
                                    </div>

                                    <label class="control-label col-sm-1" for="txt_search_statu">状态</label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" id="txt_search_statu">
                                    </div>

                                    <div class="col-sm-4" style="text-align:left;">
                                        <button type="button" style="margin-left:50px" id="btn_query"
                                                class="btn btn-primary">查询
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div id="toolbar" class="btn-group" >
                        <button id="btn_add" type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                        </button>
                        <button id="btn_edit" type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                        </button>
                        <button id="btn_delete" type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                        </button>
                    </div>
                    <table id="tb_departments"></table>
                </div>
                <div class="col-md-1"></div>
            </div>
        </div>
    </div>
</body>
</html>
