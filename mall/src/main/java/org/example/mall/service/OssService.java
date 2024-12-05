package org.example.mall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author cs
 * @description TODO OSS service接口层 ,用于
 * @date 2024/12/1
 */
public interface OssService {
    /**
     * 上传图片
     * @param file
     * @return
     */
    String uploadImages(MultipartFile file);

    /**
     * 删除图片
     * @param fileUrl
     * @return
     */
    boolean deleteImages(String fileUrl);
}
