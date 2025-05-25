package com.diplom.toloka_bee_backend.controller;

import com.diplom.toloka_bee_backend.model.dto.RequestComplaintDTO;
import com.diplom.toloka_bee_backend.model.dto.UserComplaintDTO;
import com.diplom.toloka_bee_backend.service.ComplaintService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @PostMapping("/request")
    @Operation(summary = "Submit a complaint on a request")
    public void submitRequestComplaint(@RequestBody RequestComplaintDTO dto) throws Exception {
        boolean alreadyReported = complaintService.hasReportedRequest(dto.getReporterUserId(), dto.getRequestId());
        if (!alreadyReported) {
            complaintService.submitRequestComplaint(dto.getReporterUserId(), dto.getRequestId(), dto.getReason());
        } else {
            throw new Exception("Already reported");
        }

    }

    @PostMapping("/user")
    @Operation(summary = "Submit a complaint on a user")
    public void submitUserComplaint(@RequestBody UserComplaintDTO dto) {
        boolean alreadyReported = complaintService.hasReportedUser(dto.getReporterUserId(), dto.getReportedUserId());
        if (!alreadyReported) {
            complaintService.submitUserComplaint(dto.getReporterUserId(), dto.getReportedUserId(), dto.getReason());
        } else {
            throw new RuntimeException("Already reported");
        }
    }
}
