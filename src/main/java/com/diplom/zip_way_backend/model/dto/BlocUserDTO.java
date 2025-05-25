package com.diplom.zip_way_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlocUserDTO {
    private String userId;
    private String adminUserId;
    private int days;
}
