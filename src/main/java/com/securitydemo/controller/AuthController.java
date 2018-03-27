package com.securitydemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.securitydemo.data.UserRepository;
import com.securitydemo.model.User;


@RestController
public class AuthController {
	
	@Autowired
	UserRepository repo;

	@GetMapping(value="/users")
	public ResponseEntity<List> getUsers()
	{
		List<User> users = repo.findAll();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	 
	@PostMapping(value = "/users")
	public ResponseEntity<?> addUsers(@RequestBody User user)
	{
		User cds = new User();
		cds.setId(user.getId());
		cds.setUsername(user.getUsername());
		PasswordEncoder pe=new BCryptPasswordEncoder();
		cds.setPassword(pe.encode(user.getPassword()));
		cds.setRole(user.getRole());
		//urepo.save(cds);
		repo.save(cds);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping(value="/loginuser")
	public String loginuser() {
		return "User has been Logged in!!!";
	}
	
	@GetMapping(value="/loginadmin")
	public String loginadmin() {
		return "Admin has been Logged in!!!";
	}
	
	/*@GetMapping(value = "/users")
    public ResponseEntity<List> getUsers(){
        List<User> users= userrepo.findAll();
         return new ResponseEntity<>(users,HttpStatus.ACCEPTED);
    }
    
    @PostMapping(value = "/users")
    public ResponseEntity<?> addUsers(@RequestBody User user){
        //List<User> users= userrepo.findAll();
    	
         return new ResponseEntity<>(userrepo.save(user),HttpStatus.CREATED);
    }*/
}
