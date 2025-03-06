package com.teaching.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.teaching.common.annotation.Excel;
import com.teaching.common.core.domain.BaseEntity;

/**
 * 作业布置对象 bus_homework
 * 
 * @author qiaoting
 * @date 2025-01-24
 */
public class BusHomework extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 作业ID */
    private Long homeworkId;

    /** 班级 */
    @Excel(name = "班级")
    private Long deptId;

    private String deptName;

    /** 课程 */
    @Excel(name = "课程")
    private Long courseId;

    /** 作业标题 */
    @Excel(name = "作业标题")
    private String homeworkTitle;

    /** 作业类型（1通知 2公告） */
    @Excel(name = "作业类型", readConverterExp = "1=通知,2=公告")
    private String homeworkType;

    /** 作业内容 */
    @Excel(name = "作业内容")
    private String homeworkContent;

    /** 作业状态（0正常 1关闭） */
    @Excel(name = "作业状态", readConverterExp = "0=正常,1=关闭")
    private String status;

    public void setHomeworkId(Long homeworkId) 
    {
        this.homeworkId = homeworkId;
    }

    public Long getHomeworkId() 
    {
        return homeworkId;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setCourseId(Long courseId)
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }
    public void setHomeworkTitle(String homeworkTitle) 
    {
        this.homeworkTitle = homeworkTitle;
    }

    public String getHomeworkTitle() 
    {
        return homeworkTitle;
    }
    public void setHomeworkType(String homeworkType) 
    {
        this.homeworkType = homeworkType;
    }

    public String getHomeworkType() 
    {
        return homeworkType;
    }
    public void setHomeworkContent(String homeworkContent) 
    {
        this.homeworkContent = homeworkContent;
    }

    public String getHomeworkContent() 
    {
        return homeworkContent;
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
            .append("homeworkId", getHomeworkId())
            .append("deptId", getDeptId())
            .append("courseId", getCourseId())
            .append("homeworkTitle", getHomeworkTitle())
            .append("homeworkType", getHomeworkType())
            .append("homeworkContent", getHomeworkContent())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
