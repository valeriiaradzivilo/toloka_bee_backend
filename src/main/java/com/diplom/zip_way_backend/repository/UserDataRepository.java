package com.diplom.zip_way_backend.repository;

import com.diplom.zip_way_backend.model.MongoUserData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDataRepository extends MongoRepository<MongoUserData, String> {


}
