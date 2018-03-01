
#  UE编辑器的API文档
| 编写人 | 编写日期 |主要内容|审核人  | 版本   |
|--------|----------|------  |--------|------  |
| King   | 2017/5/28 |API文档 |Nick    |v2.0.4    |

[TOC]

* 详细方法参考《sde_func.js》、《sde.design.js》
http://localhost:8088/alo/viewIII/resources/UE/sde_func.js
http://localhost:8088/alo/viewIII/resources/UE/dist/js/sde.design.js

# 1.初始化
## 1.1.基础文件
1.1.1.依赖文件js。  
    这些文件最好都弄到一个公共的 <#include '/resources/UE/commonJs.vop'>里,其他项目直接include。

```
    <script type="text/javascript" src="${ctx}/viewIII/resources/UE/sde.config.js"></script>
    <link rel="stylesheet" href="${ctx}/viewIII/resources/UE/ueditor/themes/default/css/ueditor.css" />
    <script type="text/javascript" src="${ctx}/viewIII/resources/UE/ueditor/ueditor.all.js"></script>
    <script type="text/javascript" src="${ctx}/viewIII/resources/UE/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" id="design" src="${ctx}/viewIII/resources/UE/dist/js/sde.design.js"></script>
    <script type="text/javascript" src="${ctx}/viewIII/resources/UE/sde_func.js"></script>
    
    
```

## 1.2.初始化书写区域
1.2.1.编辑器书写区域

```
 <script id="myEditor" type="text/plain" ></script>

``` 
## 1.3.初始化
1.3.1.初始化js

```
        var sde;
 window.onload = function() {
	sde = new SDE({
		id : "myEditor",
		title : title, // 自定义title
		footer : footer, // 自定义footer
		toolbars : toolbars,
		mode : 'DESIGN',
		_content : content // 自定义Html
	});
	sde.ready(function() {
		// sde对象是异步加载，即必须等待sde.ready加载完成后才能执行。
		sde.setMode("DESIGN"); // mode可选：DESIGN（设计）、EDITOR（编辑）、READONLY（只读）
		sde.setControls('name','奥巴马');
	});

};
 
  
```
 
 
 
 
 
  # 2. 接口服务
  分为两个部分：  
  第一个部分为编辑器自身提供的API接口《sde.design.js》;  
  第二个部分为编辑器数据库操作的业务接口《sde_func.js》;  
  
 ## 2.1. API接口《sde.design.js》
    直接通过sde.xxx的形式调用；
 
| 序号 | 方法 | 参数 |参数说明容| 返回类型 | 描述   |
|:--------: |:--------|:----------|:------  |:--------| :------: |
|1        | ready| A |对象 |  null |    |
|2        | html| A |对象 |String    | 获取所有的html   |
|3        | getControl| A |对象 |String    | 根据id获取控件值   |
|4        | setControl| A |对象|null    |   根据id设置控件值  |
|5        | setControls| ID,VALUE |控件Id ,值 | null  |   根据id设置控件值    |
|6        | setMode| mode |null |null    | 设计编辑器的模式    |
|7        | checkControl| null |null |String    |  |
|8        | setContent| text |null |null    |追加编辑器内容    |
|9        | setDesign| null |null |null    |设置编辑器为： 设计模式    |
|10       | setEditor| null |null |null    |设置编辑器为： 编辑模式    |
|11       | setReadonly| null |null |null    |设置编辑器为： 只读模式  |
|12       | setDisabled| code| null | null |根据code,设置编辑器为：禁用模式 param参数 code是工具条中Ueditor的值   |
|13       | setEnabled| null |null |null    | 设置编辑器为：启用模式    |
|14       | setExecCommand|code | null |null    | setExecCommands param参数 code是Ueditor的命令   |
|15       | setExecCommand| code，html |null |null    |setExecCommands param参数 code是Ueditor的命令    |
|16       | getAllControl| null |null |json数组    |调用获取所有的key_value   |
|17       | setEditorCodeValue| code，value |boolean |tempPlugin的json数组    |将数据设置到报告中，防止出现不存在的code报错    |
|18       | getEditorCodeValue| code|null |string    |获取报告code值，防止出现不存在的code报错   |
|19       | clearLoad| content |null |null    | 清空编辑内容在将content内容加载到编辑器    |
|20       | inserTextarea|param | json |null    | 插入一个文本域  |
|21       | insertPic| param |json |null    |插入一个占位图   |
|22       | insertButton| param |json |null    |插入一个按钮   |
|23       | createDialog| param |json |null    |新建一个弹框    |
|24       | getDialog| param|json |null    |得到一个弹框   |


 ## 2.2. 业务接口《sde_func.js》
     通过new sdeFun().xxx的形式调用；
 | 序号 | 方法 | 参数 |参数说明容| 返回类型 | 描述   |
|:--------: |:--------|:----------|:------  |:--------| :------: |
|1        | addSDEPlugins| myName, title, jsons |追加内容(html) |  null | 在工具条上自定义菜单参数1=id,参数2=名称,参数3=json,    |
|2        | makePluginStore| null |null |json数组    |制作模板库html   |
|3        | pluginToJson| plugin |plugin对象， |json    | 将数据库读取到的plugin转成json  |
|4        | pluginFindAll| null |null |plugins结果集    |获取所有的控件    |
|5        | pluginFindByParam| param |json |plugins结果集    |根据参数获取控件    |
|6        | pluginFindByCode| code |code |plugins结果集    | 根据Code获取控件    |
|7        | pluginFindByVId| param |json |plugins结果集    | 根据版本号获取控件   |
|8        | addPlugin| json，html |null |plugin对象   |新增控件    |
|9        | makeHtmlByPluginJson| json |json |html    |makeHtmlByPluginJson   |
|10       | getONodeHtml| json, html |null |html    |getONodeHtml    |
|11       | pluginIsContinue| plugin |plugin |oNodeHtml    |控件已经存在，提示是否继续   |
|12       | pluginDeleteById| null|null |null   |根据id删除控件   |
|13       | templateSave| param |json |null    |模板保存   |
|14       | templateFindById|templateId, verId |templateId, verId  |template结果集    |    templateFindById 第一个参数是模板id 将获取最新版本的模板 第二个参数是版本号，必须与第一个参数结合用  如果要findByVerId，请用findByVer_Id(param);    |
|15       | templateCopy| null |null |null    |模板另存    |
|16       | templateDeleteById| template_ver_id |template_ver_id |template结果集    |根据模板Id删除模板   |
|17       | insertImages| imageList, prefix |imageList, prefix |null    |insertImageList 根据imageList导入图片   |
|18       | insertImage| image, prefix|image, prefix |null    |insertImage 根据image导入图片   |
|19       | getQueryObject| null |null |obj对象    |获取get参数，转换成QueryObject对象 return值 Object    |
|20       | getQueryStr| name|name |字符    | 获取get参数，根据name获取参数值value，并返回value return值 value 注意： value 有可能出现中文乱码，推荐使用getRequestObject();  |
|21       | jsonKeyToLowerCase| json |json |json    |将json的key 转成小写  |
|22       | jsonKeyToUpperCase| json |json |json    |将json的key 转成大写    |
|23       | getIdCard| UUserCard, num |UUserCard, num |未知    |根据身份证号获取生日，年龄，性别   |
|   24    | pluginFindByParam| param|param|未知    |根据Param获取plugin，比如根据类型查找控件 |
|   25    | addReport|param |param|未知    |保存报告|
|   26    | submitReport|param |param|未知    | 提交报告|
|   27    | findAllReport| param|param|未知    |查询所有报告 |
|   28    | getByIdReport|param|param|未知    |getById查询报告|
|   29    | updateReport| param|param|未知    |更新报告 |
|   30    | delReport| param |param|未知    |删除报告 |
|   31    | addReportData| param|param|未知    |新增报告数据 |
|  32    | updateReportData| param |param|未知    |更新报告数据 |
|   33    | delReportData| param |param|未知    |删除报告数据 |
|   other1   | findBySrvFunParam| srvName, funName, param, async |param|res.data    |根据findBySrvFunParam查询 param参数 srvName 服务名称 ,funName 方法名称 ,param参数,async 同步锁，默认true return res.data |
|    other2    | srvFunByParam| srvName, funName, param, async |param|res    |根据findBySrvFunParam查询 param参数 srvName 服务名称 ,funName 方法名称 ,param参数,async 同步锁，默认true return res |


 
 

 
 # 3.demo案例
 ## 3.1.注意事项
需修改一下js的路径，并且数据库支持数据模型即可（editor.editorSrv.XXX。如templateSrv）

```
 
 <html>
<head>
    <title>电子病历编辑器</title>   
    <meta charset="utf-8" />
	<#include '/common/head.vop'>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <#include '/resources/UE/commonJs.vop'>
    <script src="demo.js"></script>
</head>

<body>
	<div class="barcode2" id="divCode" style="position: absolute;top: 90px;left: 980px; z-index: 1000;"></div>
     <script id="myEditor" type="text/plain" ></script>
    
    <script>
   var sde;
var sdefun = new sdeFun();
// -----------------扩展方式
var json = [ {name : '占位符',title : '图片占位符',onclick : 'sde.insertPic();'}, 
             {name : '按钮',title : '插入按钮',onclick : 'sde.insertButton();'},
             {name : '文本域',title : '文本域',onclick : 'sde.inserTextarea();'},
             {name : 'iframe',title : 'iframe',onclick : 'sde.getDialog();'}
             ];
sdefun.addSDEPlugins("sde-toolbar-test", "扩展", json);// 在工具条上自定义菜单参数1=id,参数2=名称,参数3=json,


// -----------------右键扩展
UE.Plugins = [{
    group: '这里加一个菜单',
    icon: '123',
    subMenu:[{
            label: '这里是一个子菜单',
            cmdName: '123',
            exec: function() {
                alert('这里天加一个菜单');
            }
        },
        '-',
        {
            label: '菜单',
            cmdName: '123',
            exec: function() {
                alert('这个菜单');
            } 
        }
        ]              
},
'-',
{
    label: '清空编辑器',
    cmdName: 'cleardoc',
    exec: function() {
        alert('即将清空编辑器内容');
        sde.setExecCommand('cleardoc');
    } 
},
'-',
{
    label: '菜单2',
    cmdName: '123',
    exec: function() {
        alert('这个菜单2');
    } 
}
];

var title = '<div style="float:left;">' // display:none;
	/*+ '<button onclick="sde.setExecCommand(\'pagebreak\');">插入分页符</button>'
	+ '<button onclick="autotypeset();">格式化</button>'
	+ '<button onclick="sde.setExecCommand(\'insertrow\')">插入行</button>'
	+ '<button onclick="sde.setExecCommand(\'deleterow\')">删除行</button>'
	+ '<button onclick="sdefun.clearLoad(\'去除后加载\');">清除后加载</button>'
	+ '<button onclick="setContent();">追加html</button>'*/
		
		+ '<button onclick="addReport();">保存报告</button>'
		+ '<button onclick="sdefun.updateReport();">更新报告</button>'
		+ '<button onclick="sdefun.templateSave();">保存模板</button>'
		+ '<button onclick="sdefun.templateCopy();">模板另存</button>'
		+ '<button onclick="sde.setDesign();">设计模式</button>'
		+ '<button onclick="sde.setEditor();">编辑模式</button>'
		+ '<button onclick="sde.setReadonly();">只读模式</button>'
		+ '<button onclick="sde.setExecCommand(\'source\')">切换源码</button>'
		+ '<button onclick="sde.setExecCommand(\'cleardoc\')">清空内容</button>'
		+ '<button onclick="sde.setExecCommand(\'time\')">插入时间</button>'
		+ '<button onclick="sdefun.inserthtml()">插入html</button>'
		+ '<button onclick="addButton();">插入按钮</button>'
		+ '<button onclick="getDialog();">获取Dialog</button>'
		+ '<button onclick="sde.setControls(\'name\',\'18\')">设置name</button>'
		+ '<button onclick="alert(JSON.stringify(sde.getControl(\'name\')));">获取name</button>'
		+ '<button onclick="alert(JSON.stringify(sde.getAllControl()))">获取所有控件</button>'
		+ '<button onclick="alert(sde.html());">获取html</button>'
		+ '<button onclick="sdefun.insertImages(\'list\',\'/UE\')">导入图片</button>'
		+ '<button onclick="alert(sdefun.getIdCard(\'45032419920914581X\',2));">身份证</button>'
		+ '<button onclick="createBarcode(\'divCode\',\'test\',\'B\');">条形码</button>'
		+ '</div>';

var footer = '';
var toolbars = '';

/**
 * templateFindById 第一个参数是模板id 将获取最新版本的模板 第二个参数是版本号，必须与第一个参数结合用
 * 如果要findByVerId，请用findByVer_Id(param);
 */
var ver_id = 6, template_id;
var template = sdefun.templateFindById(template_id, ver_id);
var temp = document.getElementById("template_id");
temp.value = template.template_id;
document.getElementById("template_ver_id").value = template.template_ver_id;
document.getElementById("syn_version").value = template.syn_version;
var content = template ? template.content : "";
// var temp = sdefun.pluginFindByParam();
window.onload = function() {
	//debugger;
	sde = new SDE({
		id : "myEditor",
		title : title, // 自定义title
		footer : footer, // 自定义footer
		// toolbars : toolbars,
		_content : content,
		//control_templates: "",
		mode :  'DESIGN',
		test : ''
	// 自定义Html
	});
	sde.ready(function() {
		// sde对象是异步加载，即必须等待sde.ready加载完成后才能执行。
		//sde.setMode("READONLY"); // mode可选：DESIGN（设计）、EDITOR（编辑）、READONLY（只读）
		//console.log("第三加载"+2);
		//sde.setControls('name','奥巴马');
	});
	//console.log("第二加载"+1);
};
//console.log("第一加载"+0);



function insertBtn(){
    var funName = prompt('输入方法名', '');
    var value = '<button onclick=parent.'+funName+'()>按钮</button>';
    sde.setExecCommand('insertHtml', value);
}
function insertcharts(){
    var value = '<a id="main" style="width:300px; height:200px;border:1px solid #ccc; display: inline-block;"></a>';
    sde.setExecCommand('insertHtml', value);
/*            var ue = UE.getEditor('myEditor');
    var node = new UE.uNode({
        type:'element',
        tagName:'span',
        attrs:{style:'width:300px; height:200px;border:1px solid #ccc; display: inline-block;'}
    });
    console.log(node);
    ue.execCommand('insertHtml', node.toHtml());*/
    console.log(ue.document.getElementById('main'));
}

function testVop() {
	var param = {
		label:'testVop',
		url:window.SDE_CONFIG.HOME_URL_DIALOGS +'button/test.vop' // 这是一个返回dialog所呈现页面的SpringMVC请求。
	}
	sdefun.getDialog(param);
}

function addReport() {
	var param = {};
	var goal = sde.getAllControl(); // 控件指标数据
	var content = sde.html(); // 所有的html
	param.content = content;
	param.template_ver_id = '1';
	//debugger;
	var date1 = new Date().getTime();
	var report=sdefun.addReport(param, goal);
	var date2 = new Date().getTime();
	if (report) {
		$.showTip("保存成功");
	} else {
		$.showTip("保存失败");
	}
	alert("耗时："+(date2-date1));
}
    
    
    </script>
</body>
</html>
 
 
 
```