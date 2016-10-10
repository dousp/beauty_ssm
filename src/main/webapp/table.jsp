<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<%@include file="WEB-INF/jsp/common/tag.jsp"%>

<!DOCTYPE html>
<html>
<head>

     <%@include file="WEB-INF/jsp/common/head.jsp"%>

     <title>表格测试</title>
	 <script type="application/javascript">

	     $(function() {
             $('#table').bootstrapTable({

                striped:true,
                sortOrder:'desc',
                method:'post',
                pagination:true,
                search:true,
                showHeader:true,
                showFooter:true,
                showColumns:true, //toolbar上是否显示 内容列下拉框（显示有那些列）
                showRefresh:true,
                toolbar:'#toolbar',
                idField:'id', //主键列
                clickToSelect:true, // 	设置true 将在点击行时，自动选择rediobox 和 checkbox

                columns: [
                    {
                        checkbox:true
                    },
                    {
                        field: 'id',
                        sortable:true,
                        title: 'ID'
                    }, {
                        field: 'name',
                        sortable:true,
                        title: '用户姓名'
                    }, {
                        field: 'age',
                        sortable:true,
                        title: '用户年龄'
                    }, {
                        field: 'phone',
                        sortable:true,
                        title: '用户电话'
                    }
                ],
                data: [
                     {
                         id: 2,
                         name: 'dd',
                         age: 11,
                         phone: '123456'
                     }, {
                         id: 1,
                         name: 'tt',
                         age: 12,
                         phone: '123457'
                     }
                 ]
             });

         });

     </script>


</head>
<body>

    <div class="container-fluid">
        <div class="row">
            <div class="col-md-1"></div>

            <div class="col-md-10">
                <div class="panel panel-default">
                    <div id="toolbar" class="btn-group">
                        <button id="btn_edit" type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                        </button>
                        <button id="btn_delete" type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                        </button>
                    </div>

                    <table id="table"></table>

                </div>
            </div>

            <div class="col-md-1"></div>
        </div>
    </div>

</body>
</html>
