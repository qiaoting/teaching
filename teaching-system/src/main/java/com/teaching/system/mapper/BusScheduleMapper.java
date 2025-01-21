package com.teaching.system.mapper;

import com.teaching.system.domain.BusSchedule;
import com.teaching.system.domain.BusScheduleDetail;

import java.util.List;

/**
 * 课程Mapper接口
 * 
 * @author qiaoting
 * @date 2025-01-12
 */
public interface BusScheduleMapper 
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
     * 删除课程
     * 
     * @param scheduleId 课程主键
     * @return 结果
     */
    public int deleteBusScheduleByScheduleId(Long scheduleId);

    /**
     * 批量删除课程
     * 
     * @param scheduleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusScheduleByScheduleIds(Long[] scheduleIds);

    /**
     * 批量删除课明细
     * 
     * @param scheduleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusScheduleDetailByScheduleIds(Long[] scheduleIds);
    
    /**
     * 批量新增课明细
     * 
     * @param busScheduleDetailList 课明细列表
     * @return 结果
     */
    public int batchBusScheduleDetail(List<BusScheduleDetail> busScheduleDetailList);
    

    /**
     * 通过课程主键删除课明细信息
     * 
     * @param scheduleId 课程ID
     * @return 结果
     */
    public int deleteBusScheduleDetailByScheduleId(Long scheduleId);
}
