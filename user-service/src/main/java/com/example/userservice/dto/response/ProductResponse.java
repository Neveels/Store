package com.example.userservice.dto.response;

import com.example.userservice.entity.enums.PRODUCT_TYPE;
import com.example.userservice.entity.mongo.Color;
import com.example.userservice.entity.mongo.Configuration;
import lombok.Builder;

import java.util.List;

@Builder
public record ProductResponse(
        String id,
        String name,
        PRODUCT_TYPE type,
        Integer price,
        String display,
        String powerAndBattery,
        List<Configuration> configurations,
        List<Color> colors,
        String video,
        String preview,
        String sizesAndWeight,
        String resistance,
        String processor,
        String camera,
        String frontCamera,
        String videoRecording,
        String faceID,
        String applePay,
        String cellularAndWireless,
        String location,
        String audioPlayback,
        String videoPlayback,
        String siri,
        String sensors,
        String operatingSystem
) {
}
