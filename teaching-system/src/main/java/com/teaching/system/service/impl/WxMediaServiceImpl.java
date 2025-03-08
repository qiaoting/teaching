package com.teaching.system.service.impl;

import java.util.List;
import com.teaching.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teaching.system.mapper.WxMediaMapper;
import com.teaching.system.domain.WxMedia;
import com.teaching.system.service.IWxMediaService;

/**
 * 微信媒体Service业务层处理
 * 
 * @author qiaoting
 * @date 2025-03-06
 */
@Service
public class WxMediaServiceImpl implements IWxMediaService 
{
    @Autowired
    private WxMediaMapper wxMediaMapper;

    /**
     * 查询微信媒体
     * 
     * @param mediaId 微信媒体主键
     * @return 微信媒体
     */
    @Override
    public WxMedia selectWxMediaByMediaId(Long mediaId)
    {
        return wxMediaMapper.selectWxMediaByMediaId(mediaId);
    }

    /**
     * 查询微信媒体列表
     * 
     * @param wxMedia 微信媒体
     * @return 微信媒体
     */
    @Override
    public List<WxMedia> selectWxMediaList(WxMedia wxMedia)
    {
        return wxMediaMapper.selectWxMediaList(wxMedia);
    }

    /**
     * 新增微信媒体
     * 
     * @param wxMedia 微信媒体
     * @return 结果
     */
    @Override
    public int insertWxMedia(WxMedia wxMedia)
    {
        wxMedia.setCreateTime(DateUtils.getNowDate());
        return wxMediaMapper.insertWxMedia(wxMedia);
    }

    /**
     * 修改微信媒体
     * 
     * @param wxMedia 微信媒体
     * @return 结果
     */
    @Override
    public int updateWxMedia(WxMedia wxMedia)
    {
        wxMedia.setUpdateTime(DateUtils.getNowDate());
        return wxMediaMapper.updateWxMedia(wxMedia);
    }

    /**
     * 批量删除微信媒体
     * 
     * @param mediaIds 需要删除的微信媒体主键
     * @return 结果
     */
    @Override
    public int deleteWxMediaByMediaIds(Long[] mediaIds)
    {
        return wxMediaMapper.deleteWxMediaByMediaIds(mediaIds);
    }

    /**
     * 删除微信媒体信息
     * 
     * @param mediaId 微信媒体主键
     * @return 结果
     */
    @Override
    public int deleteWxMediaByMediaId(Long mediaId)
    {
        return wxMediaMapper.deleteWxMediaByMediaId(mediaId);
    }
}
