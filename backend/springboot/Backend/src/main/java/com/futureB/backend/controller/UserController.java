package com.futureB.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.futureB.backend.Entity.ActivationToken;
import com.futureB.backend.Entity.Role;
import com.futureB.backend.Entity.Terms;
import com.futureB.backend.Service.ActivationTokenService;
import com.futureB.backend.Service.EmailService;
import com.futureB.backend.config.JwtService;
import com.futureB.backend.exception.ResourceNotFoundException;
import com.futureB.backend.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.futureB.backend.Entity.User;
import com.futureB.backend.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class UserController {

	private final UserRepository userRepository;
	private final TermsRepository termsRepository;

	private final PasswordEncoder passwordEncoder;
	private final EmailService emailService;
	private final ActivationTokenService activationTokenService;
	private final JwtService jwtService;


	// get all Users
	@GetMapping("/Users")
	public List<User> getAllUsers(){

		return userRepository.findAll();
	}

	//This is for getting the terms and conditions from the database
	@GetMapping("/Terms/{id}")
	public ResponseEntity<Terms> getTermsById(@PathVariable Long id){
		System.out.println(id);
		Terms terms = termsRepository.findById(id);
		return ResponseEntity.ok(terms);
	}

	// create User rest api
// send the Json in this form
//	{
//		"firstName": "asdfasdfas",
//			"lastName": "mm",
//			"emailId": "amir@gmail.com",
//			"password": "123123123",
//			"year" : "1",
//			"month" : "2",
//			"date" : "1"
//	}


	//This is for adding users into the database. It checks if the user email is there or not
	@PostMapping("/users")
	public ResponseEntity<String> createUser(@RequestBody User user) {
		// log
		//System.out.println("\n" + User + "\n");
		System.out.println(userRepository.findByEmailId(user.getEmailId()).isPresent());
		if(!userRepository.findByEmailId(user.getEmailId()).isPresent())
		{
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			System.out.println("Password encryption during registration: " + user.getPassword());
			user.setRole(Role.USER);
			User userInDB = userRepository.save(user);
			var jwtToken = jwtService.generateToken(userInDB);
			ActivationToken activationToken = activationTokenService.createAndPersistActivationToken(userInDB);
			System.out.println(activationToken);
			//emailService.sendActivationEmail(userInDB, activationToken);
			return ResponseEntity.ok("Successful registration");
		}
		return ResponseEntity.status(409).body("User Already Exist");

	}



	//This activates The user using the generated and sent token
	@GetMapping("/users/activate-account")
	public ResponseEntity<String> activateAccount(@RequestParam String token){
		if(activationTokenService.verifedAndAccountActivated(token)){
			return ResponseEntity.ok("Congrats you good to go!, you may try to login now");
//			return "http://localhost:3000/successfulactiavtion
		}else {
			System.out.println("Activation result "+ activationTokenService.verifedAndAccountActivated(token));
			return ResponseEntity.status(401).body("User Already Activated");
		}
	}

	@GetMapping("/users/Product")
	public ResponseEntity<?> findName(@RequestParam String name){
		return ResponseEntity.status(200).body(userRepository.findByfirstNameIgnoreCaseContaining(name).stream()
				.map(user -> user.getFirstName())
				.collect(Collectors.toList()));
	}
	
	// get User by id rest api
//	@GetMapping("/Users/{emailid}")
//	public ResponseEntity<User> getUserByEmailId(@PathVariable String emailId) {
//		User user = userRepository.findByEmailId(emailId).orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + emailId));
////				orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + emailId));
//		return ResponseEntity.ok(user);
//	}
//
	// update User rest api
//	Not needed but here for future functionality
//	@PutMapping("/Users/{id}")
//	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails){
//		User user = userRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
//
//		user.setFirstName(userDetails.getFirstName());
//		user.setLastName(userDetails.getLastName());
//		user.setEmailId(userDetails.getEmailId());
//
//		User updatedUser = userRepository.save(user);
//		return ResponseEntity.ok(updatedUser);
//	}
	
	// delete User rest api
	// not needed but here for future functionality
//	@DeleteMapping("/Users/{id}")
//	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id){
//		User user = userRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
//
//		userRepository.delete(user);
//		Map<String, Boolean> response = new HashMap<>();
//		response.put("deleted", Boolean.TRUE);
//		return ResponseEntity.ok(response);
//	}
	
}
