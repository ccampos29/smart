package com.solutionsystems.smart.service.impl;

import com.solutionsystems.smart.config.CustomUserDetails;
import com.solutionsystems.smart.jpa.entity.UserEntity;
import com.solutionsystems.smart.jpa.repository.UserRepository;
import com.solutionsystems.smart.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserDetailsService, IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = findByEmail(username);

        return new CustomUserDetails(user);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(
                        () -> {
                            log.error("Información erronea para iniciar sesión.");
                            return new UsernameNotFoundException("Información erronea para iniciar sesión.");
                        }
                );
    }
}
