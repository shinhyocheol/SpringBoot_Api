package kr.co.platform.util.auth;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kr.co.platform.util.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

	@Value("${spring.jwt.secret}")
	private String secretKey;
	
	private long tokenValidMilisecond = 1000L * 60 * 60; // 한시간만 JWT 토큰 유효
		
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	/**
	 * @param dataMap
	 * @method 설명 : jwt 토큰 생성
	 * @return
	 */
	public String createToken(Map<String, Object> dataMap) {
		final JwtBuilder builder = Jwts.builder();
		Date now = new Date();
		builder.setHeaderParam("typ", "JWT");
		builder.setSubject("x-access-token").setExpiration(new Date(now.getTime() + tokenValidMilisecond));
		builder.claim("admin_id", dataMap.get("admin_id"))
			   .claim("admin_regno", dataMap.get("admin_regno"))
			   .claim("authority_level", dataMap.get("authority_level"));
		builder.signWith(SignatureAlgorithm.HS256, secretKey);		
		return builder.compact();
	}
	
	/**
	 * @method 설명 : request객체 헤더에 담겨 있는 토큰 가져오기
	 * @param dataMap
	 * @return
	 */
	public String resolveToken(HttpServletRequest request) {
        return request.getHeader("x-access-token");
    }
	
	/**
	 * @method 설명 : 토큰 유효시간 만료여부 검사 실행
	 * @param jwtToken
	 * @return
	 */
	public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
	
	/**
	 * @param dataMap
	 * @param token
	 * @return
	 */
	public Authentication getAuthentication(String token, HttpServletRequest request) {
 		if(token != null) {
			Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
			if(this.validateToken(token)) {
				/** 토큰 기반으로 유저의 정보 파싱*/
				int admin_no = claims.get("admin_regno", Integer.class);
				String admin_id = claims.get("admin_id", String.class);
				String admin_authority = claims.get("authority_level", String.class);
				
				/** 오로지 검사용으로만 사용할 객체(이외 다른부분에 사용하지 않음) */
				List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		        roles.add(new SimpleGrantedAuthority(admin_authority));
				
		        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(admin_no, admin_id, roles);
				result.setDetails(new CustomUserDetails(admin_no, admin_id, admin_authority));
				
				return new UsernamePasswordAuthenticationToken(result, "", result.getAuthorities());
			} else {
				return null;
			}		
		} else {
			return null;			
		}
	}

}
