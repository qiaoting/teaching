package com.teaching.system.domain.vo;

import com.teaching.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 课明细对象 bus_schedule_detail
 * 
 * @author qiaoting
 * @date 2025-01-12
 */
public class CourseHourVo
{
    private static final long serialVersionUID = 1L;

    private Long scheduleId;

    /** 班级 */
    @Excel(name = "班级")
    private String deptName;

    /** 课程 */
    private Long courseId;

    /** 课程 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 教师 */
    private Long teacherId;

    /** 教师 */
    @Excel(name = "教师")
    private String teacherName;

    /** 周几 */
    private String dayWeek;

    /** 第几节课 */
    private String hourDay;

    @Excel(name = "第一次课")
    private String num_1;

    @Excel(name = "第二次课")
    private String num_2;

    @Excel(name = "第三次课")
    private String num_3;

    @Excel(name = "第四次课")
    private String num_4;

    @Excel(name = "第五次课")
    private String num_5;

    @Excel(name = "第六次课")
    private String num_6;

    @Excel(name = "第七次课")
    private String num_7;

    @Excel(name = "第八次课")
    private String num_8;

    @Excel(name = "第九次课")
    private String num_9;

    @Excel(name = "第十次课")
    private String num_10;

    @Excel(name = "第十一次课")
    private String num_11;

    @Excel(name = "第十二次课")
    private String num_12;

    @Excel(name = "第十三次课")
    private String num_13;

    @Excel(name = "第十四次课")
    private String num_14;

    @Excel(name = "第十五次课")
    private String num_15;


    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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

    public String getNum_1() {
        return num_1;
    }

    public void setNum_1(String num_1) {
        this.num_1 = num_1;
    }

    public String getNum_2() {
        return num_2;
    }

    public void setNum_2(String num_2) {
        this.num_2 = num_2;
    }

    public String getNum_3() {
        return num_3;
    }

    public void setNum_3(String num_3) {
        this.num_3 = num_3;
    }

    public String getNum_4() {
        return num_4;
    }

    public void setNum_4(String num_4) {
        this.num_4 = num_4;
    }

    public String getNum_5() {
        return num_5;
    }

    public void setNum_5(String num_5) {
        this.num_5 = num_5;
    }

    public String getNum_6() {
        return num_6;
    }

    public void setNum_6(String num_6) {
        this.num_6 = num_6;
    }

    public String getNum_7() {
        return num_7;
    }

    public void setNum_7(String num_7) {
        this.num_7 = num_7;
    }

    public String getNum_8() {
        return num_8;
    }

    public void setNum_8(String num_8) {
        this.num_8 = num_8;
    }

    public String getNum_9() {
        return num_9;
    }

    public void setNum_9(String num_9) {
        this.num_9 = num_9;
    }

    public String getNum_10() {
        return num_10;
    }

    public void setNum_10(String num_10) {
        this.num_10 = num_10;
    }

    public String getNum_11() {
        return num_11;
    }

    public void setNum_11(String num_11) {
        this.num_11 = num_11;
    }

    public String getNum_12() {
        return num_12;
    }

    public void setNum_12(String num_12) {
        this.num_12 = num_12;
    }

    public String getNum_13() {
        return num_13;
    }

    public void setNum_13(String num_13) {
        this.num_13 = num_13;
    }

    public String getNum_14() {
        return num_14;
    }

    public void setNum_14(String num_14) {
        this.num_14 = num_14;
    }

    public String getNum_15() {
        return num_15;
    }

    public void setNum_15(String num_15) {
        this.num_15 = num_15;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("scheduleId", getScheduleId())
            .append("courseId", getCourseId())
            .append("deptName", getDeptName())
            .append("teacherId", getTeacherId())
            .append("dayWeek", getDayWeek())
            .append("hourDay", getHourDay())
            .toString();
    }
}
