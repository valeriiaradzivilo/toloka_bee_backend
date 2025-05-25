package com.diplom.zip_way_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestComplaintDTO {
    private String id;
    private String reporterUserId;
    private String requestId;
    private String reason;
    private String createdAt;
}


