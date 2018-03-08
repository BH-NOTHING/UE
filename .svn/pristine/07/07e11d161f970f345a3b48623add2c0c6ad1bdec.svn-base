/**
 * 提交，有两种情况：
 * 	1.已经保存过report的，只需要修改，然后保存goal
 * 	2.未保存的report的，需要保存report并保存goal
 * @Autor : Nick
 * @Date : 2017-03-29
 * param {org_id:'',template_id:'',status:'',content'',report_goal:[{report_code:'',report_value:''},{}]}
 */

var result = {};
//console.log("提交报告--------------");

param.update_user = com.tt.pwp.framework.security.SecurityUtils.getLoginAccountId();
param.update_time = com.tt.pwp.framework.util.formatter.DateFormatterUtil.long2YYYY_MM_DDHH24miss(new java.util.Date());
if(param.source == 0){
	var date = new  java.text.SimpleDateFormat("yyyy-MM-dd").parse(param.apply_check_time);
	param.apply_check_time = com.tt.pwp.framework.util.formatter.DateFormatterUtil.long2YYYY_MM_DDHH24miss(date);
}
param.status = '2';

//修改待检表是否已有报告字段为已有报告
var count = db.dao.updateSelective("editor.templateModel.mp_patient_check", {
	id : param.check_id,
	is_report : param.is_report
});

if(param.id == null || param.id == 0) {
	//未保存过的，需要保存report和goal
	param.create_time = com.tt.pwp.framework.util.formatter.DateFormatterUtil.long2YYYY_MM_DDHH24miss(new java.util.Date());
	var report = db.dao.insertSelective('editor.templateModel.tp_report', param);
	result.data = report;
	//清空template_goal表数据
	db.dao.remove("editor.templateModel.tp_report_goal", 'report_id = ?', [report.id]);
	var goals = param.report_goal;
	if(goals != null) {
		for(var i = 0; i < goals.length; i++) {
			var goal = goals[i];
			goal.report_id = report.id;
			goal.create_time = com.tt.pwp.framework.util.formatter.DateFormatterUtil.long2YYYY_MM_DDHH24miss(new java.util.Date());
			//console.log(goal);
			db.dao.insertSelective('editor.templateModel.tp_report_goal', goal);
		}
	}
} else {
	//已经保存过，提交，param.id必须有值
	db.dao.updateSelective('editor.templateModel.tp_report', param);
	//先清空tp_report_goal表的数据
	db.dao.remove("editor.templateModel.tp_report_goal", 'report_id = ?', [param.id]);
	
	var goals = param.report_goal;
	var goals = param.report_goal;
	//console.log(goals);
	if(goals != null) {
		//console.log("保存报告信息goal！");
		for(var i = 0; i < goals.length; i++) {
			var goal = goals[i];
			goal.report_id = param.id;
			//console.log(goal);
			goal.create_time = com.tt.pwp.framework.util.formatter.DateFormatterUtil.long2YYYY_MM_DDHH24miss(new java.util.Date());
			db.dao.insertSelective('editor.templateModel.tp_report_goal', goal);
		}
	}
}


result.msg = '提交报告成功';
result.code = 200;
return result;