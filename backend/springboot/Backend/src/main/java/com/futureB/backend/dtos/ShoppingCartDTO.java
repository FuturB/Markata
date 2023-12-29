package com.futureB.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShoppingCartDTO {
    private Long cartId;
    private UserDTO2 user;
    private List<CartItemDTO> cartItems;
    private String totalPrice;

    // Add any other fields or methods as needed
}
