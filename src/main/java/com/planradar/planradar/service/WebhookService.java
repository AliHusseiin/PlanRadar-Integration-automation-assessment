package com.planradar.planradar.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.planradar.planradar.dto.ComponentCreationRequest;
import com.planradar.planradar.dto.TicketCreationRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WebhookService {
    private static final Logger logger = LoggerFactory.getLogger(PlanRadarService.class);

    private final PlanRadarService planRadarService;



    public Mono<String> handleWebhook() {
        return planRadarService.getFirstProjectId()
                .doOnNext(projectId -> logger.info("Received projectId: {}", projectId))
                .flatMap(projectId ->
                        planRadarService.createForm()
                                .doOnNext(formId -> logger.info("Form created with ID: {}", formId))
                                .flatMap(formId ->
                                        planRadarService.addFormToProject(projectId, formId)
                                                .doOnNext(ticketTypeId -> logger.info("Form added to project with ticketTypeId: {}", ticketTypeId))
                                                .flatMap(ticketTypeId -> {
                                                    // Create the Component
                                                    logger.info("Creating component with ticketTypeId: {}", ticketTypeId);
                                                    ComponentCreationRequest componentRequest = new ComponentCreationRequest(
                                                            List.of(new ComponentCreationRequest.DataDTO(
                                                                    new ComponentCreationRequest.AttributesDTO(
                                                                            List.of(new ComponentCreationRequest.LayerDTO("test"))
                                                                    )
                                                            ))
                                                    );

                                                    return planRadarService.createComponent(projectId, componentRequest)
                                                            .doOnNext(componentId -> logger.info("Component created with ID: {}", componentId))
                                                            .flatMap(componentId -> {
                                                                // Create the Ticket
                                                                logger.info("Creating ticket for componentId: {}", componentId);
                                                                logger.info("ticketTypeId: {}", ticketTypeId);
                                                                logger.info("componentId: {}", componentId);
                                                                TicketCreationRequest ticketRequest = new TicketCreationRequest(
                                                                        new TicketCreationRequest.DataDTO(
                                                                                new TicketCreationRequest.AttributesDTO(
                                                                                        "Ticket title",  // subject
                                                                                        formId,     // ticketTypeId
                                                                                        "string",         // parentId (if applicable, or remove if not needed)
                                                                                        1,                // statusId
                                                                                        componentId,      // componentId
                                                                                        1,                // priorityId
                                                                                        "Unknown Type: date", // dueDate
                                                                                        "string",         // extensionDate
                                                                                        new HashMap<>(),  // typedValues
                                                                                        List.of(),       // subscribers (ensure valid IDs)
                                                                                        0,                // progress
                                                                                        0,                // planX
                                                                                        0                 // planY
                                                                                )
                                                                        )
                                                                );

                                                                // Print the ticketRequest as JSON ->debugging purpose
                                                                ObjectMapper objectMapper = new ObjectMapper();
                                                                try {
                                                                    String ticketJson = objectMapper.writeValueAsString(ticketRequest);
                                                                    logger.info("Ticket JSON Payload: {}", ticketJson);
                                                                } catch (JsonProcessingException e) {
                                                                    logger.error("Error converting ticketRequest to JSON", e);
                                                                }

                                                                // Proceed with the API call
                                                                return planRadarService.createTicket(ticketRequest, projectId)
                                                                        .doOnNext(ticketId -> logger.info("Ticket created successfully with ID: {}", ticketId))
                                                                        .map(ticketId -> {
                                                                            System.out.println("Ticket created successfully with ID: " + ticketId);
                                                                            return ticketId;
                                                                        });
                                                            });
                                                })
                                )
                )
                .doOnError(this::handleError)
                .doOnTerminate(() -> System.out.println("Webhook handling completed."))
                .doOnSubscribe(subscription -> System.out.println("Webhook handling started."))
                .doOnSuccess(result -> System.out.println("Webhook handling success: " + result));
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

