package com.teaching.web.controller.teaching;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.teaching.common.core.domain.model.LoginUser;
import com.teaching.common.utils.SecurityUtils;
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
import com.teaching.system.domain.BusHomework;
import com.teaching.system.service.IBusHomeworkService;
import com.teaching.common.utils.poi.ExcelUtil;
import com.teaching.common.core.page.TableDataInfo;

/**
 * 作业布置Controller
 * 
 * @author qiaoting
 * @date 2025-01-24
 */
@RestController
@RequestMapping("/teaching/homework")
public class BusHomeworkController extends BaseController
{
    @Autowired
    private IBusHomeworkService busHomeworkService;

    /**
     * 查询作业布置列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BusHomework busHomework)
    {
        LoginUser loginUser = getLoginUser();
        if (loginUser.getUser().getRoles().stream().anyMatch(a->a.getRoleKey().equals("student"))) {
            busHomework.setDeptId(loginUser.getDeptId());
        }
        startPage();
        List<BusHomework> list = busHomeworkService.selectBusHomeworkList(busHomework);
        return getDataTable(list);
    }

    /**
     * 导出作业布置列表
     */
    @PreAuthorize("@ss.hasPermi('teaching:homework:export')")
    @Log(title = "作业布置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusHomework busHomework)
    {
        List<BusHomework> list = busHomeworkService.selectBusHomeworkList(busHomework);
        ExcelUtil<BusHomework> util = new ExcelUtil<BusHomework>(BusHomework.class);
        util.exportExcel(response, list, "作业布置数据");
    }

    /**
     * 获取作业布置详细信息
     */
    @GetMapping(value = "/{homeworkId}")
    public AjaxResult getInfo(@PathVariable("homeworkId") Long homeworkId)
    {
        return success(busHomeworkService.selectBusHomeworkByHomeworkId(homeworkId));
    }

    /**
     * 新增作业布置
     */
    @PreAuthorize("@ss.hasPermi('teaching:homework:add')")
    @Log(title = "作业布置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusHomework busHomework)
    {
        return toAjax(busHomeworkService.insertBusHomework(busHomework));
    }

    /**
     * 修改作业布置
     */
    @PreAuthorize("@ss.hasPermi('teaching:homework:edit')")
    @Log(title = "作业布置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusHomework busHomework)
    {
        return toAjax(busHomeworkService.updateBusHomework(busHomework));
    }

    /**
     * 删除作业布置
     */
    @PreAuthorize("@ss.hasPermi('teaching:homework:remove')")
    @Log(title = "作业布置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{homeworkIds}")
    public AjaxResult remove(@PathVariable Long[] homeworkIds)
    {
        return toAjax(busHomeworkService.deleteBusHomeworkByHomeworkIds(homeworkIds));
    }
}
