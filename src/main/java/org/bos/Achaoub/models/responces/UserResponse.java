package org.bos.Achaoub.models.responces;

import lombok.Data;

public @Data class UserResponse {

	private int id;
	private String userId;
	private String nom;
	private String prenom;
	private String email;
	private boolean verificationEmail;
	private String role;

	public UserResponse() {

	}

//	public UserResponse(String nom, String prenom ,String email,String userId,String role,boolean verif) {
//		this.nom = nom;
//		this.prenom=prenom;
//		this.email = email;
//		this.userId  = userId;
//		this.role = role;
//		this.verificationEmail=verif;
//	}
//	public UserResponse(String nom, String prenom ,String email,String userId,String role) {
//		this.nom = nom;
//		this.prenom=prenom;
//		this.email = email;
//		this.userId  = userId;
//		this.role = role;
//		this.verificationEmail=false;
//	}

}
