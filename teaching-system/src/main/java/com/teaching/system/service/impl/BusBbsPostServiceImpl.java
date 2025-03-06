package com.teaching.system.service.impl;

import java.util.List;
import com.teaching.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teaching.system.mapper.BusBbsPostMapper;
import com.teaching.system.domain.BusBbsPost;
import com.teaching.system.service.IBusBbsPostService;

/**
 * 帖子Service业务层处理
 * 
 * @author qiaoting
 * @date 2025-02-23
 */
@Service
public class BusBbsPostServiceImpl implements IBusBbsPostService 
{
    @Autowired
    private BusBbsPostMapper busBbsPostMapper;

    /**
     * 查询帖子
     * 
     * @param postId 帖子主键
     * @return 帖子
     */
    @Override
    public BusBbsPost selectBusBbsPostByPostId(Long postId)
    {
        return busBbsPostMapper.selectBusBbsPostByPostId(postId);
    }

    /**
     * 查询帖子列表
     * 
     * @param busBbsPost 帖子
     * @return 帖子
     */
    @Override
    public List<BusBbsPost> selectBusBbsPostList(BusBbsPost busBbsPost)
    {
        return busBbsPostMapper.selectBusBbsPostList(busBbsPost);
    }

    /**
     * 新增帖子
     * 
     * @param busBbsPost 帖子
     * @return 结果
     */
    @Override
    public int insertBusBbsPost(BusBbsPost busBbsPost)
    {
        busBbsPost.setCreateTime(DateUtils.getNowDate());
        return busBbsPostMapper.insertBusBbsPost(busBbsPost);
    }

    /**
     * 修改帖子
     * 
     * @param busBbsPost 帖子
     * @return 结果
     */
    @Override
    public int updateBusBbsPost(BusBbsPost busBbsPost)
    {
        busBbsPost.setUpdateTime(DateUtils.getNowDate());
        return busBbsPostMapper.updateBusBbsPost(busBbsPost);
    }

    /**
     * 批量删除帖子
     * 
     * @param postIds 需要删除的帖子主键
     * @return 结果
     */
    @Override
    public int deleteBusBbsPostByPostIds(Long[] postIds)
    {
        return busBbsPostMapper.deleteBusBbsPostByPostIds(postIds);
    }

    /**
     * 删除帖子信息
     * 
     * @param postId 帖子主键
     * @return 结果
     */
    @Override
    public int deleteBusBbsPostByPostId(Long postId)
    {
        return busBbsPostMapper.deleteBusBbsPostByPostId(postId);
    }
}
