package com.example.userservice.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CardRequest {
    private Integer id;
    private String number;
    private String validityDate;
    private String owner;
    private Boolean status;
}
