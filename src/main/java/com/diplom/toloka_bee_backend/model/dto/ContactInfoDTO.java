package com.diplom.toloka_bee_backend.model.dto;

import com.diplom.toloka_bee_backend.model.ContactInfoModel;
import com.diplom.toloka_bee_backend.model.ContactMethod;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfoDTO {
    private String id;

    private String userId;

    private ContactMethod preferredMethod;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phone;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String viber;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String telegram;
    @JsonInclude(JsonInclude.Include.NON_NULL)
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
