package com.diplom.zip_way_backend.service;

import com.diplom.zip_way_backend.model.MongoUserData;
import com.diplom.zip_way_backend.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
public class BlockUserService {

    @Autowired
    private UserDataRepository mongoUserRepository;

    public void blockUser(String userId, int days) {
        Optional<MongoUserData> userOptional = mongoUserRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        MongoUserData user = userOptional.get();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        user.setBannedUntil(calendar.getTime());

        mongoUserRepository.save(user);
    }

    public void blockUserPermanently(String userId) {
        Optional<MongoUserData> userOptional = mongoUserRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        MongoUserData user = userOptional.get();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 100);
        user.setBannedUntil(calendar.getTime());

        mongoUserRepository.save(user);
    }

    public void unblockUser(String userId) {
        Optional<MongoUserData> userOptional = mongoUserRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        MongoUserData user = userOptional.get();
        user.setBannedUntil(null);

        mongoUserRepository.save(user);
    }
}
