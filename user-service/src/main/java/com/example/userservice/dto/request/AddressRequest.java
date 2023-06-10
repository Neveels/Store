package com.example.userservice.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressRequest {
    private Integer id;
    private String country;
    private String state;
    private String city;
    private String street;
    private String house;
    private String apartment;

}
