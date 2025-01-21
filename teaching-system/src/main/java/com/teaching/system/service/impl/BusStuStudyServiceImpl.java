package com.teaching.system.service.impl;

import com.teaching.common.utils.DateUtils;
import com.teaching.system.domain.BusStuStudy;
import com.teaching.system.mapper.BusStuStudyMapper;
import com.teaching.system.service.IBusStuStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学习情况记录Service业务层处理
 * 
 * @author qiaoting
 * @date 2025-01-14
 */
@Service
public class BusStuStudyServiceImpl implements IBusStuStudyService 
{
    @Autowired
    private BusStuStudyMapper busStuStudyMapper;

    /**
     * 查询学习情况记录
     * 
     * @param id 学习情况记录主键
     * @return 学习情况记录
     */
    @Override
    public BusStuStudy selectBusStuStudyById(Long id)
    {
        return busStuStudyMapper.selectBusStuStudyById(id);
    }

    /**
     * 查询学习情况记录列表
     * 
     * @param busStuStudy 学习情况记录
     * @return 学习情况记录
     */
    @Override
    public List<BusStuStudy> selectBusStuStudyList(BusStuStudy busStuStudy)
    {
        return busStuStudyMapper.selectBusStuStudyList(busStuStudy);
    }

    /**
     * 新增学习情况记录
     * 
     * @param busStuStudy 学习情况记录
     * @return 结果
     */
    @Override
    public int insertBusStuStudy(BusStuStudy busStuStudy)
    {
        busStuStudy.setCreateTime(DateUtils.getNowDate());
        return busStuStudyMapper.insertBusStuStudy(busStuStudy);
    }

    /**
     * 修改学习情况记录
     * 
     * @param busStuStudy 学习情况记录
     * @return 结果
     */
    @Override
    public int updateBusStuStudy(BusStuStudy busStuStudy)
    {
        busStuStudy.setUpdateTime(DateUtils.getNowDate());
        return busStuStudyMapper.updateBusStuStudy(busStuStudy);
    }

    /**
     * 批量删除学习情况记录
     * 
     * @param ids 需要删除的学习情况记录主键
     * @return 结果
     */
    @Override
    public int deleteBusStuStudyByIds(Long[] ids)
    {
        return busStuStudyMapper.deleteBusStuStudyByIds(ids);
    }

    /**
     * 删除学习情况记录信息
     * 
     * @param id 学习情况记录主键
     * @return 结果
     */
    @Override
    public int deleteBusStuStudyById(Long id)
    {
        return busStuStudyMapper.deleteBusStuStudyById(id);
    }
}
