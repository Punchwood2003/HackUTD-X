package com.example.HackUTDX.models;
import lombok.Data;
import java.util.List;

@Data
public class PalmResponseModel {
    private List<Candidate> candidates;

    @Data
    public static class Candidate {
        private String output;
        private List<SafetyRating> safetyRatings;
    }

    @Data
    public static class SafetyRating {
        private String category;
        private String probability;
    }
}
