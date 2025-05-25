package com.diplom.toloka_bee_backend.repository;

import com.diplom.toloka_bee_backend.model.MongoUserData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDataRepository extends MongoRepository<MongoUserData, String> {


}
