package com.example.userservice.entity.mongo;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Configuration {
    private String value;
    private PRODUCT_CONFIGURATION_TYPE name;
    private Integer extraPrice;


    public enum PRODUCT_CONFIGURATION_TYPE {
        STORAGE,
        MEMORY,
        PROCESSOR,
        SIZES_AND_WEIGHT ,
        DISPLAY ,
        RESISTANCE ,
        CAMERA,
        FRONT_CAMERA,
        VIDEO_RECORDING,
        FACE_ID,
        APPLE_PAY,
        CELLULAR_AND_WIRELESS,
        LOCATION,
        AUDIO_PLAYBACK,
        VIDEO_PLAYBACK,
        SIRI,
        POWER_AND_BATTERY,
        SENSORS,
        OPERATING_SYSTEM
    }
}
