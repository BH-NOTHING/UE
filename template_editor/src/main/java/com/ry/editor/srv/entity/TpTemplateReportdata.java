package com.ry.editor.srv.entity;

import com.tt.pwp.framework.data.model.DefaultDTO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by KING on 2018/3/13.
 */
@Entity
@Table(name = "TP_TEMPLATE_REPORTDATA", schema = "EDITOR")
public class TpTemplateReportdata extends DefaultDTO {
  //private static final long serialVersionUID = 1L;
  @Id
  @Column(name = "id")
  private Integer id;
  @Basic
  @Column(name = "report_id")
  private String report_id;
  @Basic
  @Column(name = "goal_code")
  private String goal_code;
  @Basic
  @Column(name = "goal_name")
  private String goal_name;
  @Basic
  @Column(name = "goal_value")
  private String goal_value;
  @Basic
  @Column(name = "goal_type")
  private String goal_type;
  @Basic
  @Column(name = "create_time")
  private Time create_time;
  @Basic
  @Column(name = "lastupdate_time")
  private Time lastupdate_time;
  @Basic
  @Column(name = "create_user")
  private String create_user;
  @Basic
  @Column(name = "lastupdate_user")
  private String lastupdate_user;
  @Basic
  @Column(name = "plugin_ver_id")
  private String plugin_ver_id;

  public TpTemplateReportdata() {
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

  public String getReport_id() {
    return report_id;
  }

  public void setReport_id(String report_id) {
    this.report_id = report_id;
  }

  public String getGoal_code() {
    return goal_code;
  }

  public void setGoal_code(String goal_code) {
    this.goal_code = goal_code;
  }

  public String getGoal_name() {
    return goal_name;
  }

  public void setGoal_name(String goal_name) {
    this.goal_name = goal_name;
  }

  public String getGoal_value() {
    return goal_value;
  }

  public void setGoal_value(String goal_value) {
    this.goal_value = goal_value;
  }

  public String getGoal_type() {
    return goal_type;
  }

  public void setGoal_type(String goal_type) {
    this.goal_type = goal_type;
  }

  public Time getCreate_time() {
    return create_time;
  }

  public void setCreate_time(Time create_time) {
    this.create_time = create_time;
  }

  public Time getLastupdate_time() {
    return lastupdate_time;
  }

  public void setLastupdate_time(Time lastupdate_time) {
    this.lastupdate_time = lastupdate_time;
  }

  public String getCreate_user() {
    return create_user;
  }

  public void setCreate_user(String create_user) {
    this.create_user = create_user;
  }

  public String getLastupdate_user() {
    return lastupdate_user;
  }

  public void setLastupdate_user(String lastupdate_user) {
    this.lastupdate_user = lastupdate_user;
  }

  public String getPlugin_ver_id() {
    return plugin_ver_id;
  }

  public void setPlugin_ver_id(String plugin_ver_id) {
    this.plugin_ver_id = plugin_ver_id;
  }
}
