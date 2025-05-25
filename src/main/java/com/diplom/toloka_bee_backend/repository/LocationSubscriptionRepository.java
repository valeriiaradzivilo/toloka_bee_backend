package com.diplom.toloka_bee_backend.repository;

import com.diplom.toloka_bee_backend.model.LocationSubscriptionModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface LocationSubscriptionRepository extends MongoRepository<LocationSubscriptionModel, String> {
    List<LocationSubscriptionModel> findByTopic(String locationSubscription);

    Optional<LocationSubscriptionModel> findByUserIdAndTopic(String userId, String locationSubscription);

    void deleteByUserIdAndTopic(String userId, String locationSubscription);

    void deleteByUserId(String userId);
}
