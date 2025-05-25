package com.diplom.zip_way_backend.repository;


import com.diplom.zip_way_backend.model.UserComplaintModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserComplaintRepository extends MongoRepository<UserComplaintModel, String> {
    boolean existsByReporterUserIdAndReportedUserId(String reporterUserId, String reportedUserId);

    void deleteAllByReporterUserIdOrReportedUserId(String reporterUserId, String reportedUserId);
}
