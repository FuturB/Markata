package com.futureB.backend.dtos;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Component
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private  String emailId;
    private LocalDate DOB;


}
