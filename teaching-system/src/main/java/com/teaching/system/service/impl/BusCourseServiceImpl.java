package com.teaching.system.service.impl;

import java.util.List;
import com.teaching.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teaching.system.mapper.BusCourseMapper;
import com.teaching.system.domain.BusCourse;
import com.teaching.system.service.IBusCourseService;

/**
 * 课程Service业务层处理
 * 
 * @author qiaoting
 * @date 2025-01-19
 */
@Service
public class BusCourseServiceImpl implements IBusCourseService 
{
    @Autowired
    private BusCourseMapper busCourseMapper;

    /**
     * 查询课程
     * 
     * @param courseId 课程主键
     * @return 课程
     */
    @Override
    public BusCourse selectBusCourseByCourseId(Long courseId)
    {
        return busCourseMapper.selectBusCourseByCourseId(courseId);
    }

    /**
     * 查询课程列表
     * 
     * @param busCourse 课程
     * @return 课程
     */
    @Override
    public List<BusCourse> selectBusCourseList(BusCourse busCourse)
    {
        return busCourseMapper.selectBusCourseList(busCourse);
    }

    /**
     * 新增课程
     * 
     * @param busCourse 课程
     * @return 结果
     */
    @Override
    public int insertBusCourse(BusCourse busCourse)
    {
        busCourse.setCreateTime(DateUtils.getNowDate());
        return busCourseMapper.insertBusCourse(busCourse);
    }

    /**
     * 修改课程
     * 
     * @param busCourse 课程
     * @return 结果
     */
    @Override
    public int updateBusCourse(BusCourse busCourse)
    {
        busCourse.setUpdateTime(DateUtils.getNowDate());
        return busCourseMapper.updateBusCourse(busCourse);
    }

    /**
     * 批量删除课程
     * 
     * @param courseIds 需要删除的课程主键
     * @return 结果
     */
    @Override
    public int deleteBusCourseByCourseIds(Long[] courseIds)
    {
        return busCourseMapper.deleteBusCourseByCourseIds(courseIds);
    }

    /**
     * 删除课程信息
     * 
     * @param courseId 课程主键
     * @return 结果
     */
    @Override
    public int deleteBusCourseByCourseId(Long courseId)
    {
        return busCourseMapper.deleteBusCourseByCourseId(courseId);
    }
}
