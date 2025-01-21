package com.teaching.web.controller.teaching;

import com.teaching.common.annotation.Log;
import com.teaching.common.core.controller.BaseController;
import com.teaching.common.core.domain.AjaxResult;
import com.teaching.common.core.page.TableDataInfo;
import com.teaching.common.enums.BusinessType;
import com.teaching.common.utils.poi.ExcelUtil;
import com.teaching.system.domain.BusScore;
import com.teaching.system.domain.dto.ScoreImportParam;
import com.teaching.system.service.IBusScoreService;
import com.teaching.system.service.impl.ScoreImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 成绩Controller
 * 
 * @author qiaoting
 * @date 2025-01-20
 */
@RestController
@RequestMapping("/teaching/score")
public class BusScoreController extends BaseController
{
    @Autowired
    private IBusScoreService busScoreService;
    @Autowired
    private ScoreImportService scoreImportService;

    @Log(title = "成绩", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, ScoreImportParam scoreImportParam) throws Exception
    {
        List<BusScore> list= scoreImportService.processExcell(scoreImportParam, file.getInputStream());
        List<List<BusScore>> batchList = splitList(list, 10);
        for (List<BusScore> busScoreList : batchList) {
            busScoreService.batchBusScore(busScoreList);
        }
        return success();
    }

    public <T> List<List<T>> splitList(List<T> originalList, int groupSize) {
        List<List<T>> result = new ArrayList<>();
        for (int i = 0; i < originalList.size(); i += groupSize) {
            // 获取每个子列表的开始和结束索引
            int end = Math.min(i + groupSize, originalList.size());
            result.add(new ArrayList<>(originalList.subList(i, end)));
        }
        return result;
    }

    /**
     * 查询成绩列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BusScore busScore)
    {
        startPage();
        List<BusScore> list = busScoreService.selectBusScoreList(busScore);
        return getDataTable(list);
    }

    /**
     * 导出成绩列表
     */
    @Log(title = "成绩", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusScore busScore)
    {
        List<BusScore> list = busScoreService.selectBusScoreList(busScore);
        ExcelUtil<BusScore> util = new ExcelUtil<BusScore>(BusScore.class);
        util.exportExcel(response, list, "成绩数据");
    }

    /**
     * 获取成绩详细信息
     */
    @PreAuthorize("@ss.hasPermi('teaching:score:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(busScoreService.selectBusScoreById(id));
    }

    /**
     * 新增成绩
     */
    @Log(title = "成绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusScore busScore)
    {
        return toAjax(busScoreService.insertBusScore(busScore));
    }

    /**
     * 修改成绩
     */
    @Log(title = "成绩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusScore busScore)
    {
        return toAjax(busScoreService.updateBusScore(busScore));
    }

    /**
     * 删除成绩
     */
    @Log(title = "成绩", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busScoreService.deleteBusScoreByIds(ids));
    }
}
