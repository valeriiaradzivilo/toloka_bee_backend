package com.diplom.toloka_bee_backend.repository;

import com.diplom.toloka_bee_backend.model.PhotoModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<PhotoModel, String> {
}
