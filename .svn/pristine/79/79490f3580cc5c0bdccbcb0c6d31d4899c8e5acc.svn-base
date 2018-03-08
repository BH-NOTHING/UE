/**
 * 分开编写，调用方式：var dao = new Service("editor.reportSrv.loadReportSrv").callService({});
 * 查询报告
 * @Autor : Nick
 * @Date : 2017-03-29
 * param参数{}
 **/
var toJs = require('nodejava').toJs;
var loadReport = {
	
	/**
	 * 查询所有报告
	 */
	loadAllReport : function(param) {
		var result = {};
		var report = db.dao.findAll("editor.templateModel.tp_report");
		result.report = toJs.parse(report);
		result.msg = '查询所有报告成功';
		result.code = 200;
		return result;
	},
	
	/**
	 * 根据ID查询Report包含Content
	 */
	loadReportContentById : function(param) {
		var result = {};
		//console.log(param.id);
		var report = db.dao.findById("editor.templateModel.tp_report", param.id);
		result.report = toJs.parse(report);
		result.msg = '查询报告成功';
		result.code = 200;
		return result;
	},
	
	/**
	 * 该方法除了会查询report表之外，还会查询goal表的检查项
	 */
	loadReportById : function(param) {
		var result = {};
		
		var report = toJs.parse(db.dao.findById("editor.templateModel.tp_report", param.id));
		if(report != null) {
			var report_goal = toJs.parse(db.dao.findAll("editor.templateModel.tp_report_goal", "report_id = ?", [param.id]));
			report.report_goal = report_goal;
			result.report = report;
		}
		
		result.msg = '查询报告成功';
		result.code = 200;
		return result;
	}	
};

return loadReport[param.funName](param);
