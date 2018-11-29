package com.cirb.archive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cirb.archive.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByLogin(String login);

}
