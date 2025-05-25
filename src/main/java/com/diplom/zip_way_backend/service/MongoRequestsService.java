package com.diplom.zip_way_backend.service;

import com.diplom.zip_way_backend.model.LocationModel;
import com.diplom.zip_way_backend.model.RequestModel;
import com.diplom.zip_way_backend.model.VolunteerWorkModel;
import com.diplom.zip_way_backend.model.request_personalization.RequestRankingEngine;
import com.diplom.zip_way_backend.model.request_personalization.UserProfileStats;
import com.diplom.zip_way_backend.repository.RequestsDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MongoRequestsService {

    @Autowired
    private RequestsDataRepository mongoRequestsRepository;

    public RequestModel saveRequest(RequestModel request) {
        return mongoRequestsRepository.save(request);
    }

    public List<RequestModel> getAllRequests(LocationModel locationModel) {
        if (locationModel.isOnlyRemote()) {
            return mongoRequestsRepository.findByIsRemote(true);
        } else {
            Point userLocation = new Point(locationModel.getLongitude(), locationModel.getLatitude());
            Distance distance = new Distance(locationModel.getRadius(), Metrics.KILOMETERS);
            List<RequestModel> nearby = mongoRequestsRepository.findByLocationNear(userLocation, distance);
            List<RequestModel> remote = mongoRequestsRepository.findByIsRemote(true);
            Map<String, RequestModel> uniqueById = new LinkedHashMap<>();
            for (RequestModel r : nearby) uniqueById.put(r.getId(), r);
            for (RequestModel r : remote) uniqueById.put(r.getId(), r);

            ArrayList<RequestModel> results = new ArrayList<>(uniqueById.values());

            return results.stream().filter(request -> request.getDeadline().after(new Date()))
                    .filter(result -> !Objects.equals(result.getUserId().toLowerCase(), locationModel.getUserId().toLowerCase()))
                    .toList();
        }
    }

    public List<RequestModel> getPersonalizedRequests(LocationModel locationModel, UserProfileStats stats, double distanceKm, List<VolunteerWorkModel> volunteeringHistory) {
        List<RequestModel> all = getAllRequests(locationModel);
        List<String> helpingInProgress = volunteeringHistory.stream().map(VolunteerWorkModel::getRequestId).toList();
        List<RequestModel> notHelped = all.stream()
                .filter(request -> !helpingInProgress.contains(request.getId()))
                .toList();

        RequestRankingEngine engine = new RequestRankingEngine();
        return engine.rankRequests(notHelped, stats, distanceKm);
    }

    public RequestModel getRequestById(String id) {
        return mongoRequestsRepository.findById(id).orElse(null);
    }

    public void deleteRequestById(String id) {
        mongoRequestsRepository.deleteById(id);
    }

    public void updateRequest(RequestModel request) {
        mongoRequestsRepository.save(request);
    }


    public List<RequestModel> getRequestsByUserId(String id) {
        return mongoRequestsRepository.findAll().stream().filter(request -> request.getUserId().equals(id)).toList();
    }

    public List<RequestModel> getRequestsByIds(List<String> ids) {
        return mongoRequestsRepository.findAllById(ids).stream()
                .sorted(Comparator.comparing(RequestModel::getStatus))
                .collect(Collectors.toList());
    }

    public void deleteAllByUserId(String userId) {
        List<RequestModel> requests = mongoRequestsRepository.findAllByUserId(userId);
        mongoRequestsRepository.deleteAll(requests);
    }

    public void closeAllByUserId(String userId) {
        List<RequestModel> requests = mongoRequestsRepository.findAllByUserId(userId);
        for (RequestModel request : requests) {
            request.setStatus("cancelled");
            mongoRequestsRepository.save(request);
        }
    }

    public long getCountOfTodayRequestsByUserId(String id) {
        Date today = new Date();

        return mongoRequestsRepository.findAll().stream()
                .filter(request -> request.getUserId().equals(id) && request.getCreatedAt().after(today))
                .count();
    }

    public void updateRequestStatus(String id, String status) {
        RequestModel existingRequest = mongoRequestsRepository.findById(id).orElse(null);
        if (existingRequest != null) {
            existingRequest.setStatus(status);
            mongoRequestsRepository.save(existingRequest);
        }
    }
}