package org.humber.project.services;

import org.humber.project.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ApplicationUserDetailsService extends UserDetailsService {

    boolean registerUser(UserDto userDto);
}

