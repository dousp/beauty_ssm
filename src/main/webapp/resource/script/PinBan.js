/**
 * Created by dou on 16/9/13.
 */

(function (root, factory) {

    "use strict";

    // CommonJS module is defined
    if (typeof module !== 'undefined' && module.exports) {
        module.exports = factory(require('jquery'), require('bootstrap'),require('bootstrap-dialog'));
    }
    // AMD module is defined
    else if (typeof define === "function" && define.amd) {
        define("PinBan", ["jquery", "bootstrap","bootstrap-dialog"], function ($) {
            return factory($);
        });
    } else {
        // planted over the root!
        root.AssembleBoard = factory(root.jQuery);
    }

}(this, function ($) {

    "use strict";

    var AssembleBoard = function (options) {
        this.defaultOptions = $.extend(true, {
                id: AssembleBoard.newGuid(),
                contentTemp : null,
                tableId : null,
                table : null,
                currnetTd : null,
                tds : null

            }, AssembleBoard.defaultOptions);
        this.initOptions(options);
    };

    AssembleBoard.defaultOptions = {
        letters : ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","X","Y","Z"],
        colSum : 12,
        rowSum : 8,
        tdName : 'simpleTd',
        caseKey : 'dnacode',
        simpleId : 'simpleId',
        placement : 'right',
        // contentTemp : '',
        // tableId : '',
        // table : '',
        // currnetTd : '',
        // tds : ''
    };

    AssembleBoard.configDefaultOptions = function (options) {
        AssembleBoard.defaultOptions = $.extend(true, AssembleBoard.defaultOptions, options);
    };

    AssembleBoard.prototype = {
        constructor: AssembleBoard,
        initOptions: function (options) {
            console.info('initOptions');
            this.options = $.extend(true, this.defaultOptions, options);
            return this;
        },
        initBoard : function(){
            console.info('initBoard begin');
            this.options.table = $('#'+this.options.tableId);
            this.initTable();
            this.options.tds = $('#'+this.options.tableId +' td');
            this.addClickEvent();
            this.addPopoverEvent();
            console.info('initBoard end');
        },
        initTable : function(){
            this.options.table.append("<tbody></tbody>");
            var tr = $('<tr></tr>');
            tr.append($('<th></th>'));
            for(var i = 0; i<this.options.colSum; i++){
                var seq = i+1 < 10 ? "0"+(i+1) : i+1;
                tr.append($("<th>"+seq+"</th>"));
            }
            this.options.table.append(tr);
            for(var j = 0; j<this.options.rowSum; j++){
                var newTr = $('<tr></tr>');
                newTr.append($("<th>"+this.options.letters[j]+"</th>"));
                for(var i = 0; i<this.options.colSum; i++){
                    var num = i+1<10 ? "0"+(i+1) : i+1;
                    newTr.append($("<td id='"+(this.options.letters[j]+num)+"' name='"+this.options.tdName+"' dnacode='NONE' simpleId ='NONE' ></td>"));
                }
                this.options.table.append(newTr);
            }
        },
        addClickEvent : function() {
            var TimeFn = null;

            this.options.tds.on('click', {board: this}, function (event) {
                var td = $(this);
                event.data.board.onHoleClick(td);
            });

            this.options.tds.on('dblclick', {board: this}, function (event) {
                var td = $(this);
                event.data.board.onHoleDBClick(td);
            });

            //addPopoverEvent(tds);
        },
        addPopoverEvent : function() {

            var contentTemp = this.options.contentTemp;
            //var caseKey = this.options.caseKey;
            this.options.tds.popover({
                placement : this.options.placement,
                html : true,
                trigger:'hover', //触发方式
                content : function() {
                    return $("#"+contentTemp).html();
                } ,
                title : "样品信息：",
                container : 'body'

            }).on("show.bs.popover",{bdOptions: this.options},function (event) {

                $(this).data("bs.popover").tip().css("width", "160px");
                //$(this).data("bs.popover").tip().css("hight", "300px");

                var ss = event.data.bdOptions;
                var dnacode = $(this).attr(ss.caseKey);
                $('#'+ss.caseKey).text(dnacode);
            });
        },
        onHoleClick : function(td){
            //由于td中不存放文本内容，是否有样品，应从额外添加的属性dnacode(主要)和simpleId来判断
            var code = td.attr('dnacode');
            if(code == 'NONE' || code == undefined){
                if(this.options.currnetTd != '' && this.options.currnetTd != undefined){
                    //this.currnetTd.css('background','none');
                    this.clearOneHole(this.options.currnetTd);
                }
                //td.css('background','#5cb85c');
                this.setStartHole(td);
                this.options.currnetTd = td;

            }else{
                BootstrapDialog.show({
                    title: '提示：',
                    message: '该孔位已经有样品！'
                });
            }

        },
        onHoleDBClick : function(td){
            var code = td.attr(this.options.caseKey);
            var simpleId = td.attr(this.options.simpleId);
            if(code == 'NONE' || code == undefined){
               BootstrapDialog.show({
                   title: '提示：',
                   message: '没有样品，无需清孔！'
               });
            }
            else{

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
        },
        clearOneHole : function(td){
            td.css('background','none');
        },
        setStartHole : function(td){
            td.css('background','#5cb85c');
        }
    };

    AssembleBoard.newGuid = function () {
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
            var r = Math.random() * 16 | 0, v = c === 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(16);
        });
    };

    return AssembleBoard;

}));

/*;(function($, window, document,undefined) {
    //定义Beautifier的构造函数
    var Beautifier = function(ele, opt) {
        this.$element = ele,
        this.defaults = {
            'color': 'red',
            'fontSize': '12px',
            'textDecoration': 'none'
        },
        this.options = $.extend({}, this.defaults, opt)
    }
    //定义Beautifier的方法
    Beautifier.prototype = {
        beautify: function() {
            return this.$element.css({
                'color': this.options.color,
                'fontSize': this.options.fontSize,
                'textDecoration': this.options.textDecoration
            });
        }
    }
    //在插件中使用Beautifier对象
    $.fn.myPlugin = function(options) {
        //创建Beautifier的实体
        var beautifier = new Beautifier(this, options);
        //调用其方法
        return beautifier.beautify();
    }
})(jQuery, window, document);*/

