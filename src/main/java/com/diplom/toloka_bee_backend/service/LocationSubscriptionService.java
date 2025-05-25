package com.diplom.toloka_bee_backend.service;

import com.diplom.toloka_bee_backend.model.LocationSubscriptionModel;
import com.diplom.toloka_bee_backend.repository.LocationSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.diplom.toloka_bee_backend.utils.AppUtils.distinctByKey;

@Service
public class LocationSubscriptionService {

    @Autowired
    private LocationSubscriptionRepository subscriptionRepository;

    public void subscribe(String userId, String location) {
        boolean alreadySubscribed = subscriptionRepository.findByUserIdAndTopic(userId, location).isPresent();

        if (!alreadySubscribed) {
            final LocationSubscriptionModel subscription = new LocationSubscriptionModel();
            subscription.setUserId(userId);
            subscription.setTopic(location);
            subscription.setCreatedAt(new java.util.Date());
            subscriptionRepository.save(subscription);
        }
    }

    public void unsubscribe(String userId, String location) {
        subscriptionRepository.deleteByUserIdAndTopic(userId, location);
    }

    public List<LocationSubscriptionModel> getSubscriptionByTopic(String location) {
        final List<LocationSubscriptionModel> subsByTopic = subscriptionRepository.findByTopic(location);
        final java.util.Date now = new java.util.Date();

        Stream<LocationSubscriptionModel> filteredByDate = subsByTopic.stream().filter(sub -> sub.getCreatedAt() != null && sub.getUserId() != null).
                filter(locationSubscriptionModel -> new java.util.Date(now.getTime() - locationSubscriptionModel.getCreatedAt().getTime()).getTime() < 172800000);

        Stream<LocationSubscriptionModel> distinctByUserId = filteredByDate.filter(distinctByKey(LocationSubscriptionModel::getUserId));

        return distinctByUserId
                .collect(Collectors.toList());

    }

    public void deleteByUserId(String userId) {
        subscriptionRepository.deleteByUserId(userId);
    }
}
