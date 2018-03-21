/**
 * 保存报告，非提交，这个时候只会保存tp_report表的信息，不会保存goal
 * @Autor : Nick
 * @Date : 2017-03-29
 * param {org_id:'',template_id:'',status:'',content''}
 */

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

