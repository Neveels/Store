package com.example.userservice.entity.mongo;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Color {
    private String name;
    private String value;
    private List<String> photos;
}
