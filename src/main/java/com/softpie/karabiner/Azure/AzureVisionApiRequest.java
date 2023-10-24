package com.softpie.karabiner.Azure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class AzureVisionApiRequest {

    private final String apiUrl;
    private final String subscriptionKey;
    private final RestTemplate restTemplate;

    public AzureVisionApiRequest(String apiUrl, String subscriptionKey, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.subscriptionKey = subscriptionKey;
        this.restTemplate = restTemplate;
    }

    public String sendRequest(String imageUrl) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Ocp-Apim-Subscription-Key", subscriptionKey);

        String requestBody = "{\"url\":\"" + imageUrl + "\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);


            String responseBody = responseEntity.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            String textValue = jsonNode.get("description").get("captions").get(0).get("text").asText();
            System.out.println("Parsed Text: " + textValue);
            return textValue;
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                // HTTP 400 Bad Request 예외 처리
                String errorMessage = "HTTP 400 Bad Request: " + e.getResponseBodyAsString();
                // 로그에 에러 메시지
                System.out.println("Error: " + errorMessage);
            }
        }
        return "error";
    }
}
