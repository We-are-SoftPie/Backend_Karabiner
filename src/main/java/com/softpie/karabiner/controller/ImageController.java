package com.softpie.karabiner.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @PostMapping("")
    public ResponseEntity<String> uploadImage(
            @RequestPart(value = "image", required = true) MultipartFile image
    ) {
        try {
            // 이미지 파일을 저장할 디렉토리 경로 설정
            String directoryPath = "/Users/dgsw8th30/Desktop/SoftwareFuture/img/img";

//            // 디렉토리가 존재하지 않으면 생성
//            File directory = new File(directoryPath);
//            if (!directory.exists()) {
//                directory.mkdirs();
//            }

            // 이미지 파일의 원래 이름과 무작위로 생성된 파일 이름을 결합하여 저장 경로 생성
            String originalFilename = image.getOriginalFilename();
            String randomName = UUID.randomUUID().toString();
            String storedImagePath = directoryPath + randomName + "_" + originalFilename;

            // 이미지 파일을 저장
            File storedImage = new File(storedImagePath);
            image.transferTo(storedImage);

            // 이미지 저장이 성공한 경우 응답 반환
            return ResponseEntity.ok("이미지 업로드 성공");
        } catch (IOException e) {
            // 이미지 저장 실패 시 예외 처리
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("이미지 업로드 실패");
        }
    }
}
