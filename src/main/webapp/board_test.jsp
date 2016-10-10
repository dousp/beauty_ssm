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

		<link rel="stylesheet" type="text/css" href="<%=context%>/resource/bootstrap/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=context%>/resource/bootstrao-dialog/bootstrap-dialog.min.css" />
        <link rel="stylesheet" type="text/css" href="<%=context%>/resource/css/PinBan.css" />

        <script type="text/javascript" src="<%=context%>/resource/js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="<%=context%>/resource/bootstrap/bootstrap.min.js"></script>
        <script type="text/javascript" src="<%=context%>/resource/bootstrao-dialog/bootstrap-dialog.js"></script>
        <script type="text/javascript" src="<%=context%>/resource/script/PinBan.js"></script>


		<script type="application/javascript">

		    $(function() {

                var table = new AssembleBoard({
                    rowSum : 12,
                    colSum : 24,
                    tableId : 'table',
                    contentTemp : 'popoverContent'

                });
                table.initBoard();

                var table2 = new AssembleBoard({
                    rowSum : 4,
                    colSum : 4,
                    tableId : 'table2',
                    contentTemp : 'popoverContent2'

                });
                table2.initBoard();

            });

        </script>



    </head>

    <body>
        <!-- 文库孔板 -->
        <div align="center" style="float:left;width: 25%;">
            <div class="panel panel-primary table-panel"  >
                <div class="panel-heading">
                    <h3 class="panel-title">文库孔板</h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive" id="tableDiv2">
                        <table id="table2" class="table table-bordered table-condensed text-center">
                            <%--<tr>
                                <td>1</td>  <td>5</td>  <td>9</td>   <td>13</td>
                            </tr>
                            <tr>
                                <td>2</td>  <td>6</td>  <td>10</td>   <td>14</td>
                            </tr>
                            <tr>
                                <td>3</td>  <td>7</td>  <td>11</td>   <td>15</td>
                            </tr>
                            <tr>
                                <td>4</td>  <td>8</td>  <td>12</td>  <td>16</td>
                            </tr>--%>
                        </table>
                    </div>
                </div>
            </div>
            <div id="popoverContent2" class="hidden">
                <label>dnacode:</label>
                <div id="dnacode2"></div>
            </div>
        </div>
        <!-- 芯片 -->
        <div align="center" style="float:left;width: 75%;">
            <div class="panel panel-primary table-panel"  >
                <div class="panel-heading">
                    <h3 class="panel-title">芯片：<span id="boardno">xxxx</span></h3>
                </div>

                <div class="panel-body" >
                    <div class="table-responsive" id="tableDiv">

                        <table id="table" class="table table-bordered table-condensed text-center">

                        </table>

                    </div>
                </div>
            </div>
            <div id="popoverContent" class="hidden">
                 <label>dnacode:</label>
                 <div id="dnacode"></div>
             </div>
        </div>

    </body>
</html>