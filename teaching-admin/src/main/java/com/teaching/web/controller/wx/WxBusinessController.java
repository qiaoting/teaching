package com.teaching.web.controller.wx;

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
import com.teaching.system.domain.WxBusiness;
import com.teaching.system.service.IWxBusinessService;
import com.teaching.common.utils.poi.ExcelUtil;
import com.teaching.common.core.page.TableDataInfo;

/**
 * 服务Controller
 * 
 * @author qiaoting
 * @date 2025-03-06
 */
@RestController
@RequestMapping("/weixin/business")
public class WxBusinessController extends BaseController
{
    @Autowired
    private IWxBusinessService wxBusinessService;

    /**
     * 查询服务列表
     */
    @GetMapping("/list")
    public TableDataInfo list(WxBusiness wxBusiness)
    {
        startPage();
        List<WxBusiness> list = wxBusinessService.selectWxBusinessList(wxBusiness);
        return getDataTable(list);
    }

    /**
     * 导出服务列表
     */
    @Log(title = "服务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxBusiness wxBusiness)
    {
        List<WxBusiness> list = wxBusinessService.selectWxBusinessList(wxBusiness);
        ExcelUtil<WxBusiness> util = new ExcelUtil<WxBusiness>(WxBusiness.class);
        util.exportExcel(response, list, "服务数据");
    }

    /**
     * 获取服务详细信息
     */
    @GetMapping(value = "/{businessId}")
    public AjaxResult getInfo(@PathVariable("businessId") Long businessId)
    {
        return success(wxBusinessService.selectWxBusinessByBusinessId(businessId));
    }

    /**
     * 新增服务
     */
    @Log(title = "服务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxBusiness wxBusiness)
    {
        return toAjax(wxBusinessService.insertWxBusiness(wxBusiness));
    }

    /**
     * 修改服务
     */
    @Log(title = "服务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxBusiness wxBusiness)
    {
        return toAjax(wxBusinessService.updateWxBusiness(wxBusiness));
    }

    /**
     * 删除服务
     */
    @Log(title = "服务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{businessIds}")
    public AjaxResult remove(@PathVariable Long[] businessIds)
    {
        return toAjax(wxBusinessService.deleteWxBusinessByBusinessIds(businessIds));
    }
}
