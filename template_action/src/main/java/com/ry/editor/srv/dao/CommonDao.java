package com.ry.editor.srv.dao;

import com.tt.pwp.framework.data.dao.Dao;
import com.tt.pwp.framework.data.dao.DaoFactory;
import com.tt.pwp.framework.data.model.DefaultDTO;
import com.tt.pwp.framework.util.beanutils.ReflectUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 通用的DAO基类
 *
 * @param <T>  实体类型
 * @param <PK> 实体主键类型
 */
public abstract class CommonDao<T extends DefaultDTO, PK extends Serializable> implements InitializingBean {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private DaoFactory daoFactory;
    private RowMapper<T> rowMapper;
    private Class<T> persistentClass;

    public CommonDao() {
        this.persistentClass = ReflectUtil.getGenericTypeArgument(this.getClass());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.rowMapper = getDefaultDao().getRowMapper(getModel());
    }

    protected RowMapper<T> getRowMapper() {
        return this.rowMapper;
    }

    /**
     * 获取模型类名
     *
     * @return
     */
    protected String getModel() {
        return this.persistentClass.getName();
    }

    public DaoFactory getDaoFactory() {
        return this.daoFactory;
    }

    /**
     * 定义默认数据源的DAO操作类
     *
     * @return
     */
    public abstract Dao getDefaultDao();

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

    /**
     * 根据主键获取对象
     *
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    public T findById(PK id) {
        Assert.notNull(id, "id不应为空！");
        return (T) getDefaultDao().findById(getModel(), id);
    }

    /**
     * 根据where条件SQL语句及条件参数获取对象
     *
     * @param whereSql where条件sql语句
     * @param params   条件参数
     * @return
     */
    public T find(String whereSql, Object... params) {
        return getDefaultDao().find(getModel(), whereSql, params);
    }

    /**
     * 根据where条件SQL语句及条件参数获取对象列表
     *
     * @param whereSql where条件sql语句
     * @param params   条件参数
     * @return
     */
    public List<T> findList(String whereSql, Object... params) {
        List<T> results = getDefaultDao().findAll(getModel(), whereSql, params);
        if (CollectionUtils.isEmpty(results))
            return Collections.emptyList();
        return results;
    }

    /**
     * 根据SQL语句和条件参数获取对象列表
     *
     * @param sql
     * @param params
     * @return
     */
    public List<T> findBySQL(String sql, Object[] params) {
        List<T> results = this.getDefaultDao().getJdbcTemplate().query(sql, params, getRowMapper());
        if (CollectionUtils.isEmpty(results))
            return Collections.emptyList();
        return results;
    }

    /**
     * 根据SQL语句和条件参数获取对象
     *
     * @param sql
     * @param params
     * @return
     */
    public T findUniqueSQLWithParams(String sql, Object[] params) {
        List<T> results = findBySQL(sql, params);
        if (CollectionUtils.isEmpty(results))
            return null;
        return results.iterator().next();
    }

   /* *
     * 根据SQL语句、条件参数、值对象类型获取值对象列表
     * @param sql
     * @param params
     * @param mappedClass
     * @param <VO>
     * @return
     *//*

    public <VO extends EditorVO> List<VO> findSQLWithParams(String sql, Object[] params, Class<VO> mappedClass) {
        List<VO> results = getDefaultDao().getJdbcTemplate().query(sql, params, new BeanPropertyRowMapper<>(mappedClass));
        if (CollectionUtils.isEmpty(results))
            return Collections.emptyList();
        return results;
    }*/

    public void save(T object) {
        getDefaultDao().insert(object);
    }

    public void update(T object) {
        getDefaultDao().update(object);
    }

    /**
     * 仅更新有值的字段，不更新值为null的字段
     *
     * @param object
     */
    public void updateSelective(T object) {
        getDefaultDao().updateSelective(object);
    }

    /**
     * 仅更新有值的字段，不更新值为null的字段
     *
     * @param object
     */
    public void handleSelective(T object) {
        getDefaultDao().handleSelective(object);
    }

    public void remove(PK id) {
        Assert.notNull(id, "id不应为空！");
        getDefaultDao().removeById(getModel(), id);
    }

    public void remove(T object) {
        Assert.notNull(object, "要删除的对象不应为空！");
        getDefaultDao().remove(object);
    }

    /**
     * 执行SQL语句，使用参数
     *
     * @param sql
     * @param params
     * @return
     */
    public int executeSQLWithParams(String sql, Object... params) {
        return getDefaultDao().getJdbcTemplate().update(sql, params);
    }

    /**
     * 执行SQL语句，使用参数和参数类型
     *
     * @param sql
     * @param params
     * @param paramsTypes java.sql.Types INTEGER DOUBLE NUMERIC DATE TIMESTAMP VARCHAR
     * @return
     */
    public int executeSQLWithParams(String sql, Object[] params, int[] paramsTypes) {
        return getDefaultDao().getJdbcTemplate().update(sql, params, paramsTypes);
    }
}
