package com.diplom.toloka_bee_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRequestStatusDTO {
    private String requestId;
    private String status;
}
