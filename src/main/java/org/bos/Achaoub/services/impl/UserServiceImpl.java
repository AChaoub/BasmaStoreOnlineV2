package org.bos.Achaoub.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.bos.Achaoub.entities.UserEntity;
import org.bos.Achaoub.models.requests.UserRequest;
import org.bos.Achaoub.repositories.UserRepository;
import org.bos.Achaoub.services.UserService;
import org.bos.Achaoub.shared.EmailServiceImpl;
import org.bos.Achaoub.shared.Utils;
import org.bos.Achaoub.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
	
	@Autowired
	EmailServiceImpl email;
	
	@Autowired
	UserRepository userRepository;

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
		userEntity.setRoleID(0);
		if (userDto.getRole().equals("Administrateur")) {
			userEntity.setRoleID(1);
			userEntity.setEmailVerificationStatus(true);
		}

		UserEntity newUser = userRepo.save(userEntity);

		UserDto newUserDto = new UserDto();
		BeanUtils.copyProperties(newUser, newUserDto);

		return newUserDto;
	}

	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userCourant = userRepository.findByEmail(email);

//		if (user == null)
//			throw new RuntimeException("utilisateur invalide");
//		Collection<GrantedAuthority> authorities = new ArrayList<>();
//		authorities.add(new SimpleGrantedAuthority(user.getRole()));
//		return new User(user.getEmail(), user.getEncryptedPassword(), authorities);
//
//		UserEntity userCourant = userRepo.findByEmail(email);
//
		if (userCourant == null) {
			throw new UsernameNotFoundException(email);
		}
		//return new User(userCourant.getEmail(), userCourant.getEncryptedPassword(), new ArrayList<>());
		 return User.withUsername(userCourant.getEmail()).password(userCourant.getEncryptedPassword()).roles(userCourant.getRole()).build();

	}
//	@Override
//	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//		UserEntity user = this.userRepo.findByEmail(s);
//		UserPrincipal userPrincipal = new UserPrincipal(user);
//		return userPrincipal;
//	}

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
		userEntity.setRole(userDto.getRole());
		if (userDto.getRole().equals("Client")) {
			userEntity.setRoleID(0);
		} else if (userDto.getRole().equals("Administrateur")) {
			userEntity.setRoleID(1);
			userEntity.setEmailVerificationStatus(true);
		}
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
		String motPasse = util.generateUser(10);

		if (userEntity == null) {
			throw new UsernameNotFoundException(id);
		}

		userEntity.setEmailVerificationStatus(true);
		//userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode("123456"));
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(motPasse));
		UserEntity userUpdated = userRepo.save(userEntity);

		
		UserDto user = new UserDto();
		user.setPassword(motPasse);
		BeanUtils.copyProperties(userUpdated, user);
		
		

		String contenu = "Vous étes un membre , vous pouvez des maintenant acceder a votre espace client."
				+ "vous trouverez votre nouveau mot de passe:"+user.getPassword()+"                                                                   Cordialement";
		String sujet = "Validation de votre Compte";
		email.envoyerEmail(userUpdated.getEmail(), sujet, contenu);
		
		
		return user;
	}

//	public boolean changerMotP(UserDto user) {
//		boolean res = false;
//		if(userRepo.findByUserId(user.getUserId())!=null) {
//			//String motPasse = util.generateUser(10);
//			//user.setEncryptedPassword(bCryptPasswordEncoder.encode(motPasse));
//			//updateUser(user.getUserId(), user);
//			String contenu = "Vous étes un membre , vous pouvez des maintenant acceder a votre espace client, vous trouverez votre nouveau mot de passe"+user.getPassword()+"                               Cordialement";
//			String sujet = "Validation de votre Compte";
//			email.envoyerEmail(user.getEmail(), sujet, contenu);
//			res=true;
//		}	
//		return res;
//	}

}
