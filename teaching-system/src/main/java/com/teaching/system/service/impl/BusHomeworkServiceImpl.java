package com.teaching.system.service.impl;

import java.util.List;
import com.teaching.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teaching.system.mapper.BusHomeworkMapper;
import com.teaching.system.domain.BusHomework;
import com.teaching.system.service.IBusHomeworkService;

/**
 * 作业布置Service业务层处理
 * 
 * @author qiaoting
 * @date 2025-01-24
 */
@Service
public class BusHomeworkServiceImpl implements IBusHomeworkService 
{
    @Autowired
    private BusHomeworkMapper busHomeworkMapper;

    /**
     * 查询作业布置
     * 
     * @param homeworkId 作业布置主键
     * @return 作业布置
     */
    @Override
    public BusHomework selectBusHomeworkByHomeworkId(Long homeworkId)
    {
        return busHomeworkMapper.selectBusHomeworkByHomeworkId(homeworkId);
    }

    /**
     * 查询作业布置列表
     * 
     * @param busHomework 作业布置
     * @return 作业布置
     */
    @Override
    public List<BusHomework> selectBusHomeworkList(BusHomework busHomework)
    {
        return busHomeworkMapper.selectBusHomeworkList(busHomework);
    }

    /**
     * 新增作业布置
     * 
     * @param busHomework 作业布置
     * @return 结果
     */
    @Override
    public int insertBusHomework(BusHomework busHomework)
    {
        busHomework.setCreateTime(DateUtils.getNowDate());
        return busHomeworkMapper.insertBusHomework(busHomework);
    }

    /**
     * 修改作业布置
     * 
     * @param busHomework 作业布置
     * @return 结果
     */
    @Override
    public int updateBusHomework(BusHomework busHomework)
    {
        busHomework.setUpdateTime(DateUtils.getNowDate());
        return busHomeworkMapper.updateBusHomework(busHomework);
    }

    /**
     * 批量删除作业布置
     * 
     * @param homeworkIds 需要删除的作业布置主键
     * @return 结果
     */
    @Override
    public int deleteBusHomeworkByHomeworkIds(Long[] homeworkIds)
    {
        return busHomeworkMapper.deleteBusHomeworkByHomeworkIds(homeworkIds);
    }

    /**
     * 删除作业布置信息
     * 
     * @param homeworkId 作业布置主键
     * @return 结果
     */
    @Override
    public int deleteBusHomeworkByHomeworkId(Long homeworkId)
    {
        return busHomeworkMapper.deleteBusHomeworkByHomeworkId(homeworkId);
    }
}
