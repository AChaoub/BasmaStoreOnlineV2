package org.bos.Achaoub.models.requests;

import lombok.Data;

public @Data class UserLoginRequest {
	
	private String email;
	private String password;

}
