package com.diplom.toloka_bee_backend.model.dto;

import com.diplom.toloka_bee_backend.model.RequestModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;


import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {
    private String id;
    @NonNull
    private String userId;
    @NonNull
    private String status;
    @NonNull
    private Date deadline;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint location;
    @JsonProperty("isRemote")
    private boolean isRemote;
    @Getter
    private boolean requiresPhysicalStrength;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer price;

    @NonNull
    private String description;
    @NonNull
    private Date createdAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date updatedAt;
    private int requiredVolunteersCount;
    @NonNull
    private String requestType;

    public RequestModel toModel() {
        RequestModel model = new RequestModel();
        model.setId(this.id);
        model.setUserId(this.userId);
        model.setStatus(this.status);
        model.setDeadline(this.deadline);
        model.setLocation(this.location);
        model.setRemote(this.isRemote);
        model.setRequiresPhysicalStrength(this.requiresPhysicalStrength);
        model.setPrice(this.price);
        model.setDescription(this.description);
        model.setCreatedAt(this.createdAt);
        model.setUpdatedAt(this.updatedAt);
        model.setRequiredVolunteersCount(this.requiredVolunteersCount);
        model.setRequestType(this.requestType);
        return model;
    }
}
