package com.example.demo.controller;

import com.example.demo.dto.ResultCodeEnum;
import com.example.demo.dto.ResultInfo;
import com.example.demo.entity.Attachment;
import com.example.demo.service.AttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

//附件管理
@Slf4j
@Controller
@RequestMapping(value = "/attachment")
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;

    //所有附件
    @GetMapping(value = "/index")
    public String attachments(Model model) {
        model.addAttribute("attachments", attachmentService.getAllAttachments());
        return "attachment/attachment";
    }

    //选择附件
    @GetMapping(value = "/select")
    public String selectAttachment(Model model,
                                   @RequestParam(value = "id", defaultValue = "none") String id,
                                   @RequestParam(value = "type", defaultValue = "normal") String type) {
        model.addAttribute("attachments", attachmentService.getAllAttachments());
        model.addAttribute("id", id);
        return "attachment/select";
    }

    //上传窗口
    @GetMapping(value = "/uploadModal")
    public String uploadModal() {
        return "attachment/upload";
    }

    //上传请求
    @PostMapping(value = "/upload", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        final Map<String, Object> result = new HashMap<>(3);
        if (!file.isEmpty()) {
            try {
                final Map<String, String> resultMap = attachmentService.attachUpload(file);
                if (resultMap == null || resultMap.isEmpty()) {
                    log.error("文件上传失败");
                    result.put("success", ResultCodeEnum.FAIL.getCode());
                    result.put("message", "上传失败");
                    return result;
                }
                //数据库保存文件对象
                Attachment att = new Attachment();
                att.setName(resultMap.get("fileName"));
                att.setUrl(resultMap.get("filePath"));
                att.setThumbnailUrl(resultMap.get("smallPath"));
                att.setType(file.getContentType());
                att.setSuffix(resultMap.get("suffix"));
                att.setSize(resultMap.get("size"));
                att.setLengthAndWidth(resultMap.get("wh"));
                att.setLocation(resultMap.get("location"));
                attachmentService.createAttachment(att);

                log.info("文件 {} 上传到 {} 成功", resultMap.get("fileName"), resultMap.get("filePath"));
                result.put("success", ResultCodeEnum.SUCCESS.getCode());
                result.put("message", "上传成功");
                result.put("url", resultMap.get("filePath"));
                //markdown得到filename用于图片渲染，此处必须给文件的url信息
                result.put("filename",  resultMap.get("filePath"));


            } catch (Exception e) {
                log.error("Upload file failed:{}", e.getMessage());
                result.put("success", ResultCodeEnum.FAIL.getCode());
                result.put("message", "上传失败");
            }
        } else {
            log.error("文件不能为空!");
        }
        return result;
    }

    //获取附件
    @GetMapping(value = "/attachment")
    public String attachmentDetail(Model model, @RequestParam("attachId") String attachId) {
        model.addAttribute("attachment", attachmentService.getById(Long.parseLong(attachId))==null?new Attachment():attachmentService.getById(Long.parseLong(attachId)));
        return "attachment/detail";
    }

    //删除附件
    @GetMapping(value = "/remove")
    @ResponseBody
    public ResultInfo removeAttachment(@RequestParam("attachId") String attachId, HttpServletRequest request) {
        final Attachment att = attachmentService.getById(Long.parseLong(attachId))==null?new Attachment():attachmentService.getById(Long.parseLong(attachId));
        boolean flag;
        try {
            //尝试删除物理文件
            flag = attachmentService.attachDelete(att);
            if (flag) {
                //删除数据库中存储的数据
                attachmentService.deleteAttachment(Long.parseLong(attachId));
                log.info("附件删除成功!");
            } else {
                log.error("附件删除失败");
            }
        } catch (Exception e) {
            log.error("附件删除失败: {}", e.getMessage());
            return new ResultInfo().Code(ResultCodeEnum.FAIL).Msg("附件删除失败");
        }
        return new ResultInfo().Code(ResultCodeEnum.SUCCESS).Msg("附件删除成功");
    }
}
