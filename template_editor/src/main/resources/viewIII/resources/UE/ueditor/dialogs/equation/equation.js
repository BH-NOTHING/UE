/**
 * Copyleft 2014 pcb (puchonbin@163.com)
 *   
 */
UE.registerUI('dialog',function(editor,uiName){
    //创建dialog
    var dialog = new UE.ui.Dialog({
        //指定弹出层中页面的路径，这里只能支持页面,因为跟addCustomizeDialog.js相同目录，所以无需加路径
        iframeUrl:'dialogs/equation/mathdialog.html',
        //需要指定当前的编辑器实例
        editor:editor,
        //指定dialog的名字
        name:uiName,
        //dialog的标题
        title:"公式输入",

        //指定dialog的外围样式
        cssRules:"width:600px;height:350px;",

        //如果给出了buttons就代表dialog有确定和取消
        buttons:[
            {
                className:'edui-okbutton',
                label:'确定',
                onclick:function () {
                    var math=$("#"+dialog.id+"_iframe").contents().find("#matheq_latex");
                    //var x=math.focus().mathquill('revert');
                    var mathcode=$("#"+dialog.id+"_iframe").contents().find("#matheqCode");
                    //获取html显示
                    $(math).children().eq(0).remove();
                    var html = $(math).html();
                    if (html == '') {
                       dialog.close(true);
                       return;
                    }
                    html = "<span class=\"mathquill-rendered-math\"  >" + html + "</span>";
                  
                  //获取相应 编码
                   var codehtml=$(mathcode).html();
                   html+="<span style=\"display: none\">"+codehtml+"<span>";
                   
                   this.editor.execCommand('insertHtml',html);
                   dialog.close(true);
                }
            },
            {
                className:'edui-cancelbutton',
                label:'取消',
                onclick:function () {
                    dialog.close(false);
                }
            }
        ]
    });

    //参考
    var btn = new UE.ui.Button({
        name:'equation' + uiName,
        title:'插入公式',
        //需要添加的额外样式，指定icon图标，这里默认使用一个重复的icon
        cssRules :'background-position: -830px 0px;',
        onclick:function () {
            //渲染dialog
            dialog.render();
            dialog.open();
        }
    });

    return btn;
}/*index 指定添加到工具栏上的那个位置，默认时追加到最后,editorId 指定这个UI是那个编辑器实例上的，默认是页面上所有的编辑器都会添加这个按钮*/);


