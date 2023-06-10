package com.example.userservice.dto.response;

import com.example.userservice.entity.mongo.Configuration;
import lombok.Builder;

import java.util.List;

@Builder
public record CartResponse(
        String id,
        String name,
        String preview,
        Double price,
        String color,
        List<Configuration> configurations
) {
}
