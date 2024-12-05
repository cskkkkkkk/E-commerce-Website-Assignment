package org.example.mall.controller;

import org.example.mall.returnEntity.Response;
import org.example.mall.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author An
 * @description TODO OSS 控制器 返回前端 JSON格式数据
 * @date 2022/6/22 1:24
 */
@RestController
@RequestMapping("/images")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;


    @PostMapping("/upload")
    public Response<String> uploadImages(MultipartFile file) {
        // 获取上传头像的文件 MultipartFile
        // 返回上传的oss路径
        String url = ossService.uploadImages(file);
        return Response.success(url,"200");
    }


    @PostMapping("/delete")
    public Response<Boolean> deleteImages(String fileUrl) {
        boolean flag = ossService.deleteImages(fileUrl);
        if (flag) {
            return Response.success(true);
        }
        return Response.failure("删除失败", false);
    }

}

