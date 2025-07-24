package org.example.backend.Configuration;

import org.example.backend.Entities.SuperUser;
import org.example.backend.Entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final SuperUser superUser;
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(superUser != null){
            return List.of(new SimpleGrantedAuthority("ROLE_SUPERUSER"));
        }else if(user != null){
            return List.of(new SimpleGrantedAuthority("ROLE".concat("_").concat(user.getRole())));
        }
        return List.of();
    }

    public Integer getId(){
        return superUser != null ? superUser.getId() : user.getId();
    }

    public CustomUserDetails(SuperUser superUser){
        this.superUser = superUser;
        this.user = null;
    }

    public CustomUserDetails(User user){
        this.user = user;
        this.superUser = null;
    }


    @Override
    public String getPassword() {
        return superUser != null ? superUser.getPassword() : user.getPassword();
    }

    @Override
    public String getUsername() {
        return superUser != null ? superUser.getEmail() : user.getEmail();
    }
}
