package org.bos.Achaoub.services;

import java.util.ArrayList;
import org.bos.Achaoub.entities.UserEntity;
import org.bos.Achaoub.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	public UserDto createUser(UserDto userDto);

	UserDto getUser(String email);

	UserDto getUserByUserId(String idUser);

	UserDto updateUser(String id, UserDto userDto);
	UserDto updateEmailVerifUser(String id);

	void deleteUser(String id);

	ArrayList<UserEntity> listUsers();
}
