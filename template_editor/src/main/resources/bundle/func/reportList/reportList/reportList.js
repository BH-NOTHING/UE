var sdefun = new sdeFun();
var templateList;
var isclicktxtRuleList = !1;
var $G = function(id) {
    return document.getElementById(id)
};


function selectCode(template_ver_id) {
    for (var i = 0,l = templateList.length; i < l; i++) {
        var data = templateList[i];
        if (data.template_ver_id.toString() === template_ver_id) {
            var content = data.content;
            var $ss = $(content);
            var codes = $ss.find("span[id]");
            var cl = codes.length;
            console.log("selectCode:" + JSON.stringify(codes) + "codes.length:" + cl);
            var html = [];
            html.push('<option value=""> --请选择控件-- </option>');
            for (var ii = 0; ii < cl; ii++) {
                var sdeModel = $(codes[ii]).attr('sde-model');
                var json = JSON.parse(sdeModel);
                var jsonCODE = json.ID || json.CODE;
                var jsonNAME = jsonCODE + "|" + json.NAME || "";
                html.push('<option value="' + jsonCODE + '" >' + jsonNAME + '</option>');
            }

            $G("selectCode").innerHTML = html.join('');
        }
    }
}



function templateListFun() {
    templateList = sdefun.templateFindAll();
    var html = [];
    html.push('<option value=""> --请选择模板-- </option>');
    for (var i = 0,l = templateList.length; i < l; i++) {
        var data = templateList[i];
        html.push('<option value="' + data.template_ver_id + '" >' + i + "," + data.name + '</option>');
    }
    $G('selectTemplate').innerHTML = html.join('');
}
function onSelectText() {
    $('.selectHead').tabs('enableTab',0);
    $('.txtCOLOR').css("display", "none");//debugger;break;
    $('.txtInputValue').css("display", "none");//隐藏
}
function onSelectSelect() {
    $('.selectHead').tabs('enableTab',1);
    $('.txtCOLOR').css("display","none" );
    $('.txtInputValue').css("display","");

}
function onSelectDate() {
    $('.selectHead').tabs('enableTab',2);
    $('.txtCOLOR').css("display","" );
    $('.txtInputValue').css("display","none");
}
function onSelectCheckBox() {
    $('.selectHead').tabs('enableTab',3);
    $('.txtCOLOR').css("display","none" );
    $('.txtInputValue').css("display","none");
}
function onSelectQrcode() {
    $('.selectHead').tabs('enableTab',4);
    $('.txtCOLOR').css("display","none" );
    $('.txtInputValue').css("display","none");
}
function onSelectBarcode() {
    $('.selectHead').tabs('enableTab',5);
    $('.txtCOLOR').css("display","none" );
    $('.txtInputValue').css("display","none");
}
$(function () {
    packageFund.packageBind();
    packageFund.packageFunc();
    packageFund.initGrid();
    $('#pubAdjForm').show();

    /* 唯一性检验 */
    $("input#txtCODE").blur(function() {
        //编辑则不需要校验
        if (isEditor) {
            return;
        }
        var val = $(this).val();
        if (temp || !val) {
            return;
        }
        var plugin = sdefun.pluginFindByCode(val);
        if (plugin.length > 0) {
            $("#txtCODE").css("border", "#ff0000 1px solid");
            $("#txtMsg").text("该控件编码已经存在");
        } else {
            $("#txtCODE").removeAttr("style");
            $("#txtMsg").text("*");
        }
    });
    $('#txtVERIFYTYPE').change(function() {
        if ($('#txtVERIFYTYPE option:selected').val() == "textarea") {
            $("#textarea-width").css("display", "block");
            $(".text-col").text("行");
            $(".text-row").text("列");
        } else {
            $(".text-col").text("px");
            $("#textarea-width").css("display", "none");
        }
    });
    $("input#ISDBLCLICK").on('change',
        function() {
            if ($(this).is(':checked')) {
                $('.isdblclick').css("display", "");
            } else {
                $('.isdblclick').css("display", "none");
            }
        });
    $("select#txtRuleList").change(function() {
        ruleId = $(this).val();
        ruleCode = $(this).find("option:selected").attr('rule_code');
        if (!ruleId) {
            $G('selectCode').innerHTML = ['<option value=""> --请选择控件-- </option>'].join('');
            return;
        }
        console.log("ruleId:" + ruleId + ",ruleCode:" + ruleCode);
    });
    $("select#selectTemplate").on('change',function() {
        var template_ver_id = $(this).val();
        if (!template_ver_id) return;
        selectCode(template_ver_id);
    });
    $("select#txtRuleList").on('click',function() {
        if (!isclicktxtRuleList) templateListFun();
        isclicktxtRuleList = !0;
    });

    $('#addPackageBtn').click(function(){
        addDialog.open();
        onSelectText();
        $('#selectHead').tabs('select',0);
        $('.selectHead').tabs('enableTab',0);
        $('.selectHead').tabs('enableTab',1);
        $('.selectHead').tabs('enableTab',2);
        $('.selectHead').tabs('enableTab',3);
        $('.selectHead').tabs('enableTab',4);
        $('.selectHead').tabs('enableTab',5);
        pubAdjForm.setValues({isEditor:0});
        packageFund.getRuleList("txtRuleList", {param: {}});
        $('#txtCODE').attr('readonly',false);
    });
    $('#openQueryConditionBtn').click(function(){
        $('#budgetCondition').toggle();
    });
    $('#searchBudgetBtn').click(function(){
        searchUnitForm.submit();
    });
    $('#resetBudgetBtn').click(function() {
        searchUnitForm.reset();
    });
    $('#deletePackageBtn').click(function(){
        var rows = packageGrid.getSelections();
        if(rows.length>1){
            $.messager.alert("提示","一次性只能删除单行记录","error");
        }else if(rows.length==1){
            $.messager.confirm('确认','您确认想要删除此条记录吗？',function(y){
                if (y){
                    var ver_id = rows[0].ver_id;
                    sdefun.pluginDeleteByVId(ver_id);
                    $.showTip("删除成功！");
                    window.packageGrid.reload();
                }else{
                    $.showTip("取消删除！");
                }

            });
        }
    });
});
var packageFund = (function(){
    return {
        getRuleList:function (selectId, param) {
            var datas = sdefun.getRuleList(param);
            var html = [];
            html.push('<option value=""> --请选择规则-- </option>');
            if (datas && datas.data != undefined && datas.data != null) {
                var data = datas.data.data;
                for (var i = 0; i < data.length; i++) {
                    html.push('<option value="' + data[i].rule_code + '" rule_code="' + data[i].rule_id + '"');
                    if (data[i].SELECTED != undefined && data[i].SELECTED == 1) {
                        html.push(' selected="selected" ');
                    }
                    html.push('>' + data[i].rule_name + '</option>');
                }
            }
            $G(selectId).innerHTML = html.join('');
            return html;
        },
        getJson : function() {
            if ($G('txtCODE').value == '') {
                alert('请输入控件编码');
                return false;
            }
            if ($G('txtNAME').value == '') {
                alert('请输入控件名称');
                return false;
            }
            var json = {
                ID: $G('txtCODE').value,
                CODE: $G('txtCODE').value,
                TYPE: 'text',
                NAME: $G('txtNAME').value,
                TAG: $G('txtTAG').value,
                DESCNAME: $G('txtDESCNAME').value,
                VERIFYTYPE: $G('txtVERIFYTYPE').value,
                VALUE: $G('txtVALUE').value,
                COLOR: $G('txtCOLOR').value,
                DBLCLICKFUNCTION: $G('txtDBLCLICKFUNCTION').value
            };
            if ($G('txtREQUIRED').checked) {
                json.REQUIRED = 1;
            }
            if ($G('txtISDISPLAY').checked) {
                json.ISDISPLAY = 1;
            }
            if ($G('ISBRACKETS').checked) {
                json.ISBRACKETS = 1;
            }
            if ($G('txtREADONLY').checked) {
                json.READONLY = 1;
            }

            json.plugin_ex_json =[];
            if(json.VERIFYTYPE){
                json.plugin_ex_json.push({"field_code":"verifytype","field_name":"数据类型","field_value":json.VERIFYTYPE}) ;
            }
            if(json.ISBRACKETS){
                json.plugin_ex_json.push({"field_code":"isbrackets","field_name":"隐藏括号","field_value":json.ISBRACKETS}) ;
            }
            if (json.DBLCLICKFUNCTION) {
                json.plugin_ex_json.push({"field_code": "dblclickfunction","field_name": "双击事件", "field_value": json.DBLCLICKFUNCTION});
            }
            return json;
        },
        updatePlugin : function() {
            var json = this.getJson();
            json.ID = $G('plugin_id').value;
            json.VER_ID = $G('plugin_ver_id').value;
            json.STATUS = $G('plugin_status').value;
            json.TEMPLATE_TYPE_ID = $G('plugin_template_type_id').value;
            var plugin = sdefun.pluginFindByCode(json.CODE);//根据code 去查询控件库是否已经存在
            if(plugin&&plugin.length>0){
                plugin = sdefun.updatePlugin(json);
            }else{
                $.messager.alert("提示","该控件编码：' + plugin.code + ' 模板库不存在！","error");
            }
            return plugin;
        },
        addPlugin : function() {
            var json = this.getJson();
            var plugin = sdefun.pluginFindByCode(json.CODE);//根据code 去查询控件库是否已经存在
            if(plugin&&plugin.length>0){
                $.messager.alert("提示","该控件编码：' + plugin.code + ' 模板库已经存在！","error");
            }else if(plugin){
                plugin = sdefun.addPlugin(json);
            }
            return plugin;
        },
        packageFunc: function () {
            window.searchUnitForm=$("#searchUnitForm").getWidget();
            window.editBudgetUnit=$('#editBudgetUnit').getWidget();

            window.addDialog=$('#addDialog').getWidget();
            window.packageGrid = $('#packageGrid').widgets({
                xtype: "datagrid",
                idField: 'id',
                pagination: true,
                fitColumns: false,
                /*singleSelect:true,*/
                border: false,
                nowrap: false,
                pageList: [10, 20,30, 50, 100],
                pageSize: '30',
                rownumbers: true,
                onDblClickRow:function(index, row){
                    addDialog.open();
                    packageFund.getRuleList("txtRuleList", {param: {}});
                    row.isEditor= '1';
                    pubAdjForm.setValues(row);
                    if (row.required == 1) {
                        $G('txtREQUIRED').checked = true;
                    }
                    $G('editorJson').value = JSON.stringify(row);
                    if (row.readonly == 1) {
                        $G('txtREADONLY').checked = true;
                    }
                    if (row.isdisplay == 1) {
                        $G('txtISDISPLAY').checked = true;
                    }
                    if (row.isbrackets == 1) {
                        $G('ISBRACKETS').checked = true;
                    }
                    $('#txtCODE').attr('readonly',true);
                    $('.selectHead').tabs('disableTab',0);
                    $('.selectHead').tabs('disableTab',1);
                    $('.selectHead').tabs('disableTab',2);
                    $('.selectHead').tabs('disableTab',3);
                    $('.selectHead').tabs('disableTab',4);
                    $('.selectHead').tabs('disableTab',5);
                    switch(row.type){
                        case "text":{
                            $('#selectHead').tabs('select',0);
                            onSelectText();
                            break;
                        }
                        case "select":{
                            $('#selectHead').tabs('select',1);
                            onSelectSelect();
                            break;
                        }
                        case "date":{
                            $('#selectHead').tabs('select',2);
                            onSelectDate();
                            break;
                        }
                        case "checkbox":{
                            $('#selectHead').tabs('select',3);
                            onSelectCheckBox();
                            break;
                        }
                        case "qrcode":{
                            $('#selectHead').tabs('select',4);
                            onSelectQrcode();
                            break;
                        }
                        case "barcode":{
                            $('#selectHead').tabs('select',5);
                            onSelectBarcode();
                            break;
                        }
                    }
                },
                frozenColumns: [[
                    {field: 'check', checkbox: true, unexport: true}
                ]],
                columns: [[
                    {field:'id',title:'控件ID',sortable:true,width:50,align:'center'},
                    {field:'ver_id',title:'版本ID',sortable:true,width:50,align:'center'},
                    {field:'code',title:'控件编码',width:100,align:'center'},
                    {field:'name',title:'控件名称',width:100,align:'center'},
                    {field:'descname',title:'描述',width:100,align:'center'},
                    {field:'value',title:'默认值',width:100,align:'center'},
                    {field:'type',title:'类型',width:70,align:'center'},
                    {field:'verifytype',title:'数据类型',width:70,align:'center'},
                    {field:'status',title:'状态',width:50,align:'center'},
                    {field:'isdisplay',title:'显示',width:50,align:'center'},
                    {field:'required',title:'必填',width:50,align:'center'},
                    {field:'readonly',title:'只读',width:50,align:'center'},
                    /* {field:'bindingdata',title:'数据扩展',width:100,align:'center'},*/
                    {field:'tag',title:'标签',width:100,align:'center'},
                    {field:'rule',title:'取数规则',width:100,align:'center'},
                    {field:'text_color',title:'控件颜色',width:100,align:'center'},
                    {field:'template_type_id',title:'模板类型ID',width:100,align:'center'},
                    {field:'create_time',title:'创建时间',sortable:true,width:100,align:'center'},
                    {field:'lastupdate_time',title:'最后修改时间',width:100,align:'center'},
                    {field:'xx',title:'操作',width:74,align:'center'
                        /*formatter:function(value,row,index){
                         return "<button value='btn' type='button'>编辑</button>"+"<button value='btn' type='button'>删除</button>"+"<button value='btn' type='button'>查看</button>"

                         }*/
                    }

                ]]
            });


        },
        initGrid:function(){
            window.pubAdjForm = $("#pubAdjForm").widgets({
                xtype:'pwpForm'
            });
        },
        //主表业务数据
        packageBind: function () {
            window.packBind = $W.databind.arrayDatabind({
                id: 'testdemo',
                name: '主表业务数据',
                autoload: true,
                pageSize: 30,
                idField: 'id',
                binds: ['#packageGrid','#searchUnitForm'],
                beforeLoad:function (param) {

                },
                afterLoad:function(data){
                }
            });
        }
        /*  //初始化 状态
         initServiceStatus:function(){
         $('#biz_status').widgets({
         xtype:"dictList",
         value:"",
         dictName:"serviceStatus",
         emptyText : '请选择',
         onSelect:function(){
         },
         loadFilter:function(data){
         if(data != ""){
         return [{id:"",text:"全部"}].concat(data);
         }
         return data;
         }
         });
         }*/
    }
})();