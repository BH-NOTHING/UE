package com.ry.editor.srv.dao;

import com.tt.pwp.framework.data.dao.Dao;
import com.tt.pwp.framework.data.dao.DaoFactory;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;

/**
 * Copy by KING on 2018/3/13.
 * 基于JDBC操作的普通DAO
 */
public abstract class JdbcDao {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private DaoFactory daoFactory;

    public DaoFactory getDaoFactory() {
        return this.daoFactory;
    }

    /**
     * 获取平台默认数据源的DAO操作类
     *
     * @return
     */
    public Dao getDao() {
        try {
            return this.daoFactory.getDao();
        } catch (Exception var2) {
            logger.error(ExceptionUtils.getStackTrace(var2));
            return null;
        }
    }


    /**
     * 获取指定数据源ID的DAO操作类
     *
     * @param dataSourceId
     * @return
     */
    public Dao getDao(String dataSourceId) {
        try {
            return this.daoFactory.getDao(dataSourceId);
        } catch (Exception var2) {
            logger.error(ExceptionUtils.getStackTrace(var2));
            return null;
        }
    }

    public JdbcTemplate getJdbcTemplate() {
        return getDao().getJdbcTemplate();
    }

}
