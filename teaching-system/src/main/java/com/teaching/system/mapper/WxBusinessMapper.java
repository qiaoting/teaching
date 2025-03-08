package com.teaching.system.mapper;

import java.util.List;
import com.teaching.system.domain.WxBusiness;

/**
 * 服务Mapper接口
 * 
 * @author qiaoting
 * @date 2025-03-06
 */
public interface WxBusinessMapper 
{
    /**
     * 查询服务
     * 
     * @param businessId 服务主键
     * @return 服务
     */
    public WxBusiness selectWxBusinessByBusinessId(Long businessId);

    /**
     * 查询服务列表
     * 
     * @param wxBusiness 服务
     * @return 服务集合
     */
    public List<WxBusiness> selectWxBusinessList(WxBusiness wxBusiness);

    /**
     * 新增服务
     * 
     * @param wxBusiness 服务
     * @return 结果
     */
    public int insertWxBusiness(WxBusiness wxBusiness);

    /**
     * 修改服务
     * 
     * @param wxBusiness 服务
     * @return 结果
     */
    public int updateWxBusiness(WxBusiness wxBusiness);

    /**
     * 删除服务
     * 
     * @param businessId 服务主键
     * @return 结果
     */
    public int deleteWxBusinessByBusinessId(Long businessId);

    /**
     * 批量删除服务
     * 
     * @param businessIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWxBusinessByBusinessIds(Long[] businessIds);
}
