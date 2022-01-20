package com.airfrance.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.airfrance.demo.entity.User;

/**
 * Spring Data MongoDB repository for the {@link User} entity.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String>  {
	
	
    Optional<User> findOneByEmailIgnoreCase(String email);

}
