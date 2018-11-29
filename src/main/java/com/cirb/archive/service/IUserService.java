package com.cirb.archive.service;

import java.util.List;

import com.cirb.archive.domain.User;
import com.cirb.archive.service.exception.NotFoundException;

public interface IUserService {
	
	User addUser(User user);

	User findUser(long id) throws NotFoundException;

	List<User> findAllUsers();

	void deleteUser(long id) throws NotFoundException;

	List<User> searchUsers(User userDto) throws NotFoundException;

	User updateUser(User user) throws NotFoundException;
	
	User findUserByUsername(String username) throws NotFoundException;
	
}
