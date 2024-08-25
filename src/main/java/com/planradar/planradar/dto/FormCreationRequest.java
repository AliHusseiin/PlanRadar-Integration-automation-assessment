package com.planradar.planradar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.List;

// Main request DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormCreationRequest {
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
        private String name;
        private Map<String, TypedFieldDTO> typedFields;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TypedFieldDTO {
        private String name;
        private String type;
        private int order;
        private List<String> visibleTo;
        private List<String> editableBy;
    }
}
