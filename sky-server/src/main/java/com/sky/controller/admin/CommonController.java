package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.constant.PathConstant;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {

    @Value("${static.url}")
    private String localPath;

    @PostMapping("/upload")
    @ApiOperation("上传")
    public Result<String> upload(@RequestBody MultipartFile file) {

        try {
            String originalFilename = file.getOriginalFilename();
            String extension = null;
            if (originalFilename != null) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String objectName = UUID.randomUUID() + extension;

            File rootFile = new File(localPath);
            String storePath = rootFile.getAbsolutePath();

            File destFile = new File(Path.of(storePath, objectName).toString());
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }

            file.transferTo(destFile);

            String accessPath = PathConstant.staticResourcePath;
            accessPath = Path.of(accessPath, objectName).toString();

            return Result.success(accessPath);

        } catch (Exception e) {
            log.error("上传文件失败", e);
        }

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
