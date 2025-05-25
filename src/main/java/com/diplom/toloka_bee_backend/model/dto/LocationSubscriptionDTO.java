package com.diplom.toloka_bee_backend.model.dto;


import com.diplom.toloka_bee_backend.model.LocationSubscriptionModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class LocationSubscriptionDTO {

    private String id;
    @Getter
    @Setter
    private String topic;
    @Getter
    @Setter
    private String userId;
    private Date createdAt;

    public LocationSubscriptionModel toLocationSubscriptionModel() {
        LocationSubscriptionModel model = new LocationSubscriptionModel();
        model.setId(this.id);
        model.setTopic(this.topic);
        model.setUserId(this.userId);
        model.setCreatedAt(this.createdAt);
        return model;
    }
}
