package com.example.Students.Security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final String SECRET = "mysecretkey1234567890mysecretkey1234567890"; 
	private final long EXPIRATION_TIME = 1000 * 60 * 60; 

	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes());
	}

	public String generateToken(String userName) {
		return Jwts.builder().setSubject(userName).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
	}

	public boolean validateToken(String token, String userName) {
		final String extractedUser = extractUsername(token);
		return (extractedUser.equals(userName) && !isTokenExpired(token));
	}

	public String extractUsername(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody().getSubject();
	}

	private boolean isTokenExpired(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
		         .parseClaimsJws(token).getBody().getExpiration().before(new Date());

	}
}