/**
 * 1.在srv里面调用
 * 		var service = new Service('editor.editorSrv.template');
 * 2.在VOP上面调用，调用方法跟普通的srv方式一样，只不过需要在原有的参数上面增加一个参数funName
 * 		funName:findById/loadAll
 * @Autor : King
 * @Date : 2017-04-29
 **/
var toJs = require('nodejava').toJs;
var handler = {

		/**
		 * 查询所有报告
		 */
		getAll : function(param) {
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
		getById : function(param) {
			var result = {};
			////console.log(param.id);
			var report = db.dao.findById("editor.templateModel.tp_report", param.id);
			result.report = toJs.parse(report);
			result.msg = '查询报告成功';
			result.code = 200;
			return result;
		},
		
		/**
		 * 该方法除了会查询report表之外，还会查询goal表的检查项
		 */
		getReportGoalById : function(param) {
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
		},
		
		/**
		 * 保存报告，非提交，这个时候只会保存tp_report表的信息，不会保存goal
		 * @Autor : King
		 * @Date : 2017-05-10
		 * param {org_id:'',template_id:'',status:'',content''}
		 */
		save: function (param){
			var result = {};
			param.update_user = com.tt.pwp.framework.security.SecurityUtils.getLoginAccountId();
			param.update_time = com.tt.pwp.framework.util.formatter.DateFormatterUtil.long2YYYY_MM_DDHH24miss(new java.util.Date());
			if(param.source == 0){
				var date = new  java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(param.apply_check_time);
				param.apply_check_time = com.tt.pwp.framework.util.formatter.DateFormatterUtil.long2YYYY_MM_DDHH24miss(date);
			}
			param.status = '1';	//默认是1-保存
			if(param.id == null || param.id == 0) {
				param.create_time = com.tt.pwp.framework.util.formatter.DateFormatterUtil.long2YYYY_MM_DDHH24miss(new java.util.Date());
				result.data = db.dao.insertSelective('editor.templateModel.tp_report', param);
			} else {
				param.create_time = com.tt.pwp.framework.util.formatter.DateFormatterUtil.long2YYYY_MM_DDHH24miss(new java.util.Date());
				result.data = db.dao.updateSelective('editor.templateModel.tp_report', param);
			}
			result.msg = '保存成功';
			result.code = 200;
			return result;
		},
		
		/**
		 * 提交，有两种情况：
		 * 	1.已经保存过report的，只需要修改，然后保存goal
		 * 	2.未保存的report的，需要保存report并保存goal
		 * @Autor : King
		 * @Date : 2017-05-10
		 * param {org_id:'',template_id:'',status:'',content'',report_goal:[{report_code:'',report_value:''},{}]}
		 */
		submit : function (param){
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
			
		},
		/**
		 * 删除报告
		 * @Autor : King
		 * @Date : 2017-05-10
		 * param参数{id:'',delType:0}
		 * 	0-表示逻辑删除，默认，不传就是逻辑删除
		 * 	1-表示物理删除
		 **/
		del : function (param){
			var result = {};
			if(param.delType == null || param.delType == 0) {
				//逻辑删除
				param.status = '0';
				db.dao.updateSelective('editor.templateModel.tp_report', param);
			} else if(param.delType == '1') {
				//物理删除
				db.dao.remove('editor.templateModel.tp_report_goal', 'report_id = ?', [param.id]);
				db.dao.removeById('editor.templateModel.tp_report', param.id);
			}

			result.msg = '删除报告成功';
			result.code = 200;
			return result;
		},
		
		/**
		 * 修改报告
		 * @Autor : King
		 * @Date : 2017-05-10
		 * param参数{id:'',delType:0}
		 * 	0-表示逻辑删除，默认，不传就是逻辑删除
		 * 	1-表示物理删除
		 **/
		update : function (param){
			this.del(param);
			var result = {};
			param.update_user = com.tt.pwp.framework.security.SecurityUtils.getLoginAccountId();
			param.update_time = com.tt.pwp.framework.util.formatter.DateFormatterUtil.long2YYYY_MM_DDHH24miss(new java.util.Date());
			if(param.source == 0){
				var date = new  java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(param.apply_check_time);
				param.apply_check_time = com.tt.pwp.framework.util.formatter.DateFormatterUtil.long2YYYY_MM_DDHH24miss(date);
			}
			param.status = '1';	//默认是1-保存

			if(param.id == null || param.id == 0) {
				param.create_time = com.tt.pwp.framework.util.formatter.DateFormatterUtil.long2YYYY_MM_DDHH24miss(new java.util.Date());
				result.data = db.dao.insertSelective('editor.templateModel.tp_report', param);
			} else {
				param.create_time = com.tt.pwp.framework.util.formatter.DateFormatterUtil.long2YYYY_MM_DDHH24miss(new java.util.Date());
				result.data = db.dao.updateSelective('editor.templateModel.tp_report', param);
			}
			result.msg = '保存成功';
			result.code = 200;
			return result;
		}
};




return handler[param.funName](param);
//return handler[param.funName].call(this, param);
