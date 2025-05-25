package com.diplom.toloka_bee_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationModel {
    private double latitude;
    private double longitude;
    private double radius;
    private boolean onlyRemote;
    private String userId;
}
