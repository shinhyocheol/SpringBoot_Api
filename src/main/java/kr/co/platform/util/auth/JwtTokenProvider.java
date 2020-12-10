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
import kr.co.platform.api.sign.dto.LoginInfo;
import kr.co.platform.api.sign.dto.MemberResultDetail;
import kr.co.platform.util.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

	@Value("${spring.jwt.secret}")
	private String secretKey;
	
	private long tokenValidMilisecond = 1000L * 60 * 60; // 발급토큰 유효시간(한시간으로 설정)
		
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	/** @method 설명 : jwt 토큰 생성 */
	public String createToken(MemberResultDetail member) {
		
		final JwtBuilder builder = Jwts.builder();
		
		Date now = new Date();
		
		builder.setHeaderParam("typ", "JWT");
		
		builder.setSubject("x-access-token").setExpiration(new Date(now.getTime() + tokenValidMilisecond));
		
		builder.claim("id", member.getMemberId())
			   .claim("regNo", member.getRegNo())
			   .claim("authorityLevel", member.getAuthorityLevel());
		
		builder.signWith(SignatureAlgorithm.HS256, secretKey);
		
		return builder.compact();
	}
	
	/** @method 설명 : request객체 헤더에 담겨 있는 토큰 가져오기 */
	public String resolveToken(HttpServletRequest request) {
        return request.getHeader("x-access-token");
    }
	
	/** @method 설명 : 토큰 유효시간 만료여부 검사 실행 */
	public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
	
	public Authentication getAuthentication(String token, HttpServletRequest request) {
 		if(token != null) {
			Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
			
			/** 토큰 기반으로 유저의 정보 파싱*/
			int memberNo = claims.get("regNo", Integer.class);
			String memberId = claims.get("id", String.class);
			String memberAuthority = claims.get("authorityLevel", String.class);
			
			List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
	        roles.add(new SimpleGrantedAuthority(memberAuthority));
			
	        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(memberNo, memberId, roles);
			result.setDetails(new CustomUserDetails(memberNo, memberId, memberAuthority));
			
			return new UsernamePasswordAuthenticationToken(result, "", result.getAuthorities());
		} else {
			return null;			
		}
	}

}


