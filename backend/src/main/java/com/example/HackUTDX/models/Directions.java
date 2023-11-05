package com.example.HackUTDX.models;

import lombok.Data;
import java.util.List;

@Data
public class Directions {
    private List<Route> routes;
    private String status;

    @Data
    public static class Route {
        private Bounds bounds;
        private List<Leg> legs;
        private Polyline overview_polyline;
        private String summary;
    }

    @Data
    public static class Leg {
        private Distance distance;
        private Duration duration;
        private String end_address;
        private Location end_location;
        private String start_address;
        private Location start_location;
        private List<Step> steps;
    }

    @Data
    public static class Step {
        private Distance distance;
        private Duration duration;
        private Location end_location;
        private String html_instructions;
        private Polyline polyline;
        private Location start_location;
        private String travel_mode;
    }

    @Data
    public static class Duration {
        private String text;
        private long value; // Duration in seconds
    }

    @Data
    public static class Distance {
        private String text;
        private long value; // Distance in meters
    }

    @Data
    public static class Location {
        private double lat;
        private double lng;
    }

    @Data
    public static class Bounds {
        private Location northeast;
        private Location southwest;
    }

    @Data
    public static class Polyline {
        private String points;
    }
}