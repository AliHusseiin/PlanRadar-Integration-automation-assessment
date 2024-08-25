package com.planradar.planradar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Main response DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentCreationResponse {

    private List<DataDTO> data;
    private List<IncludedDTO> included;
    private MetaDTO meta;

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
        @JsonProperty("id")
        private String id;

        @JsonProperty("project-id")
        private String projectId;

        @JsonProperty("user-id")
        private String userId;

        @JsonProperty("created-on")
        private String createdOn;

        private String name;
        private String description;

        @JsonProperty("updated-on")
        private String updatedOn;

        @JsonProperty("parent-id")
        private String parentId;

        private int order;

        @JsonProperty("last-plan-uploaded")
        private String lastPlanUploaded;

        @JsonProperty("component-type")
        private int componentType;

        @JsonProperty("is-processed")
        private boolean isProcessed;

        @JsonProperty("node-id")
        private String nodeId;

        @JsonProperty("uuid")
        private String uuid;

        @JsonProperty("ml-uuid")
        private String mlUuid;

        @JsonProperty("has-runs")
        private boolean hasRuns;

        @JsonProperty("runs-count")
        private int runsCount;

        @JsonProperty("planfile-file-name")
        private String planfileFileName;

        @JsonProperty("root-node")
        private boolean rootNode;

        @JsonProperty("plan-thumb-big-url")
        private String planThumbBigUrl;

        @JsonProperty("plan-thumb-small-url")
        private String planThumbSmallUrl;

        @JsonProperty("has-failed")
        private boolean hasFailed;

        @JsonProperty("first-inactive-processed-plan")
        private InactiveProcessedPlanDTO firstInactiveProcessedPlan;

        @JsonProperty("has-inactive-processed-plans")
        private boolean hasInactiveProcessedPlans;

        @JsonProperty("model-info-url")
        private String modelInfoUrl;

        private CalibrationDTO calibration;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InactiveProcessedPlanDTO {
        private String plan;
        @JsonProperty("tile-hash")
        private String tileHash;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CalibrationDTO {
        private boolean scale;
        private boolean north;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RelationshipsDTO {
        private RelationshipDataDTO project;
        private RelationshipDataDTO plan;
        private RelationshipDataDTO parent;
        private ChildrenDTO children;
        private PlansDTO plans;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RelationshipDataDTO {
        private String id;
        private String type;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChildrenDTO {
        private List<RelationshipDataDTO> data;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PlansDTO {
        private List<RelationshipDataDTO> data;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IncludedDTO {
        private String id;
        private String type;
        private AttributesDTO attributes;
        private RelationshipsDTO relationships;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MetaDTO {
        @JsonProperty("created_plan_ids")
        private List<String> createdPlanIds;
    }
}
