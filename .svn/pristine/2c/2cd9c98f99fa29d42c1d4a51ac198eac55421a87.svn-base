package com.ry.editor.srv.dao;

import com.tt.pwp.framework.data.dao.Dao;
import com.tt.pwp.framework.data.model.DefaultDTO;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.Serializable;

/**
 * 集成平台公共DAO父类
 */
public abstract class EditorDao<T extends DefaultDTO, PK extends Serializable> extends CommonDao<T, PK>{

    /**
     * 获取PWP3默认数据源的DAO操作类
     * @return
     */
    @Override
    public Dao getDefaultDao() {
        try {
            return super.getDaoFactory().getDao();
        } catch (Exception var2) {
            logger.error(ExceptionUtils.getStackTrace(var2));
            return null;
        }
    }
}
