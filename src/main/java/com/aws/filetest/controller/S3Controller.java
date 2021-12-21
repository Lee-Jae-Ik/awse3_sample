package com.aws.filetest.controller;

import com.aws.filetest.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * S3Controller
 *
 * @author user
 * @version 0.1
 * @see
 * @since 2021-12-21
 */
@RestController
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    @GetMapping("/file-download/{fileName}")
    public ResponseEntity<byte[]> download(@PathVariable String fileName) throws IOException {
        return s3Service.getObject(fileName);
    }

    @PostMapping("/file-upload")
    public ResponseEntity<String> upload(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {
        return ResponseEntity.ok(s3Service.uploadFile(file,request));
    }

    @GetMapping("/file-list")
    public ResponseEntity<String> list() {
        return ResponseEntity.ok(s3Service.showFileList());
    }
}
