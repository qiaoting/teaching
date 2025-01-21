package com.teaching.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.teaching.common.annotation.Excel;
import com.teaching.common.core.domain.BaseEntity;

/**
 * 成绩对象 bus_score
 * 
 * @author qiaoting
 * @date 2025-01-20
 */
public class BusScore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 考试批次 */
    @Excel(name = "考试批次")
    private Long testId;

    /** 学号 */
    @Excel(name = "学号")
    private Long studentId;

    /** 学号 */
    @Excel(name = "课程")
    private Long courseId;

    /** 成绩 */
    @Excel(name = "成绩")
    private Float score;

    /** 状态（0正常 1关闭） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=关闭")
    private String status;

    private String userName;
    private String nickName;
    private String courseName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTestId(Long testId) 
    {
        this.testId = testId;
    }

    public Long getTestId() 
    {
        return testId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("testId", getTestId())
            .append("studentId", getStudentId())
            .append("score", getScore())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
