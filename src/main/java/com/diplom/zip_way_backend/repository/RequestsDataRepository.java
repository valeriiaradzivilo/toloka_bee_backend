package com.diplom.zip_way_backend.repository;

import com.diplom.zip_way_backend.model.RequestModel;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface RequestsDataRepository extends MongoRepository<RequestModel, String> {
    List<RequestModel> findByLocationNear(Point point, Distance distance);

    List<RequestModel> findByIsRemote(boolean remote);

    List<RequestModel> findAllByUserId(String userId);

    List<RequestModel> findByDeadlineBefore(Date deadline);


}
