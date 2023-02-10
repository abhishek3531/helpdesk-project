package com.helpDesk.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpDesk.user.dto.GetUserDto;
import com.helpDesk.user.dto.UpdateUserDto;
import com.helpDesk.user.dto.UserDTO;
import com.helpDesk.user.serviceImpl.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@PostMapping("/")
	public ResponseEntity<?> Create(@Valid @RequestBody UserDTO userDto)
	{
		userServiceImpl.createUser(userDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PatchMapping("/{userName}")
	public ResponseEntity<?> updateUser(@Valid @PathVariable String userName,@RequestBody UpdateUserDto updateUserDto)
	{
		userServiceImpl.updateUser(userName, updateUserDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/{userName}")
	public ResponseEntity<GetUserDto> userByName(@PathVariable String userName)
	{
		GetUserDto userByName = userServiceImpl.userByName(userName);
		return new ResponseEntity<GetUserDto>(userByName,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<GetUserDto>> getAllUsers()
	{
		List<GetUserDto> allUser = userServiceImpl.allUser();
		
		return new ResponseEntity<List<GetUserDto>>(allUser,HttpStatus.OK);
	}

	@DeleteMapping("/{userName}")
	public ResponseEntity<?> deleteUser(@PathVariable String userName)
	{
		userServiceImpl.deleteUser(userName);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
