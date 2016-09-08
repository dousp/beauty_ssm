/**
 * Created by dou on 16/9/3.
 */
// siblings


function Board(obj) {

    this.letters = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","X","Y","Z"];
    this.contentTemp = obj.contentTemp; //提示框的id
    this.tableId = obj.tableId; // 要初始化的tableId
    this.table = $('#'+this.tableId);
    this.colSum = obj.colSum == undefined || obj.colSum == null ? 12 : obj.colSum;
    this.rowSum = obj.rowSum == undefined || obj.rowSum == null ? 8 : obj.rowSum;
    this.tdName = obj.tdName  == undefined || obj.tdName == null ? 'simpleTd' : obj.tdName;    // td元素的名字
    this.caseKey = obj.caseKey  == undefined || obj.caseKey == null ? 'dnacode' : obj.caseKey;  //dnacode
    this.simpleId = obj.simpleId  == undefined || obj.simpleId == null ? 'simpleId' : obj.simpleId; //td上保存样品的id
    this.currnetTd = undefined;  //当前的td
    this.tds = undefined;       //当前板子下所有的td
}

    // Board.prototype.initBoard = function(){
    //     if(!this.tableId == ''){
    //
    //         this.setTableObj(this.tableId);
    //         var table = this.table;
    //
    //         table.append("<tbody></tbody>");
    //         var tr = $('<tr></tr>');
    //         tr.append($('<th></th>'));
    //
    //         for(var i = 0; i<this.colSum; i++){
    //             var seq = i+1 < 10 ? "0"+(i+1) : i+1;
    //             tr.append($("<th>"+seq+"</th>"));
    //         }
    //         table.append(tr);
    //
    //         for(var j = 0; j<this.rowSum; j++){
    //             var newTr = $('<tr></tr>');
    //             newTr.append($("<th>"+this.letters[j]+"</th>"));
    //             for(var i = 0; i<this.colSum; i++){
    //                 var num = i+1<10 ? "0"+(i+1) : i+1;
    //                 newTr.append($("<td id='"+(this.letters[j]+num)+"' name='"+this.tdName+"' dnacode='NONE' simpleId ='NONE' ></td>"));
    //             }
    //             table.append(newTr);
    //         }
    //         this.tds = $('#'+this.tableId +' td');
    //         this.addClickEvent();
    //     }
    // };
    // Board.prototype.setTableObj = function(tableID){
    //     this.table =  $('#'+tableID);
    // };
    // Board.prototype.addClickEvent = function () {
    //
    //     var TimeFn = null;
    //     //var obj = $('#'+this.tableId +' td');
    //     var obj = this.tds;
    //
    //     obj.click(function () {
    //         //单击事件，孔位没有样品则设置起始位置，去除之前标记的起始位置，并记录当前起始位置的td对象
    //         var td = $(this);
    //         clearTimeout(TimeFn);
    //         TimeFn = setTimeout(function(){
    //            this.onHoleClick(td);
    //         },300);
    //     });
    //
    //     obj.dblclick(function () {
    //         //双击事件，若有样品则清除样品，需要与后台交互
    //         var td = $(this);
    //         clearTimeout(TimeFn);
    //         this.onHoleDBClick(td);
    //     });
    //
    //     this.addPopoverEvent(obj);
    // };
    // Board.prototype.addPopoverEvent = function (obj) {
    //     obj.popover({
    //         placement : 'left',
    //         html : true,
    //         trigger:'hover', //触发方式
    //         content : function() {
    //             return $("#"+this.contentTemp).html();
    //         } ,
    //         title : "样品信息：",
    //         container : 'body'
    //
    //     }).on("show.bs.popover", function () {
    //         $(this).data("bs.popover").tip().css("width", "160px");
    //         //$(this).data("bs.popover").tip().css("hight", "300px");
    //         var dnacode = $(this).attr(this.caseKey);
    //         $('#'+this.caseKey).text(dnacode);
    //
    //     });
    // };
    // Board.prototype.onHoleClick = function(td){
    //     //由于td中不存放文本内容，是否有样品，应从额外添加的属性dnacode(主要)和simpleId来判断
    //     var code = td.attr('dnacode');
    //     if(code == 'NONE' || code == undefined){
    //         if(this.currnetTd != '' && this.currnetTd != undefined){
    //             //this.currnetTd.css('background','none');
    //             this.clearOneHole(this.currnetTd);
    //         }
    //         //td.css('background','#5cb85c');
    //         this.setStartHole(td);
    //         this.currnetTd = td;
    //
    //     }else{
    //         BootstrapDialog.show({
    //             title: '提示：',
    //             message: '该孔位已经有样品！'
    //         });
    //     }
    //
    // };
    // Board.prototype.onHoleDBClick = function(td){
    //      var code = td.attr(this.caseKey);
    //      var simpleId = td.attr(this.simpleId);
    //      if(code == 'NONE' || code == undefined){
    //         BootstrapDialog.show({
    //             title: '提示：',
    //             message: '没有样品，无需清孔！'
    //         });
    //     }else{
    //
    //         var obj = new Object();
    //         obj.id = simpleId;
    //         obj.dnacode = code;
    //
    //         $.ajax({
    //             type : 'json',
    //             url : 'action.php',
    //             data: obj,
    //             success: function(data){
    //                 if(data.success){
    //                     this.clearOneHole(td);
    //                     BootstrapDialog.show({
    //                         title: '提示：',
    //                         message: '样品，清孔成功！'
    //                     });
    //                 }else{
    //                     BootstrapDialog.show({
    //                         title: '提示：',
    //                         message: data.message
    //                     });
    //                 }
    //             },
    //             error:function(er){
    //
    //             }
    //         });
    //
    //     }
    // };
    // Board.prototype.clearOneHole = function(td){
    //     td.css('background','none');
    // };
    // Board.prototype.setStartHole = function(td){
    //     td.css('background','#5cb85c');
    // };

/*Board.prototype = {
        initBoard : function(){
        if(!this.tableId == ''){

           //this.setTableObj(this.tableId);
            var table = this.table;

            table.append("<tbody></tbody>");
            var tr = $('<tr></tr>');
            tr.append($('<th></th>'));

            for(var i = 0; i<this.colSum; i++){
                var seq = i+1 < 10 ? "0"+(i+1) : i+1;
                tr.append($("<th>"+seq+"</th>"));
            }
            table.append(tr);

            for(var j = 0; j<this.rowSum; j++){
                var newTr = $('<tr></tr>');
                newTr.append($("<th>"+this.letters[j]+"</th>"));
                for(var i = 0; i<this.colSum; i++){
                    var num = i+1<10 ? "0"+(i+1) : i+1;
                    newTr.append($("<td id='"+(this.letters[j]+num)+"' name='"+this.tdName+"' dnacode='NONE' simpleId ='NONE' ></td>"));
                }
                table.append(newTr);
            }
            this.tds = $('#'+this.tableId +' td');
            this.addClickEvent();
        }
        },
        addClickEvent : function () {

            var TimeFn = null;
            //var obj = $('#'+this.tableId +' td');
            var obj = this.tds;

            obj.click(function () {
                //单击事件，孔位没有样品则设置起始位置，去除之前标记的起始位置，并记录当前起始位置的td对象
                var td = $(this);
                clearTimeout(TimeFn);
                TimeFn = setTimeout(function(){
                   this.onHoleClick(td);
                },300);
            });

            obj.dblclick(function () {
                //双击事件，若有样品则清除样品，需要与后台交互
                var td = $(this);
                clearTimeout(TimeFn);
                this.onHoleDBClick(td);
            });

            this.addPopoverEvent(obj);
        },
        addPopoverEvent : function (obj) {
            obj.popover({
                placement : 'left',
                html : true,
                trigger:'hover', //触发方式
                content : function() {
                    return $("#"+this.contentTemp).html();
                } ,
                title : "样品信息：",
                container : 'body'

            }).on("show.bs.popover", function () {
                $(this).data("bs.popover").tip().css("width", "160px");
                //$(this).data("bs.popover").tip().css("hight", "300px");
                var dnacode = $(this).attr(this.caseKey);
                $('#'+this.caseKey).text(dnacode);

            });
        },
        onHoleClick :   function(td){
            //由于td中不存放文本内容，是否有样品，应从额外添加的属性dnacode(主要)和simpleId来判断
            var code = td.attr('dnacode');
            if(code == 'NONE' || code == undefined){
                if(this.currnetTd != '' && this.currnetTd != undefined){
                    //this.currnetTd.css('background','none');
                    this.clearOneHole(this.currnetTd);
                }
                //td.css('background','#5cb85c');
                this.setStartHole(td);
                this.currnetTd = td;

            }else{
                BootstrapDialog.show({
                    title: '提示：',
                    message: '该孔位已经有样品！'
                });
            }

        },
        onHoleDBClick : function(td){
             var code = td.attr(this.caseKey);
             var simpleId = td.attr(this.simpleId);
             if(code == 'NONE' || code == undefined){
                BootstrapDialog.show({
                    title: '提示：',
                    message: '没有样品，无需清孔！'
                });
            }else{

                var obj = new Object();
                obj.id = simpleId;
                obj.dnacode = code;

                $.ajax({
                    type : 'json',
                    url : 'action.php',
                    data: obj,
                    success: function(data){
                        if(data.success){
                            this.clearOneHole(td);
                            BootstrapDialog.show({
                                title: '提示：',
                                message: '样品，清孔成功！'
                            });
                        }else{
                            BootstrapDialog.show({
                                title: '提示：',
                                message: data.message
                            });
                        }
                    },
                    error:function(er){

                    }
                });

            }
        },
        clearOneHole : function(td){
            td.css('background','none');
        },
        setStartHole : function(td){
            td.css('background','#5cb85c');
        }
    };*/

function initBoard(obj){
    if(!obj.tableId == ''){

       //this.setTableObj(this.tableId);
        var table = obj.table;

        table.append("<tbody></tbody>");
        var tr = $('<tr></tr>');
        tr.append($('<th></th>'));

        for(var i = 0; i<obj.colSum; i++){
            var seq = i+1 < 10 ? "0"+(i+1) : i+1;
            tr.append($("<th>"+seq+"</th>"));
        }
        table.append(tr);

        for(var j = 0; j<obj.rowSum; j++){
            var newTr = $('<tr></tr>');
            newTr.append($("<th>"+obj.letters[j]+"</th>"));
            for(var i = 0; i<obj.colSum; i++){
                var num = i+1<10 ? "0"+(i+1) : i+1;
                newTr.append($("<td id='"+(obj.letters[j]+num)+"' name='"+obj.tdName+"' dnacode='NONE' simpleId ='NONE' ></td>"));
            }
            table.append(newTr);
        }
        obj.tds = $('#'+obj.tableId +' td');
        addClickEvent(obj.tds, obj);
    }
}

function addClickEvent(tds, obj) {

    var TimeFn = null;
    tds.click(function () {
        //单击事件，孔位没有样品则设置起始位置，去除之前标记的起始位置，并记录当前起始位置的td对象
        var td = $(this);
        clearTimeout(TimeFn);
        TimeFn = setTimeout(function(){
           onHoleClick(td,obj);
        },300);
    });

    tds.dblclick(function () {
        //双击事件，若有样品则清除样品，需要与后台交互
        var td = $(this);
        clearTimeout(TimeFn);
        onHoleDBClick(td,obj);
    });

    addPopoverEvent(tds, obj);
}

function addPopoverEvent(tds, obj) {
    tds.popover({
        placement : 'left',
        html : true,
        trigger:'hover', //触发方式
        content : function() {
            return $("#"+obj.contentTemp).html();
        } ,
        title : "样品信息：",
        container : 'body'

    }).on("show.bs.popover", function () {
        $(this).data("bs.popover").tip().css("width", "160px");
        //$(this).data("bs.popover").tip().css("hight", "300px");
        var dnacode = $(this).attr(obj.caseKey);
        $('#'+obj.caseKey).text(dnacode);

    });
}

function onHoleClick(td,obj){
    //由于td中不存放文本内容，是否有样品，应从额外添加的属性dnacode(主要)和simpleId来判断
    var code = td.attr('dnacode');
    if(code == 'NONE' || code == undefined){
        if(obj.currnetTd != '' && obj.currnetTd != undefined){
            //this.currnetTd.css('background','none');
            clearOneHole(obj.currnetTd);
        }
        //td.css('background','#5cb85c');
        setStartHole(td);
        obj.currnetTd = td;

    }else{
        BootstrapDialog.show({
            title: '提示：',
            message: '该孔位已经有样品！'
        });
    }

}

function onHoleDBClick(td,obj){
     var code = td.attr(obj.caseKey);
     var simpleId = td.attr(obj.simpleId);
     if(code == 'NONE' || code == undefined){
        BootstrapDialog.show({
            title: '提示：',
            message: '没有样品，无需清孔！'
        });
    }else{

        var obj = new Object();
        obj.id = simpleId;
        obj.dnacode = code;

        $.ajax({
            type : 'json',
            url : 'action.php',
            data: obj,
            success: function(data){
                if(data.success){
                    clearOneHole(td);
                    BootstrapDialog.show({
                        title: '提示：',
                        message: '样品，清孔成功！'
                    });
                }else{
                    BootstrapDialog.show({
                        title: '提示：',
                        message: data.message
                    });
                }
            },
            error:function(er){

            }
        });

    }
}

function clearOneHole(td){
    td.css('background','none');
}

function setStartHole(td){
    td.css('background','#5cb85c');
}


