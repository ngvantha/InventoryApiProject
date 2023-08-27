package com.inventory.config.JWT;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventory.db2.entities.AppUser;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class AuthenticationModel implements Serializable, UserDetails  {
	private static final long serialVersionUID = 1L;

	private UUID id;

	private String username;

	private String email;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public AuthenticationModel(UUID id, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	
	public static AuthenticationModel build(AppUser user) {
		List<GrantedAuthority> authorities = user.getAppRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
		return new AuthenticationModel(
				user.getId(),
				user.getUserName(),
				user.getEmail(),
				user.getPasswordHash(),
				authorities);
	}
	
	
	///tao ra lop so sanh xem gia tri co thay doi trong oject khong
	  @Override
	  public boolean equals(Object o) {
	    if (this == o)
	      return true;
	    if (o == null || getClass() != o.getClass())
	      return false;
	    AuthenticationModel user = (AuthenticationModel) o;
	    return Objects.equals(id, user.id);
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
