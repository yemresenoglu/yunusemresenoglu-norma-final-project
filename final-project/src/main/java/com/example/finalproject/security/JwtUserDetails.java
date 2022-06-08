package com.example.finalproject.security;


import com.example.finalproject.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter

public class JwtUserDetails implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private JwtUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {

        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static JwtUserDetails create(User user) {
        List<GrantedAuthority> authoritiesList = new ArrayList<>();
        if (user.getUserType().equals("CUSTOMER")) {
            authoritiesList.add(new SimpleGrantedAuthority("CUSTOMER"));
        } else if (user.getUserType().equals("OFFICER")) {
            authoritiesList.add(new SimpleGrantedAuthority("OFFICER"));
        } else if (user.getUserType().equals("CUSTOMER")) {
            authoritiesList.add(new SimpleGrantedAuthority("ADMIN"));
        }

        return new JwtUserDetails(user.getUserName(), user.getPassword(), authoritiesList);
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
