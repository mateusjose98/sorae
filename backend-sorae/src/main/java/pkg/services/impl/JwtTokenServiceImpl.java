package pkg.services.impl;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pkg.entities.JwtToken;
import pkg.entities.PostgresUserDetails;
import pkg.repositories.JwtTokenRepository;
import pkg.services.JwtTokenService;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

	private static final String AUTH="auth";
    private static final String AUTHORIZATION="Authorization";
    private String secretKey="secret-key";
    private long validityInMilliseconds = 3600000; // 1h

    @Autowired
    private JwtTokenRepository jwtTokenRepository;
    
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    @Override
    public String createToken(String username, List<String> roles) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put(AUTH, roles);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        String token =  Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
        jwtTokenRepository.save(new JwtToken(token));
        return token;
    }

    @Override
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(AUTHORIZATION);
        System.out.println("TOKEN " + bearerToken);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        if (bearerToken != null ) {
            return bearerToken;
        }
        return null;
    }

    @Override
    public boolean validateToken(String token) {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
    }
    
    @Override
    public boolean isTokenPresentInDB (String token) {
        return jwtTokenRepository.findById(token).isPresent();
    }
    
    @Override
    public UserDetails getUserDetails(String token) {
        String userName = getUsername(token);
        List<String> roleList = getRoleList(token);
        UserDetails userDetails = new PostgresUserDetails(userName,roleList.toArray(new String[roleList.size()]));
        return userDetails;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<String> getRoleList(String token) {
        return (List<String>) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).
                getBody().get(AUTH);
    }

    @Override
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
    
    @Override
    public Authentication getAuthentication(String token) {
        //using data base: uncomment when you want to fetch data from data base
        //UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        //from token take user value. comment below line for changing it taking from data base
        UserDetails userDetails = getUserDetails(token);
        return new UsernamePasswordAuthenticationToken(userDetails, "", 
        		userDetails.getAuthorities());
    }

	@Override
	public void delete(String token) {
		jwtTokenRepository.delete(new JwtToken(token));
	}
}
