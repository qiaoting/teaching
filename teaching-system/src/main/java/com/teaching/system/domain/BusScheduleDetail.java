package com.teaching.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.teaching.common.annotation.Excel;
import com.teaching.common.core.domain.BaseEntity;

/**
 * 课明细对象 bus_schedule_detail
 * 
 * @author qiaoting
 * @date 2025-01-12
 */
public class BusScheduleDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 课程表 */
    @Excel(name = "课程表")
    private Long scheduleId;

    /** 课程 */
    @Excel(name = "课程")
    private Long courseId;

    /** 班级 */
    @Excel(name = "班级", dictType="bus_class")
    private Long deptId;

    /** 教师 */
    @Excel(name = "教师", dictType="bus_teacher")
    private Long teacherId;

    /** 周几 */
    @Excel(name = "周几", dictType="bus_day_week")
    private String dayWeek;

    /** 第几节课 */
    @Excel(name = "第几节课", dictType="bus_hour_day")
    private String hourDay;

    /** 状态（0正常 1关闭） */
//    @Excel(name = "状态", readConverterExp = "0=正常,1=关闭")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setScheduleId(Long scheduleId) 
    {
        this.scheduleId = scheduleId;
    }

    public Long getScheduleId() 
    {
        return scheduleId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public void setDayWeek(String dayWeek)
    {
        this.dayWeek = dayWeek;
    }

    public String getDayWeek()
    {
        return dayWeek;
    }
    public void setHourDay(String hourDay) 
    {
        this.hourDay = hourDay;
    }

    public String getHourDay() 
    {
        return hourDay;
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
            .append("id", getId())
            .append("scheduleId", getScheduleId())
            .append("courseId", getCourseId())
            .append("classId", getDeptId())
            .append("teacherId", getTeacherId())
            .append("dayWeek", getDayWeek())
            .append("hourDay", getHourDay())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
