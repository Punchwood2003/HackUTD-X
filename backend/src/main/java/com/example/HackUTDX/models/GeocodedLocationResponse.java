package com.example.HackUTDX.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class GeocodedLocationResponse {
    private List<com.example.HackUTDX.models.GeocodedLocation> results;
    private String status;
}
