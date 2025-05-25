package com.diplom.toloka_bee_backend.controller;

import com.diplom.toloka_bee_backend.model.ContactInfoModel;
import com.diplom.toloka_bee_backend.model.dto.ContactInfoDTO;
import com.diplom.toloka_bee_backend.service.ContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactInfoService contactInfoService;


    @PostMapping("/save")
    public ContactInfoModel saveContact(@RequestBody ContactInfoDTO model) {
        return contactInfoService.saveContactInfo(model.toContactInfoModel());
    }


    @PostMapping("/update")
    public void updateContact(@RequestBody ContactInfoDTO model) {
        contactInfoService.updateContactInfo(model.toContactInfoModel());
    }


    @GetMapping("/get/{id}")
    public ContactInfoModel getContactById(@PathVariable String id) {
        return contactInfoService.getContactInfoById(id);
    }


    @GetMapping("/get-by-user/{userId}")
    public ContactInfoModel getContactByUser(@PathVariable String userId) {
        return contactInfoService.getContactInfoByUserId(userId);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteContact(@PathVariable String id) {
        contactInfoService.deleteContactInfoById(id);
    }
}
