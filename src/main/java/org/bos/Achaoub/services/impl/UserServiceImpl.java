package org.bos.Achaoub.services.impl;



import java.util.ArrayList;

import org.bos.Achaoub.entities.UserEntity;
import org.bos.Achaoub.repositories.UserRepository;
import org.bos.Achaoub.services.UserService;
import org.bos.Achaoub.shared.Utils;
import org.bos.Achaoub.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	Utils util;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub

		String userID = util.generateUser(10);
		// Verifier si email est existant
		UserEntity userByEmail = userRepo.findByEmail(userDto.getEmail());

		if (userByEmail != null) {
			throw new RuntimeException("Email Deja existant");
		}

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDto, userEntity);

		userEntity.setUserId(userID);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		userEntity.setEmailVerificationStatus(false);
		userEntity.setNom(userDto.getNom());
		userEntity.setPrenom(userDto.getPrenom());
		userEntity.setRole(userDto.getRole());
		userEntity.setRoleID(userDto.getRoleID());
		
		UserEntity newUser = userRepo.save(userEntity);

		UserDto newUserDto = new UserDto();
		BeanUtils.copyProperties(newUser, newUserDto);
		
		return newUserDto;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserEntity userCourant = userRepo.findByEmail(email);
	
		if (userCourant == null) {
			throw new UsernameNotFoundException(email);
		}
//		else {
//			userRepo.save(userCourant);
//			Principal principal = new Principal(userCourant);
//			return principal;
//		}
		 //return User.withUsername(userCourant.getEmail()).password(userCourant.getEncryptedPassword()).roles(userCourant.getRole()).build();	
		return User.withUsername(userCourant.getEmail()).password(userCourant.getEncryptedPassword()).authorities(userCourant.getRoleID() + "").build();
		//return new User(userCourant.getEmail(), userCourant.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {

		UserEntity userEntity = userRepo.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userEntity, userDto);

		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String idUser) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepo.findByUserId(idUser);
		if (userEntity == null)
			throw new UsernameNotFoundException(idUser);

		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDto updateUser(String id, UserDto userDto) {

		UserEntity userEntity = userRepo.findByUserId(id);

		if (userEntity == null)
			throw new UsernameNotFoundException(id);

		userEntity.setNom(userDto.getNom());
		userEntity.setPrenom(userDto.getPrenom());
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		// userEntity.setEncryptedPassword(userDto.getEncryptedPassword());

		UserEntity userUpdated = userRepo.save(userEntity);
		UserDto user = new UserDto();
		BeanUtils.copyProperties(userUpdated, user);

		return user;
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepo.findByUserId(id);
		if (userEntity == null)
			throw new UsernameNotFoundException(id);
		
		userRepo.delete(userEntity);
	}

	@Override
	public ArrayList<UserEntity> listUsers() {
		// TODO Auto-generated method stub
		
		ArrayList<UserEntity> list = (ArrayList<UserEntity>) userRepo.findAll();
		
		return list;
	}

	@Override
	public UserDto updateEmailVerifUser(String id) {
		UserEntity userEntity = userRepo.findByUserId(id);

		if (userEntity == null) {
			throw new UsernameNotFoundException(id);
		}

		userEntity.setEmailVerificationStatus(true);
		UserEntity userUpdated = userRepo.save(userEntity);
		UserDto user = new UserDto();
		BeanUtils.copyProperties(userUpdated, user);

		return user;
	}

}
