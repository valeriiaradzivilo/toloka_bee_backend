package com.diplom.toloka_bee_backend.model;
import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
