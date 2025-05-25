package com.diplom.zip_way_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    @Id
    private String id;
    private String email;
    private String name;
    private String surname;
    private Date birthDate;
    private String position;
    private String about;
    private String photo;
    private String photoFormat;
    private Date bannedUntil;


    static public UserData fromMongoUserData(MongoUserData user, String photo, String photoFormat) {
        return new UserData(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getSurname(),
                user.getBirthDate(),
                user.getPosition(),
                user.getAbout(),
                photo,
                photoFormat,
                user.getBannedUntil()
        );
    }

    public UserData copyWithId(String id) {
        return new UserData(id, email, name, surname, birthDate, position, about, photo, photoFormat, bannedUntil);
    }
}