package org.bos.Achaoub.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import lombok.Data;

@Entity(name = "users")
@Data
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -4727713897942278288L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String userId;

	@Column(nullable = false, length = 50)
	private String nom;

	@Column(nullable = false, length = 50)
	private String prenom;

	@Column(nullable = false, length = 120,unique=true)
	private String email;
	
	@Column(nullable = false)
	private String encryptedPassword;

	@Column(nullable = false,columnDefinition = "boolean default false")
	private Boolean emailVerificationStatus;
	
	@Column(nullable = false)
	private String role;
	
	@Column(nullable = false)
	private int roleID = 0;

}
