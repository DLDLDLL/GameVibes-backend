package com.example.gamevibe.controller;

import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.common.ErrorCode;
import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.model.vo.FileVO;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Api(tags = "文件模块")
@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${gamevibe.path}")
    private String basepath;

    /**
     * 文件上传
     *
     * @param multipartFiles
     * @param request
     * @return
     */
    @PostMapping("/upload")
    public BaseResponse<FileVO> uploadFile(@RequestPart("files") List<MultipartFile> multipartFiles, HttpServletRequest request) throws IOException {
        List<String> filePaths = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            // 原始路径名
            String originalFilename = multipartFile.getOriginalFilename();
            // 后缀
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            //使用uuid唯一识别码，与原来的文件名字组合构建新的名字
            String fileName = UUID.randomUUID().toString() + suffix;
            //创建一个目录
            File file1 = new File(basepath);
            if (!file1.exists()) {
                //如果目录不存在，就创建出来
                file1.mkdirs();
            }
            //使用UUID重新生成文件名，防止文件名重复造成覆盖
            //设置文件的转储路径
            multipartFile.transferTo(new File(basepath + fileName));
            filePaths.add(basepath + fileName);
        }
        FileVO fileVO = new FileVO();
        fileVO.setFilePaths(filePaths);
        return ResultUtils.success(fileVO);
    }
}
