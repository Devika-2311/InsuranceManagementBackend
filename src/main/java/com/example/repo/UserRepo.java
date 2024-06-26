package com.example.repo;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.model.User;
public interface UserRepo extends JpaRepository<User,Long> {
	@Query(value = "SELECT u FROM User u WHERE u.emailId = :emailId AND u.password = :password")
	 Optional<User> findByEmailIdAndPassword(@Param("emailId") String emailId, @Param("password") String password);
}
