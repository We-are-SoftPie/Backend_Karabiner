package com.softpie.karabiner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties") // 프로퍼티 파일 경로 설정
public class BackendKarabinerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendKarabinerApplication.class, args);
    }
}

