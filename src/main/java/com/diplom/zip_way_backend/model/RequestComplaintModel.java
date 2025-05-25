package com.diplom.zip_way_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("request_complaints")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestComplaintModel {
    @Id
    private String id;

    private String reporterUserId;
    private String requestId;
    private String reason;
    private Date createdAt;
}
