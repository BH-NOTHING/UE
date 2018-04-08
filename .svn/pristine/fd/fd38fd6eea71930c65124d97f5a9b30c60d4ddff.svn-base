/**
 * king 20180403
 * 1）PluginTree控件树方法。使用方法如下：
 * PluginTree.showSDETree("sdeTree");
 *
 *
 */
var PluginTree = {
    sdeTreeId: "sdeTree",   //树的id
    sdeOpenStatus: false,   //是否展开
    childrenData: "",        //树的内容数据
    initTree: function (sdeTreeId) {
        this.sdeTreeId = sdeTreeId || "sdeTree";
        var _this = this;
        window.pluginTree = $('#' + sdeTreeId).widgets({
            xtype: 'tree',
            //cascadeCheck: false,
            /*isFilterFlag: true,
             searchTip:"",
             searchType:"",
             collapsible: false,
             width: '195',
             onlyLeafCheck: false,
             noheader: true,
             fit: true,
             _css: {},
             tools: [],
             height: '400',
             closable: false,
             autoload: true,
             minimizable: false,
             checkbox: false,
             border: false,
             maximizable: false,*/
            loader: function () {//返回false 将不加载远程数据
                return false;
            },
            onLoadSuccess: function (node, row) {
                node && console.log("tree onLoadSuccess:" + node);
            },
            onClick: function (node) {
                console.log("tree onClick:" + node.id);
                _this.removeSearchTig();
            },
            onContextMenu: function (e, node) {//右键
                e.preventDefault();
                $('#' + _this.sdeTreeId).getWidget().select(node.target);//将当前节点选中
                $('#sdeTreeContextMenu').getWidget().show({// 显示快捷菜单
                    left: e.pageX,
                    top: e.pageY
                });
            },
            onExpand: function (node) {
                console.log("tree onExpand:" + node.id);
                var arr = $('#' + _this.sdeTreeId).children();
                var totalH = 0;
                for (var i = 0, len = arr.length; i < len; i++) {
                    totalH += arr[i].offsetHeight;
                }
                if (totalH >= 486) {
                    $('#' + _this.sdeTreeId).css({height: "486px", "overflow-y": "scroll"});
                }
            },
            onCollapse: function (node) {
                var arr = $('#' + _this.sdeTreeId).children();
                var totalH = 0;
                for (var i = 0, len = arr.length; i < len; i++) {
                    totalH += arr[i].offsetHeight;
                }
                if (totalH < 486) {
                    $('#' + _this.sdeTreeId).css({height: "auto", overflow: "initial"});
                }
            },
            onDblClick: function (node) {
                console.log("tree onDblClick:" + node.id + ",state:" + node.state);
                if (node.id == "root") {
                    _this.openSDETree();
                    return;
                }
                if (node.state) {
                    return;
                }
                /*向编辑器中插入选中项*/
                _this.insertPluginHtml(node);

            }
        });
    },

    /**向编辑器中插入选中项*/
    insertPluginHtml: function (node) {
        var _sdefun = new sdeFun();
        var plugin = _sdefun.pluginFindByVId({
            ver_id: node.id
        });
        if (plugin && plugin.length !== 0) {
            var json = _sdefun.pluginToJson(plugin[0]);
            var html = _sdefun.makeHtmlByPluginJson(json);
            var oNodeHtml = _sdefun.getONodeHtml(json, html);
            sde.execCommand('insertHtml', oNodeHtml);
            return true;
        } else {
            $.showTip("插入控件异常!");
        }
    },

    /**打开编辑器的搜索树*/
    openSDETree: function () {
        if (!this.sdeOpenStatus) {
            this.initTree(this.sdeTreeId);
            this['loadData']();
            this.sdeOpenStatus = true;
        }
        $("#" + this.sdeTreeId).css("display", "block").toggleClass("tree-open", "tree-close");
        $("#sde-showtree").toggleClass("sde-showtree-open", "sde-showtree-close");
        $("#sde-arrowNav").toggleClass("sde-arrowNav-open", "sde-arrowNav-close");
    },

    /**显示搜索树*/
    showSDETree: function () {
        $("#myEditor").append('' +
            '<div class="treeWrapper">' +
            '   <div id="' + this.sdeTreeId + '" class="sde-tree"></div>' +
            '   <div id="sde-showtree">' +
            '       <div id="sde-arrowNav" class="sde-arrowNav-close" onclick="PluginTree.openSDETree()">' +
            '       </div>' +
            '   </div>' +
            '</div>');
    },

    /**控件树load数据 方法1*/
    treeDatabind: function () {
        this.sdeTreeId = this.sdeTreeId;
        $W.databind.treeDatabind({
            id: 'treeload',
            name: '绑定',
            autoload: true,
            pagePath: "/config/editor/func/pluginManager/pluginList/pluginList",
            binds: ['#' + this.sdeTreeId]
        });
        this.addSearchInput();
    },

    /**控件树load数据 方法2*/
    loadData: function (data) {
        this.childrenData = this.childrenData || data || srvFunByParam("pluginTreeSrv", "findPluginTree", {}, false);//获取旧的报告，
        $('#' + this.sdeTreeId).getWidget().loadData([{
            text: '控件库',
            id: "root",
            state: 'closed',
            children: this.childrenData.data
        }]);
        this.addSearchInput();
    },

    /**移除搜索结果标识*/
    removeSearchTig: function () {
        $('.tree-search-selected').removeClass('tree-search-selected');
    },

    /**添加搜索框*/
    addSearchInput: function (data) {
        var _this = this;
        var $sdeTree = $('#' + this.sdeTreeId);
        $sdeTree.prepend('<input id="treeSearch" style="width: 200px;"/>');

        $('#treeSearch').change(function (e) {
            _this.removeSearchTig();
            var value = this.value;
            if (value == null || value == "" || value == undefined || value.length == 0) {
                PluginTree['loadData']();
                return;
            }
            window.pluginTree.collapseAll();
            var data = window.pluginTree.getData(window.pluginTree.getRoot().target);
            _this.handleData(value, data.children);

        });
    },

    /**展开搜索结果,递归*/
    handleData: function (value, datas) {
        for (var i = 0, dl = datas.length; i < dl; i++) {
            var data = datas[i];
            if (data.text && data.text.indexOf(value) >= 0) {
                var node = window.pluginTree.find(data.id);
                if (node && node.target) {
                    window.pluginTree.expandTo(node.target);
                    window.pluginTree.select(node.target);
                    $(node.target).addClass("tree-search-selected");
                }
            }
            var children = data.children;
            var boo = (children == null || children == "" || children == undefined || children.length == 0);
            if (!boo) {
                this.handleData(value, children);
            }
        }
    }
}