package com.diplom.toloka_bee_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelHelpingDTO {
    private String workId;
    private String newStatus;
}
