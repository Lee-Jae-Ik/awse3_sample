package com.aws.filetest.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.aws.filetest.model.StethScopeData;
import com.aws.filetest.service.S3Service;
import com.aws.filetest.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * S3Service
 *
 * @author user
 * @version 0.1
 * @see
 * @since 2021-12-21
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Override
    public ResponseEntity<byte[]> getObject(String storedFileName) throws IOException {
        S3Object s3Object = amazonS3.getObject(new GetObjectRequest(bucket, storedFileName));
        S3ObjectInputStream objectInputStream = ((S3Object) s3Object).getObjectContent();
        byte[] bytes = IOUtils.toByteArray(objectInputStream);

        String fileName = URLEncoder.encode(storedFileName, "UTF-8").replaceAll("\\+", "%20");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentLength(bytes.length);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }

    @Override
    public String uploadFile(MultipartFile file, HttpServletRequest request) throws IOException {
        String fileName = file.getOriginalFilename();
        if (fileName.indexOf(".") != -1) {
            System.out.println("Arrays.stream(fileName.split(\".\")) = " + Arrays.stream(fileName.split(".")));
            String ext = fileName.split("\\.")[1];
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, fileName.substring(0, fileName.lastIndexOf(".")) + "." + ext, FileUtils.convert(file));
            putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3.putObject(putObjectRequest);
            return "success";
        }
        throw new IllegalArgumentException("파일 형식이 다릅니다.");
    }

    @Override
    public List<StethScopeData> showFileList() {
        ObjectListing objectListing = amazonS3.listObjects(bucket);
        List<String> arrayKeyList = new ArrayList<>();
        List<Date> arrayDateList = new ArrayList<>();

        for (S3ObjectSummary summary : objectListing.getObjectSummaries()) {
            arrayKeyList.add(summary.getKey());
            arrayDateList.add(summary.getLastModified());
        }
        Date max = Collections.max(arrayDateList);

        List<StethScopeData> urlList = new ArrayList<>();

        for (String url : arrayKeyList) {
            String fileName = arrayKeyList.get(arrayKeyList.indexOf(url));
            url = "https://" + bucket + ".s3." + region + ".amazonaws.com/" + fileName;

//            StethScopeData stethScopeData = StethScopeData.builder()
//                    .url(url)
//                    .user()
//                    .build();
//            urlList.add(url);

        }
        return urlList;
    }

    @Override
    public String searchFile(String fileName) {
        ObjectListing objectListing = amazonS3.listObjects(bucket);
        List<String> arrayKeyList = new ArrayList<>();
        List<Date> arrayDateList = new ArrayList<>();

        for (S3ObjectSummary summary : objectListing.getObjectSummaries()) {
            arrayKeyList.add(summary.getKey());
            arrayDateList.add(summary.getLastModified());
        }

        String url = "https://" + bucket + ".s3." + region + ".amazonaws.com/" + fileName;
        return url;
    }
}
