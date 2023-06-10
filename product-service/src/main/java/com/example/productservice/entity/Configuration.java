package com.example.productservice.entity;

import lombok.*;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/10/2023 1:29 PM
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Configuration {
    private String value;
    private ProductConfiguration name;
    private Double extraPrice;


    public enum ProductConfiguration {
        STORAGE,
        MEMORY,
        PROCESSOR
    }
}
