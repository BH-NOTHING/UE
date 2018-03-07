var sql = "select * from TP_TEMPLATE t ORDER BY t.TEMPLATE_VER_ID";
var querydata = db.queryService.query(sql,param);

return querydata;