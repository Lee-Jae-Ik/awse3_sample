package com.aws.filetest.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * S3Service
 *
 * @author user
 * @version 0.1
 * @see
 * @since 2021-12-21
 */
public interface S3Service {
    public ResponseEntity<byte[]> getObject(String storedFileName) throws IOException;
    public String uploadFile(MultipartFile file, HttpServletRequest request) throws IOException;
    public String showFileList();
}
