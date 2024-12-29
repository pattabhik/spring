package com.pattabhi.files.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
public class FileController {
    @Value("${files.uploading.path}")
    private String filePath;

    @PostMapping("/upload")
    public boolean uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        file.transferTo(new File(filePath + file.getOriginalFilename()));
        return true;
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile() throws IOException {

        byte[] bytes = Files.readAllBytes(new File(filePath + "JKLKBP2.png").toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=JKLKBP2.png");
        headers.add("Content-Type", "image/png");
        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }
}
