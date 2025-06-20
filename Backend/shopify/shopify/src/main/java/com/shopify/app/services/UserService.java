package com.shopify.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shopify.app.entity.User;
import com.shopify.app.password.util.EncodePasswordUtil;
import com.shopify.app.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private EncodePasswordUtil passwordUtil;

	public void registerUser(User user) {
		String encodePassword = passwordUtil.encodePassword(user.getPassword());
		user.setPassword(encodePassword);

		User existingUser = repository.getUserByUserName(user.getUsername());

		if (existingUser == null ) {
			repository.registerUser(user);
		} else {
			throw new RuntimeException("User with username is already present");
		}

	}

	public User getUser(long id) {
		User user = repository.getUser(id);
		return user;
	}

	public void updateUser(long id, User user) {

		User existingUser = repository.getUser(id);
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		existingUser.setAddress(user.getAddress());
		existingUser.setUsername(user.getUsername());

		repository.updateUser(existingUser);

	}

	public void deleteUser(long id) {
		repository.deleteUser(id);
	}

}
