/**
 * Created by dou on 16/9/3.
 */
// siblings
var DrawBoard = {

    tableId:'',          // table的id属性值
    table:'',
    colSum:12,
    rowSum:8,
    contentTemp:'',      // 提示框的id
    caseKey:'dnacode',   // td上存放dna编码的属性，提示框有应该有一个id为这个值的div
    tdName:'td_simple',  // 每个td的name
    currnetTd:'',        // 当前'start'位置
    tds: undefined,      // 所有的td的对象数组
    letters : ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","X","Y","Z"],
    URL : {
        clearOneHole : function(dnacode,board){

        },
        clearAllHole : function(board){

        },
        addHole : function(dnacode,hole,board){

        }
    },
    config:function(object){
        DrawBoard.tableId = object.tableId;
        DrawBoard.colSum = object.colSum;
        DrawBoard.rowSum = object.rowSum;
        DrawBoard.contentTemp = object.contentTemp;
        if(object.tdName != undefined && object.tdName != ''){
            DrawBoard.tdName = object.tdName;
        }
        if(object.caseKey != undefined && object.caseKey != ''){
            DrawBoard.caseKey = object.caseKey;
        }
    },
    setTableObj : function(tableID){
        this.table =  $('#'+tableID);
    },
    init : function(){
        if(!this.tableId == ''){

            this.setTableObj(DrawBoard.tableId);
            var table = DrawBoard.table;

            table.append("<tbody></tbody>");
            var tr = $('<tr></tr>');
            tr.append($('<th></th>'));

            for(var i = 0; i<DrawBoard.colSum; i++){
                var seq = i+1 < 10 ? "0"+(i+1) : i+1;
                tr.append($("<th>"+seq+"</th>"));
            }
            table.append(tr);

            for(var j = 0; j<DrawBoard.rowSum; j++){
                var newTr = $('<tr></tr>');
                newTr.append($("<th>"+DrawBoard.letters[j]+"</th>"));
                for(var i = 0; i<DrawBoard.colSum; i++){
                    var num = i+1<10 ? "0"+(i+1) : i+1;
                    newTr.append($("<td id='"+(DrawBoard.letters[j]+num)+"' name='"+DrawBoard.tdName+"' dnacode='NONE'>"+i+"</td>"));
                }
                table.append(newTr);
            }
            DrawBoard.tds = $('#'+DrawBoard.tableId +' td');
            DrawBoard.addClickEvent();
        }
    },
    addClickEvent : function () {

        var TimeFn = null;
        //var obj = $('#'+DrawBoard.tableId +' td');
        var obj = DrawBoard.tds;

        obj.click(function () {
            //单击事件，孔位没有样品则设置起始位置，去除之前标记的起始位置，并记录当前起始位置的td对象
            var td = $(this);
            clearTimeout(TimeFn);
            TimeFn = setTimeout(function(){
               DrawBoard.onHoleClick(td);
            },300);
        });

        obj.dblclick(function () {
            //双击事件，若有样品则清除样品，需要与后台交互
            var td = $(this);
            clearTimeout(TimeFn);
            DrawBoard.onHoleDBClick(td);
        });

        DrawBoard.addPopoverEvent(obj);
    },
    addPopoverEvent : function (obj) {
        obj.popover({
            placement : 'left',
            html : true,
            trigger:'hover', //触发方式
            content : function() {
                return $("#"+DrawBoard.contentTemp).html();
            } ,
            title : "样品信息：",
            container : 'body'

        }).on("show.bs.popover", function () {
            //$(this).data("bs.popover").tip().css("width", "200px");
            //$(this).data("bs.popover").tip().css("hight", "300px");
            var dnacode = $(this).attr(DrawBoard.caseKey);
            $('#'+DrawBoard.caseKey).text(dnacode);

        });
    },
    onHoleClick : function(td){
        var code = td.attr('dnacode');
        if(code == 'NONE'){
            if(DrawBoard.currnetTd != ''){
                DrawBoard.currnetTd.text('');
            }
            td.text('start');
            DrawBoard.currnetTd = td;
        }else{
            BootstrapDialog.show({
                title: '提示：',
                message: '该孔位已经有样品！'
            });
        }

    },
    onHoleDBClick : function(td){
         var code = td.attr(DrawBoard.caseKey);
         if(code == 'NONE'){
            BootstrapDialog.show({
                title: '提示：',
                message: '没有样品，无需清孔！'
            });
        }else{

        }
    }

};