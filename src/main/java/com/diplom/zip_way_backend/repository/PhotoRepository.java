package com.diplom.zip_way_backend.repository;

import com.diplom.zip_way_backend.model.PhotoModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<PhotoModel, String> {
}
