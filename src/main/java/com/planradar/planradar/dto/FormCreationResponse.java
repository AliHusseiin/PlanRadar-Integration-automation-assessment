package com.planradar.planradar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Response DTO for form creation
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormCreationResponse {
    private DataDTO data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataDTO {
        private String id;
        private String type;
        private AttributesDTO attributes;
        private RelationshipsDTO relationships;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AttributesDTO {
        private String name;
        private String customerId;
        private String authorId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RelationshipsDTO {
        private AuthorDTO author;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class AuthorDTO {
            private DataDTO data;

            @Data
            @AllArgsConstructor
            @NoArgsConstructor
            public static class DataDTO {
                private String id;
                private String type;
            }
        }
    }
}
