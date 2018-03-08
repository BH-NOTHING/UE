/**
 * 1.在srv里面调用
 *        var service = new Service('editor.reportSrvV2.reportSrv').callService({});
 * 2.在VOP上面调用，调用方法跟普通的srv方式一样，只不过需要在原有的参数上面增加一个参数funName
 *        funName:findById/loadAll
 * @Autor : Bryant
 * @Date : 2017-05-23
 **/
var toJs = require('nodejava').toJs;
var spring = require("spring");
var result = {};
result.data = '';
result.msg = '操作失败';
result.code = 500;
var isDataSeq =  param.isDataSeq || true; //20180122 king 是否手动读取主键序列号.
var userId = com.tt.pwp.framework.security.SecurityUtils.getLoginAccountId();
var nowTime = com.tt.pwp.framework.util.formatter.DateFormatterUtil.long2YYYY_MM_DDHH24miss(new java.util.Date());
var datasource="";                          //数据源
if (param && param.datasource) {
    var dsMgr = require('pwp-datasource');  //数据源管理对象
    db = dsMgr.db(param.datasource);        //调用db([datasouceId])函数得到指定的数据源对象,dsMgr.db('default')可得到默认数据源
    datasource = param.datasource;
}

var handler = {
    /**
     * 测试srv调用别的srv,
     */
    test:function(params){
        params.id = "2";
        params.funName = 'findById';
        //params.datasource='fuDataSource';
        var templateTypeSrv = new Service("editor.editorSrvII.templateTypeSrv");
        var res = templateTypeSrv.callService(params);
        return res;
    },
    /**
     * 查询所有报告
     */
    getAll: function (param) {
        var data = db.dao.findAll("editor.editorModel.tp_template_report");
        if (data) {
            result.data = toJs.parse(data);
            result.msg = '查询所有报告成功';
            result.code = 200;
            return result;
        } else {
            result.msg = '查询所有报告失败';
            result.code = 500;
            return result;
        }

    },

    /**
     * 根据ID查询Report包含Content
     */
    getById: function (param) {
        var data = db.dao.findById("editor.editorModel.tp_template_report", param.id);
        if (data) {
            result.data = toJs.parse(data);
            result.msg = '查询报告成功';
            result.code = 200;
            return result;
        } else {
            result.msg = '查询报告失败';
            result.code = 500;
            return result;
        }

    },

    /**
     * 该方法除了会查询report表之外，还会查询data表的检查项
     */
    getReportDataById: function (param) {
        var report = toJs.parse(db.dao.findById("editor.editorModel.tp_template_report", param.id));
        if (report != null) {
            var report_goal = toJs.parse(db.dao.findAll("editor.editorModel.tp_template_reportdata", "report_id = ?", [param.id]));
            report.report_goal = report_goal;
            result.data = report;
            if (report.report_goal) {
                result.msg = '查询报告成功';
                result.code = 200;
                return result;
            } else {
                result.msg = '查询报告失败';
                result.code = 500;
                return result;
            }
        } else {
            result.msg = '查询报告失败';
            result.code = 500;
            return result;
        }
    },


    /**
     * 删除报告 逻辑删除
     * param参数{id:''}
     **/
    delReport: function (param) {
        var report = db.dao.findById("editor.editorModel.tp_template_report", param.id);
        if (report.status == '1') {
            result.msg = '报告已启用，不能再删除';
            result.code = 500;
            return result;
        }
        if (report.status == '0' || report.status == '2') {
            param.status = '3';
            var data = db.dao.updateSelective('editor.editorModel.tp_template_report', param);
            if (data) {
                result.msg = '删除报告成功';
                result.code = 200;
                return result;
            } else {
                result.msg = '删除报告失败';
                result.code = 500;
                return result;
            }
        }
    },

    /**
     * 修改报告,同时删除报告数据表关联的数据并重新插入
     * @Autor : Bryant
     * param参数 {id : '',template_ver_id : '', status : '',content : '',...,reportDatas:[{goal_id : '',goal_name : '',goal_code : '',goal_value : '',goal_value : '',...},{}]}
     * 如果报告已经是提交状态，则新增一条记录，
     * @Date : 2017-05-23
     **/
    updateReportRemoveData: function (param) {
        param.lastupdate_user = userId;
        param.lastupdate_time = nowTime;
        var report = db.dao.findById("editor.editorModel.tp_template_report", param.id);
        if (!(typeof param.syn_version === 'number' && param.syn_version % 1 === 0)) {// param.syn_version%1 === 0 ---用于除整
            result.msg = '同步锁参数错误！';
            result.code = 500;
            return result;
        }
        if (report.syn_version != param.syn_version) { //乐观锁，新增时syn版本号为0;后面没修改一次，版本号就在原基础上 +1
            result.msg = '报告已被修改，不能再修改';
            result.code = 500;
            return result;
        }
        //status(状态)：0-草稿，1-启用，2-禁用，3-删除
        if (report.status == '3') {
            result.msg = '报告已删除，不能再修改';
            result.code = 500;
            return result;
        }
        if (report.status == '1') {
            report = toJs.parse(report);
            report.reportDatas = param.reportDatas;
            this.addReport(report);
            result.msg = '报告已新增';
            result.code = 200;
            return result;
        }
        if (report.status == '0' || report.status == '2') {
            param.syn_version = report.syn_version + 1;
            var alter = db.dao.updateSelective('editor.editorModel.tp_template_report', param);
            if (alter) {
                var para = [];
                var reportDatas = param.reportDatas;
                for (var i = 0; i < reportDatas.length; i++) {
                    var reportData = reportDatas[i];
                    reportData.report_id = report.id;
                    if(isDataSeq ){
                        var tp_template_reportdata_seq = this.getReportDataSeq();
                        reportData.id = tp_template_reportdata_seq.seqNum;//主键;
                    }
                    reportData.lastupdate_user = userId;
                    reportData.lastupdate_time = nowTime;
                    reportData.updateFlag = "Appended";
                    para.push(reportData);
                }
                db.dao.remove('editor.editorModel.tp_template_reportdata', "report_id = ?", [report.id]);
                var res = db.dao.batchInsert("editor.editorModel.tp_template_reportdata", para);//批量插入
                var data = {};
                data.syn_version = param.syn_version;
                result.data = data;
                result.msg = '修改报告成功';
                result.code = 200;
            } else {
                result.msg = '修改报告失败';
                result.code = 500;
            }
            return result;
        }
    },

    /**
     * 修改报告,同时修改报告数据表关联的数据,如果有必要删除原来的data数据时，请使用updateReportRemoveData
     * @Autor : Bryant
     * param参数 {id : '',template_ver_id : '', status : '',content : '',...,reportDatas:[{goal_id : '',goal_name : '',goal_code : '',goal_value : '',goal_value : '',...},{}]}
     * 如果报告已经是提交状态，则新增一条记录，
     * @Date : 2017-05-23
     **/
    updateReport: function (param) {
        param.lastupdate_user = userId;
        param.lastupdate_time = nowTime;
        var report = db.dao.findById("editor.editorModel.tp_template_report", param.id);
        if (!(typeof param.syn_version === 'number' && param.syn_version % 1 === 0)) {// param.syn_version%1 === 0 ---用于除整
            result.msg = '同步锁参数错误！';
            result.code = 500;
            return result;
        }
        if (report.syn_version != param.syn_version) { //乐观锁，新增时syn版本号为0;后面没修改一次，版本号就在原基础上 +1
            result.msg = '报告已被修改，不能再修改';
            result.code = 500;
            return result;
        }
        if (report.status == '3') {
            result.msg = '报告已删除，不能再修改';
            result.code = 500;
            return result;
        }
        if (report.status == '1') {
            report = toJs.parse(report);
            report.reportDatas = param.reportDatas;
            this.addReport(report);
            result.msg = '报告已新增';
            result.code = 200;
            return result;
        }
        if (report.status == '0' || report.status == '2') {
            var syn_version = report.syn_version + 1;
            param.syn_version = syn_version;
            var data = db.dao.updateSelective('editor.editorModel.tp_template_report', param);

            result.data = data;
            if (data) {
                var para = [];
                var reportDatas = param.reportDatas;
                var sqlself = "UPDATE TP_TEMPLATE_REPORTDATA t SET t.goal_name = ?, t.goal_value = ?,t.goal_type = ? ,t.lastupdate_time = to_date(?,'yyyy-mm-dd hh24:mi:ss') ,t.lastupdate_user = ? ,t.plugin_ver_id = ?  WHERE t.report_id = ?  and t.goal_code = ?";
                //var date1 = new Date().getTime();
                for (var i = 0; i < reportDatas.length; i++) {
                    var reportdata = reportDatas[i];
                    reportdata.report_id = report.id;
                    reportdata.lastupdate_user = userId;
                    reportdata.lastupdate_time = nowTime;
                    reportdata.updateFlag = "Appended";
                    para.push(reportdata);

                    if (!param.isRemoveData) {
                        var goal_name = reportdata.goal_name || "";
                        var goal_value = "";
                        if ((typeof reportdata.goal_value) == 'string') {
                            goal_value = reportdata.goal_value;
                        } else if (reportdata.goal_value) {
                            goal_value = JSON.stringify(reportdata.goal_value);
                        }
                        var lastupdate_user = userId;
                        var lastupdate_time = nowTime;
                        var goal_type = reportdata.goal_type || "";
                        var plugin_ver_id = reportdata.plugin_ver_id || "";
                        var report_id = reportdata.report_id || "";
                        var goal_code = reportdata.goal_code || "";

                        var reportUArr = [goal_name, goal_value, goal_type, lastupdate_time, lastupdate_user, plugin_ver_id, report_id, goal_code];
                        //console.log("更新reportData："+reportUArr);
                        try {
                            var reportUData = db.update(sqlself, reportUArr);
                        } catch (e) {
                            //console.log("更新reportData异常："+e);
                        }
                    }

                }
                //console.log("更新reportData完毕：");
                if (param.isRemoveData) {
                    var dataRemove = db.dao.remove("editor.editorModel.tp_template_reportdata", 'report_id = ?', [param.id]);
                    var res = db.dao.batchInsert("editor.editorModel.tp_template_reportdata", para);//批量插入
                }
                //var date2 = new Date().getTime();
                // console.log("总耗时：" + (date2 - date1));

                result.syn_version = syn_version;
                result.msg = '修改报告成功';
                result.code = 200;
            } else {
                result.msg = '修改报告失败';
                result.code = 500;
            }
        }
        return result;
    },


    /*
     *新增报告
     *@Autor : Bryant
     *param参数 {template_ver_id : '', status : '',content : '',...,reportDatas:[{goal_id : '',goal_name : '',goal_code : '',goal_value : '',goal_value : '',...},{}]}
     */
    addReport: function (param) {
        param.create_user = userId;
        param.create_time = nowTime;
        /*param.lastupdate_user = userId;
         param.lastupdate_time = nowTime;*/
        if (param.status == null) {//默认状态为草稿
            param.status = '0';
        }
        param.syn_version = 0;
        if(isDataSeq ) {
            var reportseq = this.getReportSeq();
            param.id = reportseq.seqNum;//主键;
        }
        var result = {};
        var report = db.dao.insertSelective('editor.editorModel.tp_template_report', param);
        result.data = report;

        var report_id = report.id;
        var reportDatas = param.reportDatas;
        var para = [];
        for (var i = 0; i < reportDatas.length; i++) {
            var reportData = reportDatas[i];
            //if (reportData.plugin_ver_id != null) { // 在保存报告的时候，报告指标没有template_ver_id也是可以保存的
            reportData.create_user = userId;
            reportData.create_time = nowTime;
            reportData.report_id = report_id;
            if(isDataSeq ) {
                var tp_template_reportdata_seq = this.getReportDataSeq();
                reportData.id = tp_template_reportdata_seq.seqNum;//主键;
            }
            reportData.updateFlag = "Appended";
            para.push(reportData);
            // db.dao.insertSelective("editor.editorModel.tp_template_reportdata",reportData);
            //}
        }
        var res = db.dao.batchInsert("editor.editorModel.tp_template_reportdata", para);//批量插入
        result.reportdata = res;
        result.msg = "新增报告成功";
        result.code = 200;
        return result;
    },

    /*
     *提交报告，如果没有传id，则新增一条记录；如果传了id，则修改这条记录
     *param参数 {utype : '',id : '',template_ver_id : '', status : '',content : '',...,reportDatas:[{goal_id : '',goal_name : '',goal_code : '',goal_value : '',goal_value : '',...},{}]}
     *id没有则新增记录，如果是修改则要传入utype,传入规则查看updateReport方法
     */
    submitReport: function (param) {
        //console.log("提交报告--------------");

        param.lastupdate_user = userId;
        param.lastupdate_time = nowTime;
        param.status = '2';

        if (param.id == null || param.id == 0) {
            //未保存过的，需要保存report和goal
            param.create_user = userId;
            param.create_time = nowTime;
            if(isDataSeq ){
                var reportSeq = this.getReportSeq();
                param.id = reportSeq.seqNum;//主键;
            }
            var report = db.dao.insertSelective('editor.editorModel.tp_template_report', param);
            result.data = report;
            //清空template_goal表数据
            db.dao.remove("editor.editorModel.tp_template_reportdata", 'report_id = ?', [report.id]);
            var goals = param.report_goal;
            if (goals != null) {
                for (var i = 0; i < goals.length; i++) {
                    var goal = goals[i];
                    goal.report_id = report.id;
                    goal.create_user = userId;
                    goal.create_time = nowTime;
                    if(isDataSeq ) {
                        var reportDataSeq = this.getReportDataSeq();
                        goal.id = reportDataSeq.seqNum;//主键;
                    }
                    db.dao.insertSelective('editor.editorModel.tp_template_reportdata', goal);
                }
            }
        } else {
            db.dao.updateSelective('editor.editorModel.tp_template_report', param);
            db.dao.remove("editor.editorModel.tp_template_reportdata", 'report_id = ?', [param.id]);

            var goals = param.report_goal;
            //console.log(goals);
            if (goals != null) {
                for (var i = 0; i < goals.length; i++) {
                    var goal = goals[i];
                    goal.report_id = param.id;
                    //console.log(goal);
                    goal.create_time = userId;
                    goal.create_user = nowTime;
                    if(isDataSeq ) {
                        var reportDataSeq = this.getReportDataSeq();
                        goal.id = reportDataSeq.seqNum;//主键;
                    }
                    db.dao.insertSelective('editor.editorModel.tp_template_reportdata', goal);
                }
            }
        }
        result.msg = '提交报告成功';
        result.code = 200;
        return result;
    },

    saveReport: function (param) {

        param.lastupdate_user = userId;
        param.lastupdate_time = nowTime;
        param.status = '1';	//默认是1-保存

        if (param.id == null || param.id == 0) {
            param.create_user = userId;
            param.create_time = nowTime;
            if(isDataSeq ) {
                var reportSeq = this.getReportSeq();
                param.id = reportSeq.seqNum;//主键;
            }
            result.data = db.dao.insertSelective('editor.editorModel.tp_template_report', param);
        } else {
            result.data = db.dao.updateSelective('editor.editorModel.tp_template_report', param);
        }
        result.msg = '保存成功';
        result.code = 200;
        return result;
    },


    /*
     * 新增模板报告
     *
     */
    addReportData: function (params) {
        var paramArr = [];
        var reportDataSeq = "";
        for (var i = 0,pl=params.length; i < pl; i++) {
            if(isDataSeq ) {
                reportDataSeq = this.getReportDataSeq();
                params[i].id = reportDataSeq.seqNum;//主键;
            }
            paramArr.push(params[i]);
        }
        result.data = toJs.parse(db.dao.insertSelective("editor.editorModel.tp_template_reportdata", paramArr));
        result.msg = "保存模板报告成功";
        rssult.code = 200;
        return result;
    },

    /*
     * 删除模板报告
     */
    delReportData: function (param) {
        db.dao.removeById("editor.editorModel.tp_template_reportdata", param.id);
        result.msg = "删除模板报告成功";
        result.code = 200;
        return result;
    },

    /*
     * 根据id,report_id,或者plugin_ver_id来查询模板报告
     */
    find: function (param) {
        if (param.id) {
            result.data = toJs.parse(db.dao.findById("editor.editorModel.tp_template_reportdata", [param.id]));
            result.msg = "查询数据成功";
            result.code = 200;
            return result;
        }
        if (param.report_id) {
            result.data = toJs.parse(db.dao.findAll("editor.editorModel.tp_template_reportdata", "report_id=?", [param.report_id]));
            result.msg = "查询数据成功";
            result.code = 200;
            return result;

        }
        if (param.plugin_ver_id) {
            result.data = toJs.parse(db.dao.findAll("editor.editorModel.tp_template_reportdata", "plugin_ver_id=?", [param.plugin_ver_id]));
            result.msg = "查询数据成功";
            result.code = 200;
            return result;

        }
    },


    /*
     * 查询所有模板报告数据
     */
    findAll: function (param) {
        result.data = toJs.parse(db.dao.findAll("editor.editorModel.tp_template_reportdata"));
        result.msg = "查询所有数据成功";
        result.code = 200;
        return result;
    },


    /*
     * 修改模板报告数据
     */
    updateReportData: function (param) {
        result.data = db.dao.updateSelective("editor.editorModel.tp_template_reportdata", param);
        result.msg = "修改数据成功";
        result.code = 200;
        return result;
    },
    /*
     * 修改模板报告状态
     */
    updateReportState: function (param) {
        result.data = db.dao.updateSelective('editor.editorModel.tp_template_report', param);
        result.msg = "修改状态成功";
        result.code = 200;
        return result;
    },

    getold_report: function (param) {
        result.data = db.dao.findById("editor.editorModel.tp_template_report", param.report_id);
        result.msg = "修改状态成功";
        result.code = 200;
        return result;
    },
    getdata_report: function (param) {
        result.data = toJs.parse(db.dao.findAll("editor.editorModel.tp_template_reportdata", "report_id=?", [param.report_id]));
        result.msg = "修改状态成功";
        result.code = 200;
        return result;
    },

    /*
     * 根据模板版本id获取最新的报告
     */
    getNewestReportByTempVId: function (param) {
        var sql = "select * from tp_template_report t1 where t1.id = (select max(t2.id) from tp_template_report t2 where t2.template_ver_id = ?)";
        var report = toJs.parse(db.queryForList(sql, [param.template_ver_id]));
        result.data = report;
        result.msg = "查询成功";
        result.code = 200;
        return result;
    },
    /*
     * 获取report序列号
     */
    getReportSeq: function (params) {
        var seqName1 = "TP_TEMPLATE_REPORT_SEQ";
        return this.getSeqNum({seqName:seqName1});
    },
    /*
     * 获取reportData序列号
     */
    getReportDataSeq: function (params) {
        var seqName2 = "TP_TEMPLATE_REPORTDATA_SEQ";
        return this.getSeqNum({seqName:seqName2});
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
