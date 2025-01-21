package com.teaching.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.teaching.common.annotation.Excel;
import com.teaching.common.core.domain.BaseEntity;

/**
 * 课程对象 bus_course
 * 
 * @author qiaoting
 * @date 2025-01-19
 */
public class BusCourse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程 */
    private Long courseId;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 状态（0正常 1关闭） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=关闭")
    private String status;

    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }
    public void setCourseName(String courseName) 
    {
        this.courseName = courseName;
    }

    public String getCourseName() 
    {
        return courseName;
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
            .append("courseId", getCourseId())
            .append("courseName", getCourseName())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
