package com.softpie.karabiner.Azure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.softpie.karabiner.ChatGPT.ChatGPTApiRequest;
import com.softpie.karabiner.service.ImageUploadApiRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AzureController {

    private final AzureVisionApiRequest azureVisionApiRequest;
    private final ChatGPTApiRequest chatGPTApiRequest;
    private final ImageUploadApiRequest imageUploadApiRequest;
    //private final ChatGPTApiRequest chatGPTApiRequest;

//    @PostMapping("/e")
//    public void imgCall(){
//        azureVisionApiRequest.sendRequest("https://cdn.kbmaeil.com/news/photo/201908/824381_847056_2958.jpg");
//    }

    @PostMapping("/img")
    public Map<String, Object> uploadImage(
            @RequestPart(value = "image", required = true) MultipartFile image) throws JsonProcessingException {
        return chatGPTApiRequest.sendRequest(azureVisionApiRequest.sendRequest(imageUploadApiRequest.imageUpload(image)));
    }
}
