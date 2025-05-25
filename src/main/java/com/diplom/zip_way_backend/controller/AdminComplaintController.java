package com.diplom.zip_way_backend.controller;

import com.diplom.zip_way_backend.model.dto.RequestComplaintsGroupDTO;
import com.diplom.zip_way_backend.model.dto.UserComplaintsGroupDTO;
import com.diplom.zip_way_backend.service.AdminService;
import com.diplom.zip_way_backend.service.ComplaintService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/complaints")
public class AdminComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/grouped-requests")
    @Operation(summary = "Get grouped complaints for requests (admin only)")
    public List<RequestComplaintsGroupDTO> getGroupedRequestComplaints(@RequestParam String adminUserId) {
        if (!adminService.isAdmin(adminUserId)) {
            throw new RuntimeException("Access denied");
        }
        return complaintService.getRequestComplaintsGroupedDTO();
    }

    @GetMapping("/grouped-users")
    @Operation(summary = "Get grouped complaints for users (admin only)")
    public List<UserComplaintsGroupDTO> getGroupedUserComplaints(@RequestParam String adminUserId) {
        if (!adminService.isAdmin(adminUserId)) {
            throw new RuntimeException("Access denied");
        }
        return complaintService.getUserComplaintsGroupedDTO();
    }

    @DeleteMapping("/delete-request/{id}")
    @Operation(summary = "Delete a request complaint (admin only)")
    public void deleteRequestComplaint(@PathVariable String id) {
        complaintService.deleteRequestComplaint(id);
    }

    @DeleteMapping("/delete-user/{id}")
    @Operation(summary = "Delete a user complaint (admin only)")
    public void deleteUserComplaint(@PathVariable String id) {
        complaintService.deleteUserComplaint(id);
    }


}
