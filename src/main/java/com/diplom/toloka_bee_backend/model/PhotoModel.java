package com.diplom.toloka_bee_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("photos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoModel {

    @Id
    private String id;

    private String filename;

    private byte[] data;

    private String contentType;

}
