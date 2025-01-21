package com.teaching.web.controller.teaching;

import com.teaching.common.annotation.Log;
import com.teaching.common.core.controller.BaseController;
import com.teaching.common.core.domain.AjaxResult;
import com.teaching.common.core.page.TableDataInfo;
import com.teaching.common.enums.BusinessType;
import com.teaching.common.utils.poi.ExcelUtil;
import com.teaching.system.domain.BusTest;
import com.teaching.system.service.IBusTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 考试批次Controller
 * 
 * @author qiaoting
 * @date 2025-01-20
 */
@RestController
@RequestMapping("/teaching/test")
public class BusTestController extends BaseController
{
    @Autowired
    private IBusTestService busTestService;

    /**
     * 查询考试批次列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BusTest busTest)
    {
        startPage();
        List<BusTest> list = busTestService.selectBusTestList(busTest);
        return getDataTable(list);
    }

    /**
     * 导出考试批次列表
     */
    @Log(title = "考试批次", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusTest busTest)
    {
        List<BusTest> list = busTestService.selectBusTestList(busTest);
        ExcelUtil<BusTest> util = new ExcelUtil<BusTest>(BusTest.class);
        util.exportExcel(response, list, "考试批次数据");
    }

    /**
     * 获取考试批次详细信息
     */
    @GetMapping(value = "/{testId}")
    public AjaxResult getInfo(@PathVariable("testId") Long testId)
    {
        return success(busTestService.selectBusTestByTestId(testId));
    }

    /**
     * 新增考试批次
     */
    @Log(title = "考试批次", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusTest busTest)
    {
        return toAjax(busTestService.insertBusTest(busTest));
    }

    /**
     * 修改考试批次
     */
    @Log(title = "考试批次", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusTest busTest)
    {
        return toAjax(busTestService.updateBusTest(busTest));
    }

    /**
     * 删除考试批次
     */
    @Log(title = "考试批次", businessType = BusinessType.DELETE)
	@DeleteMapping("/{testIds}")
    public AjaxResult remove(@PathVariable Long[] testIds)
    {
        return toAjax(busTestService.deleteBusTestByTestIds(testIds));
    }
}
