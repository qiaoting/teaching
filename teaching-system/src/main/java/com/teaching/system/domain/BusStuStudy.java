package com.teaching.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teaching.common.annotation.Excel;
import com.teaching.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 学习情况记录对象 bus_stu_study
 * 
 * @author qiaoting
 * @date 2025-01-14
 */
public class BusStuStudy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 课程 */
    @Excel(name = "课程")
    private Long courseId;

    /** 部门 */
    @Excel(name = "部门")
    private Long deptId;

    private String nickName;

    private String deptName;

    /** 用户 */
    @Excel(name = "用户")
    private Long userId;

    /** 分类（0问题 1奖励） */
    @Excel(name = "分类", readConverterExp = "0=问题,1=奖励")
    private String scoreType;

    /** 时间点 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "时间点", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date realTime;

    /** 学习情况 */
    @Excel(name = "学习情况")
    private String content;

    /** 状态（0正常 1关闭） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=关闭")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setScoreType(String scoreType) 
    {
        this.scoreType = scoreType;
    }

    public String getScoreType() 
    {
        return scoreType;
    }
    public void setRealTime(Date realTime) 
    {
        this.realTime = realTime;
    }

    public Date getRealTime() 
    {
        return realTime;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
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
            .append("courseId", getCourseId())
            .append("deptId", getDeptId())
            .append("userId", getUserId())
            .append("scoreType", getScoreType())
            .append("realTime", getRealTime())
            .append("content", getContent())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
