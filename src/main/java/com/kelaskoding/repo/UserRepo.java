package com.kelaskoding.repo;

import org.springframework.data.repository.CrudRepository;

import com.kelaskoding.entity.User;


public interface UserRepo extends CrudRepository<User, Long>{

	public User findByEmail(String email);
	
}
