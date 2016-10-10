<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<%@include file="WEB-INF/jsp/common/tag.jsp"%>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/jsp/common/head.jsp"%>

        <title><%=title%></title>

		<script type="application/javascript">

		    $(function() {


            });

        </script>

    </head>
<body>

    <div class="col-md-offset-3 col-md-6">
        <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title text-center">列表</h3>
                </div>
                <div class="panel-body">
                    <div id="toolbar" class="btn-group">
                        <button id="btn_edit" type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                        </button>
                        <button id="btn_delete" type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                        </button>
                    </div>
                    <table id="teacher_table" data-toggle="table" data-url="" data-method="post"
                           data-query-params="queryParams"
                           data-toolbar="#toolbar"
                           data-search="true"
                           data-show-refresh="true"
                           data-show-toggle="true"
                           data-show-columns="true"
                           data-pagination="true"
                           data-page-size="5">
                        <thead>
                        <tr>
                            <th data-field="name">用户账号</th>
                            <th data-field="age">用户年龄</th>
                            <th data-field="phone">用户电话</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
    </div>

</body>
</html>