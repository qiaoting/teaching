package com.teaching.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.teaching.common.annotation.Excel;
import com.teaching.common.core.domain.BaseEntity;

/**
 * 微信媒体对象 wx_media
 * 
 * @author qiaoting
 * @date 2025-03-06
 */
public class WxMedia extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 媒体ID */
    private Long mediaId;

    /** 商业ID */
    @Excel(name = "商业ID")
    private Long businessId;

    /** 媒体名称 */
    @Excel(name = "媒体名称")
    private String mediaName;

    /** 媒体路径 */
    @Excel(name = "媒体路径")
    private String mediaPath;

    /** 状态（0正常 1关闭） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=关闭")
    private String status;

    public void setMediaId(Long mediaId) 
    {
        this.mediaId = mediaId;
    }

    public Long getMediaId() 
    {
        return mediaId;
    }
    public void setBusinessId(Long businessId) 
    {
        this.businessId = businessId;
    }

    public Long getBusinessId() 
    {
        return businessId;
    }
    public void setMediaName(String mediaName) 
    {
        this.mediaName = mediaName;
    }

    public String getMediaName() 
    {
        return mediaName;
    }
    public void setMediaPath(String mediaPath) 
    {
        this.mediaPath = mediaPath;
    }

    public String getMediaPath() 
    {
        return mediaPath;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mediaId", getMediaId())
            .append("businessId", getBusinessId())
            .append("mediaName", getMediaName())
            .append("mediaPath", getMediaPath())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
