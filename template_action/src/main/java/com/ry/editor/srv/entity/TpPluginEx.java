package com.ry.editor.srv.entity;

import com.tt.pwp.framework.data.model.DefaultDTO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by KING on 2018/3/13.
 */
@Entity
@Table(name = "TP_PLUGIN_EX", schema = "EDITOR")
public class TpPluginEx extends DefaultDTO {
    //private static final long serialVersionUID = 1L;
    private Integer id;
    private String field_code;
    private String field_name;
    private String field_value;
    private Date create_time;

    public TpPluginEx() {
    }

    public TpPluginEx(Integer id, String field_code, String field_name, String field_value, Date create_time) {
        this.id = id;
        this.field_code = field_code;
        this.field_name = field_name;
        this.field_value = field_value;
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Id
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FIELD_CODE")
    public String getFieldCode() {
        return field_code;
    }

    public void setFieldCode(String field_code) {
        this.field_code = field_code;
    }

    @Basic
    @Column(name = "FIELD_NAME")
    public String getFieldName() {
        return field_name;
    }

    public void setFieldName(String field_name) {
        this.field_name = field_name;
    }

    @Basic
    @Column(name = "FIELD_VALUE")
    public String getFieldValue() {
        return field_value;
    }

    public void setFieldValue(String field_value) {
        this.field_value = field_value;
    }

    @Basic
    @Column(name = "CREATE_TIME")
    public Date getCreateDate() {
        return create_time;
    }

    public void setCreateDate(Date create_time) {
        this.create_time = create_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TpPluginEx that = (TpPluginEx) o;

        if (id != that.id) return false;
        if (field_code != null ? !field_code.equals(that.field_code) : that.field_code != null) return false;
        if (field_name != null ? !field_name.equals(that.field_name) : that.field_name != null) return false;
        if (field_value != null ? !field_value.equals(that.field_value) : that.field_value != null) return false;
        if (create_time != null ? !create_time.equals(that.create_time) : that.create_time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (field_code != null ? field_code.hashCode() : 0);
        result = 31 * result + (field_name != null ? field_name.hashCode() : 0);
        result = 31 * result + (field_value != null ? field_value.hashCode() : 0);
        result = 31 * result + (create_time != null ? create_time.hashCode() : 0);
        return result;
    }
}
