package com.teaching.web.controller.wx;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teaching.common.config.TeachingConfig;
import com.teaching.common.utils.StringUtils;
import com.teaching.common.utils.file.FileUploadUtils;
import com.teaching.common.utils.file.FileUtils;
import com.teaching.framework.config.ServerConfig;
import com.teaching.system.domain.WxBusiness;
import com.teaching.system.domain.dto.ScoreImportParam;
import com.teaching.web.controller.common.CommonController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
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
import com.teaching.system.domain.WxMedia;
import com.teaching.system.service.IWxMediaService;
import com.teaching.common.utils.poi.ExcelUtil;
import com.teaching.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 微信媒体Controller
 * 
 * @author qiaoting
 * @date 2025-03-06
 */
@RestController
@RequestMapping("/weixin/media")
public class WxMediaController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(WxMediaController.class);

    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private IWxMediaService wxMediaService;

    /**
     * 通用上传请求（单个）
     */
    @PostMapping("/importMedia")
    public AjaxResult importMedia(MultipartFile file, WxBusiness wxBusiness) throws Exception
    {
        try
        {
            // 上传文件路径
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.commonUpload(TeachingConfig.getWeixinUploadPath(), file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", url);
            ajax.put("fileName", fileName);
            ajax.put("newFileName", FileUtils.getName(fileName));
            ajax.put("originalFilename", file.getOriginalFilename());
            WxMedia wxMedia = new WxMedia();
            wxMedia.setBusinessId(wxBusiness.getBusinessId());
            wxMedia.setMediaName(file.getOriginalFilename());
            wxMedia.setMediaPath(fileName);
            wxMediaService.insertWxMedia(wxMedia);
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 查询微信媒体列表
     */
    @GetMapping("/list")
    public TableDataInfo list(WxMedia wxMedia)
    {
        startPage();
        List<WxMedia> list = wxMediaService.selectWxMediaList(wxMedia);
        return getDataTable(list);
    }

    /**
     * 导出微信媒体列表
     */
    @Log(title = "微信媒体", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxMedia wxMedia)
    {
        List<WxMedia> list = wxMediaService.selectWxMediaList(wxMedia);
        ExcelUtil<WxMedia> util = new ExcelUtil<WxMedia>(WxMedia.class);
        util.exportExcel(response, list, "微信媒体数据");
    }

    /**
     * 获取微信媒体详细信息
     */
    @GetMapping(value = "/{mediaId}")
    public AjaxResult getInfo(@PathVariable("mediaId") Long mediaId)
    {
        return success(wxMediaService.selectWxMediaByMediaId(mediaId));
    }

    /**
     * 新增微信媒体
     */
    @Log(title = "微信媒体", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxMedia wxMedia)
    {
        return toAjax(wxMediaService.insertWxMedia(wxMedia));
    }

    /**
     * 修改微信媒体
     */
    @Log(title = "微信媒体", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxMedia wxMedia)
    {
        return toAjax(wxMediaService.updateWxMedia(wxMedia));
    }

    /**
     * 删除微信媒体
     */
    @Log(title = "微信媒体", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mediaIds}")
    public AjaxResult remove(@PathVariable Long[] mediaIds)
    {
        return toAjax(wxMediaService.deleteWxMediaByMediaIds(mediaIds));
    }
}
