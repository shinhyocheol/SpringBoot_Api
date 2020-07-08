package kr.co.platform.util.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class CustomUserDetails implements UserDetails {
	
	private int regNo;
	private int admin_regno;	
	private String username;
	private String id;
	private String password;
	private String authority;
	private String group_id;
	private boolean isFirst;

	public CustomUserDetails(int regNo, String userName,String id, String password, String authority) {
		this.regNo = regNo;
		this.username = userName;
		this.id = id;
		this.password = password;
		this.authority = authority;
		this.isFirst = true;
	}
	
	public CustomUserDetails(int admin_no, String admin_id, String admin_authority) {
		this.regNo = admin_no;
		this.id = admin_id;
		this.authority = admin_authority;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(authority));

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

	public String getId() {
		return id;
	}
	
	public int getRegNo() {
		return regNo;
	}
	
	public int getAdminRegNo() {
		return admin_regno;
	}

	public String getAuthority() {
		return authority;
	}

	public String getGroupID() {
		return group_id;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
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