package com.helpDesk.user.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.helpDesk.ExceptionHandler.UserAlreadyExist;
import com.helpDesk.ExceptionHandler.UserNotFound;
import com.helpDesk.user.dto.GetUserDto;
import com.helpDesk.user.dto.UpdateUserDto;
import com.helpDesk.user.dto.UserDTO;
import com.helpDesk.user.entity.Constants;
import com.helpDesk.user.entity.Role;
import com.helpDesk.user.entity.User;
import com.helpDesk.user.repository.RoleRepository;
import com.helpDesk.user.repository.UserRepository;
import com.helpDesk.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository  roleRepository;
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private GetUserDto getUserDto;

	// create user
	@Override
	public void createUser(UserDTO userDto) {
		User user = new User(userDto);
		String userName = user.getUserName();
		User byuserName = userRepository.findByuserName(userName);
		if (byuserName != null) {
			throw new UserAlreadyExist(userName);
		}
		Role roleById = roleRepository.findById(Constants.ROLE_NORMAL).get();
		List<Role> role = new ArrayList<>();
		role.add(roleById);
		user.setRole(role);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public void updateUser(String userName, UpdateUserDto updateUserDto) {

		User user = new User(updateUserDto);
		User byuserName = userRepository.findByuserName(userName);
		if (byuserName == null || byuserName.isActive() == true) {
			throw new UserNotFound(userName);
		}
		String UserName = user.getUserName();
		if (UserName != null) {
			byuserName.setUserName(UserName);
		}
		String password = user.getPassword();
		if (password != null) {
			byuserName.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		}
		userRepository.save(byuserName);
	}

	@Override
	public GetUserDto userByName(String userName) {

		User user = userRepository.findByuserName(userName);
		if (user == null) {
			throw new UserNotFound(userName);
		}
		return new GetUserDto(user);
	}

	@Override
	public List<GetUserDto> allUser() {
		List<User> users = userRepository.findAll();
		List<GetUserDto> allUsers = users.stream().map(user -> getUserDto.userToDto(user)).collect(Collectors.toList());
		return allUsers;
	}

	@Override
	public void deleteUser(String userName) {
		User user = userRepository.findByuserName(userName);
		if(user == null)
		{
			throw new UserNotFound(userName);
		}
		user.setActive(true);
		userRepository.save(user);
		
	}

}
