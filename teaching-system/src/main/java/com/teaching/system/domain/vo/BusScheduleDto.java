package com.teaching.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teaching.common.core.domain.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 课程对象 bus_schedule
 * 
 * @author qiaoting
 * @date 2025-01-12
 */
public class BusScheduleDto extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long[] scheduleIds;

    private String scheduleName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDay;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDay;

    private List<String> exludeDays;

    public Long[] getScheduleIds() {
        return scheduleIds;
    }

    public void setScheduleIds(Long[] scheduleIds) {
        this.scheduleIds = scheduleIds;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    public List<String> getExludeDays() {
        return exludeDays;
    }

    public void setExludeDays(List<String> exludeDays) {
        this.exludeDays = exludeDays;
    }
}
