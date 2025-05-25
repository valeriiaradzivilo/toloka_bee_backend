package com.diplom.zip_way_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestComplaintsGroupDTO {
    private String requestId;
    private int totalComplaints;
    private List<RequestComplaintDTO> complaints;
}
