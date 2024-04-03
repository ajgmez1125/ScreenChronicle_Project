package org.humber.project.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.humber.project.dto.UserDto;
import org.humber.project.repositories.UserJPARepository;
import org.humber.project.repositories.entities.UserEntity;
import org.humber.project.services.ApplicationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApplicationUserDetailsServiceImpl implements ApplicationUserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserJPARepository userRepository;

    @Autowired
    public ApplicationUserDetailsServiceImpl(PasswordEncoder passwordEncoder, UserJPARepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles().split(","))
                .build();
    }

    @Override
    public boolean registerUser(UserDto userDto) {
        try{
            if(userRepository.existsByUsername(userDto.getUsername())){
                log.info("User already exists");
                return false;
            }
            UserEntity user = new UserEntity();
            user.setUsername(userDto.getUsername());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setRoles(userDto.getRoles());
            userRepository.save(user);
            return true;
        } catch (Exception e){
            log.error("Error registering user ", e);
        }
        return false;
    }
}