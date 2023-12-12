package com.futureB.backend.controller;

import com.futureB.backend.dtos.ShoppingCartDTO;
import com.futureB.backend.Entity.ShoppingCart;
import com.futureB.backend.Service.ShoppingCartService;
import com.futureB.backend.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shopping-carts")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

@PostMapping("/create")
public void createCart(@RequestBody ShoppingCart request) {
    Long cartId = request.getCartId();
    ShoppingCart shoppingCart = new ShoppingCart(cartId);
    //shoppingCartService.saveOrUpdateShoppingCart(shoppingCart);
    shoppingCartRepository.save(shoppingCart);
}
    @GetMapping("/all")
    public ResponseEntity<List<ShoppingCartDTO>> getAllShoppingCarts() {
        List<ShoppingCartDTO> shoppingCarts = shoppingCartService.getAllShoppingCarts();
        return ResponseEntity.ok(shoppingCarts);
    }
    @GetMapping("/{cartId}")
    public ResponseEntity<ShoppingCartDTO> getShoppingCartById(@PathVariable Long cartId) {
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.getShoppingCartById(cartId);
        return ResponseEntity.ok(shoppingCartDTO);
    }

    @PostMapping("/add-product/{productId}")
    public ResponseEntity<ShoppingCartDTO> addProductToCart(
            @PathVariable Long productId,
            @RequestParam int quantity,
            @RequestParam Long cartId) {
        ShoppingCartDTO updatedCartDTO = shoppingCartService.addProductToCart(productId, cartId, quantity);
        return ResponseEntity.ok(updatedCartDTO);
    }
    @PostMapping("/save")
    public ResponseEntity<ShoppingCartDTO> saveOrUpdateShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        ShoppingCartDTO savedShoppingCart = shoppingCartService.saveOrUpdateShoppingCart(shoppingCartDTO);
        return ResponseEntity.ok(savedShoppingCart);
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<String> deleteShoppingCart(@PathVariable Long cartId) {
        shoppingCartService.deleteShoppingCart(cartId);
        return ResponseEntity.ok("ShoppingCart with ID " + cartId + " deleted successfully.");
    }

}


//import com.futureB.backend.Entity.CartItem;
//import com.futureB.backend.Entity.Product;
//import com.futureB.backend.Entity.ShoppingCart;
//import com.futureB.backend.Service.ProductService;
//import com.futureB.backend.Service.ShoppingCartService;
//import com.futureB.backend.exception.InsufficientStockException;
//import com.futureB.backend.exception.OutOfStockException;
//import com.futureB.backend.exception.ProductNotFoundException;
//import com.futureB.backend.repository.ProductRepository;
//import com.futureB.backend.repository.ShoppingCartRepository;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping("api/v1/shopping-carts")
//public class ShoppingCartController {
//    @Autowired
//    private final ShoppingCartService shoppingCartService;
//
////    @Autowired
////    private ProductService productService;
//
//    @Autowired
//    private ShoppingCartRepository shoppingCartRepository;
//@Autowired
//public ShoppingCartController(ShoppingCartService shoppingCartService) {
//    this.shoppingCartService = shoppingCartService;
//}
//    @GetMapping("/{cartId}")
//    public ResponseEntity<ShoppingCart> getShoppingCartById(@PathVariable Long cartId) {
//        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartById(cartId).get();
//        shoppingCart.convertUserToDTO();
////        return shoppingCartService.getShoppingCartById(cartId);
//        return ResponseEntity.ok(shoppingCart);
//    }
//
////@PostMapping("/create")
////public void createCart(@RequestParam Long cartId)
////{
////    ShoppingCart shoppingCart= new ShoppingCart(cartId);
////    shoppingCartService.saveOrUpdateShoppingCart(shoppingCart);
////}
//@PostMapping("/create")
//public void createCart(@RequestBody ShoppingCart request) {
//    Long cartId = request.getCartId();
//    ShoppingCart shoppingCart = new ShoppingCart(cartId);
//    shoppingCartService.saveOrUpdateShoppingCart(shoppingCart);
//}
//
//
//
//    @PostMapping("/add-product/{productId}")
//public ResponseEntity<?> addProductToCart(
//        @PathVariable Long productId,
//        @RequestParam int quantity,
//        @RequestParam Long cartId)
//{
////    ShoppingCart cart = shoppingCartRepository.findById(cart.getCartId())
////            .orElseThrow(() -> new EntityNotFoundException("ShoppingCart not found"));
////    ShoppingCart existingCart = shoppingCartRepository.findById(cart.getCartId())
////            .orElseThrow(() -> new EntityNotFoundException("ShoppingCart not found"));
////    System.out.println(cart.getCartId());
//    if (cartId==null)
//    {
//        return ResponseEntity.badRequest().body("Cart ID cannot be null");
//    }
//  else{
//
//        ShoppingCart updatedCart = shoppingCartService.addProductToCart(productId, cartId, quantity);
//        return ResponseEntity.ok(updatedCart);
//    }
////    if (cart == null) {
////        cart = new ShoppingCart(cart.getCartId());
////        // Set other properties of the cart if needed
////        return ResponseEntity.ok(cart);
////    }
////    if (existingCart != null) {
////        ShoppingCart updatedCart = shoppingCartService.addProductToCart(productId, cart.getCartId(), quantity);
////        return ResponseEntity.ok(updatedCart);
////    }
////    else {
////        // Handle the case where cartId is null, you may return an error response or handle it based on your business logic
////        return ResponseEntity.badRequest().body("Cart ID cannot be null");
////    }
//}
//
//
//    // ... (other methods)
//
////    {
////        "cartId": null,
////            "products": [
////        {
////
////            "name": "Product1",
////                "type": "Type1",
////                "description": "Description1",
////                "price": 19.99
////        },
////        {
////
////            "name": "Product2",
////                "type": "Type2",
////                "description": "Description2",
////                "price": 29.99
////        }
////  ]
////    }
//    @PostMapping()
//    public ShoppingCart saveOrUpdateShoppingCart(@RequestBody ShoppingCart shoppingCart) {
//        return shoppingCartService.saveOrUpdateShoppingCart(shoppingCart);
//    }
//
//    @DeleteMapping("/{cartId}")
//    public void deleteShoppingCart(@PathVariable Long cartId) {
//
//    shoppingCartService.deleteShoppingCart(cartId);
//    }
//}
//
