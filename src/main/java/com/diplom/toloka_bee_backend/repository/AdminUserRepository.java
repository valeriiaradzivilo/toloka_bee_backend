package com.diplom.toloka_bee_backend.repository;

import com.diplom.toloka_bee_backend.model.AdminUserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminUserRepository extends MongoRepository<AdminUserModel, String> {
    Optional<AdminUserModel> findByUserId(String userId);

    void deleteByUserId(String userId);

    boolean existsByUserId(String userId);
}
