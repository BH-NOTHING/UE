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
@Table(name = "TP_REPORT_REVISE", schema = "EDITOR")
public class TpReportRevise extends DefaultDTO {
    //private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer report_id;
    private Integer user_id;
    private String content;
    private Boolean content_type;
    private Date create_time;
    private Boolean status;
    private Date content_ctime;

    public TpReportRevise() {
    }

    public TpReportRevise(Integer id, Integer report_id, Integer user_id, String content, Boolean content_type, Date create_time, Boolean status, Date content_ctime) {
        this.id = id;
        this.report_id = report_id;
        this.user_id = user_id;
        this.content = content;
        this.content_type = content_type;
        this.create_time = create_time;
        this.status = status;
        this.content_ctime = content_ctime;
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
    @Column(name = "REPORT_ID")
    public Integer getReportId() {
        return report_id;
    }

    public void setReportId(Integer report_id) {
        this.report_id = report_id;
    }

    @Basic
    @Column(name = "USER_ID")
    public Integer getUserId() {
        return user_id;
    }

    public void setUserId(Integer user_id) {
        this.user_id = user_id;
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
    @Column(name = "CONTENT_TYPE")
    public Boolean getContentType() {
        return content_type;
    }

    public void setContentType(Boolean content_type) {
        this.content_type = content_type;
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
    @Column(name = "STATUS")
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Basic
    @Column(name = "CONTENT_CTIME")
    public Date getContentCtime() {
        return content_ctime;
    }

    public void setContentCtime(Date content_ctime) {
        this.content_ctime = content_ctime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TpReportRevise that = (TpReportRevise) o;

        if (id != that.id) return false;
        if (report_id != that.report_id) return false;
        if (user_id != that.user_id) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (content_type != null ? !content_type.equals(that.content_type) : that.content_type != null) return false;
        if (create_time != null ? !create_time.equals(that.create_time) : that.create_time != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (content_ctime != null ? !content_ctime.equals(that.content_ctime) : that.content_ctime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (report_id ^ (report_id >>> 32));
        result = 31 * result + (int) (user_id ^ (user_id >>> 32));
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (content_type != null ? content_type.hashCode() : 0);
        result = 31 * result + (create_time != null ? create_time.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (content_ctime != null ? content_ctime.hashCode() : 0);
        return result;
    }
}
