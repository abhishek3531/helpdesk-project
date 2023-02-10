package com.helpDesk.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helpDesk.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	
	//@Query(value="select * from user where active=false AND user_name=?",nativeQuery = true)
	public User findByuserName(String userName);
	
	@Query(value="select * from user WHERE active=false",nativeQuery = true)
	public List<User> getAllUser();
}
