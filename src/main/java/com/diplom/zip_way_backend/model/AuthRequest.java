package com.diplom.zip_way_backend.model;
import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
