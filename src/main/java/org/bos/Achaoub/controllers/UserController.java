package org.bos.Achaoub.controllers;

import java.util.ArrayList;

import org.bos.Achaoub.entities.UserEntity;
import org.bos.Achaoub.models.requests.UserRequest;
import org.bos.Achaoub.models.responces.UserResponse;
import org.bos.Achaoub.services.UserService;
import org.bos.Achaoub.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
public class UserController {
	
	

	@Autowired
	UserService userService;

	@GetMapping(path = "/{id}")
	public UserResponse getUser(@PathVariable String id) {

		UserDto userDto = userService.getUserByUserId(id);

		UserResponse userResponse = new UserResponse();

		BeanUtils.copyProperties(userDto, userResponse);

		return userResponse;
	}

	@PostMapping
	public UserResponse createUser(@RequestBody UserRequest user) {

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(user, userDto);
		

		UserDto createUser = userService.createUser(userDto);

		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(createUser, userResponse);

		return userResponse;
	}

	@PutMapping(path = "/{id}")
	public UserResponse updateUser(@PathVariable String id, @RequestBody UserRequest userRequest) {

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);

		UserDto updateUser = userService.updateUser(id, userDto);

		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(updateUser, userResponse);

		return userResponse;
	}

	@DeleteMapping(path = "/{id}")
	public String deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
		return "delete user was called";
	}

	@GetMapping(path = "/list")
	public ArrayList<UserResponse> getAllUsers() {

		ArrayList<UserEntity> list = userService.listUsers();
		ArrayList<UserResponse> listR = new ArrayList<UserResponse>();

		for (UserEntity user : list) {
			UserResponse userResponse = new UserResponse();
			BeanUtils.copyProperties(user, userResponse);
			listR.add(userResponse);
		}

		return listR;
	}


	@Secured(value = "Administrateur")
	@GetMapping(value = "/Valid/{id}")
	public UserResponse validClientById(@PathVariable String id) {
		UserDto client = userService.getUserByUserId(id);
		
		if (client != null) {
			if ((client.getEmailVerificationStatus() == false) && (client.getRole().equals("Client"))) {
				client = userService.updateEmailVerifUser(id);
			} else if (client.getRole().equals("Administrateur")) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST.value(), "this user is an Admin", null);
			}
			else  {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST.value(), "this user already verified", null);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST.value(), "User Not Found!!!", null);
		}


		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(client, userResponse);
		return userResponse;
	}

}
