package com.diplom.toloka_bee_backend.model.dto;

import com.diplom.toloka_bee_backend.model.RequestModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;


import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {
    private String id;
    private String userId;
    private String status;
    private Date deadline;
    private GeoJsonPoint location;
    private boolean isRemote;
    @Getter
    private boolean requiresPhysicalStrength;
    private Integer price;

    private String description;
    private Date createdAt;
    private Date updatedAt;

    private int requiredVolunteersCount;

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
