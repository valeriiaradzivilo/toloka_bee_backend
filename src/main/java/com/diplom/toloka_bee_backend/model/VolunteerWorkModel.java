package com.diplom.toloka_bee_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "volunteer_work")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerWorkModel {
    @Id
    private String id;

    private String volunteerId;
    private String requesterId;
    private String requestId;

    private Date startedAt;
    private Date finishedAt;

    private boolean volunteerConfirmed;
    private boolean requesterConfirmed;
}
