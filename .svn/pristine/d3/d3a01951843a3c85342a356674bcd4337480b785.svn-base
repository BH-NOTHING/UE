/**
 * 1.在srv里面调用
 *        var service = new Service('editor.reportModel.tp_template_type');
 * 2.在VOP上面调用，调用方法跟普通的srv方式一样，只不过需要在原有的参数上面增加一个参数funName
 *        funName:findById/findAll
 * @Autor : Bryant
 * @Date : 2017-05-19
 **/
var toJs = require('nodejava').toJs;
var spring = require("spring");
var datasource="";                          //数据源
var isDataSeq =  param.isDataSeq || true; //20180122 king 是否手动读取主键序列号.
var thisUserId = com.tt.pwp.framework.security.SecurityUtils.getLoginAccountId();
var thisTime = com.tt.pwp.framework.util.formatter.DateFormatterUtil.long2YYYY_MM_DDHH24miss(new java.util.Date());
var logger = com.tt.pwp.framework.util.log.LogUtil();
logger.info("templateTypeSrv.srv.js---thisUserId:"+thisUserId+"---thisTime:"+thisTime+"---Param:"+JSON.stringify(param));
if (param && param.datasource) {
    var dsMgr = require('pwp-datasource');  //数据源管理对象
    db = dsMgr.db(param.datasource);        //调用db([datasouceId])函数得到指定的数据源对象,dsMgr.db('default')可得到默认数据源
    datasource = param.datasource;
}

var handler = {

    /*
     * 查询出所有模板类型
     */
    findAll: function (param) {
        var result = {};
        var data = db.dao.findAll("editor.editorModel.tp_template_type");
        result.data = toJs.parse(data);
        if (result.data) {
            result.msg = '查询所有模板类型成功';
            result.code = 200;
            return result;
        } else {
            result.msg = '查询所有模板类型失败';
            result.code = 500;
            return result;
        }
    },

    /*
     * 根据ID查询出单个模板类型
     */
    findById: function (param) {
        var result = {};
        var templateType = db.dao.findAll("editor.editorModel.tp_template_type", "id=?", [param.id]);
        result.data = toJs.parse(templateType);
        if (result.data) {
            result.msg = '查询单个模板类型成功';
            result.code = 200;
            return result;
        } else {
            result.msg = '查询单个模板类型失败';
            result.code = 500;
            return result;
        }
    },

    /*
     *新增模板类型
     *param { type_code : '',type_name : ''}
     */
    addTemType: function (param) {
        var result = {};
        param.create_user = com.tt.pwp.framework.security.SecurityUtils.getLoginAccountId();
        param.create_time = com.tt.pwp.framework.util.formatter.DateFormatterUtil.long2YYYY_MM_DDHH24miss(new java.util.Date());
        if (!param.status) {//如果没有传入状态，则设为默认值
            param.status = 0;
        }
        if(isDataSeq ) {
            var templateTypeSeq = this.getTemplateTypeSeq();
            param.id = templateTypeSeq.seqNum;//主键;
        }
        var data = db.dao.insertSelective('editor.editorModel.tp_template_type', param);
        result.data = toJs.parse(data);
        if (result.data) {
            result.msg = '新增模板类型成功';
            result.code = 200;
            return result;
        } else {
            result.msg = '新增模板类型失败';
            result.code = 500;
            return result;
        }
    },

    /*
     *删除模板类型,只是改变status字段的值，并没有真正的删除记录
     *param {id : ''}
     */
    delTemType: function (param) {
        var result = {};
        param.lastupdate_user = com.tt.pwp.framework.security.SecurityUtils.getLoginAccountId();
        param.lastupdate_time = com.tt.pwp.framework.util.formatter.DateFormatterUtil.long2YYYY_MM_DDHH24miss(new java.util.Date());
        param.status = 2;
        var data = db.dao.updateSelective("editor.editorModel.tp_template_type", param);
        result.data = toJs.parse(data);
        if (result.data) {
            result.msg = '删除模板类型成功';
            result.code = 200;
            return result;
        } else {
            result.msg = '删除模板类型失败';
            result.code = 500;
            return result;
        }
    },

    /*
     *多个ID查询多个模板类型
     *param {ids : [1,2,3...]}
     */
    findByMutilId: function (param) {
        var result = {};
        var ids = param.ids;
        var datas = [];
        var length = ids.length;
        for (var i = 0; i < length; i++) {
            var data = db.dao.find("editor.editorModel.tp_template_type", "id=?", [ids[i]]);
            datas.push(data);
        }
        if (datas.length > 0) {
            result.data = toJs.parse(datas);
            result.msg = '查询多个模板类型成功';
            result.code = 200;
            return result;
        } else {
            result.msg = '查询多个模板类型失败';
            result.code = 500;
            return result;
        }
    },

    /*
     * 修改模板类型
     * param {id : '',type_code : '',type_name : '',...}
     */
    updateTemType: function (param) {
        var result = {};
        param.lastupdate_user = com.tt.pwp.framework.security.SecurityUtils.getLoginAccountId();
        param.lastupdate_time = com.tt.pwp.framework.util.formatter.DateFormatterUtil.long2YYYY_MM_DDHH24miss(new java.util.Date());
        result.data = toJs.parse(db.dao.updateSelective("editor.editorModel.tp_template_type", param));
        if (result.data) {
            result.msg = '修改模板类型成功';
            result.code = 200;
            return result;
        } else {
            result.msg = '修改模板类型失败';
            result.code = 500;
            return result;
        }
    },

    /*
     * 修改模板类型状态
     * param {id : '',status : ''}
     */
    updateStatus: function (param) {
        var result = {};
        param.lastupdate_user = com.tt.pwp.framework.security.SecurityUtils.getLoginAccountId();
        param.lastupdate_time = com.tt.pwp.framework.util.formatter.DateFormatterUtil.long2YYYY_MM_DDHH24miss(new java.util.Date());
        result.data = toJs.parse(db.dao.updateSelective("editor.editorModel.tp_template_type", param));
        if (result.data) {
            result.msg = '修改模板类型状态成功';
            result.code = 200;
            return result;
        } else {
            result.msg = '修改模板类型状态失败';
            result.code = 500;
            return result;
        }
    },
    /*
     * 获取TemplateType序列号
     */
    getTemplateTypeSeq: function (params) {
        var seqName1 = "TP_TEMPLATE_TYPE_SEQ";
        return this.getSeqNum({seqName:seqName1});
    },
    /*
     * 获取序列号
     */
    getSeqNum:function(params){
        var par = {
            seqName:params.seqName,
            datasource:datasource,
            funName:"getSeqNum"
        };
        var res = new Service('editor.editorSrvII.seqNumSrv').callService(par);
        return res;
    }
};

return handler[param.funName](param);