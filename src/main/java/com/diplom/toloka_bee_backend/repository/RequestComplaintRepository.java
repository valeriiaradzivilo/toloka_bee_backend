package com.diplom.toloka_bee_backend.repository;

import com.diplom.toloka_bee_backend.model.RequestComplaintModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RequestComplaintRepository extends MongoRepository<RequestComplaintModel, String> {
    boolean existsByReporterUserIdAndRequestId(String reporterUserId, String requestId);

    void deleteAllByReporterUserId(String reporterUserId);
}
