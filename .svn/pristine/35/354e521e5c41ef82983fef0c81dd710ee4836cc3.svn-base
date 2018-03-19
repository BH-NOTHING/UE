
var sdeAction = function () {};

sdeAction.prototype = {
    testParam: function () {
        return srvByAjax("reportSrv", "testParam", {key0: "服务名称", key1: "1", key2: 1}, false);
    }

};


function srvByAjax(srvName, funName, param, async) {
    var dataRes;
    if (typeof async != 'boolean') {
        async = true
    }
    var sdeConfigTemp = window.SDE_CONFIG ? window.SDE_CONFIG : parent.window.SDE_CONFIG;
    var srvParam = {
        datasource: (sdeConfigTemp && sdeConfigTemp.DATASOURCE) ? sdeConfigTemp.DATASOURCE : "",  //数据源配置
    };
    srvParam = $.extend(true, srvParam, param);
    $.ajax({
        type: "post",
        url: ctx + "/editor/"+srvName+"/" + funName + ".do",
        data: srvParam,
        async: async,
        dataType: "json",
        success: function (data) {
            console.log(JSON.stringify(data));
            dataRes = data;
        }
    });
    return dataRes;

}
