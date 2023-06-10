package com.example.userservice.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeletePhotoRequest {
    private String productId;
    private String photoPath;
}
