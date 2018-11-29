package com.cirb.archive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cirb.archive.domain.User;
import com.cirb.archive.repositories.UserRepository;
import com.cirb.archive.service.exception.NotFoundException;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repo.save(user);
	}

	@Override
	public User findUser(long id) throws NotFoundException {
		if (!repo.existsById(id))
			throw new NotFoundException("USER.NOT.FOUND", "No user found with id: " + id);
		return repo.findById(id).get();
	}

	@Override
	public List<User> findAllUsers() {
		List<User> users = repo.findAll();
		users.stream().forEach(user -> {
			user.setPassword(null);
		});
		return users;
	}

	@Override
	public void deleteUser(long id) throws NotFoundException {
		if (!repo.existsById(id))
			throw new NotFoundException("USER.NOT.FOUND", "No user found with id: " + id);
		repo.deleteById(id);
	}

	@Override
	public List<User> searchUsers(User userDto) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User user) throws NotFoundException {
		if (!repo.existsById(user.getId()))
			throw new NotFoundException("USER.NOT.FOUND", "No user found with id: " + user.getId());
		return repo.save(user);
	}

	@Override
	public User findUserByUsername(String login) throws NotFoundException {
		User user = repo.findByLogin(login);
		if (user == null)
			throw new NotFoundException("USER.NOT.FOUND", "No user found with username: " + login);
		return user;
	}

}
