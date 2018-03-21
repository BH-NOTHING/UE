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
@Table(name = "TP_TEMPLATE_TYPE", schema = "EDITOR")
public class TpTemplateType extends DefaultDTO {
    //private static final long serialVersionUID = 1L;
    private Integer id;
    private String type_code;
    private String type_name;
    private Integer create_user;
    private Date create_time;
    private Date lastupdate_time;
    private Integer lastupdate_user;
    private Boolean status;

    public TpTemplateType() {
    }

    public TpTemplateType(Integer id, String type_code, String type_name, Integer create_user, Date create_time, Date lastupdate_time, Integer lastupdate_user, Boolean status) {
        this.id = id;
        this.type_code = type_code;
        this.type_name = type_name;
        this.create_user = create_user;
        this.create_time = create_time;
        this.lastupdate_time = lastupdate_time;
        this.lastupdate_user = lastupdate_user;
        this.status = status;
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
    @Column(name = "TYPE_CODE")
    public String getType_code() {
        return type_code;
    }

    public void setType_code(String type_code) {
        this.type_code = type_code;
    }

    @Basic
    @Column(name = "TYPE_NAME")
    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    @Basic
    @Column(name = "CREATE_USER")
    public Integer getCreate_user() {
        return create_user;
    }

    public void setCreate_user(Integer create_user) {
        this.create_user = create_user;
    }

    @Basic
    @Column(name = "CREATE_TIME")
    public Date getCreateDate() {
        return create_time;
    }

    public void setCreateDate(Date create_time) {
        this.create_time = create_time;
    }

    @Basic
    @Column(name = "LASTUPDATE_TIME")
    public Date getLastupdateDate() {
        return lastupdate_time;
    }

    public void setLastupdateDate(Date lastupdate_time) {
        this.lastupdate_time = lastupdate_time;
    }

    @Basic
    @Column(name = "LASTUPDATE_USER")
    public Integer getLastupdate_user() {
        return lastupdate_user;
    }

    public void setLastupdate_user(Integer lastupdate_user) {
        this.lastupdate_user = lastupdate_user;
    }

    @Basic
    @Column(name = "STATUS")
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TpTemplateType that = (TpTemplateType) o;

        if (id != that.id) return false;
        if (type_code != null ? !type_code.equals(that.type_code) : that.type_code != null) return false;
        if (type_name != null ? !type_name.equals(that.type_name) : that.type_name != null) return false;
        if (create_user != null ? !create_user.equals(that.create_user) : that.create_user != null) return false;
        if (create_time != null ? !create_time.equals(that.create_time) : that.create_time != null) return false;
        if (lastupdate_time != null ? !lastupdate_time.equals(that.lastupdate_time) : that.lastupdate_time != null)
            return false;
        if (lastupdate_user != null ? !lastupdate_user.equals(that.lastupdate_user) : that.lastupdate_user != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (type_code != null ? type_code.hashCode() : 0);
        result = 31 * result + (type_name != null ? type_name.hashCode() : 0);
        result = 31 * result + (create_user != null ? create_user.hashCode() : 0);
        result = 31 * result + (create_time != null ? create_time.hashCode() : 0);
        result = 31 * result + (lastupdate_time != null ? lastupdate_time.hashCode() : 0);
        result = 31 * result + (lastupdate_user != null ? lastupdate_user.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
