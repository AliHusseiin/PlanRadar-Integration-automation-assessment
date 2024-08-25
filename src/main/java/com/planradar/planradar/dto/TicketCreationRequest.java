package com.planradar.planradar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

// Main request DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketCreationRequest {
    private DataDTO data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataDTO {
        private AttributesDTO attributes;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AttributesDTO {
        private String subject;

        @JsonProperty("ticket-type-id")
        private String ticketTypeId;

        @JsonProperty("parent-id")
        private String parentId;

        @JsonProperty("status-id")
        private int statusId;

        @JsonProperty("component-id")
        private String componentId;

        @JsonProperty("priority-id")
        private int priorityId;

        @JsonProperty("due-date")
        private String dueDate;

        @JsonProperty("extension-date")
        private String extensionDate;

        @JsonProperty("typed-values")
        private Map<String, Object> typedValues;

        private List<Integer> subscribers;
        private int progress;

        @JsonProperty("plan-x")
        private int planX;

        @JsonProperty("plan-y")
        private int planY;
    }
}
