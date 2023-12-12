package com.futureB.backend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItemDTO {
    private Long cartItemId;
    private ProductDTO product;
    @JsonIgnore
    private ShoppingCartDTO cart;
    private int quantity;


    // Add any other fields or methods as needed
}
