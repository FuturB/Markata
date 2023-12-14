package com.futureB.backend.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "shopping_carts")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    @OneToOne
   @JoinColumn(name="user_id")
//    @JsonIgnore
    private User user;
//    @Transient
//    private UserDTO user;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<CartItem>  cartItems= new ArrayList<>();

    private String totalPrice= getTotalPriceJSON();

    public ShoppingCart(Long cartId)
    {
        this.cartId=cartId;
    }
    public void addCartItem(CartItem cartItem) {
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        cartItems.add(cartItem);
//        cartItem.setCart(this); // Ensure bidirectional relationship is maintained
    }
//    @JsonIgnore
    public Double getTotalPrice() {
        if (cartItems == null) {
            return 0.0;
        }

        return cartItems.stream()
                .mapToDouble(cartItem -> cartItem.getProduct().getPrice() * cartItem.getQuantity())
                .sum();
    }
//    public void convertUserToDTO() {
//        this.userDTO = new UserDTO();
//        this.userDTO.setId(this.user.getId());
//        this.userDTO.setFirstName(this.user.getFirstName());
//    }

    public String getTotalPriceJSON() {
        double totalPrice = getTotalPrice();
        String formattedTotalPrice = String.format(Locale.US, "%.2f", totalPrice);
        return formattedTotalPrice;
//        return "{\"totalPrice\": " + formattedTotalPrice + "}";
    }
}


