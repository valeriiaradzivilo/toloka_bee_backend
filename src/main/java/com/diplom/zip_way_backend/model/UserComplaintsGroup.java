package com.diplom.zip_way_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserComplaintsGroup {
    @NonNull
    private String reportedUserId;
    private int totalComplaints;
    private List<UserComplaintModel> complaints;
}
