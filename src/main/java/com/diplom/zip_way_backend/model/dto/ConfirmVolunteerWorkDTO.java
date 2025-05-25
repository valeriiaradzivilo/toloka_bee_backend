package com.diplom.zip_way_backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmVolunteerWorkDTO {
    @NotBlank
    private String workId;
}
