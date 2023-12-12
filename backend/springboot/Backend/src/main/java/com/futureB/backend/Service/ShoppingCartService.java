package com.futureB.backend.Service;
import com.futureB.backend.Entity.CartItem;
import com.futureB.backend.dtos.CartItemDTO;
import com.futureB.backend.dtos.ProductDTO;
import com.futureB.backend.dtos.ShoppingCartDTO;
import com.futureB.backend.Entity.Product;
import com.futureB.backend.Entity.ShoppingCart;
import com.futureB.backend.exception.ProductNotFoundException;
import com.futureB.backend.exception.UserNotFoundException;
import com.futureB.backend.repository.CartItemRepository;
import com.futureB.backend.repository.ShoppingCartRepository;
import com.futureB.backend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService{
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;
    @Autowired
private UserRepository userRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ShoppingCartService(
            ShoppingCartRepository shoppingCartRepository,
            ProductService productService,
            ModelMapper modelMapper) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }
    public List<ShoppingCartDTO> getAllShoppingCarts() {
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();
        return shoppingCarts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ShoppingCartDTO getShoppingCartById(Long cartId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("ShoppingCart not found with ID: " + cartId));
        return convertToDTO(shoppingCart);
    }


//    public ShoppingCartDTO addProductToCart(Long productId, Long cartId, int quantity) {
//        ShoppingCartDTO shoppingCartDTO = getShoppingCartById(cartId);
//        ShoppingCart shoppingCart=convertToEntity(shoppingCartDTO);
//        ProductDTO productDTO = productService.getProductById(productId).get();
//        Product product= productService.convertToEntity(productDTO);
//        shoppingCart.setUser(userRepository.findById(1));
//        shoppingCartRepository.save(shoppingCart);
//        ShoppingCartDTO shoppingCartDTO2 =convertToDTO(shoppingCart);
//        CartItemDTO cartItemDTO = new CartItemDTO();
//        cartItemDTO.setProduct(productDTO);
//        cartItemDTO.setQuantity(quantity);
//        cartItemDTO.setCart(shoppingCartDTO2);
//          shoppingCartDTO2.getCartItems().add(cartItemDTO);
//
//
//        ShoppingCart savedShoppingCart = shoppingCartRepository.save(convertToEntity(shoppingCartDTO2));
//
//        return convertToDTO(savedShoppingCart);
//    }
//public ShoppingCartDTO addProductToCart(Long productId, Long cartId, int quantity) {
//    // Get the ShoppingCartDTO by ID
//    ShoppingCartDTO shoppingCartDTO = getShoppingCartById(cartId);
//
//    // Convert the ShoppingCartDTO to ShoppingCart entity
//    ShoppingCart shoppingCart = convertToEntity(shoppingCartDTO);
//
//    // Get the ProductDTO by ID
//    ProductDTO productDTO = productService.getProductById(productId)
//            .orElseThrow(() -> new ProductNotFoundException("Product not found"));
//
//    // Convert the ProductDTO to Product entity
//    Product product = productService.convertToEntity(productDTO);
//    if (product.getProductId() == null) {
//        // If the product is not saved, save it first
//        Product savedProduct = productService.saveProduct(product);
//        product = savedProduct;
//    }
//    // Set the user for the shopping cart (assuming you have a userRepository)
//    shoppingCart.setUser(userRepository.findById(1));
//
//    // Save the ShoppingCart entity to update the user and other details
//    shoppingCartRepository.save(shoppingCart);
//
//    // Convert the updated ShoppingCart entity to ShoppingCartDTO
//    ShoppingCartDTO updatedShoppingCartDTO = convertToDTO(shoppingCart);
//
//    // Create a new CartItemDTO
//    CartItem cartItem = new CartItem();
//    cartItem.setProduct(product);
//    cartItem.setQuantity(quantity);
//    cartItem.setCart(shoppingCart);
//CartItem cartItem1=cartItemRepository.save(cartItem);
//    shoppingCart.getCartItems().add(cartItem1);
//
//    // Add the new CartItemDTO to the list of cart items
////    updatedShoppingCartDTO.getCartItems().add(cartItemDTO);
//
//    // Save the updated ShoppingCart entity with the new cart item
//    ShoppingCart savedShoppingCart = shoppingCartRepository.save(shoppingCart);
//
//    // Convert the final ShoppingCart entity to ShoppingCartDTO for the response
//    return convertToDTO(savedShoppingCart);
//}


    public ShoppingCartDTO addProductToCart(Long productId, Long cartId, int quantity) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("ShoppingCart not found with ID: " + cartId));

        ProductDTO productDTO = productService.getProductById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productId));
shoppingCart.setUser(userRepository.findById(1));
shoppingCartRepository.save(shoppingCart);
        Product product = productService.convertToEntity(productDTO);

        // Check if the product is already associated with the cart
        Optional<CartItem> existingCartItem = shoppingCart.getCartItems().stream()
                .filter(cartItem -> cartItem.getProduct().equals(product))
                .findFirst();

        if (existingCartItem.isPresent()) {
            // If the product is already in the cart, update the quantity
            existingCartItem.get().setQuantity(existingCartItem.get().getQuantity() + quantity);
        } else {
            // If the product is not in the cart, create a new cart item
            CartItem newCartItem = new CartItem();
            newCartItem.setProduct(product);
            newCartItem.setQuantity(quantity);
            newCartItem.setCart(shoppingCart);

            shoppingCart.getCartItems().add(newCartItem);
        }

        // Save the updated shopping cart
        shoppingCartRepository.save(shoppingCart);

        return convertToDTO(shoppingCart);
    }


    public ShoppingCartDTO saveOrUpdateShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = convertToEntity(shoppingCartDTO);
        ShoppingCart savedCart = shoppingCartRepository.save(shoppingCart);
        return convertToDTO(savedCart);
    }

    public void deleteShoppingCart(Long cartId) {
        shoppingCartRepository.deleteById(cartId);
    }


    // Helper methods for conversion
    private ShoppingCartDTO convertToDTO(ShoppingCart shoppingCart) {
        return modelMapper.map(shoppingCart, ShoppingCartDTO.class);
    }

    private ShoppingCart convertToEntity(ShoppingCartDTO shoppingCartDTO) {
        return modelMapper.map(shoppingCartDTO, ShoppingCart.class);
    }
}

//package com.futureB.backend.Service;
//
//import com.futureB.backend.Entity.CartItem;
//import com.futureB.backend.Entity.Product;
//import com.futureB.backend.Entity.ShoppingCart;
//import com.futureB.backend.exception.InsufficientStockException;
//import com.futureB.backend.exception.ProductNotFoundException;
//import com.futureB.backend.repository.ProductRepository;
//import com.futureB.backend.repository.ShoppingCartRepository;
//import com.futureB.backend.repository.UserRepository;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class ShoppingCartService {
//
//    private final ShoppingCartRepository shoppingCartRepository;
////    @Autowired
//    private final ProductRepository productRepository;
////    @Autowired
////    ProductService productService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//
//    @Autowired
//    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository,
//                               ProductRepository productRepository) {
//        this.shoppingCartRepository = shoppingCartRepository;
//        this.productRepository = productRepository;
//    }
//
//    public Optional<ShoppingCart> getShoppingCartById(Long cartId) {
//        return shoppingCartRepository.findById(cartId);
//    }
//
//    public ShoppingCart saveOrUpdateShoppingCart(ShoppingCart shoppingCart) {
//        return shoppingCartRepository.save(shoppingCart);
//    }
//
//    public void deleteShoppingCart(Long cartId) {
//        shoppingCartRepository.deleteById(cartId);
//    }
//
//    public ShoppingCart addProductToCart(Long productId, Long cartId, int quantity) {
//        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found"));;
//
//        ShoppingCart cart = shoppingCartRepository.findById(cartId)
//                .orElseThrow(() -> new EntityNotFoundException("ShoppingCart not found"));
//
//        cart.setUser(userRepository.findById(1));
//
//        cart.convertUserToDTO();
//        System.out.println(cart.getUserDTO());
//        CartItem cartItem = new CartItem();
//        cartItem.setProduct(product);
//        cartItem.setCart(cart);
//        cartItem.setQuantity(quantity);
//
////        cart.getCartItems().add(cartItem);
//        cart.addCartItem(cartItem);
//        shoppingCartRepository.save(cart);
//        int availableQuantityInStock = product.getQuantityInStock();
//        if (availableQuantityInStock >= quantity && quantity > 0) {
//            // Update the product quantity in stock
//            product.setQuantityInStock(availableQuantityInStock - quantity);
//            productRepository.save(product);
//
//            // Add the product to the shopping cart with the specified quantity
////            shoppingCart.addProduct(product, quantity);
//
//
//        } else {
//            // Handle insufficient stock or invalid quantity scenario
//            throw new InsufficientStockException("Insufficient stock or invalid quantity");
//        }
//
////         Save or update the shopping cart
////        return saveOrUpdateShoppingCart(cart);
//        return shoppingCartRepository.save(cart);
//
//    }
//
//}
