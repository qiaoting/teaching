package com.teaching.system.service.impl;

import com.teaching.common.utils.DateUtils;
import com.teaching.system.domain.BusTest;
import com.teaching.system.mapper.BusTestMapper;
import com.teaching.system.service.IBusTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 考试批次Service业务层处理
 * 
 * @author qiaoting
 * @date 2025-01-20
 */
@Service
public class BusTestServiceImpl implements IBusTestService 
{
    @Autowired
    private BusTestMapper busTestMapper;

    /**
     * 查询考试批次
     * 
     * @param testId 考试批次主键
     * @return 考试批次
     */
    @Override
    public BusTest selectBusTestByTestId(Long testId)
    {
        return busTestMapper.selectBusTestByTestId(testId);
    }

    /**
     * 查询考试批次列表
     * 
     * @param busTest 考试批次
     * @return 考试批次
     */
    @Override
    public List<BusTest> selectBusTestList(BusTest busTest)
    {
        return busTestMapper.selectBusTestList(busTest);
    }

    /**
     * 新增考试批次
     * 
     * @param busTest 考试批次
     * @return 结果
     */
    @Override
    public int insertBusTest(BusTest busTest)
    {
        busTest.setCreateTime(DateUtils.getNowDate());
        return busTestMapper.insertBusTest(busTest);
    }

    /**
     * 修改考试批次
     * 
     * @param busTest 考试批次
     * @return 结果
     */
    @Override
    public int updateBusTest(BusTest busTest)
    {
        busTest.setUpdateTime(DateUtils.getNowDate());
        return busTestMapper.updateBusTest(busTest);
    }

    /**
     * 批量删除考试批次
     * 
     * @param testIds 需要删除的考试批次主键
     * @return 结果
     */
    @Override
    public int deleteBusTestByTestIds(Long[] testIds)
    {
        return busTestMapper.deleteBusTestByTestIds(testIds);
    }

    /**
     * 删除考试批次信息
     * 
     * @param testId 考试批次主键
     * @return 结果
     */
    @Override
    public int deleteBusTestByTestId(Long testId)
    {
        return busTestMapper.deleteBusTestByTestId(testId);
    }
}
