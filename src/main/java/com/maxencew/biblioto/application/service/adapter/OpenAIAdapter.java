package com.maxencew.biblioto.application.service.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxencew.biblioto.application.request.OpenAiRequest;
import com.maxencew.biblioto.application.response.ChatGptSummariseBookPattern;
import com.maxencew.biblioto.application.response.OpenAiResponse;
import com.maxencew.biblioto.application.service.api.OpenAIService;
import com.maxencew.biblioto.infrastructure.configuration.AppConfig;
import com.maxencew.biblioto.infrastructure.configuration.ExternalApiConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class OpenAIAdapter implements OpenAIService {

    public static final String USER_PROMPT_CONTENT = "Please, respond with a Json Format respecting the information below, your response must be interpretate by a java API resource :\n" +
            "\n" +
            "I'd like to have a \"summary\" (Less than 500 words), a \"synopsis\"  (Less than 500 words) and a paragraph   summarizing the  public opinions of the book \"The lord of the rings\" of the author name \"Tolkien\". You can split the publicOpinions paragraphe with a part named \"overallReception\", an other named \"criticisms\" and finally \"criticisms\".\n" +
            "\n" +
            "Finally, an example of the json format you always must respect when your answer :\n" +
            "\n" +
            "\n" +
            "```json\n" +
            "{\n" +
            "  \"summary\": \"exemple\",\n" +
            "  \"synopsis\": \"exemple\"\n" +
            "  \"publicOpinions\": {\n" +
            "    \"overallReception\": \"\"exemple\"\",\n" +
            "    \"praises\": \"exemple\",\n" +
            "    \"criticisms\": \"exemple\"\n" +
            "  }\n" +
            "}\n" +
            "```";
    public static final String SYSTEM_PROMPT_CONTENT = "You are a library assistant, your role is to create relevant summaries and condense the opinions of the literary community.";

    private ExternalApiConfiguration externalApiConfiguration;
    private AppConfig appConfig;

    @Override
    public ChatGptSummariseBookPattern getBookInformation(final String bookName, final String author) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + externalApiConfiguration.getOpenAIApiKey());

        ResponseEntity<OpenAiResponse> response = appConfig.restTemplateOpenAi()
                .exchange(externalApiConfiguration.getOpenAIUrl(), HttpMethod.POST,
                        getOpenAiRequestHttpEntity(headers), OpenAiResponse.class);
        try {
            if (Objects.nonNull(response.getBody()) && !response.getBody().getChoices().isEmpty()) {
                return new ObjectMapper().readValue(cleanJsonString(response.getBody()
                        .getChoices().getFirst().getMessage().getContent()), ChatGptSummariseBookPattern.class);
            }
           throw new IllegalArgumentException("OpenAI Api respond with an empty body.");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static HttpEntity<OpenAiRequest> getOpenAiRequestHttpEntity(HttpHeaders headers) {
        OpenAiRequest request = new OpenAiRequest(
                "gpt-3.5-turbo", List.of(new OpenAiRequest.Message("system", SYSTEM_PROMPT_CONTENT),
                new OpenAiRequest.Message("user", USER_PROMPT_CONTENT))

        );

        return new HttpEntity<>(request, headers);
    }

    public static String cleanJsonString(String jsonString) {
        // Supprimer les délimiteurs de code ```json et ```
        return jsonString.replaceAll("```json\\s*", "").replaceAll("\\s*```", "");
    }
}
