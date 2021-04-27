package org.bos.Achaoub.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Entity(name = "users")
@Data
public  class  UserEntity implements Serializable {

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
	
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(	name = "user_roles", 
//				joinColumns = @JoinColumn(name = "user_id"), 
//				inverseJoinColumns = @JoinColumn(name = "role_id"))
//	private Set<Role> roles = new HashSet<>();
	
	
	public List<String> getRoleList(){
        if(this.role.length() > 0){
            return Arrays.asList(this.role.split(","));
        }
        return new ArrayList<>();
    }

}
