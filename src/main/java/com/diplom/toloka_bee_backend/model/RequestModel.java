package com.diplom.toloka_bee_backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "request_notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestModel {
    private String id;
    private String userId;
    private String status;
    private Date deadline;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint location;
    @JsonProperty("isRemote")
    private boolean isRemote;
    @Getter
    private boolean requiresPhysicalStrength;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer price;

    private String description;
    private Date createdAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date updatedAt;

    private int requiredVolunteersCount;

    private String requestType;


}