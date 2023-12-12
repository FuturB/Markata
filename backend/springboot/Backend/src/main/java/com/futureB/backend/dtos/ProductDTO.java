package com.futureB.backend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.futureB.backend.Entity.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonPropertyOrder({"id", "name", "type", "description", "price"})
public class ProductDTO {
    private Long Id;
    private String name;
    private String type;
    private String description;
    private Double price;
@JsonIgnore
    private ProductCategory productCategory;
//    private Integer quantityInStock;

}