package com.teaching.web.controller.teaching;

import com.teaching.common.annotation.Log;
import com.teaching.common.core.controller.BaseController;
import com.teaching.common.core.domain.AjaxResult;
import com.teaching.common.core.page.TableDataInfo;
import com.teaching.common.enums.BusinessType;
import com.teaching.common.utils.poi.ExcelUtil;
import com.teaching.system.domain.BusStuStudy;
import com.teaching.system.service.IBusStuStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 学习情况记录Controller
 * 
 * @author qiaoting
 * @date 2025-01-14
 */
@RestController
@RequestMapping("/teaching/study")
public class BusStuStudyController extends BaseController
{
    @Autowired
    private IBusStuStudyService busStuStudyService;

    /**
     * 查询学习情况记录列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BusStuStudy busStuStudy)
    {
        startPage();
        List<BusStuStudy> list = busStuStudyService.selectBusStuStudyList(busStuStudy);
        return getDataTable(list);
    }

    /**
     * 导出学习情况记录列表
     */
    @Log(title = "学习情况记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusStuStudy busStuStudy)
    {
        List<BusStuStudy> list = busStuStudyService.selectBusStuStudyList(busStuStudy);
        ExcelUtil<BusStuStudy> util = new ExcelUtil<BusStuStudy>(BusStuStudy.class);
        util.exportExcel(response, list, "学习情况记录数据");
    }

    /**
     * 获取学习情况记录详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(busStuStudyService.selectBusStuStudyById(id));
    }

    /**
     * 新增学习情况记录
     */
    @Log(title = "学习情况记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusStuStudy busStuStudy)
    {
        return toAjax(busStuStudyService.insertBusStuStudy(busStuStudy));
    }

    /**
     * 修改学习情况记录
     */
    @Log(title = "学习情况记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusStuStudy busStuStudy)
    {
        return toAjax(busStuStudyService.updateBusStuStudy(busStuStudy));
    }

    /**
     * 删除学习情况记录
     */
    @Log(title = "学习情况记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busStuStudyService.deleteBusStuStudyByIds(ids));
    }
}
