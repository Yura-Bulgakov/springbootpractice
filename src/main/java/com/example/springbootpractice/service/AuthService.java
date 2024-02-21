package com.example.springbootpractice.service;

import com.example.springbootpractice.repository.UserInfoRepository;
import com.example.springbootpractice.security.UserInfoUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AuthService {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserInfoRepository userInfoRepository;

    public String authUser(String username) {
        UserDetails userDetails = loadUserByUsername(username);
        return jwtService.generateToken(userDetails);
    }

    private UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = new UserInfoUserDetails(userInfoRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", username))));
        List<GrantedAuthority> authorities = Collections.emptyList();

        return new org.springframework.security.core.userdetails.User(
                userDetails.getUsername(),
                userDetails.getPassword(),
                authorities
        );
    }
}
