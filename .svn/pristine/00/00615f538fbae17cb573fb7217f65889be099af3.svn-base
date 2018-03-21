package com.ry.editor.srv.service;

import com.ry.editor.srv.data.ReportDataDao;
import com.ry.editor.srv.entity.TpTemplateReportdata;
import com.ry.editor.srv.utils.SDEUtil;
import com.tt.pwp.framework.util.no.Sequence;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/*
 * ReportSrv
 */
@Component("ReportDataSrv")
public class ReportDataSrv {

    public static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ReportDataSrv.class);

    @Resource
    private ReportDataDao reportDataDao;
    @Resource
    private Sequence seq;


    @Transactional(rollbackFor = Exception.class)
    public JSONObject batchInsertReportData(List<TpTemplateReportdata> list, String dataSource) throws Exception {
        log.info("batchInsertReportDate-----java开始!");

        long startTime = System.currentTimeMillis();        //记录起始时间
        long oneCommitStartTime = startTime;                //commit时的时间戳
        int oneCommitNum = 1000;                                  //每n条命令commit一次
        int faileNum = 0;                                   //记录命令执行失败数
        int commitNum = 0;                                  //记录执行commit次数
        int resNum = 0;                                     //结果数
        List<TpTemplateReportdata> reportDataList = new ArrayList<TpTemplateReportdata>();//数据库读取序列号
        List<String> seqNumList = new ArrayList<String>();       //数据库读取序列号
        String seqName = "TP_TEMPLATE_REPORTDATA_SEQ";      //序列号名称
        int listSize = list.size();                         //报告数据数量
        boolean isDataSourceBoo = (dataSource != null && !dataSource.isEmpty());//是否多数据源

        if (isDataSourceBoo) {
            seqName = dataSource.toUpperCase() + "_" + seqName;
        }
        log.info("batchInsertReportDate-----是否多数据源：" + isDataSourceBoo + "---序列名称seqName：" + seqName + "---listSize:" + listSize);
        seqNumList = seq.generateIdList(seqName, listSize);
        log.info("batchInsertReportDate-----序列号seqNumList长度：" + seqNumList.size() + ",seqNumList：" + seqNumList);
        for (int i = 0; i < listSize; i++) {
            JSONObject json_test = JSONObject.fromObject(list.get(i));
            TpTemplateReportdata obj2 = (TpTemplateReportdata) JSONObject.toBean(json_test, TpTemplateReportdata.class);
            obj2.setId(Integer.valueOf(seqNumList.get(i)));
            log.info("ReportData实体：" + obj2.toString());
            reportDataList.add(obj2);
            if (i % oneCommitNum == 0) {
                long oneCommitEndTime = System.currentTimeMillis();
                log.info("batchInsertReportDate-----第" + commitNum + "次批量组装耗时：" + (oneCommitEndTime - oneCommitStartTime));
                oneCommitStartTime = oneCommitEndTime;
                List<TpTemplateReportdata> res1 = reportDataDao.batchInsertReportData(reportDataList, dataSource);
                resNum = resNum + res1.size();
                reportDataList.clear();
                commitNum++;
            }
        }
        if (reportDataList.size() > 0) {
            log.info("batchInsertReportDate-----第" + commitNum + "次(最后一次)批量插入：");
            List<TpTemplateReportdata> res2 = reportDataDao.batchInsertReportData(reportDataList, dataSource);
            resNum = resNum + res2.size();
            commitNum++;
        }
        int time = (int) (System.currentTimeMillis() - startTime);
        log.info("batchInsertReportDate-----每次提交：" + oneCommitNum + "条-----提交次数：" + commitNum + "次-----失败次数：" + faileNum + "次-----用时：" + time + "毫秒-----结果数量：" + resNum + "个");
        log.info("batchInsertReportDate-----java结束!");
        JSONObject res = SDEUtil.resToObject(new int[]{oneCommitNum, commitNum, faileNum, time, resNum, 200});

        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    public JSONObject batchUpdateReportData(List<TpTemplateReportdata> list, String dataSource) throws Exception {
        log.info("batchUpdateReportData-----java开始!");

        long startTime = System.currentTimeMillis();        //记录起始时间
        long oneCommitStartTime = startTime;                //commit时的时间戳
        int oneCommitNum = 1000;                                  //每n条命令commit一次
        int faileNum = 0;                                   //记录命令执行失败数
        int commitNum = 0;                                  //记录执行commit次数
        int[] resNum = new int[]{};                                     //结果数
        List<TpTemplateReportdata> reportDataList = new ArrayList<TpTemplateReportdata>();//数据库读取序列号
        List<String> seqNumList = new ArrayList<String>();     //数据库读取序列号
        String seqName = "TP_TEMPLATE_REPORTDATA_SEQ";      //序列号名称
        int listSize = list.size();                         //报告数据数量
        boolean isDataSourceBoo = (dataSource != null && !dataSource.isEmpty());//是否多数据源

        if (isDataSourceBoo) {
            seqName = dataSource.toUpperCase() + "_" + seqName;
        }
        log.info("batchUpdateReportData-----是否多数据源：" + isDataSourceBoo + "---序列名称seqName：" + seqName + "---listSize:" + listSize);
        seqNumList = seq.generateIdList(seqName, listSize);
        log.info("batchUpdateReportData-----序列号seqNumList长度：" + seqNumList.size() + ",seqNumList：" + seqNumList);
        for (int i = 0; i < listSize; i++) {
            JSONObject json_test = JSONObject.fromObject(list.get(i));
            log.info("json_test---:" + json_test.toString());
            TpTemplateReportdata obj2 = (TpTemplateReportdata) JSONObject.toBean(json_test, TpTemplateReportdata.class);
            obj2.setId(Integer.valueOf(seqNumList.get(i)));
            log.info("ReportData实体：" + obj2.toString());
            reportDataList.add(obj2);
            if (i % oneCommitNum == 0) {
                long oneCommitEndTime = System.currentTimeMillis();
                log.info("batchUpdateReportData-----第" + commitNum + "次批量组装耗时：" + (oneCommitEndTime - oneCommitStartTime));
                oneCommitStartTime = oneCommitEndTime;
                resNum[commitNum] = reportDataDao.batchUpdateReportData(reportDataList, dataSource);
                reportDataList.clear();
                commitNum++;

            }
        }
        if (reportDataList.size() > 0) {
            log.info("batchUpdateReportData-----第" + commitNum + "次(最后一次)批量插入：");
            resNum[commitNum] = reportDataDao.batchUpdateReportData(reportDataList, dataSource);
            commitNum++;
        }
        int time = (int) (System.currentTimeMillis() - startTime);
        log.info("batchUpdateReportData-----每次提交：" + oneCommitNum + "条-----提交次数：" + commitNum + "次-----失败次数：" + faileNum + "次-----用时：" + time + "毫秒-----结果数量：" + resNum + "个");
        log.info("batchUpdateReportData-----java结束!");
        JSONObject res = SDEUtil.resToObject(new int[]{oneCommitNum, commitNum, faileNum, time, 0, 200});

        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    public int batchRemoveReportData(List<TpTemplateReportdata> list, String dataSource) throws Exception {
        int listSize = list.size();                         //报告数据数量
        List<TpTemplateReportdata> reportDataList = new ArrayList<TpTemplateReportdata>();//数据库读取序列号
        int res = 0;
        for (int i = 0; i < listSize; i++) {
            JSONObject json_test = JSONObject.fromObject(list.get(i));
            TpTemplateReportdata obj2 = (TpTemplateReportdata) JSONObject.toBean(json_test, TpTemplateReportdata.class);
            reportDataList.add(obj2);
        }
        res = reportDataDao.batchRemoveReportData(reportDataList, dataSource);

        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    public JSONObject batchRemoveReportDataList(String[] strs, String dataSource) throws Exception {
        int listSize = strs.length;                         //报告数据数量
        log.info("batchRemoveReportDataList-----java开始!" + listSize);
        String where = "";
        String modelId = "editor.editorModel.tp_template_reportdata";
        List<Object> conditions = new ArrayList<Object>();
        for (int i = 0; i < listSize; i++) {
            conditions.add(strs[i]);
            where = where + " report_id = ?";
            if (i != listSize - 1) {
                where = where + " or ";
            }

        }
        int resNum = reportDataDao.getDao(dataSource).remove(modelId, where, conditions);
        JSONObject res = SDEUtil.resToObject(new int[]{0, 0, 0, 0, 0, 200});

        return res;
    }

}