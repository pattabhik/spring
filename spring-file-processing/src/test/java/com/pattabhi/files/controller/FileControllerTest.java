package com.pattabhi.files.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FileControllerTest {
    @Value("${files.uploading.path}")
    private String filePath;
    @Autowired
    RestTemplate restTemplate;
    @Test
    void uploadFile() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "multipart/form-data");

        MultiValueMap<String,Object> body = new LinkedMultiValueMap<>();
        body.add("file", new File(filePath + "JKLKBP2.png"));
        HttpEntity<MultiValueMap<String,Object>> request = new HttpEntity<>(body, headers);
        ResponseEntity<Boolean> booleanResponseEntity = restTemplate.postForEntity("http://localhost:8080/upload", request, Boolean.class);
    }

    @Test
    void downloadFile() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(java.util.Collections.singletonList(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange("http://localhost:8080/download",
                org.springframework.http.HttpMethod.GET, entity, byte[].class);
        Files.write(Paths.get(filePath+"JKLKBP2.png"),responseEntity.getBody());
    }
}