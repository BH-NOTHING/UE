var dsMgr = require('pwp-datasource'); //数据源管理对象
var db = dsMgr.db('hissystemHis'); //调用db([datasouceId])函数得到指定的数据源对象,dsMgr.db('default')可得到默认数据源
var list = [];
db.dao.query("doctor.systemMessage.tbsysteminfo", "(HISSystemIDs like '12,%' or HISSystemIDs like '12' or HISSystemIDs like '%,12' or HISSystemIDs like '%,12,%' or HISSystemIDs like '18,%' or HISSystemIDs like '18' or HISSystemIDs like '%,18' or HISSystemIDs like '%,18,%') and IdleFlag=0 and GETDATE() BETWEEN effectdate and disableddate", [], function(rs){
    //处理每一行数据
    var dictDetail = {};
    dictDetail.EffectDate = rs.getString("EffectDate");
    dictDetail.InfoCaption = rs.getString("InfoCaption");
    dictDetail.Information = rs.getString("Information");
    dictDetail.Inscribe = rs.getString("Inscribe");
    list.push(dictDetail);
    //TODO
});
return list;