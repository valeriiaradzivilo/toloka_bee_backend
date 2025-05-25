package com.diplom.zip_way_backend.repository;

import com.diplom.zip_way_backend.model.ContactInfoModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactInfoRepository extends MongoRepository<ContactInfoModel, String> {
    ContactInfoModel findByUserId(String userId);

    void deleteByUserId(String userId);
}
