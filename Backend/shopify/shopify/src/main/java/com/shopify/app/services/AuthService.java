package com.shopify.app.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopify.app.dto.UserDTO;
import com.shopify.app.entity.User;
import com.shopify.app.password.util.EncodePasswordUtil;
import com.shopify.app.repositories.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository repository ;
	
	@Autowired
	private EncodePasswordUtil passwordUtil ;
	
	public User login( UserDTO user , HttpSession session) {
		
		User existingUser = repository.getUserByUserName(user.getUsername());
		
		
//		if( existingUser != null ) {
//			session.setAttribute("user", existingUser); // session code
//			return existingUser;
//		} else {
//			throw new RuntimeException("Entered Username or password is incorrect") ;
//		}
		
		
		if( existingUser != null && passwordUtil.matchPassword(user.getPassword(), existingUser.getPassword()) ) {
			session.setAttribute("user", existingUser); // session code
			return existingUser;
		} else {
			throw new RuntimeException("Entered Username or password is incorrect") ;
		}

	}

}
