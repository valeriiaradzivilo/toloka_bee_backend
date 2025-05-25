package com.diplom.toloka_bee_backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StartVolunteerWorkDTO {
    @NotBlank
    private String volunteerId;

    @NotBlank
    private String requesterId;

    @NotBlank
    private String requestId;
}
