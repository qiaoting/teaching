package com.teaching.system.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.teaching.common.annotation.Excel;
import com.teaching.common.core.domain.BaseEntity;

/**
 * 课程对象 bus_schedule
 * 
 * @author qiaoting
 * @date 2025-01-12
 */
public class BusSchedule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程表 */
    private Long scheduleId;

    /** 课程表名称 */
    @Excel(name = "课程表名称")
    private String scheduleName;

    /** 状态（0正常 1关闭） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=关闭")
    private String status;

    /** 课明细信息 */
    private List<BusScheduleDetail> busScheduleDetailList;

    public void setScheduleId(Long scheduleId) 
    {
        this.scheduleId = scheduleId;
    }

    public Long getScheduleId() 
    {
        return scheduleId;
    }
    public void setScheduleName(String scheduleName) 
    {
        this.scheduleName = scheduleName;
    }

    public String getScheduleName() 
    {
        return scheduleName;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public List<BusScheduleDetail> getBusScheduleDetailList()
    {
        return busScheduleDetailList;
    }

    public void setBusScheduleDetailList(List<BusScheduleDetail> busScheduleDetailList)
    {
        this.busScheduleDetailList = busScheduleDetailList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("scheduleId", getScheduleId())
            .append("scheduleName", getScheduleName())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("busScheduleDetailList", getBusScheduleDetailList())
            .toString();
    }
}
