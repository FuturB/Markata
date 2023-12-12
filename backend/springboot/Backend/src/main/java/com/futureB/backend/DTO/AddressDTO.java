package com.futureB.backend.DTO;
import lombok.Data;


@Data
public class AddressDTO {
    private Long id;
    private String unit_number;
    private String street;
    private  String city;
    private String state;
    private Integer postal_code;
    private String country;


}
