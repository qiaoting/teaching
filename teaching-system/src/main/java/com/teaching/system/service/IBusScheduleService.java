package com.teaching.system.service;

import com.teaching.system.domain.BusSchedule;

import java.util.List;

/**
 * 课程Service接口
 * 
 * @author qiaoting
 * @date 2025-01-12
 */
public interface IBusScheduleService 
{
    /**
     * 查询课程
     * 
     * @param scheduleId 课程主键
     * @return 课程
     */
    public BusSchedule selectBusScheduleByScheduleId(Long scheduleId);

    public List<BusSchedule> selectBusScheduleByScheduleIds(Long[] scheduleIds);

    /**
     * 查询课程列表
     * 
     * @param busSchedule 课程
     * @return 课程集合
     */
    public List<BusSchedule> selectBusScheduleList(BusSchedule busSchedule);

    /**
     * 新增课程
     * 
     * @param busSchedule 课程
     * @return 结果
     */
    public int insertBusSchedule(BusSchedule busSchedule);

    /**
     * 修改课程
     * 
     * @param busSchedule 课程
     * @return 结果
     */
    public int updateBusSchedule(BusSchedule busSchedule);

    /**
     * 批量删除课程
     * 
     * @param scheduleIds 需要删除的课程主键集合
     * @return 结果
     */
    public int deleteBusScheduleByScheduleIds(Long[] scheduleIds);

    /**
     * 删除课程信息
     * 
     * @param scheduleId 课程主键
     * @return 结果
     */
    public int deleteBusScheduleByScheduleId(Long scheduleId);
}
