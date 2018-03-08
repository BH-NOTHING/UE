var menutree = require("pwp-menutree");
var data= menutree.getMenuTree();
var toJs = require('nodejava').toJs;
var accountJS = toJs.parse(data);
//todo 这里要加载自定义的菜单 根据当前用户ID去查
//这种是一级菜单的
var getCustomSql = " select custom_menu_id ,custom_func_id from hpb_account_custommenu where account_id =:accountid order by id asc,custom_order asc ";
var costomData = db.queryService.query(getCustomSql,param);

accountJS[0].customMenu = toJs.parse(costomData.data);
return accountJS;