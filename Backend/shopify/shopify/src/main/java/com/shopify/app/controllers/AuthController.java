package com.shopify.app.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopify.app.dto.UserDTO;
import com.shopify.app.entity.User;
import com.shopify.app.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService service ;
	
	@PostMapping("/login")
	public ResponseEntity<User> login( @RequestBody UserDTO user , HttpSession session ) {
		try {
			System.out.println(user);
			User loggedInuser = service.login( user , session ) ;
			return ResponseEntity.ok(loggedInuser) ;
		} catch (Exception e) {
			e.printStackTrace() ;
			return ResponseEntity.ok(null) ;
		}
		
	}
	
	
	@PostMapping("/logout")
	public String logout( HttpSession Session ) {
		Session.invalidate();
		return "User is Logged out" ;
	}
	
	
	@GetMapping("/check-login")
	public ResponseEntity<Boolean> checkLogin( HttpSession session ) {
		User user = (User) session.getAttribute("user") ;
		if( user != null ) {
			return ResponseEntity.ok(true) ;
 		} else {
			return ResponseEntity.ok(false) ;
		}
	}
	
}
