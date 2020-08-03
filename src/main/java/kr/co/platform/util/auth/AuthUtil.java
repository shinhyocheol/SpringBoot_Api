package kr.co.platform.util.auth;

import java.util.Collection;


import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {
	public static boolean hasRole(String role) {
		@SuppressWarnings("unchecked")
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities();
		boolean hasRole = false;
		for (GrantedAuthority authority : authorities) {
			hasRole = authority.getAuthority().equals(role);
			if (hasRole) {
				break;
			}
		}
		return hasRole;
	}

	public static String getRole() {
		@SuppressWarnings("unchecked")
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities();
		String role = "";

		for (GrantedAuthority authority : authorities) {
			role = authority.getAuthority().replace("ROLE_", "");
		}
		return role;
	}

	public static String getID() {
		return ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getUsername();
	}

	public static String getName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	public static String getNo() {
		return String.valueOf(
				((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getRegno());
	}

	public static String getUID() {
		String name = getName();
		String id = getID();
		return String.valueOf(name);
	}

	public static void chkAuth(HttpServletResponse response) {
		if (getRole().equals("ROLE_ANONYMOUS") || (!getRole().contains("1") && !getRole().contains("2") && !getRole().contains("3"))) {
			try {
				response.sendRedirect("/main");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean chkAuthMobile(HttpServletResponse response) {
		return !(getRole().equals("ROLE_ANONYMOUS") || (!getRole().contains("1") && !getRole().contains("2") && !getRole().contains("3")));
	}
}
