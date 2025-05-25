package com.diplom.zip_way_backend.controller;

import com.diplom.zip_way_backend.model.LocationModel;
import com.diplom.zip_way_backend.model.RequestModel;
import com.diplom.zip_way_backend.model.VolunteerWorkModel;
import com.diplom.zip_way_backend.model.dto.ChangeRequestStatusDTO;
import com.diplom.zip_way_backend.model.request_personalization.UserProfileStats;
import com.diplom.zip_way_backend.service.MongoRequestsService;
import com.diplom.zip_way_backend.service.VolunteerWorkService;
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
    public RequestModel saveRequest(@RequestBody RequestModel requestModel) {
        return mongoRequestsService.saveRequest(requestModel);
    }

    @PostMapping("/update")
    public void updateRequest(@RequestBody RequestModel requestModel) {
        mongoRequestsService.updateRequest(requestModel);
    }

    @PutMapping("update-status")
    public void updateRequestStatus(@RequestBody ChangeRequestStatusDTO dto) {
        mongoRequestsService.updateRequestStatus(dto.getRequestId(), dto.getStatus());
    }


    @PostMapping("/get-all")
    public List<RequestModel> getRequests(@RequestBody LocationModel locationModel) {
        return mongoRequestsService.getAllRequests(locationModel);
    }

    @PostMapping("/get-personalized")
    public List<RequestModel> getPersonalizedRequests(@RequestBody LocationModel locationModel) {
        final List<VolunteerWorkModel> userWorkHistory = volunteerWorkService.getWorksByVolunteer(locationModel.getUserId());
        final List<RequestModel> workHistoryRequests = userWorkHistory.stream().map(e -> mongoRequestsService.getRequestById(e.getRequestId())).filter(Objects::nonNull).toList();
        return mongoRequestsService.getPersonalizedRequests(locationModel, UserProfileStats.fromWorkHistory(workHistoryRequests), locationModel.getRadius(), userWorkHistory);
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

