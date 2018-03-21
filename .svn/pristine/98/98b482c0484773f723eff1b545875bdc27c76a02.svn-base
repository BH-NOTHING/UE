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
@Table(name = "TP_TEMPLATE_DATA", schema = "EDITOR")
public class TpTemplateData extends DefaultDTO {
    //private static final long serialVersionUID = 1L;
    private Integer id;
    private String goal_code;
    private String goal_name;
    private String goal_value;
    private String goal_type;
    private Date create_time;
    private Date lastupdate_time;
    private Long create_user;
    private Long lastupdate_user;
    private Long plugin_ver_id;
    private String conn_rule_code;
    private String conn_template_ver_id;
    private String conn_plugin_code;

    public TpTemplateData() {
    }

    public TpTemplateData(Integer id, String goal_code, String goal_name, String goal_value, String goal_type, Date create_time, Date lastupdate_time, Long create_user, Long lastupdate_user, Long plugin_ver_id, String conn_rule_code, String conn_template_ver_id, String conn_plugin_code) {
        this.id = id;
        this.goal_code = goal_code;
        this.goal_name = goal_name;
        this.goal_value = goal_value;
        this.goal_type = goal_type;
        this.create_time = create_time;
        this.lastupdate_time = lastupdate_time;
        this.create_user = create_user;
        this.lastupdate_user = lastupdate_user;
        this.plugin_ver_id = plugin_ver_id;
        this.conn_rule_code = conn_rule_code;
        this.conn_template_ver_id = conn_template_ver_id;
        this.conn_plugin_code = conn_plugin_code;
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
    @Column(name = "GOAL_CODE")
    public String getGoalCode() {
        return goal_code;
    }

    public void setGoalCode(String goal_code) {
        this.goal_code = goal_code;
    }

    @Basic
    @Column(name = "GOAL_NAME")
    public String getGoalName() {
        return goal_name;
    }

    public void setGoalName(String goal_name) {
        this.goal_name = goal_name;
    }

    @Basic
    @Column(name = "GOAL_VALUE")
    public String getGoalValue() {
        return goal_value;
    }

    public void setGoalValue(String goal_value) {
        this.goal_value = goal_value;
    }

    @Basic
    @Column(name = "GOAL_TYPE")
    public String getGoalType() {
        return goal_type;
    }

    public void setGoalType(String goal_type) {
        this.goal_type = goal_type;
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
    @Column(name = "PLUGIN_VER_ID")
    public Long getPluginVerId() {
        return plugin_ver_id;
    }

    public void setPluginVerId(Long plugin_ver_id) {
        this.plugin_ver_id = plugin_ver_id;
    }

    @Basic
    @Column(name = "CONN_RULE_CODE")
    public String getConnRuleCode() {
        return conn_rule_code;
    }

    public void setConnRuleCode(String conn_rule_code) {
        this.conn_rule_code = conn_rule_code;
    }

    @Basic
    @Column(name = "CONN_TEMPLATE_VER_ID")
    public String getConnTemplateVerId() {
        return conn_template_ver_id;
    }

    public void setConnTemplateVerId(String conn_template_ver_id) {
        this.conn_template_ver_id = conn_template_ver_id;
    }

    @Basic
    @Column(name = "CONN_PLUGIN_CODE")
    public String getConnPluginCode() {
        return conn_plugin_code;
    }

    public void setConnPluginCode(String conn_plugin_code) {
        this.conn_plugin_code = conn_plugin_code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TpTemplateData that = (TpTemplateData) o;

        if (id != that.id) return false;
        if (goal_code != null ? !goal_code.equals(that.goal_code) : that.goal_code != null) return false;
        if (goal_name != null ? !goal_name.equals(that.goal_name) : that.goal_name != null) return false;
        if (goal_value != null ? !goal_value.equals(that.goal_value) : that.goal_value != null) return false;
        if (goal_type != null ? !goal_type.equals(that.goal_type) : that.goal_type != null) return false;
        if (create_time != null ? !create_time.equals(that.create_time) : that.create_time != null) return false;
        if (lastupdate_time != null ? !lastupdate_time.equals(that.lastupdate_time) : that.lastupdate_time != null)
            return false;
        if (create_user != null ? !create_user.equals(that.create_user) : that.create_user != null) return false;
        if (lastupdate_user != null ? !lastupdate_user.equals(that.lastupdate_user) : that.lastupdate_user != null)
            return false;
        if (plugin_ver_id != null ? !plugin_ver_id.equals(that.plugin_ver_id) : that.plugin_ver_id != null) return false;
        if (conn_rule_code != null ? !conn_rule_code.equals(that.conn_rule_code) : that.conn_rule_code != null) return false;
        if (conn_template_ver_id != null ? !conn_template_ver_id.equals(that.conn_template_ver_id) : that.conn_template_ver_id != null)
            return false;
        if (conn_plugin_code != null ? !conn_plugin_code.equals(that.conn_plugin_code) : that.conn_plugin_code != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (goal_code != null ? goal_code.hashCode() : 0);
        result = 31 * result + (goal_name != null ? goal_name.hashCode() : 0);
        result = 31 * result + (goal_value != null ? goal_value.hashCode() : 0);
        result = 31 * result + (goal_type != null ? goal_type.hashCode() : 0);
        result = 31 * result + (create_time != null ? create_time.hashCode() : 0);
        result = 31 * result + (lastupdate_time != null ? lastupdate_time.hashCode() : 0);
        result = 31 * result + (create_user != null ? create_user.hashCode() : 0);
        result = 31 * result + (lastupdate_user != null ? lastupdate_user.hashCode() : 0);
        result = 31 * result + (plugin_ver_id != null ? plugin_ver_id.hashCode() : 0);
        result = 31 * result + (conn_rule_code != null ? conn_rule_code.hashCode() : 0);
        result = 31 * result + (conn_template_ver_id != null ? conn_template_ver_id.hashCode() : 0);
        result = 31 * result + (conn_plugin_code != null ? conn_plugin_code.hashCode() : 0);
        return result;
    }
}
