package org.example.backend.Configuration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.backend.Repository.SuperUserRepository;
import org.example.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Value("${application.security.jwt.expiryDate}")
    private long JWT_EXPIRATION_TIME;

    @Autowired
    private JwtEncoder jwtEncoder;

    public String generateToken(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
        return buildToken(userDetails.getUsername() , roles , userDetails.getId());
    }

    private String buildToken(String username , String roles , Integer id) {
        Instant instant = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .expiresAt(instant.plusMillis(JWT_EXPIRATION_TIME))
                .subject(username)
                .claim("scope", roles)
                .claim("id" , id)
                .build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS512).build(),
                jwtClaimsSet
        );
        return jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }

}
