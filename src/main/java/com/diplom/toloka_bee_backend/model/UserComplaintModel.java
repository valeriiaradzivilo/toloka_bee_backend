package com.diplom.toloka_bee_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("user_complaints")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserComplaintModel {
    @Id
    private String id;

    private String reporterUserId;
    private String reportedUserId;
    private String reason;
    private Date createdAt;
}
