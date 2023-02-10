package com.helpDesk.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.helpDesk.user.dto.GetUserDto;
import com.helpDesk.user.dto.UpdateUserDto;
import com.helpDesk.user.dto.UserDTO;

@Service
public interface UserService {
	
	public void createUser(UserDTO userDto);
	public void updateUser(String userName,UpdateUserDto updateUserDto);
	public GetUserDto userByName(String userName);
	public List<GetUserDto> allUser();
	public void deleteUser(String userName);

}
