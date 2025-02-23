package com.teaching.web.controller.bbs;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.teaching.common.annotation.Log;
import com.teaching.common.core.controller.BaseController;
import com.teaching.common.core.domain.AjaxResult;
import com.teaching.common.enums.BusinessType;
import com.teaching.system.domain.BusBbsPost;
import com.teaching.system.service.IBusBbsPostService;
import com.teaching.common.utils.poi.ExcelUtil;
import com.teaching.common.core.page.TableDataInfo;

/**
 * 帖子Controller
 * 
 * @author qiaoting
 * @date 2025-02-23
 */
@RestController
@RequestMapping("/bbs/post")
public class BusBbsPostController extends BaseController
{
    @Autowired
    private IBusBbsPostService busBbsPostService;

    /**
     * 查询帖子列表
     */
    @PreAuthorize("@ss.hasPermi('bbs:post:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusBbsPost busBbsPost)
    {
        startPage();
        List<BusBbsPost> list = busBbsPostService.selectBusBbsPostList(busBbsPost);
        return getDataTable(list);
    }

    /**
     * 导出帖子列表
     */
    @PreAuthorize("@ss.hasPermi('bbs:post:export')")
    @Log(title = "帖子", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusBbsPost busBbsPost)
    {
        List<BusBbsPost> list = busBbsPostService.selectBusBbsPostList(busBbsPost);
        ExcelUtil<BusBbsPost> util = new ExcelUtil<BusBbsPost>(BusBbsPost.class);
        util.exportExcel(response, list, "帖子数据");
    }

    /**
     * 获取帖子详细信息
     */
    @PreAuthorize("@ss.hasPermi('bbs:post:query')")
    @GetMapping(value = "/{postId}")
    public AjaxResult getInfo(@PathVariable("postId") Long postId)
    {
        return success(busBbsPostService.selectBusBbsPostByPostId(postId));
    }

    /**
     * 新增帖子
     */
    @PreAuthorize("@ss.hasPermi('bbs:post:add')")
    @Log(title = "帖子", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusBbsPost busBbsPost)
    {
        busBbsPost.setUserId(getUserId());
        busBbsPost.setNickName(getLoginUser().getUser().getNickName());
        return toAjax(busBbsPostService.insertBusBbsPost(busBbsPost));
    }

    /**
     * 修改帖子
     */
    @PreAuthorize("@ss.hasPermi('bbs:post:edit')")
    @Log(title = "帖子", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusBbsPost busBbsPost)
    {
        return toAjax(busBbsPostService.updateBusBbsPost(busBbsPost));
    }

    /**
     * 删除帖子
     */
    @PreAuthorize("@ss.hasPermi('bbs:post:remove')")
    @Log(title = "帖子", businessType = BusinessType.DELETE)
	@DeleteMapping("/{postIds}")
    public AjaxResult remove(@PathVariable Long[] postIds)
    {
        return toAjax(busBbsPostService.deleteBusBbsPostByPostIds(postIds));
    }
}
