package com.example.userservice.entity.mongo;

import com.example.userservice.entity.enums.PRODUCT_TYPE;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document()
@ToString
public class Product {
    @MongoId
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
