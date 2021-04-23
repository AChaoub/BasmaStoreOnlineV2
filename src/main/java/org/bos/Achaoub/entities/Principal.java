package org.bos.Achaoub.entities;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import static java.util.Arrays.stream;

public class Principal implements UserDetails {

	private static final long serialVersionUID = 8575398322368976138L;
	private UserEntity user;

	public Principal(UserEntity user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		GrantedAuthority authority = new SimpleGrantedAuthority("role" + user.getRole());
//		authorities.add(authority);
//		return authorities;
		return null;
		//return stream(this.user.getAuthorities()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return this.user.getEncryptedPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getNom();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
