package com.teaching.system.mapper;

import java.util.List;
import com.teaching.system.domain.BusCourse;

/**
 * 课程Mapper接口
 * 
 * @author qiaoting
 * @date 2025-01-19
 */
public interface BusCourseMapper 
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
     * 删除课程
     * 
     * @param courseId 课程主键
     * @return 结果
     */
    public int deleteBusCourseByCourseId(Long courseId);

    /**
     * 批量删除课程
     * 
     * @param courseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusCourseByCourseIds(Long[] courseIds);
}
