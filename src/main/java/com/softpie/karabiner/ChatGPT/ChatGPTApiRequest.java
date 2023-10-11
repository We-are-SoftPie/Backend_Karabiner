package com.softpie.karabiner.ChatGPT;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class ChatGPTApiRequest {
    @Value("${open.ai.api.url}")
    private String apiUrl;

    @Value("${open.ai.api.subscriptionKey}")
    private String subscriptionKey;

    private final RestTemplate restTemplate;

    public ChatGPTApiRequest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 기존 코드 생략

    public Map<String, Object> sendRequest(String text) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", subscriptionKey);
        String requestBody = "{\n" +
                "  \"messages\": [\n" +
                "    {\n" +
                "      \"role\": \"system\",\n" +
                "      \"content\": \"앞으로 너의 답변은 tag, title, content 따로 분리해서 json에 담아 출력해줘.\\n너는 무조건 아래 예시처럼 답변을 출력해야하고, 이외의 말은 출력하면 안돼\\n예시로\\n”the back of a car with its lights on” 이 주어지면\\n{\\n\\\"tag\\\":  11,\\n\\\"title\\\":  \\\"차의 LED등이 규정상황에 위반됩니다. ”,\\n\\\"content\\\":  \\\"지나가던중 사진에 찍힌 차가 규정에 어긋나는 LED가 부착되어있어 신고합니다.\\\"\\n}\\n\\njson값만 도출해. 나머지 값은 절대 출력하면 안돼\\n알겠습니다. 입력하신 내용에 따라 JSON 형식으로만 답변을 출력하겠습니다. 와 같은 맥락의 내용도 마찬가지로 출력하지마\\n입력하신 내용에 따라 JSON 형식으로 답변을 출력하겠습니다. 와 같은 맥락의 내용도 아예 출력하지마\\n\\n그리고 태그들은 다음과 같아\\n\\n\\\"0\\\": 불법광고물,\\n\\\"1\\\": 자전거/이륜차 방치 및 불편,\\n\\\"2\\\": 쓰레기, 폐기물,\\n\\\"3\\\": 해양 쓰레기,\\n\\\"4\\\": 불법 숙박,\\n\\\"5\\\": 기타 생활불편\\n”6”: 교통위반(고속도로 포함),\\n\\\"7\\\": 이륜차 위반,\\n\\\"8\\\": 적재물 추락방지, 중량∙용량 위반,\\n\\\"9\\\": 버스전용차로 위반(일반도로),\\n\\\"10\\\": 번호판 규정 위반,\\n\\\"11\\\": 불법등화, 반사판(지) 가림∙손상,\\n\\\"12\\\": 불법 튜닝, 해체, 조작,\\n\\\"13\\\": 기타 자동차 안전기준 위반\\n이 태그들에서 가장 적합한 것을 골라서 json 데이터에 담아 반환해줘\\n\\n입력하신 내용에 따라 JSON 형식으로 답변을 출력하겠습니다. 와 같은 맥락의 내용도 아예 출력하지마\\n입력하신 내용에 따라 JSON 형식으로 답변을 출력하겠습니다. 와 같은 맥락의 내용도 아예 출력하지마\\n입력하신 내용에 따라 JSON 형식으로 답변을 출력하겠습니다. 와 같은 맥락의 내용도 아예 출력하지마\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"role\": \"user\",\n" +
                "      \"content\": \"the back of a car with its lights on\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"role\": \"assistant\",\n" +
                "      \"content\": \"{\\n\\\"tag\\\":  11,\\n\\\"title\\\":  \\\"차의 LED등이 규정상황에 위반됩니다. ”,\\n\\\"content\\\":  \\\"지나가던중 사진에 찍힌 차가 규정에 어긋나는 LED가 부착되어있어 신고합니다.\\\"\\n}\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"role\": \"user\",\n" +
                "      \"content\": \"a yellow sports car with its doors open\\n\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"role\": \"assistant\",\n" +
                "      \"content\": \"{\\n\\\"tag\\\": 12,\\n\\\"title\\\": \\\"불법 튜닝, 해체, 조작\\\",\\n\\\"content\\\": \\\"주황색 스포츠카가 문을 열고 주차되어 있는 것을 확인했습니다. 주차된 차량이 불법적인 튜닝, 해체 또는 조작을 한 것으로 보입니다. 신고해야 할 상황입니다.\\\"\\n}\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"role\": \"user\",\n" +
                "      \"content\": \"차 사고가 발생함\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"role\": \"assistant\",\n" +
                "      \"content\": \"{\\n\\\"tag\\\": 6,\\n\\\"title\\\": \\\"교통위반(고속도로 포함)\\\",\\n\\\"content\\\": \\\"차 사고가 발생했습니다. 사고 현장에서는 교통 규정을 위반한 것으로 보입니다. 사고 상황을 신고하고 조사해야 합니다.\\\"\\n}\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"role\": \"user\",\n" +
                "      \"content\": \"" + text + "\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"temperature\": 0.7,\n" +
                "  \"top_p\": 0.95,\n" +
                "  \"frequency_penalty\": 0,\n" +
                "  \"presence_penalty\": 0,\n" +
                "  \"max_tokens\": 800,\n" +
                "  \"stop\": null\n" +
                "}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        //System.out.println(requestBody);  //로그 찍기
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

        String responseBody = responseEntity.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        String textValue = jsonNode.get("choices").get(0).get("message").get("content").asText();

        JsonNode contentNode = objectMapper.readTree(textValue);

        int tag = contentNode.get("tag").asInt();
        String title = contentNode.get("title").asText();
        String content = contentNode.get("content").asText();

        Map<String, String> map = new HashMap<>();
        map.put("tag", String.valueOf(tag));
        map.put("title", title);
        map.put("content", content);

        Map<String , Object> resultMap = new HashMap<>();
        resultMap.put("data" , map);

        //System.out.println(textValue);
        return resultMap;
    }

}
