package com.diplom.zip_way_backend.service;

import com.diplom.zip_way_backend.model.MongoUserData;
import com.diplom.zip_way_backend.model.PhotoModel;
import com.diplom.zip_way_backend.model.UserData;
import com.diplom.zip_way_backend.repository.PhotoRepository;
import com.diplom.zip_way_backend.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
public class MongoUserService {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private PhotoRepository photoRepository;

    public void saveUserData(MongoUserData data) {
        userDataRepository.save(data);
    }

    public Optional<MongoUserData> getUserData(String id) {
        return userDataRepository.findById(id);
    }

    public PhotoModel savePhoto(PhotoModel photo) {
        return photoRepository.save(photo);
    }

    public void deletePhotoById(String id) {
        photoRepository.deleteById(id);
    }

    public PhotoModel getPhoto(String id) {
        return photoRepository.findById(id).orElse(null);
    }

    public void deleteUser(String id) {
        final Optional<MongoUserData> userData = userDataRepository.findById(id);
        if (userData.isPresent()) {
            userDataRepository.deleteById(id);
            photoRepository.deleteById(userData.get().getPhotoId());
        }
    }

    public void updateUser(UserData data) {
        final Optional<MongoUserData> userData = userDataRepository.findById(data.getId());
        if (userData.isPresent()) {
            String base64Photo = data.getPhoto();

            if (base64Photo != null && !base64Photo.isEmpty()) {
                if (!userData.get().getPhotoId().isEmpty())
                    deletePhotoById(userData.get().getPhotoId());
                
                byte[] decoded = Base64.getDecoder().decode(base64Photo);

                PhotoModel photo = new PhotoModel();
                photo.setContentType(data.getPhotoFormat());
                photo.setData(decoded);

                String photoId = savePhoto(photo).getId();

                userDataRepository.save(MongoUserData.fromUserData(data, photoId));
                return;
            }


        }

        throw new RuntimeException("User not found");
    }

    public void updateUserPhoto(String userId, String photoId) {
        final Optional<MongoUserData> userData = userDataRepository.findById(userId);
        if (userData.isPresent()) {
            MongoUserData mongoUser = userData.get();
            mongoUser.setPhotoId(photoId);
            userDataRepository.save(mongoUser);
        } else {
            throw new RuntimeException("User not found");
        }
    }
}