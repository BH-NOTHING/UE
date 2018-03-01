var menuAllData;
function showMainmenuDiv() {
    var rightTopMenuTab = $("#secondMenuTab");
    rightTopMenuTab.empty();//清空掉里边的东东
    rightTopMenuTab.append($("#firstMenuTab").html());

    layer.open({
        type: 1,
        title: '',//如果是这样就没有title了
        fix: true,
        // maxmin: true,
        shadeClose: true,
        closeBtn: true,
        isCenter: true,
        area: ['auto', 'auto'],
        scrollbar: false,
        content: rightTopMenuTab//使用类型为1的，然后加到div里边去
    });
    setLayerHeight();

    $("div").addClass("blur");
    $("iframe").addClass("blur");
    $("#layui-layer" + layer.index).removeClass("blur");
    $("#layui-layer" + layer.index + " div").removeClass("blur");
}

//dataUp menu对象实例
function showNextMenuForSpecificMenu(dataUp) {//当不是二级菜单 的时候，这个就有问题了
    //todo 根据传过来的数据，生成下一级的菜单
    var secondMenu_li = $("#secondMenuTab");//这里应该是去父页面找
    var itemLength = secondMenu_li.find('>div>a');

    if (dataUp.isUrlMenu()) {
        //todo 跳转到指定的url
        var navicateHtml = generateNavicageForSpecificmenu(dataUp.text.reverse(), dataUp.id.reverse()) + dataUp.data.text;
        setSrc(dataUp.data.attributes.url, navicateHtml);
        //关闭弹出层
        layer.close(layer.index);
        return true;
    } else {

        secondMenu_li.empty();//删除下面的子元素
        secondMenu_li.append(dataUp.generateTabHtml_Enhancee(4));
        //这里如果不用弹出的，要动态设置 这个宽度和长度

        if ($(".layui-layer-shade").length == 0) {//如果没有遮罩层的话
            layer.open({//这里要根据判断来open
                type: 1,
                title: '',//如果是这样就没有title了
                fix: true,
                shadeClose: true,
                closeBtn: true,
                area: ['auto', 'auto'],
                isCenter: true,
                scrollbar: false,
                content: secondMenu_li//使用类型为1的，然后加到div里边去
            });
        }
        /*author: Rum 2017/5/17*/
        var itemLength = secondMenu_li.find('>div:eq(0)>a').length;
        var _height = secondMenu_li.find('>div').length >= 2 ? false : 175;
        var _left = (itemLength * 120 + 30) / 2;
        var _top = (secondMenu_li.find('>div').length >= 2 ? 294 : 175) / 2;
        layer.style(layer.index, {
            top: 'calc(50% - ' + _top + 'px)',
            left: 'calc(50% - ' + _left + 'px)',
            height: _height
        });
        /*end*/
        setLayerHeight();
        return false;
    }
}
function showSpecific(obj) {
    var name = $(obj).attr("id") || $(obj).attr("alt") || obj;
    var myMenu = Object.create(Menu);
    myMenu.init(parent.menuAllData, name);
    if (!showNextMenuForSpecificMenu(myMenu)) {//如果是url的不用下面的效果
        remainEffection(obj, true);
    }
}
//home king 20180202
function clickHome(obj) {
    window.location.replace(ctx + "/pwplogin/toMainPage.do")
}

//当图片加载失败的时候使用默认图片
function imageOnError() {
    var img = event.srcElement;
    img.src = ctx + "/config/hip_base/images/default.png";
    img.onerror = null;     //控制不要一直跳动
}

$(document).ready(function () {

    //点击了body的任何位置都将 右上角【系统设置】的高亮去掉
    $('body').click(function(){
        //只有当【系统设置】存在高亮的时候隐藏
        if($('.js-combox-btn').hasClass('select')) {
            $('.js-combox').hide();
            $('.js-combox-btn').removeClass('select');
        }
    });

    //点击系统设置
    $(document).on('click', '.js-combox-btn', function(){
        $(this).addClass('select').find('.js-combox').show();
    });
   /* //3秒轮询一次
    window.onload=setInterval(function(){
        window.mainFullcendarRemind.submit("load",{user_id:loginAccount.attributes.ACCOUNT_ID},function(data){
            if(data>0) {
                $(".fullcendar.remind").text(data);
                $(".total.remind").text($(".fullcendar.remind").text());
            }else{
                $(".fullcendar.remind").removeClass("remind");
                $(".total.remind").removeClass("remind");
            }
        });
    },5000);
*/
});

function setLayerHeight() {
    var height = $("#secondMenuTab").height() + parseInt($("#secondMenuTab").css("padding")) * 2.1;
    $(".layui-layer-content").height(height);
    layer.style(layer.index, ['auto', height]);
}

/**
 * updatePassword
 */
function updatePassword() {
    if($("#passWord").val()!=null && $("#passWord").val().length > 0){
        account.passWord = $("#passWord").val();
        var url = window.ctx + "/services/" + "uicore/common/frame/treeTab/main_tree/resetAccountPwd";
        $W.service.callService(url,{account:account},function(data){
            $("#updatePassWordDialog").getWidget().close();
            $.messager.alert(data.title,data.info,'info');
        });
    }else{
        return false;
    }
}

function skipToPage(m,n){
    showMainmenuDiv();
    $(".layui-layer.layui-layer-page.layer-anim").css("display","none");
    showSpecific($(".layui-layer.layui-layer-page.layer-anim").find("img:eq("+(m-1)+")"));
    var dockContainer;
    //dockContainer = $(".dock-container").length == 1 ? $(".dock-container") : $(".dock-container:eq(1)");
    dockContainer = $("#secondMenuTab");
    showSpecific(dockContainer.find("img:eq("+(n-1)+")"));
    $(".layui-layer.layui-layer-page.layer-anim").css("display","none");
    setTimeout(function(){
        $("div#h").trigger("click");
    },0);
}

