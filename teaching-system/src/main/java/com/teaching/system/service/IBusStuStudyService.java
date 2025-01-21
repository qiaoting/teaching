package com.teaching.system.service;

import java.util.List;
import com.teaching.system.domain.BusStuStudy;

/**
 * 学习情况记录Service接口
 * 
 * @author qiaoting
 * @date 2025-01-14
 */
public interface IBusStuStudyService 
{
    /**
     * 查询学习情况记录
     * 
     * @param id 学习情况记录主键
     * @return 学习情况记录
     */
    public BusStuStudy selectBusStuStudyById(Long id);

    /**
     * 查询学习情况记录列表
     * 
     * @param busStuStudy 学习情况记录
     * @return 学习情况记录集合
     */
    public List<BusStuStudy> selectBusStuStudyList(BusStuStudy busStuStudy);

    /**
     * 新增学习情况记录
     * 
     * @param busStuStudy 学习情况记录
     * @return 结果
     */
    public int insertBusStuStudy(BusStuStudy busStuStudy);

    /**
     * 修改学习情况记录
     * 
     * @param busStuStudy 学习情况记录
     * @return 结果
     */
    public int updateBusStuStudy(BusStuStudy busStuStudy);

    /**
     * 批量删除学习情况记录
     * 
     * @param ids 需要删除的学习情况记录主键集合
     * @return 结果
     */
    public int deleteBusStuStudyByIds(Long[] ids);

    /**
     * 删除学习情况记录信息
     * 
     * @param id 学习情况记录主键
     * @return 结果
     */
    public int deleteBusStuStudyById(Long id);
}
