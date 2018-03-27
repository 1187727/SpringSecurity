package com.securitydemo.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.securitydemo.model.User;



public interface UserRepository extends MongoRepository<User, Long>  {
	
	User findByUsername(String username);


}
