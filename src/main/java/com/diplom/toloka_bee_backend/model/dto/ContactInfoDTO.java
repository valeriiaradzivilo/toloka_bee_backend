package com.diplom.toloka_bee_backend.model.dto;

import com.diplom.toloka_bee_backend.model.ContactInfoModel;
import com.diplom.toloka_bee_backend.model.ContactMethod;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ContactInfoDTO {
    private String id;

    private String userId;

    private ContactMethod preferredMethod;
    private String phone;
    private String email;
    private String viber;
    private String telegram;
    private String whatsapp;


    public ContactInfoModel toContactInfoModel() {
        ContactInfoModel contactInfoModel = new ContactInfoModel();
        contactInfoModel.setId(this.id);
        contactInfoModel.setUserId(this.userId);
        contactInfoModel.setPreferredMethod(this.preferredMethod);
        contactInfoModel.setPhone(this.phone);
        contactInfoModel.setEmail(this.email);
        contactInfoModel.setViber(this.viber);
        contactInfoModel.setTelegram(this.telegram);
        contactInfoModel.setWhatsapp(this.whatsapp);
        return contactInfoModel;
    }
}
