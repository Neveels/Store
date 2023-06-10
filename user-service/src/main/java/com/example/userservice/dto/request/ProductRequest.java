package com.example.userservice.dto.request;

import com.example.userservice.entity.enums.PRODUCT_TYPE;
import com.example.userservice.entity.mongo.Color;
import com.example.userservice.entity.mongo.Configuration;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductRequest {
    private String id;
    private String name;
    private PRODUCT_TYPE type;
    private Integer price;
    private String display;
    private String powerAndBattery;
    private List<Configuration> configurations;
    private List<Color> colors;
    private String video;
    private String preview;
    private String sizesAndWeight;
    private String resistance;
    private String processor;
    private String camera;
    private String frontCamera;
    private String videoRecording;
    private String faceID;
    private String applePay;
    private String cellularAndWireless;
    private String location;
    private String audioPlayback;
    private String videoPlayback;
    private String siri;
    private String sensors;
    private String operatingSystem;
}
