//第二个参数是查询条件，第三个参数是条件的值
//console.log(param.type_name);
var id=param.id;
if(id.length==0){
	var count = db.dao.count("editor.templateModel.tp_template_type","type_name=?",[param.type_name]);
	if(count>0){
		return false;
	}else{
		return true;
	}
}else {
	var count = db.dao.count("editor.templateModel.tp_template_type","type_name=? and id!=?",[param.type_name,id]);
	if(count>0){
		return false;
	}else{
		return true;
	}
}