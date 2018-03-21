var sde;
var sdefun = new sdeFun();
//系统关键字

sdefun.addSDEPlugins_left("sde-toolbar-updateTemplate", "保存", {},"updateReport();");// 在工具条上自定义菜单参数1=id,参数2=名称,4如果有第四个参数时，优先执行第四个参数,
sdefun.addSDEPlugins("sde-toolbar-source", "源码", {},"sde.setExecCommand(\'source\')");// 在工具条上自定义菜单参数1=id,参数2=名称,4如果有第四个参数时，优先执行第四个参数,
sdefun.addSDEPlugins("sde-toolbar-cleardoc", "清空", {}, "cleardoc()");// 在工具条上自定义菜单参数1=id,参数2=名称,4如果有第四个参数时，优先执行第四个参数,
sdefun.addSDEPlugins("sde-toolbar-cleardoc", "所有控件值", {}, "alert(JSON.stringify(sde.getAllControl()))");// 在工具条上自定义菜单参数1=id,参数2=名称,4如果有第四个参数时，优先执行第四个参数,
sdefun.addSDEPlugins("sde-toolbar-sdeTools", "操作栏", {}, "sdeTools();");// 在工具条上自定义菜单参数1=id,参数2=名称,4如果有第四个参数时，优先执行第四个参数,

var title = '<div id="sdeTools" style="float:left;display:none; ">'
    + "<div><span>模板名称：<input type='text'  id='report_name' /></span>&nbsp" // display:none;
    + '<button onclick="lodop()">lodop</button>'
    + '<button onclick="CheckIsInstall(1)">打印预览</button>'
    + '<button onclick="CheckIsInstall(2)">直接打印</button>'
    + '<button onclick="CheckIsInstall(3)">打印维护</button>'
    + '<button onclick="CheckIsInstall(4)">打印设计</button>'
    + '<button onclick="CheckIsInstall(5)">打印码</button>'
    + '<button onclick="CheckIsInstall_lyon(1)">打印_lyon</button>'
    + '</div>';

var footer = 'footer';
var toolbars = '';

/**
 * templateFindById 第一个参数是模板id 将获取最新版本的模板 第二个参数是版本号，必须与第一个参数结合用
 * 如果要findByVerId，请用findByVer_Id(param);
 */
var content = "",
    head,
    mode = 'DESIGN',
    queryObject = sdefun.getQueryObject(),
    report_id = queryObject.report_id || 413,
    report = sdefun.getByIdReport({"id": report_id});

if (report) {
    content = report.content;
    document.getElementById("report_mode").value = mode;
    document.getElementById("syn_version").value = report.syn_version;
    delete report.content;
    document.getElementById("reportJson").value = JSON.stringify(report);
}

window.onload = function() {
    sde = new SDE({
            id : "myEditor",
            title : title,                  // 自定义title
            footer : footer,                // 自定义footer
            _content : content,
            body_font_family : "宋体",       // 默认字体
            mode : mode,                    // mode可选：DESIGN（设计）、EDITOR（编辑）、READONLY（只读）、REVISE（修订模式）
        });
    sde.ready(function() {

    });
};

// -----------------右键扩展 sta
UE.Plugins = [ {
    group : '这里加一个菜单',
    icon : '123',
    subMenu : [ {
        label : '这里是一个子菜单',
        cmdName : '123',
        exec : function() {
            alert('这里天加一个菜单');
        }
    }, '-', {
        label : '菜单',
        cmdName : '123',
        exec : function() {
            alert('这个菜单');
        }
    } ]
}, '-', {
    label : '清空编辑器',
    cmdName : 'cleardoc',
    exec : function() {
        alert('即将清空编辑器内容');
        sde.setExecCommand('cleardoc');
    }
}, '-', {
    label : '菜单',
    cmdName : '123',
    exec : function() {
        alert('这个菜单');
    }
} ];
// -----------------右键扩展 end
function sdeTools() {
    var display = $("#sdeTools").css("display");
    $("#sdeTools").css("display", display == "block" ? "none" : "block");
}

function updateReport() {
    if(window.SDE_CONFIG.IS_CONTENTCHAGNE!=="0" ){
        console.log("已经改变："+window.SDE_CONFIG.IS_CONTENTCHAGNE);
    }else{
        console.log("没有改变："+window.SDE_CONFIG.IS_CONTENTCHAGNE);
    }
    var reportJson = JSON.parse(document.getElementById("reportJson").value);
    reportJson.content = sde.html();
    reportJson.reportDatas =  sde.getAllControl();
    //更新报告数据
    var res = sdefun.updateReport(reportJson);
    if(res.code){
        /*var getReport = sdefun.getByIdReport({"id": reportJson.id});
        sde.html(getReport.content);
        sde.setReadonly();*/
        window.location.reload();
    }
    alert(res.msg);

}