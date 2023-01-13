package com.cbatech.springsecu.auth.service;

import com.cbatech.springsecu.auth.entity.UserEntity;
import com.cbatech.springsecu.auth.repository.RoleRepository;
import com.cbatech.springsecu.auth.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        Set<GrantedAuthority> authorities = userEntity.getRoles()
                .stream().map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }
}
