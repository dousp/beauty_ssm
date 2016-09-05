<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE >
<html>

    <head>
        <%
			String context = request.getContextPath();
			String title = "欢迎";
		%>
		<title><%=title%></title>

		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="shortcut icon" href="<%=context%>/resource/images/favicon.ico" type="image/x-icon" />

		<link rel="stylesheet" type="text/css" href="<%=context%>/resource/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=context%>/resource/css/bootstrap-dialog.min.css" />


        <script type="text/javascript" src="<%=context%>/resource/js/jquery-1.11.1.min.js"></script>
        <%--<script type="text/javascript" src="<%=context%>/resource/js/jquery-3.1.0.min.js"></script>--%>

        <script type="text/javascript" src="<%=context%>/resource/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="<%=context%>/resource/js/bootstrap-dialog.min.js"></script>

        <script type="text/javascript" src="<%=context%>/resource/script/DrawBoard.js"></script>

        <%--<link rel="stylesheet" type="text/css" href="<%=context%>/resource/css/bootstrap-table.min.css" />--%>
        <%--<script type="text/javascript" src="<%=context%>/resource/js/bootstrap-table.min.js"></script>--%>
		<%--<script type="text/javascript" src="<%=context%>/resource/js/bootstrap-table-locale-all.min.js"></script>--%>

        <%--
        <!-- 新 Bootstrap 核心 CSS 文件 -->
        <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
        <!-- 可选的Bootstrap主题文件（一般不用引入） -->
        <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">
        <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
        <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
        <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
        <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.css">
        <!-- Latest compiled and minified JavaScript -->
        <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.js"></script>
        <!-- Latest compiled and minified Locales -->
        <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/locale/bootstrap-table-zh-CN.min.js"></script>
        --%>

        <style>

            .table-panel{
                width: 75%;
            }
            table {

            }
            table th {
                text-align: center;
            }
            table td{

            }


        </style>

		<script type="application/javascript">

		    $(function() {

                /*$('#table').bootstrapTable({
                    //url: 'data1.json',
                    columns: [
                        {
                            field: 'id',
                            title: 'Item ID'
                        }, {
                            field: 'name',
                            title: 'Item Name'
                        }, {
                            field: 'price',
                            title: 'Item Price'
                        }
                    ],
                    data: [
                        {
                            id: 1,
                            name: 'Item 1',
                            price: '$1'
                        }, {
                            id: 2,
                            name: 'Item 2',
                            price: '$2'
                        }
                    ]
                });*/

                //$("table").addClass("table table-bordered table-condensed text-center");
                //initTable();

                DrawBoard.config({
                    rowSum : 12,
                    colSum : 24,
                    tableId : 'table',
                    contentTemp : 'popoverContent',

                });
                DrawBoard.init();


            });

        </script>



    </head>

    <body>

        <h1 id="view" align="center">Welcome to 拼版!</h1>

        <div align="center">
            <div class="panel panel-primary table-panel"  >
                <div class="panel-heading">
                    <h3 class="panel-title">384拼版</h3>
                </div>

                <div class="panel-body">
                    <div class="table-responsive" id="tableDiv">

                        <table id="table" class="table table-bordered table-condensed text-center">

                        </table>

                        <div id="popoverContent" class="hidden">
                            <label>dnacode:</label>
                            <div id="dnacode"></div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

    </body>
</html>