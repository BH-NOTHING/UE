/**
 * 删除报告
 * @Autor : Nick
 * @Date : 2017-03-29
 * param参数{id:'',delType:0}
 * 	0-表示逻辑删除，默认，不传就是逻辑删除
 * 	1-表示物理删除
 **/

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
