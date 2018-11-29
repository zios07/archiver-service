package com.cirb.archive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cirb.archive.domain.User;
import com.cirb.archive.service.IUserService;

@SpringBootApplication
public class ArchiverServiceApplication implements CommandLineRunner {

	@Autowired
	private IUserService service;

	public static void main(String[] args) {
		SpringApplication.run(ArchiverServiceApplication.class, args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Override
	public void run(String... args) throws Exception {
		User user = new User("zakaria.kaoukab@gmail.com", "Zakaria", "Kaoukab", "zios", "zios");
		User user2 = new User("souka.arwa@gmail.com", "Arwa", "Souka", "arwa	", "souka");

		user = service.addUser(user);
		user2 = service.addUser(user2);
	}
}
