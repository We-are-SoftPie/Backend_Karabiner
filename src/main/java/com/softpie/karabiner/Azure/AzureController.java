package com.softpie.karabiner.Azure;

import com.softpie.karabiner.ChatGPT.ChatGPTApiRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.softpie.karabiner.dto.ImageUrlDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AzureController {

    private final AzureVisionApiRequest azureVisionApiRequest;
    private final ChatGPTApiRequest chatGPTApiRequest;
    //private final ChatGPTApiRequest chatGPTApiRequest;

//    @PostMapping("/e")
//    public void imgCall(){
//        azureVisionApiRequest.sendRequest("https://cdn.kbmaeil.com/news/photo/201908/824381_847056_2958.jpg");
//    }

    @PostMapping("/img")
    public Map<String, Object> imgUrl(@RequestBody ImageUrlDto imgUrl) throws JsonProcessingException {
        return chatGPTApiRequest.sendRequest(azureVisionApiRequest.sendRequest(imgUrl.getImgUrl()));
    }
}
