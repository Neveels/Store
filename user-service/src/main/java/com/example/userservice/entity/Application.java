package com.example.userservice.entity;

import com.example.userservice.entity.enums.USER_ROLE;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    @Column(length = 1024)
    private String message;
    @Enumerated(EnumType.STRING)
    private USER_ROLE role;
}
