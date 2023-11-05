package com.example.HackUTDX.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Data
@Getter
@Setter
public class GeocodedLocation {
    private String name;
    private List<AddressComponent> address_components;
    private String formatted_address;
    private Geometry geometry;
    private String place_id;
    private PlusCode plus_code;
    private List<String> types;

    @Data
    @Getter
    @Setter
    public static class AddressComponent {
        private String long_name;
        private String short_name;
        private List<String> types;
    }

    @Data
    @Getter
    @Setter
    public static class Geometry {
        private Location location;
        private String location_type;
        private Viewport viewport;

        @Data
        @Getter
        @Setter
        public static class Location {
            private double lat;
            private double lng;
        }

        @Data
        @Getter
        @Setter
        public static class Viewport {
            private Northeast northeast;
            private Southwest southwest;

            @Data
            @Getter
            @Setter
            public static class Northeast {
                private double lat;
                private double lng;
            }

            @Data
            @Getter
            @Setter
            public static class Southwest {
                private double lat;
                private double lng;
            }
        }
    }

    @Data
    @Getter
    @Setter
    public static class PlusCode {
        private String compound_code;
        private String global_code;
    }
}