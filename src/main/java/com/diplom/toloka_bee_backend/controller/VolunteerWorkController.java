package com.diplom.toloka_bee_backend.controller;

import com.diplom.toloka_bee_backend.model.VolunteerWorkModel;
import com.diplom.toloka_bee_backend.model.dto.ConfirmVolunteerWorkDTO;
import com.diplom.toloka_bee_backend.model.dto.StartVolunteerWorkDTO;
import com.diplom.toloka_bee_backend.service.MongoRequestsService;
import com.diplom.toloka_bee_backend.service.VolunteerWorkService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/volunteer-work")
public class VolunteerWorkController {

    @Autowired
    private VolunteerWorkService volunteerWorkService;

    @Autowired
    private MongoRequestsService requestService;


    @PostMapping("/start")
    @Operation(summary = "Start a new volunteer work")
    public VolunteerWorkModel startWork(@RequestBody @Valid StartVolunteerWorkDTO dto) {
        return volunteerWorkService.startWork(dto.getVolunteerId(), dto.getRequesterId(), dto.getRequestId());
    }

    @PostMapping("/confirm/volunteer")
    @Operation(summary = "Volunteer confirms the work")
    public void confirmByVolunteer(@RequestBody @Valid ConfirmVolunteerWorkDTO dto) {
        volunteerWorkService.confirmByVolunteer(dto.getWorkId());
    }

    @PostMapping("/confirm/requester")
    @Operation(summary = "Requester confirms the work")
    public void confirmByRequester(@RequestBody @Valid ConfirmVolunteerWorkDTO dto) {
        volunteerWorkService.confirmByRequester(dto.getWorkId());
        requestService.updateRequestStatus(dto.getRequestId(), dto.getStatus());
    }

    @GetMapping("/volunteer/{volunteerId}")
    @Operation(summary = "Get all works by volunteer")
    public List<VolunteerWorkModel> getWorksByVolunteer(@PathVariable String volunteerId) {
        return volunteerWorkService.getWorksByVolunteer(volunteerId);
    }

    @GetMapping("/requester/{requesterId}")
    @Operation(summary = "Get all works by requester")
    public List<VolunteerWorkModel> getWorksByRequester(@PathVariable String requesterId) {
        return volunteerWorkService.getWorksByRequester(requesterId);
    }

    @GetMapping("/request/{requestId}")
    @Operation(summary = "Get all works by request id")
    public List<VolunteerWorkModel> getWorksByRequestId(@PathVariable String requestId) {
        return volunteerWorkService.getWorksByRequestId(requestId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get work by id")
    public Optional<VolunteerWorkModel> getWorkById(@PathVariable String id) {
        return volunteerWorkService.getWorkById(id);
    }

    @DeleteMapping("/cancel/{workId}")
    @Operation(summary = "Cancel a work by work id")
    public void cancelWork(@PathVariable String workId) {
        volunteerWorkService.cancelWork(workId);
    }
}
