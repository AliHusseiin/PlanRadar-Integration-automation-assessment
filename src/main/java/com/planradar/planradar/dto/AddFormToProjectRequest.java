package com.planradar.planradar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddFormToProjectRequest {
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
        @JsonProperty("ticket-type-id")
        private String ticketTypeId;

        @JsonProperty("required-fields")
        private List<String> requiredFields;
    }
}
