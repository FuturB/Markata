package com.futureB.backend.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
@Data
public class userDTO {
        private Long id;
        private String firstName;
        private String lastName;
        private  String emailId;
        private LocalDate DOB;


}
