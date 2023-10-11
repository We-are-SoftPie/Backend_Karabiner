package com.softpie.karabiner.Azure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Configuration
public class AzureVisionApiConfig {

    @Value("${azure.vision.api.url}")
    private String apiUrl;

    @Value("${azure.vision.api.subscriptionKey}")
    private String subscriptionKey;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

    @Bean
    public AzureVisionApiRequest azureVisionApiRequest(RestTemplate restTemplate) {
        return new AzureVisionApiRequest(apiUrl, subscriptionKey, restTemplate);
    }
}
