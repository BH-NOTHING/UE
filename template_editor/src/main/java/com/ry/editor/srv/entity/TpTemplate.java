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
@Table(name = "TP_TEMPLATE", schema = "EDITOR")
public class TpTemplate extends DefaultDTO {
    //private static final long serialVersionUID = 1L;
    private Integer template_ver_id;
    private Integer template_id;
    private String name;
    private String content;
    private Long org_id;
    private Long user_id;
    private Long create_user;
    private Long lastupdate_user;
    private Date create_time;
    private Date lastupdate_time;
    private String status;
    private String syn_version;
    private Date valid_date;

    public TpTemplate() {
    }

    public TpTemplate(Integer template_ver_id, Integer template_id, String name, String content, Long org_id, Long user_id, Long create_user, Long lastupdate_user, Date create_time, Date lastupdate_time, String status, String syn_version, Date valid_date) {
        this.template_ver_id = template_ver_id;
        this.template_id = template_id;
        this.name = name;
        this.content = content;
        this.org_id = org_id;
        this.user_id = user_id;
        this.create_user = create_user;
        this.lastupdate_user = lastupdate_user;
        this.create_time = create_time;
        this.lastupdate_time = lastupdate_time;
        this.status = status;
        this.syn_version = syn_version;
        this.valid_date = valid_date;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Id
    @Column(name = "TEMPLATE_VER_ID")
    public Integer getTemplateVer_id() {
        return template_ver_id;
    }

    public void setTemplateVer_id(Integer template_ver_id) {
        this.template_ver_id = template_ver_id;
    }

    @Basic
    @Column(name = "TEMPLATE_ID")
    public Integer getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(Integer template_id) {
        this.template_id = template_id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "CONTENT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "ORG_ID")
    public Long getOrg_id() {
        return org_id;
    }

    public void setOrg_id(Long org_id) {
        this.org_id = org_id;
    }

    @Basic
    @Column(name = "USER_ID")
    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @Basic
    @Column(name = "CREATE_USER")
    public Long getCreate_user() {
        return create_user;
    }

    public void setCreate_user(Long create_user) {
        this.create_user = create_user;
    }

    @Basic
    @Column(name = "LASTUPDATE_USER")
    public Long getLastupdate_user() {
        return lastupdate_user;
    }

    public void setLastupdate_user(Long lastupdate_user) {
        this.lastupdate_user = lastupdate_user;
    }

    @Basic
    @Column(name = "CREATE_TIME")
    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Basic
    @Column(name = "LASTUPDATE_TIME")
    public Date getLastupdate_time() {
        return lastupdate_time;
    }

    public void setLastupdate_time(Date lastupdate_time) {
        this.lastupdate_time = lastupdate_time;
    }

    @Basic
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "SYN_VERSION")
    public String getSynVersion() {
        return syn_version;
    }

    public void setSynVersion(String syn_version) {
        this.syn_version = syn_version;
    }

    @Basic
    @Column(name = "VALID_DATE")
    public Date getValidDate() {
        return valid_date;
    }

    public void setValidDate(Date valid_date) {
        this.valid_date = valid_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TpTemplate that = (TpTemplate) o;

        if (template_ver_id != that.template_ver_id) return false;
        if (template_id != that.template_id) return false;
        if (syn_version != that.syn_version) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (org_id != null ? !org_id.equals(that.org_id) : that.org_id != null) return false;
        if (user_id != null ? !user_id.equals(that.user_id) : that.user_id != null) return false;
        if (create_user != null ? !create_user.equals(that.create_user) : that.create_user != null) return false;
        if (lastupdate_user != null ? !lastupdate_user.equals(that.lastupdate_user) : that.lastupdate_user != null)
            return false;
        if (create_time != null ? !create_time.equals(that.create_time) : that.create_time != null) return false;
        if (lastupdate_time != null ? !lastupdate_time.equals(that.lastupdate_time) : that.lastupdate_time != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (valid_date != null ? !valid_date.equals(that.valid_date) : that.valid_date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (template_ver_id ^ (template_ver_id >>> 32));
        result = 31 * result + (int) (template_id ^ (template_id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (org_id != null ? org_id.hashCode() : 0);
        result = 31 * result + (user_id != null ? user_id.hashCode() : 0);
        result = 31 * result + (create_user != null ? create_user.hashCode() : 0);
        result = 31 * result + (lastupdate_user != null ? lastupdate_user.hashCode() : 0);
        result = 31 * result + (create_time != null ? create_time.hashCode() : 0);
        result = 31 * result + (lastupdate_time != null ? lastupdate_time.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (syn_version != null ? syn_version.hashCode() : 0);
        result = 31 * result + (valid_date != null ? valid_date.hashCode() : 0);
        return result;
    }
}
