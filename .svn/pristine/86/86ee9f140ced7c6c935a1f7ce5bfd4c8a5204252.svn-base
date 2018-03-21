var sql = "select * from TP_PLUGIN_TREE";
var field = {
    keyField:"id", //主键字段
    parentField:"pid",  //父节点字段
    textField:"name" //文本字段
};
/**
 * 如果是由控件调用的，param可以不做定义
 */
var nodeId = param.id;
var range = param.range;
var filter = param.filter;
var nodes = db.treeLoader.loadTree(sql,field,nodeId,range,filter);
console.log("由控件调用的，param可以不做定义");
return nodes;