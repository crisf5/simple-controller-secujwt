package com.cbatech.springsecu.auth.service;

import com.cbatech.springsecu.auth.config.JwtTokenProvider;
import com.cbatech.springsecu.auth.dto.JwtResponseDto;
import com.cbatech.springsecu.auth.dto.RegisterDto;
import com.cbatech.springsecu.auth.entity.RoleEntity;
import com.cbatech.springsecu.auth.entity.UserEntity;
import com.cbatech.springsecu.auth.repository.RoleRepository;
import com.cbatech.springsecu.auth.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RegisterService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    public RegisterService(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String register(RegisterDto registerDto){

        if(userRepository.existsByEmail(registerDto.getEmail()) ||
                userRepository.existsByUsername(registerDto.getUsername())){
            throw new RuntimeException("Username or email is taken");
        }

        RoleEntity role = roleRepository.findByName("ROLE_USER").get();
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(role);

        UserEntity userEntity = new UserEntity();
        userEntity.setName(registerDto.getName());
        userEntity.setUsername(registerDto.getUsername());
        userEntity.setEmail(registerDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        userEntity.setRoles(roles);

        userRepository.save(userEntity);


        return "Correct register!";
    }
}
