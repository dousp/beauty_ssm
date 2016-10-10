<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<%@include file="WEB-INF/jsp/common/tag.jsp"%>

<!DOCTYPE html>
<html>
<head>

     <%@include file="WEB-INF/jsp/common/head.jsp"%>

     <title>表格测试</title>

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
                    <table id="teacher_table"
                           data-toggle="table"
                           data-url=""
                           data-method="post"
                           data-query-params="queryParams"
                           data-toolbar="#toolbar"
                           data-pagination="true"
                           data-search="true"
                           data-show-refresh="true"
                           data-show-toggle="true"
                           data-show-columns="true"
                           data-page-size="5">
                        <thead>
                             <tr>
                                 <th data-field="name">用户账号</th>
                                 <th data-field="age">用户年龄</th>
                                 <th data-field="phone">用户电话</th>
                                 <th data-field="name">用户账号</th>
                                 <th data-field="age">用户年龄</th>
                                 <th data-field="phone">用户电话</th>
                                 <th data-field="name">用户账号</th>
                                 <th data-field="age">用户年龄</th>
                                 <th data-field="phone">用户电话</th>

                             </tr>
                        </thead>
                    </table>
                </div>
            </div>

            <div class="col-md-1"></div>
        </div>
    </div>

</body>
</html>
