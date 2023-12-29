package com.futureB.backend;

import com.futureB.backend.Entity.ShoppingCart;
import com.futureB.backend.dtos.UserDTO;
import com.futureB.backend.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication

@EnableAsync
public class Markato {

	public static void main(String[] args) {
		SpringApplication.run(Markato.class, args);
System.out.println("Application is starting");



	}

//	@Override
//	public void run(String... args) throws Exception {
//		ShoppingCart cart = new ShoppingCart();
//		shoppingCartRepository.save(cart);
//		System.out.println(cart);
//	}

//	@Bean
//	public PasswordEncoder encoder() {
//		return new BCryptPasswordEncoder();
//	}

	@Bean
	public UserDTO userDTO(){
		return new UserDTO();
	}

}
