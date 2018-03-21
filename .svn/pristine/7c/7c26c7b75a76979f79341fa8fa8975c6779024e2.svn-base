var toJs = require('nodejava').toJs;
var rows=param.rows;
//console.log(rows);
if(rows == null) {
	return false;
}
for(var i=0;i<rows.length;i++){
	//console.log(rows[i]);
	if("Appended".equals(rows[i].updateFlag)){
		rows[i].create_user = com.tt.pwp.framework.security.SecurityUtils.getLoginAccountId();
		rows[i].create_time = com.tt.pwp.framework.util.formatter.DateFormatterUtil.long2YYYY_MM_DDHH24miss(new java.util.Date());
	}else if("Updated".equals(rows[i].updateFlag)){
		rows[i].lastupdate_user = com.tt.pwp.framework.security.SecurityUtils.getLoginAccountId();
		rows[i].lastupdate_time = com.tt.pwp.framework.util.formatter.DateFormatterUtil.long2YYYY_MM_DDHH24miss(new java.util.Date());
	}
}
//console.log(rows);
try {
	var dmm = require('daoModelManager');
	var model = dmm.findModel("editor.templateModel.tp_template_type");
	var emp = db.dao.handleSelectiveList(model,rows);
	return true;
}catch (err) {
	//console.log(err);
	return false;
}