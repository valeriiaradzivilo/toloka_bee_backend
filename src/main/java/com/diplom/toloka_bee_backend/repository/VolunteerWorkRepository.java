package com.diplom.toloka_bee_backend.repository;

import com.diplom.toloka_bee_backend.model.VolunteerWorkModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VolunteerWorkRepository extends MongoRepository<VolunteerWorkModel, String> {
    List<VolunteerWorkModel> findByVolunteerId(String volunteerId);

    List<VolunteerWorkModel> findByRequesterId(String requesterId);

    void deleteAllByVolunteerId(String volunteerId);

    List<VolunteerWorkModel> findByRequestId(String requestId);
}
