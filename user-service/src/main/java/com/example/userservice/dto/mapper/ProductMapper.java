package com.example.userservice.dto.mapper;

import com.example.userservice.dto.request.ProductRequest;
import com.example.userservice.dto.response.ProductResponse;
import com.example.userservice.entity.mongo.Product;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class ProductMapper {
    public ProductResponse toResponseDto(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .colors(product.getColors())
                .powerAndBattery(product.getPowerAndBattery())
                .price(product.getPrice())
                .type(product.getType())
                .name(product.getName())
                .configurations(product.getConfigurations())
                .video(product.getVideo())
                .preview(product.getPreview())
                .sizesAndWeight(product.getSizesAndWeight())
                .resistance(product.getResistance())
                .processor(product.getProcessor())
                .camera(product.getCamera())
                .frontCamera(product.getFrontCamera())
                .videoRecording(product.getVideoRecording())
                .faceID(product.getFaceID())
                .applePay(product.getApplePay())
                .cellularAndWireless(product.getCellularAndWireless())
                .location(product.getLocation())
                .audioPlayback(product.getAudioPlayback())
                .videoPlayback(product.getVideoPlayback())
                .siri(product.getSiri())
                .sensors(product.getSensors())
                .operatingSystem(product.getOperatingSystem())
                .build();
    }

    public Product toProduct(ProductRequest productRequest) {
        return Product.builder()
                .id(productRequest.getId())
                .colors(productRequest.getColors())
                .configurations(productRequest.getConfigurations())
                .display(productRequest.getDisplay())
                .name(productRequest.getName())
                .powerAndBattery(productRequest.getPowerAndBattery())
                .price(productRequest.getPrice())
                .type(productRequest.getType())
                .video(productRequest.getVideo())
                .preview(productRequest.getPreview())
                .sizesAndWeight(productRequest.getSizesAndWeight())
                .resistance(productRequest.getResistance())
                .processor(productRequest.getProcessor())
                .camera(productRequest.getCamera())
                .frontCamera(productRequest.getFrontCamera())
                .videoRecording(productRequest.getVideoRecording())
                .faceID(productRequest.getFaceID())
                .applePay(productRequest.getApplePay())
                .cellularAndWireless(productRequest.getCellularAndWireless())
                .location(productRequest.getLocation())
                .audioPlayback(productRequest.getAudioPlayback())
                .videoPlayback(productRequest.getVideoPlayback())
                .siri(productRequest.getSiri())
                .sensors(productRequest.getSensors())
                .operatingSystem(productRequest.getOperatingSystem())
                .build();
    }
}
