package com.shopify.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopify.app.entity.User;
import com.shopify.app.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(  @RequestBody User user  ) {		
		
		try {
			service.registerUser(user) ;
			return ResponseEntity.ok("User Registed !!!") ;
		} catch (Exception e) {
			e.printStackTrace() ;
			return ResponseEntity.badRequest().body(e.getMessage()) ;
		}
		
	}
	
	@GetMapping("/get")
	public ResponseEntity<User> getUser( @RequestParam("id") long id ) {		
		
		try {
			User user = service.getUser(id) ;
			System.out.println(user);
			return ResponseEntity.ok(user) ;
		} catch (Exception e) {
			e.printStackTrace() ;
			return ResponseEntity.badRequest().body(null) ;
		}
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateUser( @RequestParam("id") long id , @RequestBody User user) {		
		
		try {
			service.updateUser(id , user) ;
			return ResponseEntity.ok("User is updated") ;
		} catch (Exception e) {
			e.printStackTrace() ;
			return ResponseEntity.badRequest().body("User is not updated") ;
		}
		
	}
	
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteUser( @RequestParam("id") long id ) {		
		
		try {
			service.deleteUser(id) ;
			return ResponseEntity.ok("User is deleted") ;
		} catch (Exception e) {
			e.printStackTrace() ;
			return ResponseEntity.badRequest().body("User is not deleted") ;
		}
		
	}
	
	
	

}
