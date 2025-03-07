package com.teaching.system.service;

import java.util.List;
import com.teaching.system.domain.BusCourse;

/**
 * 课程Service接口
 * 
 * @author qiaoting
 * @date 2025-01-19
 */
public interface IBusCourseService 
{
    /**
     * 查询课程
     * 
     * @param courseId 课程主键
     * @return 课程
     */
    public BusCourse selectBusCourseByCourseId(Long courseId);

    /**
     * 查询课程列表
     * 
     * @param busCourse 课程
     * @return 课程集合
     */
    public List<BusCourse> selectBusCourseList(BusCourse busCourse);

    /**
     * 新增课程
     * 
     * @param busCourse 课程
     * @return 结果
     */
    public int insertBusCourse(BusCourse busCourse);

    /**
     * 修改课程
     * 
     * @param busCourse 课程
     * @return 结果
     */
    public int updateBusCourse(BusCourse busCourse);

    /**
     * 批量删除课程
     * 
     * @param courseIds 需要删除的课程主键集合
     * @return 结果
     */
    public int deleteBusCourseByCourseIds(Long[] courseIds);

    /**
     * 删除课程信息
     * 
     * @param courseId 课程主键
     * @return 结果
     */
    public int deleteBusCourseByCourseId(Long courseId);
}
