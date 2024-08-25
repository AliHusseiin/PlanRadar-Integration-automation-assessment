package com.planradar.planradar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

// Main response DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
    private List<ProjectData> data;
    private List<IncludedData> included;
    private Meta meta;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProjectData {
        private String id;
        private String type;
        private ProjectAttributes attributes;
        private Map<String, Object> relationships;  // Simplified relationships as Map

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class ProjectAttributes {
            private String id;
            private String name;
            private String parentIdHashed;
            private String homepage;
            private String country;
            private String projectnumber;
            private String createdOn;
            private String updatedOn;
            private String drstartDate;
            private List<Object> typedFields;
            private String drendDate;
            private String description;
            private int runningNumber;
            private String accessToken;
            private String city;
            private String zipcode;
            private String street;
            private int status;
            private String authorId;
            private String scheduleStartDate;
            private Map<String, Object> typedValues;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IncludedData {
        private String id;
        private String type;
        private Map<String, Object> attributes;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Meta {
        private int count;
        private String page;
        private Map<String, Object> customerPermission;
    }
}

