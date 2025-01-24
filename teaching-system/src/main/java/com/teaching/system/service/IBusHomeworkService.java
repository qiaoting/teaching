package com.teaching.system.service;

import java.util.List;
import com.teaching.system.domain.BusHomework;

/**
 * 作业布置Service接口
 * 
 * @author qiaoting
 * @date 2025-01-24
 */
public interface IBusHomeworkService 
{
    /**
     * 查询作业布置
     * 
     * @param homeworkId 作业布置主键
     * @return 作业布置
     */
    public BusHomework selectBusHomeworkByHomeworkId(Long homeworkId);

    /**
     * 查询作业布置列表
     * 
     * @param busHomework 作业布置
     * @return 作业布置集合
     */
    public List<BusHomework> selectBusHomeworkList(BusHomework busHomework);

    /**
     * 新增作业布置
     * 
     * @param busHomework 作业布置
     * @return 结果
     */
    public int insertBusHomework(BusHomework busHomework);

    /**
     * 修改作业布置
     * 
     * @param busHomework 作业布置
     * @return 结果
     */
    public int updateBusHomework(BusHomework busHomework);

    /**
     * 批量删除作业布置
     * 
     * @param homeworkIds 需要删除的作业布置主键集合
     * @return 结果
     */
    public int deleteBusHomeworkByHomeworkIds(Long[] homeworkIds);

    /**
     * 删除作业布置信息
     * 
     * @param homeworkId 作业布置主键
     * @return 结果
     */
    public int deleteBusHomeworkByHomeworkId(Long homeworkId);
}
