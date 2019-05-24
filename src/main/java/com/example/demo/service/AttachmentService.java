package com.example.demo.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.StrBuilder;
import com.example.demo.entity.Attachment;
import com.example.demo.entity.Member;
import com.example.demo.helper.Common;
import com.example.demo.helper.SnowflakeIdWorker;
import com.example.demo.mapper.AttachmentMapper;
import com.example.demo.mapper.MemberMapper;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.beans.Expression;
import java.text.SimpleDateFormat;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Transactional(rollbackFor = {Exception.class})
public class AttachmentService {

    @Autowired
    private AttachmentMapper mapper;

    @Autowired
    private SnowflakeIdWorker idWorker;

    public void createAttachment(Attachment u) {
        u.setId(idWorker.nextId());
        mapper.create(u);
    }
    public void updateAttachment(Long id,Attachment u){
        mapper.update(id,u);
    }
    public void deleteAttachment(Long id){
        mapper.delete(id);
    }

    public List<Attachment> getAllAttachments() {
        return mapper.findAll();
    }
    public Attachment getById(Long id) {
        return mapper.findById(id);
    }
    public Attachment getByName(String Name) {
        return mapper.findByName(Name);
    }

    //本地服务器上传
    public Map<String, String> attachUpload(MultipartFile file) {
        final Map<String, String> resultMap = new HashMap<>(6);

        //构建文件夹名和文件名
        DateTime today = DateUtil.date();
        final String dirName = DateUtil.format(today, "yyyy-MM-dd");
        final String dateString = DateUtil.format(today, "yyyyMMddHHmmss");
        try {
            //获取当前工作目录
            final StrBuilder uploadPath = new StrBuilder(System.getProperties().getProperty("user.dir"));
            //拼接成上传目录
            uploadPath.append("/uploadFiles/").append(dirName).append("/");

            //构建物理文件夹
            final File mediaPath = new File(uploadPath.toString());
            if (!mediaPath.exists()) {
                if (!mediaPath.mkdirs()) {
                    resultMap.put("success", "0");
                    return resultMap;
                }
            }

            //处理文件名（移除特殊字符和后缀）
            final StrBuilder fixedfileName = new StrBuilder(
                    file.getOriginalFilename()
                            .substring(0, Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf('.'))
                            .replaceAll(" ", "_")
                            .replaceAll(",", ""));
            //拼接时间字符串和随机数形成新文件名
            fixedfileName.append(dateString).append(new Random().nextInt(1000));

            //获得文件后缀
            final String suffix = file.getOriginalFilename()
                    .substring(file.getOriginalFilename().lastIndexOf('.') + 1);

            //最终文件名
            final StrBuilder finalFileName = new StrBuilder(fixedfileName).append(".").append(suffix);

            //转移文件位置
            file.transferTo(new File(mediaPath.getAbsoluteFile(), finalFileName.toString()));

            //原图路径
            final StrBuilder originalPath = new StrBuilder(mediaPath.getAbsolutePath());
            originalPath.append("/").append(finalFileName);

            //缩略图路径
            final StrBuilder thumbnailPath = new StrBuilder(mediaPath.getAbsolutePath());
            thumbnailPath.append("/").append(fixedfileName).append("_small.").append(suffix);

            //压缩图片
            Thumbnails.of(originalPath.toString())
                    .size(256, 256)
                    .keepAspectRatio(false)
                    .toFile(thumbnailPath.toString());

            //region 映射文件路径

            final String filePathString = new StrBuilder("/uploadFiles/")
                    .append(dirName)
                    .append("/")
                    .append(finalFileName).toString();
            final String thumbnailPathString = new StrBuilder("/uploadFiles/")
                    .append(dirName)
                    .append("/")
                    .append(fixedfileName)
                    .append("_small.")
                    .append(suffix).toString();

            //endregion

            //得到大小和长宽
            final String size = Common.parseSize(new File(originalPath.toString()).length());
            final String wh = Common.getImageWh(new File(originalPath.toString()));

            resultMap.put("fileName", finalFileName.toString());
            resultMap.put("filePath", filePathString);
            resultMap.put("smallPath", thumbnailPathString);
            resultMap.put("suffix", suffix);
            resultMap.put("size", size);
            resultMap.put("wh", wh);
            resultMap.put("location", "local_server");
        } catch (IOException e) {
            throw new RuntimeException("文件上传失败");
        }
        return resultMap;
    }

    //本地删除
    public boolean attachDelete(Attachment att) {
        StrBuilder uploadPath = new StrBuilder(System.getProperties().getProperty("user.dir"));
        //物理地址
        StrBuilder delPath = new StrBuilder(uploadPath).append(att.getUrl());
        //缩略图物理地址
        StrBuilder delSmallPath = new StrBuilder(uploadPath).append(att.getThumbnailUrl());
        File delFile = new File(delPath.toString());
        File delSmallFile = new File(delSmallPath.toString());
        if (delFile.exists() && delFile.isFile()) {
            return delFile.delete() && delSmallFile.delete();
        }
        throw new RuntimeException("图片或缩略图不存在");
    }
}
