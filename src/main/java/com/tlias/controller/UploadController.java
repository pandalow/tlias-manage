package com.tlias.controller;

import com.tlias.pojo.Result;
import com.tlias.util.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;

//    @PostMapping("/upload")
//    public Result upload(MultipartFile image) throws IOException {
//        log.info("{} ", image);
//
//        String originalFilename = image.getOriginalFilename();
//        int i = originalFilename.lastIndexOf(".");
//        String substring = originalFilename.substring(i);
//
//        String newFileName = UUID.randomUUID().toString()+ substring;
//
//        image.transferTo(new File("/"+newFileName));
//
//        return Result.success();
//    }

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info(image.getOriginalFilename());
        String upload = aliOSSUtils.upload(image);

        return Result.success(upload);
    }

}
