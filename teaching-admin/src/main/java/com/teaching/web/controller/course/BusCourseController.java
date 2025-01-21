package com.teaching.web.controller.course;

import com.teaching.common.annotation.Log;
import com.teaching.common.core.controller.BaseController;
import com.teaching.common.core.domain.AjaxResult;
import com.teaching.common.core.page.TableDataInfo;
import com.teaching.common.enums.BusinessType;
import com.teaching.common.utils.poi.ExcelUtil;
import com.teaching.system.domain.BusCourse;
import com.teaching.system.service.IBusCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 课程Controller
 * 
 * @author qiaoting
 * @date 2025-01-19
 */
@RestController
@RequestMapping("/course/course")
public class BusCourseController extends BaseController
{
    @Autowired
    private IBusCourseService busCourseService;

    /**
     * 查询课程列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BusCourse busCourse)
    {
        startPage();
        List<BusCourse> list = busCourseService.selectBusCourseList(busCourse);
        return getDataTable(list);
    }

    /**
     * 导出课程列表
     */
    @Log(title = "课程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusCourse busCourse)
    {
        List<BusCourse> list = busCourseService.selectBusCourseList(busCourse);
        ExcelUtil<BusCourse> util = new ExcelUtil<BusCourse>(BusCourse.class);
        util.exportExcel(response, list, "课程数据");
    }

    /**
     * 获取课程详细信息
     */
    @GetMapping(value = "/{courseId}")
    public AjaxResult getInfo(@PathVariable("courseId") Long courseId)
    {
        return success(busCourseService.selectBusCourseByCourseId(courseId));
    }

    /**
     * 新增课程
     */
    @Log(title = "课程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusCourse busCourse)
    {
        return toAjax(busCourseService.insertBusCourse(busCourse));
    }

    /**
     * 修改课程
     */
    @Log(title = "课程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusCourse busCourse)
    {
        return toAjax(busCourseService.updateBusCourse(busCourse));
    }

    /**
     * 删除课程
     */
    @Log(title = "课程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{courseIds}")
    public AjaxResult remove(@PathVariable Long[] courseIds)
    {
        return toAjax(busCourseService.deleteBusCourseByCourseIds(courseIds));
    }
}
