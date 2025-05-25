package com.diplom.zip_way_backend.service;

import com.diplom.zip_way_backend.model.ContactInfoModel;
import com.diplom.zip_way_backend.repository.ContactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactInfoService {

    @Autowired
    private ContactInfoRepository contactInfoRepository;

    public ContactInfoModel saveContactInfo(ContactInfoModel info) {
        return contactInfoRepository.save(info);
    }

    public void updateContactInfo(ContactInfoModel info) {
        contactInfoRepository.save(info);
    }

    public ContactInfoModel getContactInfoById(String id) {
        return contactInfoRepository.findById(id).orElse(null);
    }

    public ContactInfoModel getContactInfoByUserId(String userId) {
        return contactInfoRepository.findByUserId(userId);
    }

    public void deleteContactInfoById(String id) {
        contactInfoRepository.deleteById(id);
    }

    public void deleteByUserId(String userId) {
        contactInfoRepository.deleteByUserId(userId);
    }
}
