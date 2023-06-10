package com.example.userservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserRequest {
    @NotBlank
    @Size(min = 6, max = 30)
    private String phoneNumber;
    @Size(min = 2)
    private String firstName;
    @Size(min = 2)
    private String lastName;


}
