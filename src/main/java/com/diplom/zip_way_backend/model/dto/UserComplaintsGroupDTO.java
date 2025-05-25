package com.diplom.zip_way_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserComplaintsGroupDTO {
    private String reportedUserId;
    private int totalComplaints;
    private List<UserComplaintDTO> complaints;
}
