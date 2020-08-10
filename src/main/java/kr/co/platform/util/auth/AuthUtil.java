package kr.co.platform.util.auth;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {
	
	public static String getID() {
		return ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getUsername();
	}

	public static String getNo() {
		return String.valueOf(
				((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getRegno());
	}
	
	public static String getAuth() {
		return String.valueOf(
				((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getAuthority());
	}

}
