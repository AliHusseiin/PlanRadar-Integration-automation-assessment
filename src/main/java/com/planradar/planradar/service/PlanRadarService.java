package com.planradar.planradar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.planradar.planradar.dto.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PlanRadarService {
    private static final Logger logger = LoggerFactory.getLogger(PlanRadarService.class);

    private final WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${planradar.api.base-url-v1}")
    private String baseUrlV1;

    @Value("${planradar.api.base-url-v2}")
    private String baseUrlV2;

    @Value("${planradar.api.token}")
    private String apiToken;

    @Value("${planradar.api.customer_id}")
    private String customerId;

    public Mono<String> getFirstProjectId() {
        String url = baseUrlV1 + "/" + customerId + "/projects?sort=-id&page=1";

        return webClient.get()
                .uri(url)
                .headers(headers -> {
                    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    headers.set("X-PlanRadar-API-Key", apiToken);
                })
                .retrieve()
                .bodyToMono(ProjectResponse.class)
                .map(projectResponse -> {
                    // Extract the first project ID from the response
                    return projectResponse.getData().stream()
                            .findFirst()
                            .map(ProjectResponse.ProjectData::getId)
                            .orElse(null);
                })
                .doOnError(this::handleError);

    }

    public Mono<String> createForm() {
        // Constructing the typed fields
        Map<String, FormCreationRequest.TypedFieldDTO> typedFields = new HashMap<>();
        typedFields.put("field_key", new FormCreationRequest.TypedFieldDTO(
                "Title", "CustomFields::ShortTextType", 0,
                Collections.singletonList("string"), Collections.singletonList("string")
        ));
        typedFields.put("field_key2", new FormCreationRequest.TypedFieldDTO(
                "Status", "CustomFields::IntegerType", 1,
                Collections.singletonList("string"), Collections.singletonList("string")
        ));

        // Constructing the request body
        FormCreationRequest request = new FormCreationRequest(
                new FormCreationRequest.DataDTO(
                        new FormCreationRequest.AttributesDTO(
                                "Sample Form", typedFields
                        )
                )
        );

        // Making the POST request
        return webClient.post()
                .uri(baseUrlV1 + "/" + customerId + "/ticket_types")
                .headers(headers -> {
                    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    headers.set("X-PlanRadar-API-Key", apiToken);
                })
                .bodyValue(request)
                .retrieve()
                .bodyToMono(FormCreationResponse.class) // Use the response DTO
                .map(response -> response.getData().getId()) // Extract the form ID from the response
                .doOnSuccess(id -> System.out.println("Form created successfully with ID: " + id))
                .doOnError(throwable -> System.err.println("Error creating form: " + throwable.getMessage()));
    }

    public Mono<String> addFormToProject(String projectId, String ticketTypeId) {
        // Constructing the request body
        AddFormToProjectRequest request = new AddFormToProjectRequest(
                new AddFormToProjectRequest.DataDTO(
                        new AddFormToProjectRequest.AttributesDTO(
                                ticketTypeId,
                                Collections.singletonList("string") // Example required fields
                        )
                )
        );

        String url = baseUrlV2 + "/" + customerId + "/projects/" + projectId + "/ticket_types_project";

        return webClient.post()
                .uri(url)
                .headers(headers -> {
                    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    headers.set("X-PlanRadar-API-Key", apiToken);
                })
                .bodyValue(request) // Set the request body
                .retrieve()
                .bodyToMono(AddFormToProjectResponse.class) // Parse response into DTO
                .map(response -> response.getData().getId()) // Extract the project ticket type ID from response
                .doOnSuccess(id -> System.out.println("Form added to project successfully with ID: " + id))
                .doOnError(this::handleError);
    }

    public Mono<String> createComponent(String projectId, ComponentCreationRequest request) {
        String url = baseUrlV1 + "/" + customerId + "/projects/" + projectId + "/components";

        return webClient.post()
                .uri(url) // Fully specify the URL here
                .headers(headers -> {
                    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    headers.set("X-PlanRadar-API-Key", apiToken);
                })
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ComponentCreationResponse.class)
                .map(response ->response.getData().get(0).getId())
                .doOnSuccess(response -> System.out.println("Component created successfully: " + response))
                .doOnError(this::handleError);
    }


    public Mono<String> createTicket(TicketCreationRequest request, String projectId) {
        String url = baseUrlV2 + "/" + customerId + "/projects/" + projectId + "/tickets";

        return webClient.post()
                .uri(url)
                .headers(headers -> {
                    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    headers.set("X-PlanRadar-API-Key", apiToken);
                })
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(response -> System.out.println("Ticket created successfully: " + response))
                .doOnError(this::handleError);
    }


    private void handleError(Throwable throwable) {
        if (throwable instanceof WebClientResponseException) {
            WebClientResponseException ex = (WebClientResponseException) throwable;
            logger.error("HTTP Status: {}", ex.getStatusCode());
            logger.error("Response Body: {}", ex.getResponseBodyAsString());
        } else {
            logger.error("Error: {}", throwable.getMessage());
        }
    }
}
