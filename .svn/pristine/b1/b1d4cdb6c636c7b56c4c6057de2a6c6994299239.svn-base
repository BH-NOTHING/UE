/**
 * Created by Nick on 2017/7/21.
 */
var fish;
var firstLeft;


/**
 * 增加方法，提供给子页面调用
 * @Autor： Nick
 * @Date : 2017-08-18
 */
function addMenuTitle(menuTitle) {

    console.log($('#navicate div span.menu-title').length > 0);
    if($('#navicate div span.menu-title').length > 0) {
        $('#navicate div span.menu-title').text(' > '+menuTitle);
    } else {
        $('#navicate div').append("<span class='menu-title'>  > " + menuTitle + "</span>");
    }
}

$(document).ready(function() {
    /*2012/4/21该类实现根据h5api实现拖动排序*/

    //获取菜单数据
    var dbId = $W.databind.databind({
        id: "mainMenuDb",
        pagePath: "config/hip_base/search",
        autoload:false
    });

    dbId.load({accountid:accountid},function(data){
        var firstMenuDiv = $("#dock-container");
        parent.menuAllData = data[0].children;
        loadDock(data,firstMenuDiv);
        $("#firstMenuTab").html(firstMenuDiv.html());//初始化，给右上角的主菜单直接调用

    });

    function loadDock(data,firstMenuDiv){
        var drag = new Drag();   //新建拖动排序实例
        var firstMenu = data[0].children;//遍历这个，得到一级菜单

        var dataMap = new Map();
        var idAndIndexMap = new Map();
        //
        for (var i = 0; i < firstMenu.length; i++) {
            dataMap.set(firstMenu[i].id, firstMenu[i]);
            idAndIndexMap.set(firstMenu[i].id, i);
            var icon = firstMenu[i].attributes.icon === undefined ? "default" : firstMenu[i].attributes.icon;
            var firstMenuHtml = '<a class="dock-item" firstMenuId="' + i + '" ><img  src="'+ctx+'/' + icon + '.png" id="' + firstMenu[i].id + '" nodeName="'+firstMenu[i].text+'"  ><span style="display: block;">' + firstMenu[i].text + '</span></a>';
            firstMenuDiv.append(firstMenuHtml);

        }
        //todo 快捷菜单生成 如果自定义的菜单可以在dataMap中找到 start
        var customData = data[0].customMenu;//自定义快捷方式传的数据格式不一样，所以不使用menu对象的形式编写
        customData.forEach(function (e) {
            var rete = Object.create(Menu);
            if(e.custom_func_id!==null&& e.custom_func_id!==undefined){//这个说明是url菜单
                rete.init(firstMenu, e.custom_func_id);
                //在页面生成菜单
            }else{//否则为弹出层菜单
                rete.init(firstMenu, e.custom_menu_id);

            }
            firstMenuDiv.append(rete.generateCurrentMenuHtml(0));

        });

        //todo 快捷菜单生成 如果自定义的菜单可以在dataMap中找到 end
        /*2017/4/21 添加拖到排序*/
        var columns = document.querySelectorAll('#dock-container .dock-item');
        [].forEach.call(columns,function(column){
            column.addEventListener("dragstart",drag.domdrugstart(),false);
            column.addEventListener('dragenter', drag.domdrugenter(), false);
            column.addEventListener('dragover', drag.domdrugover(), false);
            column.addEventListener('dragleave', drag.domdrugleave(), false);
            column.addEventListener('drop', drag.domdrop(), false);
            column.addEventListener('dragend', drag.domdrapend(), false);
        });
        /*end*/
        //这个循环之后才实现鱼眼效果 start

        fish = $('#dock').Fisheye({
            maxWidth: 80,
            items: 'a',
            itemsText: 'span',//表示这个不使用display none 作用 后台会报找不到该属性，因为作用了
            container: '.dock-container',
            itemWidth: 80,
            proximity: 80,//放大时候的大小
            halign: 'center'

        });
        var itemArray = $(".dock-item");
        for (var i = 0; i < itemArray.length; i++) {//这里有快捷方式的要过滤掉
            var id = itemArray.eq(i).parent("a").attr("id");
//						console.log(id);
//						if(i==0) firstLeft = parseInt(itemArray.eq(i).parent().css("left"));//取得第一个元素的左缩进
            itemArray.eq(i).children("img").eq(0).attr("id", id);
            itemArray.eq(i).children("img").eq(0).attr("onerror", "imageOnError();");
            var firstMenuId = itemArray.eq(i).attr("firstMenuId");
            if(!itemArray.eq(i).data("events")||!itemArray.eq(i).data("events")["click"]) {//如果没有绑定事件，才进行绑定
                itemArray.eq(i).children("img").eq(0).attr("onclick", 'showSpecific(this)');//这里要避免重复引入，不然作用域不同，js变量不同
            }
        }
		
		
    }

});


//拖拽事件监听等
var rem = document.getElementById('remove');
rem.addEventListener('dragover', dragover,false);
rem.addEventListener('drop', remove,false);
function dragover(e){
    e.preventDefault();
}
function remove(e){
    e.preventDefault();
    var data = e.dataTransfer.getData('text/html');
    var id = $(data).eq(0).prop('id');
    var el = document.getElementById(id);


    var specificStr = id;
    var dbdata  = getObject(parent.menuAllData,specificStr);
    var linkMenu = $W.databind.databind({
        id: "menulink",
        pagePath: "config/hip_base/search",
        autoload:false
    });
    var param={
        custom_menu_id:dbdata.data.attributes.url==undefined?dbdata.data.id:null,
        custom_func_id:dbdata.data.attributes.url==undefined?null:dbdata.data.id,
        accountid:accountid,
        custom_type:dbdata.data.attributes.url==undefined?1:2
    };
    ;
    linkMenu.submit("delete",param, function(){
        // alert('成功')
    });

    el.parentNode.parentNode.removeChild(el.parentNode);
    document.getElementById('remove').style.display = 'none';
}

