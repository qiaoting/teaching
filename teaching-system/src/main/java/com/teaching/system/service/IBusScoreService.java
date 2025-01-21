package com.teaching.system.service;

import com.teaching.system.domain.BusScore;

import java.util.List;

/**
 * 成绩Service接口
 * 
 * @author qiaoting
 * @date 2025-01-20
 */
public interface IBusScoreService 
{
    /**
     * 查询成绩
     * 
     * @param id 成绩主键
     * @return 成绩
     */
    public BusScore selectBusScoreById(Long id);

    /**
     * 查询成绩列表
     * 
     * @param busScore 成绩
     * @return 成绩集合
     */
    public List<BusScore> selectBusScoreList(BusScore busScore);

    /**
     * 新增成绩
     * 
     * @param busScore 成绩
     * @return 结果
     */
    public int insertBusScore(BusScore busScore);

    /**
     * 修改成绩
     * 
     * @param busScore 成绩
     * @return 结果
     */
    public int updateBusScore(BusScore busScore);

    /**
     * 批量删除成绩
     * 
     * @param ids 需要删除的成绩主键集合
     * @return 结果
     */
    public int deleteBusScoreByIds(Long[] ids);

    /**
     * 删除成绩信息
     * 
     * @param id 成绩主键
     * @return 结果
     */
    public int deleteBusScoreById(Long id);

    public int batchBusScore(List<BusScore> busScoreList);

}
