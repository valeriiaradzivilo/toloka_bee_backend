package com.diplom.zip_way_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcceptRequestModel {
    private String requestId;
    private String volunteerId;
    private String status;
}
