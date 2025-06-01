package com.diplom.toloka_bee_backend.controller;

import com.diplom.toloka_bee_backend.model.dto.LocationDTO;
import com.diplom.toloka_bee_backend.model.RequestModel;
import com.diplom.toloka_bee_backend.model.VolunteerWorkModel;
import com.diplom.toloka_bee_backend.model.dto.ChangeRequestStatusDTO;
import com.diplom.toloka_bee_backend.model.dto.RequestDTO;
import com.diplom.toloka_bee_backend.model.request_personalization.UserProfileStats;
import com.diplom.toloka_bee_backend.service.MongoRequestsService;
import com.diplom.toloka_bee_backend.service.VolunteerWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private MongoRequestsService mongoRequestsService;

    @Autowired
    private VolunteerWorkService volunteerWorkService;

    @PostMapping("/save")
    public RequestModel saveRequest(@RequestBody RequestDTO requestModel) {
        return mongoRequestsService.saveRequest(requestModel.toModel());
    }

    @PostMapping("/update")
    public void updateRequest(@RequestBody RequestDTO requestModel) {
        mongoRequestsService.updateRequest(requestModel.toModel());
    }

    @PutMapping("/update-status")
    public void updateRequestStatus(@RequestBody ChangeRequestStatusDTO dto) {
        mongoRequestsService.updateRequestStatus(dto.getRequestId(), dto.getStatus());
    }


    @PostMapping("/get-all")
    public List<RequestModel> getRequests(@RequestBody LocationDTO locationModel) {
        return mongoRequestsService.getAllRequests(locationModel);
    }

    @PostMapping("/get-personalized")
    public List<RequestModel> getPersonalizedRequests(@RequestBody LocationDTO locationDTO) {
        final List<VolunteerWorkModel> userWorkHistory = volunteerWorkService.getWorksByVolunteer(locationDTO.getUserId());
        final List<RequestModel> workHistoryRequests = userWorkHistory.stream().map(e -> mongoRequestsService.getRequestById(e.getRequestId())).filter(Objects::nonNull).toList();
        return mongoRequestsService.getPersonalizedRequests(locationDTO, UserProfileStats.fromWorkHistory(workHistoryRequests), locationDTO.getRadius(), userWorkHistory);
    }

    @GetMapping("/get/{id}")
    public RequestModel getRequestById(@PathVariable String id) {
        return mongoRequestsService.getRequestById(id);
    }


    @GetMapping("/get-by-user/{id}")
    public List<RequestModel> getRequestsByUserId(@PathVariable String id) {
        return mongoRequestsService.getRequestsByUserId(id);
    }

    @GetMapping("/count-by-user-today/{id}")
    public long getCountOfTodayRequestsByUserId(@PathVariable String id) {
        return mongoRequestsService.getCountOfTodayRequestsByUserId(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRequestById(@PathVariable String id) {
        mongoRequestsService.deleteRequestById(id);
    }

    @PostMapping("/get-by-ids")
    public List<RequestModel> getRequestsByIds(@RequestBody List<String> ids) {
        return mongoRequestsService.getRequestsByIds(ids);
    }
}

