/**
 * Created by Ben on 2017/5/10.
 */
var Menu = {
    id: null,//children 和刚从数据查回来 的是没有这些属性的
    text: null,//children 和刚从数据查回来 的是没有这些属性的
    data:null,
    children:null,//初始化后将data内的children对象copy到这个属性来
    init:function(allMenuData,specStr){//初始化
        var myMenu = getObject(allMenuData,specStr);
        this.id = myMenu.id;
        this.text = myMenu.text;
        this.data = myMenu.data;
        this.children = myMenu.data.children;
    },
    getChildrenMenu:function(){
        var childMenu = new Array();
        this.children.forEach(function(e){
            var child = Object.create(Menu);
            child.data = e;
            childMenu.push(child);
        });
        return childMenu;
    },
    isUrlMenu :function(){
        return this.data.attributes.url != undefined && this.data.attributes.url != "";
    },
    isHaveIcon:function(){
        return this.data.attributes.icon != undefined && this.data.attributes.icon != ""&&this.data.attributes.icon !="icon-lock";
    },
    generateTabHtml_Enhancee:function(colSpan){
        var html = $('<div></div>');
        var strdiv = '<div class="dock-container" style="margin-top: 18px;display: flex;"></div>';
        var stra = '<a class="dock-item" style="width: 80px; left: 600px;">';
        stra +='	<img style="width:64px; height: 64px;" />';
        stra +='    <span style="display: block;height:32px;"></span>';
        stra +='</a>';
        var $div = $(strdiv);
        var childrenData = this.getChildrenMenu();

        for (var i=0; i<childrenData.length; i++){
            if(0 === i%colSpan && 0 !== i){
                html.append($div);
                $div = $(strdiv);
            }
            var $a = $(stra);
            var imgSrc;
            if(childrenData[i].isHaveIcon() && childrenData[i].data.attributes.icon != 'default') {
                imgSrc = ctx + "/" + childrenData[i].data.attributes.icon + ".png";
            } else {
                imgSrc = ctx + "/config/hip_base/images/default.png";
            }
            $a.find('img').attr('src', imgSrc)
                .attr('onclick', 'showSpecific(this)')
                .attr('id', childrenData[i].data.id)
                .attr('nodeName', childrenData[i].data.text);
            $a.find('span').html(childrenData[i].data.text);
            $div.append($a);
        }
        html.append($div);
        return html.html();
    },
    getIcon:function(){
        var imgSrc ;
        if (this.isHaveIcon()) {
            imgSrc = ctx + "/" + this.data.attributes.icon + ".png";
        }
        else {
            imgSrc = ctx + "/config/hip_base/images/default.png";
        }
        return imgSrc;
    },
    generateCurrentMenuHtml:function(){
        var currentMenuHtml = '<a class="dock-item" ><img  src="' + this.getIcon() + '" id="' + this.data.id + '" nodeName="'+this.data.text+'"  ><span style="display: block;">' + this.data.text + '</span></a>';
        return currentMenuHtml;
    }


}