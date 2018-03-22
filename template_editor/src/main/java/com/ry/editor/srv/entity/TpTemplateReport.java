package com.ry.editor.srv.entity;

import com.tt.pwp.framework.data.model.DefaultDTO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

import java.util.Date;

/**
 * Created by KING on 2018/3/13.
 */
@Entity
@Table(name = "TP_TEMPLATE_REPORT", schema = "EDITOR")
public class TpTemplateReport extends DefaultDTO {
    //private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")

    private Integer id;
    @Basic
    @Column(name = "TEMPLATE_VER_ID")
    private String template_ver_id;
    @Basic
    @Column(name = "STATUS")
    private String status;
    @Basic
    @Column(name = "CONTENT")
    private String content;
    @Basic
    @Column(name = "CREATE_USER")
    private Integer create_user;
    @Basic
    @Column(name = "LASTUPDATE_USER")
    private Long lastupdate_user;
    @Basic
    @Column(name = "CREATE_TIME")
    private Date create_time;
    @Basic
    @Column(name = "LASTUPDATE_TIME")
    private Date lastupdate_time;
    @Basic
    @Column(name = "SYN_VERSION")
    private Integer syn_version;

    public TpTemplateReport() {
    }

    public TpTemplateReport(Integer id, String template_ver_id, String status, String content, Integer create_user, Long lastupdate_user, Date create_time, Date lastupdate_time, int syn_version) {
        this.id = id;
        this.template_ver_id = template_ver_id;
        this.status = status;
        this.content = content;
        this.create_user = create_user;
        this.lastupdate_user = lastupdate_user;
        this.create_time = create_time;
        this.lastupdate_time = lastupdate_time;
        this.syn_version = syn_version;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Integer getCreate_user() {
        return create_user;
    }

    public void setCreate_user(Integer create_user) {
        this.create_user = create_user;
    }


    public Long getLastupdate_user() {
        return lastupdate_user;
    }

    public void setLastupdate_user(Long lastupdate_user) {
        this.lastupdate_user = lastupdate_user;
    }


    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }


    public Date getLastupdate_time() {
        return lastupdate_time;
    }

    public void setLastupdate_time(Date lastupdate_time) {
        this.lastupdate_time = lastupdate_time;
    }


    public int getSyn_version() {
        return syn_version;
    }

    public void setSyn_version(int syn_version) {
        this.syn_version = syn_version;
    }

    public String getTemplate_ver_id() { return template_ver_id; }

    public void setTemplate_ver_id(String template_ver_id) { this.template_ver_id = template_ver_id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TpTemplateReport that = (TpTemplateReport) o;

        if (id != that.id) return false;
        if (create_user != that.create_user) return false;
        if (syn_version != that.syn_version) return false;
        if (template_ver_id != null ? !template_ver_id.equals(that.template_ver_id) : that.template_ver_id != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (lastupdate_user != null ? !lastupdate_user.equals(that.lastupdate_user) : that.lastupdate_user != null)
            return false;
        if (create_time != null ? !create_time.equals(that.create_time) : that.create_time != null) return false;
        if (lastupdate_time != null ? !lastupdate_time.equals(that.lastupdate_time) : that.lastupdate_time != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (template_ver_id != null ? template_ver_id.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (int) (create_user ^ (create_user >>> 32));
        result = 31 * result + (lastupdate_user != null ? lastupdate_user.hashCode() : 0);
        result = 31 * result + (create_time != null ? create_time.hashCode() : 0);
        result = 31 * result + (lastupdate_time != null ? lastupdate_time.hashCode() : 0);
        result = 31 * result + syn_version;
        return result;
    }
}
