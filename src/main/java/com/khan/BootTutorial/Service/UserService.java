package com.khan.BootTutorial.Service;

import com.khan.BootTutorial.model.User;

public interface UserService {

	public User findUserByEmail(String email) ;
	public User saveUser(User user);
}
