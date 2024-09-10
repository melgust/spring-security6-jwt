package gt.edu.umg.demodb.jwt;

import java.util.Calendar;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import gt.edu.umg.demodb.service.EncryptService;
import gt.edu.umg.demodb.utils.AppProperty;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtProvider {

	private String jwtPassword;
	private int jwtExpiration;

	@Autowired
	EncryptService encryptService;

	public JwtProvider(AppProperty properties) {
		this.jwtPassword = properties.getJwtSecret();
		this.jwtExpiration = properties.getJwtExpiration();
	}

	public String generateJwtToken(Authentication authentication, String key) {
		UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
		Date expiration = getDateExpiration(this.jwtExpiration);
        return Jwts.builder().subject(userPrinciple.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiration)
                .issuer("http://demoumg.com")
                .claim("scopes", userPrinciple.getAuthorities()).claim("loginKey", key)
                .signWith(getSecretKey(), Jwts.SIG.HS512)
                .compact();
    }
	
	public boolean validateJwtToken(String token) {
		try {
			Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

    public String getUsernameFromJWtToken(String token) {
        if (isTokenExpired(token)) {
            return null;
        }
        return getClaims(token, Claims::getSubject);
    }

    public <T> T getClaims(String token, Function<Claims, T> resolver) {
        return resolver.apply(Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload());
    }

    public boolean isTokenExpired(String token) {
        Date expiration = getClaims(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = this.jwtPassword.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

	public long getUserIdFromJwt(Authentication authentication, HttpServletRequest request) {
		long personaId;
		String token = request.getHeader("Authorization");
		token = token.replace("Bearer ", "");
		if (isTokenExpired(token)) {
            return 0;
        }
		String key = getClaims(token, Claims::getSubject);
		key = encryptService.decrypt(key);
		personaId = Long.parseLong(key);
		return personaId;
	}

	private static Date getDateExpiration(int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR, hours);
		return calendar.getTime();
	}

}