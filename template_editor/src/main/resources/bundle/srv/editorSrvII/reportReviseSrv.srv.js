var toJs = require('nodejava').toJs;
var spring = require("spring");
var result = {};
result.data = '';
result.msg = '操作失败';
result.code = 500;
var isDataSeq = param.isDataSeq || false; //20180122 king 是否手动读取主键序列号.
var thisUserId = com.tt.pwp.framework.security.SecurityUtils.getLoginAccountId();
var thisTime = com.tt.pwp.framework.util.formatter.DateFormatterUtil.long2YYYY_MM_DDHH24miss(new java.util.Date());
var logger = com.tt.pwp.framework.util.log.LogUtil();
logger.info("reportReviseSrv.srv.js---thisUserId:"+thisUserId+"---thisTime:"+thisTime+"---Param:"+JSON.stringify(param));
var datasource = "";                          //数据源
if (param && param.datasource) {
    var dsMgr = require('pwp-datasource');  //数据源管理对象
    db = dsMgr.db(param.datasource);        //调用db([datasouceId])函数得到指定的数据源对象,dsMgr.db('default')可得到默认数据源
    datasource = param.datasource;
}

var handler = {
    /**
     * 测试改srv 中调用别的srv方法；
     */
    test: function (params) {

        return result;
    },
    /**
     * 根据ID查询某一个。当没有版本号时，返回最新版本号的ID
     */
    findById: function (params) {

        var tp_report_revise;
        if (param.id) {
            var sql = "select * from tp_report_revise where id = (select max(id) from tp_report_revise where id = ?)";
            tp_report_revise = toJs.parse(db.queryForList(sql, [param.id]));
        }else{

        }
        if (tp_report_revise) {
            result.data = tp_report_revise;
            result.msg = '查询成功';
            result.code = 200;
        }
        return result;
    },
    /**
     * 根据ID查询某一个。当没有版本号时，返回最新版本号的ID
     */
    /**
     * 新增模板
     * param参数 {template_id : '',type_id : '',name : '',syn_version : '',...}
     */
    addRevise: function (param) {

        var revise ;
        if(param.reviseDatas && param.reviseDatas.length>0   ){
            for(var i=0,rdl=param.reviseDatas.length;i<rdl;i++){
                var obj=param.reviseDatas[i];
                if(obj.report_id==undefined || obj.user_id == undefined || obj.content_ctime == undefined|| obj.content_type ==undefined ){
                    //报告id，创建人，创建时间必填
                    continue;
                }
                obj.create_time = thisTime;
                logger.info("obj2:"+JSON.stringify(obj));
                if(isDataSeq ) {
                    var reviseSeq = this.getReviseSeq();
                    obj.id = reviseSeq.seqNum;//主键
                }
                logger.info("主键:"+JSON.stringify(obj));
                revise = db.dao.insertSelective("editor.editorModel.tp_report_revise", obj);
            }
        }
        if(!revise){
            return result;
        }
        result.data = toJs.parse(revise);
        result.msg = '新增成功';
        result.code = 200;
        return result;
    },

    /*
     * 获取Template序列号
     */
    getReviseSeq: function (params) {
        var seqName1 = "TP_REPORT_REVISE";
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


}
return handler[param.funName](param);
