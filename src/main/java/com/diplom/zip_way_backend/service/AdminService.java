package com.diplom.zip_way_backend.service;

import com.diplom.zip_way_backend.model.AdminUserModel;
import com.diplom.zip_way_backend.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminUserRepository adminUserRepository;

    public boolean isAdmin(String userId) {
        return adminUserRepository.existsByUserId(userId);
    }

    public void addAdmin(String userId) {
        if (!adminUserRepository.existsByUserId(userId)) {
            adminUserRepository.save(new AdminUserModel(null, userId));
        }
    }

    public void removeAdmin(String userId) {
        if (adminUserRepository.existsByUserId(userId)) {
            adminUserRepository.deleteByUserId(userId);
        }
    }
}
