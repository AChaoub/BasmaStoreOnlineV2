package org.bos.Achaoub.models.responces;

import lombok.Data;

public @Data class UserResponse {

	private int id;
	private String userId;
	private String nom;
	private String prenom;
	private String email;
	private boolean emailVerificationStatus;
	private String role;

	public UserResponse() {

	}
}
