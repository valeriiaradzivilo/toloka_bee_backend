package com.diplom.zip_way_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("location_subscription")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationSubscriptionModel {
    @Id
    private String id;
    private String topic;
    private String userId;

    @CreatedDate
    @Indexed(name = "createdAtIndex", expireAfter = "86400")
    private Date createdAt;
}
