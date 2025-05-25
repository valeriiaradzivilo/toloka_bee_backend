package com.diplom.toloka_bee_backend.controller;

import com.diplom.toloka_bee_backend.model.MongoUserData;
import com.diplom.toloka_bee_backend.model.PhotoModel;
import com.diplom.toloka_bee_backend.model.UserData;
import com.diplom.toloka_bee_backend.service.FirebaseTokenService;
import com.diplom.toloka_bee_backend.service.MongoUserService;
import com.diplom.toloka_bee_backend.service.UserDeletionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private MongoUserService mongoUserService;

    @Autowired
    private UserDeletionService userDeletionService;

    @Autowired
    private FirebaseTokenService firebaseTokenService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Creates a new user in Firebase and MongoDB")
    public String registerUser(@RequestBody UserData userData) throws Exception {
        String base64Photo = userData.getPhoto();
        String photoId;

        if (base64Photo != null && !base64Photo.isEmpty()) {
            byte[] decoded = Base64.getDecoder().decode(base64Photo);

            PhotoModel photo = new PhotoModel();
            photo.setContentType(userData.getPhotoFormat());
            photo.setData(decoded);

            photoId = mongoUserService.savePhoto(photo).getId();

            MongoUserData mongoUser = MongoUserData.fromUserData(userData, photoId);
            mongoUser.setPhotoId(photoId);

            mongoUserService.saveUserData(mongoUser);
            return mongoUser.getId();
        }

        throw new Exception("Unable to register user");

    }

    @PostMapping("/login")
    @Operation(summary = "Login a user", description = "Logs in a user using email and password")
    public UserData loginUser(@RequestBody Map<String, String> data) throws Exception {
        final Optional<MongoUserData> user = mongoUserService.getUserData(data.get("id"));
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
        System.out.println(data);
        throw new Exception("Invalid credentials");

    }


    @PutMapping("/update")
    @Operation(summary = "Update user data", description = "Updates user data in MongoDB")
    public void updateUser(@RequestBody UserData userData) {
        mongoUserService.updateUser(userData);

    }

    @PostMapping("/delete/{id}")
    @Operation(summary = "Delete user completely", description = "Deletes a user and all related data")
    public void deleteUser(@PathVariable String id) {
        userDeletionService.deleteUserCompletely(id);
    }


    @PutMapping("/update-photo")
    @Operation(summary = "Update user photo", description = "Updates user photo in MongoDB")
    public void updateUserPhoto(@RequestBody Map<String, String> data) {
        String userId = data.get("userId");
        String base64Photo = data.get("photo");
        String photoId;

        if (base64Photo != null && !base64Photo.isEmpty()) {
            byte[] decoded = Base64.getDecoder().decode(base64Photo);

            PhotoModel photo = new PhotoModel();
            photo.setContentType(data.get("photoFormat"));
            photo.setData(decoded);

            photoId = mongoUserService.savePhoto(photo).getId();

            mongoUserService.updateUserPhoto(userId, photoId);
        }
    }


    @GetMapping("/token")
    public String getAccessToken() throws IOException {
        return firebaseTokenService.getAccessToken();
    }

}