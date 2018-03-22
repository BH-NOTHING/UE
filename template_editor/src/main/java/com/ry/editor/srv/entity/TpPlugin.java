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
@Table(name = "TP_PLUGIN", schema = "EDITOR")
public class TpPlugin extends DefaultDTO {
    //private static final long serialVersionUID = 1L;
    private Integer ver_id;
    private Integer id;
    private Integer template_type_id;
    private String code;
    private String name;
    private String tag;
    private String type;
    private Date create_time;
    private Date lastupdate_time;
    private String status;
    private String descname;
    private String required;
    private String text_color;
    private String isdisplay;
    private String readonly;
    private String bindingdata;
    private String value;
    private String rule;
    private String is_readonly;
    private String verifytype;
    private Long tree_type_id;

    public TpPlugin(){
    }

    public TpPlugin(Integer ver_id, Integer id, Integer template_type_id, String code, String name, String tag, String type, Date create_time, Date lastupdate_time, String status, String descname, String required, String text_color, String isdisplay, String readonly, String bindingdata, String value, String rule, String is_readonly, String verifytype, Long tree_type_id) {
        this.ver_id = ver_id;
        this.id = id;
        this.template_type_id = template_type_id;
        this.code = code;
        this.name = name;
        this.tag = tag;
        this.type = type;
        this.create_time = create_time;
        this.lastupdate_time = lastupdate_time;
        this.status = status;
        this.descname = descname;
        this.required = required;
        this.text_color = text_color;
        this.isdisplay = isdisplay;
        this.readonly = readonly;
        this.bindingdata = bindingdata;
        this.value = value;
        this.rule = rule;
        this.is_readonly = is_readonly;
        this.verifytype = verifytype;
        this.tree_type_id = tree_type_id;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Id
    @Column(name = "VER_ID")
    public Integer getVerId() {
        return ver_id;
    }

    public void setVerId(Integer ver_id) {
        this.ver_id = ver_id;
    }

    @Basic
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TEMPLATE_TYPE_ID")
    public Integer getTemplateTypeId() {
        return template_type_id;
    }

    public void setTemplateTypeId(Integer template_type_id) {
        this.template_type_id = template_type_id;
    }

    @Basic
    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    @Column(name = "TAG")
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Basic
    @Column(name = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    @Column(name = "DESCNAME")
    public String getDescname() {
        return descname;
    }

    public void setDescname(String descname) {
        this.descname = descname;
    }

    @Basic
    @Column(name = "REQUIRED")
    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    @Basic
    @Column(name = "TEXT_COLOR")
    public String getTextColor() {
        return text_color;
    }

    public void setTextColor(String text_color) {
        this.text_color = text_color;
    }

    @Basic
    @Column(name = "ISDISPLAY")
    public String getIsdisplay() {
        return isdisplay;
    }

    public void setIsdisplay(String isdisplay) {
        this.isdisplay = isdisplay;
    }

    @Basic
    @Column(name = "READONLY")
    public String getReadonly() {
        return readonly;
    }

    public void setReadonly(String readonly) {
        this.readonly = readonly;
    }

    @Basic
    @Column(name = "BINDINGDATA")
    public String getBindingdata() {
        return bindingdata;
    }

    public void setBindingdata(String bindingdata) {
        this.bindingdata = bindingdata;
    }

    @Basic
    @Column(name = "VALUE")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "RULE")
    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    @Basic
    @Column(name = "IS_READONLY")
    public String getIsReadonly() {
        return is_readonly;
    }

    public void setIsReadonly(String is_readonly) {
        this.is_readonly = is_readonly;
    }

    @Basic
    @Column(name = "VERIFYTYPE")
    public String getVerifytype() {
        return verifytype;
    }

    public void setVerifytype(String verifytype) {
        this.verifytype = verifytype;
    }

    @Basic
    @Column(name = "TREE_TYPE_ID")
    public Long getTreeTypeId() {
        return tree_type_id;
    }

    public void setTreeTypeId(Long tree_type_id) {
        this.tree_type_id = tree_type_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TpPlugin tpPlugin = (TpPlugin) o;

        if (ver_id != tpPlugin.ver_id) return false;
        if (id != tpPlugin.id) return false;
        if (template_type_id != tpPlugin.template_type_id) return false;
        if (code != null ? !code.equals(tpPlugin.code) : tpPlugin.code != null) return false;
        if (name != null ? !name.equals(tpPlugin.name) : tpPlugin.name != null) return false;
        if (tag != null ? !tag.equals(tpPlugin.tag) : tpPlugin.tag != null) return false;
        if (type != null ? !type.equals(tpPlugin.type) : tpPlugin.type != null) return false;
        if (create_time != null ? !create_time.equals(tpPlugin.create_time) : tpPlugin.create_time != null) return false;
        if (lastupdate_time != null ? !lastupdate_time.equals(tpPlugin.lastupdate_time) : tpPlugin.lastupdate_time != null)
            return false;
        if (status != null ? !status.equals(tpPlugin.status) : tpPlugin.status != null) return false;
        if (descname != null ? !descname.equals(tpPlugin.descname) : tpPlugin.descname != null) return false;
        if (required != null ? !required.equals(tpPlugin.required) : tpPlugin.required != null) return false;
        if (text_color != null ? !text_color.equals(tpPlugin.text_color) : tpPlugin.text_color != null) return false;
        if (isdisplay != null ? !isdisplay.equals(tpPlugin.isdisplay) : tpPlugin.isdisplay != null) return false;
        if (readonly != null ? !readonly.equals(tpPlugin.readonly) : tpPlugin.readonly != null) return false;
        if (bindingdata != null ? !bindingdata.equals(tpPlugin.bindingdata) : tpPlugin.bindingdata != null)
            return false;
        if (value != null ? !value.equals(tpPlugin.value) : tpPlugin.value != null) return false;
        if (rule != null ? !rule.equals(tpPlugin.rule) : tpPlugin.rule != null) return false;
        if (is_readonly != null ? !is_readonly.equals(tpPlugin.is_readonly) : tpPlugin.is_readonly != null) return false;
        if (verifytype != null ? !verifytype.equals(tpPlugin.verifytype) : tpPlugin.verifytype != null) return false;
        if (tree_type_id != null ? !tree_type_id.equals(tpPlugin.tree_type_id) : tpPlugin.tree_type_id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (ver_id ^ (ver_id >>> 32));
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (template_type_id ^ (template_type_id >>> 32));
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (create_time != null ? create_time.hashCode() : 0);
        result = 31 * result + (lastupdate_time != null ? lastupdate_time.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (descname != null ? descname.hashCode() : 0);
        result = 31 * result + (required != null ? required.hashCode() : 0);
        result = 31 * result + (text_color != null ? text_color.hashCode() : 0);
        result = 31 * result + (isdisplay != null ? isdisplay.hashCode() : 0);
        result = 31 * result + (readonly != null ? readonly.hashCode() : 0);
        result = 31 * result + (bindingdata != null ? bindingdata.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (rule != null ? rule.hashCode() : 0);
        result = 31 * result + (is_readonly != null ? is_readonly.hashCode() : 0);
        result = 31 * result + (verifytype != null ? verifytype.hashCode() : 0);
        result = 31 * result + (tree_type_id != null ? tree_type_id.hashCode() : 0);
        return result;
    }
}
