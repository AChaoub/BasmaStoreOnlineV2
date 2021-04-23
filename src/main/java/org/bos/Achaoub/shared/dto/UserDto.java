package org.bos.Achaoub.shared.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

public @Data class UserDto implements Serializable {

	private static final long serialVersionUID = -8593162992891859581L;
	private int id;
	private String userId;
	private String nom;
	private String prenom;
	private String email;
	private String password;
	private String encryptedPassword;
	private Boolean emailVerificationStatus;
	private String role;
	private int roleID;
	

}
