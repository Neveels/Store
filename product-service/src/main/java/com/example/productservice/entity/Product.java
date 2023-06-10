package com.example.productservice.entity;

import com.sun.jdi.connect.Connector;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/10/2023 1:12 PM
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document()
public class Product {
    @MongoId
    private String id;
    private String name;
    private Type type;
    private Double price;
    private String display;
    private String powerAndBattery;
    private List<Configuration> configurationList;
    private List<Color> colorList;

    public enum Type {
        iPhone,
        Mac,
        iPad,
        Watch,
        AirPods
    }


}
