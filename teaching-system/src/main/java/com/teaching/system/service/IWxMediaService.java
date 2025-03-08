package com.teaching.system.service;

import java.util.List;
import com.teaching.system.domain.WxMedia;

/**
 * 微信媒体Service接口
 * 
 * @author qiaoting
 * @date 2025-03-06
 */
public interface IWxMediaService 
{
    /**
     * 查询微信媒体
     * 
     * @param mediaId 微信媒体主键
     * @return 微信媒体
     */
    public WxMedia selectWxMediaByMediaId(Long mediaId);

    /**
     * 查询微信媒体列表
     * 
     * @param wxMedia 微信媒体
     * @return 微信媒体集合
     */
    public List<WxMedia> selectWxMediaList(WxMedia wxMedia);

    /**
     * 新增微信媒体
     * 
     * @param wxMedia 微信媒体
     * @return 结果
     */
    public int insertWxMedia(WxMedia wxMedia);

    /**
     * 修改微信媒体
     * 
     * @param wxMedia 微信媒体
     * @return 结果
     */
    public int updateWxMedia(WxMedia wxMedia);

    /**
     * 批量删除微信媒体
     * 
     * @param mediaIds 需要删除的微信媒体主键集合
     * @return 结果
     */
    public int deleteWxMediaByMediaIds(Long[] mediaIds);

    /**
     * 删除微信媒体信息
     * 
     * @param mediaId 微信媒体主键
     * @return 结果
     */
    public int deleteWxMediaByMediaId(Long mediaId);
}
