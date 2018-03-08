var sql = "select * from tp_template_type";
var userFormatterSql = "select * from pwp_account";
var formatter = {
	formatter:{
		"create_user":db.formatterUtil.sql(userFormatterSql,"account_id","account_name"),
		"lastupdate_user":db.formatterUtil.sql(userFormatterSql,"account_id","account_name"),
		"status":db.formatterUtil.dict("templateType_status")
	}
}
var data = db.queryService.query(sql,param,formatter);
return data;
