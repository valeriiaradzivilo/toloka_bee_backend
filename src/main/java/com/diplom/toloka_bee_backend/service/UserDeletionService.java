package com.diplom.toloka_bee_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDeletionService {

    @Autowired
    private MongoUserService mongoUserService;
    @Autowired
    private VolunteerWorkService volunteerWorkService;
    @Autowired
    private MongoRequestsService mongoRequestsService;
    @Autowired
    private ContactInfoService contactService;
    @Autowired
    private ComplaintService complaintService;
    @Autowired
    private LocationSubscriptionService locationSubscriptionService;

    public void deleteUserCompletely(String userId) {
        mongoUserService.deleteUser(userId);
        volunteerWorkService.deleteByUserId(userId);
        mongoRequestsService.closeAllByUserId(userId);
        contactService.deleteByUserId(userId);
        complaintService.deleteComplaintsByUserId(userId);
        locationSubscriptionService.deleteByUserId(userId);
    }
}
