package com.diplom.zip_way_backend.service;

import com.diplom.zip_way_backend.model.RequestComplaintModel;
import com.diplom.zip_way_backend.model.RequestComplaintsGroup;
import com.diplom.zip_way_backend.model.UserComplaintModel;
import com.diplom.zip_way_backend.model.UserComplaintsGroup;
import com.diplom.zip_way_backend.model.dto.RequestComplaintDTO;
import com.diplom.zip_way_backend.model.dto.RequestComplaintsGroupDTO;
import com.diplom.zip_way_backend.model.dto.UserComplaintDTO;
import com.diplom.zip_way_backend.model.dto.UserComplaintsGroupDTO;
import com.diplom.zip_way_backend.repository.RequestComplaintRepository;
import com.diplom.zip_way_backend.repository.UserComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ComplaintService {

    @Autowired
    private RequestComplaintRepository requestComplaintRepository;

    @Autowired
    private UserComplaintRepository userComplaintRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void submitRequestComplaint(String reporterUserId, String requestId, String reason) {
        RequestComplaintModel complaint = new RequestComplaintModel(
                null, reporterUserId, requestId, reason, new Date()
        );
        requestComplaintRepository.save(complaint);
    }

    public void submitUserComplaint(String reporterUserId, String reportedUserId, String reason) {
        UserComplaintModel complaint = new UserComplaintModel(
                null, reporterUserId, reportedUserId, reason, new Date()
        );
        userComplaintRepository.save(complaint);
    }

    public List<RequestComplaintModel> getAllRequestComplaints() {
        return requestComplaintRepository.findAll();
    }

    public List<UserComplaintModel> getAllUserComplaints() {
        return userComplaintRepository.findAll();
    }

    public List<RequestComplaintsGroup> getRequestComplaintsGrouped() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("requestId")
                        .first("requestId").as("requestId")
                        .count().as("totalComplaints")
                        .push("$$ROOT").as("complaints"),
                Aggregation.project("totalComplaints", "complaints")
                        .and("_id").as("requestId")
                        .andExclude("_id")
        );

        AggregationResults<RequestComplaintsGroup> results =
                mongoTemplate.aggregate(aggregation, "request_complaints", RequestComplaintsGroup.class);

        return results.getMappedResults();
    }

    public List<UserComplaintsGroup> getUserComplaintsGrouped() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("reportedUserId").ne(null)),
                Aggregation.group("reportedUserId")
                        .first("reportedUserId").as("reportedUserId")
                        .count().as("totalComplaints")
                        .push("$$ROOT").as("complaints"),
                Aggregation.project("totalComplaints", "complaints")
                        .and("_id").as("reportedUserId")
                        .andExclude("_id")
        );


        AggregationResults<UserComplaintsGroup> results =
                mongoTemplate.aggregate(aggregation, "user_complaints", UserComplaintsGroup.class);

        return results.getMappedResults();
    }

    public List<RequestComplaintsGroupDTO> getRequestComplaintsGroupedDTO() {
        List<RequestComplaintsGroup> groups = getRequestComplaintsGrouped();

        return groups.stream().map(group -> new RequestComplaintsGroupDTO(
                group.getRequestId(),
                group.getTotalComplaints(),
                group.getComplaints().stream().map(complaint -> new RequestComplaintDTO(
                        complaint.getId(),
                        complaint.getReporterUserId(),
                        complaint.getRequestId(),
                        complaint.getReason(),
                        complaint.getCreatedAt().toString()
                )).toList()
        )).toList();
    }

    public List<UserComplaintsGroupDTO> getUserComplaintsGroupedDTO() {
        List<UserComplaintsGroup> groups = getUserComplaintsGrouped();

        return groups.stream().map(group -> new UserComplaintsGroupDTO(
                group.getReportedUserId(),
                group.getTotalComplaints(),
                group.getComplaints().stream().map(complaint -> new UserComplaintDTO(
                        complaint.getId(),
                        complaint.getReporterUserId(),
                        complaint.getReportedUserId(),
                        complaint.getReason(),
                        complaint.getCreatedAt().toString()
                )).toList()
        )).toList();
    }

    public boolean hasReportedRequest(String reporterUserId, String requestId) {
        return requestComplaintRepository.existsByReporterUserIdAndRequestId(reporterUserId, requestId);
    }

    public boolean hasReportedUser(String reporterUserId, String reportedUserId) {
        return userComplaintRepository.existsByReporterUserIdAndReportedUserId(reporterUserId, reportedUserId);
    }

    public void deleteComplaintsByUserId(String userId) {
        userComplaintRepository.deleteAllByReporterUserIdOrReportedUserId(userId, userId);
        requestComplaintRepository.deleteAllByReporterUserId(userId);
    }

    public void deleteRequestComplaint(String id) {
        requestComplaintRepository.deleteById(id);
    }

    public void deleteUserComplaint(String id) {
        userComplaintRepository.deleteById(id);
    }


}
