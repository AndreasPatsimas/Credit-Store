package gr.pwc.assignment.dto.authenticate;

import gr.pwc.assignment.domain.StaffMember;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsDTO implements UserDetails {
	
	private final String username;
	
	private final String password;

	private final List<GrantedAuthority> authorities;

	public UserDetailsDTO(StaffMember staffMember) {
		super();
		
		this.username = staffMember.getUserName();
		
		this.password = staffMember.getPassword();

		this.authorities = List.of(new SimpleGrantedAuthority(staffMember.getAuthority().getDescription().name()));
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		
		return username;
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
