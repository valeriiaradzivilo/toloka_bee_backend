package com.diplom.zip_way_backend.controller;

import com.diplom.zip_way_backend.model.MongoUserData;
import com.diplom.zip_way_backend.model.PhotoModel;
import com.diplom.zip_way_backend.model.UserData;
import com.diplom.zip_way_backend.service.MongoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private MongoUserService mongoUserService;

    @GetMapping("/{id}")
    public UserData getUserById(@PathVariable String id) throws Exception {
        final Optional<MongoUserData> user = mongoUserService.getUserData(id);
        if (user.isPresent()) {
            final MongoUserData mongoUser = user.get();

            if (mongoUser.getPhotoId() != null) {
                PhotoModel photo = mongoUserService.getPhoto(mongoUser.getPhotoId());
                if (photo != null) {
                    String base64Photo = Base64.getEncoder().encodeToString(photo.getData());
                    return UserData.fromMongoUserData(mongoUser, base64Photo, photo.getContentType());
                }
            }

            return UserData.fromMongoUserData(mongoUser, "", "");

        }
        throw new Exception("User not found");
    }


}
