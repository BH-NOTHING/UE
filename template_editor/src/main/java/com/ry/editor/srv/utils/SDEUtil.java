package com.ry.editor.srv.utils;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

/*
 * SDE工具类
 */
public class SDEUtil {
    public static Logger log = Logger.getLogger(SDEUtil.class);

    public static JSONObject resToObject(int[] param) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("oneCommitNum", param[0]);
        jsonObj.put("resCommitNum", param[1]);
        jsonObj.put("faileNum", param[2]);
        jsonObj.put("timeNum", param[3]);
        jsonObj.put("resNum", param[4]);
        jsonObj.put("code", param[5]);
        log.info("返回结果:" + jsonObj.toString());
        return jsonObj;
    }

    /**
     * 开始记录日志输出,返回开始时间
     *
     * @return long
     */
    public static long logStart(String LogFuncName, String aloDataSource) throws Exception {
        //ReportDataJdbcDao dao =  SpringContextUtil.getBeanOfType(ReportDataJdbcDao.class);
        long startTime = System.currentTimeMillis();
        log.info("---------------------------------------------" + LogFuncName + "开始---------------------------------------------------------------");
        log.info(LogFuncName + "---开始---数据源名称:" + aloDataSource + "--是否多数据源：" + (aloDataSource != null && !aloDataSource.isEmpty()));
        return startTime;

    }

    /**
     * 结束记录日志输出,无返回
     *
     * @return void
     */
    public static void logEnd(String LogFuncName, long startTime, Object res) throws Exception {
        log.info(LogFuncName + "---结束---耗时:" + (System.currentTimeMillis() - startTime) + "毫秒--------commit----------执行结果：" + res);
        log.info("------------------------------------------------" + LogFuncName + "结束------------------------------------------------------------");

    }
}