/**
 * @Autor : king
 * @Date : 2018-02-01
 */
var toJs = require('nodejava').toJs;
var spring = require("spring");
var result = {};
result.data = '';
result.msg = '操作失败';
result.code = 500;
var isDataSeq =  param.isDataSeq || true; //20180122 king 是否手动读取主键序列号.
var datasource="";                          //数据源
if (param && param.datasource) {
    datasource = param.datasource;
}

var handler = {
    /*
     * 获取序列号
     * var params = {
         seqName:params.seqName,
         datasource:datasource,
         funName:"getSeqNum"
        };
     */
    getSeqNum:function(params){
        if(!params.seqName)return;
        var seqName = "";
        var seqRes = {};
        var seqNum=-1;
        seqName = params.seqName;

        if (datasource){
            seqName = datasource.toUpperCase()+"_"+seqName;
            seqRes.isDataSource=true;
        }
        if(!isDataSeq){//是否需要自己读取序列号
            try {
                var sequenceFactory = spring.getBean("sequenceFactory");
                var datasourceManager = spring.getBean("datasourceManager");
                var dt = datasourceManager.getDataSource(datasource);
                var seqdatasource = new com.tt.pwp.data.dao.util.CurrentThreadSeqDatasourceInfo();
                seqdatasource.setSequenceDataSource(dt);//也可以设置数据源对应的jdbctemplate
                com.tt.pwp.data.dao.util.CurrentThreadDatasourceUtil.setCurrentThreadSeqDatasourceInfo(seqdatasource);            //把这个数据源对象信息给设置到线程变量中
                seqNum = sequenceFactory.generate(params.seqName); //直接调用获取序列号的方法
            } finally {
                //记得清除掉这个线程里面的数据源信息。
                com.tt.pwp.data.dao.util.CurrentThreadDatasourceUtil.clearCurrentThreadSeqDatasourceInfo();
            }
        }else{
            var sequence = spring.getBean("sequence");
            //console.log("seqName:"+seqName);
            seqNum = sequence.generate(seqName);// 获取已创建的序列;
        }
        if(seqNum!=="" && seqNum!== undefined) {
            seqRes.seqNum = seqNum;
            seqRes.msg = "获取成功";
            seqRes.code = 200;
        }else{
            seqRes.seqNum = "";
            seqRes.msg = "获取失败";
            seqRes.code = 500;
        }
        return seqRes;
    }
};

return handler[param.funName](param);
