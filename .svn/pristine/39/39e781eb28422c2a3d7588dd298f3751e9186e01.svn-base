/*2012/4/21该类实现根据h5api实现拖动排序*/
function Drag() {
};
Drag.prototype.constructor = Drag;
Drag.prototype.domdrugstart = function () {
    var _this = this;
    return function (e) {
        document.getElementById('remove').style.display = 'block';
        dragEl = this;
        e.dataTransfer.setData("text/html", this.innerHTML);
    };
};
Drag.prototype.domdrugenter = function () {
    return function (e) {
        // e.target.classList.add('over');
    };
};
Drag.prototype.domdrugover = function () {
    return function (e) {
        if (e.preventDefault) {
            e.preventDefault();
        }
        // e.dataTransfer.dropEffect = 'move';
        return false;
    };
};
Drag.prototype.domdrugleave = function () {
    return function (e) {
        // e.target.classList.remove('over');
    };
};
Drag.prototype.domdrop = function () {
    return function (e) {
        if (e.stopPropagation) {
            e.stopPropagation();
        }
        if (dragEl != this) {
            dragEl.innerHTML = this.innerHTML;
            this.innerHTML = e.dataTransfer.getData('text/html');
            this.firstChild.style.opacity = '1';
            e.target.style.opacity = '1';
        }
        document.getElementById('remove').style.display = 'none';
        return false;
    };
};
Drag.prototype.domdrapend = function () {
    return function (e) {
        document.getElementById('remove').style.display = 'none';
    }
};
/*end*/

//使用layer.js效果实现
function remainEffection(img, isparent) {
    //保持hover的效果
    if (isparent) {
        $(img).addClass("dyclass");
    } else {
        $(img).parent().addClass("dyclass");
    }
    //遮罩层模糊化
    $("div").addClass("blur");
    $("#layui-layer" + layer.index).removeClass("blur");
    $("#layui-layer" + layer.index + " div").removeClass("blur");
    $("#secondMenuTab").parent().css({'overflow': 'hidden'});
    $("#secondMenuTab").parent().css({'overflowY': 'auto', 'maxHeight': '294px'});//滚动条自动

    backAndTitle($(img).attr('nodeName'), layer.index);
    dragMove(layer.index);
}

/*
 2017/4/23 拖拽创建快捷方式
 */
function dragMove(index) {
    var drag = new Drag();   //新建拖动排序实例
    var columns = document.querySelectorAll('#secondMenuTab .dock-item');
    [].forEach.call(columns, function (column) {
        column.addEventListener("dragstart", drag.domdrugstart(), false);
        column.addEventListener('dragenter', drag.domdrugenter(), false);
        column.addEventListener('dragover', drag.domdrugover(), false);
        column.addEventListener('dragleave', drag.domdrugleave(), false);
        column.addEventListener('drop', function (e) {
            document.getElementById('remove').style.display = 'none';
        }, false);
    });


    var msb = document.getElementById('layui-layer-shade' + index);//获取layer蒙版
    msb.addEventListener('dragover', function (e) {
        e.preventDefault()
    }, false);
    msb.addEventListener('drop', drop, false);

    function drop(e) {
        e.preventDefault();
        var data = e.dataTransfer.getData('text/html');
        var a = $('<a class="dock-item" firstmenuid="add" style="width: 80px; left: 480px;"></a>')
        var id = $(data).eq(0).attr('id') || new Date().getTime();
        a = a[0];
        a.innerHTML = data;
        a.addEventListener("dragstart", drag.domdrugstart(), false);
        a.addEventListener('dragover', function (e) {
            e.preventDefault()
        }, false);
        a.addEventListener('drop', drag.domdrop(), false);
        document.getElementById('remove').style.display = 'none';

        var check = $('#dock-container').find('#' + id).length;
        if (!!check) return; //说明dock-container中已经存在快捷方式，直接结束
        document.getElementById('dock-container').appendChild(a);

        //todo 插入到数据库
        var specificStr = id;
        var dbdata = getObject(parent.menuAllData, specificStr);
        var linkMenu = $W.databind.databind({
            id: "menulink",
            pagePath: "config/hip_base/search",
            autoload: false
        });

        var param = {
            custom_menu_id: dbdata.data.attributes.url == undefined ? dbdata.data.id : null,//这里传入的应该是他本身的id
            custom_func_id: dbdata.data.attributes.url == undefined ? null : dbdata.data.id,//这个要根据判断传值，如果有url的才传值
            custom_type: dbdata.data.attributes.url == undefined ? 1 : 2,
            custom_name: dbdata.data.text,
            custom_order: 0,//传入的菜单排序
            account_id: loginAccount.accountId,
            //createtime:new Date(),
            datasource_type: 1
        };
        linkMenu.submit(param, function () {
            // alert('成功')
        });
    }
}

/*end*/

/*
 2017/4/23 添加返回按钮和标题
 */
function backAndTitle() {
    var name = arguments[0];
    var index = arguments[1];
    var secondMenu_li = $("#secondMenuTab");//这里应该是去父页面找
    var backClick = function () {
        return function () {
            var secondMenu_li = $("#secondMenuTab");//这里应该是去父页面找
            secondMenu_li.empty();//删除下面的子元素
            showSpecific(name);
            secondMenu_li.parent().parent().find('span[name="backing"]')
                .css({'display': 'none'});
            secondMenu_li.parent().parent().find('span[name="title-name"]')
                .html('<h1 style="color:#333;font-size:23px;">' + name + '</h1>');
        };
    };

    var cssObj = {
        "position": "absolute",
        "left": "-18px",
        "top": "-7px",
        "font-size": "0",
        "line-height": "initial",
        "width": "30px",
        "height": "30px",
        "display": "none"
    };

    var $back = $('<span name="backing"></span>')
        .css(cssObj)
        .bind('click', backClick())
        .append('<img src="' + ctx + '/config/hip_base/main_menu/images/backing.png" style="display:block;width:100%;height:100%">');
    secondMenu_li.parent().parent().append($back)
    //end
    /*添加面板标题*/
    // top: 'calc(50% - '+_top+'px)',
    //     left: 'calc(50% - '+_left+'px)',
    var cssObj = {
        "position": "absolute",
        "left": "0",
        "top": "-66px",
        "font-size": "0",
        "line-height": "initial"
    };
    var check = secondMenu_li.parent().parent().find('div[name="title-name"]').length;
    name = check
        && secondMenu_li.parent().parent().find('div[name="title-name"] h1').html() + '>' + name
        || name;
    if (!!check) {
        var _width = name.length * 23;//粗略计算一个字23px;
        secondMenu_li.parent().parent().find('div[name="title-name"]').css('left', 'calc(50% - ' + _width / 2 + 'px)');
        secondMenu_li.parent().parent().find('div[name="title-name"] h1').html(name);
    } else {
        var $titleName = $('<div name="title-name"></div>')
            .css(cssObj)
            .append('<h1 style="color:#333;font-size:23px;white-space: nowrap;">' + name + '</h1>');

        var _width = name.length * 23;//粗略计算一个字23px；
        $titleName.css('left', 'calc(50% - ' + _width / 2 + 'px)');
        secondMenu_li.parent().parent().append($titleName)
    }
}


function generateNavicageForSpecificmenu(textArray, idArray) {//这个数据是经过反转的
    var retStr = '当前位置：';
    for (var i = 0; i < textArray.length; i++) {
        retStr += '<a href="javascript:void(0);" style="color:#000000;" id="' + idArray[i] + '" onclick="showSpecific(this);" nodeName="' + textArray[i] + '">' + textArray[i] + " > " + '</a>';
    }
    return retStr;
}


