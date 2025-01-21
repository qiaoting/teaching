package com.teaching.system.service.impl;

import java.util.List;
import com.teaching.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.teaching.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.teaching.system.domain.BusScheduleDetail;
import com.teaching.system.mapper.BusScheduleMapper;
import com.teaching.system.domain.BusSchedule;
import com.teaching.system.service.IBusScheduleService;

/**
 * 课程Service业务层处理
 * 
 * @author qiaoting
 * @date 2025-01-12
 */
@Service
public class BusScheduleServiceImpl implements IBusScheduleService 
{
    @Autowired
    private BusScheduleMapper busScheduleMapper;

    /**
     * 查询课程
     * 
     * @param scheduleId 课程主键
     * @return 课程
     */
    @Override
    public BusSchedule selectBusScheduleByScheduleId(Long scheduleId)
    {
        return busScheduleMapper.selectBusScheduleByScheduleId(scheduleId);
    }

    @Override
    public List<BusSchedule> selectBusScheduleByScheduleIds(Long[] scheduleIds)
    {
        return busScheduleMapper.selectBusScheduleByScheduleIds(scheduleIds);
    }

    /**
     * 查询课程列表
     * 
     * @param busSchedule 课程
     * @return 课程
     */
    @Override
    public List<BusSchedule> selectBusScheduleList(BusSchedule busSchedule)
    {
        return busScheduleMapper.selectBusScheduleList(busSchedule);
    }

    /**
     * 新增课程
     * 
     * @param busSchedule 课程
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBusSchedule(BusSchedule busSchedule)
    {
        busSchedule.setCreateTime(DateUtils.getNowDate());
        int rows = busScheduleMapper.insertBusSchedule(busSchedule);
        insertBusScheduleDetail(busSchedule);
        return rows;
    }

    /**
     * 修改课程
     * 
     * @param busSchedule 课程
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBusSchedule(BusSchedule busSchedule)
    {
        busSchedule.setUpdateTime(DateUtils.getNowDate());
        busScheduleMapper.deleteBusScheduleDetailByScheduleId(busSchedule.getScheduleId());
        insertBusScheduleDetail(busSchedule);
        return busScheduleMapper.updateBusSchedule(busSchedule);
    }

    /**
     * 批量删除课程
     * 
     * @param scheduleIds 需要删除的课程主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBusScheduleByScheduleIds(Long[] scheduleIds)
    {
        busScheduleMapper.deleteBusScheduleDetailByScheduleIds(scheduleIds);
        return busScheduleMapper.deleteBusScheduleByScheduleIds(scheduleIds);
    }

    /**
     * 删除课程信息
     * 
     * @param scheduleId 课程主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBusScheduleByScheduleId(Long scheduleId)
    {
        busScheduleMapper.deleteBusScheduleDetailByScheduleId(scheduleId);
        return busScheduleMapper.deleteBusScheduleByScheduleId(scheduleId);
    }

    /**
     * 新增课明细信息
     * 
     * @param busSchedule 课程对象
     */
    public void insertBusScheduleDetail(BusSchedule busSchedule)
    {
        List<BusScheduleDetail> busScheduleDetailList = busSchedule.getBusScheduleDetailList();
        Long scheduleId = busSchedule.getScheduleId();
        if (StringUtils.isNotNull(busScheduleDetailList))
        {
            List<BusScheduleDetail> list = new ArrayList<BusScheduleDetail>();
            for (BusScheduleDetail busScheduleDetail : busScheduleDetailList)
            {
                busScheduleDetail.setScheduleId(scheduleId);
                list.add(busScheduleDetail);
            }
            if (list.size() > 0)
            {
                busScheduleMapper.batchBusScheduleDetail(list);
            }
        }
    }
}
