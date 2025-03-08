package com.teaching.system.service.impl;

import java.util.List;
import com.teaching.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teaching.system.mapper.WxBusinessMapper;
import com.teaching.system.domain.WxBusiness;
import com.teaching.system.service.IWxBusinessService;

/**
 * 服务Service业务层处理
 * 
 * @author qiaoting
 * @date 2025-03-06
 */
@Service
public class WxBusinessServiceImpl implements IWxBusinessService 
{
    @Autowired
    private WxBusinessMapper wxBusinessMapper;

    /**
     * 查询服务
     * 
     * @param businessId 服务主键
     * @return 服务
     */
    @Override
    public WxBusiness selectWxBusinessByBusinessId(Long businessId)
    {
        return wxBusinessMapper.selectWxBusinessByBusinessId(businessId);
    }

    /**
     * 查询服务列表
     * 
     * @param wxBusiness 服务
     * @return 服务
     */
    @Override
    public List<WxBusiness> selectWxBusinessList(WxBusiness wxBusiness)
    {
        return wxBusinessMapper.selectWxBusinessList(wxBusiness);
    }

    /**
     * 新增服务
     * 
     * @param wxBusiness 服务
     * @return 结果
     */
    @Override
    public int insertWxBusiness(WxBusiness wxBusiness)
    {
        wxBusiness.setCreateTime(DateUtils.getNowDate());
        return wxBusinessMapper.insertWxBusiness(wxBusiness);
    }

    /**
     * 修改服务
     * 
     * @param wxBusiness 服务
     * @return 结果
     */
    @Override
    public int updateWxBusiness(WxBusiness wxBusiness)
    {
        wxBusiness.setUpdateTime(DateUtils.getNowDate());
        return wxBusinessMapper.updateWxBusiness(wxBusiness);
    }

    /**
     * 批量删除服务
     * 
     * @param businessIds 需要删除的服务主键
     * @return 结果
     */
    @Override
    public int deleteWxBusinessByBusinessIds(Long[] businessIds)
    {
        return wxBusinessMapper.deleteWxBusinessByBusinessIds(businessIds);
    }

    /**
     * 删除服务信息
     * 
     * @param businessId 服务主键
     * @return 结果
     */
    @Override
    public int deleteWxBusinessByBusinessId(Long businessId)
    {
        return wxBusinessMapper.deleteWxBusinessByBusinessId(businessId);
    }
}
