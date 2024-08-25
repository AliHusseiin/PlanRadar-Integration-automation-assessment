package com.planradar.planradar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentCreationRequest {
    private List<DataDTO> data;

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
        private List<LayerDTO> layers;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LayerDTO {
        private String name;
    }
}
