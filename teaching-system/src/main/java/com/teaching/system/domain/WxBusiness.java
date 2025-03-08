package com.teaching.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.teaching.common.annotation.Excel;
import com.teaching.common.core.domain.BaseEntity;

/**
 * 服务对象 wx_business
 * 
 * @author qiaoting
 * @date 2025-03-06
 */
public class WxBusiness extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商业ID */
    private Long businessId;

    /** 商业编码 */
    @Excel(name = "商业编码")
    private String businessCode;

    /** 商业名称 */
    @Excel(name = "商业名称")
    private String businessName;

    /** 状态（0正常 1关闭） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=关闭")
    private String status;

    public void setBusinessId(Long businessId) 
    {
        this.businessId = businessId;
    }

    public Long getBusinessId() 
    {
        return businessId;
    }
    public void setBusinessCode(String businessCode) 
    {
        this.businessCode = businessCode;
    }

    public String getBusinessCode() 
    {
        return businessCode;
    }
    public void setBusinessName(String businessName) 
    {
        this.businessName = businessName;
    }

    public String getBusinessName() 
    {
        return businessName;
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
            .append("businessId", getBusinessId())
            .append("businessCode", getBusinessCode())
            .append("businessName", getBusinessName())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
