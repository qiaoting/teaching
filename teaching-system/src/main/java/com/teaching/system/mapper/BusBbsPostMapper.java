package com.teaching.system.mapper;

import java.util.List;
import com.teaching.system.domain.BusBbsPost;

/**
 * 帖子Mapper接口
 * 
 * @author qiaoting
 * @date 2025-02-23
 */
public interface BusBbsPostMapper 
{
    /**
     * 查询帖子
     * 
     * @param postId 帖子主键
     * @return 帖子
     */
    public BusBbsPost selectBusBbsPostByPostId(Long postId);

    /**
     * 查询帖子列表
     * 
     * @param busBbsPost 帖子
     * @return 帖子集合
     */
    public List<BusBbsPost> selectBusBbsPostList(BusBbsPost busBbsPost);

    /**
     * 新增帖子
     * 
     * @param busBbsPost 帖子
     * @return 结果
     */
    public int insertBusBbsPost(BusBbsPost busBbsPost);

    /**
     * 修改帖子
     * 
     * @param busBbsPost 帖子
     * @return 结果
     */
    public int updateBusBbsPost(BusBbsPost busBbsPost);

    /**
     * 删除帖子
     * 
     * @param postId 帖子主键
     * @return 结果
     */
    public int deleteBusBbsPostByPostId(Long postId);

    /**
     * 批量删除帖子
     * 
     * @param postIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusBbsPostByPostIds(Long[] postIds);
}
