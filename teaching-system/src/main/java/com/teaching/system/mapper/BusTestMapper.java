package com.teaching.system.mapper;

import java.util.List;
import com.teaching.system.domain.BusTest;

/**
 * 考试批次Mapper接口
 * 
 * @author qiaoting
 * @date 2025-01-20
 */
public interface BusTestMapper 
{
    /**
     * 查询考试批次
     * 
     * @param testId 考试批次主键
     * @return 考试批次
     */
    public BusTest selectBusTestByTestId(Long testId);

    /**
     * 查询考试批次列表
     * 
     * @param busTest 考试批次
     * @return 考试批次集合
     */
    public List<BusTest> selectBusTestList(BusTest busTest);

    /**
     * 新增考试批次
     * 
     * @param busTest 考试批次
     * @return 结果
     */
    public int insertBusTest(BusTest busTest);

    /**
     * 修改考试批次
     * 
     * @param busTest 考试批次
     * @return 结果
     */
    public int updateBusTest(BusTest busTest);

    /**
     * 删除考试批次
     * 
     * @param testId 考试批次主键
     * @return 结果
     */
    public int deleteBusTestByTestId(Long testId);

    /**
     * 批量删除考试批次
     * 
     * @param testIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusTestByTestIds(Long[] testIds);
}
