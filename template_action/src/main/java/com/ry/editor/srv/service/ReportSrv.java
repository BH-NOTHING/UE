package com.ry.editor.srv.service;

import com.ry.editor.srv.data.ReportDao;
import com.ry.editor.srv.entity.TpTemplateReport;
import com.ry.editor.srv.utils.SDEUtil;
import com.tt.pwp.framework.util.no.Sequence;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * ReportSrv
 */
@Component("ReportSrv")
public class ReportSrv {

    public static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ReportSrv.class);

    @Resource
    private ReportDao reportDao;
    @Resource
    private Sequence seq;


    @Transactional(rollbackFor = Exception.class)
    public JSONObject batchInsertReport(String dataSource, List<TpTemplateReport> list) throws Exception {
        log.info("batchInsertReport-----java开始!");
        long startTime = System.currentTimeMillis();        //记录起始时间
        long oneCommitStartTime = startTime;                //commit时的时间戳
        int oneCommitNum = 1000;                                  //每n条命令commit一次
        int faileNum = 0;                                   //记录命令执行失败数
        int commitNum = 0;                                  //记录执行commit次数
        int resNum = 0;                                     //结果数
        List<TpTemplateReport> reportList = new ArrayList<TpTemplateReport>();//数据库读取序列号
        List<String> seqNumList = new ArrayList<String>();       //数据库读取序列号
        String seqName = "TP_TEMPLATE_REPORT_SEQ";      //序列号名称
        int listSize = list.size();                         //报告数据数量
        boolean isDataSourceBoo = (dataSource != null && !dataSource.isEmpty());//是否多数据源

        if (isDataSourceBoo) {
            seqName = dataSource.toUpperCase() + "_" + seqName;
        }
        log.info("batchInsertReport-----是否多数据源：" + isDataSourceBoo + "---序列名称seqName：" + seqName + "---listSize:" + listSize);
        seqNumList = seq.generateIdList(seqName, listSize);
        log.info("batchInsertReport-----序列号seqNumList长度：" + seqNumList.size() + ",seqNumList：" + seqNumList);
        for (int i = 0; i < listSize; i++) {
            JSONObject json_test = JSONObject.fromObject(list.get(i));
            TpTemplateReport obj2 = (TpTemplateReport) JSONObject.toBean(json_test, TpTemplateReport.class);
            obj2.setId(Integer.valueOf(seqNumList.get(i)));
            log.info("Report实体：" + obj2.toString());
            reportList.add(obj2);
            if (i % oneCommitNum == 0) {
                long oneCommitEndTime = System.currentTimeMillis();
                log.info("batchInsertReport-----第" + commitNum + "次批量组装耗时：" + (oneCommitEndTime - oneCommitStartTime));
                oneCommitStartTime = oneCommitEndTime;
                List<TpTemplateReport> res1 = reportDao.batchInsertReport(reportList, dataSource);
                resNum = resNum + res1.size();
                reportList.clear();
                commitNum++;
            }
        }
        if (reportList.size() > 0) {
            log.info("batchInsertReport-----第" + commitNum + "次(最后一次)批量插入：");
            List<TpTemplateReport> res2 = reportDao.batchInsertReport(reportList, dataSource);
            resNum = resNum + res2.size();
            commitNum++;
        }
        int time = (int) (System.currentTimeMillis() - startTime);
        log.info("batchInsertReport-----每次提交：" + oneCommitNum + "条-----提交次数：" + commitNum + "次-----失败次数：" + faileNum + "次-----用时：" + time + "毫秒-----结果数量：" + resNum + "个");
        log.info("batchInsertReport-----java结束!");
        JSONObject res = SDEUtil.resToObject(new int[]{oneCommitNum, commitNum, faileNum, time, resNum, 200});

        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchUpdateReportList(List<TpTemplateReport> list, String dataSource) throws Exception {
        log.info("batchUpdateReport-----java开始!list:" + list.toString());
        long startTime = System.currentTimeMillis();        //记录起始时间
        int resNum = 0;
        List<TpTemplateReport> reportList = new ArrayList<TpTemplateReport>();//数据库读取序列号
        int listSize = list.size();                         //报告数据数量
        for (int i = 0; i < listSize; i++) {
            JSONObject jsonObj = JSONObject.fromObject(list.get(i));
            TpTemplateReport report = (TpTemplateReport) JSONObject.toBean(jsonObj, TpTemplateReport.class);
            report.setCreate_time(new java.sql.Time(System.currentTimeMillis()));
            report.setLastupdate_time(new java.sql.Time(System.currentTimeMillis()));
            log.info("Report实体：" + report.toString());
            reportList.add(report);
        }
        if (reportList.size() > 0) {
            resNum = reportDao.batchUpdateReport(reportList, dataSource);
        }
        int time = (int) (System.currentTimeMillis() - startTime);
        log.info("batchUpdateReport-----java结束!");


    }

    @Transactional(rollbackFor = Exception.class)
    public List<TpTemplateReport> findById(List<Object> list, String dataSource) throws Exception {
        log.info("testBean-----java开始!list:" + list.toString());
        List<TpTemplateReport> tpReportId = null;
        try {
            tpReportId = reportDao.findById(dataSource);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("testBean-----java结束!tpReportId=" + tpReportId);

        return tpReportId;
    }

    @Transactional(rollbackFor = Exception.class)
    public void testMap(List<Object> list) throws Exception {
        log.info("testBean-----java开始---list:" + list.size());
        for(Object obj : list){
            log.info("testBean-----obj:" + obj.toString());
        }



    }


}