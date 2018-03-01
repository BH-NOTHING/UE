var subsql = param.custom_func_id==null||param.custom_func_id==""? " custom_func_id is null  and ":" custom_func_id ='"+param.custom_func_id+"' and "
var subMenuSql = param.custom_menu_id==null||param.custom_func_id==""? " custom_menu_id is null " : " custom_menu_id ='"+param.custom_menu_id+"' "
var sql = "delete  from hpb_account_custommenu where "+subsql+ subMenuSql+" and account_id=? and custom_type=? " ;

var params = [param.accountid,param.custom_type];
console.log(sql);
console.log(param.custom_menu_id);
var ret = db.update(sql,params);
console.log(ret);