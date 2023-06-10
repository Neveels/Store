package com.example.productservice.entity;

import lombok.*;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/10/2023 1:38 PM
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Color {
    private String name;
    private String value;
}
