package com.ruoyi.activiti.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 流程模型对象 act_re_model
 * 
 * @author yangyouwang
 * @date 2020-07-24
 */
public class ActReModel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID_ */
    private String id;

    /** 乐观锁 */
    @Excel(name = "乐观锁")
    private Long rev;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** KEY_ */
    @Excel(name = "KEY_")
    private String key;

    /** 分类	 */
    @Excel(name = "分类")
    private String category;

    /** 最新修改时间 */
    @Excel(name = "最新修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastUpdateTime;

    /** 版本 */
    @Excel(name = "版本")
    private Long version;

    /** META_INFO_ */
    @Excel(name = "META_INFO_")
    private String metaInfo;

    /** 部署ID */
    @Excel(name = "部署ID")
    private String deploymentId;

    private String editorSourceValueId;

    private String editorSourceExtraValueId;

    private String tenantId;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setRev(Long rev) 
    {
        this.rev = rev;
    }

    public Long getRev() 
    {
        return rev;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setKey(String key) 
    {
        this.key = key;
    }

    public String getKey() 
    {
        return key;
    }
    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }
    public void setLastUpdateTime(Date lastUpdateTime) 
    {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getLastUpdateTime() 
    {
        return lastUpdateTime;
    }
    public void setVersion(Long version) 
    {
        this.version = version;
    }

    public Long getVersion() 
    {
        return version;
    }
    public void setMetaInfo(String metaInfo) 
    {
        this.metaInfo = metaInfo;
    }

    public String getMetaInfo() 
    {
        return metaInfo;
    }
    public void setDeploymentId(String deploymentId) 
    {
        this.deploymentId = deploymentId;
    }

    public String getDeploymentId() 
    {
        return deploymentId;
    }
    public void setEditorSourceValueId(String editorSourceValueId) 
    {
        this.editorSourceValueId = editorSourceValueId;
    }

    public String getEditorSourceValueId() 
    {
        return editorSourceValueId;
    }
    public void setEditorSourceExtraValueId(String editorSourceExtraValueId) 
    {
        this.editorSourceExtraValueId = editorSourceExtraValueId;
    }

    public String getEditorSourceExtraValueId() 
    {
        return editorSourceExtraValueId;
    }
    public void setTenantId(String tenantId) 
    {
        this.tenantId = tenantId;
    }

    public String getTenantId() 
    {
        return tenantId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("rev", getRev())
            .append("name", getName())
            .append("key", getKey())
            .append("category", getCategory())
            .append("createTime", getCreateTime())
            .append("lastUpdateTime", getLastUpdateTime())
            .append("version", getVersion())
            .append("metaInfo", getMetaInfo())
            .append("deploymentId", getDeploymentId())
            .append("editorSourceValueId", getEditorSourceValueId())
            .append("editorSourceExtraValueId", getEditorSourceExtraValueId())
            .append("tenantId", getTenantId())
            .toString();
    }
}
