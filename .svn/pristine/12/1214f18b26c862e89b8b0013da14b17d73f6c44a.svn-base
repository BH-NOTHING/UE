var sql = "insert into hpb_account_custommenu(ID,CUSTOM_MENU_ID,CUSTOM_FUNC_ID,CUSTOM_TYPE,CUSTOM_NAME,CUSTOM_ORDER,ACCOUNT_ID,CREATETIME,DATASOURCE_TYPE)" +
    " values(?,?,?,?,?,?,?,?,?)";
var sequence = require('pwp-sequence');
var noId = "HPB_ACCOUNT_CUSTOMMENU_SEQ"
if(!sequence.isExist(noId)){
    var nodemoObj = {
        noid:noId,
        noname:"HPB_ACCOUNT_CUSTOMMENU 表的序列值",
        nolength:10,
        notype:1,
        prefix:"",
        postfix:"",
        bufferSize:1000,
        initialValue:1,
        noIncrement:1 };
    sequence.create(nodemoObj);
}
var nextval = sequence.generate(noId);
//if(param.custom_func_id=="") param.custom_func_id = null;
var params = [nextval, param.custom_menu_id, param.custom_func_id,param.custom_type, param.custom_name, param.custom_order, param.account_id, new Date(), param.datasource_type];

var ret = db.update(sql,params);
