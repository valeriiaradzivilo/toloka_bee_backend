package com.diplom.toloka_bee_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserComplaintDTO {
    private String id;
    private String reporterUserId;
    private String reportedUserId;
    private String reason;
    private String createdAt;
}