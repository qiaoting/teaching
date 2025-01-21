package com.teaching.system.service.impl;

import com.teaching.common.utils.DateUtils;
import com.teaching.system.domain.BusScore;
import com.teaching.system.mapper.BusScoreMapper;
import com.teaching.system.service.IBusScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 成绩Service业务层处理
 * 
 * @author qiaoting
 * @date 2025-01-20
 */
@Service
public class BusScoreServiceImpl implements IBusScoreService 
{
    @Autowired
    private BusScoreMapper busScoreMapper;

    /**
     * 查询成绩
     * 
     * @param id 成绩主键
     * @return 成绩
     */
    @Override
    public BusScore selectBusScoreById(Long id)
    {
        return busScoreMapper.selectBusScoreById(id);
    }

    /**
     * 查询成绩列表
     * 
     * @param busScore 成绩
     * @return 成绩
     */
    @Override
    public List<BusScore> selectBusScoreList(BusScore busScore)
    {
        return busScoreMapper.selectBusScoreList(busScore);
    }

    /**
     * 新增成绩
     * 
     * @param busScore 成绩
     * @return 结果
     */
    @Override
    public int insertBusScore(BusScore busScore)
    {
        busScore.setCreateTime(DateUtils.getNowDate());
        return busScoreMapper.insertBusScore(busScore);
    }

    /**
     * 修改成绩
     * 
     * @param busScore 成绩
     * @return 结果
     */
    @Override
    public int updateBusScore(BusScore busScore)
    {
        busScore.setUpdateTime(DateUtils.getNowDate());
        return busScoreMapper.updateBusScore(busScore);
    }

    /**
     * 批量删除成绩
     * 
     * @param ids 需要删除的成绩主键
     * @return 结果
     */
    @Override
    public int deleteBusScoreByIds(Long[] ids)
    {
        return busScoreMapper.deleteBusScoreByIds(ids);
    }

    /**
     * 删除成绩信息
     * 
     * @param id 成绩主键
     * @return 结果
     */
    @Override
    public int deleteBusScoreById(Long id)
    {
        return busScoreMapper.deleteBusScoreById(id);
    }

    @Override
    public int batchBusScore(List<BusScore> busScoreList) {
        return busScoreMapper.batchBusScore(busScoreList);
    }
}
