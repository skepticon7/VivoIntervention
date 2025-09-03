package org.example.backend.Configuration;

import com.nimbusds.jose.proc.SecurityContext;
import jakarta.xml.bind.PrintConversionEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.backend.DTO.Auth.PasswordDTO;
import org.example.backend.Entities.SuperUser;
import org.example.backend.Entities.Supervisor;
import org.example.backend.Entities.User;
import org.example.backend.Exceptions.NotFoundException;
import org.example.backend.Exceptions.UnauthorizedAccessException;
import org.example.backend.Repository.SuperUserRepository;
import org.example.backend.Repository.SupervisorRepository;
import org.example.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Value("${application.security.jwt.expiryDate}")
    private long JWT_EXPIRATION_TIME;

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SuperUserRepository superUserRepository;

    @Autowired
    private SupervisorRepository supervisorRepository;
    @Autowired
    private UserRepository userRepository;

    public String generateToken(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
        return buildToken(userDetails.getFullName() , roles , userDetails.getId());
    }

    public String getFullName(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getFullName();
    }


    public boolean confirmPassword(PasswordDTO password) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() == null)
            throw new UnauthorizedAccessException("Unauthorized , access denied");
        CustomUserDetails userDetails = this.getAuthenticatedUser();
        return passwordEncoder.matches(password.getPassword(), userDetails.getPassword());
    }

    public CustomUserDetails getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() == null)
            throw new UnauthorizedAccessException("Unauthorized , access denied");
        if(authentication.getPrincipal() instanceof Jwt ){
            return convertJwtToCustomUserDetails((Jwt) authentication.getPrincipal());
        }else if(authentication.getPrincipal() instanceof CustomUserDetails){
            return (CustomUserDetails) authentication.getPrincipal();
        }
        throw new UnauthorizedAccessException("Unauthorized , access denied");
    }

    private String buildToken(String fullName , String roles , Integer id) {
        Instant instant = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .expiresAt(instant.plusMillis(JWT_EXPIRATION_TIME))
                .subject(fullName)
                .claim("scope", roles)
                .claim("id" , id)
                .build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS512).build(),
                jwtClaimsSet
        );
        return jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }

    private CustomUserDetails convertJwtToCustomUserDetails(Jwt jwt) {
        Long userId = jwt.getClaim("id");
        String roles = jwt.getClaim("scope");
        if(roles.contains("ROLE_SUPERUSER")) {
            SuperUser superUser = superUserRepository.findById((userId.intValue()))
                    .orElseThrow(() -> new NotFoundException("superuser with id " + userId + " not found"));
            return new CustomUserDetails(superUser);
        }
        System.out.println(userId.intValue());
        User user = userRepository.findUserById(userId.intValue())
                .orElseThrow(() -> new NotFoundException("user with id : " + userId + " not found"));
        return new CustomUserDetails(user);
    }

}
