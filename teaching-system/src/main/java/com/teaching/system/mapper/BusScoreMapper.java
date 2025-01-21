package com.teaching.system.mapper;

import com.teaching.system.domain.BusScore;

import java.util.List;

/**
 * 成绩Mapper接口
 * 
 * @author qiaoting
 * @date 2025-01-20
 */
public interface BusScoreMapper 
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
     * 删除成绩
     * 
     * @param id 成绩主键
     * @return 结果
     */
    public int deleteBusScoreById(Long id);

    /**
     * 批量删除成绩
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusScoreByIds(Long[] ids);

    public int batchBusScore(List<BusScore> busScoreList);

}
