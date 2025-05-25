package com.diplom.toloka_bee_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "user_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoUserData {
    @Id
    private String id;
    private String email;
    private String name;
    private String surname;
    private Date birthDate;
    private String position;
    private String about;
    private String photoId;
    private Date bannedUntil;


    public static MongoUserData fromUserData(UserData userData, String photoId) {
        return new MongoUserData(userData.getId(), userData.getEmail(),  userData.getName(),
                userData.getSurname(), userData.getBirthDate(), userData.getPosition(), userData.getAbout(),
                photoId, userData.getBannedUntil());
    }
}
