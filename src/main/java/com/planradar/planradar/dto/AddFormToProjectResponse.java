package com.planradar.planradar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Response DTO for adding form to project
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddFormToProjectResponse {
    private DataDTO data;

    @Data
    public static class DataDTO {
        private String id;
        private String type;
        private AttributesDTO attributes;
        private RelationshipsDTO relationships;
    }

    @Data
    public static class AttributesDTO {
        private String id;
        private String ticketTypeId;
        private String projectId;
        private String updatedAt;
        private String createdAt;
        private List<String> requiredFields;
        private boolean canChange;
    }

    @Data
    public static class RelationshipsDTO {
        private ListsTicketTypeDTO listsTicketType;

        @Data
        public static class ListsTicketTypeDTO {
            private List<DataDTO> data;

            @Data
            public static class DataDTO {
                // Define fields if needed
            }
        }
    }
}
