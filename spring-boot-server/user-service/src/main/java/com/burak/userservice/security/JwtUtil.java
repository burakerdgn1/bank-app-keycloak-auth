package com.burak.userservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

  private final String SECRET_KEY = "secret"; // Change this in production

  public String generateToken(String username) {
    return Jwts.builder()
      .setSubject(username)
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiry
      .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
      .compact();
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = Jwts.parser()
      .setSigningKey(SECRET_KEY)
      .parseClaimsJws(token)
      .getBody();
    return claimsResolver.apply(claims);
  }

  public boolean isTokenValid(String token, String username) {
    return extractUsername(token).equals(username) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }
}

