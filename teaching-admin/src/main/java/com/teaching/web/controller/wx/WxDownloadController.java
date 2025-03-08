package com.teaching.web.controller.wx;

import com.teaching.common.annotation.Log;
import com.teaching.common.config.TeachingConfig;
import com.teaching.common.constant.Constants;
import com.teaching.common.core.controller.BaseController;
import com.teaching.common.core.domain.AjaxResult;
import com.teaching.common.core.page.TableDataInfo;
import com.teaching.common.enums.BusinessType;
import com.teaching.common.utils.StringUtils;
import com.teaching.common.utils.file.FileUploadUtils;
import com.teaching.common.utils.file.FileUtils;
import com.teaching.common.utils.poi.ExcelUtil;
import com.teaching.framework.config.ServerConfig;
import com.teaching.system.domain.WxBusiness;
import com.teaching.system.domain.WxMedia;
import com.teaching.system.service.IWxBusinessService;
import com.teaching.system.service.IWxMediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 微信媒体Controller
 * 
 * @author qiaoting
 * @date 2025-03-06
 */
@RestController
@RequestMapping("/wx")
public class WxDownloadController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(WxDownloadController.class);

    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private IWxMediaService wxMediaService;
    @Autowired
    private IWxBusinessService wxBusinessService;

    /**
     * 查询微信媒体列表
     */
    @GetMapping("/list")
    public List<WxMedia> list(String code)
    {
        WxBusiness wxBusiness = new WxBusiness();
        wxBusiness.setBusinessCode(code);
        wxBusiness.setStatus("0");
        List<WxBusiness> businesses = wxBusinessService.selectWxBusinessList(wxBusiness);
        WxMedia wxMedia = new WxMedia();
        wxMedia.setBusinessId(businesses.get(0).getBusinessId());
        wxMedia.setStatus("0");
        return wxMediaService.selectWxMediaList(wxMedia);
    }

    @GetMapping("/download")
    public void download(String fileName, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!FileUtils.checkAllowDownload(fileName))
            {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String filePath = TeachingConfig.getWeixinUploadPath() + "/" + fileName;
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            FileUtils.setAttachmentResponseHeader(response, fileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

}
