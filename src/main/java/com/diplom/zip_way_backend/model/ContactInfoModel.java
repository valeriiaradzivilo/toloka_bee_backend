package com.diplom.zip_way_backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contact_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfoModel {
    @Id
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
}
