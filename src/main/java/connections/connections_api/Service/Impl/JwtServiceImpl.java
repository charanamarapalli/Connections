package connections.connections_api.Service.Impl;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import connections.connections_api.Service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {
    private static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);    
    private String secretKey;
    
    public JwtServiceImpl() {
        try {
        	logger.debug("Jwt service constructor called, secret key created");
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String generateToken(String userEmail) {
    	logger.debug("generate Key");
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(userEmail)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token valid for 10 hours
                .and()
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
    	logger.debug("returns encoded secret key");
        byte[] keyByte = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyByte);
    }

    private <T> T extractClaim(String jwtToken, Function<Claims, T> claimResolver) {
    	logger.debug("Extract claim resolver");
        final Claims claims = extractAllClaims(jwtToken);
        return claimResolver.apply(claims);
    }
    
    private Claims extractAllClaims(String jwtToken) {
    	logger.debug("Extract all claims");
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }
    
    public String extractUserEmail(String jwtToken) {
    	logger.debug("Extract username from jwttoken");
        return extractClaim(jwtToken, Claims::getSubject);
    }

    public boolean validateToken(String jwtToken, UserDetails userDetails) {
    	logger.debug("Validate jwt token");
        final String userEmail = extractUserEmail(jwtToken);
        return (userEmail.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
    }

    private boolean isTokenExpired(String jwtToken) {
    	logger.debug("verify if token expired or not");
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
    	logger.debug("extract expiration date from claim");
        return extractClaim(jwtToken, Claims::getExpiration);
    }
}
