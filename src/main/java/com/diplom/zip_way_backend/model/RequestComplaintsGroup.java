package com.diplom.zip_way_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestComplaintsGroup {
    private String requestId;
    private int totalComplaints;
    private List<RequestComplaintModel> complaints;
}
